package net.giovannibotta.sets;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class DisjointSetForestTest {
    @Test
    public void simpleTest() {
        Random rnd = new Random(1235331443L);
        DisjointSetForest forest = new DisjointSetForest(20);
        assertEquals(20, forest.size());
        for (int i = 0; i < forest.size(); i += 2) {
            forest.make(i);
        }
        for (int i = 0; i < forest.size() / 2; i++) {
            int x = rnd.nextInt(forest.size());
            int y = rnd.nextInt(forest.size());
            forest.union(x, y);
        }
        System.out.println(forest);
    }
}
