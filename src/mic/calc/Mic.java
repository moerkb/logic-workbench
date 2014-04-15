/** --------------------------------------------------------------------------
 * The MNI Integer Expression Parser provides the Calculator to evaluate 
 * expressions.
 * 
 * Copyright (c) 2008
 * by Fachhochschule Gießen-Friedberg University of Applied Sciences.
 * 
 * mic is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * mic is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * --------------------------------------------------------------------------
 * $Id: Mic.java 750 2008-12-18 07:31:33Z brenz $
 * --------------------------------------------------------------------------
 */
package mic.calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;

import mic.parser.MicParser;
import mic.parser.MicParserVisitor;
import mic.parser.MicStart;
import mic.parser.ParseException;
import mic.visitor.Calculator;
import mic.visitor.TreePrinter;

/**
 * Provides the evaluator that calculates integer expressions.
 * @author Burkhardt Renz
 */
public class Mic {
	
	/**
	 * Calculates the expression.
	 * 
	 * @pre expression != null
	 * @param expression to be calculated
	 * @return integer value of expression according to the specification of 
	 * eval in m4
	 * @throws ParseException if the syntax of the expression is invalid
	 * @throws NumberFormatException if the parsing of numbers yields invalid 
	 * results
	 * @throws IllegalStateException if the syntax tree is invalid, <br/>
	 * @throws IOException 
	 */
	public final int calculate( String expression ) 
		throws IllegalStateException, NumberFormatException, ParseException {
			
		// Calculator
		MicParser micParser = new MicParser( new StringReader( expression ) );	
		MicParserVisitor evaluator = new Calculator();
	  MicStart root = micParser.Start();
		Integer result = (Integer)root.jjtAccept( evaluator, null );
		return result;
	}
	
	/**
	 * Evaluates the expression.
	 * 
	 * @pre expression != null
	 * @param expression to be calculated
	 * @return evaluation of expression (as a string) according to the 
	 * specification  of eval in m4
	 * @throws ParseException if the syntax of the expression is invalid
	 * @throws NumberFormatException if the parsing of numbers yields invalid 
	 * results
	 * @throws IllegalStateException if the syntax tree is invalid
	 */
	public final String evaluate( String expression ) 
		throws NumberFormatException, IllegalStateException, ParseException {
		String result = null;
		try {
    	result = String.format( "%d", calculate( expression ) );
    } catch ( Throwable e ) {
    	throw new ParseException( e.toString() );
    }	
    return result;
		
	}
	
	/**
	 * Prints the syntax tree of the expression to writer out.
	 * 
	 * @pre expression != 0; out != null
	 * @effects parses the expression, generates the syntax tree and 
	 * prints it on out
	 * @param expression  to be parsed
	 * @param out where to write the syntax tree
	 * @throws ParseException in case of syntax error
	 * @throws IOException in case of io error
	 */
	public final void printSyntaxTree( String expression, Writer out ) 
		throws ParseException, IOException {
		
		// TreePrinter
		MicParser micParser = new MicParser( new StringReader( expression ) );	
		MicParserVisitor treePrinter = new TreePrinter( 
					new PrintWriter( out ) );
	  MicStart root = micParser.Start();
		root.jjtAccept( treePrinter, null );
		out.flush();
	}
}
