package sum.ast.expressions;

import sum.interfaces.expressions.IASTnot;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTnot extends ASTunaryOp implements IASTnot{

	public ASTnot(IASTexpression arg) {
		super(arg);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}
}
