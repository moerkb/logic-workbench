/** --------------------------------------------------------------------------
 * Implementation of variant model (vm) macros
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mml is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mml is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: vm.java 499 2008-08-20 09:44:24Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.varmod;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * vm( exp1, ..., expn )
 * </h3>
 * <h5>
 * Generates expressions framed by _vm_begin() and _vm_end().
 * </h5>
 * <br/>Implemented by vm( "vm" ).
 * 
 * <h3>
 * _vm_begin()
 * </h3>
 * <h5>
 * Initializes static data structure for dependencies in vm.
 * </h5>
 * <br/>Implemented by vd( "beg" ).
 * 
 * <h3>
 * _vm_end()()
 * </h3>
 * <h5>
 * Generates conditions for dependencies of variants.
 * </h5>
 * <br/>Implemented by vd( "end" ).
 * 
 * @author Burkhardt Renz
 */
public class vm extends Macro {
	
 	enum Mode {
  	VM,
  	BEG,
  	END,
  }
 	
 	
 	private static Logger logger = 
		Logger.getLogger( vm.class.getName() );
 	
 	private Mode mode = Mode.VM; 	

	public vm( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
		// default Mode.MAN
		if ( mode.equals("beg") ) {
			this.mode = Mode.BEG;
		}
		else if ( mode.equals("end") ) {
			this.mode = Mode.END;
		}
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	String result = "";
  	switch ( mode ) {
  		case VM:
  			if ( size < 2 ) {
					throw new SyntaxErrorException( 1001,
							String.format("'%s' has %d arguments, expected >= 1", macroName, size-1) );
  			}	
  			StringBuffer sb = new StringBuffer();
  			sb.append( "_vm_beg()");
  			for ( String expr: macArgs.subList(1, size) ) {
  				sb.append(expr);
  			}
  			sb.append( "_vm_end()"); 
  			result = sb.toString();
  			break;
  		case BEG:
  			// initialize static vdRegister
  			vdRegister.clear();
  			break;
  		case END:
  			// generate conditions from vdRegister
  			result = generateVdConditions( vdRegister );
  			break;
  		
  	}
			
  	if ( !result.isEmpty() ) {
  		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
  	}	
		return result;
  }
	
	
 	private String generateVdConditions( Map<String, HashSet<String>> vdReg ) {
 		StringBuffer sb = new StringBuffer();
 		sb.append( "(" );
 		int i = 0;
 		int isize = vdReg.keySet().size();
 		for ( String v : vdReg.keySet() ) {
 			i++;
 			sb.append( "(" + v + "->(" );
 			int j = 0;
 			HashSet<String> vps = vdReg.get( v );
 			int jsize = vps.size();
 			for ( String vp : vps ) {
 				j++;
 				sb.append( vp );
 				if ( j < jsize ) {
 					sb.append( "|" );
 				}
 			}
 			sb.append( "))" );
 			if ( i < isize ) {
 				sb.append( "&" );
 			}
 		}
 		sb.append( ")" );
		return sb.toString();
	}


	static Map<String, HashSet<String>> vdRegister = new HashMap<String,HashSet<String>>();

 	public static void register( String variant, String variantPoint ) {
 		if ( vdRegister.containsKey(variant) ){
 			// variant already registered
 			HashSet<String> vps = vdRegister.get(variant);
 			vps.add( variantPoint );
 		} else {
 			// new variant
 			HashSet<String> vps = new HashSet<String>();
 			vps.add( variantPoint );
 			vdRegister.put( variant, vps );
 		}
 	}
 	
}
