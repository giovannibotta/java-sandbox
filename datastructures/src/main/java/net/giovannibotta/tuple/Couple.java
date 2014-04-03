package net.giovannibotta.tuple;

/**
 * @author giovanni
 * @since 4/3/14
 */
public final class Couple<T1, T2> {
    private final T1 left;
    private final T2 right;

    public static <X1, X2> Couple<X1, X2> create(X1 left, X2 right) {
        return new Couple<>(left, right);
    }

    private Couple(T1 left, T2 right) {
        if (left == null) throw new IllegalArgumentException("Left element can't be null");
        if (right == null) throw new IllegalArgumentException("Right element can't be null");
        this.left = left;
        this.right = right;
    }

    public T1 left() {
        return left;
    }

    public T2 right() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Couple)) return false;
        Couple couple = (Couple) o;
        return left.equals(couple.left) && right.equals(couple.right);

    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left + "," + right + ")";
    }
}
