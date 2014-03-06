/** --------------------------------------------------------------------------
 * Implementation of builtins 'incr' and 'decr'
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
 * $Id: incr.java 381 2008-07-14 04:16:03Z brenz $
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
 * incr( integer )
 * </h3>
 * <h5>
 * increment of integer
 * </h5>
 * Implemented by incr( "incr" ).<br/>
 * 
 * <h3>
 * decr( integer )
 * </h3>
 * <h5>
 * decrement of integer
 * </h5>
 * Implemented by incr( "decr" ).<br/>
 * 
 * @author Burkhardt Renz
 */
public class incr extends Macro {
	
	private static Logger logger =  Logger.getLogger( incr.class.getName() );
	
	private String mode;
	
	/**
	 * @pre mode == "incr" or mode == "decr"
	 * @param mode
	 */
	public incr( String mode ) {
		super( Style.NEEDS_PARENTHESIS, mode );
		this.mode = mode;
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size();
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size != 2 ) {
			throw new SyntaxErrorException( 1001,
					String.format("%s has %d arguments, expected 1", macroName, size-1) );
		}
		String result = "";
		try {
			int number = Integer.parseInt( macArgs.get(1) );
			result = mode.equals("incr") ? 
					String.format("%d", number+1) : String.format("%d", number-1);;
		} catch ( NumberFormatException e )	{
			throw new SyntaxErrorException ( 1101,
					String.format("could not parse '%s' to an integer", macArgs.get(1)) );
		}
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		
		return result;
  }
  
}  
