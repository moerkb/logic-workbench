/** --------------------------------------------------------------------------
 * Implementation of macros 'include' and 'sinclude'
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
 * $Id: include.java 385 2008-07-15 03:56:52Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.File;
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
 * include( filename )
 * </h3>
 * <h5>
 * include file
 * </h5>
 * <br/>Implemented by include("include").
 * 
 * <h3>
 * sinclude( filename )
 * </h3>
 * <h5>
 * silently include file
 * </h5>
 * <br/>Implemented by include("sinclude").
 * 
 * @author Burkhardt Renz
 *
 */
public class include extends Macro {
	
	private static Logger logger =  Logger.getLogger( include.class.getName() );
	
	private final String mode;
	
	/**
	 * @pre mode == "include" or mode == "sinclude"
	 * @param mode
	 */
	public include( String mode ) {
		super( Style.NEEDS_PARENTHESIS, mode );
		this.mode = mode;
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException {
  	
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		String filename = macArgs.get(1);
		
		// try to open file
		File f;
		for ( String dir : engineContext.getSettings().getSearchPath() ) {
			f = new File( dir, filename );
			
			if ( f.isFile() && f.canRead() ) {
				engineContext.getInput().pushInputSource( new FileReader(f) );
				return "";
			}
		}
		// not found
		if ( mode.equals("include") ) {
			throw new RuntimeErrorException( 1003,
					String.format("Can't open file '%s'", filename) );
		}
	  return "";
  }
}

