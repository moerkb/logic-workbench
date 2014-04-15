/** --------------------------------------------------------------------------
 * Scanner for mmp input
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
 * $Id: Scanner.java 336 2008-07-01 06:46:14Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import mmp.util.CharType;

/**
 * Scanner splits the input into tokens.
 *  
 * @author Burkhardt Renz
 *
 */
class Scanner {
	
	private static Logger logger = Logger.getLogger( Scanner.class.getName() );
	
	private Input source = null;
	private Settings settings = null;
	private int recursionLevel = 0;
	
	/**
	 * Constructor initializes the scanner with input to read from.
	 * @param source
	 */
	Scanner( EngineContext engineContext, Input source ) {
		this.source = source;
		this.settings = engineContext.getSettings();
	}
	
	/**
	 * reads nextToken in input
	 * @return Token found
	 * @throws IOException 
	 * @throws SyntaxErrorException 
	 * @modifies this
	 */
	Token readToken() throws IOException, SyntaxErrorException {
	  int next = this.source.peekChar();
		if ( next != -1 ) {	
			char ch = (char)next;
			
			// check for comment
			String begComment = settings.getBegComment();
			if ( begComment.length() > 0 && ch == begComment.charAt(0) ) {
				String found = checkForComment();
				if ( found != null ) {
					return new Token( Token.Type.COMMENT, found );
				}
			} 
			
			// check for quoted string
			String begQuote = settings.getBegQuote();
			if ( begQuote.length() > 0 && ch == begQuote.charAt(0) ) {
				String found = checkForString();
				if ( found != null ) {
					return new Token( Token.Type.QSTRING, found );
				}
			} 
			
			// check for identifier
			if ( CharType.isIdentifierFirstChar(ch) ) {
				StringBuffer sb = new StringBuffer();
				this.source.readChar();
				sb.append( ch );
				while ( (ch = (char)this.source.peekChar()) != -1 && CharType.isIdentifierChar(ch) ) {
					this.source.readChar();
					sb.append( ch );
				}
				return new Token( Token.Type.IDENTIFIER, sb.toString() );
			}
				
			// check for special characters; default a single character
			this.source.readChar();
   		switch ( (char)ch ) {
				case '(' :
					return new Token( Token.Type.BEGARGS, "(" );
				case ')' :	
					return new Token( Token.Type.ENDARGS, ")" );
				case ',' :	
					return new Token( Token.Type.COMMA, "," );
				default:
					return new Token( Token.Type.CHAR, String.valueOf( (char)ch ) );
			}
		}
		return new Token( Token.Type.EOI );
	}
	
	private boolean isNextTokenBegArgs() throws IOException {
		return this.source.peekChar() == '(';
	}
	
	private String checkForComment() throws IOException, SyntaxErrorException {
		
		StringBuffer sb = new StringBuffer();
		
		String beg = settings.getBegComment();
		String end = settings.getEndComment();
		
		if ( source.matches(beg) ) {
			sb.append( beg );
			
			while ( !source.matches(end) ) {
				int ch = source.readChar();
				if ( ch == -1 ) {
					throw new SyntaxErrorException( 901, String.format("end of input in comment; near '%s'", 
							sb.toString()) );
				}
				sb.append( (char)ch );
			}
			sb.append( end );
			return sb.toString();
		}
		return null;
	}
	
	private String checkForString() throws IOException, SyntaxErrorException {
		StringBuffer sb = new StringBuffer();
		int quoteLevel = 0;
		
		String beg =  settings.getBegQuote();
		String end =  settings.getEndQuote();
		
		// allow nested quotes
		if ( source.matches(beg) ) {
			quoteLevel++;
			while ( true ) {
				int ch = source.peekChar();
				if ( ch == -1 ) {
					throw new SyntaxErrorException( 902, String.format("end of input in quoted string; near '%s'", 
							sb.toString()) );
				}
				if ( source.matches(end) ) {
				// endQuote
					quoteLevel--;
					if ( quoteLevel == 0 ) {
						return sb.toString();
					} else {
						sb.append( end );
					}
				} else if ( source.matches(beg) ) {
				// begQuote
					quoteLevel++;
					sb.append( beg );
				} else {
					sb.append( (char)source.readChar() );
				}
			}
		}
		return null;
	}

	private void collectArguments(EngineContext engineContext, Input source, List<String> macArgs) 
		throws IOException, SyntaxErrorException, RuntimeErrorException, M4ExitException {
		
		logger.fine( String.format("collecting arguments to macro '%s'", macArgs.get(0)) );
  	
  	if ( isNextTokenBegArgs()) {
  		readToken(); 
  		// now input is on the first character within the parenthesis
	  	boolean moreArgs = true;
  		do {
  			moreArgs = expandArgument( engineContext, macArgs );
  		} while ( moreArgs );
  	}
  }

	private boolean expandArgument( EngineContext engineContext, List<String> macArgs ) 
		throws IOException, SyntaxErrorException, RuntimeErrorException, M4ExitException {
		
		logger.fine( String.format("expanding argument $%d", macArgs.size()) );
		
  	Token token = null;
  	// skip white space
  	
  	do {
  		token = readToken();
  	} while ( token.getType() == Token.Type.CHAR && Character.isWhitespace(token.getValue().charAt(0)) );
  	
  	int parenLevel = 0;
  	StringBuffer sb = new StringBuffer();
  	
  	while ( true ) {
  		logger.fine( token.toString() );
  		
  		switch ( token.getType() ) {
  			case COMMA:
  				if ( parenLevel == 0 ) {
  					macArgs.add( sb.toString() );
  					return true;
  				}
  			// fall through	
  			case ENDARGS:
  				if ( parenLevel == 0 ) {
  					macArgs.add( sb.toString() );
  					return false;
  				}
  			// fall through	
  			case BEGARGS:	
  			// fall through	
  			case CHAR:
  				if ( token.getValue().equals("(") ) {
  					parenLevel++;
  				} else if ( token.getValue().equals(")") ) {
  					parenLevel--;
  				}
  				sb.append( expand(engineContext, token) );
  				break;
  				
  			case EOI:
  				throw new SyntaxErrorException( 903, "End of input in argument list, near " + sb.toString() );
  			case COMMENT:
  			case IDENTIFIER:
  			case QSTRING:
  				sb.append( expand(engineContext, token) );
  				break;
  		}
  		token = readToken();
  	}
  }

	String expand( EngineContext engineContext, Token token ) 
		throws IOException, SyntaxErrorException, RuntimeErrorException, M4ExitException {
  	
		String result = "";
		
  	switch( token.getType() ) {
  		case BEGARGS:
  		case ENDARGS:
  		case COMMA:
  		case CHAR:
  		case QSTRING:
  		case COMMENT:	
  			result = token.getValue();
  			break;
  		case IDENTIFIER:	
    		Macro macro = engineContext.getMacroRegistry().getMacro( token.getValue() );
      	if ( macro != null && !(macro.needsParenthesis() && !isNextTokenBegArgs()) ) {
      		List<String> macArgs = new Vector<String>();
      		macArgs.add(  token.getValue() ); // macArgs[0] is macro name
      		recursionLevel++;
      		if ( recursionLevel > engineContext.getSettings().getRecursionLimit() ) {
      			throw new RuntimeErrorException( 1001, 
      					String.format("Recursion limit of %d exceeded", 
      							engineContext.getSettings().getRecursionLimit()) );
      		}
      		
      		collectArguments( engineContext, source, macArgs );
      		// result of macro.call must be parsed again at the beginning of input
      		String expandedMacro = macro.call( macArgs, engineContext );
      		recursionLevel--;
      		if ( !expandedMacro.isEmpty() ) {
          	logger.fine(  String.format( "expanded macro '%s': '%s'; will be pushed on top of input", 
          			macArgs.get(0), expandedMacro) );
          	source.pushInputSource( new StringReader(expandedMacro) );
      		}
      		result = "";
      	}
      	else {
      		result = token.getValue();
      	}
      	break;
  	}	
  	logger.fine( String.format("result of expansion: '%s'", result) );
  	return result;
  }
	
	int getRecursionLevel() {
		return this.recursionLevel;
	}

}
