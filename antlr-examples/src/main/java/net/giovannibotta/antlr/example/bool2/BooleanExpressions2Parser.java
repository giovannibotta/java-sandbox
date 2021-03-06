// Generated from antlr-examples/src/main/java/net/giovannibotta/antlr/example/bool2/BooleanExpressions2.g4 by ANTLR 4.2
package net.giovannibotta.antlr.example.bool2;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BooleanExpressions2Parser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, IntegerConstant=16, 
		BooleanConstant=17, Variable=18, WS=19;
	public static final String[] tokenNames = {
		"<INVALID>", "'&'", "')'", "'+'", "'-'", "'*'", "'('", "'<'", "'!='", 
		"'<='", "'>'", "'=='", "'/'", "'~'", "'>='", "'|'", "IntegerConstant", 
		"BooleanConstant", "Variable", "WS"
	};
	public static final int
		RULE_start = 0, RULE_arithmeticExpression = 1, RULE_arithmeticRelation = 2, 
		RULE_booleanExpression = 3;
	public static final String[] ruleNames = {
		"start", "arithmeticExpression", "arithmeticRelation", "booleanExpression"
	};

	@Override
	public String getGrammarFileName() { return "BooleanExpressions2.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	private final HashSet<String> integerVariables = new HashSet<String>();
	private final HashSet<String> booleanVariables = new HashSet<String>();

	private void defineBooleanVariable(String var){
	    if(integerVariables.contains(var)) throw new IllegalArgumentException("Variable " + var + " already declared as an integer variable");
	    booleanVariables.add(var);
	}

	private void defineIntegerVariable(String var){
	    if(booleanVariables.contains(var)) throw new IllegalArgumentException("Variable " + var + " already declared as a boolean variable");
	    integerVariables.add(var);
	}

	public Set<String> getIntegerVariables(){
	    return ImmutableSet.copyOf(integerVariables);
	}

	public Set<String> getBooleanVariables(){
	    return ImmutableSet.copyOf(booleanVariables);
	}

	public BooleanExpressions2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); booleanExpression(0);
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

	public static class ArithmeticExpressionContext extends ParserRuleContext {
		public ArithmeticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpression; }
	 
		public ArithmeticExpressionContext() { }
		public void copyFrom(ArithmeticExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArithAtomExprContext extends ArithmeticExpressionContext {
		public Token constant;
		public Token variable;
		public TerminalNode Variable() { return getToken(BooleanExpressions2Parser.Variable, 0); }
		public ArithAtomExprContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitArithAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithOpExprContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext left;
		public Token op;
		public ArithmeticExpressionContext right;
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public ArithOpExprContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitArithOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithParenExpContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext child;
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public ArithParenExpContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitArithParenExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		return arithmeticExpression(0);
	}

	private ArithmeticExpressionContext arithmeticExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, _parentState);
		ArithmeticExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_arithmeticExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			switch (_input.LA(1)) {
			case 6:
				{
				_localctx = new ArithParenExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(11); match(6);
				setState(12); ((ArithParenExpContext)_localctx).child = arithmeticExpression(0);
				setState(13); match(2);
				}
				break;
			case IntegerConstant:
				{
				_localctx = new ArithAtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(15); ((ArithAtomExprContext)_localctx).constant = match(IntegerConstant);
				}
				break;
			case Variable:
				{
				_localctx = new ArithAtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(16); ((ArithAtomExprContext)_localctx).variable = match(Variable);
				defineIntegerVariable((((ArithAtomExprContext)_localctx).variable!=null?((ArithAtomExprContext)_localctx).variable.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(28);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(26);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ArithOpExprContext(new ArithmeticExpressionContext(_parentctx, _parentState));
						((ArithOpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpression);
						setState(20);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(21);
						((ArithOpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==5 || _la==12) ) {
							((ArithOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(22); ((ArithOpExprContext)_localctx).right = arithmeticExpression(5);
						}
						break;

					case 2:
						{
						_localctx = new ArithOpExprContext(new ArithmeticExpressionContext(_parentctx, _parentState));
						((ArithOpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpression);
						setState(23);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(24);
						((ArithOpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==3 || _la==4) ) {
							((ArithOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(25); ((ArithOpExprContext)_localctx).right = arithmeticExpression(4);
						}
						break;
					}
					} 
				}
				setState(30);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class ArithmeticRelationContext extends ParserRuleContext {
		public ArithmeticExpressionContext left;
		public Token op;
		public ArithmeticExpressionContext right;
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public ArithmeticRelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticRelation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitArithmeticRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticRelationContext arithmeticRelation() throws RecognitionException {
		ArithmeticRelationContext _localctx = new ArithmeticRelationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arithmeticRelation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); ((ArithmeticRelationContext)_localctx).left = arithmeticExpression(0);
			setState(32);
			((ArithmeticRelationContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 8) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 14))) != 0)) ) {
				((ArithmeticRelationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(33); ((ArithmeticRelationContext)_localctx).right = arithmeticExpression(0);
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

	public static class BooleanExpressionContext extends ParserRuleContext {
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
	 
		public BooleanExpressionContext() { }
		public void copyFrom(BooleanExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BoolAtomExprContext extends BooleanExpressionContext {
		public Token constant;
		public Token variable;
		public TerminalNode Variable() { return getToken(BooleanExpressions2Parser.Variable, 0); }
		public BoolAtomExprContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitBoolAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolParenExpContext extends BooleanExpressionContext {
		public BooleanExpressionContext child;
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public BoolParenExpContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitBoolParenExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolOpExprContext extends BooleanExpressionContext {
		public BooleanExpressionContext left;
		public Token op;
		public BooleanExpressionContext right;
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public BoolOpExprContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitBoolOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolNotExpContext extends BooleanExpressionContext {
		public BooleanExpressionContext child;
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public BoolNotExpContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitBoolNotExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithRelContext extends BooleanExpressionContext {
		public ArithmeticRelationContext arithmeticRelation() {
			return getRuleContext(ArithmeticRelationContext.class,0);
		}
		public ArithRelContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanExpressions2Visitor ) return ((BooleanExpressions2Visitor<? extends T>)visitor).visitArithRel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		return booleanExpression(0);
	}

	private BooleanExpressionContext booleanExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, _parentState);
		BooleanExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_booleanExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new BoolNotExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(36); match(13);
				setState(37); ((BoolNotExpContext)_localctx).child = booleanExpression(6);
				}
				break;

			case 2:
				{
				_localctx = new BoolParenExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38); match(6);
				setState(39); ((BoolParenExpContext)_localctx).child = booleanExpression(0);
				setState(40); match(2);
				}
				break;

			case 3:
				{
				_localctx = new BoolAtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42); ((BoolAtomExprContext)_localctx).constant = match(BooleanConstant);
				}
				break;

			case 4:
				{
				_localctx = new BoolAtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43); ((BoolAtomExprContext)_localctx).variable = match(Variable);
				defineBooleanVariable((((BoolAtomExprContext)_localctx).variable!=null?((BoolAtomExprContext)_localctx).variable.getText():null));
				}
				break;

			case 5:
				{
				_localctx = new ArithRelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45); arithmeticRelation();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(54);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new BoolOpExprContext(new BooleanExpressionContext(_parentctx, _parentState));
						((BoolOpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(48);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(49); ((BoolOpExprContext)_localctx).op = match(1);
						setState(50); ((BoolOpExprContext)_localctx).right = booleanExpression(6);
						}
						break;

					case 2:
						{
						_localctx = new BoolOpExprContext(new BooleanExpressionContext(_parentctx, _parentState));
						((BoolOpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(51);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(52); ((BoolOpExprContext)_localctx).op = match(15);
						setState(53); ((BoolOpExprContext)_localctx).right = booleanExpression(5);
						}
						break;
					}
					} 
				}
				setState(58);
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
		case 1: return arithmeticExpression_sempred((ArithmeticExpressionContext)_localctx, predIndex);

		case 3: return booleanExpression_sempred((BooleanExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmeticExpression_sempred(ArithmeticExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 4);

		case 1: return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean booleanExpression_sempred(BooleanExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 5);

		case 3: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25>\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\25\n\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\7\3\35\n\3\f\3\16\3 \13\3\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\61\n\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\59\n\5\f\5\16\5<\13\5\3\5\2\4\4\b\6\2\4\6\b\2\5\4\2\7\7\16\16"+
		"\3\2\5\6\4\2\t\r\20\20C\2\n\3\2\2\2\4\24\3\2\2\2\6!\3\2\2\2\b\60\3\2\2"+
		"\2\n\13\5\b\5\2\13\3\3\2\2\2\f\r\b\3\1\2\r\16\7\b\2\2\16\17\5\4\3\2\17"+
		"\20\7\4\2\2\20\25\3\2\2\2\21\25\7\22\2\2\22\23\7\24\2\2\23\25\b\3\1\2"+
		"\24\f\3\2\2\2\24\21\3\2\2\2\24\22\3\2\2\2\25\36\3\2\2\2\26\27\f\6\2\2"+
		"\27\30\t\2\2\2\30\35\5\4\3\7\31\32\f\5\2\2\32\33\t\3\2\2\33\35\5\4\3\6"+
		"\34\26\3\2\2\2\34\31\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37"+
		"\5\3\2\2\2 \36\3\2\2\2!\"\5\4\3\2\"#\t\4\2\2#$\5\4\3\2$\7\3\2\2\2%&\b"+
		"\5\1\2&\'\7\17\2\2\'\61\5\b\5\b()\7\b\2\2)*\5\b\5\2*+\7\4\2\2+\61\3\2"+
		"\2\2,\61\7\23\2\2-.\7\24\2\2.\61\b\5\1\2/\61\5\6\4\2\60%\3\2\2\2\60(\3"+
		"\2\2\2\60,\3\2\2\2\60-\3\2\2\2\60/\3\2\2\2\61:\3\2\2\2\62\63\f\7\2\2\63"+
		"\64\7\3\2\2\649\5\b\5\b\65\66\f\6\2\2\66\67\7\21\2\2\679\5\b\5\78\62\3"+
		"\2\2\28\65\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\t\3\2\2\2<:\3\2\2\2"+
		"\b\24\34\36\608:";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}