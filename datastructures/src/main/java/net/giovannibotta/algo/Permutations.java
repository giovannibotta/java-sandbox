package net.giovannibotta.algo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class Permutations<T> extends Backtracking<T> {
    public Permutations(final Set<T> universe, Consumer<PartialSolution<T>> processSolution) {
        super(
                (a, k) -> k == universe.size(),
                processSolution,
                (a, k) -> Lists.newLinkedList(Sets.difference(universe, ImmutableSet.copyOf(a.elements()))),
                new SetPartialSolution<>()
        );
    }

    private static class SetPartialSolution<T> implements PartialSolution<T> {
        private final List<T> set;

        @Override
        public List<T> elements() {
            return ImmutableList.copyOf(set);
        }

        public SetPartialSolution(List<T> set) {
            this.set = set;
        }

        public SetPartialSolution() {
            this(new LinkedList<>());
        }

        @Override
        public PartialSolution<T> addAndCreateNew(T t) {
            List<T> ret = Lists.newLinkedList(set);
            ret.add(t);
            return new SetPartialSolution<>(ret);
        }

        @Override
        public String toString() {
            return set.toString();
        }
    }
}
