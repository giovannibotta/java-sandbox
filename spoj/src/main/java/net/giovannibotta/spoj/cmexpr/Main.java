package net.giovannibotta.spoj.cmexpr;

import java.util.Stack;

/**
 * CMEXPR
 *
 * @author giovanni
 * @since 4/11/14
 */
public class Main {
    private static final char[] stack = new char[100];
    private static int top = 0;

    public static void main(String[] args) {
        parse("((2*4-6/3)*(3*5+8/4))-(2+3)");
    }

    static ExpNode parse(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isDigit(c)) builder.append(c).append(' ');
            else if (isOperator(c)) {
                while (!stack.isEmpty()
                        && isOperator(stack.peek())
                        && priority(stack.peek()) > priority(c)) {
                    char op = stack.pop();
                    builder.append(op).append(' ');
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                char t;
                while ((t = stack.pop()) != '(') {
                    builder.append(t).append(' ');
                }
            }
        }
        while (!stack.isEmpty()) {
            char t = stack.pop();
            builder.append(t).append(' ');
        }
        builder.delete(builder.length() - 1, builder.length());
        System.out.println(builder);
        return null;
    }

    private static int priority(char c) {
        return (c == '+' || c == '-') ? 0 : 1;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static Operator operator(char c) {
        if (c == '+') return Operator.plus;
        if (c == '-') return Operator.minus;
        if (c == '*') return Operator.times;
        if (c == '/') return Operator.div;
        throw new IllegalArgumentException("Trying to parse character " + c + " as operator");
    }

    private static boolean isToken(char c) {
        return c != '(' && c != ')' & c != ' ';
    }

    static interface Exp {
    }

    static class Var implements Exp {
        final char v;

        Var(char v) {
            this.v = v;
        }
    }

    static class ExpNode implements Exp {
        final Operator op;
        Exp l, r;

        ExpNode(Exp l, Operator op, Exp r) {
            this.op = op;
            this.l = l;
            this.r = r;
        }
    }

    static enum Operator {
        plus(0), minus(0), times(1), div(1);
        private final int prec;

        Operator(int prec) {
            this.prec = prec;
        }

        int precedence() {
            return prec;
        }
    }
}
