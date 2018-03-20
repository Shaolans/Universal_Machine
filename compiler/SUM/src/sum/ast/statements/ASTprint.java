package sum.ast.statements;

import sum.ast.ast.ASTstatement;
import sum.interfaces.iast.IASTstatement;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTprint;

public class ASTprint extends ASTstatement implements IASTprint{
	private IASTstatement arg;
	
	public ASTprint(IASTstatement arg) {
		this.arg = arg;
	}
	
	public IASTstatement getArg() {
		return arg;
	}
	
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
