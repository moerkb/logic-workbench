/** --------------------------------------------------------------------------
 * Test of operator ~
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
 * $Id: BnegTest.java 544 2008-08-27 06:04:44Z mgutenbrunner $
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
public class BnegTest {
	
public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void bneg001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~0";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~-1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~1";
		int expectedResult = -2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~-2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~5";
		int expectedResult = -6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~-6";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~2147483647";
		int expectedResult = Integer.MIN_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~-2147483648";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void bneg009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "~~5";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
