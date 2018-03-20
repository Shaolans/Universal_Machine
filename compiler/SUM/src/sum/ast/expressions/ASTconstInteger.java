package sum.ast.expressions;

import sum.ast.statements.ASTexpression;
import sum.interfaces.expressions.IASTconstInteger;
import sum.interfaces.iast.IASTvisitor;

public class ASTconstInteger extends ASTexpression implements IASTconstInteger{
	private int value;
	
	public ASTconstInteger(int value) {
		this.value = value;
	}
	
	public int getInteger() {
		return value;
	}
	
	@Override
	public void accept(IASTvisitor visitor) {
		visitor.visit(this);
	}
}
