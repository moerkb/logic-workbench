/** --------------------------------------------------------------------------
 * Implementation of macro 'defn'
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
 * $Id: defn.java 359 2008-07-11 02:25:14Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;
import mmp.engine.UserMacro;

/**
 * <h3>
 * defn( name1 [, name2 ...] )
 * </h3>
 * <h5>
 * quoted expansion of macros 'name1', 'name2' ...
 * </h5>
 * <br/>Implemented by defn().
 * 
 * @author Burkhardt Renz
 */
public class defn extends Macro {
	
	private static Logger logger =  Logger.getLogger( defn.class.getName() );
	
	public defn() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
  	String begQuote = engineContext.getSettings().getBegQuote();
  	String endQuote = engineContext.getSettings().getEndQuote();
  	
		int size = macArgs.size();
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
		
		StringBuffer sb = new StringBuffer();
		for ( String arg : macArgs.subList(1, size) ) {
			Macro macro = engineContext.getMacroRegistry().getMacro( arg );
			if ( macro == null ) {
				continue;
			}
			if ( !(macro  instanceof UserMacro) ) {
				throw new SyntaxErrorException( 1106, 
						String.format("invalid argument '%s' for 'defn'", arg) );
			}
			sb.append( begQuote );
			sb.append( ((UserMacro)macro).getExpansion() );
			sb.append( endQuote );
		}
		
 		logger.fine( String.format( MMPTRACE_EXP, macroName, sb.toString()) );
 		
		return sb.toString();
  }

}
