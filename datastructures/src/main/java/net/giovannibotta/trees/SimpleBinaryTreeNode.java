package net.giovannibotta.trees;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class SimpleBinaryTreeNode<T> implements BinaryTreeNode<T> {
    private BinaryTreeNode<T> left, right;
    private final T elem;
    private int height;

    public SimpleBinaryTreeNode(T elem) {
        this.elem = elem;
    }

    @Override
    public T value() {
        return elem;
    }

    @Override
    public BinaryTreeNode<T> left() {
        return left;
    }

    @Override
    public BinaryTreeNode<T> right() {
        return right;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BinaryTreeNode<T> parent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void left(BinaryTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void right(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public void parent(BinaryTreeNode<T> parent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public void height(int h) {
        this.height = h;
    }
}
