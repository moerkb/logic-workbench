/** --------------------------------------------------------------------------
 * Implementation of macro 'dnl'
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
 * $Id: dnl.java 363 2008-07-11 03:35:33Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Input;
import mmp.engine.Macro;

/**
 * <h3>
 * dnl
 * </h3>
 * <h5>
 * discard to next line
 * </h5>
 * <br/>Implemented by dnl().
 * 
 * @author Burkhardt Renz
 */
public class dnl extends Macro {
	
	private static Logger logger =  Logger.getLogger( dnl.class.getName() );
	
	public dnl() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}

	@Override
  public String call( List<String> macArgs, EngineContext engineContext ) throws IOException {
  	
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		Input source = engineContext.getInput();
		while ( true ) {
			int ch = source.readChar();
			if ( ch == -1 || (char)ch == '\n' )
				return "";
		}	
  }
  
}
