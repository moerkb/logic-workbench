/** --------------------------------------------------------------------------
 * Test 'improved_.' 
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
 * $Id: Test_improved.java 322 2008-06-30 10:41:57Z brenz $
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
public class Test_improved {

	@Test 
	public final void test188improved_e() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`exch', ``$2', `$1'')\n" +
				"define(exch(`expansion text', `macro'))\n" +
				"macro\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"expansion text\n" 
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
	public final void test189improved_f1() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"undivert(`forloop2.m4')dnl\n" +
				"include(`forloop2.m4')\n" +
				"forloop(`i', `2', `1', `no iteration occurs')\n" +
				"forloop(`', `1', `2', ` odd iterator name')\n" +
				"forloop(`i', `5 + 5', `0xc', ` 0x`'eval(i, `16')')\n"
		);	
	
		String expectedOutput = new String(
				"divert(`-1')\n" +
				"# forloop(var, from, to, stmt) - improved version:\n" +
				"#   works even if VAR is not a strict macro name\n" +
				"#   performs sanity check that FROM is larger than TO\n" +
				"#   allows complex numerical expressions in TO and FROM\n" +
				"define(`forloop', `ifelse(eval(`($3) >= ($2)'), `1',\n" +
				"  `pushdef(`$1', eval(`$2'))_$0(`$1',\n" +
				"    eval(`$3'), `$4')popdef(`$1')')')\n" +
				"define(`_forloop',\n" +
				"  `$3`'ifelse(indir(`$1'), `$2', `',\n" +
				"    `define(`$1', incr(indir(`$1')))$0($@)')')\n" +
				"divert`'dnl\n" +
				"\n" +
				"\n" +
				" odd iterator name odd iterator name\n" +
				" 0xa 0xb 0xc\n"
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
	
	@Test (expected=SyntaxErrorException.class)
	public final void test189improved_f2() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`forloop2.m4')\n" +
				"forloop(`i', `a', `b', `non-numeric bounds')\n"
		);	
	
		String expectedOutput = new String(
				"m4:stdin:6: bad expression in eval (bad input): (b) >= (a)\n" +
				"\n"
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
	public final void test191improved_f() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreachq.m4')\n" +
				//"traceon(`shift')debugmode(`aq')\n" +
				//is set by function in mmp
				"foreachq(`x', ``1', `2', `3', `4'', `x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test192improved_f() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreachq2.m4')\n" +
				//"traceon(`shift')debugmode(`aq')\n" +
				//is set by function in mmp
				"foreachq(`x', ``1', `2', `3', `4'', `x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test193improved_f() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreachq3.m4')\n" +
				//"traceon(`shift')debugmode(`aq')\n" +
				//is set by function in mmp
				"foreachq(`x', ``1', `2', `3', `4'', `x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
		
	@Test 
	public final void test194improved_f() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreach2.m4')\n" +
				//"traceon(`shift')debugmode(`aq')\n" +
				//is set by function in mmp
				"foreach(`x', ``1', `2', `3', `4'', `x\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test195improved_f() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"include(`foreach2.m4')\n" +
				"include(`foreachq2.m4')\n" +
				"dnl 0-element list:\n" +
				"foreach(`x', `', `<x>') / foreachq(`x', `', `<x>')\n" +
				"dnl 1-element list of empty element\n" +
				"foreach(`x', `()', `<x>') / foreachq(`x', ``'', `<x>')\n" +
				"dnl 2-element list of empty elements\n" +
				"foreach(`x', `(`',`')', `<x>') / foreachq(`x', ``',`'', `<x>')\n" +
				"dnl 1-element list of a comma\n" +
				"foreach(`x', `(`,')', `<x>') / foreachq(`x', ``,'', `<x>')\n" +
				"dnl 2-element list of unbalanced parentheses\n" +
				"foreach(`x', `(`(', `)')', `<x>') / foreachq(`x', ``(', `)'', `<x>')\n" +
				"define(`ab', `oops')dnl using defn(`iterator')\n" +
				"foreach(`x', `(`a', `b')', `defn(`x')') /dnl\n" +
 				" foreachq(`x', ``a', `b'', `defn(`x')')\n" +
				"define(`active', `ACT, IVE')\n" +
				"traceon(`active')\n" +
				"dnl list of unquoted macros; expansion occurs before recursion\n" +
				"foreach(`x', `(active, active)', `<x>\n" +
				"')dnl\n" +
				"foreachq(`x', `active, active', `<x>\n" +
				"')dnl\n" +
				"dnl list of quoted macros; expansion occurs during recursion\n" +
				"foreach(`x', `(`active', `active')', `<x>\n" +
				"')dnl\n" +
				"foreachq(`x', ``active', `active'', `<x>\n" +
				"')dnl\n" +
				"dnl list of double-quoted macro names; no expansion\n" +
				"foreach(`x', `(``active'', ``active'')', `<x>\n" +
				"')dnl\n" +
				"foreachq(`x', ```active'', ``active''', `<x>\n" +
				"')dnl\n"
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				" / \n" +
				"<> / <>\n" +
				"<><> / <><>\n" +
				"<,> / <,>\n" +
				"<(><)> / <(><)>\n" +
				"ab / ab\n" +
				"\n" +
				"\n" +
				//"dnl @error{}m4trace: -4- active -> `ACT, IVE'\n" +
				//"dnl @error{}m4trace: -4- active -> `ACT, IVE'\n" +
				"<ACT>\n" +
				"<IVE>\n" +
				"<ACT>\n" +
				"<IVE>\n" +
				//"dnl @error{}m4trace: -3- active -> `ACT, IVE'\n" +
				//"dnl @error{}m4trace: -3- active -> `ACT, IVE'\n" +
				"<ACT>\n" +
				//"dnl @error{}m4trace: -3- active -> `ACT, IVE'\n" +
				//"dnl @error{}m4trace: -3- active -> `ACT, IVE'\n" +
				"<IVE>\n" +
				"<ACT>\n" +
				"<IVE>\n" +
				//"dnl @error{}m4trace: -1- active -> `ACT, IVE'\n" +
				"<ACT, IVE>\n" +
				//"dnl @error{}m4trace: -1- active -> `ACT, IVE'\n" +
				"<ACT, IVE>\n" +
				//"dnl @error{}m4trace: -1- active -> `ACT, IVE'\n" +
				"<ACT, IVE>\n" +
				//"dnl @error{}m4trace: -1- active -> `ACT, IVE'\n" +
				"<ACT, IVE>\n" +
				"<active>\n" +
				"<active>\n" +
				"<active>\n" +
				"<active>\n"
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		//System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
	@Test 
	public final void test198improved_c() throws IOException, SyntaxErrorException, RuntimeErrorException {
		String input = new String(
				"define(`cleardivert',\n" +
				"  `pushdef(`_n', divnum)divert(`-1')undivert($@)divert(_n)popdef(`_n')')\n" +
				"divert(`1')one\n" +
				"divert\n" +
				"cleardivert\n" +
				"undivert\n" +
				"define(`cleardivert',\n" +
				"  `pushdef(`_num', divnum)divert(`-1')ifelse(`$#', `0',\n" +
				"    `undivert`'', `undivert($@)')divert(_num)popdef(`_num')')\n" +
				"divert(`2')two\n" +
				"divert\n" +
				"cleardivert\n" +
				"undivert\n" 
		);	
	
		String expectedOutput = new String(
				"\n" +
				"\n" +
				"\n" +
				"one\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" 
		);		
		StringWriter output = new StringWriter();
		
		// run 
		Engine engine = new Engine( new StringReader(input), new PrintWriter(output) );
		engine.setTraceOn( "shift" );
		engine.getSettings().addToSearchPath( "gnu-examples" );
		engine.run();	
		
		System.out.println( output.toString() );
		// compare result
		assertTrue( "expected output", output.toString().equals(expectedOutput) );
	}
	
}
