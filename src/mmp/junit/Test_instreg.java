/** --------------------------------------------------------------------------
 * Test 'instreg' and 'instpush'
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
 * $Id: Test_instreg.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_instreg {

	@Test
	public final void instreg_noargs() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instreg\n" +
				"instpush\n"
		);		
		String expectedOutput = new String(
				"instreg\n" +
				"instpush\n"
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
	public final void instreg_foo() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instreg(`foo', `mmp.engine.UserMacro', `foo', `bar')\n" +
				"foo\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"bar\n"
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
	public final void instreg_hello() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instreg(`hello', `mmp.engine.UserMacro', `hello', `Hello $1!')\n" +
				"hello(instreg)\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"Hello instreg!\n"
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
	public final void instreg_eval() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"undefine(`eval')\n" +
				"eval(`1+1')\n" +
				"instreg(`eval', `mmp.builtins.eval')\n" +
				"eval(`1+1')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"eval(1+1)\n" +
				"\n" +
				"2\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
}
