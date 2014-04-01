package net.giovannibotta.bm;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class MaxPerformance2 {
    private final BiFunction<Integer, Integer, Integer> max;
    private final int[] array1, array2;

    public MaxPerformance2(BiFunction<Integer, Integer, Integer> max, int[] array1, int[] array2) {
        this.max = max;
        this.array1 = array1;
        this.array2 = array2;
        if (array1.length != array2.length) throw new IllegalArgumentException();
    }

    public double time() {
        long start = System.nanoTime();

        int m = Integer.MIN_VALUE;
        for (int i = 0; i < array1.length; ++i) m = max.apply(array1[i], array2[i]);
        m += m; // to avoid optimizations!

        return ((double) (System.nanoTime() - start)) / (double) array1.length;
    }

    public double averageTime(int repeats) {
        // warm up rounds:
        double tmp = 0;
        for (int i = 0; i < 10; i++) tmp += time();
        tmp *= 2.0;

        double cumulativeTime = 0;
        for (int i = 0; i < repeats; i++)
            cumulativeTime += time();
        return cumulativeTime / (double) repeats;
    }

    public static void main(String[] args) {
        int size = 1000000;
        Random random = new Random(123123123L);
        int[] array1 = new int[size];
        int[] array2 = new int[size];
        for (int i = 0; i < size; i++) {
            array1[i] = random.nextInt();
            array2[i] = random.nextInt();
        }

        double tMath = new MaxPerformance2(Math::max, array1, array2).averageTime(100);
        double tAlt1 = new MaxPerformance2(MaxPerformance2::max1, array1, array2).averageTime(100);
        double tAlt2 = new MaxPerformance2(MaxPerformance2::max2, array1, array2).averageTime(100);

        System.out.println("Java Math: " + tMath);
        System.out.println("Alt 1:     " + tAlt1);
        System.out.println("Alt 2:     " + tAlt2);
    }

    public static int max1(final int a, final int b) {
        if (a >= b) return a;
        return b;
    }

    public static int max2(final int a, final int b) {
        return (a >= b) ? a : b; // same as JDK implementation
    }
}
