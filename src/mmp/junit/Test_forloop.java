/** --------------------------------------------------------------------------
 * Test 'forloop' and 'foreach'
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
 * $Id: Test_forloop.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_forloop {

	@Test 
	public final void test073forloop() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`forloop.m4')\n" +
				"forloop(`i', `1', `8', `i ')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"1 2 3 4 5 6 7 8 \n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test074forloop() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`forloop.m4')\n" +
				"forloop(`i', `1', `4', `forloop(`j', `1', `8', ` (i, j)')\n" +
				"')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				" (1, 1) (1, 2) (1, 3) (1, 4) (1, 5) (1, 6) (1, 7) (1, 8)\n" +
				" (2, 1) (2, 2) (2, 3) (2, 4) (2, 5) (2, 6) (2, 7) (2, 8)\n" +
				" (3, 1) (3, 2) (3, 3) (3, 4) (3, 5) (3, 6) (3, 7) (3, 8)\n" +
				" (4, 1) (4, 2) (4, 3) (4, 4) (4, 5) (4, 6) (4, 7) (4, 8)\n" +
				"\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/* 
	 * 075forloop just echos forloop.m4
	 */
	
	@Test 
	public final void test076foreach() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreach.m4')\n" +
				"foreach(`x', (foo, bar, foobar), `Word was: x\n" +
				"')dnl\n" +
				"include(`foreachq.m4')\n" +
				"foreachq(`x', `foo, bar, foobar', `Word was: x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"Word was: foo\n" +
				"Word was: bar\n" +
				"Word was: foobar\n" +
				"\n" +
				"Word was: foo\n" +
				"Word was: bar\n" +
				"Word was: foobar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test077foreach() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreach.m4')\n" +
				"define(`_case', `  $1)\n" +
 				"    $2=\" $1\";;\n" +
				"')dnl\n" +
				"define(`_cat', `$1$2')dnl\n" +
				"case $`'1 in\n" +
				"foreach(`x', `(`(`a', `vara')', `(`b', `varb')', `(`c', `varc')')',\n" +
        "        `_cat(`_case', x)')dnl\n" +
				"esac\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"case $1 in\n" +
				"  a)\n" +
				"    vara=\" a\";;\n" +
				"  b)\n" +
				"    varb=\" b\";;\n" +
				"  c)\n" +
				"    varc=\" c\";;\n" +
				"esac\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/* 
	 * 078forloop just echos foreach.m4
	 */
	
	@Test 
	public final void test079foreach() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`a', `1')define(`b', `2')define(`c', `3')\n" +
				"include(`foreach.m4')\n" +
				"include(`foreachq.m4')\n" +
				"foreach(`x', `(``a'', ``(b'', ``c)'')', `x\n" +
				"')\n" +
				"foreachq(`x', ```a'', ``(b'', ``c)''', `x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"1\n" +
				"(2)1\n" +
				"\n" +
				", x\n" +
				")\n" +
				"a\n" +
				"(b\n" +
				"c)\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/* 
	 * 080forloop just echos foreachq.m4
	 */
	
	@Test 
	public final void test081foreach() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreach.m4')include(`foreachq.m4')\n" +
				"foreach(`name', `(`a', `b')', ` defn(`name')')\n" +
				"foreachq(`name', ``a', `b'', ` defn(`name')')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				" a b\n" +
				" _arg1(`a', `b') _arg1(shift(`a', `b'))\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
}
