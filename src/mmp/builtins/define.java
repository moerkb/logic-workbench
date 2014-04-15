/** --------------------------------------------------------------------------
 * Implementation of macro 'define'
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
 * $Id: define.java 359 2008-07-11 02:25:14Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;
import mmp.engine.UserMacro;

/**
 * <h3>
 * define( name [,expansion] )
 * </h3>
 * <h5>
 * define macro
 * </h5>
 * <br/>Implemented by define( "define" ).<br/>
 *
 * <h3>
 * pushdef( name [, expansion] )
 * </h3>
 * <h5>
 * define macro on top of definition stack
 * </h5>
 * <br/>Implemented by define( "pushdef" ).<br/>
 *
 * @author Burkhardt Renz
 */
public class define extends Macro {
	
	private static Logger logger =  Logger.getLogger( define.class.getName() );
	
	private String mode;
	
	/**
	 * @pre mode == "define" or mode == "pushdef"
	 * @param mode
	 */
	public define( String mode ) {
		super( Style.NEEDS_PARENTHESIS, mode );
		this.mode = mode;
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		String name = macArgs.get( 1 );
		String expansion = (size > 2) ? macArgs.get(2) : "";
		
		// define and register user macro
		UserMacro userMacro = new UserMacro( name, expansion );
		if ( mode.equals("define") ) {
			engineContext.getMacroRegistry().registerMacro( name, userMacro );
		} else if ( mode.equals("pushdef") ) {
			engineContext.getMacroRegistry().pushMacro( name, userMacro );
		}
		
	  return "";
  }
  
}
