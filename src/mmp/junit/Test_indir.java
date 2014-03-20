/** --------------------------------------------------------------------------
 * Test 'indir'
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
 * $Id: Test_indir.java 322 2008-06-30 10:41:57Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
public class Test_indir {

	@Test
	public final void test051indir() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`$$internal$macro', `Internal macro (name `$0')')\n" +
				"$$internal$macro\n" +
				"indir(`$$internal$macro')\n" 
		);		
		String expectedOutput = new String(
				"\n" +
				"$$internal$macro\n" +
				"Internal macro (name $$internal$macro)\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test052indir1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`f', `1')\n" +
				"f(define(`f', `2'))\n" +
				"indir(`f', define(`f', `3'))\n" 
		);		
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"3\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test052indir2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`f', `1')\n" +
				"indir(`f', undefine(`f'))\n" 
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test053indir1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"indir(defn(`defn'), `divnum')\n"
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test053indir2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"indir(`define', defn(`defn'), `divnum')\n"
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp does not allow builtins as argument to defn
	 */
	@Test
	public final void test053indir3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				//"indir(`define', `foo', defn(`divnum'))\n" +
				"indir(`define', `foo', `0')\n" +
				"foo\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"0\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test053indir4() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"indir(`divert', defn(`foo'))\n"
		);		
		String expectedOutput = new String(
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
}
