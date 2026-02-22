// Generated from C:/Users/kanyi/IdeaProjects/codeGenerator/src/main/antlr4/EntityDSL.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EntityDSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EntityDSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EntityDSLParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(EntityDSLParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityDSLParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(EntityDSLParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityDSLParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(EntityDSLParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityDSLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EntityDSLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PRIMARY}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPRIMARY(EntityDSLParser.PRIMARYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GENERATED}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGENERATED(EntityDSLParser.GENERATEDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code REQUIRED}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitREQUIRED(EntityDSLParser.REQUIREDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UNIQUE}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUNIQUE(EntityDSLParser.UNIQUEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OPTIONAL}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOPTIONAL(EntityDSLParser.OPTIONALContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MIN}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMIN(EntityDSLParser.MINContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MAX}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMAX(EntityDSLParser.MAXContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LENGTH}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLENGTH(EntityDSLParser.LENGTHContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MIN_LENGTH}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMIN_LENGTH(EntityDSLParser.MIN_LENGTHContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MAX_LENGTH}
	 * labeled alternative in {@link EntityDSLParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMAX_LENGTH(EntityDSLParser.MAX_LENGTHContext ctx);
}