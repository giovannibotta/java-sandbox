package net.giovannibotta.trees;

import java.util.HashSet;
import java.util.Set;

import net.giovannibotta.util.BoundingBox2D;
import net.giovannibotta.util.Point2D;

public class QuadTree {
  final QuadTreeNode root;

  public static QuadTree Create(BoundingBox2D boundary, int nodeCapacity) {
    return new QuadTree(boundary, nodeCapacity);
  }

  private QuadTree(BoundingBox2D boundary, int nodeCapacity) {
    root = new QuadTreeNode(nodeCapacity, boundary);
  }

  public boolean insert(Point2D p) {
    return root.insert(p);
  }

  public boolean queryRange(BoundingBox2D range, Set<Point2D> Point2Ds) {
    return root.queryRange(range, Point2Ds);
  }

  public Set<Point2D> queryRange(BoundingBox2D range) {
    Set<Point2D> ret = new HashSet<>();
    queryRange(range, ret);
    return ret;
  }

  boolean rootHasChildren() {
    return root.hasChildren();
  }

  static class QuadTreeNode {
    final int capacity;
    final Set<Point2D> points;
    final BoundingBox2D boundary;
    QuadTreeNode nw, ne, sw, se;

    public QuadTreeNode(int capacity, BoundingBox2D boundary) {
      this.capacity = capacity;
      this.boundary = boundary;
      points = new HashSet<>(capacity);
    }

    private boolean hasChildren() {
      return nw != null;
    }

    private boolean insert(Point2D p) {
      if (!boundary.containsPoint(p)) {
        return false;
      }
      if (points.size() < capacity) {
        return points.add(p);
      }
      if (nw == null)
        subdivide();

      if (nw.insert(p))
        return true;
      if (ne.insert(p))
        return true;
      if (sw.insert(p))
        return true;
      if (se.insert(p))
        return true;

      return false;
    }

    private void subdivide() {
      final double halfDimension = boundary.halfDimension() / 2;
      nw = new QuadTreeNode(capacity,//
          BoundingBox2D.Create(//
              Point2D.Create(boundary.center().x() - halfDimension,//
                  boundary.center().y() + halfDimension), halfDimension));
      ne = new QuadTreeNode(capacity,//
          BoundingBox2D.Create(//
              Point2D.Create(boundary.center().x() + halfDimension,//
                  boundary.center().y() + halfDimension), halfDimension));
      sw = new QuadTreeNode(capacity,//
          BoundingBox2D.Create(//
              Point2D.Create(boundary.center().x() - halfDimension,//
                  boundary.center().y() - halfDimension), halfDimension));
      se = new QuadTreeNode(capacity,//
          BoundingBox2D.Create(//
              Point2D.Create(boundary.center().x() + halfDimension,//
                  boundary.center().y() - halfDimension), halfDimension));
    }

    private boolean queryRange(BoundingBox2D range, Set<Point2D> ret) {
      if (!boundary.intersects(range)) {
        return false;
      }
      boolean found = false;
      for (Point2D p : points) {
        if (range.containsPoint(p)) {
          ret.add(p);
          found = true;
        }
      }
      if (nw == null) {
        return found;
      }
      found = nw.queryRange(range, ret) || found;
      found = ne.queryRange(range, ret) || found;
      found = sw.queryRange(range, ret) || found;
      found = se.queryRange(range, ret) || found;

      return found;
    }
  }
}