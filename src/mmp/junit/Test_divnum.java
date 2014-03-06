/** --------------------------------------------------------------------------
 * Test 'divnum'
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
 * $Id: Test_divnum.java 322 2008-06-30 10:41:57Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import mmp.engine.Engine;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

import org.junit.Test;

/**
 * @author Burkhardt Renz
 *
 */
public class Test_divnum {


	@Test
	public final void test138divnum() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"Initial divnum\n" +
				"divert(`1')\n" +
				"Diversion one: divnum\n" +
				"divert(`2')\n" +
				"Diversion two: divnum\n"
		);
		String expectedOutput = new String(
				"Initial 0\n" +
				"\n" +
				"Diversion one: 1\n" +
				"\n" +
				"Diversion two: 2\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test139cleardiver() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')\n" +
				"Diversion one: divnum\n" +
				"divert(`2')\n" +
				"Diversion two: divnum\n" +
				"divert(`-1')\n" +
				"undivert\n" 
		);
		String expectedOutput = new String(
				""
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
}
