/** --------------------------------------------------------------------------
 * Registry for macros
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
 * $Id: MacroRegistry.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
//import java.util.logging.Logger;


/**
 * The macroRegistry holds stacks of macros identified by name.
 * <br/><br/>
 * The macroRegistry is a container for builtin and user defined macros.
 * The container for the active macros is organized as a collection of 
 * stacks of macros.
 * <br/>
 * Builtin macros are furthermore stored in a second registry. If a builtin
 * macro gets undefined, it can nevertheless by called by the 'builtin' macro. 
 * 
 * @author Burkhardt Renz
 *
 */
public class MacroRegistry {
	
	//private static Logger logger = Logger.getLogger( MacroRegistry.class.getName() );
	
	// invariant: if there is a macro stack for a macro name, it is never empty
	private Map<String, Stack<Macro>> macroRegistry = new HashMap<String, Stack<Macro>>();
	private Map<String, Macro> builtinRegistry = new HashMap<String, Macro>();
	
	/**
	 * Register macro object as macro 'name'.
	 * <br/>
	 * If there is already a macro with this name, 
	 * the top definition is replaced.
	 * 
	 * @param name name of macro.
	 * @param macro macro object.
	 */
	public final void registerMacro( String name, Macro macro ) {
  	if ( macroRegistry.containsKey(name) ) {
  		// replace the top macro
  		Stack<Macro> macStack = macroRegistry.get( name );
  		macStack.pop();
  		macStack.push(  macro );
  	} else {
  		Stack<Macro> macStack = new Stack<Macro>();
  		macStack.push(  macro  );
  		macroRegistry.put( name, macStack );
  	}
  }
	
	/**
	 * Pushes macro object on the top of an already defined macro 
	 * with the same name. <br/>
	 * If there is no macro with the given name, the macro object is registered.
	 * 
	 * @param name name of macro
	 * @param macro macro object
	 */
	public final void pushMacro( String name, Macro macro ) {
  	if ( macroRegistry.containsKey(name) ) {
  		// push macro
  		Stack<Macro> macStack = macroRegistry.get( name );
  		macStack.push(  macro );
  	} else {
  		Stack<Macro> macStack = new Stack<Macro>();
  		macStack.push(  macro  );
  		macroRegistry.put( name, macStack );
  	}
  }

	/**
	 * The macro named 'name' is unregistered. If there are several macros
	 * on the stack with this name, they are <i>all</i> deleted from the
	 * registry.
	 * 
	 * @param name the name of the macro to unregister.
	 */
	public final void unregisterMacro( String name ) {
  	macroRegistry.remove( name );
  }
	
  /**
   * The macro named 'name' is popped from the stack of macros. 
   * 
   * @param name name of the macro to be popped.
   */
  public final void popMacro( String name ) {
  	if ( macroRegistry.containsKey(name) ) {
  		// pop macro
  		Stack<Macro> macStack = macroRegistry.get( name );
  		macStack.pop();
  		// remove macro if stack is empty
  		if ( macStack.isEmpty() ) {
  			macroRegistry.remove( name );
  		}	
  	}	
  	return;
  }

	/**
	 * Get macro named 'name'. The result is the top macro if there are several 
	 * definitions with the same name.
	 * 
	 * @param name name of macro to be found.
	 * @return macro object, null if not found.
	 */
	public final Macro getMacro( String name ) {
  	Stack<Macro> macStack =  macroRegistry.get( name );
  	if ( macStack != null ) {
  		// found
  		return macStack.peek();
  	} else {
  		return null;
  	}
  }
	
	/**
	 * Get the set of all names of currently defined macros.
	 * 
	 * @return the set of names of macros.
	 */
	public final Set<String> getMacroNames() {
		return macroRegistry.keySet();
	}

	final void registerBuiltin( String name, Macro builtin ) {
		builtinRegistry.put( name, builtin );
		registerMacro( name, builtin );
	}

  /**
   * Gets the builtin macro named 'name', even if it has been
   * undefined.
   * 
   * @param name name of builtin to find
   * @return builtin macro object
   */
  public final Macro getBuiltin( String name ) {
  	return builtinRegistry.get( name );
  }

}
