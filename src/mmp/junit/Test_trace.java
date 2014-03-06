/** --------------------------------------------------------------------------
 * Test 'traceon' and 'traceoff'
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
 * $Id: Test_trace.java 322 2008-06-30 10:41:57Z brenz $
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
 * Observe results in System.err to check the test!!
 * @author Burkhardt Renz
 *
 */
public class Test_trace {

	@Test 
	public final void test084trace() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `Hello World.')\n" +
				"define(`echo', `$@')\n" +
				"traceon(`foo', `echo')\n" +
				"foo\n" +
				"echo(`gnus', `and gnats')\n"
		);	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"Hello World.\n" +
				"gnus,and gnats\n"
		);		
		/* expect output on System.err similar to
			  m4trace: -1- foo -> `Hello World.'
				m4trace: -1- echo(`gnus', `and gnats') -> ``gnus',`and gnats''
		*/
		StringWriter output = new StringWriter();
		System.err.println( "--- test084trace ----------");
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test085trace() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"traceon\n" +
				"ifelse(`one level')\n" +
				"ifelse(ifelse(ifelse(`three levels')))\n" +
				"ifelse(ifelse(ifelse(ifelse(`four levels'))))\n" 
		);	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"\n"
		);		
		/* expect output on System.err similar to
		*/
		StringWriter output = new StringWriter();
		System.err.println( "--- test085trace ----------");
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "ifelse" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test086trace() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"traceoff(`foo')\n" +
				"traceon(`foo')\n" +
				"foo\n" +
				"define(`foo', `bar')\n" +
				"foo\n" +
				"undefine(`foo')\n" +
				"ifdef(`foo', `yes', `no')\n" +
				//"indir(`foo')\n" +
				"define(`foo', `blah')\n" +
				"foo\n" +
				"traceoff\n" +
				"foo\n"
		);	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"foo\n" +
				"\n" +
				"bar\n" +
				"\n" +
				"no\n" +
				"\n" +
				//"\n" +
				"blah\n" +
				"\n" +
				"blah\n" 
		);		
		/* expect output on System.err similar to
				m4trace: -1- foo -> `bar'
				m4trace: -1- foo -> `blah'
		*/
		StringWriter output = new StringWriter();
		System.err.println( "--- test086trace ----------");
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
}
