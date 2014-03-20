/** --------------------------------------------------------------------------
 * Test 'define'
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
 * $Id: $
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
public class Test_define {

	@Test
	public final void define_arg1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define\n"
		);		
		String expectedOutput = new String(
				"define\n"
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
	 * define() defines a macro with name '' and expansion '';
	 *          not very useful, but possible
	 */          
	@Test
	public final void define_arg2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define()\n" +
				"define(`empty')\n" +
				"empty\n" +
				"define(`empty',)\n" +
				"empty\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"\n" +
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
	
	@Test
	public final void simpledefine() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`Author', `W. Shakespeare')dnl\n" +
				"A Midsummer Night's Dream by Author\n" +
				"The `Author' is Author\n" +
				"The ``Author'' is Author\n"
		);		
		String expectedOutput = new String(
				"A Midsummer Night's Dream by W. Shakespeare\n" +
				"The Author is W. Shakespeare\n" +
				"The `Author' is W. Shakespeare\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void argsdefine() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`hello', `Hello $1!')dnl\n" +
				"hello(world)\n" +
				"hello(m4)\n"
		);		
		String expectedOutput = new String(
				"Hello world!\n" +
				"Hello m4!\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	
	@Test
	public final void test022define() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `Hello world.')\n" +
				"foo\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"Hello world.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test023define() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(foo, one)\n" +
				"define(foo, two)\n" +
				"one\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"two\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test
	public final void test024define() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`array', `defn(format(``array[%d]'', `$1'))')\n" +
				"define(`array_set', `define(format(``array[%d]'', `$1'), `$2')')\n" +
				"array_set(`4', `array element no. 4')\n" +
				"array_set(`17', `array element no. 17')\n" +
				"array(`4')\n" +
				"array(eval(`10 + 7'))\n" 
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"array element no. 4\n" +
				"array element no. 17\n" 
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
	public final void test025arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`exch', `$2, $1')\n" +
				"exch(`arg1', `arg2')\n"
		);
		String expectedOutput = new String(
				"\n" +
				"arg2, arg1\n"	
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test026arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`exch', `$2, $1')\n" +
				"define(exch(``expansion text'', ``macro''))\n" +
				"macro\n"
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"expansion text\n"	
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test027arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`test', ``Macro name: $0'')\n" +
				"test\n"
		);
		String expectedOutput = new String(
				"\n" +
				"Macro name: test\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test028arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `This is macro `foo'.')\n" +
				"foo\n"
		);
		String expectedOutput = new String(
				"\n" +
				"This is macro foo.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test029arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `single quoted $`'{1} output')\n" +
				"define(`bar', ``double quoted $'`{2} output'')\n" +
				"foo(`a', `b')\n" +
				"bar(`a', `b')\n"
		);
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"single quoted ${1} output\n" +
				"double quoted ${2} output\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test030arguments() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `$001 ${1} $1')\n" +
				"foo(`bar')\n"
	
		);
		String expectedOutput = new String(
				"\n" +
				"bar ${1} bar\n"
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
	public final void test187other_inco() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`x', `x')\n" +
				"define(`x', `x ')\n"
		);
		
		String expectedOutput = new String(
				"\n" +
				"\n"
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
