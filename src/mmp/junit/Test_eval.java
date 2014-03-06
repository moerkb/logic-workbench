/** --------------------------------------------------------------------------
 * Test eval
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
 * $Id: Test_eval.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import mic.parser.ParseException;
import mmp.engine.Engine;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

import org.junit.Test;

/**
 * @author Burkhardt Renz
 *
 */
public class Test_eval {

	@Test
	public final void test160incr1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"incr(`4')\n" +
				"decr(`7')\n"
				//"incr()\n" +
				//"decr()\n" 
		);		
		
		String expectedOutput = new String(
				"5\n" +
				"6\n"
			//  "dnl @error{}m4:stdin:3: empty string treated as 0 in builtin `incr'\n" +
			//	"dnl @result{}1\n" +
			//	"dnl @error{}m4:stdin:4: empty string treated as 0 in builtin `decr'\n" +
			//	"dnl @result{}-1\n"
		);		
				
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test(expected=SyntaxErrorException.class)
	public final void test160incr2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"incr(`4')\n" +
				"decr(`7')\n" +
				"incr()\n" +
				"decr()\n" 
		);		
		
		String expectedOutput = new String(
				"5\n" +
				"6\n" +
			  "m4:stdin:3: empty string treated as 0 in builtin `incr'\n" +
				"1\n" +
				"m4:stdin:4: empty string treated as 0 in builtin `decr'\n" +
				"-1\n"
		);		
				
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test(expected=SyntaxErrorException.class)
	public final void test161eval1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`2 = 2')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	/*
	 * mmp does not allow ++
	 */
	@Test(expected=SyntaxErrorException.class)
	public final void test161eval2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`++0')\n"
		);		
		String expectedOutput = new String(
				"0\n");
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}	
	
	@Test(expected=SyntaxErrorException.class)
	public final void test161eval3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`0 |= 1')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	@Test
	public final void test162eval1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`1 == (2 > 0)')\n" +
				"eval(`(1 == 2) > 0')\n" +
				"eval(`! 0 * 2')\n" +
				"eval(`! (0 * 2)')\n" +
				"eval(`1 | 1 ^ 1')\n" +
				"eval(`(1 | 1) ^ 1')\n" +
				"eval(`+ + - ~ ! ~ 0')\n" +
				"eval(`2 || 1 / 0')\n" +
				"eval(`0 && 1 % 0')\n"
		);		
		String expectedOutput = new String(
				"1\n" +
				"0\n" +
				"2\n" +
				"1\n" +
				"1\n" +
				"0\n" +
				"1\n" +
				"1\n" +
				"0\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test(expected=SyntaxErrorException.class)
	public final void test162eval2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`0 || 1 / 0')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	@Test(expected=SyntaxErrorException.class)
	public final void test162eval3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`2 && 1 % 0')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	/*
	 * mmp evaluates '0**0' to '1'
	 */ 
	@Test
	public final void test163eval1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`2 ** 3 ** 2')\n" +
				"eval(`(2 ** 3) ** 2')\n" +
				"eval(`0 ** 1')\n" +
				"eval(`2 ** 0')\n" +
				"eval(`0 ** 0')\n"
		);	
	
		String expectedOutput = new String(
				"512\n" +
				"64\n" +
				"0\n" +
				"1\n" +
				"1\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}
	
	@Test(expected=SyntaxErrorException.class)
	public final void test163eval3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`4 ** -2')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	/* 
	 * mmp does not support integer literals like 0r1:..., 0b...
	 */
	@Test
	public final void test164eval1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`-3 * 5')\n" +
				"eval(`-99 / 10')\n" +
				"eval(`-99 % 10')\n" +
				"eval(`99 % -10')\n" +
				"eval(index(`Hello world', `llo') >= 0)\n" +
				"define(`square', `eval(`($1) ** 2')')\n" +
				"square(`9')\n" +
				"square(square(`5')` + 1')\n" +
				"define(`foo', `666')\n" +
				"eval(foo / 6)\n"
		);		
		String expectedOutput = new String(
				"-15\n" +
				"-9\n" +
				"-9\n" +
				"9\n" +
				"1\n" +
				"\n" +
				"81\n" +
				"676\n" +
				"\n" +
				"111\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.run();	
		
		// compare result
		assertTrue( "expected output", output.getBuffer().toString().equals(expectedOutput) );
	}

	@Test(expected=SyntaxErrorException.class)
	public final void test164eval2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`foo', `666')\n" +
				"eval(`foo / 6')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	/*
	 * mmp does not accept 0x80000000, but -0x80000000
   * see java's Integer::decode
	 */
	@Test
	public final void test165eval() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`max_int', eval(`0x7fffffff'))\n" +
				"define(`min_int', incr(max_int))\n" +
				"eval(min_int` < 0')\n" +
				"eval(max_int` > 0')\n" +
				"ifelse(eval(min_int` / -1'), min_int, `overflow occurred')\n" +
				"min_int\n" +
				"eval(`-0x80000000 % -1')\n" +
				"eval(`-4 >> 1')\n" +
				"eval(`-4 >> 33')\n"
		);		
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"1\n" +
				"1\n" +
				"overflow occurred\n" +
				"-2147483648\n" +
				"0\n" +
				"-2\n" +
				"-2\n"
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
	public final void test166eval1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`666', `10')\n" +
				"eval(`666', `11')\n" +
				"eval(`666', `6')\n" +
				"eval(`666', `6', `10')\n" +
				"eval(`-666', `6', `10')\n" +
				"eval(`10', `', `0')\n" +
				"eval(`10', `16')\n" +
				"eval()\n"
		);
		String expectedOutput = new String(
				"666\n" +
				"556\n" +
				"3030\n" +
				"0000003030\n" +
				"-0000003030\n" +
				"10\n" +
				"a\n" +
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
	
	@Test(expected=SyntaxErrorException.class)
	public final void test166eval2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`1', `37')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
	@Test(expected=SyntaxErrorException.class)
	public final void test166eval3() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"eval(`1', , `-1')\n"
		);		
		StringWriter output = new StringWriter();
		// run 
		Engine engine = new Engine( new StringReader(input), output );
		engine.run();	
	}	
	
}
