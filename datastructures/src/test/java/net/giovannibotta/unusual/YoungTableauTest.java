package net.giovannibotta.unusual;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author giovanni
 * @since 4/10/14
 */
public class YoungTableauTest {
    YoungTableau tableau1;
    YoungTableau tableau2;

    @Before
    public void createTableau() {
        int[][] t1 = {
                {1, 3, 5, 7, 8, 11},
                {4, 6, 9, 14, 15, 19},
                {10, 21, 23, 33, 56, 57},
                {34, 37, 45, 55, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}
        };
        tableau1 = new YoungTableau(t1);

        int[][] t2 = {
                {1, 4, 9},
                {3, 6, 12}
        };
        tableau2 = new YoungTableau(t2);
    }

    @Test
    public void testAdd() {
        assertTrue(tableau1.add(2));
        assertTrue(tableau1.contains(2));
        System.out.println(tableau1.toTable());
        assertTrue(!tableau1.add(1));
        assertTrue(tableau1.contains(1));
        assertTrue(tableau1.add(6463));
        assertTrue(tableau1.contains(6463));
        System.out.println(tableau1.toTable());
        assertTrue(tableau1.add(1461));
        assertTrue(tableau1.contains(1461));
        System.out.println(tableau1.toTable());
        assertTrue(tableau1.add(9000));
        assertTrue(tableau1.contains(9000));
        System.out.println(tableau1.toTable());
        assertTrue(tableau1.add(4000));
        assertTrue(tableau1.contains(4000));
        System.out.println(tableau1.toTable());
    }

    @Test
    public void testContains() {
        assertTrue(tableau2.contains(1));
        assertTrue(!tableau2.contains(2));
        assertTrue(tableau2.contains(3));
        assertTrue(tableau2.contains(4));
        assertTrue(!tableau2.contains(5));
        assertTrue(tableau2.contains(6));
        assertTrue(!tableau2.contains(51));
        assertTrue(tableau2.contains(12));

        assertTrue(tableau1.contains(9));
        assertTrue(tableau1.contains(21));
        assertTrue(tableau1.contains(55));
        assertTrue(tableau1.contains(9));
        assertTrue(tableau1.contains(10));
        assertTrue(tableau1.contains(15));
        assertTrue(tableau1.contains(57));
        assertTrue(!tableau1.contains(2));
        assertTrue(!tableau1.contains(30));
        assertTrue(!tableau1.contains(12));
        assertTrue(!tableau1.contains(65));
        assertTrue(!tableau1.contains(13));
        assertTrue(!tableau1.contains(29));
    }

    @Test
    public void testExtractMin() {
        System.out.println(tableau1);
        System.out.println(tableau1.toTable());
        int s = 22;
        assertEquals(s--, tableau1.size());
        assertEquals(1, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(3, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(4, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(5, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(6, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(7, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        System.out.println(tableau1);
        System.out.println(tableau1.toTable());
        assertEquals(8, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(9, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(10, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        assertEquals(11, tableau1.extractMin());
        assertEquals(s--, tableau1.size());
        System.out.println(tableau1);
        System.out.println(tableau1.toTable());

        s = 6;

        System.out.println(tableau2.toTable());
        assertEquals(s--, tableau2.size());
        assertEquals(1, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(3, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(4, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(6, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(9, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(12, tableau2.extractMin());
        assertEquals(s--, tableau2.size());
        System.out.println(tableau2.toTable());
        assertEquals(Integer.MAX_VALUE, tableau2.extractMin());
    }
}
