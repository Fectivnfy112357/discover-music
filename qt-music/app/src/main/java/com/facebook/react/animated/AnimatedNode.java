package com.facebook.react.animated;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AnimatedNode.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0000J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0000H\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0000H\u0016J\b\u0010\u000f\u001a\u00020\u0010H&J\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0000J\b\u0010\u0013\u001a\u00020\nH\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/animated/AnimatedNode;", "", "()V", "BFSColor", "", "activeIncomingNodes", "children", "", "tag", "addChild", "", "child", "onAttachedToNode", "parent", "onDetachedFromNode", "prettyPrint", "", "prettyPrintWithChildren", "removeChild", "update", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AnimatedNode {
    public static final int DEFAULT_ANIMATED_NODE_CHILD_COUNT = 1;
    public static final int INITIAL_BFS_COLOR = 0;
    public int BFSColor;
    public int activeIncomingNodes;
    public List<AnimatedNode> children;
    public int tag = -1;

    public void onAttachedToNode(AnimatedNode parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
    }

    public void onDetachedFromNode(AnimatedNode parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
    }

    public abstract String prettyPrint();

    public void update() {
    }

    public final void addChild(AnimatedNode child) {
        Intrinsics.checkNotNullParameter(child, "child");
        ArrayList arrayList = this.children;
        if (arrayList == null) {
            arrayList = new ArrayList(1);
            this.children = arrayList;
        }
        arrayList.add(child);
        child.onAttachedToNode(this);
    }

    public final void removeChild(AnimatedNode child) {
        Intrinsics.checkNotNullParameter(child, "child");
        List<AnimatedNode> list = this.children;
        if (list == null) {
            return;
        }
        child.onDetachedFromNode(this);
        list.remove(child);
    }

    public final String prettyPrintWithChildren() {
        List<AnimatedNode> list = this.children;
        String strJoinToString$default = list != null ? CollectionsKt.joinToString$default(list, " ", null, null, 0, null, null, 62, null) : null;
        String str = strJoinToString$default;
        return prettyPrint() + ((str == null || StringsKt.isBlank(str)) ? "" : " children: " + strJoinToString$default);
    }
}
