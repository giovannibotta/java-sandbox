package giovannibotta.vext;

/**
 * @author giovanni
 * @since 3/18/14
 */
public class OrderedObject implements Comparable<OrderedObject> {
    private final String blah;

    public OrderedObject(String blah) {
        this.blah = blah;
    }

    @Override
    public int compareTo(OrderedObject o) {
        return blah.compareTo(o.blah);
    }
}
