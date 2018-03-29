package sum.ast.expressions;

import sum.interfaces.expressions.IASTor;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTor extends ASTbinOp implements IASTor{

	public ASTor(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
	}

	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}
}
