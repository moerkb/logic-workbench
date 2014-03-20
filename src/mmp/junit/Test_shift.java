/** --------------------------------------------------------------------------
 * Test 'shift'
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
 * $Id: Test_shift.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_shift {

	@Test 
	public final void test_noargshift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
			"shift is a word.\n"
		);	
	
		String expectedOutput = new String(
			"shift is a word.\n"
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
	public final void test065shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"shift\n" +
				"shift(`bar')\n" +
				"shift(`foo', `bar', `baz')\n"
		);	
	
		String expectedOutput = new String(
				"shift\n" +
				"\n" +
				"bar,baz\n" 
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
	public final void test066shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`reverse', `ifelse(`$#', `0', , `$#', `1', ``$1'',\n" +
				"                          `reverse(shift($@)), `$1'')')\n" +
				"reverse\n" +
				"reverse(`foo')\n" +
				"reverse(`foo', `bar', `gnats', `and gnus')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"foo\n" +
				"and gnus, gnats, bar, foo\n" 
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
	public final void test067shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`cond',\n" +
				"`ifelse(`$#', `1', `$1',\n" +
				"        `ifelse($1, `$2', `$3',\n" +
				"                `$0(shift(shift(shift($@))))')')')dnl\n" +
				"define(`side', `define(`counter', incr(counter))$1')dnl\n" +
				"define(`example1',\n" +
				"`define(`counter', `0')dnl\n" +
				"ifelse(side(`$1'), `yes', `one comparison: ',\n" +
				"       side(`$1'), `no', `two comparisons: ',\n" +
				"       side(`$1'), `maybe', `three comparisons: ',\n" +
				"       `side(`default answer: ')')counter')dnl\n" +
				"define(`example2',\n" +
				"`define(`counter', `0')dnl\n" +
				"cond(`side(`$1')', `yes', `one comparison: ',\n" +
				"     `side(`$1')', `no', `two comparisons: ',\n" +
				"     `side(`$1')', `maybe', `three comparisons: ',\n" +
				"     `side(`default answer: ')')counter')dnl\n" +
				"example1(`yes')\n" +
				"example1(`no')\n" +
				"example1(`maybe')\n" +
				"example1(`feeling rather indecisive today')\n" +
				"example2(`yes')\n" +
				"example2(`no')\n" +
				"example2(`maybe')\n" +
				"example2(`feeling rather indecisive today')\n"
		);	
	
		String expectedOutput = new String(
				"one comparison: 3\n" +
				"two comparisons: 3\n" +
				"three comparisons: 3\n" +
				"default answer: 4\n" +
				"one comparison: 1\n" +
				"two comparisons: 2\n" +
				"three comparisons: 3\n" +
				"default answer: 4\n"
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
	public final void test068shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`join.m4')\n" +
				"join,join(`-'),join(`-', `'),join(`-', `', `')\n" +
				"joinall,joinall(`-'),joinall(`-', `'),joinall(`-', `', `')\n" +
				"join(`-', `1')\n" +
				"join(`-', `1', `2', `3')\n" +
				"join(`', `1', `2', `3')\n" +
				"join(`-', `', `1', `', `', `2', `')\n" +
				"joinall(`-', `', `1', `', `', `2', `')\n" +
				"join(`,', `1', `2', `3')\n" +
				"define(`nargs', `$#')dnl\n" +
				"nargs(join(`,', `1', `2', `3'))\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				",,,\n" +
				",,,-\n" +
				"1\n" +
				"1-2-3\n" +
				"123\n" +
				"1-2\n" +
				"-1---2-\n" +
				"1,2,3\n" +
				"1\n" 
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
	public final void test069shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"undivert(`join.m4')dnl\n"
		);	
	
		String expectedOutput = new String(
				"divert(`-1')\n" +
				"# join(sep, args) - join each non-empty ARG into a single\n" +
				"# string, with each element separated by SEP\n" +
				"define(`join',\n" +
				"`ifelse(`$#', `2', ``$2'',\n" +
				"  `ifelse(`$2', `', `', ``$2'_')$0(`$1', shift(shift($@)))')')\n" +
				"define(`_join',\n" +
				"`ifelse(`$#$2', `2', `',\n" +
				"  `ifelse(`$2', `', `', ``$1$2'')$0(`$1', shift(shift($@)))')')\n" +
				"# joinall(sep, args) - join each ARG, including empty ones,\n" +
				"# into a single string, with each element separated by SEP\n" +
				"define(`joinall', ``$2'_$0(`$1', shift($@))')\n" +
				"define(`_joinall',\n" +
				"`ifelse(`$#', `2', `', ``$1$3'$0(`$1', shift(shift($@)))')')\n" +
				"divert`'dnl\n"
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
	public final void test070shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`quote.m4')\n" +
				"-quote-dquote-dquote_elt-\n" +
				"-quote()-dquote()-dquote_elt()-\n" +
				"-quote(`1')-dquote(`1')-dquote_elt(`1')-\n" +
				"-quote(`1', `2')-dquote(`1', `2')-dquote_elt(`1', `2')-\n" +
				"define(`n', `$#')dnl\n" +
				"-n(quote(`1', `2'))-n(dquote(`1', `2'))-n(dquote_elt(`1', `2'))-\n" +
				"dquote(dquote_elt(`1', `2'))\n" +
				"dquote_elt(dquote(`1', `2'))\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"----\n" +
				"--`'-`'-\n" +
				"-1-`1'-`1'-\n" +
				"-1,2-`1',`2'-`1',`2'-\n" +
				"-1-1-2-\n" +
				"``1'',``2''\n" +
				"``1',`2''\n" 
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
	public final void test071shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"undivert(`quote.m4')dnl\n"
		);	
	
		String expectedOutput = new String(
				"divert(`-1')\n" +
				"# quote(args) - convert args to single-quoted string\n" +
				"define(`quote', `ifelse(`$#', `0', `', ``$*'')')\n" +
				"# dquote(args) - convert args to quoted list of quoted strings\n" +
				"define(`dquote', ``$@'')\n" +
				"# dquote_elt(args) - convert args to list of double-quoted strings\n" +
				"define(`dquote_elt', `ifelse(`$#', `0', `', `$#', `1', ```$1''',\n" +
				"                             ```$1'',$0(shift($@))')')\n" +
				"divert`'dnl\n"
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
	public final void test072shift() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`argn', `ifelse(`$1', 1, ``$2'',\n" +
				"  `argn(decr(`$1'), shift(shift($@)))')')\n" +
				"argn(`1', `a')\n" +
				"define(`foo', `argn(`11', $@)')\n" +
				"foo(`a', `b', `c', `d', `e', `f', `g', `h', `i', `j', `k', `l')\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"a\n" +
				"\n" +
				"k\n"
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
