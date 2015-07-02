package net.giovannibotta.trees;

import static java.lang.Integer.*;

public final class VEBTreeUtils {
  private VEBTreeUtils() {
  }

  static int floorLog2(int x) {
    return 31 - numberOfLeadingZeros(x);
  }

  static int lowerSquareRoot(int u) {
    // Assuming us is a power of 2, floor(log2(u)) == log2(u).
    return 1 << (floorLog2(u) / 2);
  }

  static int upperSquareRoot(int u) {
    // Assuming us is a power of 2, floor(log2(u)) == log2(u).
    int log2 = floorLog2(u);
    if (log2 % 2 == 0)
      return 1 << (log2 / 2);
    return 1 << (log2 / 2 + 1);
  }

  static int firstLargerPowerOf2(int x) {
    if (x < 0)
      return 0;
    if (x <= 2)
      return x;
    return x > 1 ? highestOneBit(x - 1) << 1 : 1;
  }
}
