/** --------------------------------------------------------------------------
 * Command line interface for MNI Integer Calculator mic.
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
 * $Id: mic.java 736 2008-12-16 11:10:30Z brenz $
 * --------------------------------------------------------------------------
 */

package mic.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mic.calc.Mic;

/**
 * Command line interface for MNI Integer Calculator mic.
 * 
 * @author Burkhardt Renz
 *
 */
// CHECKSTYLE:OFF -- mic intentionally lower case
public final class mic {
// CHECKSTYLE:ON	
	
	private static String rev = "mic - the MNI integer calculator, rev 1.0";
	private static String prompt = "mic %";
	private static String mode = "mic - interactive mode - enter q to quit";
	private static BufferedReader input = new BufferedReader( 
			new InputStreamReader( System.in ) );
	
	private mic() {
	}
	
	/**
	 * Starts mic with arguments or in interactive mod.
	 * 
	 * @param args terms to be calculated <br/> if empty, 
	 * mic starts in interactive mode.
	 */
	public static void main( String[] args ) {
		
		if ( args.length > 0 ) {
			runCommandLine( args );
		} else {
			runInteractiveMode();
		}
	}

  private static void runCommandLine( String[] args ) {
  	
  	StringBuffer sb = new StringBuffer();
  	for ( int i = 0; i < args.length; i++ ) {
  		sb.append( args[i] );
  		if ( i < args.length - 1 ) {
  			sb.append( " " );
  		}
  	}
		Mic evaluator = new Mic();
  	try {
	    System.out.println( evaluator.evaluate( sb.toString() ) );
    } catch ( Exception e ) {
	    System.out.println( "mic failed" );
	    System.out.println( e.getMessage() );
    }
  }
  
  private static void runInteractiveMode() {
  	
  	String command = null;
  	
  	System.out.println( rev );
  	System.out.println( mode );
  	System.out.print( prompt );
  	
  	try {
	    while ( ( command = readCommand() ) != null ) {
	    	String trimmed = command.trim();
	    	Mic evaluator = new Mic();
	    	if ( !trimmed.equals( "" ) ) {
	    		try {
	          System.out.println( evaluator.evaluate( trimmed ) );
          } catch ( Exception e ) {
          	System.out.println( "mic failed" );
          	System.out.println( e.getMessage() );
          }
	    	}
	    	System.out.print( prompt );
	    }
    } catch ( IOException e ) {
	    e.printStackTrace();
    }
  	System.out.println( "bye" );
  }

  private static String readCommand() throws IOException {
  	
  	String command;
  	
  	if ( input == null ) {
    	return null;
  	}
  	
  	command = input.readLine();
  	if ( command == null ) {
  		return null;
  	}
  	if ( command.equals( "q" ) ) {
  		return null;
  	}
  	return command;
  }

}
