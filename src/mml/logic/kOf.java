/** --------------------------------------------------------------------------
 * Implementation of macros for boolean constraints 'kOf'
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
 * $Id: kOf.java 487 2008-08-18 05:43:37Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.logic;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

//import org.junit.Test;
//import java.util.Arrays;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * kOf( no, atom1, ..., atomn )
 * </h3>
 * <h5>
 * Exactly no of the atoms are true.
 * </h5>
 * <br/>Implemented by kOf( "exactly" ).
 * 
 * <h3>
 * maxKOf( no, atom1, ..., atomn )
 * </h3>
 * <h5>
 * At most no of the atoms are true.
 * </h5>
 * <br/>Implemented by kOf( "max" ).
 * 
 * <h3>
 * minKOf( no, atom1, ..., atomn )
 * </h3>
 * <h5>
 * At least no of the atoms are true.
 * </h5>
 * <br/>Implemented by kOf( "min" ).
 * 
 * @author Burkhardt Renz
 */
public class kOf extends Macro {
	
  enum Mode {
  	EXACTLY,
  	MAX,
  	MIN
  }

	private static Logger logger = 
		Logger.getLogger( kOf.class.getName() );
	
	private Mode mode = Mode.EXACTLY;
	
	public kOf() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	public kOf( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
		if ( mode.equals("max") ) {
			this.mode = Mode.MAX;
		}
		if ( mode.equals("min") ) {
			this.mode = Mode.MIN;
		}
		// default Mode.EXACTLY
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	// expect at least two argument
  	if ( size < 3  ) {
			throw new SyntaxErrorException( 1001,
					String.format("kOf has %d arguments, expected at least 2", size-1) );
  	}
  	
  	// get the number
  	int k;
  	try {
	  	k = Integer.parseInt( macArgs.get(1) );
  	}
  	catch( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, e.getMessage() );
  	}
  	
  	// precondition 0 <= k <= n
  	int n = size -2;
  	if ( k < 0 || k > n ) {
			throw new SyntaxErrorException( 1003,
					String.format("1 <= %d <= %d not fulfilled", k, n) );
  	}
  	
  	String result;
  	switch (mode) {
  		case MAX:
  			if ( k == n ) {
  				result = ""; // trivially true
  			} else {
  				result = maxKOf( k, macArgs.subList(2, size) );
  			}
  			break;
  		case MIN:
  			if ( k == 0 ) {
  				result = ""; // trivially true
  			} else {
	  			result = minKOf( k, macArgs.subList(2, size) );
  			}
  			break;
  		default: // EXACTLY
  			if ( k == n ) {
	  			result = minKOf( k, macArgs.subList(2, size) );
  			} else if ( k == 0 ) {
  				result = maxKOf( k, macArgs.subList(2, size) );
  			} else {
	  			result = "(" + maxKOf( k, macArgs.subList(2, size) ) + " &\n" 
 		 										+ minKOf( k, macArgs.subList(2, size) ) + ")";
  			}
  			break;
  	}
  	
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }

	private String minKOf( int k, List<String> atoms ) {
		int n = atoms.size(); 
		// calculate (n-k+1)-sized subsets from n integers
		Set<Set<Integer>> subsets = Combinatorics.kSubsetsOfN( n-k+1, n );
		// create formula
		StringBuffer sb = new StringBuffer();		
		sb.append( "(" );
		for ( Set<Integer> subset: subsets ) {
			sb.append( "(");
			for ( int i: subset ) {
				sb.append( atoms.get(i-1) + "|" );
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append( ")&");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append( ")" );
	  return sb.toString();
  }

	private String maxKOf( int k, List<String> atoms ) {
		int n = atoms.size(); 
		// calculate (k+1)-sized subsets from n integers
		Set<Set<Integer>> subsets = Combinatorics.kSubsetsOfN( k+1, n );
		// create formula
		StringBuffer sb = new StringBuffer();		
		sb.append( "(" );
		for ( Set<Integer> subset: subsets ) {
			sb.append( "(");
			for ( int i: subset ) {
				sb.append( "!" + atoms.get(i-1) + "|" );
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append( ")&");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append( ")" );
	  return sb.toString();
  }
	
	/*
	@Test
	public void maxKOfTest() {
		List<String> atoms =  Arrays.asList("x1", "x2", "x3"); //, "x4", "x5");
		System.out.println( maxKOf(2,atoms) );
		System.out.println( minKOf(3,atoms) );
	}
	*/

}
