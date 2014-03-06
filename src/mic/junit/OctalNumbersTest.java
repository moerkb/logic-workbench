/** --------------------------------------------------------------------------
 * Test of conversion of octal numbers
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gieﬂen-Friedberg University of Applied Sciences.
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
 * $Id: OctalNumbersTest.java 584 2008-09-06 17:38:07Z mgutenbrunner $
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
public class OctalNumbersTest {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void octalNumbers001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "077";
		int expectedResult = 63;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void octalNumbers002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "072";
		int expectedResult = 58;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void octalNumbers003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "037";
		int expectedResult = 31;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void octalNumbers004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "032";
		int expectedResult = 26;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void octalNumbers005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "017777777777";
		int expectedResult = Integer.MAX_VALUE;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = java.lang.NumberFormatException.class)
	public final void octalNumbers006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "037777777777";
		
		mic.calculate( expression );
		
	}
	
	@Test
	public final void octalNumbers007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-032";
		int expectedResult = -26;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
