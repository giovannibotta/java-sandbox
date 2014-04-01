package net.giovannibotta.trees;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
    BinaryTree(List<T> inOrder, List<T> preOrder) {
        if (inOrder.size() != preOrder.size())
            throw new IllegalArgumentException("inorder and preorder traversal lists have different sizes");
        Map<T, Integer> inOrderPos = inOrderPositions(inOrder);
        root = create(preOrder.get(0));
        fromInOrderAndPreOrder(root, inOrderPos, 0, inOrder.size() - 1, preOrder, 0);
    }

    public List<T> preOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        preOrder(root, b);
        return b.build();
    }

    public List<T> postOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        postOrder(root, b);
        return b.build();
    }

    public List<T> inOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        inOrder(root, b);
        return b.build();
    }

    BinaryTreeNode<T> root;

    BinaryTree() {
    }

    private void preOrder(BinaryTreeNode<T> root, ImmutableList.Builder<T> b) {
        if (root != null) {
            b.add(root.value());
            preOrder(root.left(), b);
            preOrder(root.right(), b);
        }
    }

    private void postOrder(BinaryTreeNode<T> root, ImmutableList.Builder<T> b) {
        if (root != null) {
            postOrder(root.left(), b);
            postOrder(root.right(), b);
            b.add(root.value());
        }
    }

    private void inOrder(BinaryTreeNode<T> root, ImmutableList.Builder<T> b) {
        if (root != null) {
            inOrder(root.left(), b);
            b.add(root.value());
            inOrder(root.right(), b);
        }
    }

    public static <X> BinaryTree<X> fromInOrderAndPreOrder(List<X> inOrder, List<X> preOrder) {
        return new BinaryTree<>(inOrder, preOrder);
    }

    private void fromInOrderAndPreOrder(BinaryTreeNode<T> root, Map<T, Integer> inOrderPositions, int inOrderStart, int inOrderEnd, List<T> preOrder, int preOrderStart) {
        int rootPos = inOrderPositions.get(root.value());
        int leftLength = rootPos - inOrderStart;
        int rightLength = inOrderEnd - rootPos;
        if (leftLength > 0) {
            int newPreOrderStart = preOrderStart + 1;
            root.left(create(preOrder.get(newPreOrderStart)));
            fromInOrderAndPreOrder(root.left(), inOrderPositions, inOrderStart, rootPos - 1, preOrder, newPreOrderStart);
        }
        if (rightLength > 0) {
            int newPreOrderStart = preOrderStart + leftLength + 1;
            root.right(create(preOrder.get(newPreOrderStart)));
            fromInOrderAndPreOrder(root.right(), inOrderPositions, rootPos + 1, inOrderEnd, preOrder, preOrderStart + leftLength + 1);
        }
    }

    private static <X> Map<X, Integer> inOrderPositions(List<X> inOrder) {
        ImmutableMap.Builder<X, Integer> b = ImmutableMap.builder();
        int i = 0;
        for (X x : inOrder) b.put(x, i++);
        ImmutableMap<X, Integer> inOrderPositions = b.build();
        if (inOrderPositions.size() != inOrder.size()) throw new IllegalArgumentException("elements are not unique");
        return inOrderPositions;
    }

    @Override
    public BinaryTreeNode<T> create(T elem) {
        return new SimpleBinaryTreeNode<>(elem);
    }
}
