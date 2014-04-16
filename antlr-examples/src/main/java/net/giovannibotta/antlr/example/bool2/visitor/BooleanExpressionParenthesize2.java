package net.giovannibotta.antlr.example.bool2.visitor;

import net.giovannibotta.antlr.example.bool2.BooleanExpressions2BaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import static net.giovannibotta.antlr.example.bool2.BooleanExpressions2Parser.*;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class BooleanExpressionParenthesize2 extends BooleanExpressions2BaseVisitor<String> {
    @Override
    public String visitStart(@NotNull StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitArithAtomExpr(@NotNull ArithAtomExprContext ctx) {
        return ctx.constant != null ? ctx.constant.getText() : ctx.variable.getText();
    }

    @Override
    public String visitBoolAtomExpr(@NotNull BoolAtomExprContext ctx) {
        return ctx.constant != null ? ctx.constant.getText() : ctx.variable.getText();
    }

    @Override
    public String visitBoolParenExp(@NotNull BoolParenExpContext ctx) {
        return visit(ctx.child);
    }

    @Override
    public String visitArithParenExp(@NotNull ArithParenExpContext ctx) {
        return visit(ctx.child);
    }

    @Override
    public String visitBoolOpExpr(@NotNull BoolOpExprContext ctx) {
        return parens(visit(ctx.left) + " " + ctx.op.getText() + " " + visit(ctx.right));
    }

    @Override
    public String visitArithOpExpr(@NotNull ArithOpExprContext ctx) {
        return parens(visit(ctx.left) + " " + ctx.op.getText() + " " + visit(ctx.right));
    }

    @Override
    public String visitBoolNotExp(@NotNull BoolNotExpContext ctx) {
        return "~" + parens(visit(ctx.child));
    }

    @Override
    public String visitArithmeticRelation(@NotNull ArithmeticRelationContext ctx) {
        return parens(visit(ctx.left) + " " + ctx.op.getText() + " " + visit(ctx.right));
    }

    @Override
    public String visitArithRel(@NotNull ArithRelContext ctx) {
        return visit(ctx.arithmeticRelation());
    }

    private String parens(String s) {
        return "( " + s + " )";
    }
}
