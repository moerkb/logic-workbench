/** --------------------------------------------------------------------------
 * Exception that causes exit of mmp, used by 'm4exit'
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
 * $Id: M4ExitException.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

/**
 * Exception that causes exit of mmp, used by 'm4exit.
 * 
 * @author Burkhardt Renz
 * @serial exclude
 *
 */
public class M4ExitException extends Exception {

  private static final long serialVersionUID = -4475541539620054269L;
  private int exitCode = 0;
  
	/**
	 * Constructor.
	 *  
	 * @param exitCode to be returned by mmp in case 'm4exit' was called
	 */
	public M4ExitException( int exitCode ) {
		super();
		this.exitCode = exitCode;
	}

	/**
	 * Constructor.
	 *  
	 * @param exitCode to be returned by mmp in case 'm4exit' was called
	 * @param message for tracing
	 */
	public M4ExitException( int exitCode, String message ) {
		super( String.format( "[mmp m4exit %4d] %s", exitCode, message ) );
		this.exitCode = exitCode;
	}

  /**
   * Get exit code.
   * 
   * @return the exit code
   */
  public final int getExitCode() {
	  return this.exitCode;
  }

}
