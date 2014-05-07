package net.giovannibotta.hashtable;

import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * @author giovanni
 * @since 5/7/14
 */
public class OpenAddressingHashTable<K, V> {
    private final ToIntFunction<K> hash1;
    private final ToIntBiFunction<K, Integer> hash2;
    private final BiPredicate<K, K> equals;
    private Entry<K, V> table[];
    private int size = 0;

    public static <K, V> OpenAddressingHashTable<K, V> createLinear(ToIntFunction<K> hash, BiPredicate<K, K> equals) {
        return new OpenAddressingHashTable<>(hash, (k, i) -> i, equals);
    }

    public static <K, V> OpenAddressingHashTable<K, V> createQuadratic(ToIntFunction<K> hash, BiPredicate<K, K> equals) {
        return new OpenAddressingHashTable<>(hash, (k, i) -> i * i, equals);
    }

    public static <K, V> OpenAddressingHashTable<K, V> createDoubleHashing(ToIntFunction<K> hash1, ToIntFunction<K> hash2, BiPredicate<K, K> equals) {
        return new OpenAddressingHashTable<>(hash1, (k, i) -> i * hash2.applyAsInt(k), equals);
    }

    private int hash1(K k) {
        return hash1.applyAsInt(k);
    }

    private int hash2(K k, int i) {
        return hash2.applyAsInt(k, i);
    }

    private boolean eq(K k1, K k2) {
        return equals.test(k1, k2);
    }

    @SuppressWarnings("unchecked")
    private OpenAddressingHashTable(ToIntFunction<K> hash1, ToIntBiFunction<K, Integer> hash2, BiPredicate<K, K> equals) {
        this.hash1 = hash1;
        this.hash2 = hash2;
        this.equals = equals;
        checkSize();
    }

    private int find(K k) {
        int h = hash1(k);
        int pos = h % table.length;

        int i = 0;
        int firstDeleted = -1;
        while (i < table.length && table[pos] != null) {
            // keep track of the first entry marked as deleted
            if (table[pos] == deleted && firstDeleted == -1) firstDeleted = pos;
            // if I found the key, I'm done
            if (eq(table[pos].k, k)) {
                if (firstDeleted >= 0) {
                    // move the found entry to the first deleted entry if any
                    table[firstDeleted] = table[pos];
                    table[pos] = deleted;
                    pos = firstDeleted;
                }
                return pos;
            }
            // compute next index
            pos = (h + hash2(k, ++i)) % table.length;
        }
        // if I got here, I went through the whole table without any match
        // note that if the table is not full, this will return -(x+1) where x is the position where the item should be put
        return firstDeleted >= 0 ? -(firstDeleted + 1) : -(pos + 1);
    }

    public boolean containsKey(K k) {
        return find(k) >= 0;
    }

    public V get(K k) {
        int pos = find(k);
        if (pos >= 0) return table[pos].v;
        return null;
    }

    private boolean blindPut(K k, V v) {
        int pos = find(k);
        if (pos >= 0) {
            if ((v != null && v.equals(table[pos].v)) || table[pos].v == null) return false;
            table[pos].v = v;
            return true;
        } else {
            table[-pos - 1] = new Entry<>(k, v);
            size++;
            return true;
        }
    }

    public boolean put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("Key can not be null");
        checkSize();
        return blindPut(k, v);
    }

    @SuppressWarnings("unchecked")
    private void checkSize() {
        if (table == null) table = new Entry[1];
        if (size == table.length || size < table.length / 4) {
            int newSize = 2 * table.length;
            if (size < table.length / 4) {
                newSize = Math.max(1, table.length / 2);
            }

            Entry<K, V>[] oldTable = table;
            table = new Entry[newSize];

            // reset the size because blindPut will increment it
            size = 0;
            for (Entry<K, V> e : oldTable)
                if (e != null && e != deleted)
                    blindPut(e.k, e.v);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{ ");
        for (Entry<K, V> e : table) if (e != null && e != deleted) b.append(e.k).append(" : ").append(e.v).append(", ");
        if (size > 0) b.delete(b.length() - 2, b.length());
        return b.append(" }").toString();
    }

    // the key can never be null so null here is fine
    private final Entry<K, V> deleted = new Entry<>(null, null);

    public static class Entry<KK, VV> {
        private final KK k;
        private VV v;

        public Entry(KK key, VV value) {
            this.k = key;
            this.v = value;
        }
    }
}
