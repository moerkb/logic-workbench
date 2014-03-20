/** --------------------------------------------------------------------------
 * Implementation of macro 'regexp'
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
 * $Id: regexp.java 356 2008-07-10 08:58:56Z brenz $
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
 * regexp( string, pattern )
 * </h3>
 * <h5>
 * find pattern in string
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * Two arguments
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * A call of the macro 'regexp' with a 'pattern' expands to the index
 * of the first occurrence of the pattern in 'string', or -1 if there is
 * no match.<br/>
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
 * <code>mmp</code> does not support an optional third argument for
 * replacement as<acronym>GNU</acronym> <code>m4</code>does.
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class regexp extends Macro {
	
	private static Logger logger =  Logger.getLogger( regexp.class.getName() );
	
	public regexp() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( (size != 3) && (size != 4) ) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' has %d arguments, expected 2 or 3", macroName, size-1) );
		}
		
		String string = macArgs.get(1);
		String pattern = macArgs.get(2);
		
		String result;
		try {
    	Pattern p = Pattern.compile( pattern );
    	Matcher m = p.matcher( string );
			boolean found = m.find();
			int index = found ? m.start() : -1;
			if ( size == 3 ) {
				result = String.format( "%d", index );
			} else {
				if ( index == -1 ) {
					result = "";
				} else {
					result = replace( macArgs.get(3), m );
				}	
			}
		} catch ( PatternSyntaxException e ) {
			throw new SyntaxErrorException( 1104, e.getMessage() );
		}
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		return result;
  }

  /*
   * replace $i in String s by group i of Matcher m 
   */
  private String replace( String s, Matcher m ) throws SyntaxErrorException {
  	int i = 0;
  	StringBuffer result = new StringBuffer();
  	
  	while( i < s.length() ) {
  		char ch = s.charAt( i );
  		if ( ch == '\\' ) {
  			i++;
  			ch = s.charAt( i );
  			result.append( ch );
  			i++;
  		} else if ( ch == '$' )  {
  			// skip $
  			i++;
  			int gNum = (int)s.charAt(i) - '0';
  			if ( (gNum < 0) || (gNum > 9) ) {
  				throw new SyntaxErrorException( 1104,
  						"illegal group reference in replacement" );
  			}	
  			// skip number
  			i++;
  			// more digits?
  			boolean noMoreDigits = false;
  			while ( !noMoreDigits ) {
  				if (i >= s.length() ) {
  					break;
  				}
  				int nDigit = s.charAt(i) - '0';
  				if ( (nDigit < 0) || (nDigit > 9) ) {
  					break;
  				}
  				int gNum2 = (gNum * 10) + nDigit;
  				if ( m.groupCount() < gNum2 ) {
  					noMoreDigits = true;
  				} else {
  					gNum = gNum2;
  					i++;
  				}
  			}	
  			// append group
  			if ( gNum > m.groupCount() ) {
  				throw new SyntaxErrorException( 1104, "no group " + gNum );
  			}
  			if ( m.group(gNum) != null ) {
  				result.append( m.group(gNum) );
  			}	
  		} else {
  			result.append( ch );
  			i++;
  		}	
  	}
  	return result.toString();
  }
  
} 