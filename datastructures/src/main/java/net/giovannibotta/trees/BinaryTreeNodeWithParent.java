package net.giovannibotta.trees;

/**
 * @author giovanni
 * @since 3/31/14
 */
public class BinaryTreeNodeWithParent<T> extends SimpleBinaryTreeNode<T> {
    private BinaryTreeNode<T> parent;

    public BinaryTreeNodeWithParent(T elem) {
        super(elem);
    }

    @Override
    public void parent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public BinaryTreeNode<T> parent() {
        return parent;
    }
}
