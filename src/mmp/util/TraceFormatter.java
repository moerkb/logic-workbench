/** --------------------------------------------------------------------------
 * A formatter for tracing in mmp
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
 * $Id$
 * --------------------------------------------------------------------------
 */
package mmp.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A formatter for tracing in mmp.
 * 
 * @author Burkhardt Renz
 */
public class TraceFormatter extends Formatter {

	@Override
	public String format( LogRecord r ) {
	  return String.format( "%s%n", r.getMessage() );
	}
}
