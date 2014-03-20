/** --------------------------------------------------------------------------
 * Testsuite for mic
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
 * $Id: MicTest.java 646 2008-09-21 12:21:58Z mgutenbrunner $
 * --------------------------------------------------------------------------
 */
package mic.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Testsuite for mic.<br/>
 * MicTest calls all test for mic.
 * 
 * @author Burkhardt Renz
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BandTest.class,
	BnegTest.class,
	BorTest.class,
	BxorTest.class,
	CalculationTest.class,
	DivTest.class,
	EqTest.class,
	GeTest.class,
	GtTest.class,
	HexadecimalNumbersTest.class,
	LandTest.class,
	LeTest.class,
	LnegTest.class,
	LorTest.class,
	LshiftTest.class,
	LtTest.class,
	MinusTest.class,
	ModTest.class,
	MulTest.class,
	NeTest.class,
	OctalNumbersTest.class,
	PlusTest.class,
	PowerTest.class,
	RshiftTest.class,
	SingleInstanceTest.class,
	SyntaxTest.class
})

public class MicTest {
}