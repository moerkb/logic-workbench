/** --------------------------------------------------------------------------
 * Implementation of macro 'translit'
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
 * $Id: translit.java 356 2008-07-10 08:58:56Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * translit( string, chars [, replacement] )
 * </h3>
 * <h5>
 * character translation
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * Two or three arguments
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * 'translit' expands to the string with all characters from 'chars' 
 * replaced by the character from 'replacement' at the same index. <br/>
 * If 'replacement' is empty or shorten than 'chars', the characters that
 * have no defined replacement will be deleted in string. <br/>
 * If 'replacement' is longer than 'chars' the excess characters are ignored. <br/>
 * <br/>
 * If a character appears several times in 'chars', the first occurence is used for
 * the translation.<br/>
 * The definition of the 'chars' and 'replacement' can contain ranges of characters:<br/>
 * <code>'c1'-'c2'</code> defines the characters from 'c1' to 'c2' in ascending
 * order if 'c1' <= 'c2', in descending order, if 'c1' > 'c2'. </br>
 * It is possible that the to-character of a range is the from-character of the
 * next range, i.e. 'a-d-b' means 'abcdcb'.</br>
 * To translate '-' into another character, it may be placed as first or
 * last character of the sequence, or is is the bound of a range.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments <br/>
 * SyntaxErrorException [1105], if internal error reading 'chars' or 'replacement' <br/>
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * The possibility to give ranges in 'chars' and 'replacement' is an extension of 
 * <code>mmp</code> as against <acronym>POSIX 2004</acronym>.<br/>
 * <code>mmp</code> conforms with <acronym>GNU m4</acronym>
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class translit extends Macro {
	
  private enum ExpandRangesState {
  	Start, 
  	SawCharAndPotentialLeft,
  	WaitForRight
  }

	private static Logger logger =  Logger.getLogger( translit.class.getName() );
	
	public translit() {
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
		String chars = macArgs.get(2);
		String replc = (size == 4) ? macArgs.get(3) : "";
		
		// expand chars and repl
		String xChars = expandRanges( chars );
		String xReplc = expandRanges( replc );
		// build mapping
		Map<Character, Integer> map = buildMap( xChars, xReplc );
		
		// perform translation
		String result = translate( string, map );
		
  	if ( !result.isEmpty()) {
	 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }

  /*
   * expands ranges in the definition of chars
   * uses internal a little state machine
   */
   	private String expandRanges( String chars ) throws SyntaxErrorException {
  	ExpandRangesState state = ExpandRangesState.Start;
  	StringReader sr = new StringReader( chars );
  	StringBuffer sb = new StringBuffer();
  	char left = ' ';
  	
  	while ( true ) {
  		int event = -1;
  		try {
	      event = sr.read();
      } catch ( IOException e ) {
      	throw new SyntaxErrorException( 1105, 
      			String.format("internal error reading %s", chars) );
      }
      switch ( state ) {
      	case Start:
      		if ( event == -1 ) {
      			return "";
      		} else {
      			left = (char)event;
      			sb.append( left );
      			state = ExpandRangesState.SawCharAndPotentialLeft;
      		}
      		break;
      	case SawCharAndPotentialLeft:
      		if ( event == -1 ) {
      			return sb.toString();
      		} else if ( (char)event == '-' ) {
      			state = ExpandRangesState.WaitForRight;
      		} else {
      			left = (char)event;
      			sb.append( left );
      			// stay in current state
      		}
      		break;
      	case WaitForRight:
      		if ( event == -1 ) {
      			sb.append( '-' );
      			return sb.toString();
      		} else {
      			expandRange( sb, left, (char)event );
      			left = (char)event;
      			state = ExpandRangesState.SawCharAndPotentialLeft;
      		}
      		break;
      }
  	}
  }

	/* 
	 * Writes (left,right] to provided StringBuffer.
   */
  private void expandRange( StringBuffer sb, char left, char right ) {
  	if ( left == right ) {
  		sb.append( right );
  	}
  	if ( left < right ) {
  		for ( char c = (char)(left+1); c <= right; c++ ) {
  			sb.append(  c  );
  		}
  	}
  	if ( left > right ) {
  		for ( char c = (char)(left-1); c >= right; c-- ) {
  			sb.append(  c  );
  		}
  	}
 		return;
  }

	/* 
   * generates a map for translation.
   * implementation is NOT efficient, it would be better to
   * calculate the lowest and highest char in chars and use an
   * array for direct translation
   */
  private Map<Character, Integer> buildMap( String chars, String replc ) {
  	Map<Character, Integer> trMap = new HashMap<Character, Integer>();
  	int clen = chars.length();
  	int rlen = replc.length();
  	
  	for ( int i = 0; i < clen; i++) {
  		char key = chars.charAt( i );
  		int  value = -1;
  		if ( i < rlen ) {
  			value = replc.charAt( i );
  		} 
  		if ( !trMap.containsKey(key) ) {
  			trMap.put( key, value );
  		}
  	}
    return trMap;
  }

	/*
	 * uses the trMap for character translation in string
   */
  private String translate( String string, Map<Character, Integer> trMap ) {
  	StringBuffer sb = new StringBuffer();
  	
  	for ( int i = 0; i < string.length(); i++ ) {
  		char key = string.charAt( i );
  		Integer value = trMap.get( key );
  		if ( value == null ) {
  			sb.append( key );
  		} else if ( value.equals( -1 ) ) {
  			// delete i.e. don't append
  		} else {
  			sb.append( (char)value.intValue() );
  		}
  	}
    return sb.toString();
  }

}
