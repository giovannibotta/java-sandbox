package net.giovannibotta.sets;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;

/**
 * @author giovanni
 * @since 4/15/14
 */
public final class DisjointSetForest {
    private final int[] parent;
    private final int[] rank;

    public DisjointSetForest(int size) {
        parent = new int[size];
        rank = new int[size];
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 0);
    }

    /**
     * Makes a set containing element x only.
     *
     * @param x Integer identifying an element.
     * @return The new set just created.
     * @throws IllegalArgumentException if i is not smaller than the total size of the forest.
     * @throws IllegalStateException    If element i is already in some set in the forest.
     */
    public int make(int x) {
        if (x >= parent.length)
            throw new IllegalArgumentException("The forest can only contain elements from 0 to " + (parent.length - 1));
        if (parent[x] >= 0)
            throw new IllegalStateException("Element " + x + " is already in set " + parent[x]);
        parent[x] = x;
        return x;
    }

    /**
     * Performs the union of the two sets containing the two given elements. If the
     *
     * @param x
     * @param y
     * @return The element representing the set resulting from the union.
     */
    public int union(int x, int y) {
        int xp, yp;
        if (parent[x] == -1) xp = make(x);
        else xp = find(x);
        if (parent[y] == -1) yp = make(y);
        else yp = find(y);
        return link(xp, yp);
    }

    private int link(int x, int y) {
        if (rank[x] > rank[y]) {
            parent[y] = x;
            return x;
        } else {
            parent[x] = y;
            if (rank[x] == rank[y]) rank[y] += 1;
            return y;
        }
    }

    /**
     * Finds the set associated with element x or -1 if the element is not in any set.
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (parent[x] == -1) return -1;
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    /**
     * Total number of elements in the forest.
     *
     * @return
     */
    public int size() {
        return parent.length;
    }

    @Override
    public String toString() {
        // TODO: there might be a faster way to do this
        final int n = parent.length;
        Multimap<Integer, Integer> m = HashMultimap.create(n, n / 2);
        for (int i = 0; i < n; i++) {
            int p = find(i);
            if (p >= 0) m.put(p, i);
        }
        return m.asMap().values().toString();
    }
}
