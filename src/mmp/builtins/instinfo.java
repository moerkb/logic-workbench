/** --------------------------------------------------------------------------
 * Implementation of macro 'instinfo'
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
 * $Id: instinfo.java 356 2008-07-10 08:58:56Z brenz $
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
 * instinfo( name ) <br/>
 * </h3>
 * <h5>
 * instantiation info
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * name of a macro
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * 'instinfo' expands to a comma separated list of the quoted strings,
 * the first the class name of the macro, and then the arguments to
 * the constructor of the macro. <br/>
 * 'instinfo' without an argument expands to 'instinfo'.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments
 * SyntaxErrorException [1002], if name not a macro
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
public class instinfo extends Macro {
	
	private static Logger logger =  Logger.getLogger( instinfo.class.getName() );
	
	public instinfo() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	String begQuote = engineContext.getSettings().getBegQuote();
  	String endQuote = engineContext.getSettings().getEndQuote();
		if ( size == 1 ) {
			return begQuote + macroName + endQuote;
		}
		
		if ( size != 2 ) {
			throw new SyntaxErrorException( 1001,
					String.format( "%s called with %d arguments, expected 1", 
							macroName, size-1) );
		}
		
		String name = macArgs.get( 1 );
		
		Macro macro = engineContext.getMacroRegistry().getMacro( name );
		if ( macro == null ) {
			throw new SyntaxErrorException( 1002,
					String.format("There is no macro named '%s'.", name) );
		}
		
		StringBuffer sb = new StringBuffer();
		for ( String info : macro.getInstInfo() ) {
			sb.append( begQuote );
			sb.append( info );
			sb.append( endQuote );
			sb.append( ',' );
		}
		if ( sb.length() > 0 ) {
			sb.deleteCharAt( sb.length()-1 );
		}
		
		String result = sb.toString();
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }
  
}
