/** --------------------------------------------------------------------------
 * Test of operator <=
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
 * $Id: LeTest.java 547 2008-08-27 06:33:20Z mgutenbrunner $
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
public class LeTest {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void le001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 <= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 <= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 <= 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 <= 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 <= 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 <= 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 <= 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 <= -0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 <= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 <= -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 <= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 <= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 <= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 <= -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 <= 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 <= -5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= 5 <= 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(1 <= 5) <= 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 <= (5 <= 4)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void le025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 <= -5 <= 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
