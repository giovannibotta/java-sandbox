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
public class ArithmeticExpressionPartEval2 extends BooleanExpressions2BaseVisitor<Integer> {
    private final Map<String, Integer> bindings;

    public ArithmeticExpressionPartEval2(Map<String, Integer> bindings) {
        this.bindings = ImmutableMap.copyOf(bindings);
    }

    @Override
    public Integer visitArithAtomExpr(@NotNull BooleanExpressions2Parser.ArithAtomExprContext ctx) {
        if (ctx.constant != null)
            return new Integer(ctx.constant.getText());
        Integer val = bindings.get(ctx.variable.getText());
        if (val == null)
            throw new IllegalStateException("Variable " + ctx.variable.getText() + " is not bound to an integer value!");
        return val;
    }

    @Override
    public Integer visitArithOpExpr(@NotNull BooleanExpressions2Parser.ArithOpExprContext ctx) {
        int left = visit(ctx.left);
        int right = visit(ctx.right);
        String op = ctx.op.getText();
        switch (op.charAt(0)) {
            case '*':
                return left * right;
            case '/':
                return left / right;
            case '+':
                return left + right;
            case '-':
                return left - right;
            default:
                throw new IllegalArgumentException("Unkown opeator " + op);
        }
    }

    @Override
    public Integer visitArithParenExp(@NotNull BooleanExpressions2Parser.ArithParenExpContext ctx) {
        return visit(ctx.child);
    }
}
