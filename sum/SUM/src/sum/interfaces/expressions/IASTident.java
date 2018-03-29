package sum.interfaces.expressions;

import sum.interfaces.iast.IASTexpression;

public interface IASTident extends IASTexpression {
	public String getName();
}
