package org.jaudiotagger.utils.tree;

import java.util.EventObject;

/* loaded from: classes3.dex */
public class TreeModelEvent<T> extends EventObject {
    private static final long serialVersionUID = 1135112176859241636L;
    protected int[] childIndices;
    protected TreeNode<T>[] children;
    protected TreePath<T> path;

    public TreeModelEvent(Object obj, TreeNode<T>[] treeNodeArr, int[] iArr, TreeNode<T>[] treeNodeArr2) {
        this(obj, new TreePath(treeNodeArr), iArr, treeNodeArr2);
    }

    public TreeModelEvent(Object obj, TreePath<T> treePath, int[] iArr, TreeNode<T>[] treeNodeArr) {
        super(obj);
        this.path = treePath;
        this.childIndices = iArr;
        this.children = treeNodeArr;
    }

    public TreeModelEvent(Object obj, TreeNode<T>[] treeNodeArr) {
        this(obj, new TreePath(treeNodeArr));
    }

    public TreeModelEvent(Object obj, TreePath<T> treePath) {
        super(obj);
        this.path = treePath;
        this.childIndices = new int[0];
    }

    public TreePath<T> getTreePath() {
        return this.path;
    }

    public TreeNode<T>[] getPath() {
        TreePath<T> treePath = this.path;
        if (treePath != null) {
            return treePath.getPath();
        }
        return null;
    }

    public TreeNode<T>[] getChildren() {
        TreeNode<T>[] treeNodeArr = this.children;
        if (treeNodeArr == null) {
            return null;
        }
        int length = treeNodeArr.length;
        TreeNode<T>[] treeNodeArr2 = new TreeNode[length];
        System.arraycopy(treeNodeArr, 0, treeNodeArr2, 0, length);
        return treeNodeArr2;
    }

    public int[] getChildIndices() {
        int[] iArr = this.childIndices;
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    @Override // java.util.EventObject
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName() + " " + Integer.toString(hashCode()));
        if (this.path != null) {
            stringBuffer.append(" path " + this.path);
        }
        if (this.childIndices != null) {
            stringBuffer.append(" indices [ ");
            for (int i = 0; i < this.childIndices.length; i++) {
                stringBuffer.append(Integer.toString(this.childIndices[i]) + " ");
            }
            stringBuffer.append("]");
        }
        if (this.children != null) {
            stringBuffer.append(" children [ ");
            for (int i2 = 0; i2 < this.children.length; i2++) {
                stringBuffer.append(this.children[i2] + " ");
            }
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
}
