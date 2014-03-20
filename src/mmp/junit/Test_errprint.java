/** --------------------------------------------------------------------------
 * Test 'errprint'
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
 * $Id: Test_errprint.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_errprint {

	@Test
	public final void test178errprint() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"errprint(`Invalid arguments to forloop\n" +
				"')\n" +
				"errprint(`1')errprint(`2',`3\n" +
				"')\n"
		);		
		
		String expectedOutput = new String(
				"\n" +
				"\n"
		);		
		StringWriter output = new StringWriter();
		
		/*
		String expectedErr = new String(
				"dnl @error{}Invalid arguments to forloop\n" +
				"dnl @error{}12 3\n"
		);		
		mmp actually uses two lines for the two last errprints
		*/
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
}
