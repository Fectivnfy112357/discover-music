package org.jaudiotagger.utils.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/* loaded from: classes3.dex */
public class TreePath<T> implements Serializable {
    private static final long serialVersionUID = -5521484730448766444L;
    private transient TreeNode<T> lastPathComponent;
    private TreePath<T> parentPath;

    public TreePath(TreeNode<T>[] treeNodeArr) {
        if (treeNodeArr == null || treeNodeArr.length == 0) {
            throw new IllegalArgumentException("path in TreePath must be non null and not empty.");
        }
        this.lastPathComponent = treeNodeArr[treeNodeArr.length - 1];
        if (treeNodeArr.length > 1) {
            this.parentPath = new TreePath<>(treeNodeArr, treeNodeArr.length - 1);
        }
    }

    public TreePath(TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("path in TreePath must be non null.");
        }
        this.lastPathComponent = treeNode;
        this.parentPath = null;
    }

    protected TreePath(TreePath<T> treePath, TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("path in TreePath must be non null.");
        }
        this.parentPath = treePath;
        this.lastPathComponent = treeNode;
    }

    protected TreePath(TreeNode<T>[] treeNodeArr, int i) {
        int i2 = i - 1;
        this.lastPathComponent = treeNodeArr[i2];
        if (i > 1) {
            this.parentPath = new TreePath<>(treeNodeArr, i2);
        }
    }

    protected TreePath() {
    }

    public TreeNode<T>[] getPath() {
        int pathCount = getPathCount();
        int i = pathCount - 1;
        TreeNode<T>[] treeNodeArr = new TreeNode[pathCount];
        TreePath<T> treePath = this;
        while (treePath != null) {
            treeNodeArr[i] = treePath.lastPathComponent;
            treePath = treePath.parentPath;
            i--;
        }
        return treeNodeArr;
    }

    public TreeNode<T> getLastPathComponent() {
        return this.lastPathComponent;
    }

    public int getPathCount() {
        int i = 0;
        for (TreePath<T> treePath = this; treePath != null; treePath = treePath.parentPath) {
            i++;
        }
        return i;
    }

    public TreeNode<T> getPathComponent(int i) {
        int pathCount = getPathCount();
        if (i < 0 || i >= pathCount) {
            throw new IllegalArgumentException("Index " + i + " is out of the specified range");
        }
        TreePath<T> treePath = this;
        for (int i2 = pathCount - 1; i2 != i; i2--) {
            treePath = treePath.parentPath;
        }
        return treePath.lastPathComponent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TreePath)) {
            return false;
        }
        TreePath<T> treePath = (TreePath) obj;
        if (getPathCount() != treePath.getPathCount()) {
            return false;
        }
        for (TreePath<T> treePath2 = this; treePath2 != null; treePath2 = treePath2.parentPath) {
            if (!treePath2.lastPathComponent.equals(treePath.lastPathComponent)) {
                return false;
            }
            treePath = treePath.parentPath;
        }
        return true;
    }

    public int hashCode() {
        return this.lastPathComponent.hashCode();
    }

    public boolean isDescendant(TreePath<T> treePath) {
        int pathCount;
        int pathCount2;
        if (treePath == this) {
            return true;
        }
        if (treePath == null || (pathCount2 = treePath.getPathCount()) < (pathCount = getPathCount())) {
            return false;
        }
        while (true) {
            int i = pathCount2 - 1;
            if (pathCount2 > pathCount) {
                treePath = treePath.getParentPath();
                pathCount2 = i;
            } else {
                return equals(treePath);
            }
        }
    }

    public TreePath<T> pathByAddingChild(TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new NullPointerException("Null child not allowed");
        }
        return new TreePath<>(this, treeNode);
    }

    public TreePath<T> getParentPath() {
        return this.parentPath;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        int pathCount = getPathCount();
        for (int i = 0; i < pathCount; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(getPathComponent(i));
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Vector vector = new Vector();
        TreeNode<T> treeNode = this.lastPathComponent;
        if (treeNode != null && (treeNode instanceof Serializable)) {
            vector.addElement("lastPathComponent");
            vector.addElement(this.lastPathComponent);
        }
        objectOutputStream.writeObject(vector);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Vector vector = (Vector) objectInputStream.readObject();
        if (vector.size() <= 0 || !vector.elementAt(0).equals("lastPathComponent")) {
            return;
        }
        this.lastPathComponent = (TreeNode) vector.elementAt(1);
    }
}
