/** --------------------------------------------------------------------------
 * Test of operator &&
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
 * $Id: LandTest.java 554 2008-08-27 13:23:54Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import static org.junit.Assert.assertEquals;
import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Mathias Gutenbrnner
 *
 */
public class LandTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void land001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 && -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 && -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 && 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 && 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 && -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 && -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 && 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 && 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
		
	@Test
	public final void land012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 && -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 && -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 && 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 && 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 && -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void land029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 1 && 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void land030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 1 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void land031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 && 0 && 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void land032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 && 1 && 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
}
