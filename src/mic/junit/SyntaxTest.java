/** --------------------------------------------------------------------------
 * Test of mic syntax
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
 * $Id: SyntaxTest.java 629 2008-09-17 11:58:13Z mgutenbrunner $
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
public class SyntaxTest {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void syntax001() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10-2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax002() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10- 2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax003() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 -2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax004() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10  -2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax005() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10-  2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax006() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = " 10 - 2";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax007() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 - 2 ";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax008() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10-2  ";
		int expectedResult = 8;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax009() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10+2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax010() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10+ 2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax011() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 +2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax012() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10  +2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax013() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10+  2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax014() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = " 10 + 2";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax015() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10 + 2 ";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax016() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10+2  ";
		int expectedResult = 12;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax017() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 +";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax018() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 -";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax019() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 *";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax020() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 /";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax021() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 **";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax022() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "* 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax023() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "/ 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax024() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "** 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax025() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 * * 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax026() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 ++ 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax027() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 -- 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax028() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 / / 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax029() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 ++";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax030() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 --";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax031() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "++ 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax032() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "-- 3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax033() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3)";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax034() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 )";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax035() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax036() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "( 3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void syntax037() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3)";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax038() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "(3 )";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax039() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "( 3)";
		int expectedResult = 3;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax040() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "3 3";
		mic.calculate( expression );
		
	}
	
	@Test
	public final void syntax041() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "+ + 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax042() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "- + 1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax043() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "+ - 1";
		int expectedResult = -1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax044() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "- - 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax045() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + + 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	
	@Test
	public final void syntax046() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + - 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax047() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - + 1";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void syntax048() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - - 1";
		int expectedResult = 2;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax049() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - * 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax050() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - / 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax051() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - % 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax052() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - ** 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax053() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - << 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax054() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - >> 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax055() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - < 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax056() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - > 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax057() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - <= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax058() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - >= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax059() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - == 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax060() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - != 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax061() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - & 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax062() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - ^ 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax063() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - | 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax064() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - && 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax065() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 - || 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax066() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + * 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax067() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + / 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax068() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + % 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax069() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + ** 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax070() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + << 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax071() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + >> 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax072() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + < 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax073() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + > 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax074() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + <= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax075() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + >= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax076() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + == 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax077() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + != 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax078() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + & 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax079() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + ^ 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax080() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + | 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax081() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + && 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax082() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "1 + || 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax083() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "* 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax084() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "/ 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax085() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "% 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax086() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "** 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax087() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "<< 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax088() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = ">> 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax089() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "< 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax090() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "> 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax091() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "<= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax092() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = ">= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax093() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "== 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax094() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "!= 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax095() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "& 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax096() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "^ 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax097() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "| 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax098() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "&& 1";
		mic.calculate( expression );
		
	}
	
	@Test (expected = mic.parser.ParseException.class)
	public final void syntax099() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "|| 1";
		mic.calculate( expression );
		
	}
	
}
