/** --------------------------------------------------------------------------
 * Test 'instinfo' 
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
 * $Id: Test_instinfo.java 358 2008-07-10 10:12:06Z brenz $
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
public class Test_instinfo {

	@Test
	public final void instinfo_noarg() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instinfo\n"
		);		
		String expectedOutput = new String(
				"instinfo\n"
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
	public final void instinfo_builtins() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instinfo(`changecom')\n" +
				"instinfo(`define')\n" +
				"instinfo(`pushdef')\n" +
				"instinfo(`include')\n" +
				"instinfo(`sinclude')\n" +
				"instinfo(`incr')\n" +
				"instinfo(`decr')\n"
		);		
		String expectedOutput = new String(
				"mmp.builtins.changecom\n" +
				"mmp.builtins.define,define\n" +
				"mmp.builtins.define,pushdef\n" +
				"mmp.builtins.include,include\n" +
				"mmp.builtins.include,sinclude\n" +
				"mmp.builtins.incr,incr\n" +
				"mmp.builtins.incr,decr\n"
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
	public final void instinfo_foo1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"instreg(`foo', `mmp.engine.UserMacro', `foo', `bar')\n" +
				"foo\n" +
				"instinfo(`foo')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"bar\n" +
				"mmp.engine.UserMacro,foo,bar\n"		
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test
	public final void instreg_foo2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo',`bar')\n" +
				"foo\n" +
				"instreg(`fooCopy', instinfo(`foo'))\n" +
				"undefine(`foo')\n" +
				"foo\n" +
				"fooCopy\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"bar\n" +
				"\n" +
				"\n" +
				"foo\n" +
				"bar\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
}
