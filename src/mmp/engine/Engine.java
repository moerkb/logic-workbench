/** --------------------------------------------------------------------------
 * The MNI macro processor engine
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
 * $Id: Engine.java 742 2008-12-17 11:44:59Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import mmp.builtins.changecom;
import mmp.builtins.changequote;
import mmp.builtins.define;
import mmp.builtins.defn;
import mmp.builtins.divert;
import mmp.builtins.divnum;
import mmp.builtins.dnl;
import mmp.builtins.dumpdef;
import mmp.builtins.errprint;
import mmp.builtins.eval;
import mmp.builtins.format;
import mmp.builtins.id;
import mmp.builtins.ifdef;
import mmp.builtins.ifelse;
import mmp.builtins.include;
import mmp.builtins.incr;
import mmp.builtins.index;
import mmp.builtins.indir;
import mmp.builtins.instinfo;
import mmp.builtins.instreg;
import mmp.builtins.len;
import mmp.builtins.m4exit;
import mmp.builtins.m4wrap;
import mmp.builtins.patsubst;
import mmp.builtins.popdef;
import mmp.builtins.regexp;
import mmp.builtins.shift;
import mmp.builtins.substr;
import mmp.builtins.trace;
import mmp.builtins.translit;
import mmp.builtins.undefine;
import mmp.builtins.undivert;
import mmp.engine.Settings.CompatibilityLevel;

/**
 * The MNI macro processor engine.
 * 
 * @author Burkhardt Renz
 */
public class Engine implements EngineContext {
	
	private static Logger logger = Logger.getLogger( Engine.class.getName() );
	
	private Input input;
	private Scanner scanner;
	private Output output;
	private MacroRegistry macroRegistry = new MacroRegistry();
	private Settings settings = new Settings();
	
	/**
	 * Constructs the MMP engine with default compatibility level.
	 * 
	 * @param reader   where to read the input.
	 * @param writer   where to write the output.
	 */
	public Engine( Reader reader, Writer writer ) {
		this( reader, writer, Settings.CompatibilityLevel.MMP );
	}
	
	/**
	 * Constructs the MMP engine.
	 * 
	 * @param reader  where to read the input.
	 * @param writer  where to write the output.
	 * @param compLevel compatibility level for the engine,
	 *   the levels are {@linkplain Settings.CompatibilityLevel}.
	 */
	public Engine( Reader reader, Writer writer, 
			CompatibilityLevel compLevel ) {
		input         = new Input( reader );
		scanner       = new Scanner( this, input );
		output        = new Output( writer );
		// initializing	
		settings.addToSearchPath( "." );
		initBuiltins( compLevel );
		initLogging();
	}
	
  private void initLogging() {
  	// Loggers use TraceHnadler from Settings
  	Logger rootLogger = Logger.getLogger( "" );
  	Handler[] handlers = rootLogger.getHandlers();
	  for ( int i = 0; i < handlers.length; i++ ) {
	  	rootLogger.removeHandler( handlers[i] );
	  }
		rootLogger.addHandler( settings.getTraceHandler() );
		// initially tracing of user defined macros is off
		Logger.getLogger( 
				mmp.engine.UserMacro.class.getName() ).setLevel( Level.OFF );
  }

	private void initBuiltins( CompatibilityLevel compLevel ) {
  	switch ( compLevel ) {
  		case MMP:
				macroRegistry.registerBuiltin(  "__mmp__",     new id() );
				macroRegistry.registerBuiltin(  "instinfo",    new instinfo() );
				macroRegistry.registerBuiltin(  "instpush",    new instreg("instpush") );
				macroRegistry.registerBuiltin(  "instreg",     new instreg("instreg") );
  			// fall thru
  		case GNU:
				macroRegistry.registerBuiltin(  "builtin",     new indir("builtin") );
				macroRegistry.registerBuiltin(  "format",      new format() );
				macroRegistry.registerBuiltin(  "indir",       new indir("indir") );
    		macroRegistry.registerBuiltin(  "patsubst",    new patsubst() );
				macroRegistry.registerBuiltin(  "regexp",      new regexp() );
  			// fall thru
  		case POSIX:
    		macroRegistry.registerBuiltin(  "changecom",   new changecom() );
    		macroRegistry.registerBuiltin(  "changequote", new changequote() );
    		macroRegistry.registerBuiltin(  "errprint",    new errprint() );
    		macroRegistry.registerBuiltin(  "eval",        new eval() );
    		macroRegistry.registerBuiltin(  "decr",        new incr("decr") );
    		macroRegistry.registerBuiltin(  "define",      new define("define") );
    		macroRegistry.registerBuiltin(  "defn",        new defn() );
    		macroRegistry.registerBuiltin(  "dnl",         new dnl() );
    		macroRegistry.registerBuiltin(  "divert",      new divert() );
    		macroRegistry.registerBuiltin(  "divnum",      new divnum() );
    		macroRegistry.registerBuiltin(  "dumpdef",     new dumpdef() );
    		macroRegistry.registerBuiltin(  "ifdef",       new ifdef() );
    		macroRegistry.registerBuiltin(  "ifelse",      new ifelse() );
    		macroRegistry.registerBuiltin(  "include",     new include("include") );
    		macroRegistry.registerBuiltin(  "incr",        new incr("incr") );
    		macroRegistry.registerBuiltin(  "index",       new index() );
    		macroRegistry.registerBuiltin(  "len",         new len() );
    		macroRegistry.registerBuiltin(  "m4exit",      new m4exit() );
    		macroRegistry.registerBuiltin(  "m4wrap",      new m4wrap() );
    		macroRegistry.registerBuiltin(  "popdef",      new popdef() );
    		macroRegistry.registerBuiltin(  "pushdef",     new define("pushdef") );
    		macroRegistry.registerBuiltin(  "shift",       new shift() );
    		macroRegistry.registerBuiltin(  "sinclude",    new include("sinclude") );
    		macroRegistry.registerBuiltin(  "substr",      new substr() );
    		macroRegistry.registerBuiltin(  "traceoff",    new trace("traceoff") );
    		macroRegistry.registerBuiltin(  "traceon",     new trace("traceon") );
    		macroRegistry.registerBuiltin(  "translit",    new translit() );
    		macroRegistry.registerBuiltin(  "undefine",    new undefine() );
    		macroRegistry.registerBuiltin(  "undivert",    new undivert() );
  	}
  }

	/**
	 * Runs the MMP engine.
	 * 
	 * @return 0 <br/>If the engine is stopped by 'm4exit', 
	 * it returns the error code given as parameter to 'm4exit'.
	 * @throws IOException if io error
	 * @throws SyntaxErrorException if syntax error
	 * @throws RuntimeErrorException if runtime error
	 */
	public final int run() 
		throws IOException, SyntaxErrorException, RuntimeErrorException {
		
		// reads input token after token and expand the token.
		try {
	    Token token = scanner.readToken();
		 	logger.fine(  token.toString() );
		  while ( token.getType() != Token.Type.EOI ) {
		  	String expansion = scanner.expand(  this, token );
		  	logger.fine( String.format(
		  				"expansion of %s: '%s'", 
		  				token.getType().name(), expansion ) );
		  	output.write( expansion );
		   	token = scanner.readToken();
		  	logger.fine(  token.toString() );
		  }
		  output.close();
	  } catch ( M4ExitException e ) {
	  	output.exit();
		  setTraceOff();
	  	return e.getExitCode();
	  }
		setTraceOff();
	  return 0;
	}
	
	@Override
	public final Input getInput() {
	  return input;
  }

	@Override
  public final MacroRegistry getMacroRegistry() {
	  return macroRegistry;
  }

	@Override
  public final Settings getSettings() {
	  return settings;
  }

	@Override
  public final Output getOutput() {
	  return output;
  }

  @Override
  public final int getRecursionLevel() {
	  return this.scanner.getRecursionLevel();
  }

	/**
	 * Turn tracing of macro 'macroName' on.<br/>
	 * It switches to tracing of all the macros that
	 * share the logger of 'macroName', i.e. of all the
	 * macros that are implemented by the same Java class.
	 * E.g. used with a user defined macro, tracing of <em>all</em>
	 * user defined macros is on, because they all are implemented by
	 * the same class.
	 * <br/>
	 * The tracing is automatically set off after run of the engine.
	 * 
	 * @param macroName the name of the macro
	 */
  public final void setTraceOn( String macroName ) {
  	// find class and loggerName
  	Macro m = macroRegistry.getMacro( macroName );
  	if ( m != null ) {
  		List<String> instInfo = m.getInstInfo();
  		Logger.getLogger( instInfo.get(0) ).setLevel( Level.FINE );
  	}
  }
  
  private final void setTraceOff() {
  	for (String macroName: macroRegistry.getMacroNames()) {
  		Macro m = macroRegistry.getMacro( macroName );
  		if ( m != null ) {
  			List<String> instInfo = m.getInstInfo();
  			Logger.getLogger( instInfo.get(0) ).setLevel( Level.OFF );
  		}
  	}
  }

	/**
	 * mmp uses the Java logging mechanism for tracing.
	 * This method sets the trace handler for the Java logging
	 * to 'traceHandler'.
	 * 
	 * @param traceHandler the trace handler
	 */
  public final void setTraceHandler( Handler traceHandler ) {
  	settings.setTraceHandler( traceHandler );
  	initLogging();
  }

  /**
   * Defined macro 'macroName' to expand to 'expansion'.
   * 
   * @param macroName name of macro
   * @param expansion expansion of macro
   */
  public final void define( String macroName, String expansion ) {
  	if ( expansion == null ) {
  		expansion = "";
  	}
		// define and register user macro
		UserMacro userMacro = new UserMacro( macroName, expansion );
		this.getMacroRegistry().registerMacro( macroName, userMacro );
  }

	/**
	 * Registers the macro object 'macro' under the name 'macroName'.
	 * 
	 * @pre macroName != null && macro != null
	 * @param macroName the macro name
 	 * @param macro  the macro object
 	 * @see Macro Macro
	 */
  public final void register( String macroName, Macro macro ) {
		this.getMacroRegistry().registerMacro( macroName, macro );
  }

}
