/** --------------------------------------------------------------------------
 * Test 'm4exit'
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
 * $Id: Test_m4exit.java 322 2008-06-30 10:41:57Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
public class Test_m4exit {

	@Test
	public final void test182m4exit() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"m4wrap(`This text is lost due to `m4exit'.')\n" +
				"divert(`1') So is this.\n" +
				"divert\n" +
				"m4exit And this is never read.\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		
		int rc = engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
		assertTrue( "expected returncode", rc == 0);
	}	
	
	@Test
	public final void test182m4exitvariant() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"m4wrap(`This text is lost due to `m4exit'.')\n" +
				"divert(`1') So is this.\n" +
				"divert\n" +
				"m4exit(-1) And this is never read.\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		
		int rc = engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
		assertTrue( "expected returncode", rc == -1);
	}	
}
