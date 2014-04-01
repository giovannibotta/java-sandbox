package giovannibotta.vext;

/**
 * @author giovanni
 * @since 3/18/14
 */
public interface ExtendedComparable<T> extends Comparable<T> {
    public default boolean gt(T other) {
        return compareTo(other) > 0;
    }

    public default boolean gte(T other) {
        return compareTo(other) >= 0;
    }

    public default boolean lt(T other) {
        return compareTo(other) < 0;
    }

    public default boolean lte(T other) {
        return compareTo(other) <= 0;
    }
}
