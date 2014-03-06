/** --------------------------------------------------------------------------
 * Test define
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
 * $Id: Test_Args.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_Args {

	/*
	 * mmp has a method eingineContext::define for definition of macros analogous to the command line
	 * option -D
	 */
	@Test
	public final void test002command_li() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"bar\n" +
				"foo"
		);		
		
		String expectedOutput = new String(
				"hello\n" +
				"world"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.define( "bar", "hello" );
		engine.define( "foo", "world" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	@Test
	public final void test003comments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"`quoted text' # `commented text'\n" +
				"`quoting inhibits' `#' `comments'\n"
		);		
		
		String expectedOutput = new String(
				"quoted text # `commented text'\n" +
				"quoting inhibits # comments\n"
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
	public final void test014macro_argu() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`macro', `$1')\n" +
				"macro( unquoted leading space lost)\n" +
				"macro(` quoted leading space kept')\n" +
				"macro(\n" +
				" divert `unquoted space kept after expansion')\n" +
				"macro(macro(`\n" +
				"')`whitespace from expansion kept')\n" +
				"macro(`unquoted trailing whitespace kept'\n" +
				")\n"
		);		
		
		String expectedOutput = new String(
				"\n" +
				"unquoted leading space lost\n" +
 				" quoted leading space kept\n" +
 				" unquoted space kept after expansion\n" +
				"\n" +
				"whitespace from expansion kept\n" +
				"unquoted trailing whitespace kept\n" +
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/* GNU m4 gives warnings if the number of arguments to 'index' is wrong.
	 * mmp throws an exception.
	 * Therefore the test of 015.macro_argu with mmp differs from that with GNU m4.  
	 * Test 016.macro_argu is the same as 015.macro_argu with command line option '-Q',
	 * mmp does not support that option. 
	 */
	@Test(expected=SyntaxErrorException.class)
	public final void test015macro_argu1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`abc')\n"
		);		
		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
	}
	
	@Test(expected=SyntaxErrorException.class)
	public final void test015macro_argu2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`abc',`b', `c' )\n"
		);		
		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
	}
	
	@Test
	public final void test015macro_argu3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`abc', `b')\n" +
				"index(`abc', )\n" +
				"index(`abc', `d')\n"
		);		
		String expectedOutput = new String(
				"1\n" +
				"0\n" +
				"-1\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test017macro_argu() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`f', `1')\n" +
				"f(define(`f', `2'))\n" +
				"f\n" 
		);		
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"2\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	/*
	 * mmp throws an exception in case the input ends in an argument list
	 */
	@Test(expected=SyntaxErrorException.class)
	public final void test018macro_argu() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"hello world\n" +
				"define(\n"
		);
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
	}
	
	@Test
	public final void test019quoting_ar() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`active', `ACT, IVE')\n" +
				"define(`show', `$1 $1')\n" +
				"show(active)\n" +
				"show(`active')\n" +
				"show(``active'')\n" 
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"ACT ACT\n" +
				"ACT, IVE ACT, IVE\n" +
				"active active\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test020Macro_expa() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"foo\n"
		);
		String expectedOutput = new String(
				"Hello\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.define( "bar", "Hello" );
		engine.define( "foo", "bar" );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test021Macro_expa() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"foo\n" +
				"foo(`silently ignored')\n" +
				"echo(`1', `2')\n"
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"1,2\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.define( "foo", "" );
		engine.define( "echo", "$@" );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test185extensions() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`a1', `A1')\n" +
				"define(`_1', `$1')define(`first1', `_1($@)1')\n" +
				"dnl First argument, concatenated with 1\n" +
				"define(`_9', `$9')define(`eleventh', `_9(shift(shift($@)))')\n" +
				"dnl Eleventh argument, portable\n" +
				"dnl Eleventh argument, GNU style\n" +
				"define(`Eleventh', `$11')\n" +
				"first1(`a', `b', `c', `d', `e', `f', `g', `h', `i', `j', `k')\n" +
				"eleventh(`a', `b', `c', `d', `e', `f', `g', `h', `i', `j', `k')\n" +
				"Eleventh(`a', `b', `c', `d', `e', `f', `g', `h', `i', `j', `k')\n"
		);
		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"A1\n" +
				"k\n" +
				"k\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
}
