/** --------------------------------------------------------------------------
 * Test 'include' and 'sincc
 * lude'
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
 * $Id: Test_include.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_include {

	@Test
	public final void include_noarg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include\n"
		);		
		String expectedOutput = new String(
				"include\n"
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
	public final void sinclude_noarg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"sinclude\n"
		);		
		String expectedOutput = new String(
				"sinclude\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test (expected=RuntimeErrorException.class)
	public final void test121include1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`none')\n"
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test (expected=RuntimeErrorException.class)
	public final void test121include2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include()\n"
		);		
		String expectedOutput = new String(
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
	public final void test121include3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"sinclude(`none')\n" +
				"sinclude()\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n"		
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
	public final void test122include() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `FOO')\n" +
				"include(`incl.m4')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"Include file start\n" +
				"FOO\n" +
				"Include file end\n" +
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void test123include() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`bar', include(`incl.m4'))\n" +
				"This is `bar':  >>bar<<\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"This is bar:  >>Include file start\n" +
				"foo\n" +
				"Include file end\n" +
				"<<\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
}
