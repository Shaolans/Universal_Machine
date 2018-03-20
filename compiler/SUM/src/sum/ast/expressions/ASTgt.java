package sum.ast.expressions;

import sum.interfaces.expressions.IASTgt;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTgt extends ASTbinOp implements IASTgt{

	public ASTgt(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
