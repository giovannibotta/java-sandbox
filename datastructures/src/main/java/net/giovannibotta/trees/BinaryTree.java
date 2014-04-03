package net.giovannibotta.trees;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.giovannibotta.tuple.Couple;

import java.util.*;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
    @Override
    public List<T> preOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        preOrder(root, b);
        return b.build();
    }

    @Override
    public List<T> postOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        postOrder(root, b);
        return b.build();
    }

    @Override
    public List<T> inOrder() {
        ImmutableList.Builder<T> b = ImmutableList.builder();
        inOrder(root, b);
        return b.build();
    }

    @Override
    public int radius() {
        // populate all the tree heights
        computeHeights(root);
        return radius(root);
    }

    private int radius(BinaryTreeNode<T> root) {
        if (root == null) return 0;
        int lh = 0, rh = 0;
        if (root.left() != null) lh = root.left().height();
        if (root.right() != null) rh = root.right().height();
        int hsum = lh + rh;
        int lr = radius(root.left()), rr = radius(root.right());
        return Math.max(lr, Math.max(rr, hsum));
    }

    private int computeHeights(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        int l = 0, r = 0;
        if (node.left() != null) l = computeHeights(node.left());
        if (node.right() != null) r = computeHeights(node.right());
        node.height(Math.max(l, r) + 1);
        return node.height();
    }

    BinaryTree(List<T> inOrder, List<T> preOrder) {
        if (inOrder.size() != preOrder.size())
            throw new IllegalArgumentException("inorder and preorder traversal lists have different sizes");
        Map<T, Integer> inOrderPos = inOrderPositions(inOrder);
        root = create(preOrder.get(0));
        fromInOrderAndPreOrder(root, inOrderPos, 0, inOrder.size() - 1, preOrder, 0);
    }

    BinaryTreeNode<T> root;

    BinaryTree() {
    }

    public Collection<T> minimumVertexCover() {
        return minimumVertexCover(null);
    }

    Collection<T> minimumVertexCover(Set<BinaryTreeNode<T>> cover) {
        if (cover == null) cover = new HashSet<>();
        if (!cover.isEmpty()) throw new IllegalArgumentException("The given set should be empty!");
        minimumVertexCover(root, cover);
        return ImmutableSet.copyOf(Lists.transform(ImmutableList.copyOf(cover), input -> input.value()));
    }

    private void minimumVertexCover(BinaryTreeNode<T> node, Set<BinaryTreeNode<T>> coverSoFar) {
        // no node or no children, so it's a leaf, I don't need to add it
        if (node == null || (node.left() == null && node.right() == null)) return;
        minimumVertexCover(node.left(), coverSoFar);
        minimumVertexCover(node.right(), coverSoFar);
        // if either child is not in the cover, I have to add this to make sure the edge is covered by this node
        if (node.left() != null && !coverSoFar.contains(node.left())) coverSoFar.add(node);
        if (node.right() != null && !coverSoFar.contains(node.right())) coverSoFar.add(node);
    }

    public Couple<Collection<T>, Collection<T>> biColoring() {
        ImmutableList.Builder<T> black = ImmutableList.builder();
        ImmutableList.Builder<T> red = ImmutableList.builder();
        black(root, black, red);
        return Couple.<Collection<T>, Collection<T>>create(black.build(), red.build());
    }

    private void black(BinaryTreeNode<T> node, ImmutableList.Builder<T> black, ImmutableList.Builder<T> red) {
        if (node != null) {
            black.add(node.value());
            red(node.left(), black, red);
            red(node.right(), black, red);
        }
    }

    private void red(BinaryTreeNode<T> node, ImmutableList.Builder<T> black, ImmutableList.Builder<T> red) {
        if (node != null) {
            red.add(node.value());
            black(node.left(), black, red);
            black(node.right(), black, red);
        }
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

    BinaryTreeNode<T> create(T elem) {
        return new SimpleBinaryTreeNode<>(elem);
    }
}
