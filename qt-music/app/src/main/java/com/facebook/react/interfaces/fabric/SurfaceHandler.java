package com.facebook.react.interfaces.fabric;

import com.facebook.react.bridge.NativeMap;
import kotlin.Metadata;

/* compiled from: SurfaceHandler.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J@\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0003H&J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u001d\u001a\u00020\u000eH&J\b\u0010\u001e\u001a\u00020\u000eH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/fabric/SurfaceHandler;", "", "isRunning", "", "()Z", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "surfaceId", "", "getSurfaceId", "()I", "setLayoutConstraints", "", "widthMeasureSpec", "heightMeasureSpec", "offsetX", "offsetY", "doLeftAndRightSwapInRTL", "isRTL", "pixelDensity", "", "setMountable", "mountable", "setProps", "props", "Lcom/facebook/react/bridge/NativeMap;", "setSurfaceId", "start", "stop", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SurfaceHandler {
    String getModuleName();

    int getSurfaceId();

    boolean isRunning();

    void setLayoutConstraints(int widthMeasureSpec, int heightMeasureSpec, int offsetX, int offsetY, boolean doLeftAndRightSwapInRTL, boolean isRTL, float pixelDensity);

    void setMountable(boolean mountable);

    void setProps(NativeMap props);

    void setSurfaceId(int surfaceId);

    void start();

    void stop();
}
