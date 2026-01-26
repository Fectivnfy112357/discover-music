package org.jaudiotagger.utils.tree;

/* loaded from: classes3.dex */
public interface TreeModel<T> {
    void addTreeModelListener(TreeModelListener treeModelListener);

    TreeNode<T> getChild(TreeNode<T> treeNode, int i);

    int getChildCount(TreeNode<T> treeNode);

    int getIndexOfChild(TreeNode<T> treeNode, TreeNode<T> treeNode2);

    TreeNode<T> getRoot();

    boolean isLeaf(TreeNode<T> treeNode);

    void removeTreeModelListener(TreeModelListener treeModelListener);

    void valueForPathChanged(TreePath<T> treePath, T t);
}
