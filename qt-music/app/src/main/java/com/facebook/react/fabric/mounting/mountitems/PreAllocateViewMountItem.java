package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;

/* loaded from: classes2.dex */
final class PreAllocateViewMountItem implements MountItem {
    private final String mComponent;
    private final boolean mIsLayoutable;
    private final ReadableMap mProps;
    private final int mReactTag;
    private final StateWrapper mStateWrapper;
    private final int mSurfaceId;

    PreAllocateViewMountItem(int i, int i2, String str, ReadableMap readableMap, StateWrapper stateWrapper, boolean z) {
        this.mComponent = FabricNameComponentMapping.getFabricComponentName(str);
        this.mSurfaceId = i;
        this.mProps = readableMap;
        this.mStateWrapper = stateWrapper;
        this.mReactTag = i2;
        this.mIsLayoutable = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(FabricUIManager.TAG, "Skipping View PreAllocation; no SurfaceMountingManager found for [" + this.mSurfaceId + "]");
        } else {
            surfaceManager.preallocateView(this.mComponent, this.mReactTag, this.mProps, this.mStateWrapper, this.mIsLayoutable);
        }
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("PreAllocateViewMountItem [").append(this.mReactTag).append("] - component: ").append(this.mComponent).append(" surfaceId: ").append(this.mSurfaceId).append(" isLayoutable: ").append(this.mIsLayoutable);
        if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
            StringBuilder sbAppend2 = sbAppend.append(" props: ");
            ReadableMap readableMap = this.mProps;
            StringBuilder sbAppend3 = sbAppend2.append(readableMap != null ? readableMap.toString() : "<null>").append(" state: ");
            StateWrapper stateWrapper = this.mStateWrapper;
            sbAppend3.append(stateWrapper != null ? stateWrapper.toString() : "<null>");
        }
        return sbAppend.toString();
    }
}
