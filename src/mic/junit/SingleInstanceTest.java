/** --------------------------------------------------------------------------
 * Test of the solving of multiple expressions, using only one instance of MIC
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
 * $Id: SingleInstanceTest.java 647 2008-09-21 12:23:38Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import static org.junit.Assert.assertEquals;
import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Test;


/**
 * @author Mathias Gutenbrunner
 *
 */
public class SingleInstanceTest {
	
  public Mic mic = new Mic();
  
	@Test
	public final void singleInstance001() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(200 + 57) / 50 ^ (2 | 4) && 2000 - 50";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void singleInstance002() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(500 + 57) / 50 ^ (2 | 4)";
		int expectedResult = 13;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void singleInstance003() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(200 + 57) / 50 ^ (2 | 4)";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void singleInstance004() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(200 + 57) / 50 ^ (2 | 4) && 1000 / 1001";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
}
