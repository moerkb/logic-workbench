/** --------------------------------------------------------------------------
 * Test 'builtin'
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
 * $Id: Test_builtin.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_builtin {

	@Test 
	public final void builtin_noargs() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"builtin\n"
		);		
		String expectedOutput = new String(
				"builtin\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp does not allow defn(`divnum')
	 */
	@Test 
	public final void test054builtin() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"pushdef(`define', `hidden')\n" +
				"undefine(`undefine')\n" +
				"define(`foo', `bar')\n" +
				"foo\n" +
				//"builtin(`define', `foo', defn(`divnum'))\n" +
				"builtin(`define', `foo', `0')\n" +
				"foo\n" +
				"builtin(`define', `foo', `BAR')\n" +
				"foo\n" +
				"undefine(`foo')\n" +
				"foo\n" +
				"builtin(`undefine', `foo')\n" +
				"foo\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"hidden\n" +
				"foo\n" +
				"\n" +
				"0\n" +
				"\n" +
				"BAR\n" +
				"undefine(foo)\n" +
				"BAR\n" +
				"\n" +
				"foo\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	/*
	 * test055builtin
	 * mmp does not support option -P of GNU m4
	 */
	
	@Test (expected=SyntaxErrorException.class)
	public final void test056builtin1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"builtin()\n"
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	/*
	 * mmp allows this!!
	 */
	@Test // (expected=SyntaxErrorException.class)
	public final void test056builtin2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"builtin(`builtin')\n"
		);		
		String expectedOutput = new String(
				"builtin\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test (expected=SyntaxErrorException.class)
	public final void test056builtin3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"builtin(`builtin',)\n"
		);		
		String expectedOutput = new String(
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test057builtin() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"builtin(`include', `foo')dnl\n"
		);		
		String expectedOutput = new String(
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test058builtin() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`s', `builtin(`shift', $@)')dnl\n" +
				"define(`loop', `ifelse(`$2', `', `-', `$1$2: $0(`$1', s(s($@)))')')dnl\n" +
				"loop(`1')\n" +
				"loop(`1', `2')\n" +
				"loop(`1', `2', `3')\n" +
				"loop(`1', `2', `3', `4')\n" +
				"loop(`1', `2', `3', `4', `5')\n"
		);
		String expectedOutput = new String(
				"-\n" +
				"12: -\n" +
				"12: 13: -\n" +
				"12: 13: 14: -\n" +
				"12: 13: 14: 15: -\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
}
