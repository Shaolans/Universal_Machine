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
	
	//numero d'instruction courant
	private int numInst;
	
	//indice de la position de la variable dans le plateau
	private int indVar;
	
	//map de la variable et de la position de la variable dans le plateau
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
		
		//operation d'arret du programme
		writeOperation(CompilerInstruction.HALT, 0, 0, 0);
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Permet d'écrire l'opération en binaire dans le fichier de sortie
	 * @param op le numéro de l'opération
	 * @param regA l'indice du registre A
	 * @param regB l'indice du registre B
	 * @param regC l'indice du registre C
	 */
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
		//on d'interesse au i-eme bit, si il est a 1 alors on ajoute 2^i
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
	
	/**
	 * Charge dans le registre A la valeur value sur 25 bits
	 * @param regA l'indice du registre
	 * @param value la valeur
	 */
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
		operation[1] = (byte)((value & 0x00FF0000) >>> 16); //on n'oublie pas de décalé les bits
		operation[2] = (byte)((value & 0x0000FF00) >>> 8);
		operation[3] = (byte)(value & 0x000000FF);
		try {
			fos.write(operation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		numInst++;
	}
	
	/**
	 * Récupère une valeur simple situé dans le plateau[i][0] dans le cas d'une variable
	 * et l'ajoute dans le registre reg
	 * @param i la position de la variable dans le plateau
	 * @param reg l'indice du registre
	 */
	public void fetchIntoReg(int i, int reg) {
		writeSpecialOperation(7, i);
		writeSpecialOperation(6, 0);
		writeOperation(CompilerInstruction.ARRAY_INDEX, reg, 7, 6);
	}
	
	/**
	 * Ajoute dans le plateau[i][0] la valeur dans le registre d'indice reg
	 * @param i l'indice dans le plateau
	 * @param reg l'indice du registre
	 */
	public void putIntoArray(int i, int reg) {
		writeSpecialOperation(7, i);
		writeSpecialOperation(6, 0);
		writeOperation(CompilerInstruction.ARRAY_AMEND, 7, 6, reg);
	}
	
	/**
	 * Alloue un plateau de taille 1 pour une variable
	 */
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
		
		//suivant la valeur de la condition, on saute soit a l'adresse du then ou alors a l'adresse du else
		//les blocs de then et else sont fixés, ce qui permet de calculer directement les adresses de saut
		writeSpecialOperation(1, numInst+8);
		writeSpecialOperation(0, numInst+8+DEFAULT_SIZE-1);
		fetchIntoReg(condctx, 2);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 2);
		writeSpecialOperation(1, 0);
		writeOperation(CompilerInstruction.LOAD_PROG, 0, 1, 0);
		
		//instructions du then
		int start = numInst;
		for(IASTstatement istmt: iast.getConsequence()) {
			istmt.accept(this, NO_CONTEXT);
		}
		
		//saut hors du if
		writeSpecialOperation(1, 0);
		writeSpecialOperation(0, start+2*DEFAULT_SIZE);
		writeOperation(CompilerInstruction.LOAD_PROG, 0, 1, 0);
		
		//on remplit le reste du bloc avec des NOP
		boolean asmtrace_atpre = asmtrace;
		asmtrace = false;
		if(asmtrace_atpre) System.out.println("FILL REST OF ALTERNATIVE (CONS) WITH NOP");
		while(numInst < start+DEFAULT_SIZE) {
			writeSpecialOperation(0, 0);
		}
		asmtrace = asmtrace_atpre;
		
		//insutrctions du else
		for(IASTstatement istmt: iast.getAlternative()) {
			istmt.accept(this, NO_CONTEXT);
		}
		
		//on remplit le reste du bloc avec des NOP
		asmtrace = false;
		if(asmtrace_atpre) System.out.println("FILL REST OF ALTERNATIVE (ALT) WITH NOP");
		while(numInst < start+2*DEFAULT_SIZE) {
			writeSpecialOperation(0, 0);
		}
		asmtrace = asmtrace_atpre;
	}

	@Override
	public void visit(IASTbinding iast, int context) {
		if(printtree) System.out.println("Binding");
		
		//allocation d'une variable
		int varcontext = indVar++;
		allocateVar();
		env.put(iast.getName(), varcontext);
		
		//si une expression est avec la declaration, on stock la valeur dans le plateau[varcontext][0]
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
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 0);
		fetchIntoReg(contextarg2, 1);
		
		//on peut remarquer que soit a un entier
		//a+NAND(a,a)+1 = 0 <=> a+~(a&a)+1 = 0
		//car NAND(a,a) <=> NOT(a)
		//par conséquent a et NOT(a) on des bits opposés
		//si on effectue l'addition on a donc tout les bits a 1
		//et si on effectue une addition a +1 on dépasse la capacité on retombe sur 0
		//Donc a+NAND(b,b)+1 = 0 ssi a = b
		writeOperation(CompilerInstruction.NOT_AND, 2, 1, 1);
		writeSpecialOperation(3, 1);
		writeOperation(CompilerInstruction.ADD, 2, 2, 3);
		writeOperation(CompilerInstruction.ADD, 0, 0, 2);
		writeSpecialOperation(1, 1);
		writeSpecialOperation(2, 0);
		writeOperation(CompilerInstruction.COND_MOV, 1, 2, 0);
		putIntoArray(context, 1);
	}

	@Override
	public void visit(IASTgt iast, int context) {
		if(printtree) System.out.println(">");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 1);
		fetchIntoReg(contextarg2, 2);
		
		//on ajoute +1 pour eviter un probleme de division par 0
		writeSpecialOperation(3, 1);
		writeOperation(CompilerInstruction.ADD, 1, 1, 3);
		writeOperation(CompilerInstruction.ADD, 2, 2, 3);
		
		//si reg[1]/reg[2] = 0 alors reg[1] < reg[2] car c'est une division entiere 
		writeOperation(CompilerInstruction.DIV, 3, 1, 2);
		writeSpecialOperation(0, 0);
		writeSpecialOperation(4, 1);
		writeOperation(CompilerInstruction.COND_MOV, 0, 4, 3);
		
		
		//on verifie que reg[1] != reg[2]
		writeOperation(CompilerInstruction.NOT_AND, 3, 2, 2);
		writeSpecialOperation(4, 1);
		writeOperation(CompilerInstruction.ADD, 3, 3, 4);
		writeOperation(CompilerInstruction.ADD, 1, 3, 1);
		writeSpecialOperation(2, 1);
		writeSpecialOperation(3, 0);
		writeOperation(CompilerInstruction.COND_MOV, 2, 3, 1);
		writeSpecialOperation(1, 0);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 2);
		
		putIntoArray(context, 0);
		
		
		
		
	}

	@Override
	public void visit(IASTlt iast, int context) {
		if(printtree) System.out.println("<");
		if(context == NO_CONTEXT) return;
		
		int contextarg1 = indVar++;
		allocateVar();
		int contextarg2 = indVar++;
		allocateVar();
		iast.getArg1().accept(this, contextarg1);
		iast.getArg2().accept(this, contextarg2);
		fetchIntoReg(contextarg1, 2);
		fetchIntoReg(contextarg2, 1);
		
		writeSpecialOperation(3, 1);
		writeOperation(CompilerInstruction.ADD, 1, 1, 3);
		writeOperation(CompilerInstruction.ADD, 2, 2, 3);
		
		
		writeOperation(CompilerInstruction.DIV, 3, 1, 2);
		writeSpecialOperation(0, 0);
		writeSpecialOperation(4, 1);
		writeOperation(CompilerInstruction.COND_MOV, 0, 4, 3);
		
		
		
		writeOperation(CompilerInstruction.NOT_AND, 3, 2, 2);
		writeSpecialOperation(4, 1);
		writeOperation(CompilerInstruction.ADD, 3, 3, 4);
		writeOperation(CompilerInstruction.ADD, 1, 3, 1);
		writeSpecialOperation(2, 1);
		writeSpecialOperation(3, 0);
		writeOperation(CompilerInstruction.COND_MOV, 2, 3, 1);
		writeSpecialOperation(1, 0);
		writeOperation(CompilerInstruction.COND_MOV, 0, 1, 2);
		
		putIntoArray(context, 0);
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
		//reg[1] et reg[2] contiennent le resultat de membre gauche et droit de OR
		//reg[0] contient 0, si reg[1] ou reg[2] contiennent la valeur != 0 alors
		//on met dans reg[0] la valeur 1, donc ceci est bien un OR
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
		//reg[2] et reg[3] contiennent le resultat du membre gauche et droit de AND
		//reg[1] = 0 si reg[2] != 0 alors reg[1] = 1
		//reg[0] = 0 si reg[3] != 0 alors reg[0] = reg[1]
		//donc si on a 0 AND 1, reg[0] = 0 etc
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
		//si le resultat est != 0 alors on met 1 sinon 0
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
