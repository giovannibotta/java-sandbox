package net.giovannibotta.expression;

/**
 * @author giovanni
 * @since 4/11/14
 */
public final class Expression {
    private final Token op;
    private final Expression leftOp, rightOp;

    private Expression(Token op, Expression leftOp, Expression rightOp) {
        if (op == null) throw new IllegalArgumentException("Operator can't be null");
        this.op = op;
        if (rightOp != null && op.nArgs() < 1)
            throw new IllegalArgumentException("Operator " + op + " does not accept operands");
        this.rightOp = rightOp;
        if (leftOp != null && op.nArgs() < 2)
            throw new IllegalArgumentException("Operator " + op + " does not accept 2 operands");
        this.leftOp = leftOp;
    }

    public static Expression not(Expression operand) {
        return new Expression(UnaryOp.not, null, operand);
    }

    public static Expression or(Expression leftOperand, Expression rightOperand) {
        return new Expression(BinaryOp.or, leftOperand, rightOperand);
    }

    public static Expression eq(String var, int value) {
        return new Expression(BinaryOp.eq, var(var), val(value));
    }

    public static Expression neq(String var, int value) {
        return new Expression(BinaryOp.neq, var(var), val(value));
    }

    public static Expression lt(String var, int value) {
        return new Expression(BinaryOp.lt, var(var), val(value));
    }

    public static Expression gt(String var, int value) {
        return new Expression(BinaryOp.gt, var(var), val(value));
    }

    public static Expression lte(String var, int value) {
        return new Expression(BinaryOp.lte, var(var), val(value));
    }

    public static Expression gte(String var, int value) {
        return new Expression(BinaryOp.gte, var(var), val(value));
    }

    public static Expression and(Expression leftOperand, Expression rightOperand) {
        return new Expression(BinaryOp.and, leftOperand, rightOperand);
    }

    public static Expression var(String var) {
        return new Expression(new Var(var), null, null);
    }

    public static Expression val(Object val) {
        return new Expression(new Val(val), null, null);
    }

    public static Expression predicate(String predicate, String varName) {
        return new Expression(new Predicate(predicate), null, var(varName));
    }

    private static class Predicate implements Operator {
        private final String symbol;

        private Predicate(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public int nArgs() {
            return 1;
        }

        @Override
        public String symbol() {
            return symbol;
        }
    }

    private static class Var implements Operand {
        private final String name;

        private Var(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int nArgs() {
            return 0;
        }

        @Override
        public String symbol() {
            return toString();
        }
    }

    private static class Val implements Operand {
        private final Object value;

        private Val(Object val) {
            this.value = val;
        }

        public Object val() {
            return value;
        }

        @Override
        public String toString() {
            return "" + value;
        }

        @Override
        public int nArgs() {
            return 0;
        }

        @Override
        public String symbol() {
            return toString();
        }
    }

    private static interface Token {
        int nArgs();

        String symbol();
    }

    private static interface Operator extends Token {
    }

    private static interface Operand extends Token {
    }

    private static enum UnaryOp implements Operator {
        not("~");

        private final String symbol;

        UnaryOp(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public int nArgs() {
            return 2;
        }

        @Override
        public String symbol() {
            return symbol;
        }
    }

    private static enum BinaryOp implements Operator {
        or("|"), and("&"), eq("=="), neq("~="), lt("<"), gt(">"), lte("<="), gte(">=");

        private final String symbol;

        BinaryOp(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public int nArgs() {
            return 2;
        }

        @Override
        public String symbol() {
            return symbol;
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        inorder(this, b);
        return b.toString();
    }

    private void inorder(Expression expression, StringBuilder b) {
        if (expression == null) return;
        boolean needParens = expression != this && // this means the expression is the root, no parenthesis needed
                (
                        (expression.rightOp != null && expression.rightOp.op instanceof Operator) ||
                                (expression.leftOp != null && expression.leftOp.op instanceof Operator)
                );

        if (needParens) b.append("(");
        else if (expression.rightOp != null && expression.rightOp.op instanceof Operand) b.append(" ");

        inorder(expression.leftOp, b);

        b.append(expression.op.symbol());
        if (expression.op instanceof Predicate) b.append("(");

        inorder(expression.rightOp, b);
        if (expression.op instanceof Predicate) b.append(")");

        if (needParens) b.append(")");
        else if (expression.leftOp != null && expression.leftOp.op instanceof Operand) b.append(" ");
    }
}
