package sum.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import sum.antlr.SUMgrammarListener;
import sum.antlr.SUMgrammarParser.AlternativeContext;
import sum.antlr.SUMgrammarParser.BinOpContext;
import sum.antlr.SUMgrammarParser.BindingContext;
import sum.antlr.SUMgrammarParser.ConstIntegerContext;
import sum.antlr.SUMgrammarParser.ConstStringContext;
import sum.antlr.SUMgrammarParser.ExpressionContext;
import sum.antlr.SUMgrammarParser.IdentContext;
import sum.antlr.SUMgrammarParser.LogicBinOpContext;
import sum.antlr.SUMgrammarParser.LogicUnOpContext;
import sum.antlr.SUMgrammarParser.PrintContext;
import sum.antlr.SUMgrammarParser.ProgContext;
import sum.antlr.SUMgrammarParser.RelationBinOpContext;
import sum.antlr.SUMgrammarParser.ScanContext;
import sum.antlr.SUMgrammarParser.StmtContext;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTstatement;

public class SUMListener implements SUMgrammarListener {
	
	private IASTfactory factory;
	
	public SUMListener(IASTfactory factory) {
		this.factory = factory;
	}
	
	@Override public void enterEveryRule(ParserRuleContext arg0) {}
	@Override public void visitErrorNode(ErrorNode arg0) {}
	@Override public void exitEveryRule(ParserRuleContext arg0) {}
	@Override public void visitTerminal(TerminalNode arg0) {}
	@Override public void enterBinding(BindingContext ctx) {}

	@Override
	public void exitBinding(BindingContext ctx) {
		ctx.node = factory.newBinding(ctx.var.getText(), ctx.val.node);
	}

	@Override
	public void enterPrint(PrintContext ctx) {}

	@Override
	public void exitPrint(PrintContext ctx) {
		ctx.node = factory.newPrint(ctx.val.node);
	}

	@Override
	public void enterConstInteger(ConstIntegerContext ctx) {}

	@Override
	public void exitConstInteger(ConstIntegerContext ctx) {
		ctx.node = factory.newInt(Integer.parseInt(ctx.intConst.getText()));
	}

	@Override
	public void enterAlternative(AlternativeContext ctx) {
}

	@Override
	public void exitAlternative(AlternativeContext ctx) {
		ctx.node = factory.newAlternative(ctx.cond.node, ctx.cons.node, ctx.alt.node);
	}

	@Override
	public void enterExpression(ExpressionContext ctx) {}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		ctx.node = ctx.node;

	}

	@Override
	public void enterLogicUnOp(LogicUnOpContext ctx) {}

	@Override
	public void exitLogicUnOp(LogicUnOpContext ctx) {
		ctx.node = factory.newNot(ctx.arg.node);
	}

	@Override
	public void enterScan(ScanContext ctx) {}

	@Override
	public void exitScan(ScanContext ctx) {
		ctx.node = factory.newScan(ctx.var.getText());
	}

	@Override
	public void enterConstString(ConstStringContext ctx) {}

	@Override
	public void exitConstString(ConstStringContext ctx) {
		ctx.node = factory.newString(ctx.stringConst.getText().substring(1, ctx.stringConst.getText().length()-1));
	}

	@Override
	public void enterBinOp(BinOpContext ctx) {}

	@Override
	public void exitBinOp(BinOpContext ctx) {
		String op = ctx.op.getText();
		if(op.equals("+")) {
			ctx.node = factory.newAdd(ctx.arg1.node, ctx.arg2.node);
		}
		if(op.equals("*")) {
			ctx.node = factory.newMul(ctx.arg1.node, ctx.arg2.node);
		}
		if(op.equals("/")) {
			ctx.node = factory.newDiv(ctx.arg1.node, ctx.arg2.node);
		}
	}

	@Override
	public void enterRelationBinOp(RelationBinOpContext ctx) {}

	@Override
	public void exitRelationBinOp(RelationBinOpContext ctx) {
		String op = ctx.op.getText();
		if(op.equals("=")) {
			ctx.node = factory.newEq(ctx.arg1.node, ctx.arg2.node);
		}
		if(op.equals(">")) {
			ctx.node = factory.newGt(ctx.arg1.node, ctx.arg2.node);
		}
		if(op.equals("<")) {
			ctx.node = factory.newLt(ctx.arg1.node, ctx.arg2.node);
		}

	}

	@Override
	public void enterProg(ProgContext ctx) {}

	@Override
	public void exitProg(ProgContext ctx) {
		List<IASTstatement> stmts = new ArrayList<>();
		for(StmtContext sc: ctx.stmts) {
			stmts.add(sc.node);
		}
		ctx.node =  factory.newProgram(stmts);
	}

	@Override
	public void enterLogicBinOp(LogicBinOpContext ctx) {}

	@Override
	public void exitLogicBinOp(LogicBinOpContext ctx) {
		String op = ctx.op.getText();
		if(op.equals("AND")) {
			ctx.node = factory.newAnd(ctx.arg1.node, ctx.arg2.node);
		}
		if(op.equals("OR")) {
			ctx.node = factory.newOr(ctx.arg1.node, ctx.arg2.node);
		}

	}

	@Override
	public void enterIdent(IdentContext ctx) {
	}

	@Override
	public void exitIdent(IdentContext ctx) {
		ctx.node = factory.newIdent(ctx.ident.getText());
	}

}
