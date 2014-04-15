/** --------------------------------------------------------------------------
 * Management of output and diversions
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
 * $Id: Output.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
//import java.util.logging.Logger;

/**
 * Output manages the diversions. The writer given to the constructor is
 * the standard output of the engine, registered as diversion '0'.<br/>
 * The built-in macro 'divert' can generate new diversions or redirect output
 * to already existing diversions.
 * 
 * @author Burkhardt Renz
 *
 */
public class Output {
	
	//private static Logger logger = Logger.getLogger( Output.class.getName() );
	
	private SortedMap<Integer, Writer> outputMap = new TreeMap<Integer, Writer>();
	private Writer currentWriter;
	private int currentDivnum;
	
	/**
	 * Initializes the output to the given writer. This writer
	 * is the standard output with divnum '0' of the engine.
	 * 
	 * @pre writer != 0
	 * @param writer where to write output
	 */
	public Output( Writer writer ) {
		outputMap.put( 0, writer );
		currentDivnum = 0;
		currentWriter = writer;
	}
	
	/**
	 * Write string to current writer.
	 * 
	 * @param string to be written to the current writer
	 * @throws IOException if io error
	 */
	public final void write( String string ) throws IOException {
		if ( currentWriter != null ) {
    		currentWriter.write( string );
		}
	}
	
	/**
	 * Write single char to current writer.
	 * @param c char to be written
	 * @throws IOException if io error
	 */
	public final void write( int c ) throws IOException {
		if ( currentWriter != null ) {
			currentWriter.write( c );
		}
	}
	
	/**
	 * Sets the current writer to the string writer with diversion number
	 * 'divnum'. <br/>
	 * If there is no string writer with that diversion number, 
	 * it is created.<br/>
	 * If the diversion number is negative, the current writer will 
	 * be a writer that discards all input.
	 * 
	 * @param divnum number of writer to assign as the current writer.
	 */
	public final void divert( int divnum ) {
		if ( divnum < 0 ) {
			currentWriter = null;
		}
		if ( outputMap.containsKey(divnum) ) {
			currentWriter = outputMap.get( divnum );
			currentDivnum = divnum;
		} else {
			StringWriter newDiversion = new StringWriter();
			outputMap.put( divnum, newDiversion );
			currentWriter = newDiversion;
			currentDivnum = divnum;
		}
	}

	/**
	 * Writes the content of diversion 'divnum' to the
	 * current writer and discards the diversion.
	 * 
	 * @param divnum number of diversion to be output.
	 * @throws IOException  if io error
	 */
	public final void undivert( int divnum ) throws IOException {
		
		if ( divnum == currentDivnum ) {
			// ignore this silently
			return;
		}
		
		if ( currentWriter != null ) {
			currentWriter.flush();
		}
		if ( divnum > 0 ) {
			Writer divWriter = outputMap.get( divnum );
			if ( divWriter != null ) {
				currentWriter.write( divWriter.toString() );
				outputMap.remove( divnum );
			}
		}
	}
	
	/**
	 * Undiverts all diversions in numerical order into
	 * the current diversion. 
	 * 
	 * @throws IOException if io error
	 */
	public final void undivert() throws IOException {
		SortedSet<Integer> divSet = new TreeSet<Integer>();
		divSet.addAll( outputMap.keySet() );
		for ( Integer divnum : divSet ) {
			undivert( divnum );
		}
		return;
	}
	
	/**
	 * Closes the output and flushes all diversions to the writer
	 * with divnum '0'.
	 * 
	 * @throws IOException if io error
	 */
	public final void close() throws IOException {
		divert(0);
		undivert();
		currentWriter.close();
	}
	
	/**
	 * Closes the output without flushing the diversions.
	 * 
	 * @throws IOException if io error
	 */
	public final void exit() throws IOException {
		divert(0);
		currentWriter.close();
	}
	
	/**
	 * Get the number of current diversion.
	 * @return number of current writer.
	 */
	public final int getCurrentDivnum() {
		return this.currentDivnum;
	}
}
