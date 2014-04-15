/** --------------------------------------------------------------------------
 * Tokens of MMPScanner
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
 * $Id: Token.java 322 2008-06-30 10:41:57Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

/**
 * @author Burkhardt Renz
 *
 */
class Token {

	/**
   * Types of MMPTokens
   */
  public enum Type {
  	EOI,  			/** end of input */
  	QSTRING,		/** quoted string */
  	COMMENT,		/** comment */
  	IDENTIFIER,	/** word, may be the name of a macro */
  	BEGARGS,    /** begin of arguments, i.e. '(' */
  	ENDARGS,    /** right parenthesis ')' */
  	COMMA,		  /** comma ',' */
  	CHAR        /** any other single character */
  }
	
	
	private Type type;
	private String value;
	
	/**
   * Default constructor
   */
  Token() {
  }

	/**
	 * Constructor
   * @param type
   */
  Token( Type type ) {
	  this.type = type;
	  this.value = "";
  }

	/**
	 * Constructor
   * @param type
   * @param value
   */
  Token( Type type, String value ) {
	  this.type = type;
	  this.value = value;
  }

	/**
   * @return the type
   */
  Type getType() {
  	return type;
  }

	/**
   * @param type the type to set
   */
  void setType( Type type ) {
  	this.type = type;
  }

	/**
   * @return the value
   */
  String getValue() {
  	return value;
  }

	/**
   * @param value the value to set
   */
  void setValue( String value ) {
  	this.value = value;
  }
  
  @Override
  public String toString() {
  	if ( type == Type.CHAR && Character.isISOControl(value.charAt(0)) ) {
  		return String.format( "Token: %s, value: %#04x", type, (byte)value.charAt(0) );
  	}
	  return String.format( "Token: %s, value: %s" , type, value );
  }

}
