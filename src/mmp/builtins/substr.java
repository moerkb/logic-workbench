/** --------------------------------------------------------------------------
 * Implementation of macro 'substr'
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
 * $Id: substr.java 356 2008-07-10 08:58:56Z brenz $
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
 * substr( string, begin [,length] )
 * </h3>
 * <h5>
 * substring
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * Two or three arguments <br/>
 * 'begin' is a non-negative number <br/>
 * 'length' is a non-negative number
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * 'substr' expands to the substring of 'string' beginning at the index 'begin'
 * and with length 'length'. <br/>
 * If 'length' is not provided, it expands to the substring
 * of 'string' from 'begin' to the end of the string. <br/>
 * If 'index' is greater than the length of the string, the expansion is the empty
 * string.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments <br/>
 * SyntaxErrorException [1101], if 'begin' and 'length' can't be parsed to numbers <br/>
 * SyntaxErrorException [1003], if 'length' < 0
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym> <br/>
 * <code>mmp</code> conforms with <acronym>GNU m4</acronym>
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class substr extends Macro {
	
	private static Logger logger =  Logger.getLogger( substr.class.getName() );
	
	public substr() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size < 3 || size > 4 ) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' has %d arguments, expected 2 or 3", macroName, size-1) );
		}
		
		String string = macArgs.get(1);
		int beginIndex = 0;
		int length = 0;
		try {
			beginIndex = Integer.parseInt( macArgs.get(2) );
		} catch ( NumberFormatException e ) {
			throw new SyntaxErrorException( 1101,
					String.format("Can't parse %s to a number", macArgs.get(2)) );
		}
		if ( beginIndex < 0 ) {
			throw new SyntaxErrorException( 1003, "begin is negative" );
		}
		try {
			length = (size == 4) ? Integer.parseInt(macArgs.get(3)) : string.length();
		} catch ( NumberFormatException e ) {
			throw new SyntaxErrorException( 1101,
					String.format("Can't parse %s to a number", macArgs.get(3)) );
		}
		if ( length < 0 ) {
			throw new SyntaxErrorException( 1003, "length is negative" );
		}
		
		if ( beginIndex >= string.length() ) {
			return "";
		}
		String result;
		int endIndex = beginIndex + length;
		if ( endIndex > string.length() ) {
			result = string.substring( beginIndex );
		} else {
			result = string.substring(  beginIndex, endIndex );
		}	
		
  	if ( !result.isEmpty()) {
	 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }
  
}
