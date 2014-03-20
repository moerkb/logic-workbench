/** --------------------------------------------------------------------------
 * Implementation of variation point (vp) macro
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
 * $Id: vp.java 498 2008-08-20 09:42:13Z brenz $
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
 * vp( varpoint, vd1, ..., vdn )
 * </h3>
 * <h5>
 * Defines variation point by variation dependencies vd1, ..., vdn.
 * </h5>
 * <br/>Implemented by vp().
 *  
 * @author Burkhardt Renz
 */
public class vp extends Macro {
	
 	private static Logger logger = 
		Logger.getLogger( vp.class.getName() );

	public vp( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
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
			
		String begQuote = engineContext.getSettings().getBegQuote();
		String endQuote = engineContext.getSettings().getEndQuote();;
		
  	String vp = macArgs.get( 1  );
  	StringBuffer sb = new StringBuffer();
  	List<String> args = macArgs.subList(2, size);
  	// construct subexpressions _man, _opt, _alt
  	for ( String v: args ) {
  		// patch
  		if ( v.startsWith("man(") ) {
  			sb.append( "_man(" + begQuote + vp + endQuote + "," + v.substring(4)); 
  		}	
  		else if ( v.startsWith("opt(") ) {
  			sb.append( "_opt(" + begQuote + vp + endQuote + "," + v.substring(4)); 
  		}
  		else if ( v.startsWith("alt(") ) {
  			sb.append( "_alt(" + begQuote + vp + endQuote + "," + v.substring(4)); 
  		}
  		sb.append( " &\n" );
  	}
		String result = sb.toString();
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }
}	
