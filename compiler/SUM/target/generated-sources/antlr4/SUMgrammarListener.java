// Generated from SUMgrammar.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SUMgrammarParser}.
 */
public interface SUMgrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBinding(@NotNull SUMgrammarParser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBinding(@NotNull SUMgrammarParser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(@NotNull SUMgrammarParser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(@NotNull SUMgrammarParser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdent(@NotNull SUMgrammarParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdent(@NotNull SUMgrammarParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicUnOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicUnOp(@NotNull SUMgrammarParser.LogicUnOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicUnOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicUnOp(@NotNull SUMgrammarParser.LogicUnOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SUMgrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull SUMgrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SUMgrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull SUMgrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicBinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicBinOp(@NotNull SUMgrammarParser.LogicBinOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicBinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicBinOp(@NotNull SUMgrammarParser.LogicBinOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Print}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull SUMgrammarParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull SUMgrammarParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstInteger(@NotNull SUMgrammarParser.ConstIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstInteger(@NotNull SUMgrammarParser.ConstIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Expression}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull SUMgrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Expression}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull SUMgrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Scan}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterScan(@NotNull SUMgrammarParser.ScanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Scan}
	 * labeled alternative in {@link SUMgrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitScan(@NotNull SUMgrammarParser.ScanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstString(@NotNull SUMgrammarParser.ConstStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstString(@NotNull SUMgrammarParser.ConstStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinOp(@NotNull SUMgrammarParser.BinOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinOp(@NotNull SUMgrammarParser.BinOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationBinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationBinOp(@NotNull SUMgrammarParser.RelationBinOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationBinOp}
	 * labeled alternative in {@link SUMgrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationBinOp(@NotNull SUMgrammarParser.RelationBinOpContext ctx);
}