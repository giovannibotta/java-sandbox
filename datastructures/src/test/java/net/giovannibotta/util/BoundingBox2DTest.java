package net.giovannibotta.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoundingBox2DTest {
  @Test
  public void test() {
    BoundingBox2D box = BoundingBox2D.Create(Point2D.Create(0, 0), 1);

    assertTrue(box.containsPoint(Point2D.Create(0.5, -.1)));

    assertTrue(box.intersects(BoundingBox2D.Create(Point2D.Create(0, 0), 0.5)));
  }
}
