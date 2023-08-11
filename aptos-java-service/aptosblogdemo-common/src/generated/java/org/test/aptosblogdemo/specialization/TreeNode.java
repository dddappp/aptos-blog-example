package org.test.aptosblogdemo.specialization;

public interface TreeNode<T> {
    T getContent();

    Iterable<TreeNode<T>> getChildren();
}
