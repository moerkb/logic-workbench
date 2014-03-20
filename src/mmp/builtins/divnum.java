/** --------------------------------------------------------------------------
 * Implementation of macro 'divnum'
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mmp is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mmp is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: divnum.java 360 2008-07-11 02:33:14Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * divnum
 * </h3>
 * <h5>
 * diversion number
 * </h5>
 * <br/>Implemented by divnum().
 * 
 * @author Burkhardt Renz
 */
public class divnum extends Macro {
	
	private static Logger logger =  Logger.getLogger( divnum.class.getName() );
	
	public divnum() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size != 1 ) {
			throw new SyntaxErrorException( 1001,
					String.format("divnum has %d arguments, expected 0", size-1) );
		}
		
		String result = String.format( "%d", 
				engineContext.getOutput().getCurrentDivnum( ));
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		
		return result;
  }

}
