/** --------------------------------------------------------------------------
 * Test of operator ==
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
 * $Id: EqTest.java 551 2008-08-27 10:19:19Z mgutenbrunner $
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
public class EqTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void eq001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 == -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 == -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 == 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 == 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 == 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 == -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 == -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 == 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 == 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 == 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
		
	@Test
	public final void eq012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 == -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 == -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 == 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 == 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 == 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 == -0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 == 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 == -0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == 4 == 4";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(1 == 4) == 4";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 == (4 == 4)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 == 4 == 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(4 == 4) == 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void eq034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 == (4 == 1)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
