package net.giovannibotta.trees;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class CartesianTreeTest {
    @Test
    public void test() {
        List<Integer> sequence = ImmutableList.of(9, 3, 7, 1, 8, 12, 10, 20, 15, 18, 5);
        CartesianTree ct = new CartesianTree(sequence);
        List<Integer> inorder = ct.inOrder();
        System.out.println(inorder);
        Assert.assertEquals(sequence, inorder);
    }

}
