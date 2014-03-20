/** --------------------------------------------------------------------------
 * Implementation of macro 'traceon' and 'traceoff'
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
 * $Id: trace.java 358 2008-07-10 10:12:06Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.M4ExitException;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * traceon
 * </h3>
 * <h5>
 * turn tracing on
 * </h5>
 * 
 * <h3>
 * traceoff
 * </h3>
 * <h5>
 * turn tracing off
 * </h5>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * requires: <br/> 
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * effects: <br>
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * throws: <br/>
 * TODO
 * </p>
 * 
 * <p style='margin-left:1em;text-indent:-1em'>
 * compatibility:<br/>
 * TODO
 * <code>mmp</code> conforms with <acronym>POSIX 2004</acronym>.<br/>
 * <code>mmp</code> doesn't allow built-ins as arguments to 'defn', 
 * whereas <acronym>GNU m4</acronym> does.<br/>
 * </p>
 * 
 * @author Burkhardt Renz
 *
 */
public class trace extends Macro {
	
	private static Logger logger =  Logger.getLogger( trace.class.getName() );
	
	private String mode;
	
	/**
	 * @pre mode == "traceon" or mode == "traceoff"
	 * @param mode
	 */
	public trace( String mode ) {
		super( Style.DOES_NOT_NEED_PARENTHESIS, mode );
		this.mode = mode;
	}
	
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, M4ExitException {
  	
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		Logger tracer = Logger.getLogger( mmp.engine.UserMacro.class.getName() );
		if ( mode.equals("traceon") ) {
			tracer.setLevel( Level.INFO );
		} else {
			tracer.setLevel( Level.OFF );
			
		}
		return "";	
  }

}
