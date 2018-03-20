package sum.ast.expressions;

import sum.interfaces.expressions.IASTdiv;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTdiv extends ASTbinOp implements IASTdiv{

	public ASTdiv(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
