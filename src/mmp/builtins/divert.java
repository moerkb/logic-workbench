/** --------------------------------------------------------------------------
 * Implementation of macro 'divert'
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gieﬂen-Friedberg University of Applied Sciences.
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
 * $Id: divert.java 380 2008-07-14 03:53:48Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * divert( [divnum] )
 * </h3>
 * <h5>
 * divert output 
 * </h5>
 * Implemented by divert().
 * 
 * @author Burkhardt Renz
 */
public class divert extends Macro {
	
	private static Logger logger =  Logger.getLogger( divert.class.getName() );
	
	public divert() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size > 2 ) {
			throw new SyntaxErrorException( 1001,
					String.format("divert has %d arguments, expected  0 or 1", size-1) );
		}
		
		try{
			int divnum;
			if ( size == 1 || macArgs.get(1).isEmpty() ) {
				divnum = 0;
			} else {
				divnum = Integer.parseInt( macArgs.get(1) );
			}	
			engineContext.getOutput().divert( divnum );
			logger.fine( String.format("MMPTrace - Current divnum: %d", 
					engineContext.getOutput().getCurrentDivnum()) );
		} catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, e.getMessage() );
		}
	  return "";
  }
  
}
