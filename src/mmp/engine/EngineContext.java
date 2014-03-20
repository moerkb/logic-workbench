/** --------------------------------------------------------------------------
 * EngineContext gives macros access to the engine
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
 * $Id: EngineContext.java 755 2008-12-18 09:10:25Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.engine;

/**
 * The interface EngineContext gives macro objects access to the engine.
 * 
 * @author Burkhardt Renz
 *
 */
public interface EngineContext {
	
	/**
	 * Get input of macro processor.
	 * 
	 * @return Input
	 * @see mmp.engine.Input Input
	 */
	Input getInput();
	
	/**
	 * Get output of macro processor.
	 * 
	 * @return Output
	 * @see mmp.engine.Output Output
	 */
	Output getOutput();
	
	/**
	 * Get macro registry of macro processor.
	 * 
	 * @return MacroRegistry
	 * @see mmp.engine.MacroRegistry MacroRegistry
	 */
	MacroRegistry getMacroRegistry();
	
	/**
	 * Get settings of macro processor.
	 * 
	 * @return Settings
	 * @see mmp.engine.Settings Settings
	 */
	Settings getSettings();
	
	/**
	 * Get current recursion level of expansion of macro.
	 * 
	 * @return recursion level 
	 */
	int getRecursionLevel();

}
