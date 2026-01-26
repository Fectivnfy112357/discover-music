package org.jaudiotagger.utils.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EventListener;
import java.util.Vector;

/* loaded from: classes3.dex */
public class DefaultTreeModel<T> implements Serializable, TreeModel<T> {
    private static final long serialVersionUID = -267197228234880401L;
    protected boolean asksAllowsChildren;
    protected EventListenerList listenerList;
    protected TreeNode<T> root;

    public DefaultTreeModel(TreeNode<T> treeNode) {
        this(treeNode, false);
    }

    public DefaultTreeModel(TreeNode<T> treeNode, boolean z) {
        this.listenerList = new EventListenerList();
        this.root = treeNode;
        this.asksAllowsChildren = z;
    }

    public void setAsksAllowsChildren(boolean z) {
        this.asksAllowsChildren = z;
    }

    public boolean asksAllowsChildren() {
        return this.asksAllowsChildren;
    }

    public void setRoot(TreeNode<T> treeNode) {
        TreeNode<T> treeNode2 = this.root;
        this.root = treeNode;
        if (treeNode == null && treeNode2 != null) {
            fireTreeStructureChanged(this, null);
        } else {
            nodeStructureChanged(treeNode);
        }
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public TreeNode<T> getRoot() {
        return this.root;
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public int getIndexOfChild(TreeNode<T> treeNode, TreeNode<T> treeNode2) {
        if (treeNode == null || treeNode2 == null) {
            return -1;
        }
        return treeNode.getIndex(treeNode2);
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public TreeNode<T> getChild(TreeNode<T> treeNode, int i) {
        return treeNode.getChildAt(i);
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public int getChildCount(TreeNode<T> treeNode) {
        return treeNode.getChildCount();
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public boolean isLeaf(TreeNode<T> treeNode) {
        if (this.asksAllowsChildren) {
            return !treeNode.getAllowsChildren();
        }
        return treeNode.isLeaf();
    }

    public void reload() {
        reload(this.root);
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public void valueForPathChanged(TreePath<T> treePath, T t) {
        MutableTreeNode mutableTreeNode = (MutableTreeNode) treePath.getLastPathComponent();
        mutableTreeNode.setUserObject(t);
        nodeChanged(mutableTreeNode);
    }

    public void insertNodeInto(MutableTreeNode<T> mutableTreeNode, MutableTreeNode<T> mutableTreeNode2, int i) {
        mutableTreeNode2.insert(mutableTreeNode, i);
        nodesWereInserted(mutableTreeNode2, new int[]{i});
    }

    public void removeNodeFromParent(MutableTreeNode<T> mutableTreeNode) {
        MutableTreeNode mutableTreeNode2 = (MutableTreeNode) mutableTreeNode.getParent();
        if (mutableTreeNode2 == null) {
            throw new IllegalArgumentException("node does not have a parent.");
        }
        int[] iArr = {mutableTreeNode2.getIndex(mutableTreeNode)};
        mutableTreeNode2.remove(iArr[0]);
        nodesWereRemoved(mutableTreeNode2, iArr, new TreeNode[]{mutableTreeNode});
    }

    public void nodeChanged(TreeNode<T> treeNode) {
        if (this.listenerList == null || treeNode == null) {
            return;
        }
        TreeNode<T> parent = treeNode.getParent();
        if (parent != null) {
            int index = parent.getIndex(treeNode);
            if (index != -1) {
                nodesChanged(parent, new int[]{index});
                return;
            }
            return;
        }
        if (treeNode == getRoot()) {
            nodesChanged(treeNode, null);
        }
    }

    public void reload(TreeNode<T> treeNode) {
        if (treeNode != null) {
            fireTreeStructureChanged(this, getPathToRoot(treeNode), null, null);
        }
    }

    public void nodesWereInserted(TreeNode<T> treeNode, int[] iArr) {
        if (this.listenerList == null || treeNode == null || iArr == null || iArr.length <= 0) {
            return;
        }
        int length = iArr.length;
        TreeNode<T>[] treeNodeArr = new TreeNode[length];
        for (int i = 0; i < length; i++) {
            treeNodeArr[i] = treeNode.getChildAt(iArr[i]);
        }
        fireTreeNodesInserted(this, getPathToRoot(treeNode), iArr, treeNodeArr);
    }

    public void nodesWereRemoved(TreeNode<T> treeNode, int[] iArr, TreeNode<T>[] treeNodeArr) {
        if (treeNode == null || iArr == null) {
            return;
        }
        fireTreeNodesRemoved(this, getPathToRoot(treeNode), iArr, treeNodeArr);
    }

    public void nodesChanged(TreeNode<T> treeNode, int[] iArr) {
        if (treeNode != null) {
            if (iArr != null) {
                int length = iArr.length;
                if (length > 0) {
                    TreeNode<T>[] treeNodeArr = new TreeNode[length];
                    for (int i = 0; i < length; i++) {
                        treeNodeArr[i] = treeNode.getChildAt(iArr[i]);
                    }
                    fireTreeNodesChanged(this, getPathToRoot(treeNode), iArr, treeNodeArr);
                    return;
                }
                return;
            }
            if (treeNode == getRoot()) {
                fireTreeNodesChanged(this, getPathToRoot(treeNode), null, null);
            }
        }
    }

    public void nodeStructureChanged(TreeNode<T> treeNode) {
        if (treeNode != null) {
            fireTreeStructureChanged(this, getPathToRoot(treeNode), null, null);
        }
    }

    public TreeNode<T>[] getPathToRoot(TreeNode<T> treeNode) {
        return getPathToRoot(treeNode, 0);
    }

    protected TreeNode<T>[] getPathToRoot(TreeNode<T> treeNode, int i) {
        TreeNode<T>[] pathToRoot;
        if (treeNode == null) {
            if (i == 0) {
                return null;
            }
            return new TreeNode[i];
        }
        int i2 = i + 1;
        if (treeNode == this.root) {
            pathToRoot = new TreeNode[i2];
        } else {
            pathToRoot = getPathToRoot(treeNode.getParent(), i2);
        }
        pathToRoot[pathToRoot.length - i2] = treeNode;
        return pathToRoot;
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public void addTreeModelListener(TreeModelListener treeModelListener) {
        this.listenerList.add(TreeModelListener.class, treeModelListener);
    }

    @Override // org.jaudiotagger.utils.tree.TreeModel
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
        this.listenerList.remove(TreeModelListener.class, treeModelListener);
    }

    public TreeModelListener[] getTreeModelListeners() {
        return (TreeModelListener[]) this.listenerList.getListeners(TreeModelListener.class);
    }

    protected void fireTreeNodesChanged(Object obj, TreeNode<T>[] treeNodeArr, int[] iArr, TreeNode<T>[] treeNodeArr2) {
        Object[] listenerList = this.listenerList.getListenerList();
        TreeModelEvent treeModelEvent = null;
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            if (listenerList[length] == TreeModelListener.class) {
                if (treeModelEvent == null) {
                    treeModelEvent = new TreeModelEvent(obj, treeNodeArr, iArr, treeNodeArr2);
                }
                ((TreeModelListener) listenerList[length + 1]).treeNodesChanged(treeModelEvent);
            }
        }
    }

    protected void fireTreeNodesInserted(Object obj, TreeNode<T>[] treeNodeArr, int[] iArr, TreeNode<T>[] treeNodeArr2) {
        Object[] listenerList = this.listenerList.getListenerList();
        TreeModelEvent treeModelEvent = null;
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            if (listenerList[length] == TreeModelListener.class) {
                if (treeModelEvent == null) {
                    treeModelEvent = new TreeModelEvent(obj, treeNodeArr, iArr, treeNodeArr2);
                }
                ((TreeModelListener) listenerList[length + 1]).treeNodesInserted(treeModelEvent);
            }
        }
    }

    protected void fireTreeNodesRemoved(Object obj, TreeNode<T>[] treeNodeArr, int[] iArr, TreeNode<T>[] treeNodeArr2) {
        Object[] listenerList = this.listenerList.getListenerList();
        TreeModelEvent treeModelEvent = null;
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            if (listenerList[length] == TreeModelListener.class) {
                if (treeModelEvent == null) {
                    treeModelEvent = new TreeModelEvent(obj, treeNodeArr, iArr, treeNodeArr2);
                }
                ((TreeModelListener) listenerList[length + 1]).treeNodesRemoved(treeModelEvent);
            }
        }
    }

    protected void fireTreeStructureChanged(Object obj, TreeNode<T>[] treeNodeArr, int[] iArr, TreeNode<T>[] treeNodeArr2) {
        Object[] listenerList = this.listenerList.getListenerList();
        TreeModelEvent treeModelEvent = null;
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            if (listenerList[length] == TreeModelListener.class) {
                if (treeModelEvent == null) {
                    treeModelEvent = new TreeModelEvent(obj, treeNodeArr, iArr, treeNodeArr2);
                }
                ((TreeModelListener) listenerList[length + 1]).treeStructureChanged(treeModelEvent);
            }
        }
    }

    private void fireTreeStructureChanged(Object obj, TreePath<T> treePath) {
        Object[] listenerList = this.listenerList.getListenerList();
        TreeModelEvent treeModelEvent = null;
        for (int length = listenerList.length - 2; length >= 0; length -= 2) {
            if (listenerList[length] == TreeModelListener.class) {
                if (treeModelEvent == null) {
                    treeModelEvent = new TreeModelEvent(obj, treePath);
                }
                ((TreeModelListener) listenerList[length + 1]).treeStructureChanged(treeModelEvent);
            }
        }
    }

    public <X extends EventListener> X[] getListeners(Class<X> cls) {
        return (X[]) this.listenerList.getListeners(cls);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Vector vector = new Vector();
        objectOutputStream.defaultWriteObject();
        TreeNode<T> treeNode = this.root;
        if (treeNode != null && (treeNode instanceof Serializable)) {
            vector.addElement("root");
            vector.addElement(this.root);
        }
        objectOutputStream.writeObject(vector);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Vector vector = (Vector) objectInputStream.readObject();
        if (vector.size() <= 0 || !vector.elementAt(0).equals("root")) {
            return;
        }
        this.root = (TreeNode) vector.elementAt(1);
    }
}
