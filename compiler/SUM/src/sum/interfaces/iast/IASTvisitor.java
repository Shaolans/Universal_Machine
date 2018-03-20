package sum.interfaces.iast;


import sum.interfaces.expressions.IASTadd;
import sum.interfaces.expressions.IASTand;
import sum.interfaces.expressions.IASTconstInteger;
import sum.interfaces.expressions.IASTconstString;
import sum.interfaces.expressions.IASTdiv;
import sum.interfaces.expressions.IASTeq;
import sum.interfaces.expressions.IASTgt;
import sum.interfaces.expressions.IASTident;
import sum.interfaces.expressions.IASTlt;
import sum.interfaces.expressions.IASTmul;
import sum.interfaces.expressions.IASTnot;
import sum.interfaces.expressions.IASTor;
import sum.interfaces.statements.IASTalternative;
import sum.interfaces.statements.IASTbinding;
import sum.interfaces.statements.IASTprint;
import sum.interfaces.statements.IASTscan;

public interface IASTvisitor {
	public void visit(IASTprogram iast);
	public void visit(IASTalternative iast);
	public void visit(IASTbinding iast);
	public void visit(IASTprint iast);
	public void visit(IASTscan iast);
	
	public void visit(IASTadd iast);
	public void visit(IASTmul iast);
	public void visit(IASTdiv iast);
	public void visit(IASTeq iast);
	public void visit(IASTgt iast);
	public void visit(IASTlt iast);
	public void visit(IASTor iast);
	public void visit(IASTand iast);
	public void visit(IASTnot iast);
	
	public void visit(IASTconstInteger iast);
	public void visit(IASTconstString iast);
	public void visit(IASTident iast);
}
