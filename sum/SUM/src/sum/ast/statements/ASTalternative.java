package sum.ast.statements;

import java.util.List;

import sum.ast.ast.ASTstatement;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTstatement;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTalternative;

public class ASTalternative extends ASTstatement implements IASTalternative{
	private IASTexpression condition;
	private List<IASTstatement> consequence;
	private List<IASTstatement> alternative;
	
	
	public ASTalternative(IASTexpression condition, List<IASTstatement> consequence, List<IASTstatement> alternative) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternative = alternative;
	}
	
	@Override
	public IASTexpression getCondition() {
		return condition;
	}

	@Override
	public List<IASTstatement> getConsequence() {
		return consequence;
	}

	@Override
	public List<IASTstatement> getAlternative() {
		return alternative;
	}

	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}

}
