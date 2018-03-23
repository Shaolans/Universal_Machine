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
	private static boolean printtree = false;
	private static boolean asmtrace = true;
	private static final int NO_CONTEXT = -1;
	private int numInst;
	private int indVar;
	private Map<String, Integer> env;
	private FileOutputStream fos;
	
	public Compiler() {
		env = new HashMap<>();
		indVar = 1;
		fos = null;
		numInst = 0;
	}
	
	public void compile(IASTprogram iast, String file) {
		try {
			fos = new FileOutputStream(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.visit(iast, NO_CONTEXT);
		
		
		writeOperation(CompilerInstruction.HALT, 0, 0, 0);
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeOperation(int op, int regA, int regB, int regC) {
		if(op == CompilerInstruction.ARRAY_INDEX && asmtrace) System.out.println("REG["+regA+"] = "+"Array[REG["+regB+"][REG["+regC+"]]");
		if(op == CompilerInstruction.ARRAY_AMEND && asmtrace) System.out.println("Array[REG["+regA+"][REG["+regB+"]] = REG["+regC+"]");
		if(op == CompilerInstruction.ADD && asmtrace) System.out.println("REG["+regA+"] = "+"REG["+regB+"] + REG["+regC+"]");
		if(op == CompilerInstruction.MUL && asmtrace) System.out.println("REG["+regA+"] = "+"REG["+regB+"] * REG["+regC+"]");
		if(op == CompilerInstruction.DIV && asmtrace) System.out.println("REG["+regA+"] = "+"REG["+regB+"] / REG["+regC+"]");
		if(op == CompilerInstruction.ALLOCATION && asmtrace) System.out.println("ALLOC "+(indVar-1)+" SIZE REG["+regC+"]");
		if(op == CompilerInstruction.OUTPUT && asmtrace) System.out.println("PRINT REG["+regC+"]");
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
		numInst++;
	}
	
	public void writeSpecialOperation(int regA, int value) {
		if(asmtrace) System.out.println("CHARGE REG["+regA+"] = "+value);
		byte operation[] = new byte[4];
		byte val = 0;
		val = (byte)(CompilerInstruction.ORTHOGRAPHY << 4);
		val += (byte)((regA & 0x00000004)==0?0:8);
		val += (byte)((regA & 0x00000002)==0?0:4);
		val += (byte)((regA & 0x00000001)==0?0:2);
		val += (byte)((value & 0x01000000)==0?0:1);
		operation[0] = val;
		operation[1] = (byte)((value & 0x00FF0000) >>> 16);
		operation[2] = (byte)((value & 0x0000FF00) >>> 8);
		operation[3] = (byte)(value & 0x000000FF);
		try {
			fos.write(operation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		numInst++;
	}
	
	public void fetchIntoReg(int i, int reg) {
		writeSpecialOperation(7, i);
		writeSpecialOperation(6, 0);
		writeOperation(CompilerInstruction.ARRAY_INDEX, reg, 7, 6);
	}
	
	public void putIntoArray(int i, int reg) {
		writeSpecialOperation(7, i);
		writeSpecialOperation(6, 0);
		writeOperation(CompilerInstruction.ARRAY_AMEND, 7, 6, reg);
	}
	
	public void allocateVar() {
		writeSpecialOperation(7, 1);
		writeOperation(CompilerInstruction.ALLOCATION, 0, 7, 7);
	}
	
	
	@Override
	public void visit(IASTprogram iast, int context) {
		
		for(IASTstatement stmts: iast.getProgram()) {
			if(printtree) System.out.println("Stmt du programme");
			stmts.accept(this, context);
		}
		
	}

	@Override
	public void visit(IASTalternative iast, int context) {
		if(printtree) System.out.println("If");
		
		
		int DEFAULT_SIZE = 4096;
		int condctx = indVar++;
		allocateVar();
		iast.getCondition().accept(this, condctx);
		
		writeSpecialOperation(1, numInst+8);
		writeSpecialOperation(0, numInst+8+DEFAULT_SIZE-1);
		fetchIntoReg(condctx, 2);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 2);
		writeSpecialOperation(1, 0);
		writeOperation(CompilerInstruction.LOAD_PROG, 0, 1, 0);
		
		
		int start = numInst;
		for(IASTstatement istmt: iast.getConsequence()) {
			istmt.accept(this, NO_CONTEXT);
		}
		
		writeSpecialOperation(1, 0);
		writeSpecialOperation(0, start+2*DEFAULT_SIZE);
		writeOperation(CompilerInstruction.LOAD_PROG, 0, 1, 0);
		
		while(numInst < start+DEFAULT_SIZE) {
			writeSpecialOperation(0, 0);
		}

		for(IASTstatement istmt: iast.getAlternative()) {
			istmt.accept(this, NO_CONTEXT);
		}
		
		while(numInst < start+2*DEFAULT_SIZE) {
			writeSpecialOperation(0, 0);
		}
	}

	@Override
	public void visit(IASTbinding iast, int context) {
		if(printtree) System.out.println("Binding");
		
		int varcontext = indVar++;
		env.put(iast.getName(), varcontext);
		allocateVar();
		
		if(iast.getExpression() != null) iast.getExpression().accept(this, varcontext);
	}

	@Override
	public void visit(IASTprint iast, int context) {
		if(printtree) System.out.println("Print");
		
		IASTstatement expr = iast.getArg();
		
		if(expr instanceof IASTconstString) {
			String s = ((IASTconstString)expr).getString();
			for(int i = 0; i < s.length(); i++) {
				if(i+1 < s.length() && s.charAt(i)=='\\' && s.charAt(i+1)=='n') {
					writeSpecialOperation(0, (int)'\n');
					writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
					i++;
				}else {
					writeSpecialOperation(0, (int)s.charAt(i));
					writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
				}
			}
			
		} else if (expr instanceof IASTconstInteger) {
			String s = Integer.toString(((IASTconstInteger)expr).getInteger());
			for(int i = 0; i < s.length(); i++) {
				writeSpecialOperation(0, (int)s.charAt(i));
				writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
			}
			
		} else if (expr instanceof IASTexpression) {
			int indcontext = indVar++;
			allocateVar();
			((IASTexpression)expr).accept(this, indcontext);
			fetchIntoReg(indcontext, 0);
			writeOperation(CompilerInstruction.OUTPUT, 0, 0, 0);
		} 
	}

	@Override
	public void visit(IASTscan iast, int context) {
		if(printtree) System.out.println("Scan: "+iast.getName());
		
		int varcontext = env.get(iast.getName());
		writeOperation(CompilerInstruction.INPUT, 0, 0, 0);
		putIntoArray(varcontext, 0);
			
	}

	@Override
	public void visit(IASTadd iast, int context) {
		if(printtree) System.out.println("Add");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 0);
		fetchIntoReg(contextarg2, 1);
		writeOperation(CompilerInstruction.ADD, 2, 0, 1);
		putIntoArray(context, 2);
		
	}

	@Override
	public void visit(IASTmul iast, int context) {
		if(printtree) System.out.println("Mul");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 0);
		fetchIntoReg(contextarg2, 1);
		writeOperation(CompilerInstruction.MUL, 2, 0, 1);
		putIntoArray(context, 2);
		
	}

	@Override
	public void visit(IASTdiv iast, int context) {
		if(printtree) System.out.println("Div");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 0);
		fetchIntoReg(contextarg2, 1);
		writeOperation(CompilerInstruction.DIV, 2, 0, 1);
		putIntoArray(context, 2);
		
	}

	@Override
	public void visit(IASTeq iast, int context) {
		if(printtree) System.out.println("=");
		if(context == NO_CONTEXT) return;
		
		iast.getArg1().accept(this, context);
		iast.getArg2().accept(this, context);
	}

	@Override
	public void visit(IASTgt iast, int context) {
		if(printtree) System.out.println(">");
		if(context == NO_CONTEXT) return;
		
		iast.getArg1().accept(this, context);
		iast.getArg2().accept(this, context);
		
	}

	@Override
	public void visit(IASTlt iast, int context) {
		if(printtree) System.out.println("<");
		if(context == NO_CONTEXT) return;
		
		iast.getArg1().accept(this, context);
		iast.getArg2().accept(this, context);
		
	}

	@Override
	public void visit(IASTor iast, int context) {
		if(printtree) System.out.println("Or");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 1);
		fetchIntoReg(contextarg2, 2);
		writeSpecialOperation(0, 0);
		writeSpecialOperation(3, 1);
		writeOperation(CompilerInstruction.COND_MOV, 0, 3, 1);
		writeOperation(CompilerInstruction.COND_MOV, 0, 3, 2);
		putIntoArray(context, 0);
		
	}

	@Override
	public void visit(IASTand iast, int context) {
		if(printtree) System.out.println("And");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 2);
		fetchIntoReg(contextarg2, 3);
		
		writeSpecialOperation(4, 1);
		writeSpecialOperation(5, 0);
		writeSpecialOperation(1, 0);
		writeSpecialOperation(0, 0);
		writeOperation(CompilerInstruction.COND_MOV, 1, 4, 2);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 3);
		
		putIntoArray(context, 0);
	}

	@Override
	public void visit(IASTnot iast, int context) {
		if(printtree) System.out.println("Not");
		if(context == NO_CONTEXT) return;
		
		int argcontext = indVar++;
		allocateVar();
		iast.getArg().accept(this, argcontext);
		fetchIntoReg(argcontext, 2);
		writeSpecialOperation(0, 1);
		writeSpecialOperation(1, 0);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 2);
		putIntoArray(context, 0);
	}

	@Override
	public void visit(IASTconstInteger iast, int context) {
		if(printtree) System.out.println("Integer");
		if(context == NO_CONTEXT) return;
		
		writeSpecialOperation(0, iast.getInteger());
		putIntoArray(context, 0);
	}

	@Override
	public void visit(IASTconstString iast, int context) {
		if(printtree) System.out.println("String");
		
	}

	@Override
	public void visit(IASTident iast, int context) {
		if(printtree) System.out.print("Ident: "+iast.getName());
		if(context == NO_CONTEXT) return;
		
		int varcontext = env.get(iast.getName());
		fetchIntoReg(varcontext, 0);
		putIntoArray(context, 0);
		
	}
	
	public void setAsmTrace(boolean b) {
		asmtrace = b;
	}
	
	public void setPrintTree(boolean b) {
		printtree = b;
	}

}
