/** --------------------------------------------------------------------------
 * Test of conversion of hecadecimal numbers
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
 * $Id: HexadecimalNumbersTest.java 585 2008-09-06 17:44:49Z mgutenbrunner $
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
public class HexadecimalNumbersTest {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void hexadecimalNumbers001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0xFF";
		int expectedResult = 255;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0xF2";
		int expectedResult = 242;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0x3F";
		int expectedResult = 63;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0x32";
		int expectedResult = 50;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0x7FFFFFFF";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.NumberFormatException.class)
	public final void hexadecimalNumbers006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0xFFFFFFFF";
		
		mic.calculate( expression );
		
	}
	
	@Test
	public final void hexadecimalNumbers007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0x32";
		int expectedResult = -50;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0xff";
		int expectedResult = 255;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0XFF";
		int expectedResult = 255;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void hexadecimalNumbers010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0Xff";
		int expectedResult = 255;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
