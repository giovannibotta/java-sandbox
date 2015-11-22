package net.giovannibotta.util;

public class Point2D {
  final double x, y;

  public static Point2D Create(double x, double y) {
    return new Point2D(x, y);
  }

  private Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double x() {
    return x;
  }

  public double y() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Point2D) {
      Point2D otherPoint = (Point2D) other;
      return otherPoint.x == x && otherPoint.y == y;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Double.hashCode(x) * 31 + Double.hashCode(y);
  }
}
