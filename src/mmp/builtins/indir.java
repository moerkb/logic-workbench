/** --------------------------------------------------------------------------
 * Implementation of builtin 'indir' and 'builtin'
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
 * $Id: indir.java 358 2008-07-10 10:12:06Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.M4ExitException;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * indir( name[, arg1 [, arg2... ...]] )
 * </h5>
 * <h5>
 * Indirect call of macro 'name'.
 * </h5>
 * Implemented by indir( "indir" ).<br/>
 * Needs parenthesis to be recognized as a macro.
 * 
 * <h3>
 * builtin( name[, arg1 [, arg2... ...]] )
 * </h3>
 * <h5>
 * Indirect call of builtin 'name'.
 * </h5>
 * Implemented by indir( "builtin" ).<br/>
 * Needs parenthesis to be recognized as a macro.
 * 
 * @author Burkhardt Renz
 */
public class indir extends Macro {
	
	private static Logger logger =  Logger.getLogger( indir.class.getName() );
	
	private final String mode;
	
	/**
	 * Constructor
	 * @pre mode == "indir" or mode == "builtin"
	 * @param mode
	 */
	public indir( String mode ) {
		super( Style.NEEDS_PARENTHESIS, mode );
		this.mode = mode;
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException, M4ExitException {
  	
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		String name = macArgs.get( 1 );
		
		Macro macro = null;
		if ( mode.equals("indir") ) {
   		macro = engineContext.getMacroRegistry().getMacro( name );
		} else if ( mode.equals("builtin") ) {
   		macro = engineContext.getMacroRegistry().getBuiltin( name );
		}
   	if ( macro == null ) {
   		throw new SyntaxErrorException( 1002, 
   				String.format("Indirectly called macro '%s' not defined", name) );
   	}	
   	
   	if ( macro.needsParenthesis() && macArgs.size() == 2 ) {
 				logger.fine( String.format(MMPTRACE_EXP, macroName, name) );
   		return name;
   	} else {
      macArgs.remove( 0 );
   		String result = macro.call( macArgs, engineContext );
  		if ( !result.isEmpty()) {
		 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  		}	
  	return result;
   	}
  }
  
}
