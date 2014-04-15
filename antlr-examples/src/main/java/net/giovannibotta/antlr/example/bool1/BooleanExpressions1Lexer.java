// Generated from antlr-examples/src/main/java/net/giovannibotta/antlr/example/bool1/BooleanExpressions1.g4 by ANTLR 4.2
package net.giovannibotta.antlr.example.bool1;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BooleanExpressions1Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, Integer=16, BooleanConstant=17, 
		WS=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'&'", "')'", "'+'", "'-'", "'*'", "'('", "'<'", "'!='", "'<='", "'>'", 
		"'=='", "'/'", "'~'", "'>='", "'|'", "Integer", "BooleanConstant", "WS"
	};
	public static final String[] ruleNames = {
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "Integer", "BooleanConstant", 
		"WS"
	};


	public BooleanExpressions1Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BooleanExpressions1.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24]\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\17\3\20\3\20\3\21\6\21K\n\21\r\21\16\21L\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\5\22X\n\22\3\23\3\23\3\23\3\23\2\2\24\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\3\2\3\5\2\13\f\17\17\"\"^\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5)\3\2\2\2\7"+
		"+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\61\3\2\2\2\17\63\3\2\2\2\21\65\3\2"+
		"\2\2\238\3\2\2\2\25;\3\2\2\2\27=\3\2\2\2\31@\3\2\2\2\33B\3\2\2\2\35D\3"+
		"\2\2\2\37G\3\2\2\2!J\3\2\2\2#W\3\2\2\2%Y\3\2\2\2\'(\7(\2\2(\4\3\2\2\2"+
		")*\7+\2\2*\6\3\2\2\2+,\7-\2\2,\b\3\2\2\2-.\7/\2\2.\n\3\2\2\2/\60\7,\2"+
		"\2\60\f\3\2\2\2\61\62\7*\2\2\62\16\3\2\2\2\63\64\7>\2\2\64\20\3\2\2\2"+
		"\65\66\7#\2\2\66\67\7?\2\2\67\22\3\2\2\289\7>\2\29:\7?\2\2:\24\3\2\2\2"+
		";<\7@\2\2<\26\3\2\2\2=>\7?\2\2>?\7?\2\2?\30\3\2\2\2@A\7\61\2\2A\32\3\2"+
		"\2\2BC\7\u0080\2\2C\34\3\2\2\2DE\7@\2\2EF\7?\2\2F\36\3\2\2\2GH\7~\2\2"+
		"H \3\2\2\2IK\4\62;\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\"\3\2\2"+
		"\2NO\7v\2\2OP\7t\2\2PQ\7w\2\2QX\7g\2\2RS\7h\2\2ST\7c\2\2TU\7n\2\2UV\7"+
		"u\2\2VX\7g\2\2WN\3\2\2\2WR\3\2\2\2X$\3\2\2\2YZ\t\2\2\2Z[\3\2\2\2[\\\b"+
		"\23\2\2\\&\3\2\2\2\5\2LW\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}