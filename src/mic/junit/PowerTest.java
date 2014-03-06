/** --------------------------------------------------------------------------
 * Test of operator **
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
 * $Id: PowerTest.java 537 2008-08-25 16:03:36Z mgutenbrunner $
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
public class PowerTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void power001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1";
		mic.calculate( expression );
			
	}
	
	@Test
	public final void power007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-5";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1**-5";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**1";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**3";
		int expectedResult = 125;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5**-3";
		mic.calculate( expression );
		
	}
	

	@Test
	public final void power023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0**-5";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**5";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1**-5";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**-0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**1";
		int expectedResult = -5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**3";
		int expectedResult = -125;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**4";
		int expectedResult = 625;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5**-3";
		mic.calculate( expression );
		
	}
  
	@Test
	public final void power039() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power040() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**-0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power041() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power042() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power043() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power044() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**0**-3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power045() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power046() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**-0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power047() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power048() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power049() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power051() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**1**-3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power052() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power053() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**-0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power054() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power055() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power056() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power057() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power058() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-1**-3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power059() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**3**0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void power060() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**3**1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power061() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**3**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power062() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**3**3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power063() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**3**-3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power064() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power065() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power066() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**-1";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void power067() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power068() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = java.lang.ArithmeticException.class)
	public final void power069() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0**-3**-3";
		mic.calculate( expression );
		
	}
	
}
