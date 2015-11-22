package net.giovannibotta.util;

public class BoundingBox2D {
  private final Point2D center;
  private final double halfDimension;

  public static BoundingBox2D Create(Point2D center, double halfDimension) {
    return new BoundingBox2D(center, halfDimension);
  }

  private BoundingBox2D(Point2D center, double halfDimension) {
    this.center = center;
    this.halfDimension = halfDimension;
  }

  public Point2D center() {
    return center;
  }

  public double halfDimension() {
    return halfDimension;
  }

  public boolean containsPoint(Point2D p) {
    return p.x() >= center.x() - halfDimension
        && p.x() <= center.x() + halfDimension
        && p.y() >= center.y() - halfDimension
        && p.y() <= center.y() + halfDimension;
  }

  public boolean intersects(BoundingBox2D b) {
    return b.center().x() + b.halfDimension() <= center.x() - halfDimension
        || b.center().x() - b.halfDimension() <= center.x() + halfDimension
        || b.center().y() + b.halfDimension() <= center.y() - halfDimension
        || b.center().y() - b.halfDimension() <= center.y() + halfDimension;
  }

  @Override
  public String toString() {
    return "{" + center + ", " + halfDimension + "}";
  }
}
