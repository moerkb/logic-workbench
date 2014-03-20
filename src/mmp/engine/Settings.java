/** --------------------------------------------------------------------------
 * Settings of mmp
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
 * $Id: Settings.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
//import java.util.logging.Logger;

import mmp.util.TraceFormatter;

/**
 * Settings of the MNI macro processor.
 * 
 * @author Burkhardt Renz
 *
 */
public class Settings {
	
	//private static Logger logger = Logger.getLogger( Settings.class.getName() );
	
	/**
	 * Default constructor.
	 */
	public Settings() {
		initTraceHandler();
		
	}
	
  private void initTraceHandler() {
		traceHandler = new ConsoleHandler();
		traceHandler.setLevel( Level.ALL );
		traceHandler.setFormatter( new TraceFormatter() );
  }

	/**
   * Levels of compatibility of builtin macros 
   * (MMP > GNU > POSIX).
   */
  public enum CompatibilityLevel {
  	/** Classical m4 as specified by POSIX */ POSIX, 
  	/** Extended m4 as provided by GNU     */ GNU,
  	/** Embeddable m4 in Java by MNI       */ MMP
  }
  
  // TraceHandler
  private Handler traceHandler;

	// Quotes
	// an empty string begQuote disables the quoting mechanism
  // @invariant the first character of begQuote must not be
  //            a letter, a digit, '_', '(', ',', or ')'
	private static final String begQuoteDefault = "`";
	private static final String endQuoteDefault = "'";
	private String begQuote = begQuoteDefault;
	private String endQuote = endQuoteDefault;

	// Comments
	// an empty string begComment disables the comment mechanism
  // @invariant the first character of begComment must not be
  //            a letter, a digit, '_', '(', ',', or ')'
	private static final String begCommentDefault = "#";
	private static final String endCommentDefault = "\n";
	private String endComment = endCommentDefault;
	private String begComment = begCommentDefault;
	
	// Limits
	// limit for recursion in macro calls
	private static final int recursionLimitDefault = 512;
	private int recursionLimit = recursionLimitDefault;
	
	// SearchPath
	private List<String> searchPath = new ArrayList<String>();
	
	/**
	 * Get the string that's the beginning quote.
	 * 
   * @return the begQuote
   */
  public final String getBegQuote() {
  	return begQuote;
  }

	/**
	 * Get the string that's the end quote.
	 * 
   * @return the endQuote
   */
  public final String getEndQuote() {
  	return endQuote;
  }

	/**
	 * Get the string that begins a comment.
   * @return the begComment
   */
  public final String getBegComment() {
  	return begComment;
  }

	/**
	 * Get the string that ends a comment.
	 * 
   * @return the endComment
   */
  public final String getEndComment() {
  	return endComment;
  }

	/**
	 * Changes the delimiters for quoted strings.
	 * 
	 * @pre The delimiters should not begin with a letter in [A-Z,a-z], a digit, 
	 * '_', '(', ')', or ','. 
   * @param begQuote the begQuote to set
   * @param endQuote the endQuote to set
   * @modifies this
   */
  public final void changeQuote( String begQuote, String endQuote ) {
  	this.begQuote = begQuote;
  	this.endQuote = endQuote;
  }
  
  /**
   * Sets the delimiters for quoted strings to default.
   * 
   * @modifies this
   */
  public final void setDefaultQuote() {
  	this.begQuote = begQuoteDefault;
  	this.endQuote = endQuoteDefault;
  }
  	
	/**
	 * Changes the delimiters for comments.
	 * 
	 * @pre The delimiters should not begin with a letter in [A-Z,a-z], a digit, 
	 * '_', '(', ')', or ','. <br/>
   * @param begComment the begComment to set
   * @param endComment the endComment to set
   * @modifies this
   */
  public final void changeComment( String begComment, String endComment ) {
  	this.begComment = begComment;
  	this.endComment = endComment;
  }

  /**
   * Sets the delimiters for comments to default.
   * 
   * @modifies this
   */
  public final void setDefaultComment() {
  	this.begComment = begCommentDefault;
  	this.endComment = endCommentDefault;
  }
	/**
	 * Get the limit for recursive calls of macros.
	 * 
   * @return the recursion limit
   */
  public final int getRecursionLimit() {
  	return this.recursionLimit;
  }

	/**
	 * Set the limit for recursion.
   * @param recursionLimit the limit to set
   * @modifies this
   */
  public final void setRecursionLimit( int recursionLimit ) {
  	this.recursionLimit = recursionLimit;
  }
  
  /**
   * Adds directory to the search path for includes.
   * 
   * @param directory to be searched too
   * @modifies this
   */
  public final void addToSearchPath( String directory ) {
  	searchPath.add( directory );
  }
  
  /**
   * Get list of all directories in the search path.
   * 
   * @return search path
   */
  public final List<String> getSearchPath() {
  	return this.searchPath;
  }
  
  /**
   * Get current trace handler.
   * 
   * @return trace handler
   */
  public final Handler getTraceHandler() {
  	return this.traceHandler;
  }

  /**
   * Set handler as trace handler.
   * 
   * @param traceHandler to be set
   * @modifies this
   */
  public final void setTraceHandler( Handler traceHandler ) {
  	this.traceHandler = traceHandler;
  }
}
