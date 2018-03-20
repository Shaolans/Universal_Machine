package sum.interfaces.statements;

import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTstatement;

public interface IASTalternative extends IASTstatement {
	public IASTexpression getCondition();
	public IASTstatement getConsequence();
	public IASTstatement getAlternative();
}
