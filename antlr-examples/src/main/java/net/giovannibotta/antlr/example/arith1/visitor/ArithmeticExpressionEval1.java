package net.giovannibotta.antlr.example.arith1.visitor;

import net.giovannibotta.antlr.example.arith1.Expressions1BaseVisitor;

import static net.giovannibotta.antlr.example.arith1.Expressions1Parser.*;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class ArithmeticExpressionEval1 extends Expressions1BaseVisitor<Integer> {
    @Override
    public Integer visitStart(StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitOpExpr(OpExprContext ctx) {
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
    public Integer visitAtomExpr(AtomExprContext ctx) {
        Integer atom = new Integer(ctx.atom.getText());
        return atom;
    }
}
