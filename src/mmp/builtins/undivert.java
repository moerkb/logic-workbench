/** --------------------------------------------------------------------------
 * Implementation of macro 'undivert'
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
 * $Id: undivert.java 356 2008-07-10 08:58:56Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * undivert( [diversion1 [,diversion2 ...]] )
 * </h3>
 * <h5>
 * undivert diversions 
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * -
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * RuntimeErrorException [1003], if file can't be opened
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * TODO
 * <code>mmp</code> extends <acronym>POSIX 2004</acronym>: 'divnum' may be any integer.<br/>
 * <code>mmp</code> does not allow filenames as argument to 'divert' 
 * as <acronym>GNU m4</acronym>i does.<br/>
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class undivert extends Macro {
	
	private static Logger logger =  Logger.getLogger( undivert.class.getName() );
	
	public undivert() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size == 1 ) {
			engineContext.getOutput().undivert();
			return "";
		}
		
		if ( size == 2 && macArgs.get(1).isEmpty() ) {
			engineContext.getOutput().undivert(0);
			return "";
		}
		
		for ( String div: macArgs.subList(1, size) ) {
			try {
				// number?
				Integer divnum = Integer.parseInt( div );
				if ( divnum > 0 
							&&  divnum != engineContext.getOutput().getCurrentDivnum() ) {
					engineContext.getOutput().undivert( divnum );	
				}
			} catch ( NumberFormatException e) {
				// file?
					undivertFile( engineContext, div );
			}
		}
	  return "";
  }

  private void undivertFile( EngineContext engineContext, String filename ) 
  	throws RuntimeErrorException, IOException {

		File f;
		for ( String dir : engineContext.getSettings().getSearchPath() ) {
			f = new File( dir, filename );
			
			if ( f.isFile() && f.canRead() ) {
				FileReader fr;
        try {
	        fr = new FileReader(f);
        } catch ( FileNotFoundException e ) {
					throw new RuntimeErrorException( 1003,
						String.format("Can't open file '%s'", filename) );
        	
        }
				int c;
				while ( (c = fr.read()) != -1 ) {
					engineContext.getOutput().write( c );
				}
				return;
			}
		}	
		// not found
			throw new RuntimeErrorException( 1003,
					String.format("Can't open file '%s'", filename) );
  }
  
}
