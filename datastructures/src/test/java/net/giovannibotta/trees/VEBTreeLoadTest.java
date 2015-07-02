package net.giovannibotta.trees;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class VEBTreeLoadTest {
  @Test
  public void BasicTest() {
    int[] size = new int[] { 1024, 4 * 1024, 16 * 1024, 128 * 1024, 512 * 1024,
        1024 * 1024, 4 * 1024 * 1024 };
    Random r = new Random(464573453L);
    for (int u : size) {
      VEBTree t = new VEBTree(u);
      for (double x : new double[] { 0.25, 0.5, 0.75 }) {
        TestCase test = new TestCase(u, x, 20, r);
        double avgInsertionTime = test.testInsert(t);
        System.out.println("u = " + u + " fullness = " + x
            + " avg insert time = " + avgInsertionTime);
      }
    }
  }

  private static class Timer {
    private final long start;

    Timer() {
      start = System.nanoTime();
    }

    long checkpoint() {
      long now = System.nanoTime();
      return now - start;
    }
  }

  private static class TestCase {
    private final Set<Integer> elems;
    private final int repetitions;

    TestCase(int u, double x, int repetitions, Random rnd) {
      if (x > 1.0)
        throw new IllegalArgumentException("x must be < 1, instead is " + x);
      int n = (int) (x * u);
      this.elems = new HashSet<>(n);
      int i = 0;
      while (elems.size() < n) {
        if (i > 2 * u)
          throw new RuntimeException("Running for too long, added "
              + elems.size() + "/" + n + " elements");
        elems.add(rnd.nextInt(u));
        ++i;
      }
      this.repetitions = repetitions;
    }

    double testInsert(Set<Integer> toTest) {
      Timer t = new Timer();
      for (int r = 0; r < repetitions; r++) {
        toTest.clear();
        for (Integer i : elems) {
          toTest.add(i);
        }
      }
      double elapsed = t.checkpoint();
      return elapsed / ((double) repetitions * elems.size());
    }
  }
}
