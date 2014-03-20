/** --------------------------------------------------------------------------
 * Test 'undivert'
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
 * $Id: Test_undivert.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_undivert {

	@Test
	public final void test132undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')\n" +
				"This text is diverted.\n" +
				"divert\n" +
				"This text is not diverted.\n" +
				"undivert(`1')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"This text is not diverted.\n" +
				"\n" +
				"This text is diverted.\n" +
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
	public final void test133undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')diverted text\n" +
				"divert\n" +
				"undivert()\n" +
				"undivert(`0')\n" +
				"undivert\n" 
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"diverted text\n" +
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
	public final void test134undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')\n" +
				"This text is diverted first.\n" +
				"divert(`0')undivert(`1')dnl\n" +
				"undivert(`1')\n" +
				"divert(`1')\n" +
				"This text is also diverted but not appended.\n" +
				"divert(`0')undivert(`1')dnl\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"This text is diverted first.\n" +
				"\n" +
				"\n" +
				"This text is also diverted but not appended.\n"
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
	public final void test135undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')one\n" +
				"divert(`2')two\n" +
				"divert(`3')three\n" +
				"divert(`2')undivert`'dnl\n" +
				"divert`'undivert`'dnl\n"
		);		
		String expectedOutput = new String(
				"two\n" +
				"one\n" +
				"three\n"
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
	public final void test136undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`bar', `BAR')\n" +
				"undivert(`foo')\n" +
				"include(`foo')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"bar\n" +
				"\n" +
				"BAR\n" +
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
	public final void test137undivert() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"divert(`1')diversion one\n" +
				"divert(`2')undivert(`foo')dnl\n" +
				"divert(`3')diversion three\n" +
				"divert`'dnl\n" +
				"undivert(`1', `2', `foo', `3')dnl\n"
		);		
		String expectedOutput = new String(
				"diversion one\n" +
				"bar\n" +
				"bar\n" +
				"diversion three\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
}
