/** --------------------------------------------------------------------------
 * Test 'dnl'
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
 * $Id: Test_dnl.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_dnl {

	@Test 
	public final void test090dnl() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `Macro `foo'.')dnl A very simple macro, indeed.\n" +
				"foo\n"
		);	
	
		String expectedOutput = new String(
				"Macro foo.\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test091dnl() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"dnl(`args are ignored, but side effects occur',\n" +
				"define(`foo', `like this')) while this text is ignored: undefine(`foo')\n" +
				"See how `foo' was defined, foo?\n"
		);	
	
		String expectedOutput = new String(
				//"m4:stdin:1: Warning: excess arguments to builtin `dnl' ignored\n" +
				//mmp doesn't output this warning
				"See how foo was defined, like this?\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test092dnl() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"m4wrap(`m4wrap(`2 hi\n" +
				"')0 hi dnl 1 hi')\n" +
				"define(`hi', `HI')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				//"m4:stdin:1: Warning: end of file treated as newline\n" +
				// mmp ddoesn't output this warning
				//"0 HI 2 HI\n"
				// mmp uses queue for wrap, that means that '2 hi' comes after 'dnl' 
				"0 HI "
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
}
