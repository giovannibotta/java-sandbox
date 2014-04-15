// Generated from antlr-examples/src/main/java/net/giovannibotta/antlr/example/bool2/BooleanExpressions2.g4 by ANTLR 4.2
package net.giovannibotta.antlr.example.bool2;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BooleanExpressions2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BooleanExpressions2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#boolAtomExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolAtomExpr(@NotNull BooleanExpressions2Parser.BoolAtomExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#boolParenExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolParenExp(@NotNull BooleanExpressions2Parser.BoolParenExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull BooleanExpressions2Parser.StartContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#arithAtomExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithAtomExpr(@NotNull BooleanExpressions2Parser.ArithAtomExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#boolOpExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpr(@NotNull BooleanExpressions2Parser.BoolOpExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#arithOpExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOpExpr(@NotNull BooleanExpressions2Parser.ArithOpExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#boolNotExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolNotExp(@NotNull BooleanExpressions2Parser.BoolNotExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#arithRel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithRel(@NotNull BooleanExpressions2Parser.ArithRelContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#arithmeticRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticRelation(@NotNull BooleanExpressions2Parser.ArithmeticRelationContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions2Parser#arithParenExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithParenExp(@NotNull BooleanExpressions2Parser.ArithParenExpContext ctx);
}