/** --------------------------------------------------------------------------
 * Test define
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
 * $Id: Test_regexp.java 343 2008-07-04 07:57:56Z brenz $
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
public class Test_regexp {

	@Test
	public final void test_regexp() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`Flight QA777. is the next flight. It is on time.', `Q[^u]\\d+\\.')\n"
		);	
	
		String expectedOutput = new String(
				"7\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	/*
	 * Syntax for regular expressions and replacements like Java, differns from gnu m4!!
	 */
	@Test
	public final void test144regexp() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`GNUs not Unix', `\\b[a-z]\\w+')\n"  +
				"regexp(`GNUs not Unix', `\\Q\\w*')\n" +
				"regexp(`GNUs not Unix', `\\w(\\w+)$', `*** $0 *** $1 ***')\n" +
				"regexp(`GNUs not Unix', `\\Q}}\\w*', `*** $0 *** $1 ***')\n"
		);	
	
		String expectedOutput = new String(
				"5\n" +
				"-1\n" +
				"*** Unix *** nix ***\n" +
				"\n"
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
	public final void test145regexp1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`abc', `(b)', `\\\\$10a')\n"
		);	
	
		String expectedOutput = new String(
				"\\b0a\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test145regexp2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`abc', `b', `$1')\n"
		);	
	
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test145regexp3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`abc', `((d)?)(c)', `$1$2$3$4$5$6')\n"
		);	
	
		String expectedOutput = new String(
				"\\b0a\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	@Test (expected=SyntaxErrorException.class)
	public final void test146regexp1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`abc')\n"
		);	
	
		String expectedOutput = new String(
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
	public final void test146regexp2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"regexp(`abc',`')\n"
		);	
	
		String expectedOutput = new String(
				"0\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.getBuffer().toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

}
