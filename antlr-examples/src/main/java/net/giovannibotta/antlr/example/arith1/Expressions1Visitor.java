// Generated from antlr-examples/src/main/java/net/giovannibotta/antlr/example/arith1/Expressions1.g4 by ANTLR 4.2
package net.giovannibotta.antlr.example.arith1;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Expressions1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Expressions1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Expressions1Parser#opExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(@NotNull Expressions1Parser.OpExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link Expressions1Parser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull Expressions1Parser.StartContext ctx);

	/**
	 * Visit a parse tree produced by {@link Expressions1Parser#atomExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(@NotNull Expressions1Parser.AtomExprContext ctx);
}