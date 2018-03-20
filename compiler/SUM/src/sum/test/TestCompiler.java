package sum.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sum.ast.ast.ASTfactory;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;
import sum.parser.SUMParser;

public class TestCompiler {
	public static void main(String[] args) throws IOException {
		IASTfactory factory = new ASTfactory();
		SUMParser sp = new SUMParser(factory);
		IASTprogram p = sp.getProgram("test\\test2.sum");
		sum.compiler.Compiler c = new sum.compiler.Compiler();
		//c.visit(p);
		c.compile(p,"out\\test2.um");
		try {
			FileInputStream is = new FileInputStream(new File("out\\test2.um"));
			byte b[] = new byte[12];
			is.read(b);
			for(int i = 0; i < 12; i++) {
				//System.out.println(b[i]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
