/* Generated By:JJTree: Do not edit this line. MicBOrExpression.java */

package mic.parser;

public class MicBOrExpression extends SimpleNode {
  public MicBOrExpression(int id) {
    super(id);
  }

  public MicBOrExpression(MicParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MicParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
