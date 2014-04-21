package net.giovannibotta.spoj.cmexpr;

import java.util.Stack;

/**
 * CMEXPR
 *
 * @author giovanni
 * @since 4/11/14
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.valueOf(r.readLine());
        Stack<Exp> expressions = new Stack<Exp>();
        for (int j = 0; j < n; j++) {
            String exp = r.readLine();
            for (int i = 0; i < exp.length(); i++) {
                char c = exp.charAt(i);
                if (c >= 'a' && c <= 'z') expressions.push(new Exp(c));
                else if (c == '+' || c == '-' || c == '*' || c == '/') expressions.push(new Exp(c));
                else while (c == ')' && expressions.size() >= 3) {
                        Exp right = expressions.pop();
                        Exp op = expressions.pop();
                        op.l = expressions.pop();
                        op.r = right;
                        expressions.push(op);
                    }
            }
            while (expressions.size() >= 3) {
                Exp right = expressions.pop();
                Exp op = expressions.pop();
                op.l = expressions.pop();
                op.r = right;
                expressions.push(op);
            }
            System.out.println(expressions.pop());
        }
    }

    private final static class Exp {
        char c;
        Exp l, r;

        private Exp(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return (l == null && r == null) ? "" + c : ("" +
                    ((l.c == '+' || l.c == '-') && (c == '*' || c == '/') ? "(" + l + ")" : l) +
                    c +
                    (((r.c == '+' || r.c == '-') && (c == '*' || c == '/'))
                            || ((c == '-' && (r.c == '+' || r.c == '-')) || (c == '/' && r.c == '/'))
                            ? "(" + r + ")" : r));
        }
    }
}
