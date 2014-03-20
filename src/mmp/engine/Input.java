/** --------------------------------------------------------------------------
 * Management of input sources for mmp
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
 * $Id: Input.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
//import java.util.logging.Logger;

/**
 * Input is responsible for the management of the input sources of mmp.
 * <br/><br/>
 * During the processing of input, the input can be altered by
 * the inclusion of files to be processed by the macro 'include' and
 * by macro expansions which are inserted in front of the remaining
 * input.
 * <br/><br/>
 * Input is organized as a stack of readers for input, the 'inputStack'.
 * <br/> 
 * Input furthermore has a queue of readers, called the 'wrapupQueue',  
 * for parts of the input that are postponed for processing at the end 
 * of input.
 * 
 * @author Burkhardt Renz
 *
 */
public class Input {
	
	//private static Logger logger = Logger.getLogger( Input.class.getName() );
	
	private Deque<PushbackReader> inputStack = new ArrayDeque<PushbackReader>();
	// input is a stack (deque used as a stack)
	
	private Deque<PushbackReader> wrapupQueue = new ArrayDeque<PushbackReader>();
	// wrapup is according to POSIX 2004 a queue (deque used as a queue)
	
	private PushbackReader currentReader = null;
	
	/**
	 * Constructor, that initializes the Input with a Reader source.
	 * <br/>
	 * @param source where to read input.
	 */
	public Input( Reader source ) {
		pushInputSource( source );
	}
	
	/**
	 * Pushes the reader 'source' on the inputStack.
	 * 
	 * @param source reader to be pushed on the inputStack.
	 * @modifies this
	 */
	public final void pushInputSource( Reader source ) {	
		if ( !(source instanceof PushbackReader) ) {
			inputStack.push(  new PushbackReader(source) );
		} else {
			inputStack.push( (PushbackReader)source );
		}
		currentReader = inputStack.peek();
	}
	
	/**
	 * Adds the reader 'source' to the wrapupQueue, to be processed
	 * at the end of input.
	 * 
	 * @param source reader to be queued.
	 * @modifies this
	 */
	public final void addWrapupSource( Reader source ) {
		if ( !(source instanceof PushbackReader) ) {
			wrapupQueue.add(  new PushbackReader(source) );
		} else {
			wrapupQueue.add( (PushbackReader)source );
		}
	}
	
	/**
	 * Reads a single character from Input.
	 * 
	 * @return the char found (as an integer like Reader::read()),<br/>
	 *  -1 if the end of input is reached.
	 *  
	 * @throws IOException if io error
	 * @modifies this
	 */
	public final int readChar() throws IOException {
		if ( inputStack.isEmpty() ) {
			return -1;
		}
		int ch = currentReader.read();
		if ( ch == -1 ) {
			// end of current input stream
			inputStack.pop();
			if ( !inputStack.isEmpty() ) {
				currentReader = inputStack.peek();
				return readChar();
			} else if ( !wrapupQueue.isEmpty() ) {
				inputStack.push(  wrapupQueue.remove() );
				currentReader = inputStack.peek();
				return readChar();
			} else {
				return -1;
			}
		}	
		return ch;
	}
	
	/**
	 * Lookahead of one character in the Input.
	 * 
	 * @return the char found (as an integer like Reader::read()),<br/>
	 *  -1 if the end of input is reached.
	 * @throws IOException if io error
	 * @modifies this; more precise: <br/>
	 * The character is pushed back to input, but in case the read 
	 * pops a reader from input stack, the input is modified. <br>
	 * If the complete input is exhausted, i.e. the method returns -1, 
	 * the -1 is not pushed back.
	 */
	public final int peekChar() throws IOException {
		int ch = readChar();
		if ( ch != -1 ) {
			currentReader.unread( ch );
			// if currentReader is unchanged, unread follows read
			// if currentReader has changed, unread is the first action on the
			// reader, i.e. the reader's buffer is empty.
		}
		return ch;
	}

	/**
   * The method checks whether the input matches string.<br/>
   * If the input matches the string it is read from input.
   * 
   * @pre string != null
   * @param string to be compared with the input
   * @return true iff the string matches the beginning of the input
	 * @throws IOException if io error
	 * @modifies this
   */
  public final boolean matches( String string ) throws IOException {
  	
  	// the answer is trivially true if the string is empty
  	if ( string.length() == 0 ) { return true; }
  	
  	// first handle case that the string has just one character
  	if ( string.length() == 1 ) {
  		int next = peekChar();
  		if ( (char)next == string.charAt(0) ) {
  			readChar();
  			return true;
  		}
  		return false;
  	}
  	
  	// now the case that the string has more characters
  	StringBuffer sb = new StringBuffer();
  	boolean result = true;
  	for ( int i = 0; i < string.length(); i++ ) {
  		int next = peekChar();
  		if ( next == -1 ) {
  			result = false;
  			break;
  		}
  		if ( (char)next == string.charAt(i) ) {
  			readChar();
  			sb.append( (char)next );
  		} else {
  			result = false;
  			break;
  		}
  	}
  	if ( !result ) {
  		this.pushInputSource( new StringReader( sb.toString() ));
  	}
  	return result;
  }
}
