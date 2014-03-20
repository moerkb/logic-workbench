/** --------------------------------------------------------------------------
 * Test 'defn'
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
 * $Id: Test_defn.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_defn {

	@Test 
	public final void test_noargdefn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"defn is a word.\n"
		);	
	
		String expectedOutput = new String(
			"defn is a word.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp does not allow defn for built-ins
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test042defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`zap', defn(`undefine'))\n" +
				"zap(`undefine')\n" +
				"undefine(`zap')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"undefine(zap)\n"
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
	public final void test043defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `This is `$0'')\n" +
				"define(`bar', defn(`foo'))\n" +
				"bar\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"This is bar\n"
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
	public final void test044defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`string', `The macro dnl is very useful\n" +
				"')\n" +
				"string\n" +
				"defn(`string')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"The macro \n" +
				"The macro dnl is very useful\n" +
				"\n" 
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
	public final void test045defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', a'a)\n" +
				"define(`a', `A')\n" +
				"define(`echo', `$@')\n" +
				"foo\n" +
				"defn(`foo')\n" +
				"echo(foo)\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"A'A\n" +
				"aA'\n" +
				"AA'\n"
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
	public final void test046defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`l', `<[>')define(`r', `<]>')\n" +
				"changequote(`[', `]')\n" +
				"defn([l])defn([r])\n" +
				"])\n" +
				"defn([l], [r])\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"<[>]defn([r])\n" +
				")\n" +
				"<[>][<]>\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test047defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"defn(`defn')\n" +
				"define(defn(`divnum'), `cannot redefine a builtin token')\n" +
				"divnum\n" +
				"len(defn(`divnum'))\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"m4:stdin:2: Warning: define: invalid macro name ignored\n" +
				"\n" +
				"0\n" +
				"0\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test048defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`a', `A')define(`AA', `b')\n" +
				"traceon(`defn', `define')\n" +
				"defn(`a', `divnum', `a')\n" +
				"define(`mydivnum', defn(`divnum', `divnum'))mydivnum\n" +
				"traceoff(`defn', `define')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"m4:stdin:3: Warning: cannot concatenate builtin `divnum'\n" +
				"m4trace: -1- defn(`a', `divnum', `a') -> ``A'`A''\n" +
				"AA\n" +
				"m4:stdin:4: Warning: cannot concatenate builtin `divnum'\n" +
				"m4:stdin:4: Warning: cannot concatenate builtin `divnum'\n" +
				"m4trace: -2- defn(`divnum', `divnum')\n" +
				"m4trace: -1- define(`mydivnum', `')\n" +
				"\n" +
				"\n"
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
