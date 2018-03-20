package sum.ast.expressions;

import sum.interfaces.expressions.IASTadd;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTadd extends ASTbinOp implements IASTadd{

	public ASTadd(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
	}

	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
