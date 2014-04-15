// Generated from antlr-examples/src/main/java/net/giovannibotta/antlr/example/bool1/BooleanExpressions1.g4 by ANTLR 4.2
package net.giovannibotta.antlr.example.bool1;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BooleanExpressions1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BooleanExpressions1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#boolAtomExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolAtomExpr(@NotNull BooleanExpressions1Parser.BoolAtomExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#boolParenExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolParenExp(@NotNull BooleanExpressions1Parser.BoolParenExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull BooleanExpressions1Parser.StartContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#arithAtomExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithAtomExpr(@NotNull BooleanExpressions1Parser.ArithAtomExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#boolOpExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpr(@NotNull BooleanExpressions1Parser.BoolOpExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#arithOpExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOpExpr(@NotNull BooleanExpressions1Parser.ArithOpExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#boolNotExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolNotExp(@NotNull BooleanExpressions1Parser.BoolNotExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#arithRel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithRel(@NotNull BooleanExpressions1Parser.ArithRelContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#arithmeticRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticRelation(@NotNull BooleanExpressions1Parser.ArithmeticRelationContext ctx);

	/**
	 * Visit a parse tree produced by {@link BooleanExpressions1Parser#arithParenExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithParenExp(@NotNull BooleanExpressions1Parser.ArithParenExpContext ctx);
}