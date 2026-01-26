package org.jaudiotagger.utils.tree;

import java.util.EventListener;

/* loaded from: classes3.dex */
public interface TreeModelListener extends EventListener {
    void treeNodesChanged(TreeModelEvent treeModelEvent);

    void treeNodesInserted(TreeModelEvent treeModelEvent);

    void treeNodesRemoved(TreeModelEvent treeModelEvent);

    void treeStructureChanged(TreeModelEvent treeModelEvent);
}
