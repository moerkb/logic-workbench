/** --------------------------------------------------------------------------
 * Implementation of variation constraint (vc) macro
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mml is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mml is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: vc.java 498 2008-08-20 09:42:13Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.varmod;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * req( v, v1, ..., vn )
 * </h3>
 * <h5>
 * Generates requires conditions.
 * </h5>
 * <br/>Implemented by vc( "req" ).
 * 
 * <h3>
 * excl( v, v1, ..., vn )
 * </h3>
 * <h5>
 * Generates excludes conditions.
 * </h5>
 * <br/>Implemented by vc( "excl" ).
 * 
 * @author Burkhardt Renz
 */
public class vc extends Macro {
	
 	private static Logger logger = 
		Logger.getLogger( vc.class.getName() );
	
 	enum Mode {
  	REQ,
  	EXCL,
  }
 	
 	private Mode mode = Mode.REQ; 	


	public vc( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
		// default Mode.REQ
		if ( mode.equals("excl") ) {
			this.mode = Mode.EXCL;
		}
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	if ( size < 3 ) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' has %d arguments, expected >= 2", macroName, size-1) );
  	}	
			
  	StringBuffer sb = new StringBuffer();
  	String v = macArgs.get( 1 );
  	sb.append( "(" );
  	int i = 0;
  	for ( String vi : macArgs.subList(2,size) ) {
  		i++;
  		sb.append( String.format( "(%s->%s%s)", v, mode == Mode.REQ ? "" : "!", vi) );
  		if ( i < size-2 ) {
  			sb.append("&");
  		}
  	}
  	sb.append( ") &\n" );
  	
		String result = sb.toString();
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }

}
