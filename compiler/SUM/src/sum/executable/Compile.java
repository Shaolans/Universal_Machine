package sum.executable;

import sum.ast.ast.ASTfactory;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;
import sum.parser.SUMParser;

public class Compile {

	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("java -jar <intpufile.sum> <outputfile.um> [--verbose]||[--verbose-asm]");
		}
		
		IASTfactory factory = new ASTfactory();
		SUMParser sp = new SUMParser(factory);
		IASTprogram p = sp.getProgram(args[0]);
		sum.compiler.Compiler c = new sum.compiler.Compiler();
		c.setPrintTree(false);
		c.setAsmTrace(false);
		if(args.length == 3) {
			if(args[2].equals("--verbose")) {
				c.setPrintTree(true);
			}
			if(args[2].equals("--verbose-asm")) {
				c.setAsmTrace(true);
			}
		}
		c.compile(p,args[1]);
		System.out.println("COMPILATION COMPLETE");
	}

}
