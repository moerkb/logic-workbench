/** --------------------------------------------------------------------------
 * Test of mic printSyntaxTree
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
 * $Id: PrintSyntaxText.java 732 2008-12-16 09:49:53Z brenz $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import java.io.IOException;
import java.io.PrintWriter;

import mic.calc.Mic;
import mic.parser.ParseException;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Mathias Gutenbrunner
 *
 */
public class PrintSyntaxText {

  public Mic mic;
	
	@Before
	public final void before() {
		mic = new Mic();
	}
	
	@Test
	public final void syntax001() throws NumberFormatException, IllegalStateException, ParseException {
		
		String expression = "10-2+34>>5";
		try {
	    mic.printSyntaxTree( expression, new PrintWriter(System.out) );
    } catch ( IOException e ) {
	    e.printStackTrace();
    }
		
	}
	
	
}
