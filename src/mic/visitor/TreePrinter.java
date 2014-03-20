/** --------------------------------------------------------------------------
 * mic TreePrinter prints the parse tree.
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
 * $Id: TreePrinter.java 733 2008-12-16 09:50:07Z brenz $
 * --------------------------------------------------------------------------
 */
package mic.visitor;

import java.io.PrintWriter;
import java.io.Writer;

import mic.parser.MicAdditiveExpression;
import mic.parser.MicBAndExpression;
import mic.parser.MicBOrExpression;
import mic.parser.MicBXorExpression;
import mic.parser.MicEqualExpression;
import mic.parser.MicInteger;
import mic.parser.MicLAndExpression;
import mic.parser.MicLOrExpression;
import mic.parser.MicMultiplicativeExpression;
import mic.parser.MicParserVisitor;
import mic.parser.MicPowerExpression;
import mic.parser.MicRelationalExpression;
import mic.parser.MicShiftExpression;
import mic.parser.MicStart;
import mic.parser.MicUnaryExpression;
import mic.parser.SimpleNode;

/**
 * mic TreePrinter prints the parse tree.
 * 
 * @author Burkhardt Renz
 */
public class TreePrinter implements MicParserVisitor {
	
	private int indent = 0;
	private PrintWriter out;
	
  private String indentString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < indent; ++i) {
      sb.append( " " );
    }
    return sb.toString();
  }  
  
  /**
   * Constructor that gives the tree printer a destination for output.
   * 
   * @param writer where to write the output.
   */
  public TreePrinter( Writer writer ) {
  	this.out = new PrintWriter( writer );
  }

  @Override
	public Object visit( SimpleNode node, Object data ) {
		return null;
	}
  
	private Object process( SimpleNode node, Object data ) {
    out.println( indentString() + node );
    ++indent;
    data = node.childrenAccept( this, data );
    --indent;
    return data;
	}

	@Override
	public Object visit( MicStart node, Object data ) {
		return process( node, data );
	}

  @Override
  public Object visit( MicInteger node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicLOrExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicLAndExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicBOrExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicBXorExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicBAndExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicEqualExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicRelationalExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicShiftExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicAdditiveExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicMultiplicativeExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicUnaryExpression node, Object data ) {
		return process( node, data );
  }

  @Override
  public Object visit( MicPowerExpression node, Object data ) {
		return process( node, data );
  }
}
