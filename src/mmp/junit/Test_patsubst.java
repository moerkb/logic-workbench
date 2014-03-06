/** --------------------------------------------------------------------------
 * Test patsubst
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
 * $Id: Test_patsubst.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_patsubst {

	/*
	 * Since java does not support '\\<' we must use '\\b(?=\\w' in line 2
	 * Replacement in java uses '$n' for a capturing group       in line 3 and 4
	 */
	@Test
	public final void test152patsubst1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"patsubst(`GNUs not Unix', `^', `OBS: ')\n" +
				"patsubst(`GNUs not Unix', `\\b(?=\\w)', `OBS: ')\n" +
				"patsubst(`GNUs not Unix', `\\w*', `($0)')\n"  +
				"patsubst(`GNUs not Unix', `\\w+', `($0)')\n" +
				"patsubst(`GNUs not Unix', `[A-Z][a-z]+')\n"
		);	
	
		String expectedOutput = new String(
				"OBS: GNUs not Unix\n" +
				"OBS: GNUs OBS: not OBS: Unix\n" +
				"(GNUs)() (not)() (Unix)()\n" +
				"(GNUs) (not) (Unix)\n" +
				"GN not \n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test152patsubst2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"patsubst(`GNUs not Unix', `not', `NOT\\')\n"
		);	
	
		String expectedOutput = new String(
				"GNUs NOT Unix\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	/*
	 * mmp does not support capitalize, see gnu-examples/capitalize.m4
	 */
	@Test
	public final void test153patsubst() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`capitalize.m4')\n" +
				"upcase(`GNUs not Unix')\n" +
				"downcase(`GNUs not Unix')\n" 
				//"capitalize(`GNUs not Unix')\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"GNUS NOT UNIX\n" +
				"gnus not unix\n" 
				//"Gnus Not Unix\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test (expected=SyntaxErrorException.class)
	public final void test156patsubst1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"patsubst(`abc')\n"
		);	
	
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test156patsubst2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"patsubst(`abc', `')\n" +
				"patsubst(`abc', `', `\\\\-')\n"
		);	
	
		String expectedOutput = new String(
				"abc\n" +
				"\\-a\\-b\\-c\\-\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}


}
