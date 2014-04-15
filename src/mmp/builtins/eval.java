/** --------------------------------------------------------------------------
 * Implementation of macro 'eval'
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
 * $Id: eval.java 568 2008-08-29 02:03:26Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.builtins;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mic.calc.Mic;
import mic.parser.ParseException;
import mmp.engine.EngineContext;
import mmp.engine.Macro;
import mmp.engine.RuntimeErrorException;
import mmp.engine.SyntaxErrorException;

/**
 * <h3>
 * eval( expression [,base [,width]] )
 * </h3>
 * <h5>
 * evaluate integer expression
 * </h5>
 * <br/>Implemented by eval().
 * 
 * @author Burkhardt Renz
 */
public class eval extends Macro {
	
	private static Logger logger =  Logger.getLogger( eval.class.getName() );
	
	public eval() {
		super( Style.NEEDS_PARENTHESIS );
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException, RuntimeErrorException {
  	
		int size = macArgs.size(); 
		String macroName = macArgs.get( 0 );
		
  	logger.fine( String.format( MMPTRACE_CALL, 
  			macroName, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
		if ( size < 2 || size > 4 ) {
			throw new SyntaxErrorException( 1001,
					String.format("define for '%s' has %d arguments, expected 1 to 3", macArgs.get(0), size-1) );
		}
		
		String expression = macArgs.get(1);
		if ( expression.trim().isEmpty() ) {
			expression = "0";
		}
		String base = (size >= 3) ? macArgs.get(2) : "10";
		if ( base.trim().isEmpty() ) {
			base = "10";
		}
		String width = (size == 4) ? macArgs.get(3) : "1";
		
		
		// evaluate expression
		Mic evaluator = new mic.calc.Mic();
		String result;
    try {
	    result = evaluator.evaluate( expression );
    } catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, e.getMessage() );
    } catch ( ArithmeticException e ) {
    	throw new SyntaxErrorException( 1101, e.getMessage() );
    } catch ( IllegalStateException e ) {
    	throw new SyntaxErrorException( 1103, e.getMessage() );
    } catch ( ParseException e ) {
    	throw new SyntaxErrorException( 1102, e.getMessage() );
    } catch ( Throwable e ) {
    	throw new RuntimeErrorException( 1202, e.toString() );
    }
    
    // format output
    int ibase = 10;
    int iwidth = 1;
    try {
    	ibase = Integer.parseInt( base );
    } catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, "base in macro 'eval' is not an integer" );
    }	
    try {
    	iwidth = Integer.parseInt( width );
    } catch ( NumberFormatException e ) {
    	throw new SyntaxErrorException( 1101, "width in macro 'eval' is not an integer" );
    }	
    if ( ibase < 1 || ibase > 36 ) {
    	throw new SyntaxErrorException( 1103, "base in macro 'eval' is out of range" );
    }
    if ( iwidth < 0 ) {
    	throw new SyntaxErrorException( 1103, "width in macro 'eval' is negative" );
    }
    
    if ( ibase != 10 ) {
    	result = Integer.toString( Integer.parseInt(result), ibase );
    } 
    
    int len = result.length();
    if ( len < iwidth ) {
    	boolean neg = (result.charAt(0) == '-');
    	if ( neg ) {
    		result = result.substring( 1 );
    	}
    	StringBuffer sb = new StringBuffer();
    	if ( neg ) {
    		sb.append( "-" );
    	}
    	int pre = iwidth - result.length();
    	for ( int i = 0; i < pre; i++ ) {
    		sb.append( "0" );
    	}
    	sb.append( result );
	  	result = sb.toString();
    }
    
 		logger.fine( String.format(MMPTRACE_EXP, macroName, result) );
 		
	  return result;
  }
  
}
