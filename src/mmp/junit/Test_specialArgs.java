/** --------------------------------------------------------------------------
 * Test special arguments to macros
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
 * $Id: Test_specialArgs.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_specialArgs {

	@Test
	public final void test031pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`nargs', `$#')\n" +
				"nargs\n" +
				"nargs()\n" +
				"nargs(`arg1', `arg2', `arg3')\n" +
				"nargs(`commas can be quoted, like this')\n" +
				"nargs(arg1#inside comments, commas do not separate arguments}\n" +
				"still arg1)\n" +
				"nargs((unquoted parentheses, like this, group arguments))\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"0\n" +
				"1\n" +
				"3\n" +
				"1\n" +
				"1\n" +
				"1\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test032pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(underquoted, $#)\n" +
				"oops)\n" +
				"underquoted\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"0)\n" +
				"oops\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	
	@Test
	public final void test033pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$*')\n" +
				"echo(arg1,    arg2, arg3 , arg4)\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"arg1,arg2,arg3 ,arg4\n"	
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test034pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$@')\n" +
				"echo(arg1,    arg2, arg3 , arg4)\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"arg1,arg2,arg3 ,arg4\n"	
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test035pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo1', `$*')\n" +
				"define(`echo2', `$@')\n" +
				"define(`foo', `This is macro `foo'.')\n" +
				"echo1(foo)\n" +
				"echo1(`foo')\n" +
				"echo2(foo)\n" +
				"echo2(`foo')\n"
		);
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"This is macro This is macro foo..\n" +
				"This is macro foo.\n" +
				"This is macro foo.\n" +
				"foo\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test036pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo1', `$*')\n" +
				"define(`echo2', `$@')\n" +
				"define(`foo', `bar')\n" +
				"echo1(#foo'foo\n" +
				"foo)\n" +
				"echo2(#foo'foo\n" +
				"foo)\n"
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"#foo'foo\n" +
				"bar\n" +
				"#foobar\n" +
				"bar'\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test037pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$@')dnl\n" +
				"echo(echo(`01234567890123456789', `01234567890123456789')\n" +
				"echo(`98765432109876543210', `98765432109876543210'))\n" +
				"len((echo(`01234567890123456789',\n" +
		    "          `01234567890123456789')echo(`98765432109876543210',\n" +
		    "                                      `98765432109876543210')))\n" +
				"indir(`echo', indir(`echo', `01234567890123456789',\n" +
		    "                            `01234567890123456789')\n" +
		    "indir(`echo', `98765432109876543210', `98765432109876543210'))\n" +
				"define(`argn', `$#')dnl\n" +
				"define(`echo1', `-$@-')define(`echo2', `,$@,')dnl\n" +
				"echo1(`1', `2', `3') argn(echo1(`1', `2', `3'))\n" +
				"echo2(`1', `2', `3') argn(echo2(`1', `2', `3'))\n"
		);
		String expectedOutput = new String(
				"01234567890123456789,01234567890123456789\n" +
				"98765432109876543210,98765432109876543210\n" +
				"84\n" +
				"01234567890123456789,01234567890123456789\n" +
				"98765432109876543210,98765432109876543210\n" +
				"-1,2,3- 3\n" +
				",1,2,3, 5\n"
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
	public final void test038pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `$$$ hello $$$')\n" +
				"foo\n"
		);
		String expectedOutput = new String(
				"\n" +
				"$$$ hello $$$\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test039pseudo_arg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `no nested quote: $1')\n" +
				"foo(`arg')\n" +
				"define(`foo', `nested quote around $: `$'1')\n" +
				"foo(`arg')\n" +
				"define(`foo', `nested empty quote after $: $`'1')\n" +
				"foo(`arg')\n" +
				"define(`foo', `nested quote around next character: $`1'')\n" +
				"foo(`arg')\n" +
				"define(`foo', `nested quote around both: `$1'')\n" +
				"foo(`arg')\n" 
		);
	
		String expectedOutput = new String(
				"\n" +
				"no nested quote: arg\n" +
				"\n" +
				"nested quote around $: $1\n" +
				"\n" +
				"nested empty quote after $: $1\n" +
				"\n" +
				"nested quote around next character: $1\n" +
				"\n" +
				"nested quote around both: arg\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
}
