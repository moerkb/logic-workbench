/** --------------------------------------------------------------------------
 * Test of operator +
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
 * $Id: PlusTest.java 586 2008-09-07 10:34:08Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import static org.junit.Assert.assertEquals;
import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test of operator plus.
 * @author Burkhardt Renz
 *
 */
public class PlusTest {
	
	public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void plus001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 + 4";
		int expectedResult = 7;
		
		//Mic mic = new Mic();
		int result = mic.calculate( expression );
		
		// compare result
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus002() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 + 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus003() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-0 + -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void plus004() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-0 + 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus005() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 + -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus006() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 + -1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus007() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-1 + 0";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus008() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 + 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus009() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus010() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 + 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus011() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 0";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus012() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus013() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus014() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + -2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus015() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + -3";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus016() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-2 + 1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus017() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-2 + 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus018() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-2 + 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus019() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2147483647 + 1";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus020() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2147483646 + 1";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus021() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2147483647 + 2";
		int expectedResult = Integer.MIN_VALUE + 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void plus031() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + 3 + 7";
		int expectedResult = 20;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus032() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + 3 + -7";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus033() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + -3 + 7";
		int expectedResult = 14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus034() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + -3 + -7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus035() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-10 + 3 + 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus036() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-10 + 3 + -7";
		int expectedResult = -14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus037() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-10 + -3 + 7";
		int expectedResult = -6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus038() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-10 + -3 + -7";
		int expectedResult = -20;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void plus039() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + (3 + 7)";
		int expectedResult = 20;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	
	}
	
	@Test
	public final void plus040() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + -(3 + 7)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	
	}
	
	@Test
	public final void plus041() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(10 + 3) + 7";
		int expectedResult = 20;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	
	}
	
	@Test
	public final void plus042() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-(10 + 3) + 7";
		int expectedResult = -6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	
	}
	
}
