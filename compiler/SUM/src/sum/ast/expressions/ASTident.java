package sum.ast.expressions;

import sum.ast.statements.ASTexpression;
import sum.interfaces.expressions.IASTident;
import sum.interfaces.iast.IASTvisitor;

public class ASTident extends ASTexpression implements IASTident {
	private String name;
	
	public ASTident(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
