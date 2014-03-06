/** --------------------------------------------------------------------------
 * Test examples of mmp paper
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
 * $Id: Test_examples.java 407 2008-07-21 00:19:48Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
public class Test_examples {
	
	/* 
	 * Note: ByteArrayOutputStream uses \r\n as line separator on Windows machine!
	 */

	@Test
	public final void ex___mmp__() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"This macro processor is ifdef(`__mmp__', `mmp', `not mmp').\n"
		);		
		
		String expectedOutput = new String(
				"This macro processor is mmp.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_builtin() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"undefine(`define')dnl\n" +
				"builtin(`define',`foo',`bar')dnl\n" +
				"foo\n"
		);		
		
		String expectedOutput = new String(
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')dnl\n" +
				"/* foo */\n" +
				"changecom(`/*',`*/')dnl\n" +
				"/* foo */\n"
		);		
		
		String expectedOutput = new String(
				"/* bar */\n" +
				"/* foo */\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_changequote() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changequote(`[',`]')dnl\n" +
				"define([foo],[bar])dnl\n" +
				"/* foo */\n"
		);		
		
		String expectedOutput = new String(
				"/* bar */\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_decr() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"decr(`111')\n"
		);		
		
		String expectedOutput = new String(
				"110\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_define() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')dnl\n" +
				"foo\n"
		);		
		
		String expectedOutput = new String(
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_defn() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`The macro dnl should not be processed here.')dnl\n" +
				"defn(`bar',`foo')\n"
		);		
		
		String expectedOutput = new String(
				"The macro dnl should not be processed here.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_divert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')dnl\n" +
				"first\n" +
				"divert()dnl\n" +
				"second\n"
		);		
		
		String expectedOutput = new String(
				"second\n" +
				"first\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_divnum() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"initial divnum\n" +
				"divert(`1')dnl\n" +
				"now divnum\n"
		);		
		
		String expectedOutput = new String(
				"initial 0\n" +
				"now 1\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_dnl() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"The following text dnl is discarded.\n"
		);		
		
		String expectedOutput = new String(
				"The following text "
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_dumpdef() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')dnl\n" +
				"dumpdef(`foo',`bar')\n"
		);		
		
		String expectedError = new String(
				"MMPDumpdef -          bar: -- not defined --\r\n" +
				"MMPDumpdef -          foo: [mmp.engine.UserMacro, foo, bar]\r\n"
		);		
		StringWriter output = new StringWriter();
		
		ByteArrayOutputStream errout = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream( errout );
		System.setErr( ps );
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
    String errMessage = errout.toString();
		
		// compare result
		assertTrue( "expected output", errMessage.equals(expectedError) );
	}
	
	@Test
	public final void ex_errprint() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"errprint(`This is an error message!')\n"
		);		
		
		String expectedError = new String(
				"This is an error message!\r\n"
		);		
		StringWriter output = new StringWriter();
		
		ByteArrayOutputStream errout = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream( errout );
		System.setErr( ps );
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
    String errMessage = errout.toString();
		
		//System.out.println( errMessage );
		// compare result
		assertTrue( "expected output", errMessage.equals(expectedError) );
	}
	
	@Test
	public final void ex_eval() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`(2 **3) **2 -64')\n"
		);		
		
		String expectedOutput = new String(
				"0\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_format() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')dnl\n" +
				"format(`The string \"%s\" has a length of %d characters.',foo,len(foo))\n"
		);		
		
		String expectedOutput = new String(
				"The string \"bar\" has a length of 3 characters.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_ifdef() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo')dnl\n" +
				"ifdef(`foo',``foo' is defined.')\n" +
				"ifdef(`bar',``bar' is defined.',``bar' is not defined.')\n"
		);		
		
		String expectedOutput = new String(
				"foo is defined.\n" +
				"bar is not defined.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
		@Test
	public final void ex_ifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `bar')dnl\n" +
				"ifelse(foo, `bar', `true', `false')\n" +
				"ifelse(foo, `foo', `true', `false')\n"
		);	
	
		String expectedOutput = new String(
				"true\n" +
				"false\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_include() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foo')"
		);	
	
		String expectedOutput = new String(
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_incr() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"incr(decr(`111'))\n"
		);		
		
		String expectedOutput = new String(
				"111\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_indir() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`+foo+',`bar')\n" +
				"indir(`+foo+')\n"
		);		
		
		String expectedOutput = new String(
				"\n" +
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_pushdef() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')dnl\n" +
				"foo\n" + 
				"pushdef(`foo',`new bar')dnl\n" +
				"foo\n" + 
				"popdef(`foo')dnl\n" +
				"foo\n"
		);		
		
		String expectedOutput = new String(
				"bar\n" +
				"new bar\n" +
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void ex_sinclude() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"Content of file foox:sinclude(`fooix')\n"
		);	
	
		String expectedOutput = new String(
				"Content of file foox:\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
}
