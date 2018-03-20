package sum.interfaces.iast;

import java.util.List;

public interface IASTfactory {
	public IASTprogram newProgram(List<IASTstatement> statements);
	
	public IASTstatement newAlternative(IASTexpression cond, IASTstatement cons, IASTstatement alt);
	public IASTstatement newBinding(String name, IASTexpression expr);
	public IASTstatement newPrint(IASTstatement stmt);
	public IASTstatement newScan(String name);
	
	public IASTexpression newAdd(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newAnd(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newDiv(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newMul(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newEq(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newGt(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newLt(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newOr(IASTexpression arg1, IASTexpression arg2);
	public IASTexpression newNot(IASTexpression arg);
	
	public IASTexpression newString(String s);
	public IASTexpression newIdent(String s);
	public IASTexpression newInt(int i);
}
