/** --------------------------------------------------------------------------
 * Implementation of builtin 'changecom'
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
 * $Id: changecom.java 359 2008-07-11 02:25:14Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;
import mmp.util.CharType;

/**
 * <h3>
 * changecom( [start] [, end] )
 * </h3>
 * <h5>
 * Change comment delimiters.
 * </h5>
 * <br/>Implemented by changecom().
 * 
 * @author Burkhardt Renz
 */
public class changecom extends Macro {
	
	private static Logger logger = 
		Logger.getLogger( changecom.class.getName() );
	
	public changecom() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size == 1  || macArgs.get(1).isEmpty()) {
			engineContext.getSettings().changeComment( "", "" );
			return "";
		}
		String newBegComment = macArgs.get( 1 );
		String newEndComment = (size > 2) ? macArgs.get(2) : "\n";
		if ( newEndComment.isEmpty() ) {
			newEndComment = "\n";
		}
		
		// check precondition on delimiters
		if ( !CharType.isDelimiter(newBegComment) || !CharType.isDelimiter(newEndComment) ) {
			throw new SyntaxErrorException( 1003,
					String.format("delimiter of '%s' must not begin with a letter, a digit, " +
							"'_', '(', ',', ')'" , macroName) );
		}
		engineContext.getSettings().changeComment( newBegComment, newEndComment );
		
		return "";
  }

}
