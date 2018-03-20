// Generated from SUMgrammar.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SUMgrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, INT=19, IDENT=20, STRING=21, ESC=22, LINE_COMMENT=23, 
		COMMENT=24, SPACE=25;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'AND'", "'{'", "'scan'", "';'", "'<'", "'NOT'", "'}'", 
		"'='", "'>'", "'if'", "'OR'", "'let'", "'else'", "'print'", "'*'", "'+'", 
		"'then'", "INT", "IDENT", "STRING", "ESC", "LINE_COMMENT", "COMMENT", 
		"SPACE"
	};
	public static final int
		RULE_prog = 0, RULE_stmt = 1, RULE_expr = 2;
	public static final String[] ruleNames = {
		"prog", "stmt", "expr"
	};

	@Override
	public String getGrammarFileName() { return "SUMgrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SUMgrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public sum.interfaces.iast.IASTprogram node;
		public StmtContext stmt;
		public List<StmtContext> stmts = new ArrayList<StmtContext>();
		public TerminalNode EOF() { return getToken(SUMgrammarParser.EOF, 0); }
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__11) | (1L << T__7) | (1L << T__5) | (1L << T__3) | (1L << INT) | (1L << IDENT) | (1L << STRING))) != 0)) {
				{
				{
				setState(6); ((ProgContext)_localctx).stmt = stmt();
				((ProgContext)_localctx).stmts.add(((ProgContext)_localctx).stmt);
				setState(8);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(7); match(T__13);
					}
				}

				}
				}
				setState(14);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(15); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public sum.interfaces.iast.IASTstatement node;
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
			this.node = ctx.node;
		}
	}
	public static class BindingContext extends StmtContext {
		public Token var;
		public ExprContext val;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(SUMgrammarParser.IDENT, 0); }
		public BindingContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitBinding(this);
		}
	}
	public static class PrintContext extends StmtContext {
		public ExprContext val;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitPrint(this);
		}
	}
	public static class AlternativeContext extends StmtContext {
		public ExprContext cond;
		public StmtContext cons;
		public StmtContext alt;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public AlternativeContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitAlternative(this);
		}
	}
	public static class ExpressionContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExpressionContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitExpression(this);
		}
	}
	public static class ScanContext extends StmtContext {
		public Token var;
		public TerminalNode IDENT() { return getToken(SUMgrammarParser.IDENT, 0); }
		public ScanContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterScan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitScan(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(37);
			switch (_input.LA(1)) {
			case T__11:
			case INT:
			case IDENT:
			case STRING:
				_localctx = new ExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17); expr(0);
				}
				break;
			case T__5:
				_localctx = new BindingContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(18); match(T__5);
				setState(19); ((BindingContext)_localctx).var = match(IDENT);
				setState(20); match(T__9);
				setState(21); ((BindingContext)_localctx).val = expr(0);
				}
				break;
			case T__3:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(22); match(T__3);
				setState(23); ((PrintContext)_localctx).val = expr(0);
				}
				break;
			case T__14:
				_localctx = new ScanContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(24); match(T__14);
				setState(25); ((ScanContext)_localctx).var = match(IDENT);
				}
				break;
			case T__7:
				_localctx = new AlternativeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(26); match(T__7);
				setState(27); ((AlternativeContext)_localctx).cond = expr(0);
				setState(28); match(T__0);
				setState(29); match(T__15);
				setState(30); ((AlternativeContext)_localctx).cons = stmt();
				setState(31); match(T__10);
				setState(32); match(T__4);
				setState(33); match(T__15);
				setState(34); ((AlternativeContext)_localctx).alt = stmt();
				setState(35); match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public sum.interfaces.iast.IASTexpression node;
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this.node = ctx.node;
		}
	}
	public static class ConstIntegerContext extends ExprContext {
		public Token intConst;
		public TerminalNode INT() { return getToken(SUMgrammarParser.INT, 0); }
		public ConstIntegerContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterConstInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitConstInteger(this);
		}
	}
	public static class IdentContext extends ExprContext {
		public Token ident;
		public TerminalNode IDENT() { return getToken(SUMgrammarParser.IDENT, 0); }
		public IdentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitIdent(this);
		}
	}
	public static class LogicUnOpContext extends ExprContext {
		public ExprContext arg;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LogicUnOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterLogicUnOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitLogicUnOp(this);
		}
	}
	public static class ConstStringContext extends ExprContext {
		public Token stringConst;
		public TerminalNode STRING() { return getToken(SUMgrammarParser.STRING, 0); }
		public ConstStringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterConstString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitConstString(this);
		}
	}
	public static class BinOpContext extends ExprContext {
		public ExprContext arg1;
		public Token op;
		public ExprContext arg2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterBinOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitBinOp(this);
		}
	}
	public static class RelationBinOpContext extends ExprContext {
		public ExprContext arg1;
		public Token op;
		public ExprContext arg2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelationBinOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterRelationBinOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitRelationBinOp(this);
		}
	}
	public static class LogicBinOpContext extends ExprContext {
		public ExprContext arg1;
		public Token op;
		public ExprContext arg2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LogicBinOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).enterLogicBinOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SUMgrammarListener ) ((SUMgrammarListener)listener).exitLogicBinOp(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			switch (_input.LA(1)) {
			case T__11:
				{
				_localctx = new LogicUnOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(40); match(T__11);
				setState(41); ((LogicUnOpContext)_localctx).arg = expr(1);
				}
				break;
			case INT:
				{
				_localctx = new ConstIntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42); ((ConstIntegerContext)_localctx).intConst = match(INT);
				}
				break;
			case STRING:
				{
				_localctx = new ConstStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43); ((ConstStringContext)_localctx).stringConst = match(STRING);
				}
				break;
			case IDENT:
				{
				_localctx = new IdentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44); ((IdentContext)_localctx).ident = match(IDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new BinOpContext(new ExprContext(_parentctx, _parentState));
						((BinOpContext)_localctx).arg1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(47);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(48);
						((BinOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__2) | (1L << T__1))) != 0)) ) {
							((BinOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(49); ((BinOpContext)_localctx).arg2 = expr(5);
						}
						break;
					case 2:
						{
						_localctx = new RelationBinOpContext(new ExprContext(_parentctx, _parentState));
						((RelationBinOpContext)_localctx).arg1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(50);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(51);
						((RelationBinOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__9) | (1L << T__8))) != 0)) ) {
							((RelationBinOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(52); ((RelationBinOpContext)_localctx).arg2 = expr(4);
						}
						break;
					case 3:
						{
						_localctx = new LogicBinOpContext(new ExprContext(_parentctx, _parentState));
						((LogicBinOpContext)_localctx).arg1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(53);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(54);
						((LogicBinOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__6) ) {
							((LogicBinOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(55); ((LogicBinOpContext)_localctx).arg2 = expr(3);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 4);
		case 1: return precpred(_ctx, 3);
		case 2: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\33@\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\5\2\13\n\2\7\2\r\n\2\f\2\16\2\20\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3(\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\60\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\4\2\3\6\5\2\4\6\2\5\4\2\3\3\22"+
		"\23\4\2\b\b\13\f\4\2\4\4\16\16H\2\16\3\2\2\2\4\'\3\2\2\2\6/\3\2\2\2\b"+
		"\n\5\4\3\2\t\13\7\7\2\2\n\t\3\2\2\2\n\13\3\2\2\2\13\r\3\2\2\2\f\b\3\2"+
		"\2\2\r\20\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\21\3\2\2\2\20\16\3\2\2"+
		"\2\21\22\7\2\2\3\22\3\3\2\2\2\23(\5\6\4\2\24\25\7\17\2\2\25\26\7\26\2"+
		"\2\26\27\7\13\2\2\27(\5\6\4\2\30\31\7\21\2\2\31(\5\6\4\2\32\33\7\6\2\2"+
		"\33(\7\26\2\2\34\35\7\r\2\2\35\36\5\6\4\2\36\37\7\24\2\2\37 \7\5\2\2 "+
		"!\5\4\3\2!\"\7\n\2\2\"#\7\20\2\2#$\7\5\2\2$%\5\4\3\2%&\7\n\2\2&(\3\2\2"+
		"\2\'\23\3\2\2\2\'\24\3\2\2\2\'\30\3\2\2\2\'\32\3\2\2\2\'\34\3\2\2\2(\5"+
		"\3\2\2\2)*\b\4\1\2*+\7\t\2\2+\60\5\6\4\3,\60\7\25\2\2-\60\7\27\2\2.\60"+
		"\7\26\2\2/)\3\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60<\3\2\2\2\61\62\f"+
		"\6\2\2\62\63\t\2\2\2\63;\5\6\4\7\64\65\f\5\2\2\65\66\t\3\2\2\66;\5\6\4"+
		"\6\678\f\4\2\289\t\4\2\29;\5\6\4\5:\61\3\2\2\2:\64\3\2\2\2:\67\3\2\2\2"+
		";>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\7\3\2\2\2><\3\2\2\2\b\n\16\'/:<";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}