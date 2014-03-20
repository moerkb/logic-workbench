/** --------------------------------------------------------------------------
 * Implementation of macro 'errprint'
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
 * $Id: errprint.java 368 2008-07-11 05:00:33Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * errprint( message[, more ...] )
 * </h3>
 * <h5>
 * prints message and more to the current trace handler.
 * </h5>
 * <br/>Implemented by errprint().
 * 
 * @author Burkhardt Renz
 */
public class errprint extends Macro {
	
	private static Logger logger =  Logger.getLogger( errprint.class.getName() );
	
	private Logger errPrinter = Logger.getAnonymousLogger();
	
	public errprint() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		StringBuffer sb = new StringBuffer();
		for ( String msg : macArgs.subList(1, size) ) {
			sb.append(  msg );
			sb.append( " " );
		}
		sb.deleteCharAt( sb.length()-1 );
		
		// initialize errPrinter
  	Handler[] handlers = errPrinter.getHandlers();
	  for ( int i = 0; i < handlers.length; i++ ) {
	  	errPrinter.removeHandler( handlers[i] );
	  }
		errPrinter.addHandler( engineContext.getSettings().getTraceHandler() );
		errPrinter.setUseParentHandlers( false );
		
		errPrinter.setLevel( Level.INFO );
		errPrinter.info( sb.toString() );
		errPrinter.setLevel( Level.OFF );
		
		return "";
  }
  
}
