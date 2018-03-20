package sum.ast.expressions;

import sum.ast.statements.ASTexpression;
import sum.interfaces.expressions.IASTbinOp;
import sum.interfaces.iast.IASTexpression;

public abstract class ASTbinOp extends ASTexpression implements IASTbinOp{
	private IASTexpression arg1;
	private IASTexpression arg2;
	
	public ASTbinOp(IASTexpression arg1, IASTexpression arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public IASTexpression getArg1() {
		return arg1;
	}
	
	public IASTexpression getArg2() {
		return arg2;
	}
	

}

