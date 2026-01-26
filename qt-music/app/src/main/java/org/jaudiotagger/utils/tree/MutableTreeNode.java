package org.jaudiotagger.utils.tree;

/* loaded from: classes3.dex */
public interface MutableTreeNode<T> extends TreeNode<T> {
    void insert(MutableTreeNode<T> mutableTreeNode, int i);

    void remove(int i);

    void remove(MutableTreeNode<T> mutableTreeNode);

    void removeFromParent();

    void setParent(MutableTreeNode<T> mutableTreeNode);

    void setUserObject(T t);
}
