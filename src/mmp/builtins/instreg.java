/** --------------------------------------------------------------------------
 * Implementation of macro 'instreg'
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
 * $Id: instreg.java 358 2008-07-10 10:12:06Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * instreg( name ,classname [,param1 [,param2...]] )
 * </h3>
 * <h5>
 * instantiate and register Java macro
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * TODO
 * Defines an user macro 'name' that expands to 'expansion'. <br/>
 * If the second argument is omitted, the expansion is the empty
 * string, i.e. <code>define(`name')</code> is the same as 
 * <code>define( `name', `' )</code>. <br/>
 * If there are several definitions of the macro 'name', define
 * replaces the topmost definition.<br/>
 * 'define' without arguments expands to 'define'.
 * <br/>
 * The defined expansion may contain arguments. Arguments are denoted by
 * <code>$[0-9]+</code>, the nth argument is <code>$n</code>.<br/>
 * Special arguments are:<br>
 * <code>$0</code> The name of the macro.<br>
 * <code>$#</code> The number of arguments in the moment of the macro call.<br>
 * <code>$*</code> The values of all arguments, separated by commas, 
 * i.e. <code>arg1,arg2,...,argn</code>.<br>
 * <code>$@</code> Like <code>$*</code>, but the arguments as quoted string, 
 * i.e. <code>`arg1',`arg2',...,`argn'</code>.<br/>
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * TODO
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym>.<br/>
 * <code>mmp</code> conforms with <acronym>GNU m4</acronym>.<br/>
 * </p>
 *
 * instpush( name, classname [, param1 [, param2 ...]] )
 * <h3>
 * </h3>
 * <h5>
 * instantiate and push Java macro
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * TODO
 * 'pushdef' defines a macro named 'name' that expands to 'expansion' or the empty
 * string if 'expansion' is not provided.<br/>
 * 'pushdef' does not replace a previous definition of 'name' but preserve it. <br/>
 * If there is no previous definition of 'name' 'pushdef' is equivalent to
 * 'define'.<br/>
 * 'pushdef' without arguments expands to 'pushdef'.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * TODO
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym>.<br/>
 * <code>mmp</code> conforms with <acronym>GNU m4</acronym>.<br/>
 * </p>
 *
 * @author Burkhardt Renz
 *
 */
public class instreg extends Macro {
	
	private static Logger logger =  Logger.getLogger( instreg.class.getName() );
	
	private final String mode;
	
	/**
	 * @pre mode == "instreg" or mode == "instpush"
	 * @param mode
	 */
	public instreg( String mode ) {
		super( Style.NEEDS_PARENTHESIS, mode );
		this.mode = mode;
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size < 3 ) {
			throw new SyntaxErrorException( 1001,
					String.format("%s has %d arguments, expected > 2", macroName, size-1) );
		}
		String name = macArgs.get( 1 );
		String className = macArgs.get( 2 );
		Macro macro = null;
		Class<?> paramTypes[] = new Class[size-3];
		for ( int i = 0; i < size-3; i++ ) {
			paramTypes[i] = String.class;
		}
		try {
    	Class<?> macroClass = Class.forName( className );
    	macro = (Macro)macroClass.getConstructor(paramTypes).newInstance( macArgs.subList(3, size).toArray() );
		}	catch ( Throwable e ) {
			throw new RuntimeErrorException ( 1004, e.toString() );
		}
		
		if ( mode.equals("instreg") ) {
			engineContext.getMacroRegistry().registerMacro( name, macro );
		} else if ( mode.equals("instpush") ) {
			engineContext.getMacroRegistry().pushMacro( name, macro );
		}
		
	  return "";
  }
  
}
