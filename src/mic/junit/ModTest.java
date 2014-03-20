/** --------------------------------------------------------------------------
 * Test of operator %
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mic is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mic is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: ModTest.java 529 2008-08-24 12:45:36Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import static org.junit.Assert.assertEquals;
import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Mathias Gutenbrunner
 *
 */
public class ModTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void mod001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 % 0";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void mod002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 % 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 % 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void mod004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 % 0";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void mod005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 % 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 % 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void mod007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 % 0";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void mod008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 % 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 % 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 % 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 % 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "32 % 10";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % 10 % 4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % 4 % 10";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 % 37 % 10";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 % 10 % 37";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "10 % 5 % 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 % -3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 % 3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 % -3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % 10 % -4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % -10 % 4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % -10 % -4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % 10 % 4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % 10 % -4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % -10 % 4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % -10 % -4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % (10 % 4)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % (10 % -4)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % (-10 % 4)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "37 % (-10 % -4)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % (10 % 4)";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % (10 % -4)";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % (-10 % 4)";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-37 % (-10 % -4)";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(37 % 10) % 4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(-37 % 10) % 4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(37 % 10) % 4";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mod039() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(-37 % 10) % 4";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
}
