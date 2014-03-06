/** --------------------------------------------------------------------------
 * Test of operator /
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
 * $Id: DivTest.java 566 2008-08-29 01:54:14Z brenz $
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
public class DivTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test (expected=java.lang.ArithmeticException.class)
	public final void div001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / 0";
		mic.calculate( expression );
		
	}

	@Test (expected=java.lang.ArithmeticException.class)
	public final void div002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 / 0";
		mic.calculate( expression );
		
	}
	@Test (expected=java.lang.ArithmeticException.class)
	public final void div003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / -0";
		mic.calculate( expression );
		
	}
	
	@Test (expected=java.lang.ArithmeticException.class)
	public final void div004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 / -0";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void div005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void div007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 / 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 / -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2 / 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2 / 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2 / -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2 / -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "8 / 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-8 / 2";
		int expectedResult = -4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "8 / -2";
		int expectedResult = -4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-8 / -2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 / 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 / 2";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 / -2";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 / -2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "7 / 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-7 / 7";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "7 / -7";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-7 / -7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 / 1";
		int expectedResult = 2147483647;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 / 2";
		int expectedResult = 1073741823;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / 3 / 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / 3 / -3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / -3 / 3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	@Test
	public final void div031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / -3 / -3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-25 / 3 / 3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-25 / 3 / -3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-25 / -3 / 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-25 / -3 / -3";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void div036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 / 3 / 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void div037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / 0 / 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void div038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "25 / 3 / 0";
		mic.calculate( expression );
		
	}
	
}
