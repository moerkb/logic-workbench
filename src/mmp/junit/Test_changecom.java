/** --------------------------------------------------------------------------
 * Test 'changecom'
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
 * $Id: Test_changecom.java 322 2008-06-30 10:41:57Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import mmp.builtins.changecom;
import mmp.engine.Engine;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

import org.junit.Test;

/**
 * @author Burkhardt Renz
 *
 */
public class Test_changecom {

	@Test
	public final void testInstInfo() throws IOException, SyntaxErrorException, RuntimeErrorException {
		
		Macro m = new changecom();
		//System.out.println( m.getInstInfo().toString() );
		
		// compare result
		assertTrue( "expected InstInfo", m.getInstInfo().toString().equals("[mmp.builtins.changecom]") );
	}
	
	@Test
	public final void test103changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`comment', `COMMENT')\n" +
				"# A normal comment\n" +
				"changecom(`/*', `*/')\n" +
				"# Not a comment anymore\n" +
				"But: /* this is a comment now */ while this is not a comment\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"# A normal comment\n" +
				"\n" +
				"# Not a COMMENT anymore\n" +
				"But: /* this is a comment now */ while this is not a COMMENT\n" 
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
	public final void test104changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`comment', `COMMENT')\n" +
				"changecom\n" +
				"# Not a comment anymore\n" +
				"changecom(`#', `')\n" +
				"# comment again\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"# Not a COMMENT anymore\n" +
				"\n" +
				"# comment again\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "changecom" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test
	public final void test105changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`a', `b')\n" +
				"«a»\n" +
				"changecom(`«', `»')\n" +
				"«a»\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"«b»\n" +
				"\n" +
				"«a»\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp checks delimiters!
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test106changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`hi', `HI')\n" +
				"define(`hi1hi2', `hello')\n" +
				"changecom(`q', `Q')\n" +
				"q hi Q hi\n" +
				"changecom(`1', `2')\n" +
				"hi1hi2\n" +
				"hi 1hi2\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"q hi Q HI\n" +
				"\n" +
				"hello\n" +
				"HI 1hi2\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp checks delimiters!
	 */
	@Test (expected=SyntaxErrorException.class)
	public final void test107changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`echo', `$#:$*:$@:')\n" +
				"define(`hi', `HI')\n" +
				"changecom(`(',`)')\n" +
				"traceon()\n" +
				"echo(hi)\n" +
				"changecom\n" +
				"changecom(`((', `))')\n" +
				"echo(hi)\n"  +
				"echo((hi))\n" +
				"changecom(`,', `)')\n" +
				"echo(hi,hi)bye)\n" +
				"changecom\n" +
				"echo(hi,`,`'hi',hi)\n" +
				"echo(hi,`,`'hi',hi`'changecom(`,,', `hi'))\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"0:::(hi)\n" +
				"\n" +
				"\n" +
				"1:HI:HI:\n" +
				"0:::((hi))\n" +
				"\n" +
				"1:HI,hi)bye:HI,hi)bye:\n" +
				"\n" +
				"3:HI,,HI,HI:HI,,`'hi,HI:\n" +
				"3:HI,,`'hi,HI:HI,,`'hi,HI:\n"
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
	public final void test108changecom() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"changecom(`/*', `*/')\n" +
				"/*dangling comment\n" 
		);	
	
		String expectedOutput = new String(
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
