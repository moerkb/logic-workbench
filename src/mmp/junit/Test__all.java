/** --------------------------------------------------------------------------
 * Test suite for mmp
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
 * $Id: Test__all.java 381 2008-07-14 04:16:03Z brenz $
 * --------------------------------------------------------------------------
 */
package mmp.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author Burkhardt Renz
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  Test_Args.class,
  Test_builtin.class,
  Test_changecom.class,
  Test_changequote.class,
  Test_define.class,
  Test_defn.class,
  Test_divert.class,
  Test_divnum.class,
  Test_dnl.class,
  Test_dumpdef.class,
  Test_eval.class,
  Test_errprint.class,
  Test_examples.class,
  Test_forloop.class,
  Test_format.class,
  Test_ifdef.class,
  Test_ifelse.class,
  Test_improved.class,
  Test_include.class,
  Test_indir.class,
  Test_inhibiting.class,
  Test_instinfo.class,
  Test_instreg.class,
  Test_len.class,
  Test_m4exit.class,
  Test_m4wrap.class,
  Test_patsubst.class,
  Test_pushdef.class,
  Test_regexp.class,
  Test_shift.class,
  Test_specialArgs.class,
  Test_substr.class,
  Test_trace.class,
  Test_translit.class,
  Test_undefine.class,
  Test_undivert.class
})
public class Test__all {

}
