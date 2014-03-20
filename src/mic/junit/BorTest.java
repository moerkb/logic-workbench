/** --------------------------------------------------------------------------
 * Test of operator |
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
 * $Id: BorTest.java 564 2008-08-28 17:19:57Z mgutenbrunner $
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
public class BorTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void bor001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 | 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 | 4";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 | 0";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "4 | 2";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "2 | 4";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "5 | 3";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 | 5";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bor008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "11 | 3 | 4";
		int expectedResult = 15;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
}
