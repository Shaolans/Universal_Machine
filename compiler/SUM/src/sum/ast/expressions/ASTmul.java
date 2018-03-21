package sum.ast.expressions;

import sum.interfaces.expressions.IASTmul;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTmul extends ASTbinOp implements IASTmul{

	public ASTmul(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}
}
