/** --------------------------------------------------------------------------
 * Test 'undefine'
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
 * $Id: Test_undefine.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_undefine {

	@Test 
	public final void test_noargundefine() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"undefine is a word.\n"
		);	
	
		String expectedOutput = new String(
			"undefine is a word.\n"
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
	public final void test040undefine() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"foo bar blah\n" +
	      "define(`foo', `some')define(`bar', `other')define(`blah', `text')\n" +
				"foo bar blah\n" +
				"undefine(`foo')\n" +
				"foo bar blah\n" +
				"undefine(`bar', `blah')\n" +
				"foo bar blah\n"
		);	
	
		String expectedOutput = new String(
				"foo bar blah\n" +
				"\n" +
				"some other text\n" +
				"\n" +
				"foo other text\n" +
				"\n" +
				"foo bar blah\n"
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
	public final void test041undefine() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`f', ``$0':$1')\n" +
				"f(f(f(undefine(`f')`hello world')))\n" +
				"f(`bye')\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"f:f:f:hello world\n" +
				"f(bye)\n" 
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
