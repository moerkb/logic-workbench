/** --------------------------------------------------------------------------
 * Utility functions for ISO characters
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
 * $Id: CharType.java 338 2008-07-01 08:06:00Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.util;

/**
 * Utility functions for ISO characters.
 * 
 * @author Burkhardt
 */
public class CharType {
	
	/**
	 * Does character ch match [a-z]||[A-Z]||'_' ?.
	 * @param ch character to test
	 * @return result of test
	 */
	public static boolean isIdentifierFirstChar( char ch ) {
		return (ch >= 'a' && ch <='z') || (ch >='A' && ch <='Z') || ch == '_';
	}
	
	/**
	 * Does character ch match [0-9] ?.
	 * @param ch character to test
	 * @return result of test
	 */
	public static boolean isDigit( char ch ) {
		return ch >= '0' && ch <= '9';
	}
	
	/**
	 * Is character ch {@linkplain #isIdentifierFirstChar(char)} || {@linkplain #isDigit(char)} ?
	 * @param ch character to test
	 * @return result of test
	 */
	public static boolean isIdentifierChar( char ch ) {
		return isIdentifierFirstChar(ch) || isDigit(ch);
	}	
	
	/**
	 * Is character ch allowed as first character of a delimiter?
	 * <br/><br/>
	 * To facilitate the parsing of mmp's input, we do not allow that
	 * a delimter begins with a character used for identifiers
	 * ({@linkplain #isIdentifierChar(char)} or is part of the syntax of
	 * argumentlist, i.e. parenthesis and comma.
	 * 
	 * @param ch charcter to test
	 * @return true in ch does <i>not</i> match [a-z], [A-Z], [0-9], '_', '(', ')', ','.
	 */
	public static boolean isDelimiterFirstChar( char ch ) {
		if ( isIdentifierChar(ch) ) {
			return false;
		}
		if ( ch == '(' || ch == ')' || ch == ',' ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Is the string allowed as a delimiter string?
	 * <br/><br/>
	 * The string must be either empty or start with an allowed character,
	 * see {@linkplain #isDelimiterFirstChar(char)}.
	 * @param s the string to test
	 * @return result of test
	 */
	public static boolean isDelimiter( String s ) {
		if ( s.isEmpty() ) {
			return true;
		}
		return  isDelimiterFirstChar( s.charAt(0) );
	}

}
