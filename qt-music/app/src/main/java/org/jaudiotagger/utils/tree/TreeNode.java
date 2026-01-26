package org.jaudiotagger.utils.tree;

import java.util.Enumeration;

/* loaded from: classes3.dex */
public interface TreeNode<T> {
    Enumeration<TreeNode<T>> children();

    boolean getAllowsChildren();

    TreeNode<T> getChildAt(int i);

    int getChildCount();

    int getIndex(TreeNode<T> treeNode);

    TreeNode<T> getParent();

    T getUserObject();

    boolean isLeaf();
}
