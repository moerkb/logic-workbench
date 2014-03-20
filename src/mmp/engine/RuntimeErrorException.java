/** --------------------------------------------------------------------------
 * Exception for runtime errors in mmp
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
 * $Id: RuntimeErrorException.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

/**
 * Exception for runtime errors during execution of the mmp engine.
 * <br/><br/>
 * List of error numbers of runtime error exceptions
 * <ul>
 * 	 <li>1001 recursion limit exceeded</li>
 * 	 <li>1002 stack overflow</li>
 *   <li>1003 can't open file</li>
 *   <li>1004 can't construct macro by reflection</li>
 * </ul>
 * 
 * @author Burkhardt Renz
 * @serial exclude
 *
 */
public class RuntimeErrorException extends Exception {

  private static final long serialVersionUID = -3539059511492933828L;
	@SuppressWarnings("unused") // is actually used in String.format!!
  private int errorNumber;

	/**
	 * Constructor.
	 * 
	 * @param errorNumber to specify type of runtime error
	 * @param message to give details on runtime error
	 */
	public RuntimeErrorException( int errorNumber, String message ) {
		super( String.format("[mmp %4d] %s", errorNumber, message) );
		this.errorNumber = errorNumber;
	}

}
