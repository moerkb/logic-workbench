/** --------------------------------------------------------------------------
 * Implementation of loop macros
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
 * $Id: loops.java 483 2008-08-14 06:29:24Z brenz $
 * --------------------------------------------------------------------------
 */
package mml.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * for( var, lower, upper, expr )
 * </h3>
 * <h5>
 * Apply macro var for lower to upper to expr.
 * </h5>
 * <br/>Implemented by vp( "for" ).
 * 
 * <h3>
 * for_sl( var, lower, upper, expr, expr_l )
 * </h3>
 * <h5>
 * Apply macro var for lower to upper-1 to expr, but expr_l to the last.
 * </h5>
 * <br/>Implemented by vp( "for_sl" ).
 * 
 * <h3>
 * forstep( var, bound1, bound2, step, expr )
 * </h3>
 * <h5>
 * Apply macro var for bound1 to bound2 with step to expr.
 * </h5>
 * <br/>Implemented by vp( "forstep" ).
 * 
 * <h3>
 * forstep_sl( var, bound1, bound2, step, expr, expr_l )
 * </h3>
 * <h5>
 * Apply macro var for bound1 to bound2 with step to expr, but to expr_l for the last one.
 * </h5>
 * <br/>Implemented by vp( "forstep_sl" ).
 * 
 * <h3>
 * foreach( var, item1, ..., itemn, expr )
 * </h3>
 * <h5>
 * Apply macro var for item1, ... itemn to expr.
 * </h5>
 * <br/>Implemented by vp( "foreach" ).
 * 
 * <h3>
 * foreach_sl( var, item1, ..., itemn, expr, expr_l )
 * </h3>
 * <h5>
 * Apply macro var for item1, ... itemn-1 to expr, itemn to expr_l.
 * </h5>
 * <br/>Implemented by vp( "foreach" ).
 * 
 * 
 * @author Burkhardt Renz
 */
public class loops extends Macro {
	
  enum Mode {
  	FOR,
  	FORSTEP,
  	FOREACH,
  }

	private static Logger logger = 
		Logger.getLogger( loops.class.getName() );
	
	private Mode mode = Mode.FOR;
	
	public loops( String mode ) {
		super( Style.NEEDS_PARENTHESIS );
		// default Mode.FOR
		if ( mode.equals("forstep") ) {
			this.mode = Mode.FORSTEP;
		} else if ( mode.equals("foreach") ){
			this.mode = Mode.FOREACH;
		}
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	// arguments to iterate
  	List<String> args = null;
  	String var = null;
  	String expr = null;
  	
  	switch ( mode ) {
  		case FOR:
  			checkParams( size-1, 4 );
  			args = prepareArgs4For( macArgs );
				var = macArgs.get(1);
				expr = macArgs.get(4);
  			break;
  		case FORSTEP:
  			checkParams( size-1, 5 );
  			args = prepareArgs4Forstep( macArgs );
				var = macArgs.get(1);
				expr = macArgs.get(5);
  			break;
  		case FOREACH:
  			checkParamsMin( size-1, 3 );
  			args = prepareArgs4Foreach( macArgs, Mode.FOREACH );
				var = macArgs.get(1);
				expr = macArgs.get(size-1);
  			break;
  	}
  	
		String result = iterate( engineContext, var, args, expr );
		
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
		return result;
  }

	private void checkParams( int value, int expected) throws SyntaxErrorException {
		if ( value != expected ) {
			throw new SyntaxErrorException( 1001,
					String.format("found %d arguments, expected %d.", value, expected) );
		}
  }
	
	private void checkParamsMin( int value, int expected) throws SyntaxErrorException {
		if ( value < expected ) {
			throw new SyntaxErrorException( 1001,
					String.format("found %d arguments, expected at least %d.", value, expected) );
		}
  }

	private List<String> prepareArgs4For( List<String> macArgs ) throws SyntaxErrorException {
		int lower;
  	int upper;
  	try {
  		lower = Integer.parseInt( macArgs.get(2) );
  		upper = Integer.parseInt( macArgs.get(3) );
		} catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, e.getMessage() );
		}	
		List<String> args = new ArrayList<String>();
		for ( int i = lower; i <= upper; i++ ){
			args.add( String.valueOf(i) );
		}
	  return args;
  }

	private List<String> prepareArgs4Forstep( List<String> macArgs ) throws SyntaxErrorException {
		int bound1;
		int bound2;
		int step;
  	try {
  		bound1 = Integer.parseInt( macArgs.get(2) );
  		bound2 = Integer.parseInt( macArgs.get(3) );
  		step  = Integer.parseInt( macArgs.get(4) );
		} catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, e.toString() );
		}	
		if ( step == 0 ) {
    	throw new SyntaxErrorException( 1003, "step must be != 0" );
		}
		if ( bound1 <= bound2 && step < 0 ) {
    	throw new SyntaxErrorException( 1003, "step must be > 0" );
		}
		if ( bound1 >= bound2 && step > 0 ) {
    	throw new SyntaxErrorException( 1003, "step must be < 0" );
		}
		List<String> args = new ArrayList<String>();
		
		if ( bound1 <= bound2 ) {
			for ( int i = bound1; i <= bound2; i = i+step ){
				args.add( String.valueOf(i) );
			}	
		} else {
			for ( int i = bound1; i >= bound2; i = i+step ) {
				args.add( String.valueOf(i) );
			}
		}
	  return args;
  }
	
	private List<String> prepareArgs4Foreach( List<String> macArgs, Mode mode ) throws SyntaxErrorException {
		List<String> args = new ArrayList<String>();
		
		int size = macArgs.size();
		int lower = 2;
		int upper = (mode == Mode.FOREACH) ? size-2 : size-3;
		for ( int i = lower; i <= upper; i++ ){
			args.add( macArgs.get(i) );
		}
	  return args;
  }

	private String iterate( EngineContext engineContext, String var,
      List<String> args, String expr ) {
		
		String begQuote = engineContext.getSettings().getBegQuote();
  	String endQuote = engineContext.getSettings().getEndQuote();
  	
		StringBuffer sb = new StringBuffer();
		int cnt = 0;
		int size = args.size();
		for ( String arg: args ) {
			cnt++;
			sb.append( "pushdef(" + begQuote + var + endQuote + "," + arg + ")" );
			if ( cnt == size ) {
				sb.append( "define(" + begQuote + "@last" + var + endQuote + ")" );
				sb.append( expr );
				sb.append( begQuote + endQuote );
				sb.append( "undefine(" + begQuote + "@last" + var + endQuote + ")" );
			} else {
				sb.append( expr );
			}
			sb.append( begQuote + endQuote );
			sb.append( "popdef(" + begQuote + var + endQuote + ")" );
		}	
		
		return sb.toString();
  }

}
