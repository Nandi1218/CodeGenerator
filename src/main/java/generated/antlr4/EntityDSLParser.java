package generated.antlr4;// Generated from C:/Users/kanyi/IdeaProjects/codeGenerator/src/main/antlr4/EntityDSL.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class EntityDSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, ID=21, INT=22, WS=23;
	public static final int
		RULE_model = 0, RULE_entity = 1, RULE_property = 2, RULE_type = 3, RULE_modifier = 4;
	private static String[] makeRuleNames() {
		return new String[] {
				"model", "entity", "property", "type", "modifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'entity'", "'{'", "'}'", "':'", "';'", "'['", "']'", "'primary'", 
			"'generated'", "'required'", "'unique'", "'optional'", "'min'", "'('", 
			"')'", "'max'", "'length'", "','", "'minLength'", "'maxLength'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "ID", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EntityDSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EntityDSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModelContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(EntityDSLParser.EOF, 0); }
		public List<EntityContext> entity() {
			return getRuleContexts(EntityContext.class);
		}
		public EntityContext entity(int i) {
			return getRuleContext(EntityContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(10);
				entity();
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(16);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EntityContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntityDSLParser.ID, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public EntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityContext entity() throws RecognitionException {
		EntityContext _localctx = new EntityContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_entity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(19);
			match(ID);
			setState(20);
			match(T__1);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(21);
				property();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntityDSLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(ID);
			setState(30);
			match(T__3);
			setState(31);
			type();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1785600L) != 0)) {
				{
				{
				setState(32);
				modifier();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(38);
				match(T__4);
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTypeContext extends TypeContext {
		public TerminalNode ID() { return getToken(EntityDSLParser.ID, 0); }
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleTypeContext extends TypeContext {
		public TerminalNode ID() { return getToken(EntityDSLParser.ID, 0); }
		public SimpleTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new SimpleTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(ID);
				setState(43);
				match(T__5);
				setState(44);
				match(T__6);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ModifierContext extends ParserRuleContext {
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
	 
		public ModifierContext() { }
		public void copyFrom(ModifierContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OPTIONALContext extends ModifierContext {
		public OPTIONALContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitOPTIONAL(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MINContext extends ModifierContext {
		public TerminalNode INT() { return getToken(EntityDSLParser.INT, 0); }
		public MINContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitMIN(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MIN_LENGTHContext extends ModifierContext {
		public TerminalNode INT() { return getToken(EntityDSLParser.INT, 0); }
		public MIN_LENGTHContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitMIN_LENGTH(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MAXContext extends ModifierContext {
		public TerminalNode INT() { return getToken(EntityDSLParser.INT, 0); }
		public MAXContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitMAX(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LENGTHContext extends ModifierContext {
		public List<TerminalNode> INT() { return getTokens(EntityDSLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(EntityDSLParser.INT, i);
		}
		public LENGTHContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitLENGTH(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class REQUIREDContext extends ModifierContext {
		public REQUIREDContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitREQUIRED(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UNIQUEContext extends ModifierContext {
		public UNIQUEContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitUNIQUE(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PRIMARYContext extends ModifierContext {
		public PRIMARYContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitPRIMARY(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GENERATEDContext extends ModifierContext {
		public GENERATEDContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitGENERATED(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MAX_LENGTHContext extends ModifierContext {
		public TerminalNode INT() { return getToken(EntityDSLParser.INT, 0); }
		public MAX_LENGTHContext(ModifierContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityDSLVisitor ) return ((EntityDSLVisitor<? extends T>)visitor).visitMAX_LENGTH(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_modifier);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				_localctx = new PRIMARYContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				match(T__7);
				}
				break;
			case T__8:
				_localctx = new GENERATEDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				match(T__8);
				}
				break;
			case T__9:
				_localctx = new REQUIREDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				match(T__9);
				}
				break;
			case T__10:
				_localctx = new UNIQUEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(50);
				match(T__10);
				}
				break;
			case T__11:
				_localctx = new OPTIONALContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(51);
				match(T__11);
				}
				break;
			case T__12:
				_localctx = new MINContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(52);
				match(T__12);
				setState(53);
				match(T__13);
				setState(54);
				match(INT);
				setState(55);
				match(T__14);
				}
				break;
			case T__15:
				_localctx = new MAXContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(56);
				match(T__15);
				setState(57);
				match(T__13);
				setState(58);
				match(INT);
				setState(59);
				match(T__14);
				}
				break;
			case T__16:
				_localctx = new LENGTHContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(60);
				match(T__16);
				setState(61);
				match(T__13);
				setState(62);
				match(INT);
				setState(63);
				match(T__17);
				setState(64);
				match(INT);
				setState(65);
				match(T__14);
				}
				break;
			case T__18:
				_localctx = new MIN_LENGTHContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(66);
				match(T__18);
				setState(67);
				match(T__13);
				setState(68);
				match(INT);
				setState(69);
				match(T__14);
				}
				break;
			case T__19:
				_localctx = new MAX_LENGTHContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(70);
				match(T__19);
				setState(71);
				match(T__13);
				setState(72);
				match(INT);
				setState(73);
				match(T__14);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0017M\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0005\u0000\f\b\u0000\n\u0000\f\u0000\u000f\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"\u0017\b\u0001\n\u0001\f\u0001\u001a\t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\"\b\u0002\n\u0002"+
		"\f\u0002%\t\u0002\u0001\u0002\u0003\u0002(\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003.\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004K\b\u0004\u0001\u0004\u0000\u0000\u0005\u0000"+
		"\u0002\u0004\u0006\b\u0000\u0000U\u0000\r\u0001\u0000\u0000\u0000\u0002"+
		"\u0012\u0001\u0000\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u0006"+
		"-\u0001\u0000\u0000\u0000\bJ\u0001\u0000\u0000\u0000\n\f\u0003\u0002\u0001"+
		"\u0000\u000b\n\u0001\u0000\u0000\u0000\f\u000f\u0001\u0000\u0000\u0000"+
		"\r\u000b\u0001\u0000\u0000\u0000\r\u000e\u0001\u0000\u0000\u0000\u000e"+
		"\u0010\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u0010\u0011"+
		"\u0005\u0000\u0000\u0001\u0011\u0001\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0005\u0001\u0000\u0000\u0013\u0014\u0005\u0015\u0000\u0000\u0014\u0018"+
		"\u0005\u0002\u0000\u0000\u0015\u0017\u0003\u0004\u0002\u0000\u0016\u0015"+
		"\u0001\u0000\u0000\u0000\u0017\u001a\u0001\u0000\u0000\u0000\u0018\u0016"+
		"\u0001\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u001b"+
		"\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000\u0000\u0000\u001b\u001c"+
		"\u0005\u0003\u0000\u0000\u001c\u0003\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0005\u0015\u0000\u0000\u001e\u001f\u0005\u0004\u0000\u0000\u001f#\u0003"+
		"\u0006\u0003\u0000 \"\u0003\b\u0004\u0000! \u0001\u0000\u0000\u0000\""+
		"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&(\u0005\u0005"+
		"\u0000\u0000\'&\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(\u0005"+
		"\u0001\u0000\u0000\u0000).\u0005\u0015\u0000\u0000*+\u0005\u0015\u0000"+
		"\u0000+,\u0005\u0006\u0000\u0000,.\u0005\u0007\u0000\u0000-)\u0001\u0000"+
		"\u0000\u0000-*\u0001\u0000\u0000\u0000.\u0007\u0001\u0000\u0000\u0000"+
		"/K\u0005\b\u0000\u00000K\u0005\t\u0000\u00001K\u0005\n\u0000\u00002K\u0005"+
		"\u000b\u0000\u00003K\u0005\f\u0000\u000045\u0005\r\u0000\u000056\u0005"+
		"\u000e\u0000\u000067\u0005\u0016\u0000\u00007K\u0005\u000f\u0000\u0000"+
		"89\u0005\u0010\u0000\u00009:\u0005\u000e\u0000\u0000:;\u0005\u0016\u0000"+
		"\u0000;K\u0005\u000f\u0000\u0000<=\u0005\u0011\u0000\u0000=>\u0005\u000e"+
		"\u0000\u0000>?\u0005\u0016\u0000\u0000?@\u0005\u0012\u0000\u0000@A\u0005"+
		"\u0016\u0000\u0000AK\u0005\u000f\u0000\u0000BC\u0005\u0013\u0000\u0000"+
		"CD\u0005\u000e\u0000\u0000DE\u0005\u0016\u0000\u0000EK\u0005\u000f\u0000"+
		"\u0000FG\u0005\u0014\u0000\u0000GH\u0005\u000e\u0000\u0000HI\u0005\u0016"+
		"\u0000\u0000IK\u0005\u000f\u0000\u0000J/\u0001\u0000\u0000\u0000J0\u0001"+
		"\u0000\u0000\u0000J1\u0001\u0000\u0000\u0000J2\u0001\u0000\u0000\u0000"+
		"J3\u0001\u0000\u0000\u0000J4\u0001\u0000\u0000\u0000J8\u0001\u0000\u0000"+
		"\u0000J<\u0001\u0000\u0000\u0000JB\u0001\u0000\u0000\u0000JF\u0001\u0000"+
		"\u0000\u0000K\t\u0001\u0000\u0000\u0000\u0006\r\u0018#\'-J";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}