package org.jaudiotagger.utils.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

/* loaded from: classes3.dex */
public class DefaultMutableTreeNode<T> implements Cloneable, MutableTreeNode<T>, Serializable {
    private static final long serialVersionUID = 7195119412898901913L;
    protected boolean allowsChildren;
    protected Vector<TreeNode<T>> children;
    protected MutableTreeNode<T> parent;
    protected transient T userObject;

    public static final <X> Enumeration<X> emptyEnumeration() {
        return new Enumeration<X>() { // from class: org.jaudiotagger.utils.tree.DefaultMutableTreeNode.1
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return false;
            }

            @Override // java.util.Enumeration
            public X nextElement() {
                throw new NoSuchElementException("No more elements");
            }
        };
    }

    public DefaultMutableTreeNode() {
        this(null);
    }

    public DefaultMutableTreeNode(T t) {
        this(t, true);
    }

    public DefaultMutableTreeNode(T t, boolean z) {
        this.parent = null;
        this.allowsChildren = z;
        this.userObject = t;
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void insert(MutableTreeNode<T> mutableTreeNode, int i) {
        if (!this.allowsChildren) {
            throw new IllegalStateException("node does not allow children");
        }
        if (mutableTreeNode == null) {
            throw new IllegalArgumentException("new child is null");
        }
        if (isNodeAncestor(mutableTreeNode)) {
            throw new IllegalArgumentException("new child is an ancestor");
        }
        MutableTreeNode mutableTreeNode2 = (MutableTreeNode) mutableTreeNode.getParent();
        if (mutableTreeNode2 != null) {
            mutableTreeNode2.remove(mutableTreeNode);
        }
        mutableTreeNode.setParent(this);
        if (this.children == null) {
            this.children = new Vector<>();
        }
        this.children.insertElementAt(mutableTreeNode, i);
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void remove(int i) {
        MutableTreeNode mutableTreeNode = (MutableTreeNode) getChildAt(i);
        this.children.removeElementAt(i);
        mutableTreeNode.setParent(null);
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void setParent(MutableTreeNode<T> mutableTreeNode) {
        this.parent = mutableTreeNode;
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public TreeNode<T> getParent() {
        return this.parent;
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public TreeNode<T> getChildAt(int i) {
        Vector<TreeNode<T>> vector = this.children;
        if (vector == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return vector.elementAt(i);
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public int getChildCount() {
        Vector<TreeNode<T>> vector = this.children;
        if (vector == null) {
            return 0;
        }
        return vector.size();
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public int getIndex(TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (isNodeChild(treeNode)) {
            return this.children.indexOf(treeNode);
        }
        return -1;
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public Enumeration<TreeNode<T>> children() {
        Vector<TreeNode<T>> vector = this.children;
        if (vector == null) {
            return emptyEnumeration();
        }
        return vector.elements();
    }

    public void setAllowsChildren(boolean z) {
        if (z != this.allowsChildren) {
            this.allowsChildren = z;
            if (z) {
                return;
            }
            removeAllChildren();
        }
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public boolean getAllowsChildren() {
        return this.allowsChildren;
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void setUserObject(T t) {
        this.userObject = t;
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public T getUserObject() {
        return this.userObject;
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void removeFromParent() {
        MutableTreeNode mutableTreeNode = (MutableTreeNode) getParent();
        if (mutableTreeNode != null) {
            mutableTreeNode.remove(this);
        }
    }

    @Override // org.jaudiotagger.utils.tree.MutableTreeNode
    public void remove(MutableTreeNode<T> mutableTreeNode) {
        if (mutableTreeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!isNodeChild(mutableTreeNode)) {
            throw new IllegalArgumentException("argument is not a child");
        }
        remove(getIndex(mutableTreeNode));
    }

    public void removeAllChildren() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            remove(childCount);
        }
    }

    public void add(MutableTreeNode<T> mutableTreeNode) {
        if (mutableTreeNode != null && mutableTreeNode.getParent() == this) {
            insert(mutableTreeNode, getChildCount() - 1);
        } else {
            insert(mutableTreeNode, getChildCount());
        }
    }

    public boolean isNodeAncestor(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return false;
        }
        TreeNode<T> parent = this;
        while (parent != treeNode) {
            parent = parent.getParent();
            if (parent == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isNodeDescendant(DefaultMutableTreeNode<T> defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            return false;
        }
        return defaultMutableTreeNode.isNodeAncestor(this);
    }

    public TreeNode<T> getSharedAncestor(DefaultMutableTreeNode<T> defaultMutableTreeNode) {
        int i;
        TreeNode parent;
        if (defaultMutableTreeNode == this) {
            return this;
        }
        if (defaultMutableTreeNode == null) {
            return null;
        }
        int level = getLevel();
        int level2 = defaultMutableTreeNode.getLevel();
        if (level2 > level) {
            i = level2 - level;
            parent = this;
        } else {
            i = level - level2;
            parent = defaultMutableTreeNode;
            defaultMutableTreeNode = this;
        }
        while (i > 0) {
            defaultMutableTreeNode = defaultMutableTreeNode.getParent();
            i--;
        }
        while (defaultMutableTreeNode != parent) {
            defaultMutableTreeNode = defaultMutableTreeNode.getParent();
            parent = parent.getParent();
            if (defaultMutableTreeNode == null) {
                if (defaultMutableTreeNode == null && parent == null) {
                    return null;
                }
                throw new Error("nodes should be null");
            }
        }
        return defaultMutableTreeNode;
    }

    public boolean isNodeRelated(DefaultMutableTreeNode<T> defaultMutableTreeNode) {
        return defaultMutableTreeNode != null && getRoot() == defaultMutableTreeNode.getRoot();
    }

    public int getDepth() {
        Enumeration<TreeNode<T>> enumerationBreadthFirstEnumeration = breadthFirstEnumeration();
        TreeNode<T> treeNodeNextElement = null;
        while (enumerationBreadthFirstEnumeration.hasMoreElements()) {
            treeNodeNextElement = enumerationBreadthFirstEnumeration.nextElement();
        }
        if (treeNodeNextElement == null) {
            throw new Error("nodes should be null");
        }
        return ((DefaultMutableTreeNode) treeNodeNextElement).getLevel() - getLevel();
    }

    public int getLevel() {
        int i = 0;
        TreeNode parent = this;
        while (true) {
            parent = parent.getParent();
            if (parent == null) {
                return i;
            }
            i++;
        }
    }

    public TreeNode<T>[] getPath() {
        return getPathToRoot(this, 0);
    }

    protected TreeNode<T>[] getPathToRoot(TreeNode<T> treeNode, int i) {
        if (treeNode == null) {
            if (i == 0) {
                return null;
            }
            return new TreeNode[i];
        }
        int i2 = i + 1;
        TreeNode<T>[] pathToRoot = getPathToRoot(treeNode.getParent(), i2);
        pathToRoot[pathToRoot.length - i2] = treeNode;
        return pathToRoot;
    }

    public Object[] getUserObjectPath() {
        TreeNode<T>[] path = getPath();
        Object[] objArr = new Object[path.length];
        for (int i = 0; i < path.length; i++) {
            objArr[i] = ((DefaultMutableTreeNode) path[i]).getUserObject();
        }
        return objArr;
    }

    public TreeNode<T> getRoot() {
        DefaultMutableTreeNode<T> defaultMutableTreeNode = this;
        while (true) {
            TreeNode<T> parent = defaultMutableTreeNode.getParent();
            if (parent == null) {
                return defaultMutableTreeNode;
            }
            defaultMutableTreeNode = parent;
        }
    }

    public boolean isRoot() {
        return getParent() == null;
    }

    public DefaultMutableTreeNode<T> getNextNode() {
        if (getChildCount() == 0) {
            DefaultMutableTreeNode<T> nextSibling = getNextSibling();
            if (nextSibling != null) {
                return nextSibling;
            }
            for (DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) getParent(); defaultMutableTreeNode != null; defaultMutableTreeNode = (DefaultMutableTreeNode) defaultMutableTreeNode.getParent()) {
                DefaultMutableTreeNode<T> nextSibling2 = defaultMutableTreeNode.getNextSibling();
                if (nextSibling2 != null) {
                    return nextSibling2;
                }
            }
            return null;
        }
        return (DefaultMutableTreeNode) getChildAt(0);
    }

    public DefaultMutableTreeNode<T> getPreviousNode() {
        DefaultMutableTreeNode<T> defaultMutableTreeNode = (DefaultMutableTreeNode) getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        DefaultMutableTreeNode<T> previousSibling = getPreviousSibling();
        return previousSibling != null ? previousSibling.getChildCount() == 0 ? previousSibling : previousSibling.getLastLeaf() : defaultMutableTreeNode;
    }

    public Enumeration<TreeNode<T>> preorderEnumeration() {
        return new PreorderEnumeration(this);
    }

    public Enumeration<TreeNode<T>> postorderEnumeration() {
        return new PostorderEnumeration(this);
    }

    public Enumeration<TreeNode<T>> breadthFirstEnumeration() {
        return new BreadthFirstEnumeration(this);
    }

    public Enumeration<TreeNode<T>> depthFirstEnumeration() {
        return postorderEnumeration();
    }

    public Enumeration<TreeNode<T>> pathFromAncestorEnumeration(TreeNode<T> treeNode) {
        return new PathBetweenNodesEnumeration(treeNode, this);
    }

    public boolean isNodeChild(TreeNode<T> treeNode) {
        return (treeNode == null || getChildCount() == 0 || treeNode.getParent() != this) ? false : true;
    }

    public TreeNode<T> getFirstChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return getChildAt(0);
    }

    public TreeNode<T> getLastChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return getChildAt(getChildCount() - 1);
    }

    public TreeNode<T> getChildAfter(TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int index = getIndex(treeNode);
        if (index == -1) {
            throw new IllegalArgumentException("node is not a child");
        }
        if (index < getChildCount() - 1) {
            return getChildAt(index + 1);
        }
        return null;
    }

    public TreeNode<T> getChildBefore(TreeNode<T> treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int index = getIndex(treeNode);
        if (index == -1) {
            throw new IllegalArgumentException("argument is not a child");
        }
        if (index > 0) {
            return getChildAt(index - 1);
        }
        return null;
    }

    public boolean isNodeSibling(TreeNode<T> treeNode) {
        boolean z = false;
        if (treeNode == null) {
            return false;
        }
        if (treeNode == this) {
            return true;
        }
        TreeNode<T> parent = getParent();
        if (parent != null && parent == treeNode.getParent()) {
            z = true;
        }
        if (!z || ((DefaultMutableTreeNode) getParent()).isNodeChild(treeNode)) {
            return z;
        }
        throw new Error("sibling has different parent");
    }

    public int getSiblingCount() {
        TreeNode<T> parent = getParent();
        if (parent == null) {
            return 1;
        }
        return parent.getChildCount();
    }

    public DefaultMutableTreeNode<T> getNextSibling() {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) getParent();
        DefaultMutableTreeNode<T> defaultMutableTreeNode2 = defaultMutableTreeNode == null ? null : (DefaultMutableTreeNode) defaultMutableTreeNode.getChildAfter(this);
        if (defaultMutableTreeNode2 == null || isNodeSibling(defaultMutableTreeNode2)) {
            return defaultMutableTreeNode2;
        }
        throw new Error("child of parent is not a sibling");
    }

    public DefaultMutableTreeNode<T> getPreviousSibling() {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) getParent();
        DefaultMutableTreeNode<T> defaultMutableTreeNode2 = defaultMutableTreeNode == null ? null : (DefaultMutableTreeNode) defaultMutableTreeNode.getChildBefore(this);
        if (defaultMutableTreeNode2 == null || isNodeSibling(defaultMutableTreeNode2)) {
            return defaultMutableTreeNode2;
        }
        throw new Error("child of parent is not a sibling");
    }

    @Override // org.jaudiotagger.utils.tree.TreeNode
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    public DefaultMutableTreeNode<T> getFirstLeaf() {
        DefaultMutableTreeNode<T> defaultMutableTreeNode = this;
        while (!defaultMutableTreeNode.isLeaf()) {
            defaultMutableTreeNode = (DefaultMutableTreeNode) defaultMutableTreeNode.getFirstChild();
        }
        return defaultMutableTreeNode;
    }

    public DefaultMutableTreeNode<T> getLastLeaf() {
        DefaultMutableTreeNode<T> defaultMutableTreeNode = this;
        while (!defaultMutableTreeNode.isLeaf()) {
            defaultMutableTreeNode = (DefaultMutableTreeNode) defaultMutableTreeNode.getLastChild();
        }
        return defaultMutableTreeNode;
    }

    public DefaultMutableTreeNode<T> getNextLeaf() {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        DefaultMutableTreeNode<T> nextSibling = getNextSibling();
        if (nextSibling != null) {
            return nextSibling.getFirstLeaf();
        }
        return defaultMutableTreeNode.getNextLeaf();
    }

    public DefaultMutableTreeNode<T> getPreviousLeaf() {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        DefaultMutableTreeNode<T> previousSibling = getPreviousSibling();
        if (previousSibling != null) {
            return previousSibling.getLastLeaf();
        }
        return defaultMutableTreeNode.getPreviousLeaf();
    }

    public int getLeafCount() {
        Enumeration<TreeNode<T>> enumerationBreadthFirstEnumeration = breadthFirstEnumeration();
        int i = 0;
        while (enumerationBreadthFirstEnumeration.hasMoreElements()) {
            if (enumerationBreadthFirstEnumeration.nextElement().isLeaf()) {
                i++;
            }
        }
        if (i >= 1) {
            return i;
        }
        throw new Error("tree has zero leaves");
    }

    public String toString() {
        T t = this.userObject;
        if (t == null) {
            return null;
        }
        return t.toString();
    }

    public DefaultMutableTreeNode<T> clone() {
        try {
            DefaultMutableTreeNode<T> defaultMutableTreeNode = (DefaultMutableTreeNode) super.clone();
            defaultMutableTreeNode.children = null;
            defaultMutableTreeNode.parent = null;
            return defaultMutableTreeNode;
        } catch (CloneNotSupportedException e) {
            throw new Error(e.toString());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Object[] objArr;
        objectOutputStream.defaultWriteObject();
        T t = this.userObject;
        if (t != null && (t instanceof Serializable)) {
            objArr = new Object[]{"userObject", t};
        } else {
            objArr = new Object[0];
        }
        objectOutputStream.writeObject(objArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object[] objArr = (Object[]) objectInputStream.readObject();
        if (objArr.length <= 0 || !objArr[0].equals("userObject")) {
            return;
        }
        this.userObject = (T) objArr[1];
    }

    final class PreorderEnumeration implements Enumeration<TreeNode<T>> {
        protected Stack<Enumeration<TreeNode<T>>> stack;

        public PreorderEnumeration(TreeNode<T> treeNode) {
            Vector vector = new Vector(1);
            vector.addElement(treeNode);
            Stack<Enumeration<TreeNode<T>>> stack = new Stack<>();
            this.stack = stack;
            stack.push(vector.elements());
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return !this.stack.empty() && this.stack.peek().hasMoreElements();
        }

        @Override // java.util.Enumeration
        public TreeNode<T> nextElement() {
            Enumeration<TreeNode<T>> enumerationPeek = this.stack.peek();
            TreeNode<T> treeNodeNextElement = enumerationPeek.nextElement();
            Enumeration<TreeNode<T>> enumerationChildren = treeNodeNextElement.children();
            if (!enumerationPeek.hasMoreElements()) {
                this.stack.pop();
            }
            if (enumerationChildren.hasMoreElements()) {
                this.stack.push(enumerationChildren);
            }
            return treeNodeNextElement;
        }
    }

    final class PostorderEnumeration<X> implements Enumeration<TreeNode<X>> {
        protected Enumeration<TreeNode<X>> children;
        protected TreeNode<X> root;
        protected Enumeration<TreeNode<X>> subtree = DefaultMutableTreeNode.emptyEnumeration();

        public PostorderEnumeration(TreeNode<X> treeNode) {
            this.root = treeNode;
            this.children = treeNode.children();
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.root != null;
        }

        @Override // java.util.Enumeration
        public TreeNode<X> nextElement() {
            if (this.subtree.hasMoreElements()) {
                return this.subtree.nextElement();
            }
            if (this.children.hasMoreElements()) {
                PostorderEnumeration postorderEnumeration = new PostorderEnumeration(this.children.nextElement());
                this.subtree = postorderEnumeration;
                return postorderEnumeration.nextElement();
            }
            TreeNode<X> treeNode = this.root;
            this.root = null;
            return treeNode;
        }
    }

    final class BreadthFirstEnumeration<X> implements Enumeration<TreeNode<X>> {
        protected DefaultMutableTreeNode<T>.Queue<X>.Queue<Enumeration<TreeNode<X>>> queue;

        public BreadthFirstEnumeration(TreeNode<X> treeNode) {
            Vector vector = new Vector(1);
            vector.addElement(treeNode);
            DefaultMutableTreeNode<T>.Queue<X>.Queue<Enumeration<TreeNode<X>>> queue = new Queue<>();
            this.queue = queue;
            queue.enqueue(vector.elements());
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return !this.queue.isEmpty() && this.queue.firstObject().hasMoreElements();
        }

        @Override // java.util.Enumeration
        public TreeNode<X> nextElement() {
            Enumeration<TreeNode<X>> enumerationFirstObject = this.queue.firstObject();
            TreeNode<X> treeNodeNextElement = enumerationFirstObject.nextElement();
            Enumeration<TreeNode<X>> enumerationChildren = treeNodeNextElement.children();
            if (!enumerationFirstObject.hasMoreElements()) {
                this.queue.dequeue();
            }
            if (enumerationChildren.hasMoreElements()) {
                this.queue.enqueue(enumerationChildren);
            }
            return treeNodeNextElement;
        }

        final class Queue<Y> {
            DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> head;
            DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> tail;

            Queue() {
            }

            final class QNode<Z> {
                public DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Z> next;
                public Z object;

                public QNode(Z z, DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Z> qNode) {
                    this.object = z;
                    this.next = qNode;
                }
            }

            public void enqueue(Y y) {
                if (this.head == null) {
                    DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> qNode = new QNode<>(y, null);
                    this.tail = qNode;
                    this.head = qNode;
                } else {
                    this.tail.next = new QNode<>(y, null);
                    this.tail = this.tail.next;
                }
            }

            public Object dequeue() {
                DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> qNode = this.head;
                if (qNode == null) {
                    throw new NoSuchElementException("No more elements");
                }
                Y y = qNode.object;
                DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> qNode2 = this.head;
                DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> qNode3 = qNode2.next;
                this.head = qNode3;
                if (qNode3 == null) {
                    this.tail = null;
                } else {
                    qNode2.next = null;
                }
                return y;
            }

            public Y firstObject() {
                DefaultMutableTreeNode<T>.QNode<X>.QNode<Y>.QNode<Y> qNode = this.head;
                if (qNode == null) {
                    throw new NoSuchElementException("No more elements");
                }
                return qNode.object;
            }

            public boolean isEmpty() {
                return this.head == null;
            }
        }
    }

    final class PathBetweenNodesEnumeration<X> implements Enumeration<TreeNode<X>> {
        protected Stack<TreeNode<X>> stack;

        public PathBetweenNodesEnumeration(TreeNode<X> treeNode, TreeNode<X> treeNode2) {
            if (treeNode == null || treeNode2 == null) {
                throw new IllegalArgumentException("argument is null");
            }
            Stack<TreeNode<X>> stack = new Stack<>();
            this.stack = stack;
            stack.push(treeNode2);
            TreeNode<X> parent = treeNode2;
            while (parent != treeNode) {
                parent = parent.getParent();
                if (parent == null && treeNode2 != treeNode) {
                    throw new IllegalArgumentException("node " + treeNode + " is not an ancestor of " + treeNode2);
                }
                this.stack.push(parent);
            }
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.stack.size() > 0;
        }

        @Override // java.util.Enumeration
        public TreeNode<X> nextElement() {
            try {
                return this.stack.pop();
            } catch (EmptyStackException unused) {
                throw new NoSuchElementException("No more elements");
            }
        }
    }
}
