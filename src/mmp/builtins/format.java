/** --------------------------------------------------------------------------
 * Implementation of macro 'format'
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
 * $Id: format.java 383 2008-07-15 02:48:56Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
 * format( formatstring[, arg1[, arg2 ...]] )
 * </h3>
 * <h5>
 * formatted string
 * </h5>
 * <br/>Implemented by format().
 * 
 * @author Burkhardt Renz
 *
 */
public class format extends Macro {
	
	/**
   * Types of arguments expected in argument list of format
   */
  private enum ArgType {
  	BOOLEAN,
  	STRING,
  	CHARACTER,
  	INTEGER,
  	FLOAT,
  	DATE,
  	PERCENT,
  	NEWLINE
  }

	private static Logger logger =  Logger.getLogger( format.class.getName() );
	
	// formatSpecifier for formatString
	// %[argument_index$][flags][width][.precision][t]conversion
	private static final String formatSpecifier = 
				"%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
	
		
	public format() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		String formatString = macArgs.get(1);
		
		// compile pattern and create matcher
		Pattern p = null;
		Matcher m = null;
		try {
    	p = Pattern.compile( formatSpecifier );
    	m = p.matcher( formatString );
		} catch ( PatternSyntaxException e ) {
			throw new SyntaxErrorException( 1107, e.getMessage() );
		}
    	
		// format string without arguments
		if ( size == 2 && !m.find() ) {
			return formatString;
		}
		
		// analyze format string to determine the types of arguments
		m.reset();
		ArgType argTypes[] = new ArgType[size-2];
    int i = 0;
    int idx = 0;
    
    while ( i < formatString.length() ) {
    	if ( m.find(i) ) {
    		int current_idx;
    		logger.fine("group1 = " + m.group(1) );
    		logger.fine("group2 = " + m.group(2) );
    		logger.fine("group5 = " + m.group(5) );
    		logger.fine("group6 = " + m.group(6) );
    		
    		// determine index of argument
    		if ( m.group(1) == null || m.group(1).isEmpty() ) {
    			if ( m.group(2).isEmpty() || !m.group(2).contains("<") ) {
    				current_idx = idx++;
    			} else
    				// use previous index
    				current_idx = idx-1;
    		} else if ( m.group(1).endsWith("$") ) {
    			// explicit index in format specification	
    			String s_index = m.group(1).substring( 0, m.group(1).length()-1 );
    			current_idx = Integer.parseInt(s_index);
    		} else {
					throw new SyntaxErrorException( 1107, 
							"argument index not valid");
    		}
    		// current_idx okay?
    		if ( current_idx >= argTypes.length ) {
					throw new SyntaxErrorException( 1107, 
							"argument index exceeds argument list");
    		}
    		// determine argument type
    		if ( m.group(5) != null && (m.group(5).equals("t") || m.group(5).equals("T")) ) {
    			argTypes[current_idx] = ArgType.DATE;
    		} else if ( !m.group(6).isEmpty() ) {
    			char convChar = m.group(6).charAt(0);
    			switch (convChar) {
    				case 'b':
    				case 'B':
			    			argTypes[current_idx] = ArgType.BOOLEAN;
    						break;
    				case 's':
    				case 'S':	
			    			argTypes[current_idx] = ArgType.STRING;
    						break;
    				case 'c':
    				case 'C':	
			    			argTypes[current_idx] = ArgType.CHARACTER;
    						break;
    				case 'd':
    				case 'o':
    				case 'x':
    				case 'X':
			    			argTypes[current_idx] = ArgType.INTEGER;
    						break;
    				case 'e':		
    				case 'E':		
    				case 'f':		
    				case 'g':		
    				case 'G':		
    				case 'a':		
    				case 'A':		
			    			argTypes[current_idx] = ArgType.FLOAT;
    						break;
    				case '%':		
    				case 'n':		
    					// have no corresponding argument!!
    					idx--;
    						break;
    				default:		
							throw new SyntaxErrorException( 1107,  
									"unknown conversion '" + convChar + "'" );
    			}
    		}
    	// advance 
    	i = m.end();
    	} else {
    			break;
    	}
    }
    
   	// format output
    Object argObjs[] = collectArgObjs( macArgs, argTypes );
    String result = "";
    try {
    	result = String.format( formatString, argObjs );
    } catch ( Exception e ) {	
			throw new SyntaxErrorException( 1107, e.toString() );
    }	
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		
 		return result;
  }

	/**
	 * Converts arguments to corresponding objects and collects them in an object array.
	 * 
   * @param macArgs arguments as strings
   * @param argTypes types of arguments
   * @return array of corresponding objects
	 * @throws SyntaxErrorException 
   */
  private Object[] collectArgObjs( List<String> macArgs, ArgType[] argTypes ) throws SyntaxErrorException {
  	int size = macArgs.size();
    Object argObjs[] = new Object[size-2];
    
		try {
      for ( int j = 0; j <size-2; j++ ) {
      	if ( argTypes[j] == null ) {
      		continue;
      	}
      	switch ( argTypes[j] ) {
       		case BOOLEAN:
       			argObjs[j] = Boolean.valueOf( macArgs.get(j+2) );
       			break;
       		case STRING:
       			argObjs[j] =  macArgs.get(j+2);
       			break;
       		case CHARACTER:
       			if ( macArgs.get(j+2).isEmpty() ) {
       				argObjs[j] = null;
       			} else {
       				argObjs[j] = Character.valueOf( macArgs.get(j+2).charAt(0) );
       			}	
       			break;
       		case INTEGER:	
       			argObjs[j] = Integer.decode( macArgs.get(j+2) );
       			break;
       		case FLOAT:	
       			argObjs[j] = Float.valueOf( macArgs.get(j+2) );
       			break;
       		case DATE:	
    	         argObjs[j] = DateFormat.getInstance().parse( macArgs.get(j+2) );
    				break;
      	}	
      }	
    } catch ( ParseException e ) {
			throw new SyntaxErrorException( 1107, e.getMessage() );
    } catch ( NumberFormatException e ) {
			throw new SyntaxErrorException( 1107, e.toString() );
   	}
    
	  return argObjs;
  }
  
}
