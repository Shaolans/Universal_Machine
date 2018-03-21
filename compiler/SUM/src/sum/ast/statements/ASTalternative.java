package sum.ast.statements;

import sum.ast.ast.ASTstatement;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTstatement;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTalternative;

public class ASTalternative extends ASTstatement implements IASTalternative{
	private IASTexpression condition;
	private IASTstatement consequence;
	private IASTstatement alternative;
	
	
	public ASTalternative(IASTexpression condition, IASTstatement consequence, IASTstatement alternative) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternative = alternative;
	}
	
	@Override
	public IASTexpression getCondition() {
		return condition;
	}

	@Override
	public IASTstatement getConsequence() {
		return consequence;
	}

	@Override
	public IASTstatement getAlternative() {
		return alternative;
	}

	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}

}
