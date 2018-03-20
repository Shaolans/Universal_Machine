package sum.ast.ast;

import java.util.List;

import sum.interfaces.iast.IASTprogram;
import sum.interfaces.iast.IASTstatement;
import sum.interfaces.iast.IASTvisitor;

public class ASTprogram implements IASTprogram {
	private List<IASTstatement> statements;
	
	
	public ASTprogram(List<IASTstatement> statements) {
		this.statements = statements;
	}
	
	@Override
	public List<IASTstatement> getProgram() {
		return statements;
	}

	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
	
}
