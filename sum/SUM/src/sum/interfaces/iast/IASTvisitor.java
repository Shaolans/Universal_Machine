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
	public void visit(IASTprogram iast, int context);
	public void visit(IASTalternative iast, int context);
	public void visit(IASTbinding iast, int context);
	public void visit(IASTprint iast, int context);
	public void visit(IASTscan iast, int context);
	
	public void visit(IASTadd iast, int context);
	public void visit(IASTmul iast, int context);
	public void visit(IASTdiv iast, int context);
	public void visit(IASTeq iast, int context);
	public void visit(IASTgt iast, int context);
	public void visit(IASTlt iast, int context);
	public void visit(IASTor iast, int context);
	public void visit(IASTand iast, int context);
	public void visit(IASTnot iast, int context);
	
	public void visit(IASTconstInteger iast, int context);
	public void visit(IASTconstString iast, int context);
	public void visit(IASTident iast, int context);
}
