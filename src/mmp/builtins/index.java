/** --------------------------------------------------------------------------
 * Implementation of macro 'index'
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
 * $Id: index.java 356 2008-07-10 08:58:56Z brenz $
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
 * index( string, substring )
 * </h3>
 * <h5>
 * find index of substring in string
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * 0 or 2 arguments
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * Expands to the index of the first occurrence of 'substring' in 'string'.<br/>
 * The result is '-1', if the string doesn't contain the substring.<br/>
 * If the substring is empty, the result is '0'.
 * <br/>
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * <acronym>GNU</acronym> <code>m4</code> expands index without an argument to the
 * string 'index' and gives a warning if the second argument is missing, 
 * whereas <code>mmp</code> throws an exception in both cases.
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class index extends Macro {
	
	private static Logger logger =  Logger.getLogger( index.class.getName() );
	
	public index() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( macArgs.size() != 3 ) {
			throw new SyntaxErrorException( 1001,
					String.format("define for '%s' has %d arguments, expected 2", macArgs.get(0), size-1) );
		}
		String string = macArgs.get(1);
		String substring = macArgs.get(2);
		
		String result = String.format( "%d", string.indexOf(substring) );
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		
	  return result;
  }
  
}
