/** --------------------------------------------------------------------------
 * Test format
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
 * $Id: Test_format.java 342 2008-07-03 11:27:43Z brenz $
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
public class Test_format {

	@Test
	public final void test157format() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
						"define(`foo', `The brown fox jumped over the lazy dog')\n" +
						"format(`The string \"%s\" uses %d characters', foo, len(foo))\n" +
						// No * in Java!!
						//"format(`%*.*d', `-1', `-1', `1')\n" 
						"format(`%1d', `1')\n" +
						"format(`%.0f', `56789.9876')\n" +
						// No * in Java!!
						"len(format(`%-5000X', `1'))\n" +
						//"ifelse(format(`%010F', `infinity'), `       INF', `success',\n" +
       			//"       format(`%010F', `infinity'), `  INFINITY', `success',\n" +
       			//"       format(`%010F', `infinity'))\n" +
						"ifelse(format(`%010f', `Infinity'), `       INF', `success',\n" +
       			"       format(`%010f', `Infinity'), `  Infinity', `success',\n" +
       			"       format(`%010f', `Infinity'))\n" +
						// 0X1.0P1 in Java
						//"ifelse(format(`%.1A', `1.999'), `0X1.0P+1', `success',\n" +
       			//"       format(`%.1A', `1.999'), `0X2.0P+0', `success',\n" +
       			//"       format(`%.1A', `1.999'))\n" +
						"ifelse(format(`%.1A', `1.999'), `0X1.0P1', `success',\n" +
       			"       format(`%.1A', `1.999'), `0X2.0P0', `success',\n" +
       			"       format(`%.1A', `1.999'))\n" +
						// needs width.precision in Java
						//"format(`%g', `0xa.P+1')\n"
						"format(`%2.2g', `0xa.P+1')\n"
		);	
	
		String expectedOutput = new String(
						"\n" +
						"The string \"The brown fox jumped over the lazy dog\" uses 38 characters\n" +
						"1\n" +
						"56790\n" +
						"5000\n" +
						"success\n" +
						"success\n" +
						"20\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test158format() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
					"include(`forloop.m4')\n" +
					"forloop(`i', `1', `10', `format(`%6d squared is %10d\n" +
					"', i, eval(i**2))')\n" 
		);	
		String expectedOutput = new String(
					"\n" +
					"     1 squared is          1\n" +
					"     2 squared is          4\n" +
					"     3 squared is          9\n" +
					"     4 squared is         16\n" +
					"     5 squared is         25\n" +
					"     6 squared is         36\n" +
					"     7 squared is         49\n" +
					"     8 squared is         64\n" +
					"     9 squared is         81\n" +
					"    10 squared is        100\n" +
					"\n"
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
	
	/*
	 * mmp throws an excpeiton where GNU m4 reports an error.
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test159format() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
					"format(`%p', `0')\n"
		);	
		String expectedOutput = new String(
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
}
