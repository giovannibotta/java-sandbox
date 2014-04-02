package net.giovannibotta.trees;

/**
 * @author giovanni
 * @since 3/31/14
 */
public interface BinaryTreeNode<T> {
    T value();

    BinaryTreeNode<T> left();

    BinaryTreeNode<T> right();

    BinaryTreeNode<T> parent();

    void left(BinaryTreeNode<T> left);

    void right(BinaryTreeNode<T> right);

    void parent(BinaryTreeNode<T> parent);

    int height();

    void height(int h);
}
