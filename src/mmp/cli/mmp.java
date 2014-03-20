/** --------------------------------------------------------------------------
 * mmp's command line interface
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
 * $Id: mmp.java 742 2008-12-17 11:44:59Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.cli;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import mmp.engine.Engine;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;


/**
 * mmp's command line interface.
 * <br/>
 * This class provides a spartane command line interface for mmp.<br/><br/>
 * It's not an objective of mmp to provide a sophisticated  
 * command line interface like <acronym>GNU</acronym> m4.
 * The primary use case for mmp is the embedding of mmp in a Java
 * application. The command line interface is just a helper to
 * test mmp and to try out m4 files.
 * 
 * @author Burkhardt Renz
 */
public final class mmp {
	
	private static Logger logger =  Logger.getLogger( mmp.class.getName() );
	private static final String rev = "1.0";

  private mmp() {
  }

	/**
	 * usage: mmp &lt;filename>.
	 * 
	 * @param args the name of the file to be processed by mmp
	 * @throws IOException if io error
	 * @throws SyntaxErrorException  if syntax error in m4 file
	 * @throws RuntimeErrorException if runtime error
	 */
	public static void main( String[] args ) 
		throws IOException, SyntaxErrorException, RuntimeErrorException {
		if ( args.length != 1 ) {
			usage();
			System.exit( -1 );
		}
		logger.info( String.format( 
				"Invoking mmp to process file %s", args[0] ) );
		
		try {
			Engine engine = new Engine( new FileReader( args[0] ), 
					new OutputStreamWriter( System.out ) );
			engine.run();
    } catch ( FileNotFoundException e ) {
	    e.getMessage();
    }
	}

	private static void usage() {
		System.out.println( String.format( 
				"mmp - the MNI Macro Processor Revision %s", rev ) );
		System.out.println( "usage: mmp <filename>" );
  }

}
