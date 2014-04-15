/** --------------------------------------------------------------------------
 * mic Calculator visits the tree and calculates the expression.
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
 * $Id: Calculator.java 733 2008-12-16 09:50:07Z brenz $
 * --------------------------------------------------------------------------
 */
package mic.visitor;

import mic.parser.MicAdditiveExpression;
import mic.parser.MicBAndExpression;
import mic.parser.MicBOrExpression;
import mic.parser.MicBXorExpression;
import mic.parser.MicEqualExpression;
import mic.parser.MicInteger;
import mic.parser.MicLAndExpression;
import mic.parser.MicLOrExpression;
import mic.parser.MicMultiplicativeExpression;
import mic.parser.MicParserConstants;
import mic.parser.MicParserVisitor;
import mic.parser.MicPowerExpression;
import mic.parser.MicRelationalExpression;
import mic.parser.MicShiftExpression;
import mic.parser.MicStart;
import mic.parser.MicUnaryExpression;
import mic.parser.SimpleNode;

/**
 * mic Calculator visits the tree and calculates the expression.
 * 
 * @author Burkhardt Renz
 *
 */
public class Calculator implements MicParserVisitor {
	
	
	@Override
	public Object visit( SimpleNode node, Object data ) {
		return null;
	}

	@Override
	public Object visit( MicStart node, Object data ) {
		// Start has exactly one node
		if ( node.jjtGetNumChildren() != 1 ) {
			throw new IllegalStateException( 
					"Expected 1 node in root of expression" );
		}
    return node.jjtGetChild(0).jjtAccept( this, null );
	}

  @Override
  public Object visit( MicLOrExpression node, Object data ) {
		// Logical or expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in logical or expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		if ( left != 0 ) { // shortcut
			return 1;
		}
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		return (right != 0) ? 1 : 0;
  }

  @Override
  public Object visit( MicLAndExpression node, Object data ) {
		// Logical and expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in logical and expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		if ( left == 0 ) { //shortcut
			return 0;
		}
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		return (right != 0) ? 1 : 0;
  }

  @Override
  public Object visit( MicBOrExpression node, Object data ) {
		// Binary or expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in binary or expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		return left | right;
  }

  @Override
  public Object visit( MicBXorExpression node, Object data ) {
		// Binary xor expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in binary xor expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		return left ^ right;
  }

  @Override
  public Object visit( MicBAndExpression node, Object data ) {
		// Binary and expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in binary and expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		return left & right;
  }

  @Override
  public Object visit( MicEqualExpression node, Object data ) {
		// Equal expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in equal expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 0;
		switch ( node.getOp() ) {
			case MicParserConstants.EQ:
				result = (left == right) ? 1 : 0;
				break;
			case MicParserConstants.NE:
				result = (left != right) ? 1 : 0;
				break;
			default:
				throw new IllegalStateException( 
						"Wrong operator in equal expression" );
		}
    return result;
  }

  @Override
  public Object visit( MicRelationalExpression node, Object data ) {
		// Relational expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in relational expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 0;
		switch ( node.getOp() ) {
			case MicParserConstants.LT:
				result = (left < right) ? 1 : 0;
				break;
			case MicParserConstants.LE:
				result = (left <= right) ? 1 : 0;
				break;
			case MicParserConstants.GT:
				result = (left > right) ? 1 : 0;
				break;
			case MicParserConstants.GE:
				result = (left >= right) ? 1 : 0;
				break;
			default:
				throw new IllegalStateException( 
						"Wrong operator in relational expression" );
		}
    return result;
  }

  @Override
  public Object visit( MicShiftExpression node, Object data ) {
		// Shift expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in shift expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 0;
		switch ( node.getOp() ) {
			case MicParserConstants.LSHIFT:
				result = left << right;
				break;
			case MicParserConstants.RSHIFT:
				result = left >> right;
				break;
			default:
				throw new IllegalStateException( 
						"Wrong operator in shift expression" );
		}
    return result;
  }

  @Override
  public Object visit( MicAdditiveExpression node, Object data ) {
		// Additive expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in additive expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 0;
		switch ( node.getOp() ) {
			case MicParserConstants.PLUS:
				result = left + right;
				break;
			case MicParserConstants.MINUS:
				result = left - right;
				break;
			default:
				throw new IllegalStateException( 
						"Wrong operator in additive expression" );
		}
    return result;
  }

  @Override
  public Object visit( MicMultiplicativeExpression node, Object data ) {
		// Multiplicative expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in multiplicative expression" );
		}
		int left = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int right = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 0;
		switch ( node.getOp() ) {
			case MicParserConstants.MUL:
				result = left * right;
				break;
			case MicParserConstants.DIV:
				result = left / right;
				break;
			case MicParserConstants.MOD:
				result = left % right;
				break;
			default:
				throw new IllegalStateException( 
						"Wrong operator in multiplicative expression" );
		}
    return result;
  }
  
  @Override
  public Object visit( MicPowerExpression node, Object data ) {
		// Power expression has exactly two nodes
		if ( node.jjtGetNumChildren() != 2 ) {
			throw new IllegalStateException( 
					"Expected 2 nodes in power expression" );
		}
		int base = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		int exponent = (Integer)node.jjtGetChild(1).jjtAccept( this, null );
		int result = 1;
		if ( exponent < 0 ) {
			throw new ArithmeticException( 
					"Expected positive exponent in power expression" );
		}
		result = 1;
		for ( int i = 0; i < exponent; i++) {
			result = result * base;
		}
    return result;
  }
  
  @Override
  public Object visit( MicUnaryExpression node, Object data ) {
		// Unary expression has exactly one node
		if ( node.jjtGetNumChildren() != 1 ) {
			throw new IllegalStateException( 
					"Expected 1 node in unary expression" );
		}
		
		// if the operator is MINUS we check the child to allow MIN_INT
		if ( node.getOp() == MicParserConstants.MINUS ) {
			if ( node.jjtGetChild(0) instanceof MicInteger ) {
				String lexeme = "-" + 
					((SimpleNode)node.jjtGetChild(0)).getLexeme();
			  return Integer.decode( lexeme );
			}  
		}
		
		int result = (Integer)node.jjtGetChild(0).jjtAccept( this, null );
		switch ( node.getOp() ) {
			case MicParserConstants.PLUS:
				break;
			case MicParserConstants.MINUS:
				result = -result;
				break;
			case MicParserConstants.BNEG:
				result = ~result;
				break;
			case MicParserConstants.LNEG:
				result = (result == 0) ? 1 : 0;
				break;
		}
		return result;
  }

  @Override
  public Object visit( MicInteger node, Object data ) {
  		return Integer.decode( node.getLexeme() );
  }
}
