package net.giovannibotta.trees;

import static net.giovannibotta.trees.VEBTreeUtils.firstLargerPowerOf2;
import static net.giovannibotta.trees.VEBTreeUtils.floorLog2;
import static net.giovannibotta.trees.VEBTreeUtils.lowerSquareRoot;
import static net.giovannibotta.trees.VEBTreeUtils.upperSquareRoot;
import static org.junit.Assert.*;

import org.junit.Test;

public class VEBTreeTest {
  @Test
  public void BasicTest() {
    VEBTree tree = new VEBTree(100);
    assertTrue("added element 10", tree.add(10));
    assertEquals("size is 1", 1, tree.size());
    assertFalse("added element 10 again", tree.add(10));
    assertTrue("added element 5", tree.add(5));
    assertTrue("added element 2", tree.add(2));
    assertEquals("size is 3", 3, tree.size());
    assertEquals("successor of 1 is 2", Integer.valueOf(2), tree.successor(1));
    assertEquals("successor of 2 is 5", Integer.valueOf(5), tree.successor(2));
    assertEquals("successor of 9 is 10", Integer.valueOf(10), tree.successor(9));
    assertEquals("predecessor of 40 is 10", Integer.valueOf(10), tree.predecessor(40));
    assertEquals("predecessor of 5 is 2", Integer.valueOf(2), tree.predecessor(5));
    assertEquals("min is 2", Integer.valueOf(2), tree.first());
    assertEquals("max is 10", Integer.valueOf(10), tree.last());

    for (int x : new int[] { 0, 11, 12, 13, 17, 35, 60, 41, 42, 101, 4 })
      assertTrue("added element " + x, tree.add(x));
    for (int x : new int[] { 0, 11, 12, 13, 17, 35, 60, 41, 42, 101, 4 })
      assertFalse("added element " + x, tree.add(x));
    assertEquals("size is 14", 14, tree.size());
    assertEquals("predecessor of 12 is 11", Integer.valueOf(11), tree.predecessor(12));
    assertEquals("successor of 12 is 13", Integer.valueOf(13), tree.successor(12));

    System.out.println(tree);
  }
  
  @Test
  public void UtilsTest() {
    int[] size = new int[] { 1, 2, 3, 5, 10, 15, 16, 17, 30, 50, 100, 500,
        1023, 1024, 1025 };
    int[] u = new int[] { 1, 2, 4, 8, 16, 16, 16, 32, 32, 64, 128, 512, 1024,
        1024, 2048 };
    int[] log2 = new int[] { 0, 1, 2, 3, 4, 4, 4, 5, 5, 6, 7, 9, 10, 10, 11 };

    for (int i = 0; i < size.length; i++) {
      int pw2 = firstLargerPowerOf2(size[i]);
      assertEquals(u[i], pw2);
        int l2 = floorLog2(pw2);
        assertEquals(log2[i], l2);
        int low = lowerSquareRoot(pw2);
        int up = upperSquareRoot(pw2);
        assertEquals(pw2, low * up);
    }
  }

}
