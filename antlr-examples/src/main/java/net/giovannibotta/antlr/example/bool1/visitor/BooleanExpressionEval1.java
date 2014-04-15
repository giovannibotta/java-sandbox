package net.giovannibotta.antlr.example.bool1.visitor;

import net.giovannibotta.antlr.example.bool1.BooleanExpressions1BaseVisitor;
import net.giovannibotta.antlr.example.bool1.BooleanExpressions1Parser;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class BooleanExpressionEval1 extends BooleanExpressions1BaseVisitor<Boolean> {
    ArithmeticExpressionPartEval1 expressionPartEval = new ArithmeticExpressionPartEval1();

    @Override
    public Boolean visitStart(@NotNull BooleanExpressions1Parser.StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitBoolAtomExpr(@NotNull BooleanExpressions1Parser.BoolAtomExprContext ctx) {
        return Boolean.valueOf(ctx.booleanAtom.getText());
    }

    @Override
    public Boolean visitBoolOpExpr(@NotNull BooleanExpressions1Parser.BoolOpExprContext ctx) {
        boolean left = visit(ctx.left);
        boolean right = visit(ctx.right);
        char op = ctx.op.getText().charAt(0);
        switch (op) {
            case '&':
                return left && right;
            case '|':
                return left || right;
            default:
                throw new IllegalArgumentException("Unkown opeator " + op);
        }
    }

    @Override
    public Boolean visitBoolParenExp(@NotNull BooleanExpressions1Parser.BoolParenExpContext ctx) {
        return visit(ctx.child);
    }

    @Override
    public Boolean visitArithmeticRelation(@NotNull BooleanExpressions1Parser.ArithmeticRelationContext ctx) {
        int l = expressionPartEval.visit(ctx.left);
        int r = expressionPartEval.visit(ctx.right);
        String op = ctx.op.getText();
        switch (op) {
            case "==":
                return l == r;
            case "!=":
                return l != r;
            case ">":
                return l > r;
            case "<":
                return l < r;
            case ">=":
                return l >= r;
            case "<=":
                return l <= r;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Boolean visitBoolNotExp(@NotNull BooleanExpressions1Parser.BoolNotExpContext ctx) {
        return !visit(ctx.child);
    }
}
