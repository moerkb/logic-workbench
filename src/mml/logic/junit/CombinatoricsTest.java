/** --------------------------------------------------------------------------
 * Test of Combinatorics
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mml is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mml is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: CombinatoricsTest.java 486 2008-08-18 04:31:08Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.logic.junit;


import static mml.logic.Combinatorics.*;

import org.junit.Test;
/**
 * @author Burkhardt Renz
 *
 */
public class CombinatoricsTest {
	
	@Test
	public void someSubsets() {
		System.out.println( kSubsetsOfN(2,5) );
	}
		
	

}
