/** --------------------------------------------------------------------------
 * Test of operator <
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
 * $Id: LtTest.java 542 2008-08-26 10:46:49Z mgutenbrunner $
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
public class LtTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void lt001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 < 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 < 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 < 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 < 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 < 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 < 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 < 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 < -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 < 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 < -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 < 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 < 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 < 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 < -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 < 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 < -5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < 5 < 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(1 < 5) < 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < (5 < 4)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lt025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 < -5 < 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
