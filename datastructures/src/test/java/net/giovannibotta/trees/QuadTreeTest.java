package net.giovannibotta.trees;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import net.giovannibotta.util.BoundingBox2D;
import net.giovannibotta.util.Point2D;

public class QuadTreeTest {
  @Test
  public void test() {
    QuadTree q = QuadTree.Create(BoundingBox2D.Create(Point2D.Create(0, 0), 1),
        2);

    assertTrue(q.insert(Point2D.Create(0, 0)));
    assertTrue(q.insert(Point2D.Create(0.2, 0.1)));
    assertFalse(q.rootHasChildren());
    assertTrue(q.insert(Point2D.Create(-0.6, 0.3)));
    assertTrue(q.rootHasChildren());
    assertTrue(q.insert(Point2D.Create(-0.03, -0.9)));
    assertTrue(q.insert(Point2D.Create(0.6777, -0.1)));

    Set<Point2D> ret = new HashSet<>();
    assertTrue(q.queryRange(
        BoundingBox2D.Create(Point2D.Create(0.5, 0.5), 0.5), ret));
    assertEquals("Points found in range",
        ImmutableSet.of(Point2D.Create(0.2, 0.1), Point2D.Create(0, 0)),
        ImmutableSet.copyOf(ret));
    
    ret.clear();
    assertTrue(q.queryRange(
        BoundingBox2D.Create(Point2D.Create(-0.5, -0.5), 0.5), ret));
    assertEquals("Points found in range",
        ImmutableSet.of(Point2D.Create(-0.03, -0.9), Point2D.Create(0, 0)),
        ImmutableSet.copyOf(ret));
    
  }
}
