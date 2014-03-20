/** --------------------------------------------------------------------------
 * Test of operator >=
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
 * $Id: GeTest.java 548 2008-08-27 06:49:25Z mgutenbrunner $
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
public class GeTest {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void ge001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >= 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 >= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 >= 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 >= 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 >= 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >= -0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 >= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >= -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >= 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >= -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >= 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >= -5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 >= 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 >= -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-5 >= -5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 >= 1 >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(4 >= 1) >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 >= (1 >= 5)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ge025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 >= -5 >= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
