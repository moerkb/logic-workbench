/** --------------------------------------------------------------------------
 * Combinatorics -- needed for Boolean cardinality constraints
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
 * $Id: Combinatorics.java 486 2008-08-18 04:31:08Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * Combinatorics provides static methods for doing combinatorics with sets.
 * 
 * @author Burkhardt Renz
 *
 */
public class Combinatorics {
	
	/**
	 * Builds all subsets of size k of the integers {1, 2, ..., n}
	 * @pre n >= 1 && 1 <= k <= n
	 * @param k size of subsets
	 * @param n size of set
	 * @return 
	 * @return subsets
	 */
	public static Set<Set<Integer>> kSubsetsOfN( int k, int n ) {
		// start with the subset {1, 2, ..., k}
		int workSet[] = new int[k];
		fillAscFromPos( workSet, 0 );
		Set<Integer> subset = createSubsetFromArray( workSet );
		Set<Set<Integer>> result = new HashSet<Set<Integer>>();
		result.add( subset );
		// loop calculating the subsets
		while ( calcNextSubset(workSet, n) ) {
			subset = createSubsetFromArray( workSet );
			result.add( subset );
		}
		return result;
	}

	private static boolean calcNextSubset( int[] workSet, int n ) {
		int idx = workSet.length-1;
		int max = n;
		// check from last to first
		for ( int i = idx; i >= 0; i-- ) {
			if ( workSet[i] < max ) {
				fillAscFromPos( workSet, i );
				return true;
			}
			max--;
		}
	  return false;
  }

	private static Set<Integer> createSubsetFromArray( int[] workSet ) {
		int len = workSet.length;
		Set<Integer> result = new HashSet<Integer>( len );
		for ( int i = 0; i < len; i++ ) {
			result.add( workSet[i] );
		}
	  return result;
  }

	private static void fillAscFromPos( int[] workSet, int pos) {
		// value at pos
		int val = workSet[pos] + 1;
		// fill
		for ( int i = pos; i < workSet.length; i++ ) {
			workSet[i] = val++;
		}
  }

}
