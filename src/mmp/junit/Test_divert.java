/** --------------------------------------------------------------------------
 * Test divert
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
 * $Id: Test_divert.java 322 2008-06-30 10:41:57Z brenz $
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

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Burkhardt Renz
 *
 */
public class Test_divert {

	@Test
	@Ignore
	public final void test_124diversions() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`-1')define(`f', `.')\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"define(`f', defn(`f')defn(`f'))\n" +
				"divert`'dnl\n" +
				"len(f)\n" +
				"divert(`1')\n" +
				"f\n" +
				"divert(`-1')undivert\n"
		);		
		
		String expectedOutput = new String(
				"1048576\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	/*
	 * 125.diversions is the same as 124.diversions but with m4exit
	 * 126.diversions uses sycmd; mmp does not support syscmd
	 */
	
	@Test
	public final void divert_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(-1)\n" +
				"define(`hello',`Hello $1!')\n" +
				"divert(0)dnl\n" +
				"hello(`m4')\n"
		);		
		String expectedOutput = new String(
				"Hello m4!\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	/*
	 * Slightly changed because mmp throws exception if divert has no arguments
	 */
	@Test
	public final void test127divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')\n" +
				"This text is diverted.\n" +
				"divert\n" +
				"This text is not diverted.\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"This text is not diverted.\n" +
				"\n" +
				"This text is diverted.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test128divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`text', `TEXT')\n" +
				"divert(`1')`diverted text.'\n" +
				"divert\n" +
				"m4wrap(`Wrapped text precedes ')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"Wrapped TEXT precedes diverted text.\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	
	@Test
	public final void test129divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`-1')\n" +
				"define(`foo', `Macro `foo'.')\n" +
				"define(`bar', `Macro `bar'.')\n" +
				"divert\n"
		);		
		String expectedOutput = new String(
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}

	@Test
	public final void test130divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(eval(`1<<28'))world\n" +
				"divert(`2')hello\n"
		);		
		String expectedOutput = new String(
				"hello\n" +
				"world\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}

	@Test
	public final void test131divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"We decided to divert the stream for irrigation.\n" +
				"define(`divert', `ifelse(`$#', `0', ``$0'', `builtin(`$0', $@)')')\n" +
				"divert(`-1')\n" +
				"Ignored text.\n" +
				"divert(`0')\n" +
				"We decided to divert the stream for irrigation.\n"
		);
		String expectedOutput = new String(
				"We decided to  the stream for irrigation.\n" +
				"\n" +
				"\n" +
				"We decided to divert the stream for irrigation.\n"
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
