/** --------------------------------------------------------------------------
 * Test 'len' and 'index'
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
 * $Id: Test_len.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_len {

	@Test
	public final void test141len() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"len()\n" +
				"len(`abcdef')\n"
		);
		
		String expectedOutput = new String(
				"0\n" +
				"6\n"
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
	public final void test142index_macr() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`gnus, gnats, and armadillos', `nat')\n" +
				"index(`gnus, gnats, and armadillos', `dag')\n"
		);
		
		String expectedOutput = new String(
				"7\n" +
				"-1\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test143aindex_macr() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`abc')\n" +
				"index(`abc', `')\n" +
				"index(`abc', `b')\n"
		);
		
		String expectedOutput = new String(
				"m4:stdin:1: Warning: too few arguments to builtin `index'\n" +
				"0\n" +
				"0\n" +
				"1\n"
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
	public final void test143bindex_macr() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"index(`abc', `')\n" +
				"index(`abc', `b')\n"
		);
		
		String expectedOutput = new String(
				"0\n" +
				"1\n"
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
