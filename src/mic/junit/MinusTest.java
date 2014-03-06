/** --------------------------------------------------------------------------
 * Test of operator -
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gieﬂen-Friedberg University of Applied Sciences.
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
 * $Id: MinusTest.java 586 2008-09-07 10:34:08Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import static org.junit.Assert.assertEquals;
import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test of operator minus.
 * @author Burkhardt Renz
 *
 */
public class MinusTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test //(expected=java.lang.ArithmeticException.class)
	public final void minus001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 - 4";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void minus002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 - 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 - 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 - -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "+0 - +0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 - +1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 - -3";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 - 3";
		int expectedResult = -4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 - -3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 - 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 - 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 - 3";
		int expectedResult = -3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 - 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
	@Test
	public final void minus015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 - 1";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483647 - 1";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 - 2";
		int expectedResult = Integer.MAX_VALUE - 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void minus026() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - 7 - 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus027() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - 7 - 10";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus028() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "7 - 12 - 3";
		int expectedResult = -8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus029() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - 7 - -3";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus030() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - -7 - 3";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus031() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - -7 - -3";
		int expectedResult = 22;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus032() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-12 - 7 - 3";
		int expectedResult = -22;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus033() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-12 - 7 - -3";
		int expectedResult = -16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus034() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-12 - -7 - -3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus035() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - (7 - 3)";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus036() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - (3 - 7)";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus037() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(12 - 7) - 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus038() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(7 - 12) - 3";
		int expectedResult = -8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus039() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-(12 - 7) - 3";
		int expectedResult = -8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void minus040() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "12 - -(7 - 3)";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
}
