/** --------------------------------------------------------------------------
 * Implementation of macro 'ifelse'
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gieﬂen-Friedberg University of Applied Sciences.
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
 * $Id: ifelse.java 385 2008-07-15 03:56:52Z brenz $
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
 * ifelse( comment ) <br/>
 * ifelse( string1, string2, equal [, not_equal] ) <br/>
 * ifelse( string1, string2, equal1, string3, string4, equal2, ...[, not_equal] ) <br/>
 * </h3>
 * <h5>
 * conditional expansion
 * </h5>
 * <br/>Implemented by ifelse().
 * 
 * @author Burkhardt Renz
 *
 */
public class ifelse extends Macro {
	
	private static Logger logger =  Logger.getLogger( ifelse.class.getName() );
	
	public ifelse() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size == 2 ) {
			return "";
		}
		if ( size == 3 ) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' is called with %d arguments, expected not 2", macArgs.get(0), size-1) );
		}
		
		String result = null;
		List<String> args = macArgs.subList( 1, size );
		while ( result == null ) {
			int argc = args.size();
			if ( args.get(0).equals(args.get(1)) ) {
				result = args.get(2);
			} else {
				switch ( argc ) {
					case 3: 
						result = "";
						break;
					case 4:
					case 5:
						result = args.get(3);
						break;
					default: // >=6
						args = args.subList( 3, argc );
						break;
				}
			}
		}
		
  	if ( !result.isEmpty()) {
 			logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }
  
}
