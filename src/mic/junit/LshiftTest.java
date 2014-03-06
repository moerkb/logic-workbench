/** --------------------------------------------------------------------------
 * Test of operator <<
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
 * $Id: LshiftTest.java 582 2008-09-05 17:39:37Z mgutenbrunner $
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
public class LshiftTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void lshift001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 30";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 31";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 32";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 << 35";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 5";
		int expectedResult = 32;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 30";
		int expectedResult = 1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 31";
		int expectedResult = -2147483648;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 32";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 35";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void lshift015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 0";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 1";
		int expectedResult = 10;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 5";
		int expectedResult = 160;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 30";
		int expectedResult = 1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 31";
		int expectedResult = -2147483648;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 32";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 << 35";
		int expectedResult = 40;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 0";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 1";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 5";
		int expectedResult = -32;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 30";
		int expectedResult = -1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 31";
		int expectedResult = -2147483648;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 32";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << 35";
		int expectedResult = -8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 0";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 1";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 5";
		int expectedResult = -32;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 30";
		int expectedResult = -1073741824;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 31";
		int expectedResult = -2147483648;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 32";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift035() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2147483647 << 35";
		int expectedResult = -8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift036() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 0";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift037() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift038() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift039() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 30";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift040() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 31";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift041() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 32";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift042() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-2147483648 << 35";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift043() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << -31";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift044() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 << -31";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift045() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << 8 << 1";
		int expectedResult = 512;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lshift046() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 << (8 << 1)";
		int expectedResult = 65536;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
