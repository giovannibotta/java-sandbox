package net.giovannibotta.algo;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * @author giovanni
 * @since 4/4/14
 */
public class Backtracking<T> {
    private final BiPredicate<PartialSolution<T>, Integer> isSolution;
    private final Consumer<PartialSolution<T>> processSolution;
    private final BiFunction<PartialSolution<T>, Integer, List<T>> computeSet;
    private final PartialSolution<T> start;

    public Backtracking(BiPredicate<PartialSolution<T>, Integer> solution, Consumer<PartialSolution<T>> processSolution, BiFunction<PartialSolution<T>, Integer, List<T>> computeSet, PartialSolution<T> start) {
        isSolution = solution;
        this.processSolution = processSolution;
        this.computeSet = computeSet;
        this.start = start;
    }

    public void backTrack() {
        backTrack(start, 0);
    }

    private void backTrack(PartialSolution<T> a, int k) {
        if (isSolution.test(a, k)) processSolution.accept(a);
        else {
            k++;
            List<T> s = computeSet.apply(a, k);
            while (!s.isEmpty()) backTrack(a.addAndCreateNew(s.remove(0)), k);
        }
    }

    static interface PartialSolution<T> {
        PartialSolution<T> addAndCreateNew(T t);

        List<T> list();
    }

    static class GenericPartialSolution<T> implements PartialSolution<T> {
        private final List<T> list;

        GenericPartialSolution() {
            this(ImmutableList.of());
        }

        GenericPartialSolution(List<T> l) {
            this.list = l;
        }

        @Override
        public GenericPartialSolution<T> addAndCreateNew(T t) {
            return new GenericPartialSolution<>(
                    ImmutableList.<T>builder()
                            .addAll(list)
                            .add(t)
                            .build()
            );
        }

        @Override
        public List<T> list() {
            return list;
        }
    }
}
