/** --------------------------------------------------------------------------
 * Tokenizer for expansions
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mmp is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mmp is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: ExpTokenizer.java 345 2008-07-07 03:57:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.util.Iterator;
import java.util.NoSuchElementException;

import mmp.builtins.ExpToken.Type;
import mmp.util.CharType;

/**
 * Tokenizer for expansions of user defined macros.
 * 
 * @author Burkhardt Renz
 */
public class ExpTokenizer implements Iterator<ExpToken>{

	private String input;
	private int index = 0;
	private int len;

	public ExpTokenizer( String expansion ) {
		input = expansion;
		len = input.length();
	}
	
	@Override
  public boolean hasNext() {
		return  index < len ;
  }

	@Override
  public ExpToken next() {
		if ( !(index < len) ) {
			throw new NoSuchElementException( "ExpTokenizer has no more elements" );
		}	
		
		ExpToken rc;
		// search for '$'
		int found = input.indexOf( '$', index );
		if ( found == -1 ) {
			// no more '$'
			rc = new ExpToken( Type.TEXT, input.substring(index) );
			index = len;
			return rc;
		}
		if ( found > index ) {
			rc = new ExpToken( Type.TEXT, input.substring(index, found) );
			index = found;
			return rc;
		}
		// '$' at index
		if ( found == len -1 ) {
			index = len;
			return new ExpToken( Type.TEXT, "$" );
		}
		char ch = input.charAt( found+1 );
		switch ( ch ) {
			case '#':
				index = found+2; 
				return new ExpToken( Type.ARGC, "#" );
			case '*':	
				index = found+2; 
				return new ExpToken( Type.ARGS, "*" );
			case '@':	
				index = found+2; 
				return new ExpToken( Type.QARGS, "@" );
		}
		if ( CharType.isDigit(ch) ) {
			int i;
			if ( found+1 < len-1 ) {
    		for	( i = found+2; i < len && CharType.isDigit(input.charAt(i)); i++ );
				index = i;
				return new ExpToken( Type.PARAM, input.substring(found+1, i) );
			}	else {
				index = len;
				return new ExpToken( Type.PARAM, input.substring(found+1) );
			}
		}	
		index = found+1;
		return new ExpToken( Type.TEXT, "$" );
  }

	@Override
  public void remove() {
		throw new UnsupportedOperationException();
  }

}
