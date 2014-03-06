/** --------------------------------------------------------------------------
 * Test of operator *
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
 * $Id: MulTest.java 532 2008-08-25 06:15:57Z mgutenbrunner $
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
public class MulTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void mul001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 * 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 * 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 * -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 * 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 * 1";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 * -1";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 * 1";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 * 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 * 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 * -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 * 5";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 * 5";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 * -5";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * 7";
		int expectedResult = 21;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * 7";
		int expectedResult = -21;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -7";
		int expectedResult = -21;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * -7";
		int expectedResult = 21;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "46341 * 46341";
		int expectedResult = -2147479015;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "46340 * 46340";
		int expectedResult = 2147395600;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
	@Test
	public final void mul020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 * 1";
		int expectedResult = 2147483647;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 * 2147483647";
		int expectedResult = 2147483647;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected=java.lang.NumberFormatException.class)
	public final void mul022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483648 * 1";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * 5 * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * 5 * -2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -5 * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -5 * -2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * 5 * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * 5 * -2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * -5 * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void mul030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 * -5 * -2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * 5 * 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * 0 * 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 * 5 * 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * (5 * 2)";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * (5 * -2)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * (-5 * 2)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * (-5 * -2)";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -(5 * 2)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul039() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -(5 * -2)";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul040() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -(-5 * 2)";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul041() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 * -(-5 * -2)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul042() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(3 * 5) * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul043() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(3 * -5) * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul044() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(-3 * 5) * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul045() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(-3 * -5) * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul046() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(3 * 5) * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul047() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(3 * -5) * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul048() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(-3 * 5) * 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul049() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(-3 * -5) * 2";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul050() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(3 * 5 * 2)";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void mul051() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-(3 * 5 * 2)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
}
