/** --------------------------------------------------------------------------
 * Implementation of macro 'patsubst'
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
 * $Id: patsubst.java 356 2008-07-10 08:58:56Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * patsubst( string, pattern [,replacement] )
 * </h3>
 * <h5>
 * substitutes pattern by replacement
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * Two or three arguments
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * A call of the macro 'patsubst' with a 'pattern' and a 'replacement' expands
 * to the replacement of each match of the pattern in 'string' with the 
 * given replacement, according to the behavior of Java's Matcher::replaceAll.<br/>
 * If 'replacement' is not given or empty it is equivalent to the empty string,
 * i.e. pattern will be deleted in the string.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments <br/>
 * SyntaxErrorException [1104], if syntax error in regular expression <br/>
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * caveat:<br/>
 * <acronym>GNU</acronym> <code>m4</code> has a slightly different
 * behavior with respect to replacement.
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class patsubst extends Macro {
	
	private static Logger logger =  Logger.getLogger( patsubst.class.getName() );
	
	public patsubst() {
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
		String pattern = macArgs.get(2);
		String replacement = (size == 4) ? macArgs.get(3) : "";
		
		String result;
		try {
			Pattern p = Pattern.compile( pattern );
			Matcher m = p.matcher( string );
			result = m.replaceAll( replacement );
		} catch ( PatternSyntaxException e ) {
				throw new SyntaxErrorException( 1104, e.getMessage() );
		} catch ( StringIndexOutOfBoundsException e ) {
				throw new SyntaxErrorException( 1104, e.getMessage() );
		}
		
  	if ( !result.isEmpty()) {
 			logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }
  
}
