/** --------------------------------------------------------------------------
 * Test of operator !=
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
 * $Id: NeTest.java 552 2008-08-27 10:52:03Z mgutenbrunner $
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
public class NeTest {
public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void ne001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 != -3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 != -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 != 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 != 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 != -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 != -1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 != 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 != 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
		
	@Test
	public final void ne012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 != -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 != -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 != 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 != 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 != 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 != -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 != -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 != 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 != 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 != 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 != -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != 4 != 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(0 != 4) != 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 != (4 != 4)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 != 4 != 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne033() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "(4 != 4) != 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void ne034() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 != (4 != 0)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
