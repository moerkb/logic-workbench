/* Generated By:JJTree: Do not edit this line. MicShiftExpression.java */

package mic.parser;

public class MicShiftExpression extends SimpleNode {
  public MicShiftExpression(int id) {
    super(id);
  }

  public MicShiftExpression(MicParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MicParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}