package net.giovannibotta.algo;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class Permutations<T> {
    private final Backtracking<T> finder;

    public Permutations(final Set<T> universe, Consumer<List<T>> processPermutation) {
        final int n = universe.size();
        finder = new Backtracking<T>(
                (a, k) -> k == n,
                a -> processPermutation.accept(a.list()),
                (a, k) -> Lists.newLinkedList(Sets.difference(universe, ImmutableSet.copyOf(a.list()))),
                new Backtracking.GenericPartialSolution<>()
        );
    }

    public void findAll() {
        finder.backTrack();
    }
}
