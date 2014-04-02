package net.giovannibotta.trees;

import java.util.List;

/**
 * @author giovanni
 * @since 3/31/14
 */
interface BinaryTreeInterface<T> {
    List<T> preOrder();

    List<T> inOrder();

    List<T> postOrder();

    int radius();
}
