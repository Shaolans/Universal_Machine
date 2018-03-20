package sum.ast.ast;

import java.util.List;

import sum.ast.expressions.ASTadd;
import sum.ast.expressions.ASTand;
import sum.ast.expressions.ASTconstInteger;
import sum.ast.expressions.ASTconstString;
import sum.ast.expressions.ASTdiv;
import sum.ast.expressions.ASTeq;
import sum.ast.expressions.ASTgt;
import sum.ast.expressions.ASTident;
import sum.ast.expressions.ASTlt;
import sum.ast.expressions.ASTmul;
import sum.ast.expressions.ASTnot;
import sum.ast.expressions.ASTor;
import sum.ast.statements.ASTalternative;
import sum.ast.statements.ASTbinding;
import sum.ast.statements.ASTprint;
import sum.ast.statements.ASTscan;
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;
import sum.interfaces.iast.IASTstatement;

public class ASTfactory implements IASTfactory{

	@Override
	public IASTprogram newProgram(List<IASTstatement> statements) {
		return new ASTprogram(statements);
	}

	@Override
	public IASTstatement newAlternative(IASTexpression cond, IASTstatement cons, IASTstatement alt) {
		return new ASTalternative(cond, cons, alt);
	}

	@Override
	public IASTstatement newBinding(String name, IASTexpression expr) {
		return new ASTbinding(name, expr);
	}

	@Override
	public IASTstatement newPrint(IASTstatement stmt) {
		return new ASTprint(stmt);
	}

	@Override
	public IASTstatement newScan(String name) {
		return new ASTscan(name);
	}

	@Override
	public IASTexpression newAdd(IASTexpression arg1, IASTexpression arg2) {
		return new ASTadd(arg1, arg2);
	}

	@Override
	public IASTexpression newAnd(IASTexpression arg1, IASTexpression arg2) {
		return new ASTand(arg1, arg2);
	}

	@Override
	public IASTexpression newDiv(IASTexpression arg1, IASTexpression arg2) {
		return new ASTdiv(arg1, arg2);
	}

	@Override
	public IASTexpression newMul(IASTexpression arg1, IASTexpression arg2) {
		return new ASTmul(arg1, arg2);
	}

	@Override
	public IASTexpression newEq(IASTexpression arg1, IASTexpression arg2) {
		return new ASTeq(arg1, arg2);
	}

	@Override
	public IASTexpression newGt(IASTexpression arg1, IASTexpression arg2) {
		return new ASTgt(arg1, arg2);
	}

	@Override
	public IASTexpression newLt(IASTexpression arg1, IASTexpression arg2) {
		return new ASTlt(arg1, arg2);
	}

	@Override
	public IASTexpression newOr(IASTexpression arg1, IASTexpression arg2) {
		return new ASTor(arg1, arg2);
	}

	@Override
	public IASTexpression newNot(IASTexpression arg) {
		return new ASTnot(arg);
	}

	@Override
	public IASTexpression newString(String s) {
		return new ASTconstString(s);
	}

	@Override
	public IASTexpression newIdent(String s) {
		return new ASTident(s);
	}

	@Override
	public IASTexpression newInt(int i) {
		return new ASTconstInteger(i);
	}

}
