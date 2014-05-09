package net.giovannibotta.hashtable;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * @author giovanni
 * @since 5/7/14
 */
public class OpenAddressingHashTable<K, V> extends AbstractMap<K, V> {
    private final ToIntFunction<K> hash1;
    private final ToIntBiFunction<K, Integer> hash2;
    private final BiPredicate<K, K> equals;
    private SimpleImmutableEntry<K, V> table[];
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
            if (eq(k, table[pos].getKey())) {
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
            // mod could be negative
            while (pos < 0) pos += table.length;
        }
        // if I got here, I went through the whole table without any match
        // note that if the table is not full, this will return -(x+1) where x is the position where the item should be put
        // or -(table.length + 1) if the position of the element in the table could not be determined (could happen with double hashing)
        if (firstDeleted >= 0) return -(firstDeleted + 1);
        if (table[pos] == null) return -(pos + 1);
        return -(table.length + 1);
//        return firstDeleted >= 0 ? -(firstDeleted + 1) : -(pos + 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object k) {
        if (k == null) throw new NullPointerException("Null key");
        K key = (K) k;
        return safeContainsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (SimpleImmutableEntry<K, V> e : table)
            if (e.getValue() == value || (value != null && value.equals(e.getValue()))) return true;
        return false;
    }

    public boolean safeContainsKey(K k) {
        return find(k) >= 0;
    }

    @Override
    public V get(Object k) {
        K key = fromObject(k);
        return safeGet(key);
    }

    @SuppressWarnings("unchecked")
    private K fromObject(Object k) {
        if (k == null) throw new NullPointerException("Null key");
        return (K) k;
    }

    @Override
    public V remove(Object key) {
        K k = fromObject(key);
        int pos = find(k);
        if (pos < 0) return null;
        V old = table[pos].getValue();
        table[pos] = deleted;
        size--;
        return old;
    }

    public V safeGet(K k) {
        int pos = find(k);
        if (pos >= 0) return table[pos].getValue();
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        table = null;
        checkSize();
    }

    private V blindPut(SimpleImmutableEntry<K, V> newEntry) {
        int pos = find(newEntry.getKey());
        if (pos >= 0) {
            V old = table[pos].getValue();
            table[pos] = newEntry;
            return old;
        } else {
            int j = -pos - 1;
            if (j >= table.length) throw new RuntimeException("Unable to position key " + newEntry.getKey());
            table[-pos - 1] = newEntry;
            size++;
            return null;
        }
    }

    @Override
    public V put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("Key can not be null");
        checkSize();
        return blindPut(new SimpleImmutableEntry<>(k, v));
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Method entrySet not supported at this moment");
    }

    @SuppressWarnings("unchecked")
    private void checkSize() {
        if (table == null) table = new SimpleImmutableEntry[1];
        if (size == table.length || size < table.length / 4) {
            int newSize = 2 * table.length;
            if (size < table.length / 4) {
                newSize = Math.max(1, table.length / 2);
            }

            SimpleImmutableEntry<K, V>[] oldTable = table;
            table = new SimpleImmutableEntry[newSize];

            // reset the size because blindPut will increment it
            size = 0;
            for (SimpleImmutableEntry<K, V> e : oldTable)
                if (e != null && e != deleted)
                    blindPut(e);
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{ ");
        for (SimpleImmutableEntry<K, V> e : table)
            if (e != null && e != deleted) b.append(e.getKey()).append(" -> ").append(e.getValue()).append(", ");
        if (size > 0) b.delete(b.length() - 2, b.length());
        return b.append(" }").toString();
    }

    // the key can never be null so null here is fine
    private final SimpleImmutableEntry<K, V> deleted = new SimpleImmutableEntry<>(null, null);

    private class EntrySet extends AbstractSet<Entry<K, V>> {
        @Override
        public int size() {
            return OpenAddressingHashTable.this.size();
        }

        @Override
        public boolean isEmpty() {
            return OpenAddressingHashTable.this.isEmpty();
        }

        @SuppressWarnings("unchecked")
        private Entry<K, V> fromObject(Object o) {
            if (o == null) throw new NullPointerException("Null entry");
            Entry<K, V> e = (Entry<K, V>) o;
            return e;
        }

        @Override
        public boolean contains(Object o) {
            Entry<K, V> e = fromObject(o);

            if (!OpenAddressingHashTable.this.containsKey(e.getKey())) return false;

            V val = OpenAddressingHashTable.this.get(e.getKey());
            return val == e.getValue() || (val != null && val.equals(e.getValue()));
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean add(Entry<K, V> kvEntry) {
            throw new UnsupportedOperationException("Can not add to this view using the add method");
        }

        @Override
        public boolean remove(Object o) {
            Entry<K, V> e = fromObject(o);
            return OpenAddressingHashTable.this.remove(e.getKey()) != null;
        }

        @Override
        public boolean addAll(Collection<? extends Entry<K, V>> c) {
            throw new UnsupportedOperationException("Can not add to this view using the addAll method");
        }

        @Override
        public void clear() {
            OpenAddressingHashTable.this.clear();
        }

        @Override
        public String toString() {
            return OpenAddressingHashTable.this.toString();
        }
    }
}
