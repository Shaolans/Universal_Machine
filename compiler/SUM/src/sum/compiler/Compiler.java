package sum.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import sum.interfaces.iast.IASTexpression;
import sum.interfaces.iast.IASTprogram;
import sum.interfaces.iast.IASTstatement;
import sum.interfaces.iast.IASTvisitor;
import sum.interfaces.statements.IASTalternative;
import sum.interfaces.statements.IASTbinding;
import sum.interfaces.statements.IASTprint;
import sum.interfaces.statements.IASTscan;

public class Compiler implements IASTvisitor {
	private static final boolean print = false;
	private int indVar;
	private Map<String, Integer> env;
	private FileOutputStream fos;
	
	public Compiler() {
		env = new HashMap<>();
		indVar = 1;
		fos = null;
	}
	
	public void compile(IASTprogram iast, String file) {
		try {
			fos = new FileOutputStream(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.visit(iast);
		
		writeOperation(7, 0, 0, 0);
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeOperation(int op, int regA, int regB, int regC) {
		byte operation[] = new byte[4];
		operation[0] = (byte)(op << 4);
		operation[1] = 0;
		operation[2] = (byte)((regA & 0x00000004)==0?0:1);
		byte val = 0;
		val += (byte)((regC & 0x00000001)==0?0:1);
		val += (byte)((regC & 0x00000002)==0?0:2);
		val += (byte)((regC & 0x00000004)==0?0:4);
		val += (byte)((regB & 0x00000001)==0?0:8);
		val += (byte)((regB & 0x00000002)==0?0:16);
		val += (byte)((regB & 0x00000004)==0?0:32);
		val += (byte)((regA & 0x00000001)==0?0:64);
		val += (byte)((regA & 0x00000002)==0?0:128);
		operation[3] = val;
		try {
			fos.write(operation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeSpecialOperation(int regA, int value) {
		byte operation[] = new byte[4];
		byte val = 0;
		val = (byte)(CompilerInstruction.ORTHOGRAPHY << 4);
		val += (byte)((regA & 0x00000004)==0?0:8);
		val += (byte)((regA & 0x00000002)==0?0:4);
		val += (byte)((regA & 0x00000001)==0?0:2);
		val += (byte)((value & 0x01000000)==0?0:1);
		operation[0] = val;
		operation[1] = (byte)(value & 0x00FF0000);
		operation[2] = (byte)(value & 0x0000FF00);
		operation[3] = (byte)(value & 0x000000FF);
		try {
			fos.write(operation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void visit(IASTprogram iast) {
		
		for(IASTstatement stmts: iast.getProgram()) {
			if(print) System.out.println("Stmts du programme");
			stmts.accept(this);
		}
		
	}

	@Override
	public void visit(IASTalternative iast) {
		if(print) System.out.println("If");
		iast.getCondition().accept(this);
		iast.getConsequence().accept(this);
		iast.getAlternative().accept(this);
	}

	@Override
	public void visit(IASTbinding iast) {
		if(print) System.out.println("Binding");
		iast.getExpression().accept(this);
	}

	@Override
	public void visit(IASTprint iast) {
		if(print) System.out.println("Print");
		
		IASTstatement expr = iast.getArg();
		
		if(expr instanceof IASTconstString) {
			String s = ((IASTconstString)expr).getString();
			for(int i = 0; i < s.length(); i++) {
				if(i+1 < s.length() && s.charAt(i)=='\\' && s.charAt(i+1)=='n') {
					writeSpecialOperation(CompilerInstruction.COND_MOV, (int)'\n');
					writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
					i++;
				}else {
					writeSpecialOperation(CompilerInstruction.COND_MOV, (int)s.charAt(i));
					writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
				}
				
			}
			
		} else if(expr instanceof IASTconstInteger) {
			String s = Integer.toString(((IASTconstInteger)expr).getInteger());
			for(int i = 0; i < s.length(); i++) {
				writeSpecialOperation(CompilerInstruction.COND_MOV, (int)s.charAt(i));
				writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
			}
			
		} else if (expr instanceof IASTexpression) {
			
		}
	}

	@Override
	public void visit(IASTscan iast) {
		if(print) System.out.println("Scan: "+iast.getName());
		
		
		
	}

	@Override
	public void visit(IASTadd iast) {
		if(print) System.out.println("Add");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTmul iast) {
		if(print) System.out.println("Mul");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTdiv iast) {
		if(print) System.out.println("Div");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTeq iast) {
		if(print) System.out.println("=");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
	}

	@Override
	public void visit(IASTgt iast) {
		if(print) System.out.println(">");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTlt iast) {
		if(print) System.out.println("<");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTor iast) {
		if(print) System.out.println("Or");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
	}

	@Override
	public void visit(IASTand iast) {
		if(print) System.out.println("And");
		iast.getArg1().accept(this);
		iast.getArg2().accept(this);
		
	}

	@Override
	public void visit(IASTnot iast) {
		if(print) System.out.println("Not");
		iast.getArg().accept(this);
	}

	@Override
	public void visit(IASTconstInteger iast) {
		if(print) System.out.println("Integer");
	}

	@Override
	public void visit(IASTconstString iast) {
		if(print) System.out.println("String");
		
	}

	@Override
	public void visit(IASTident iast) {
		if(print) System.out.print("Ident: "+iast.getName());
		
	}

}
