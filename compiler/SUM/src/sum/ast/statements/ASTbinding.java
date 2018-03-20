package sum.ast.statements;

import sum.ast.ast.ASTstatement;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTbinding;

public class ASTbinding extends ASTstatement implements IASTbinding {
	private String name;
	private IASTexpression expr;
	
	public ASTbinding(String name, IASTexpression expr) {
		this.name = name;
		this.expr = expr;
	}
	
	public String getName() {
		return name;
	}
	public IASTexpression getExpression() {
		return expr;
	}
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
