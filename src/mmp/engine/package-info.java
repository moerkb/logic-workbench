/**
 * Provides the engine of the MNI macro processor mmp.
 * <br/><br/>
 * The class {@linkplain mmp.engine.Engine} contains the machine that processes macros.
 * <br/><br/>
 * The interface {@linkplain mmp.engine.EngineContext} gives the programmer of Java macros
 * access to the current state of the engine, i.e. {@linkplain mmp.engine.Input},
 * {@linkplain mmp.engine.Output}, {@linkplain mmp.engine.MacroRegistry} and the
 * {@linkplain mmp.engine.Settings}.
 * <br/><br/>
 * To program a Java macro, one must extend {@linkplain mmp.engine.Macro}, implementing
 * {@linkplain mmp.engine.Macro#call(java.util.List, EngineContext)} and respect the contract
 * of {@linkplain mmp.engine.Macro}. 
 */ 
package mmp.engine;
/* 
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
 * $Id: package-info.java 338 2008-07-01 08:06:00Z brenz $
 * --------------------------------------------------------------------------
 */