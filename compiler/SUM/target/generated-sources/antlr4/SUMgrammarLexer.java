// Generated from SUMgrammar.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SUMgrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, INT=19, IDENT=20, STRING=21, ESC=22, LINE_COMMENT=23, 
		COMMENT=24, SPACE=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", 
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "INT", "IDENT", "STRING", "ESC", "LINE_COMMENT", "COMMENT", "SPACE"
	};


	public SUMgrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SUMgrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00a9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\6\24"+
		"p\n\24\r\24\16\24q\3\25\3\25\7\25v\n\25\f\25\16\25y\13\25\3\26\3\26\3"+
		"\26\7\26~\n\26\f\26\16\26\u0081\13\26\3\26\3\26\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\7\30\u008c\n\30\f\30\16\30\u008f\13\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\7\31\u0099\n\31\f\31\16\31\u009c\13\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\6\32\u00a4\n\32\r\32\16\32\u00a5\3\32\3\32\2\2"+
		"\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2\13\3\2\62;"+
		"\5\2C\\aac|\6\2\62;C\\aac|\4\2$$^^\7\2$$^^ppttvv\4\2\f\f\17\17\3\2\61"+
		"\61\3\2,,\5\2\13\f\17\17\"\"\u00b0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2"+
		"\2\2\5\67\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13B\3\2\2\2\rD\3\2\2\2\17F\3\2"+
		"\2\2\21J\3\2\2\2\23L\3\2\2\2\25N\3\2\2\2\27P\3\2\2\2\31S\3\2\2\2\33V\3"+
		"\2\2\2\35Z\3\2\2\2\37_\3\2\2\2!e\3\2\2\2#g\3\2\2\2%i\3\2\2\2\'o\3\2\2"+
		"\2)s\3\2\2\2+z\3\2\2\2-\u0084\3\2\2\2/\u0087\3\2\2\2\61\u0092\3\2\2\2"+
		"\63\u00a3\3\2\2\2\65\66\7\61\2\2\66\4\3\2\2\2\678\7C\2\289\7P\2\29:\7"+
		"F\2\2:\6\3\2\2\2;<\7}\2\2<\b\3\2\2\2=>\7u\2\2>?\7e\2\2?@\7c\2\2@A\7p\2"+
		"\2A\n\3\2\2\2BC\7=\2\2C\f\3\2\2\2DE\7>\2\2E\16\3\2\2\2FG\7P\2\2GH\7Q\2"+
		"\2HI\7V\2\2I\20\3\2\2\2JK\7\177\2\2K\22\3\2\2\2LM\7?\2\2M\24\3\2\2\2N"+
		"O\7@\2\2O\26\3\2\2\2PQ\7k\2\2QR\7h\2\2R\30\3\2\2\2ST\7Q\2\2TU\7T\2\2U"+
		"\32\3\2\2\2VW\7n\2\2WX\7g\2\2XY\7v\2\2Y\34\3\2\2\2Z[\7g\2\2[\\\7n\2\2"+
		"\\]\7u\2\2]^\7g\2\2^\36\3\2\2\2_`\7r\2\2`a\7t\2\2ab\7k\2\2bc\7p\2\2cd"+
		"\7v\2\2d \3\2\2\2ef\7,\2\2f\"\3\2\2\2gh\7-\2\2h$\3\2\2\2ij\7v\2\2jk\7"+
		"j\2\2kl\7g\2\2lm\7p\2\2m&\3\2\2\2np\t\2\2\2on\3\2\2\2pq\3\2\2\2qo\3\2"+
		"\2\2qr\3\2\2\2r(\3\2\2\2sw\t\3\2\2tv\t\4\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2"+
		"\2\2wx\3\2\2\2x*\3\2\2\2yw\3\2\2\2z\177\7$\2\2{~\5-\27\2|~\n\5\2\2}{\3"+
		"\2\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7$\2\2\u0083,\3\2\2\2\u0084\u0085"+
		"\7^\2\2\u0085\u0086\t\6\2\2\u0086.\3\2\2\2\u0087\u0088\7\61\2\2\u0088"+
		"\u0089\7\61\2\2\u0089\u008d\3\2\2\2\u008a\u008c\n\7\2\2\u008b\u008a\3"+
		"\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\b\30\2\2\u0091\60\3\2\2"+
		"\2\u0092\u0093\7\61\2\2\u0093\u0094\7,\2\2\u0094\u009a\3\2\2\2\u0095\u0096"+
		"\7,\2\2\u0096\u0099\n\b\2\2\u0097\u0099\n\t\2\2\u0098\u0095\3\2\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7,\2\2\u009e"+
		"\u009f\7\61\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\b\31\2\2\u00a1\62\3\2"+
		"\2\2\u00a2\u00a4\t\n\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\32"+
		"\2\2\u00a8\64\3\2\2\2\13\2qw}\177\u008d\u0098\u009a\u00a5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}