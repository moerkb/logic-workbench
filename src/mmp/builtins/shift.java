/** --------------------------------------------------------------------------
 * Implementation of macro 'shift'
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
 * $Id: shift.java 356 2008-07-10 08:58:56Z brenz $
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
 * shift( arg1, arg2 ... ) <br/>
 * </h3>
 * <h5>
 * shift arguments
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * -
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * 'shift' expands to a comma separated list of the quoted arguments, except the first one. <br/>
 * 'shift' without an argument expands to 'shift'.
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * -
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym>.<br/>
 * <code>mmp</code> conforms with <acronym>GNU m4</acronym>.<br/>
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class shift extends Macro {
	
	private static Logger logger =  Logger.getLogger( shift.class.getName() );
	
	public shift() {
		super ( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	String begQuote = engineContext.getSettings().getBegQuote();
  	String endQuote = engineContext.getSettings().getEndQuote();
  	
		StringBuffer sb = new StringBuffer();
		for ( String arg : macArgs.subList(2, size) ) {
			sb.append( begQuote );
			sb.append( arg );
			sb.append( endQuote );
			sb.append( ',' );
		}
		if ( sb.length() > 0 ) {
			sb.deleteCharAt( sb.length()-1 );
		}
		
		String result = sb.toString();
  	if ( !result.isEmpty()) {
 			logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }
  
}
