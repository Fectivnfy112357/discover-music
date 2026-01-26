package com.facebook.react.fabric.mounting.mountitems;

import kotlin.Metadata;

/* compiled from: DispatchCommandMountItem.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/fabric/mounting/mountitems/DispatchCommandMountItem;", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "()V", "numRetries", "", "getRetries", "incrementRetries", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class DispatchCommandMountItem implements MountItem {
    private int numRetries;

    public final void incrementRetries() {
        this.numRetries++;
    }

    /* renamed from: getRetries, reason: from getter */
    public final int getNumRetries() {
        return this.numRetries;
    }
}
