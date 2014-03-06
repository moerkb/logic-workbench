/** --------------------------------------------------------------------------
 * Implementation of macro 'ifdef'
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
 * $Id: ifdef.java 384 2008-07-15 03:05:08Z brenz $
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
 * ifdef( name, expand_if[, expand_else] )
 * </h3>
 * <h5>
 * conditional expansion
 * </h5>
 * <br/>Implemented by ifdef().
 * 
 * @author Burkhardt Renz
 *
 */
public class ifdef extends Macro {
	
	private static Logger logger =  Logger.getLogger( ifdef.class.getName() );
	
	public ifdef() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size < 3 || size > 4) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' is called with %d arguments, expected 2 or 3", macArgs.get(0), size-1) );
		}
		
		String name = macArgs.get(1);
		String expand_if = macArgs.get(2);
		String expand_else = (size == 4) ? macArgs.get(3) : "";
		
		String result = (engineContext.getMacroRegistry().getMacro(name) != null) ? 
													expand_if : expand_else;
		
  	if ( !result.isEmpty()) {
 			logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
  	return result;
  }

}
