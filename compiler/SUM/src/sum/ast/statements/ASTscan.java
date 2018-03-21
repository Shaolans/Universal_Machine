package sum.ast.statements;

import sum.ast.ast.ASTstatement;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTscan;

public class ASTscan extends ASTstatement implements IASTscan {
	private String name;
	
	public ASTscan(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	@Override
	public void accept(IASTvisitor visitor, int context) {
		visitor.visit(this, context);
	}
}
