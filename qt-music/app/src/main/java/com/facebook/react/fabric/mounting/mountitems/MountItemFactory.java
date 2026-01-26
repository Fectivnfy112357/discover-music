package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.StateWrapper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MountItemFactory.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J5\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0013J<\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J \u0010\u001c\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0007¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/fabric/mounting/mountitems/MountItemFactory;", "", "()V", "createDispatchCommandMountItem", "Lcom/facebook/react/fabric/mounting/mountitems/DispatchCommandMountItem;", "surfaceId", "", "reactTag", "commandId", "commandArgs", "Lcom/facebook/react/bridge/ReadableArray;", "", "createIntBufferBatchMountItem", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "intBuf", "", "objBuf", "", "commitNumber", "(I[I[Ljava/lang/Object;I)Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "createPreAllocateViewMountItem", "component", "props", "Lcom/facebook/react/bridge/ReadableMap;", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "isLayoutable", "", "createSendAccessibilityEventMountItem", "eventType", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MountItemFactory {
    public static final MountItemFactory INSTANCE = new MountItemFactory();

    private MountItemFactory() {
    }

    @JvmStatic
    public static final DispatchCommandMountItem createDispatchCommandMountItem(int surfaceId, int reactTag, int commandId, ReadableArray commandArgs) {
        return new DispatchIntCommandMountItem(surfaceId, reactTag, commandId, commandArgs);
    }

    @JvmStatic
    public static final DispatchCommandMountItem createDispatchCommandMountItem(int surfaceId, int reactTag, String commandId, ReadableArray commandArgs) {
        Intrinsics.checkNotNullParameter(commandId, "commandId");
        return new DispatchStringCommandMountItem(surfaceId, reactTag, commandId, commandArgs);
    }

    @JvmStatic
    public static final MountItem createSendAccessibilityEventMountItem(int surfaceId, int reactTag, int eventType) {
        return new SendAccessibilityEventMountItem(surfaceId, reactTag, eventType);
    }

    @JvmStatic
    public static final MountItem createPreAllocateViewMountItem(int surfaceId, int reactTag, String component, ReadableMap props, StateWrapper stateWrapper, boolean isLayoutable) {
        Intrinsics.checkNotNullParameter(component, "component");
        return new PreAllocateViewMountItem(surfaceId, reactTag, component, props, stateWrapper, isLayoutable);
    }

    @JvmStatic
    public static final MountItem createIntBufferBatchMountItem(int surfaceId, int[] intBuf, Object[] objBuf, int commitNumber) {
        Intrinsics.checkNotNullParameter(intBuf, "intBuf");
        Intrinsics.checkNotNullParameter(objBuf, "objBuf");
        return new IntBufferBatchMountItem(surfaceId, intBuf, objBuf, commitNumber);
    }
}
