/** --------------------------------------------------------------------------
 * Test 'm4wrap'
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
 * $Id: Test_m4wrap.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_m4wrap {

	@Test
	public final void test115m4wrap() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`cleanup', `This is the `cleanup' action.\n" +
				"')\n" +
				"m4wrap(`cleanup')\n" +
				"This is the first and last normal input line.\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"This is the first and last normal input line.\n" +
				"This is the cleanup action.\n"
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
	 * check 115m4wrap redefines m4wrap such that is has FIFO behaviour.
	 * mmp's m4wrap conforms to POSIX and has FIFO semantics 
	 */
	@Test
	public final void testfifom4wrap() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`hello', `Hello $1!')\n" +
				"m4wrap(hello(`first wrapup line')\n" +
				")\n" +
				"m4wrap(hello(`second wrapup line'))\n" +
				"This is the first and last normal input line.\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"This is the first and last normal input line.\n" +
				"Hello first wrapup line!\n" +
				"Hello second wrapup line!"
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
	public final void test118m4wrap() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`f', `ifelse(`$1', `0', `Answer: 0!=1\n" +
				"', eval(`$1>1'), `0', `Answer: $2$1=eval(`$2$1')\n" +
				"', `m4wrap(`f(decr(`$1'), `$2$1*')')')')\n" +
				"f(`10')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"Answer: 10*9*8*7*6*5*4*3*2*1=3628800\n" 
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
	public final void test119m4wrap() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`aa', `AA\n" +
				"')\n" +
				"m4wrap(`a')m4wrap(`a')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"AA\n" 
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
	 * mmp allows this whereas GNU m4 does not
	 */
	@Test
	public final void test120m4wrap() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"m4wrap(`m4wrap(`)')len(abc')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"3"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "m4wrap" );
		engine.setTraceOn( "len" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
}
