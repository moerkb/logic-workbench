/** --------------------------------------------------------------------------
 * Test of expressions with multiple operators
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
 * $Id: CalculationTest.java 623 2008-09-16 19:26:41Z mgutenbrunner $
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
public class CalculationTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	
	@Test
	public final void calculation001() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 4 + 2";
		int expectedResult = 14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation002() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * (4 + 2)";
		int expectedResult = 18;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation003() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 3 * 4";
		int expectedResult = 14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation004() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + (3 * 4)";
		int expectedResult = 14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation005() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 + 3) * 4";
		int expectedResult = 20;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation006() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 3 * 4";
		int expectedResult = -10;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation007() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - (3 * 4)";
		int expectedResult = -10;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation008() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 - 3) * 4";
		int expectedResult = -4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation009() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 3 / 4";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation010() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + (3 / 4)";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation011() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 + 3) / 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation012() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 3 / 4";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation013() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - (3 / 4)";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation014() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 - 3) / 4";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation015() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 4 / 2";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation016() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 / 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation017() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3 * 2) / 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation018() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * (2 / 3)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation019() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 5 + 2";
		int expectedResult = 34;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation020() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 ** 5) + 2";
		int expectedResult = 34;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation021() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** (5 + 2)";
		int expectedResult = 128;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation022() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 5 - 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation023() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 ** 5) - 2";
		int expectedResult = 30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation024() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** (5 - 2)";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation025() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 5 * 2";
		int expectedResult = 64;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation026() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 ** 5) * 2";
		int expectedResult = 64;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation027() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** (5 * 2)";
		int expectedResult = 1024;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation028() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 5 / 2";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation029() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 ** 5) / 2";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation030() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** (5 / 2)";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation031() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 2 ** 5";
		int expectedResult = 34;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation032() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + (2 ** 5)";
		int expectedResult = 34;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation033() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 + 2) ** 5";
		int expectedResult = 1024;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation034() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 2 ** 5";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation035() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - (2 ** 5)";
		int expectedResult = -30;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation036() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(2 - 2) ** 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation037() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 ** 2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation038() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * (2 ** 2)";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation039() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3 * 2) ** 2";
		int expectedResult = 36;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation040() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 / 2 ** 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation041() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 / (2 ** 2)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation042() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3 / 2) ** 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation043() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 ** (3 * (2 / 3))";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation044() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 / 3 || 3 * (2 / 3)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation045() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * (2 / 3) || 3 * (2 / 3)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation046() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 / 3 && 3 * (2 / 3)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation047() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 / 3 && 3 * 2 / 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation048() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "~(3 * 2 / 3 && 3 * (2 / 3))";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation049() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 2 / 3 || 3 * (2 / 3) ^ (3 * 2 / 3 && 3 * (2 / 3))";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation050() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 0 && 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation051() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || (0 && 0)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation052() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(1 || 0) && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation053() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "!1 || 0 && 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation054() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 0 && 1 ^ 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation055() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || (0 && (1 ^ 1))";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation056() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "((1 || 0) && 1) ^ 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation057() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 && 1 ^ 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation058() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 && (1 ^ 1)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation059() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(0 && 1) ^ 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation060() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 2 & 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation061() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && (2 & 1)";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation062() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(1 && 2) & 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation063() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 0 | 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation064() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && (0 | 2)";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation065() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(1 && 0) | 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation066() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 1 << 1 + 1";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation067() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 1 << 2 - 1";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation068() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 1 << 1 * 3";
		int expectedResult = 24;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation069() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 + 1 << 2 / 3";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation070() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 1 << 1 + 1";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation071() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 1 << 3 - 1";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation072() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 - 1 << 1 * 3";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation073() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 2 << 2 / 3";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation074() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 1 << 1 + 1";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation075() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 1 << 2 - 1";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation076() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 * 1 << 1 * 3";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation077() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 1 << 2 / 3";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation078() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 / 1 << 1 + 1";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation079() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 / 1 << 2 - 1";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation080() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 / 1 << 1 * 3";
		int expectedResult = 16;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation081() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 / 2 << 3 / 2";
		int expectedResult = 2;  
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation082() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 3 << 2 ** 0";
		int expectedResult = 16;  
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation083() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "8 + 8 >> 1 + 1";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation084() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "8 + 8 >> 3 - 1";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation085() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "8 + 8 >> 1 * 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation086() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "8 + 8 >> 16 / 8";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation087() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 - 8 >> 1 + 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation088() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 - 8 >> 3 - 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation089() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 - 8 >> 1 * 2";
		int expectedResult = 2; 
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation090() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 - 8 >> 4 / 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation091() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * 4 >> 1 + 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation092() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "8 * 1 >> 3 - 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation093() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 * 1 >> 1 * 3";
		int expectedResult = 2; 
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation094() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 * 1 >> 9 / 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation095() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 / 2 >> 1 + 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation096() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 / 2 >> 3 - 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation097() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 / 2 >> 1 * 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation098() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "16 / 2 >> 4 / 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation099() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ** 5 >> 2 ** 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation100() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 + 3 * 2";
		int expectedResult = 11;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation101() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 + 3 / 2";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation102() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 + 3 % 2";
		int expectedResult = 6;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation103() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 + 3 ** 2";
		int expectedResult = 14;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation104() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 3 * 2";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation105() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 3 / 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation106() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 3 % 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation107() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 3 ** 2";
		int expectedResult = -4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation108() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 - 3 + 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation109() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 * 3 / 2";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation110() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 * 3 % 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation111() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 * 3 ** 2";
		int expectedResult = 45;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation112() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 / 3 * 2";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation113() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 / 3 / 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation114() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 / 3 % 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation115() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 / 3 ** 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation116() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 % 3 * 2";
		int expectedResult = 4;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation117() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 % 3 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation118() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 % 3 % 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation119() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 % 3 ** 2";
		int expectedResult = 5;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation120() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 3 << 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation121() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 < 5 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation122() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 3 + 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation123() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 3 - 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation124() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 3 * 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation125() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 16 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation126() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 < 12 % 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation127() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 3 ** 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation128() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 3 << 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation129() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 2 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation130() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 3 + 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation131() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 3 - 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation132() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 3 * 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation133() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 8 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation134() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 6 % 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation135() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 3 ** 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation136() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 3 << 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation137() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 <= 5 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation138() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 3 + 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation139() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 3 - 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation140() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 3 * 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation141() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 16 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation142() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 <= 12 % 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation143() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 3 ** 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation144() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 3 << 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation145() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 2 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation146() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 3 + 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation147() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 3 - 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation148() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 3 * 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation149() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 8 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation150() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 6 % 4";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation151() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 3 ** 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation152() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 < 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation153() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 > 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation154() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 <= 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation155() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 >= 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation156() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 << 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation157() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 10 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation158() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 + 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation159() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 - 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation160() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 * 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation161() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 10 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation162() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "7 == 7 % 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation163() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 == 5 ** 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation164() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 != 5 < 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation165() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 != 1 > 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation166() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 != 5 <= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation167() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 != 5 >= 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation168() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 << 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation169() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 2 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation170() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 + 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation171() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 - 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation172() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 * 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation173() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
  
	@Test
	public final void calculation174() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "7 != 7 % 5";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}

	@Test
	public final void calculation175() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 != 5 ** 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation176() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 < 5 > 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	@Test
	public final void calculation177() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 < 5 <= 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
		
	@Test
	public final void calculation178() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 < 5 >= 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation179() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 2 < 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation180() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 4 <= 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation181() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 > 5 >= 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation182() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 <= 5 < 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation183() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 <= 5 > 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation184() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "0 <= 5 >= 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation185() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 0 < 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation186() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 4 <= 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation187() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 >= 5 > 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation188() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 & 7 == 7";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation189() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 & 7 != 7";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation190() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 & 7 < 3";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation191() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 & 6 > 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation192() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "7 & 8 <= 5";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation193() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 & 6 >= 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation194() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 & 1 << 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation195() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 & 2 >> 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation196() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 & 1 + 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation197() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "6 & 3 - 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation198() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 & 1 * 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation199() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 & 4 / 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation200() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "6 & 5 % 3";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation201() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 & 2 ** 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation202() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 5 == 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation203() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 5 != 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation204() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 2 < 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation205() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 5 > 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation206() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 2 <= 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation207() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 5 >= 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation208() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 ^ 1 << 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation209() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 2 >> 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation210() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 ^ 1 + 1";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation211() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 ^ 3 - 1";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation212() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 ^ 1 * 2";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation213() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 ^ 4 / 2";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation214() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 ^ 6 % 4";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation215() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 2 ** 0";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation216() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 ^ 1 & 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation217() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 5 == 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation218() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 5 != 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation219() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 2 < 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation220() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 5 > 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation221() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 2 <= 5";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation222() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 5 >= 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation223() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 | 1 << 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation224() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 2 >> 1";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation225() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 | 1 + 1";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation226() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 | 3 - 1";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation227() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 | 1 * 2";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation228() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 | 4 / 2";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation229() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "5 | 6 % 4";
		int expectedResult = 7;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation230() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 2 ** 0";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation231() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 | 2 & 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation232() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "2 | 1 ^ 2";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation233() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 | 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation234() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 ^ 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation235() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 2 & 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation236() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 2 == 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation237() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 2 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation238() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 3 < 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation239() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 3 > 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation240() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 3 <= 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation241() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 3 >= 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation242() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 << 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation243() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 2 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation244() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 + 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation245() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 - 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation246() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 1 * 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation247() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 4 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation248() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 4 % 2";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test //TODO: Precedence not tested!
	public final void calculation249() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 && 0 ** 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation250() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 | 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation251() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 ^ 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation252() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 2 & 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation253() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 == 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation254() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 2 != 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation255() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 2 < 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation256() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 3 > 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation257() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 2 <= 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation258() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 3 >= 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation259() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 << 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation260() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 2 >> 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation261() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 + 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation262() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 - 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation263() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 1 * 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation264() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 4 / 2";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test //TODO Precedence not tested!
	public final void calculation265() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 5 % 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test //TODO: Precedence not tested!
	public final void calculation266() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 0 ** 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void calculation267() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 || 0 && 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
}
