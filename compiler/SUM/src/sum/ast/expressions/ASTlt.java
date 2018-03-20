package sum.ast.expressions;

import sum.interfaces.expressions.IASTlt;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;

public class ASTlt extends ASTbinOp implements IASTlt{

	public ASTlt(IASTexpression arg1, IASTexpression arg2) {
		super(arg1, arg2);
	}
	
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}

}
