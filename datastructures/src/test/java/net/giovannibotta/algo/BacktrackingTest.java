package net.giovannibotta.algo;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class BacktrackingTest {
    @Test
    public void testPermutations() {
        final List<List<String>> ret = new ArrayList<>();
        new Permutations<>(ImmutableSet.of("A", "B", "C", "D"), a -> ret.add(a.elements())).backTrack();
        for (List<String> s : ret) System.out.println(s);
    }
}
