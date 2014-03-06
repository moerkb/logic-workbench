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
 * $Id: Test_inhibiting.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_inhibiting {

	@Test
	public final void test006inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval\n" +
				"eval(`1')\n"
		);		
		String expectedOutput = new String(
				"eval\n" +
				"1\n"
		);
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	/*
	 * test007inhibiting: mmp does not support command line option '-P'
	 */
	
	@Test
	public final void test008inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"`divert'\n" +
				"`d'ivert\n" +
				"di`ver't\n" +
				"div`'ert\n"
		);		
		
		String expectedOutput = new String(
				"divert\n" +
				"divert\n" +
				"divert\n" +
				"divert\n"
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
	public final void test009inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"`'divert\n" +
				"divert`'\n"
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
	public final void test010inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`cde', `CDE')\n" +
				"define(`x', `substr(ab')\n" +
				"define(`y', `cde, `1', `3')')\n" +
				"x`'y\n"
		);		
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"bCD\n"
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
	public final void test011inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`x1', `len(`$1'')\n" +
				"define(`y1', ``$1')')\n" +
				"x1(`01234567890123456789')y1(`98765432109876543210')\n"
		);		
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"40\n"
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
	public final void test012inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`macro', `m')\n" +
				"macro(`m')macro\n" +
				"macro(`m')`'macro\n"
		);		
	
		String expectedOutput = new String(
				"\n" +
				"mmacro\n" +
				"mm\n"
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
	public final void test013inhibiting() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`macro', `di$1')\n" +
				"macro(`v')`ert'\n" +
				"macro(`v')ert\n"
		);		
	
		String expectedOutput = new String(
				"\n" +
				"divert\n" +
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
}
