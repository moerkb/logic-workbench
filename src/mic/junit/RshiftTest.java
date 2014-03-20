/** --------------------------------------------------------------------------
 * Test of operator >>
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
 * $Id: RshiftTest.java 583 2008-09-05 17:44:24Z mgutenbrunner $
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
public class RshiftTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	

	@Test
	public final void rshift001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	

	@Test
	public final void rshift002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 30";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 31";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 32";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 >> 35";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 30";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 31";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 32";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 >> 35";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 0";
		int expectedResult = 80;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 1";
		int expectedResult = 40;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 5";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 30";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 31";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 32";
		int expectedResult = 80;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "80 >> 35";
		int expectedResult = 10;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 0";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 5";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 30";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 31";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 32";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 >> 35";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 0";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 1";
		int expectedResult = -1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 5";
		int expectedResult = -67108864;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 30";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 31";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 32";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> 35";
		int expectedResult = -268435456;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "8 >> -31";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 >> -31";
		int expectedResult = -1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void rshift038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "8 >> 1 >> 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void rshift039() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "8 >> (1 >> 1)";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
}
