/** --------------------------------------------------------------------------
 * User defined macros are objects of UserMacro
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
 * $Id: UserMacro.java 130 2008-04-25 11:43:58Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import mmp.builtins.ExpToken;
import mmp.builtins.ExpTokenizer;

/**
 * Implementation of user defined macros.
 * <br/>
 * User defined macros do not need parenthesis to be recognized
 * as macros.
 * 
 * @author Burkhardt Renz
 *
 */
public class UserMacro extends Macro {
	
	private static Logger logger = 
		Logger.getLogger( mmp.engine.UserMacro.class.getName());
	
	private String name;
	private String expansion;

	/**
	 * Creates user defined macro with name 'name' that expands to 'expansion'.
	 * 
	 * @pre   name != null && expansion != null
	 * @param name of macro
	 * @param expansion of macro
	 */
	public UserMacro( String name, String expansion ) {
		super( Style.DOES_NOT_NEED_PARENTHESIS, name, expansion );
		this.name      = name;
		this.expansion = expansion;
	}
				
	/**
	 * Get the name of the user defined macro.
	 * <br/>
	 * Caveat: this is the name defined at the time of the
	 * construction of the macro. Is has not to be identical with the name,
	 * under which the macro will be registered in the mmp engine. However
	 * in the normal case it will be.
	 * 
	 * @return name of macro
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the expansion of the macro.
	 * 
	 * @return the expansion
	 */
	public String getExpansion() {
		return this.expansion;
	}
	
	@Override
  public String call( List<String> macArgs, EngineContext engineContext )
      throws IOException, SyntaxErrorException {
		
  	logger.info( String.format("MMPTrace - Call of %s [%d]): %s", 
  			name, engineContext.getRecursionLevel(), macArgs.toString()) );
  	
  	StringBuffer sb = new StringBuffer();
  	ExpTokenizer et = new ExpTokenizer( expansion );
  	int size = macArgs.size();
  	
  	ExpToken token;	
  	while ( et.hasNext() ) {
  		token = et.next();
  		switch ( token.getType() ) {
  			case TEXT:
  				sb.append( token.getValue() );
  				break;
  			case PARAM:
  				int p = Integer.parseInt( token.getValue() );
  				if ( p < size ) {
  					sb.append( macArgs.get(p) );
  				}
  				break;
  			case ARGC:	
  				// macArgs[0] is the name of the macro
  				sb.append( Integer.toString(size-1) );
  				break;
  			case ARGS:
  				for ( int i = 1; i < size; i++ ) {
  					sb.append( macArgs.get(i) );
  					if ( i != size-1 ) {
  						sb.append( "," );
  					}
  				}	
  				break;
  			case QARGS:
  				String begQuote = engineContext.getSettings().getBegQuote();
  				String endQuote = engineContext.getSettings().getEndQuote();
  				for ( int i = 1; i < size; i++ ) {
  					sb.append( begQuote );
  					sb.append( macArgs.get(i) );
  					sb.append( endQuote );
  					if ( i != size-1 ) {
  						sb.append( "," );
  					}
  				}	
  				break;
  		}
  	}
  	String result = sb.toString();
  	
  	if ( !result.isEmpty()) {
  		logger.info( String.format("MMPTrace - Expansion of '%s': %s", name, result) );
  	}	
  	
	  return sb.toString();
  }

}
