/** --------------------------------------------------------------------------
 * Test 'changequote'
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
 * $Id: Test_changequote.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_changequote {

	@Test
	public final void test004input_proc() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changequote([,])dnl\n" +
				"define([gl_STRING_MODULE_INDICATOR],\n" +
  			"  [\n" +
    		"    dnl comment\n" +
    		"    GNULIB_]translit([$1],[a-z],[A-Z])[=1\n" +
  			"  ])dnl\n" +
  			"  gl_STRING_MODULE_INDICATOR([strcase])\n"
		);	
	
		String expectedOutput = new String(
				"  \n" +
				"        GNULIB_strcase=1\n" +
				"  \n"
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
	public final void test005input_proc() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changequote([,])dnl\n" +
				"define([gl_STRING_MODULE_INDICATOR],\n" +
  			"  [dnl comment\n" +
    		"  GNULIB_[]translit([$1], [a-z], [A-Z])=1dnl\n" +
  			"])dnl\n" +
  			"  gl_STRING_MODULE_INDICATOR([strcase])\n"
		);	
	
		String expectedOutput = new String(
				"    GNULIB_STRCASE=1\n"
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
	public final void test093changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"changequote(`[', `]')\n" +
			"define([foo], [Macro [foo].])\n" +
			"foo\n" 
		);	
	
		String expectedOutput = new String(
			"\n" +
			"\n" +
			"Macro foo.\n"
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
	public final void test094changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`a', `b')\n" +
				"«a»\n" +
				"changequote(`«', `»')\n" +
				"«a»\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"«b»\n" +
				"\n" +
				"a\n"
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
	public final void test095changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changequote(`[[[', `]]]')\n" +
				"define([[[foo]]], [[[Macro [[[[[foo]]]]].]]])\n" +
				"foo\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"Macro [[foo]].\n" 
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
	public final void test096changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `Macro `FOO'.')\n" +
				"changequote(`', `')\n" +
				"foo\n" +
				"`foo'\n" +
				"changequote(`,)\n" +
				"foo\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"Macro `FOO'.\n" +
				"`Macro `FOO'.'\n" +
				"\n" +
				"Macro FOO.\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp checks delimiters
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test097changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$@')\n" +
				"define(`hi', `HI')\n" +
				"changequote(`q', `Q')\n" +
				"q hi Q hi\n" +
				"echo(hi)\n" +
				"changequote\n" +
				"changequote(`-', `EOF')\n" +
				"- hi EOF hi\n" +
				"changequote\n" +
				"changequote(`1', `2')\n" +
				"hi1hi2\n" +
				"hi 1hi2\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"q HI Q HI\n" +
				"qHIQ\n" +
				"\n" +
				"\n" +
				" hi  HI\n" +
				"\n" +
				"\n" +
				"hi1hi2\n" +
				"HI hi\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		//engine.setTraceOn ( "changequote" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp checks delimiters
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test098changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$#:$@:')\n" +
				"define(`hi', `HI')\n" +
				"changequote(`(',`)')\n" +
				"echo(hi)\n" +
				"changequote\n" +
				"changequote(`((', `))')\n" +
				"echo(hi)\n" +
				"echo((hi))\n" +
				"changequote\n" +
				"changequote(`,', `)')\n" +
				"echo(hi,hi)bye)\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"0::hi\n" +
				"\n" +
				"\n" +
				"1:HI:\n" +
				"0::hi\n" +
				"\n" +
				"\n" +
				"1:HIhibye:\n" 
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
	 * mmp checks delimiters
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test099changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changequote(`[', `]')dnl\n" +
				"define([a], [1, (b)])dnl\n" +
				"define([b], [2])dnl\n" +
				"define([quote], [[$*]])dnl\n" +
				"define([expand], [_$0(($1))])dnl\n" +
				"define([_expand],\n" +
  		  "   [changequote([(], [)])$1changequote`'changequote(`[', `]')])dnl\n" +
				"expand([a, a, [a, a], [[a, a]]])\n" +
				"quote(a, a, [a, a], [[a, a]])\n" 
		);	
	
		String expectedOutput = new String(
				"1, (2), 1, (2), a, a, [a, a]\n" +
				"1,(2),1,(2),a, a,[a, a]\n" 
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
	public final void test100changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`hi', `HI')\n" +
				"changequote(`\"\"', `\"')\n" +
				"\"\"hi\"\"\"hi\"\n" +
				"\"\"hi\" \"\"hi\"\n" +
				"\"\"hi\"\" \"hi\"\n" +
				"changequote\n" +
				"`hi`hi'hi'\n" +
				"changequote(`\"', `\"')\n" +
				"\"hi\"hi\"hi\"\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"hihi\n" +
				"hi hi\n" +
				"hi\" \"HI\"\n" +
				"\n" +
				"hi`hi'hi\n" +
				"\n" +
				"hiHIhi\n"
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
	public final void test101changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`aaaaaaaaaaaaaaaaaaaa', `A')define(`q', `\"$@\"')\n" +
				"changequote(`\"', `\"')\n" +
				"q(q(\"aaaaaaaaaaaaaaaaaaaa\", \"a\"))\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"A,a\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test102changequot() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"`hello world'\n" +
			"`dangling quote\n"
		);	
	
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
}
