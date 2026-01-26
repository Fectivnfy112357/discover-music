package com.swmansion.rnscreens;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenContentWrapper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0014R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/swmansion/rnscreens/ScreenContentWrapper;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "delegate", "Lcom/swmansion/rnscreens/ScreenContentWrapper$OnLayoutCallback;", "getDelegate$react_native_screens_release", "()Lcom/swmansion/rnscreens/ScreenContentWrapper$OnLayoutCallback;", "setDelegate$react_native_screens_release", "(Lcom/swmansion/rnscreens/ScreenContentWrapper$OnLayoutCallback;)V", ViewProps.ON_LAYOUT, "", "changed", "", "left", "", ViewProps.TOP, "right", ViewProps.BOTTOM, "OnLayoutCallback", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenContentWrapper extends ReactViewGroup {
    private OnLayoutCallback delegate;

    /* compiled from: ScreenContentWrapper.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/ScreenContentWrapper$OnLayoutCallback;", "", "onContentWrapperLayout", "", "changed", "", "left", "", ViewProps.TOP, "right", ViewProps.BOTTOM, "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnLayoutCallback {
        void onContentWrapperLayout(boolean changed, int left, int top, int right, int bottom);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenContentWrapper(ReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    /* renamed from: getDelegate$react_native_screens_release, reason: from getter */
    public final OnLayoutCallback getDelegate() {
        return this.delegate;
    }

    public final void setDelegate$react_native_screens_release(OnLayoutCallback onLayoutCallback) {
        this.delegate = onLayoutCallback;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        OnLayoutCallback onLayoutCallback = this.delegate;
        if (onLayoutCallback != null) {
            onLayoutCallback.onContentWrapperLayout(changed, left, top, right, bottom);
        }
    }
}
