/** --------------------------------------------------------------------------
 * Test translit
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
 * $Id: Test_translit.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_translit {

	@Test
	public final void test149translit() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"translit(`GNUs not Unix', `A-Z')\n" +
				"translit(`GNUs not Unix', `a-z', `A-Z')\n" +
				"translit(`GNUs not Unix', `A-Z', `z-a')\n" +
				"translit(`+,-12345', `+--1-5', `<;>a-c-a')\n" +
				"translit(`abcdef', `aabdef', `bcged')\n"
		);	
	
		String expectedOutput = new String(
				"s not nix\n" +
				"GNUS NOT UNIX\n" +
				"tmfs not fnix\n" +
				"<;>abcba\n" +
				"bgced\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test150translit() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"translit(`«abc~', `~-»')\n"
		);	
		
		String expectedOutput = new String(
				"abc\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test (expected=SyntaxErrorException.class)
	public final void test151translit() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"translit(`abc')\n"
		);	
		
		String expectedOutput = new String(
				"abc\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
}
