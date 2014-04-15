/** --------------------------------------------------------------------------
 * Exception for syntax errors in input
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
 * $Id: SyntaxErrorException.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

/**
 * Exception for syntax errors in input. 
 * <br/><br/>
 * List of error numbers of syntax error exceptions
 * <ul>
 *   <li>0901 end of input in comment</li>
 *   <li>0902 end of input in quoted string</li>
 *   <li>0903 end of input in argument list</li>
 * 
 *   <li>1001 wrong number of argument in macro</li>
 *   <li>1002 macro not defined</li>
 *   <li>1003 precondition not fulfilled</li>
 * 
 *   <li>1101 not a number</li>
 *   <li>1102 not a valid integer expression</li>
 *   <li>1103 internal error evaluating integer expression</li>
 *   <li>1104 syntax error in regular expression</li>
 *   <li>1105 internal error reading string</li>
 *   <li>1106 invalid argument to defn</li>
 *   <li>1107 syntax error in format specification</li>
 * </ul>
 * 
 * @author Burkhardt Renz
 * @serial exclude
 *
 */
public class SyntaxErrorException extends Exception {

  private static final long serialVersionUID = 3512800971639475339L;

	/**
	 * Constructor.
	 * 
	 * @param errorNumber to specify type of syntax error
	 * @param message to give details on syntax error
	 */
	public SyntaxErrorException( int errorNumber, String message ) {
		super( String.format("[mmp %4d] %s", errorNumber, message) );
	}

}
