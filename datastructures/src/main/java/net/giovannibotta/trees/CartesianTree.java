package net.giovannibotta.trees;

import java.util.List;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class CartesianTree extends BinaryTree<Integer> {
    public CartesianTree(List<Integer> sequence) {
        BinaryTreeNode<Integer> prev = create(sequence.get(0));
        for (int i = 1; i < sequence.size(); i++) {
            Integer elem = sequence.get(i);
            while (prev.parent() != null && prev.value() > elem) prev = prev.parent();
            // prev is now the parent of the new element, which is its new right child
            // the prev right child becomes the new node's left child
            BinaryTreeNode<Integer> newNode = create(elem);
            newNode.parent(prev);
            newNode.left(prev.right());
            // update the parent of the left child
            if (newNode.left() != null) newNode.left().parent(newNode);
            // the found node is the new node's parent
            prev.right(newNode);
            // prev must always point to the previous element in the sequence
            prev = newNode;
        }
        while (prev.parent() != null) prev = prev.parent();
        this.root = prev;
    }

    @Override
    public BinaryTreeNode<Integer> create(Integer elem) {
        return new BinaryTreeNodeWithParent<>(elem);
    }
}
