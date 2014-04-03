package net.giovannibotta.trees;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.SortedSet;

/**
 * @author giovanni
 * @since 3/3++i/++i4
 */
public class BinaryTreeTest {
    BinaryTree<Integer> tree1, tree2;
    BinaryTree<String> tree3;

    @Before
    public void buildTrees() {
        // test tree 1:
        /*
                         1
                    ----- -----
                   /           \
                  2             3
                -- --         -- --
               /     \       /     \
              4       5     6       7
                     /    /  \     /
                    8    9   10   11
                                    \
                                    12
         */
        ImmutableList<Integer> inorder1 = ImmutableList.of(4, 2, 8, 5, 1, 9, 6, 10, 3, 11, 12, 7);
        ImmutableList<Integer> preorder1 = ImmutableList.of(1, 2, 4, 5, 8, 3, 6, 9, 10, 7, 11, 12);

        // test tree 2:
        /*
                         1
                   ------ ------
                  /             \
                 2               3
               -- --           -- --
              /     \         /     \
             4       5       6       7
                    /      /  \     /
                   8      9   10   11
                         /          \
                       12           13
                         \         /
                         14       15
                        /        /  \
                      16        17  18
                                      \
                                       19
         */
        ImmutableList<String> inorder3 = ImmutableList.of("C", "B", "E", "D", "A", "H", "G", "I", "F", "K", "L", "J");
        ImmutableList<String> preorder3 = ImmutableList.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");

        // same as 1 with strings
        ImmutableList<Integer> inorder2 = ImmutableList.of(4, 2, 8, 5, 1, 12, 16, 14, 9, 6, 10, 3, 11, 17, 15, 18, 19, 13, 7);
        ImmutableList<Integer> preorder2 = ImmutableList.of(1, 2, 4, 5, 8, 3, 6, 9, 12, 14, 16, 10, 7, 11, 13, 15, 17, 18, 19);

        tree1 = BinaryTree.fromInOrderAndPreOrder(inorder1, preorder1);
        tree2 = BinaryTree.fromInOrderAndPreOrder(inorder2, preorder2);
        tree3 = BinaryTree.fromInOrderAndPreOrder(inorder3, preorder3);
    }

    @Test
    public void testMinimumVertexCover() {
        SortedSet<Integer> mvc1 = ImmutableSortedSet.copyOf(tree1.minimumVertexCover());
        SortedSet<Integer> mvc2 = ImmutableSortedSet.copyOf(tree2.minimumVertexCover());
        System.out.println(mvc1);
        System.out.println(mvc2);
        Assert.assertEquals(ImmutableSet.of(2, 3, 5, 6, 11), mvc1);
        Assert.assertEquals(ImmutableSet.of(2, 3, 5, 6, 9, 11, 14, 15, 18), mvc2);
    }

    @Test
    public void testColoring(){
        System.out.println(tree1.biColoring());
        System.out.println(tree2.biColoring());
    }

    @Test
    public void testBasic() {

        ImmutableList<Integer> inorder = ImmutableList.of(4, 2, 8, 5, 1, 9, 6, 10, 3, 11, 12, 7);
        ImmutableList<Integer> preorder = ImmutableList.of(1, 2, 4, 5, 8, 3, 6, 9, 10, 7, 11, 12);

        BinaryTree<Integer> reconstructed = BinaryTree.fromInOrderAndPreOrder(inorder, preorder);

        List<Integer> in = reconstructed.inOrder();
        List<Integer> pre = reconstructed.preOrder();
        System.out.println(in);
        System.out.println(pre);

        Assert.assertEquals(inorder, in);
        Assert.assertEquals(preorder, pre);

        // same as before but with letters
        ImmutableList<String> inorder1 = ImmutableList.of("C", "B", "E", "D", "A", "H", "G", "I", "F", "K", "L", "J");
        ImmutableList<String> preorder1 = ImmutableList.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");

        BinaryTree<String> reconstructed1 = BinaryTree.fromInOrderAndPreOrder(inorder1, preorder1);

        List<String> in1 = reconstructed1.inOrder();
        List<String> pre1 = reconstructed1.preOrder();
        System.out.println(in1);
        System.out.println(pre1);

        Assert.assertEquals(inorder1, in1);
        Assert.assertEquals(preorder1, pre1);

        int r1 = reconstructed1.radius();
        System.out.println(r1);
        Assert.assertEquals(7, r1);

        ImmutableList<Integer> inorder2 = ImmutableList.of(4, 2, 8, 5, 1, 12, 16, 14, 9, 6, 10, 3, 11, 17, 15, 18, 19, 13, 7);
        ImmutableList<Integer> preorder2 = ImmutableList.of(1, 2, 4, 5, 8, 3, 6, 9, 12, 14, 16, 10, 7, 11, 13, 15, 17, 18, 19);

        BinaryTree<Integer> reconstructed2 = BinaryTree.fromInOrderAndPreOrder(inorder2, preorder2);
        List<Integer> in2 = reconstructed2.inOrder();
        List<Integer> pre2 = reconstructed2.preOrder();
        System.out.println(in2);
        System.out.println(pre2);

        Assert.assertEquals(inorder2, in2);
        Assert.assertEquals(preorder2, pre2);

        int r2 = reconstructed2.radius();
        System.out.println(r2);
        Assert.assertEquals(11, r2);
    }
}
