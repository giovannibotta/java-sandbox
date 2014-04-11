package net.giovannibotta.unusual;

import java.util.Arrays;

/**
 * @author giovanni
 * @since 4/10/14
 */
public class YoungTableau {
    private static final int INFINITY = Integer.MAX_VALUE;
    private final int[][] t;
    private final int m, n;
    private int size = 0;

    YoungTableau(int m, int n) {
        this.m = m;
        this.n = n;
        t = new int[m][n];
        for (int[] r : t) Arrays.fill(r, INFINITY);
    }

    YoungTableau(int[][] t) {
        this.t = t;
        this.m = t.length;
        this.n = t[0].length;
        for (int[] r : t) for (int e : r) if (e != INFINITY) size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return t[0][0] == INFINITY;
    }

    public int min() {
        return t[0][0];
    }

    public int extractMin() {
        int min = min();
        if (min == INFINITY) return INFINITY;
        size--;
        t[0][0] = INFINITY;
        fixDown(0, 0);
        return min;
    }

    public boolean contains(final int e) {
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (t[i][j] == e) return true;
            if (t[i][j] > e) i--;
            else j++;
        }
        return false;
    }

    public boolean add(final int e) {
        if (contains(e)) return false;

        if (t[m - 1][n - 1] < INFINITY) throw new RuntimeException("Tableau too small to accomodate element " + e);

        t[m - 1][n - 1] = e;
        fixUp(m - 1, n - 1);

        return true;
    }

    private void fixUp(int i, int j) {
        if (i == 0) { // first row
            while (j > 0 && t[i][j] < t[i][j - 1]) {
                int x = t[i][j];
                t[i][j] = t[i][--j];
                t[i][j] = x;
            }
        } else if (j == 0) { // first column
            while (i > 0 && t[i][j] < t[i - 1][j]) {
                int x = t[i][j];
                t[i][j] = t[--i][j];
                t[i][j] = x;
            }
        } else {
            int rmax = t[i][j - 1]; // max for row i
            int cmax = t[i - 1][j]; // max for col j
            if (rmax > cmax) {
                int x = t[i][j];
                if (x < rmax) {
                    t[i][j] = t[i][--j];
                    t[i][j] = x;
                    fixUp(i, j);
                }
            } else {
                int x = t[i][j];
                if (x < cmax) {
                    t[i][j] = t[--i][j];
                    t[i][j] = x;
                    fixUp(i, j);
                }
            }
        }
    }

    private void fixDown(int i, int j) {
        if (i == m - 1) { // last row
            while (j < n - 1 && t[i][j] > t[i][j + 1]) {
                int x = t[i][j];
                t[i][j] = t[i][++j];
                t[i][j] = x;
            }
        } else if (j == n - 1) { // last column
            while (i < m - 1 && t[i][j] > t[i + 1][j]) {
                int x = t[i][j];
                t[i][j] = t[++i][j];
                t[i][j] = x;
            }
        } else {
            int rmin = t[i][j + 1]; // min for row i
            int cmin = t[i + 1][j]; // min for col j
            if (rmin < cmin) {
                int x = t[i][j];
                if (x > rmin) {
                    t[i][j] = rmin;
                    t[i][++j] = x;
                    fixDown(i, j);
                }
            } else {
                int x = t[i][j];
                if (x > cmin) {
                    t[i][j] = cmin;
                    t[++i][j] = x;
                    fixDown(i, j);
                }
            }
        }
    }

    public String toTable() {
        int digitLength = size() > 0 ? Math.max(3, Integer.toString(max()).length()) : 3;
        String fmt = "%" + digitLength + "d ";
        String inf = String.format("%" + digitLength + "s ", "Inf");
        StringBuilder b = new StringBuilder();
        for (int[] r : t) {
            b.append("[ ");
            for (int e : r) b.append(e < INFINITY ? String.format(fmt, e) : inf);
            b.append("]").append(System.lineSeparator());
        }
        b.delete(b.length() - 1, b.length());
        return b.toString();
    }

    private int max() {
        // FIXME: possibly suboptimal
        int max = Integer.MIN_VALUE;
        for (int[] r : t) for (int e : r) if (e < INFINITY && e > max) max = e;
        return max;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder().append("{");
        for (int[] r : t) for (int e : r) if (e != INFINITY) b.append(e).append(",");
        if (size() > 0) b.delete(b.length() - 1, b.length());
        b.append("}");
        return b.toString();
    }
}
