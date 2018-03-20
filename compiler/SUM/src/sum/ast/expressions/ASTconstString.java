package sum.ast.expressions;

import sum.ast.statements.ASTexpression;
import sum.interfaces.expressions.IASTconstString;
import sum.interfaces.iast.IASTvisitor;

public class ASTconstString extends ASTexpression implements IASTconstString{
	private String value;
	
	public ASTconstString(String value) {
		this.value = value;
	}
	
	public String getString() {
		return value;
	}
	
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
