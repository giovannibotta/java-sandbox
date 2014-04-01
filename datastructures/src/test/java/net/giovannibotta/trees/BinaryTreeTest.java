package net.giovannibotta.trees;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author giovanni
 * @since 3/3++i/++i4
 */
public class BinaryTreeTest {
    @Test
    public void test() {
        ImmutableList<Integer> inorder = ImmutableList.of(4, 2, 8, 5, 1, 9, 6, 10, 3, 11, 12, 7);
        ImmutableList<Integer> preorder = ImmutableList.of(1, 2, 4, 5, 8, 3, 6, 9, 10, 7, 11, 12);

        BinaryTree<Integer> reconstructed = BinaryTree.fromInOrderAndPreOrder(inorder, preorder);

        List<Integer> in = reconstructed.inOrder();
        List<Integer> pre = reconstructed.preOrder();
        System.out.println(in);
        System.out.println(pre);

        Assert.assertEquals(inorder, in);
        Assert.assertEquals(preorder, pre);

        ImmutableList<String> inorder1 = ImmutableList.of("C", "B", "E", "D", "A", "H", "G", "I", "F", "K", "L", "J");
        ImmutableList<String> preorder1 = ImmutableList.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");

        BinaryTree<String> reconstructed1 = BinaryTree.fromInOrderAndPreOrder(inorder1, preorder1);

        List<String> in1 = reconstructed1.inOrder();
        List<String> pre1 = reconstructed1.preOrder();
        System.out.println(in1);
        System.out.println(pre1);

        Assert.assertEquals(inorder1, in1);
        Assert.assertEquals(preorder1, pre1);
    }
}
