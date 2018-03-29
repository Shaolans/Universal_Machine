package sum.ast.expressions;

import sum.ast.statements.ASTexpression;
import sum.interfaces.expressions.IASTunaryOp;
import sum.interfaces.iast.IASTexpression;

public abstract class ASTunaryOp extends ASTexpression implements IASTunaryOp {
	private IASTexpression arg;
	
	public ASTunaryOp(IASTexpression arg) {
		this.arg = arg;
	}
	
	public IASTexpression getArg() {
		return arg;
	}
	
}
