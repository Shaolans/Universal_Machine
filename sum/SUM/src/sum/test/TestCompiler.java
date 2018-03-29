package sum.test;


import sum.ast.ast.ASTfactory;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;
import sum.parser.SUMParser;

public class TestCompiler {
	public static void main(String[] args) {
		IASTfactory factory = new ASTfactory();
		SUMParser sp = new SUMParser(factory);
		for(int i = 0; i < 18; i++) {
			IASTprogram p = sp.getProgram("test\\test"+i+".sum");
			sum.compiler.Compiler c = new sum.compiler.Compiler();
			c.compile(p,"out\\test"+i+".um");
		}
		
	}
}
