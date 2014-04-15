package net.giovannibotta.antlr.example.bool2.visitor;

import com.google.common.collect.ImmutableMap;
import net.giovannibotta.antlr.example.bool2.BooleanExpressions2BaseVisitor;
import net.giovannibotta.antlr.example.bool2.BooleanExpressions2Parser;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Map;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class BooleanExpressionEval2 extends BooleanExpressions2BaseVisitor<Boolean> {
    private final ArithmeticExpressionPartEval2 expressionPartEval;

    private final Map<String, Boolean> bindings;

    public BooleanExpressionEval2(Map<String, Boolean> booleanBindings, Map<String, Integer> integerBindings) {
        this.bindings = ImmutableMap.copyOf(booleanBindings);
        expressionPartEval = new ArithmeticExpressionPartEval2(integerBindings);
    }

    @Override
    public Boolean visitStart(@NotNull BooleanExpressions2Parser.StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitBoolAtomExpr(@NotNull BooleanExpressions2Parser.BoolAtomExprContext ctx) {
        if (ctx.constant != null)
            return Boolean.valueOf(ctx.constant.getText());
        Boolean val = bindings.get(ctx.variable.getText());
        if (val == null)
            throw new IllegalStateException("Variable " + ctx.variable.getText() + " is not bound to a boolean value!");
        return val;
    }

    @Override
    public Boolean visitBoolOpExpr(@NotNull BooleanExpressions2Parser.BoolOpExprContext ctx) {
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
    public Boolean visitBoolParenExp(@NotNull BooleanExpressions2Parser.BoolParenExpContext ctx) {
        return visit(ctx.child);
    }

    @Override
    public Boolean visitArithmeticRelation(@NotNull BooleanExpressions2Parser.ArithmeticRelationContext ctx) {
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
    public Boolean visitBoolNotExp(@NotNull BooleanExpressions2Parser.BoolNotExpContext ctx) {
        return !visit(ctx.child);
    }
}
