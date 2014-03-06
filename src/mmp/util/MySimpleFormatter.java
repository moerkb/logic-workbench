/** --------------------------------------------------------------------------
 * A very simple formatter
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
 * $Id: MySimpleFormatter.java 338 2008-07-01 08:06:00Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A very simple formatter for Java logging.
 * 
 * @author Burkhardt Renz
 */
public class MySimpleFormatter extends Formatter {

	@Override
	public String format( LogRecord r ) {
	  return String.format( "[%s::%s] %s%n", 
	  		r.getSourceClassName(), r.getSourceMethodName(), r.getMessage()  );
	}
}
