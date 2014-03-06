/** --------------------------------------------------------------------------
 * Implementation of macro 'm4wrap'
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
 * $Id: m4wrap.java 356 2008-07-10 08:58:56Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * m4wrap( string )
 * </h3>
 * <h5>
 * postpone processing until the end of input
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * One argument
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * 'm4wrap' stores the 'string' and expansds it after reaching the end of
 * input. Several calls to m4wrap are processed in first-in first-out order. 
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * SyntaxErrorException [1001], if wrong number of arguments <br/>
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym>.<br/>
 * <acronym>GNU m4</acronym> allows several arguments and processes
 * multiple calls of m4wrap in last-in first-out order.
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class m4wrap extends Macro {
	
	private static Logger logger =  Logger.getLogger( m4wrap.class.getName() );
	
	public m4wrap() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
		
		StringBuffer sb = new StringBuffer();
		for ( String arg : macArgs.subList(1, size) ) {
			sb.append( arg );
			sb.append( ' ' );
		}
		if ( sb.length() > 0 ) {
			sb.deleteCharAt( sb.length()-1 );
		}
		String wrapString = sb.toString();
		
		engineContext.getInput().addWrapupSource( new StringReader(wrapString) );
		
	  return "";
  }

}
