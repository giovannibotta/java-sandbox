package net.giovannibotta.bm;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class MaxPerformance {
    private final BiFunction<Integer, Integer, Integer> max;
    private final int[] array;

    public MaxPerformance(BiFunction<Integer, Integer, Integer> max, int[] array) {
        this.max = max;
        this.array = array;
    }

    public double time() {
        long start = System.nanoTime();

        int m = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) m = max.apply(m, array[i]);

        m = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) m = max.apply(array[i], m);
        m += m;// to avoid optimizations

        return ((double) (System.nanoTime() - start)) / (double) array.length / 2.0;
    }

    public double averageTime(int repeats) {
        double cumulativeTime = 0;
        for (int i = 0; i < repeats; i++)
            cumulativeTime += time();
        return cumulativeTime / (double) repeats;
    }

    public static void main(String[] args) {
        int size = 1000000;
        Random random = new Random(123123123L);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) array[i] = random.nextInt();

        double tMath = new MaxPerformance(Math::max, array).averageTime(100);
        double tAlt1 = new MaxPerformance(MaxPerformance::max1, array).averageTime(100);
        double tAlt2 = new MaxPerformance(MaxPerformance::max2, array).averageTime(100);

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
