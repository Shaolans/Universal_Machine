package sum.test;

import sum.ast.ast.ASTfactory;
import sum.interfaces.expressions.IASTconstInteger;
import sum.interfaces.expressions.IASTgt;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;
import sum.interfaces.statements.IASTalternative;
import sum.parser.SUMParser;

public class TestParser {

	public static void main(String[] args) {
		IASTfactory factory = new ASTfactory();
		SUMParser sp = new SUMParser(factory);
		IASTprogram p = sp.getProgram("Test\\test1.sum");
		IASTalternative alt = (IASTalternative)p.getProgram().get(0);
		IASTconstInteger i = (IASTconstInteger)((IASTgt)alt.getCondition()).getArg2();
		System.out.println(i.getInteger());
	}

}
