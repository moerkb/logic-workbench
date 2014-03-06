/** --------------------------------------------------------------------------
 * Implementation of variant dependencies (vd) macros
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gieﬂen-Friedberg University of Applied Sciences.
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
 * $Id: vd.java 498 2008-08-20 09:42:13Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.varmod;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * _man( vp, v1, ..., vn )
 * </h3>
 * <h5>
 * Generates conditions for mandatory variants
 * </h5>
 * <br/>Implemented by vd( "man" ).
 * 
 * <h3>
 * _opt( vp, v1, ..., vn )
 * </h3>
 * <h5>
 * Generates conditions for optional variants
 * </h5>
 * <br/>Implemented by vd( "opt" ).
 * 
 * <h3>
 * _alt( vp, [min, max], v1, ..., vn )
 * </h3>
 * <h5>
 * Generates conditions for alternative choice of variants
 * </h5>
 * <br/>Implemented by vd( "alt" ).
 * 
 * @author Burkhardt Renz
 */
public class vd extends Macro {
	
 	enum Mode {
  	MAN,
  	OPT,
  	ALT,
  }
 	
 	private static Logger logger = 
		Logger.getLogger( vd.class.getName() );
 	
 	private Mode mode = Mode.MAN; 	

	public vd( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
		// default Mode.MAN
		if ( mode.equals("opt") ) {
			this.mode = Mode.OPT;
		}
		else if ( mode.equals("alt") ) {
			this.mode = Mode.ALT;
		}
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	if ( size < 3 ) {
			throw new SyntaxErrorException( 1001,
					String.format("'%s' has %d arguments, expected >= 2", macroName, size-1) );
  	}	
			
  	StringBuffer sb = new StringBuffer();
  	String vp = macArgs.get( 1 );
  	
  	switch ( mode ) {
  	case MAN:
  			transformMan( sb, vp, macArgs.subList(2, size) );
  		break;
  	case OPT:
  			transformOpt( sb, vp, macArgs.subList(2, size) );
  		break;
  	case ALT:
  			transformAlt( sb, vp, macArgs.subList(2, size) );
  		break;
  	}
		String result = sb.toString();
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }

	private void transformOpt( StringBuffer sb, String vp, List<String> vs ) {
		// we generate a proposition that's trivially true
		sb.append( String.format("(%s->%s)", vp, vp) );
		// register variants with variantPoint
		registerVariants( vp, vs );
  }


	private void transformAlt( StringBuffer sb, String vp, List<String> vs ) throws SyntaxErrorException {
		int min = 1;
		int max = 1;
		boolean def = false;
		// are the first two arguments integers?
		try {  
			min = Integer.parseInt( vs.get(0) );
		} catch ( NumberFormatException e ) {
			// use default
			def = true;
		}
		if ( !def && vs.size() < 2 ) {
			throw new SyntaxErrorException( 1001, "Missing max in alt expression" );
		}	
		if ( !def ) {
			try {
				max = Integer.parseInt( vs.get(1) );
			} catch ( NumberFormatException e) {
				throw new SyntaxErrorException( 1001, "Missing max in alt expression" );
			}	
		}	
		if (!def) {
			vs = vs.subList( 2, vs.size() );
		}
		
		StringBuffer sbt = new StringBuffer();
		int i = 0;
		for ( String v: vs ) {
			i++;
			sbt.append( v );
			if ( i < vs.size() ) {
				sbt.append( "," );
			}	
		}	
		String vsList = sbt.toString();
		
		
		sb.append( "(" + vp + "->(");
		if ( min > 0 ) {
			sb.append( "minKOf(" + min + "," + vsList + ")" );
		}	
		if ( min > 0 && max < vs.size() ){
			sb.append( "&\n" );
			
		}
		if ( max < vs.size() ) {
			sb.append( "maxKOf(" + max + "," + vsList + ")" );
		}
			
		sb.append( "))" );
		
		// register variants with variantPoint
		registerVariants( vp, vs );
	}
		
		
	private void transformMan( StringBuffer sb, String vp, List<String> vs ) {
		sb.append( "(" );
		int i = 0;
		int size = vs.size();
		
		for ( String v: vs ) {
			i++;
			sb.append( "(" + vp + "->" + v + ")" );
			if ( i < size ) {
				sb.append( " & " );
			}
		}
		sb.append( ")" );
		// register variants with variantPoint
		registerVariants( vp, vs );
  }

	private void registerVariants( String vp, List<String> vs ) {
		for ( String v: vs ) {
			vm.register( v, vp );
		}
	}
}
