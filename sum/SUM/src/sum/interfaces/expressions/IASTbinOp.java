package sum.interfaces.expressions;

import sum.interfaces.iast.IASTexpression;

public interface IASTbinOp extends IASTexpression {
	public IASTexpression getArg1();
	public IASTexpression getArg2();
}
