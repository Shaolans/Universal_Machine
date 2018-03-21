package sum.interfaces.statements;

import java.util.List;

import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTstatement;

public interface IASTalternative extends IASTstatement {
	public IASTexpression getCondition();
	public List<IASTstatement> getConsequence();
	public List<IASTstatement> getAlternative();
}
