package net.giovannibotta.antlr.example.bool1.visitor;

import net.giovannibotta.antlr.example.bool1.BooleanExpressions1BaseVisitor;
import net.giovannibotta.antlr.example.bool1.BooleanExpressions1Parser;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class ArithmeticExpressionPartEval1 extends BooleanExpressions1BaseVisitor<Integer> {
    @Override
    public Integer visitArithAtomExpr(@NotNull BooleanExpressions1Parser.ArithAtomExprContext ctx) {
        return new Integer(ctx.arithmeticAtom.getText());
    }

    @Override
    public Integer visitArithOpExpr(@NotNull BooleanExpressions1Parser.ArithOpExprContext ctx) {
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
    public Integer visitArithParenExp(@NotNull BooleanExpressions1Parser.ArithParenExpContext ctx) {
        return visit(ctx.child);
    }
}
