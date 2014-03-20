/** --------------------------------------------------------------------------
 * Implementation of macro 'dumpdef'
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
 * $Id: dumpdef.java 364 2008-07-11 04:15:10Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * dumpdef( [name1 [,name2 ...]] )
 * </h3>
 * <h5>
 * dump definitions
 * </h5>
 * <br/>Implemented by dumpdef().
 * 
 * @author Burkhardt Renz
 */
public class dumpdef extends Macro {
	
	private static Logger logger =  Logger.getLogger( dumpdef.class.getName() );
	
	private Logger dumper = Logger.getAnonymousLogger();
	
	public dumpdef() {
		super( Style.DOES_NOT_NEED_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		// initialize dumper
  	Handler[] handlers = dumper.getHandlers();
	  for ( int i = 0; i < handlers.length; i++ ) {
	  	dumper.removeHandler( handlers[i] );
	  }
		dumper.addHandler( engineContext.getSettings().getTraceHandler() );
		dumper.setUseParentHandlers( false );
		
		SortedSet<String> names = new TreeSet<String>();;
		if ( size == 1  ) {
			names.addAll( engineContext.getMacroRegistry().getMacroNames() );
		} else {
			names.addAll( macArgs.subList(1,size) );
		}
		
		dumper.setLevel( Level.INFO );
		for ( String name: names ) {
			Macro macro = engineContext.getMacroRegistry().getMacro( name );
			if ( macro != null ) {
				dumper.info( String.format("MMPDumpdef - %12s: %s", 
							name, macro.getInstInfo().toString()));
			} else {
				dumper.info( String.format("MMPDumpdef - %12s: -- not defined --",  name));
			}
		}
		dumper.setLevel( Level.OFF );
		
	  return "";
  }

}
