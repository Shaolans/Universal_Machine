package sum.interfaces.statements;

import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTstatement;

public interface IASTbinding extends IASTstatement {
	public String getName();
	public IASTexpression getExpression();
}
