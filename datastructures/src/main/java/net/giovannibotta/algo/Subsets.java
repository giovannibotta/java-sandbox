package net.giovannibotta.algo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class Subsets<T> {
    private final Backtracking<Boolean> finder;

    // TODO: constructor that passes a list; uses bitset to process
    public Subsets(Set<T> universe, Consumer<Set<T>> processSubset) {
        List<T> universeList = ImmutableList.copyOf(universe);
        final int n = universeList.size();
        finder = new Backtracking<>(
                (a, k) -> k == n,
                a -> processSubset.accept(((BitSetPartialSolution) a).build(universeList)),
                (a, k) -> list(),
                new BitSetPartialSolution()
        );
    }

    private List<Boolean> list() {
        LinkedList<Boolean> ret = new LinkedList<>();
        ret.add(true);
        ret.add(false);
        return ret;
    }

    public void findAll() {
        finder.backTrack();
    }

    static class BitSetPartialSolution implements Backtracking.PartialSolution<Boolean> {
        private final BitSet bitSet;
        private final int size;

        BitSetPartialSolution(BitSet bitSet, int size) {
            this.bitSet = bitSet;
            this.size = size;
        }

        BitSetPartialSolution() {
            this(new BitSet(), 0);
        }

        @Override
        public BitSetPartialSolution addAndCreateNew(Boolean aBoolean) {
            int newSize = size + 1;
            BitSet b = new BitSet(newSize);
            for (int i = 0; i < bitSet.length(); i++) b.set(i, bitSet.get(i));
            b.set(size, aBoolean);
            return new BitSetPartialSolution(b, newSize);
        }

        public <T> Set<T> build(List<T> universe) {
            ImmutableSet.Builder<T> b = ImmutableSet.builder();
            for (int i = 0; i < bitSet.length(); i++) if (bitSet.get(i)) b.add(universe.get(i));
            return b.build();
        }

        @Override
        public List<Boolean> list() {
            throw new UnsupportedOperationException();
        }
    }
}
