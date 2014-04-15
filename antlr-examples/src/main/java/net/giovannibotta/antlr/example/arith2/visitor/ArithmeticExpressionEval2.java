package net.giovannibotta.antlr.example.arith2.visitor;

import net.giovannibotta.antlr.example.arith2.Expressions2BaseVisitor;

import static net.giovannibotta.antlr.example.arith2.Expressions2Parser.*;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class ArithmeticExpressionEval2 extends Expressions2BaseVisitor<Integer> {
    @Override
    public Integer visitStart(StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitOpExpr(OpExprContext ctx) {
        if (ctx.op == null) return visit(ctx.child);
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
