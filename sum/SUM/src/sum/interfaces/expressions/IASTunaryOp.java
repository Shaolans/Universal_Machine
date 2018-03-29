package sum.interfaces.expressions;

import sum.interfaces.iast.IASTexpression;

public interface IASTunaryOp extends IASTexpression {
	public IASTexpression getArg();
}
