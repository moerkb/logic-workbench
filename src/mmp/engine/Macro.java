/** --------------------------------------------------------------------------
 * Abstract class that must be extended by the provider of a Java macro.
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
 * $Id: Macro.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class that must be extended by the provider of a Java macro.
 * <br/><br/>
 * Contract:
 * <ol>
 * 	 <li>Implement a constructor that uses the constructor of the super class
 *       with the appropriate value for needsParenthesis.
 *       <br/>
 *       Example:
 *       <br/>
 *       <code>
 *					public myMacro() {
 *       		<br/>
 *					&nbsp;&nbsp;super( false );
 *       		<br/>
 *					}
 *       <br/>
 *       </code>
 *     </li>
 *     <li>Provide an implementation of 
 *     		{@linkplain mmp.engine.Macro#call(List, EngineContext)}. <br/>
 *       This method will be called from the engine during processing.
 *     </li>
 *     <li>Sometimes several macros are closely related and one 
 *     	may wish to use a common implementation.
 *     To achieve this, one has to
 *       <br/>
 *     (a) provide a constructor with more than one parameter, 
 *     		<i>which must be of type String</i>,
 *       <br/>
 *     (b) override {@linkplain mmp.engine.Macro#getInstInfo()} 
 *     		to allow the mmp engine a call
 *       of the macro by reflection.
 *     </li>
 * </ol>
 * 
 * @author Burkhardt Renz
 *
 */
public abstract class Macro {
	
	/**
	 * Enumeration classifying whether the macro needs
	 * parenthesis to be recognized as a macro.
	 */
	public enum Style {
		/**
		 * The macro needs parenthesis to be recognized as a macro,
		 * e.g. 'define(`foo',`bar')' will be processed as a macro,
		 * whereas 'define' will written to output without being processed
		 * by the macro processing engine.
		 */
		NEEDS_PARENTHESIS,
		/**
		 * The macro does not need parenthesis to be recognized as a 
		 * macro. This is always the case with user defined macros, e.g.
		 * if 'foo' is defined to expand to 'bar' the string 'foo' will
		 * be recognized as a macro an processed by the engine.
		 */
		DOES_NOT_NEED_PARENTHESIS
	}
	
	private boolean needsParenthesis = false;
	private List<String> instInfos = new ArrayList<String>();
	
	/**
	 * MMPTRACE_CALL is the format string for tracing the call of a macro.
	 * <br/><br/>
	 * It's arguments are:
	 * <ol>
	 *   <li>the macro name (String)</li>
	 *   <li>the recursion level of the invocation of the macro (int)</li>
	 *   <li>the arguments of the call (String) </li>
	 *  </ol>  
	 */
	public static final String MMPTRACE_CALL = 
			"MMPTrace - Call of %s [%d]): %s"; 
	/**
	 * MMPTRACE_EXP is the format string for tracing the expansion of a macro.
	 * <br/><br/>
	 * It's arguments are:
	 * <ol>
	 *   <li>the macro name (String)</li>
	 *   <li>the expansion of the macro, if not empty (String) </li>
	 *  </ol>  
	 */
	public static final String MMPTRACE_EXP  = 
			"MMPTrace - Expansion of '%s': %s";
	
	/**
	 * Constructor for a user defined Java macro.
	 * 
	 * @param style If the macro needs parenthesis to be recognized as a
	 * macro, the parameter must be Mode.NEEDS_PARENTHESIS, 
	 * Mode.DOES_NOT_NEED_PARENTHESIS otherwise. <br/>
	 * The builtin macro 'define' e.g. is only recognized 
	 * as a macro if it has an argument list like 'define( `foo', `bar' )', 
	 * whereas the name of the macro
	 * 'define' without argument list is reproduced to the output.<br/>
	 * @param instInfo are the parameters of the constructor of the
	 * macro. It has to be parameters of type String.
   */
	public Macro( Style style, String... instInfo  ) {
		this.needsParenthesis = (style == Style.NEEDS_PARENTHESIS) ? true : false;
		this.instInfos.add( this.getClass().getName() );
		for ( String s : instInfo ) {
			this.instInfos.add( s );
		}
	}
	
	/**
	 * Calls the macro with the argument 'macArg' in the 'engineContext'.
	 * <br/>
	 * 
	 * This method must be provided by macros extending this class. It is called
	 * by the mmp engine.
	 * <br/>
	 * @pre macArgs != null && engineContext != null && <br/>
	 *      ( needsParenthesis == true implies macArgs.size() >= 2 )
	 * @param macArgs  macArgs[0] is always the name of the macro
	 * @param engineContext the engineContext is provided by the 
	 * mmp engine and can be used by the implementation of the macro.
	 * @return the expansion of the macro
	 * @throws IOException io error
	 * @throws SyntaxErrorException syntax error
	 * @throws RuntimeErrorException  runtime error
	 * @throws M4ExitException m4exit 
	 */
	public abstract String call( List<String> macArgs, EngineContext engineContext ) 
		throws IOException, SyntaxErrorException, RuntimeErrorException, M4ExitException;
	
	/**
	 * Macro needs parenthesis to be recognized as a macro.
	 * 
   * @return needsParenthesis
   * @post if needsParenthesis == true the engine will 
   * call the macro with an macArgs of size >= 2
   */
  public final boolean needsParenthesis() {
	  return needsParenthesis;
  }
	
	/**
	 * Provides instantiation info for a Java macro.
	 * 
	 * @return
	 * the instantiation info for a Java macro.
	 * The first element in the list is the class name.
	 * <br/>
	 * The following arguments are the parameters of type String
	 * of the constructor of the form macro( String p1, String p2 ...)
	 * <br/>
	 * If the provider of a macro has a constructor with a constructor with
	 * arguments, they have to be Strings and must be added to the 
	 * instantiation info.
	 * 
	 * @post The returned list has at least one element. If it has
	 * more than one element, there is a corresponding constructor.
	 */
	public final List<String> getInstInfo() {
	  return this.instInfos;
  }
}
