package net.giovannibotta.trees;

import static net.giovannibotta.trees.VEBTreeUtils.firstLargerPowerOf2;
import static net.giovannibotta.trees.VEBTreeUtils.lowerSquareRoot;
import static net.giovannibotta.trees.VEBTreeUtils.upperSquareRoot;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.Stack;

// TODO: parameterize and turn into a Map<Integer, E>
public class VEBTree extends AbstractSet<Integer> implements SortedSet<Integer> {
  private final VEB veb;
  private int size = 0;

  public VEBTree(int capacity) {
    if (capacity < 1)
      throw new IllegalArgumentException(
          "VEB tree capacity must be at least 1 but " + capacity
              + " was provided");
    veb = new VEB(firstLargerPowerOf2(capacity));
  }

  private static final class VEB {
    // u is always a power of 2
    final int u;
    final int lowSqrtU;
    int min, max;
    final VEB summary;
    // final VEB[] cluster;
    final HashMap<Integer, VEB> cluster;

    void reset() {
      min = -1;
      max = -1;
      if (cluster != null)
        cluster.clear();
      if (summary != null)
        summary.reset();
    }

    VEB(int u) {
      this.u = u;
      lowSqrtU = lowerSquareRoot(u);
      int upperSqrtU = upperSquareRoot(u);
      if (u > 2) {
        summary = new VEB(upperSqrtU);
        cluster = new HashMap<>();
        // cluster = new VEB[upperSqrtU];
        // Arrays.setAll(cluster, i -> new VEB(lowSqrtU));
      } else {
        // there are no summary/cluster for the base case
        summary = null;
        cluster = null;
      }
      min = -1;
      max = -1;
    }

    int min() {
      return min;
    }

    int clusterMin(int c) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null)
        return -1;
      return cVEB.min();
      // return cluster[c].min();
    }

    int max() {
      return max;
    }

    int clusterMax(int c) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null)
        return -1;
      return cVEB.max();
      // return cluster[c].max();
    }

    boolean member(final int x) {
      if (x == min || x == max)
        return true;
      if (u == 2)
        return false;
      final int h = high(x);
      final int l = low(x);
      return clusterMember(h, l);
    }

    boolean clusterMember(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null)
        return false;
      return cVEB.member(x);
      // return cluster[c].member(x);
    }

    int successor(final int x) {
      if (u == 2) {
        if (x == 0 && max == 1)
          return 1;
        else
          return -1;
      }
      if (min != -1 && x < min)
        return min;
      final int h = high(x);
      final int l = low(x);
      int maxLow = clusterMax(h);
      if (maxLow != -1 && l < maxLow) {
        final int offset = clusterSuccessor(h, l);
        return index(h, offset);
      }
      final int succCluster = summary.successor(h);
      if (succCluster == -1)
        return -1;
      final int offset = clusterMin(succCluster);
      return index(succCluster, offset);
    }

    int clusterSuccessor(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null)
        return -1;
      return cVEB.successor(x);
      // return cluster[c].successor(x);
    }

    int predecessor(final int x) {
      if (u == 2) {
        if (x == 1 && min == 0)
          return 0;
        return -1;
      }
      if (max != -1 && x > max)
        return max;
      final int h = high(x);
      final int l = low(x);
      final int minLow = clusterMin(h);
      if (minLow != -1 && l > minLow) {
        final int offset = clusterPredecessor(h, l);
        return index(h, offset);
      }
      int predCluster = summary.predecessor(h);
      if (predCluster == -1) {
        if (min != -1 && x > min)
          return min;
        return -1;
      }
      final int offset = clusterMax(predCluster);
      return index(predCluster, offset);
    }

    int clusterPredecessor(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null)
        return -1;
      return cVEB.predecessor(x);
      // return cluster[c].predecessor(x);
    }

    void emptyInsert(final int x) {
      min = x;
      max = x;
    }

    void clusterEmptyInsert(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null) {
        cVEB = new VEB(lowSqrtU);
        cluster.put(c, cVEB);
      }
      cVEB.emptyInsert(x);
      // cluster[c].emptyInsert(x);
    }

    boolean insert(int x) {
      if (min == x || max == x) {
        return false;
      }
      if (min == -1) {
        emptyInsert(x);
        return true;
      }
      boolean added = false;
      if (x < min) {
        int tmp = min;
        min = x;
        x = tmp;
        added = true;
      }
      if (u > 2) {
        final int h = high(x);
        final int l = low(x);
        if (clusterMin(h) == -1) {
          summary.insert(h);
          clusterEmptyInsert(h, l);
          added = true;
        } else
          added = clusterInsert(h, l);
      }
      if (x > max) {
        max = x;
        added = true;
      }
      return added;
    }

    boolean clusterInsert(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB == null) {
        cVEB = new VEB(lowSqrtU);
        cluster.put(c, cVEB);
      }
      return cVEB.insert(x);
      // return cluster[c].insert(x);
    }

    void delete(int x) {
      if (min == max) {
        min = -1;
        max = -1;
      } else if (u == 2) {
        if (x == 0)
          min = 1;
        else
          min = 0;
        max = min;
      } else {
        if (x == min) {
          int firstCluster = summary.min();
          x = index(firstCluster, clusterMin(firstCluster));
          min = x;
        }
        clusterDelete(high(x), low(x));
        if (clusterMin(high(x)) == -1) {
          summary.delete(high(x));
          if (x == max) {
            int summaryMax = summary.max();
            if (summaryMax == -1) {
              max = min;
            } else {
              max = index(summaryMax, clusterMax(summaryMax));
            }
          }
        } else if (x == max) {
          max = index(high(x), clusterMax(high(x)));
        }
      }
    }

    void clusterDelete(int c, int x) {
      VEB cVEB = cluster.get(c);
      if (cVEB != null)
        cVEB.delete(x);
      // cluster[c].delete(x);
    }

    int high(int x) {
      return x / lowSqrtU;
    }

    int low(int x) {
      return x % lowSqrtU;
    }

    int index(int x, int y) {
      return x * lowSqrtU + y;
    }
  }

  private static final class OptVEBIterator implements Iterator<Integer> {
    final Stack<VEB> toProcess;
    int nextSubtree = -1;
    int add = 0;

    private OptVEBIterator(VEB root) {
      toProcess = new Stack<VEB>();
      if (root.min() != -1)
        toProcess.push(root);
    }

    @Override
    public boolean hasNext() {
      return !toProcess.empty();
    }

    @Override
    public Integer next() {
      if (!hasNext())
        throw new NoSuchElementException();
      VEB node = toProcess.peek();
      if (nextSubtree == -1) {
        return add + node.min();
      } else {

      }
      return null;
    }

  }

  // TODO: this is not optimal, it calls successor n times, so it runs in
  // O(n*lglgu) instead of O(n).
  private static final class VEBIterator implements Iterator<Integer> {
    final VEB root;
    int current = -1;
    int next = -1;

    private VEBIterator(VEB root) {
      this.root = root;
      next = root.min();
    }

    @Override
    public boolean hasNext() {
      return next != -1;
    }

    @Override
    public Integer next() {
      if (!hasNext())
        throw new NoSuchElementException();
      current = next;
      next = root.successor(current);
      return current;
    }
  }

  @Override
  public boolean add(Integer e) {
    if (e == null)
      throw new IllegalArgumentException(
          "Can not insert null element into this set");
    int elem = e;
    if (elem < 0)
      throw new IllegalArgumentException(
          "Can not insert negative value into this set, " + elem);
    if (elem >= veb.u)
      throw new IllegalArgumentException(
          "Can not insert value larger than or equals to allowed capacity "
              + veb.u + " into this set, " + elem);
    if (veb.insert(elem)) {
      size++;
      return true;
    }
    return false;
  };

  @Override
  public boolean contains(Object o) {
    if (!(o instanceof Integer))
      return false;
    int i = (Integer) o;
    if (i < 0 || i >= veb.u)
      return false;
    return veb.member(i);
  }

  @Override
  public boolean remove(Object o) {
    if (!contains(o))
      return false;
    veb.delete((Integer) o);
    return true;
  };

  public Integer predecessor(Integer x) {
    if (x == null)
      return null;
    int i = x;
    if (i < 0)
      return null;
    if (i >= veb.u)
      return veb.max();
    int p = veb.predecessor(i);
    if (p >= 0)
      return p;
    return null;
  }

  public Integer successor(Integer x) {
    if (x == null)
      return null;
    int i = x;
    if (i < 0)
      return veb.min();
    if (i >= veb.u)
      return null;
    int s = veb.successor(i);
    if (s >= 0)
      return s;
    return null;
  }

  @Override
  public Comparator<? super Integer> comparator() {
    return null;
  }

  @Override
  public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SortedSet<Integer> headSet(Integer toElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SortedSet<Integer> tailSet(Integer fromElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer first() {
    return veb.min();
  }

  @Override
  public Integer last() {
    return veb.max();
  }

  @Override
  public Iterator<Integer> iterator() {
    return new VEBIterator(veb);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    veb.reset();
    size = 0;
  }
}
