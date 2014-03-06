/** --------------------------------------------------------------------------
 * TODO: add purpose
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
 * $Id: LorTest.java 555 2008-08-27 13:35:11Z mgutenbrunner $
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
public class LorTest {
	
  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void lor001() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 || -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor002() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 || -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor003() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor004() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor005() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-3 || 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor006() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 || -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor007() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 || -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor008() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor009() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor010() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-1 || 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor011() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
		
	@Test
	public final void lor012() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor013() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor014() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor015() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor016() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor017() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor018() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor019() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor020() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor021() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 || -3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor022() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 || -1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor023() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor024() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor025() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "3 || 3";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor026() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor027() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 || 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor028() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "-0 || -0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
		
	}
	
	@Test
	public final void lor029() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || 0 || 0";
		int expectedResult = 0;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void lor030() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || 0 || 1";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void lor031() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "0 || 1 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
	@Test
	public final void lor032() throws NumberFormatException, IllegalStateException, ParseException{
		
		String expression = "1 || 0 || 0";
		int expectedResult = 1;
		int result = mic.calculate( expression );
		
		assertEquals( expectedResult, result );
	}
	
}
