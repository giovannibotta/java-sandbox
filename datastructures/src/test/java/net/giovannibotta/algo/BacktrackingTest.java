package net.giovannibotta.algo;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class BacktrackingTest {
    @Test
    public void testPermutations() {
        final List<List<String>> ret = new ArrayList<>();
        new Permutations<>(ImmutableSet.of("A", "B", "C"), ret::add).findAll();
        for (List<String> s : ret) System.out.println(s);
    }

    @Test
    public void testSubsets() {
        final List<Set<Integer>> ret = new ArrayList<>();
        new Subsets<>(ImmutableSet.of(1, 2, 3, 4, 5), ret::add).findAll();
        for (Set<Integer> s : ret) System.out.println(s);
    }
}
