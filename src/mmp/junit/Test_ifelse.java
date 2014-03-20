/** --------------------------------------------------------------------------
 * Test 'ifelse'
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
 * $Id: Test_ifelse.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_ifelse {

	@Test 
	public final void test_noargifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"ifelse is a word.\n"
		);	
	
		String expectedOutput = new String(
			"ifelse is a word.\n"
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
	public final void test060ifelse1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"ifelse(`some comments')\n"
		);	
	
		String expectedOutput = new String(
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
	
	@Test (expected=SyntaxErrorException.class)
	public final void test060ifelse2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"ifelse(`foo', `bar')\n"
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

	@Test
	public final void test061ifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"ifelse(`foo', `bar', `true')\n" +
				"ifelse(`foo', `foo', `true')\n" +
				"define(`foo', `bar')\n" +
				"ifelse(foo, `bar', `true', `false')\n" +
				"ifelse(foo, `foo', `true', `false')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"true\n" +
				"\n" +
				"true\n" +
				"false\n" 
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
	public final void test062ifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `ifelse(`$#', `0', ``$0'', `arguments:$#')')\n" +
				"foo\n" +
				"foo()\n" +
				"foo(`a', `b', `c')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"foo\n" +
				"arguments:1\n" +
				"arguments:3\n"
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
	public final void test063ifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"ifelse(`foo', `bar', `third', `gnu', `gnats')\n" +
				"ifelse(`foo', `bar', `third', `gnu', `gnats', `sixth')\n" +
				"ifelse(`foo', `bar', `third', `gnu', `gnats', `sixth', `seventh')\n" +
				"ifelse(`foo', `bar', `3', `gnu', `gnats', `6', `7', `8')\n"
		);	
	
		String expectedOutput = new String(
				"gnu\n" +
				"\n" +
				"seventh\n" +
				"7\n"
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
	public final void test064ifelse() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`e', `$@')define(`long', `01234567890123456789')\n" +
				"ifelse(long, `01234567890123456789', `yes', `no')\n" +
				"ifelse(`01234567890123456789', long, `yes', `no')\n" +
				"ifelse(long, `01234567890123456789-', `yes', `no')\n" +
				"ifelse(`01234567890123456789-', long, `yes', `no')\n" +
				"ifelse(e(long), `01234567890123456789', `yes', `no')\n" +
				"ifelse(`01234567890123456789', e(long), `yes', `no')\n" +
				"ifelse(e(long), `01234567890123456789-', `yes', `no')\n" +
				"ifelse(`01234567890123456789-', e(long), `yes', `no')\n" +
				"ifelse(-e(long), `-01234567890123456789', `yes', `no')\n" +
				"ifelse(-`01234567890123456789', -e(long), `yes', `no')\n" +
				"ifelse(-e(long), `-01234567890123456789-', `yes', `no')\n" +
				"ifelse(`-01234567890123456789-', -e(long), `yes', `no')\n" +
				"ifelse(-e(long)-, `-01234567890123456789-', `yes', `no')\n" +
				"ifelse(-`01234567890123456789-', -e(long)-, `yes', `no')\n" +
				"ifelse(-e(long)-, `-01234567890123456789', `yes', `no')\n" +
				"ifelse(`-01234567890123456789', -e(long)-, `yes', `no')\n" +
				"ifelse(`-'e(long), `-01234567890123456789', `yes', `no')\n" +
				"ifelse(-`01234567890123456789', `-'e(long), `yes', `no')\n" +
				"ifelse(`-'e(long), `-01234567890123456789-', `yes', `no')\n" +
				"ifelse(`-01234567890123456789-', `-'e(long), `yes', `no')\n" +
				"ifelse(`-'e(long)`-', `-01234567890123456789-', `yes', `no')\n" +
				"ifelse(-`01234567890123456789-', `-'e(long)`-', `yes', `no')\n" +
				"ifelse(`-'e(long)`-', `-01234567890123456789', `yes', `no')\n" +
				"ifelse(`-01234567890123456789', `-'e(long)`-', `yes', `no')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n" +
				"yes\n" +
				"yes\n" +
				"no\n" +
				"no\n"
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
	public final void test_limits1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`sigma', `ifelse(eval($1<=1),1,$1,`eval($1+sigma(decr($1)))')')\n" +
				"sigma(100)\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"5050\n"
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
	 * The exception depends on the environment, increase the argument
	 * of sigma to provoke the exception.
	 */
	@Test (expected=RuntimeErrorException.class)
	public final void test_limits2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`sigma', `ifelse(eval($1<=1),1,$1,`eval($1+sigma(decr($1)))')')\n" +
				"sigma(600)\n"
		);	
	
		String expectedOutput = new String(
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
	
