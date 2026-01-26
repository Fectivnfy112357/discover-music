package com.swmansion.rnscreens;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.utils.PaddingBundle;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FabricEnabledHeaderConfigViewGroup.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ&\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006J(\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/swmansion/rnscreens/FabricEnabledHeaderConfigViewGroup;", "Landroid/view/ViewGroup;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", "lastHeight", "", "lastPaddingEnd", "lastPaddingStart", "setStateWrapper", "", "wrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "updateHeaderConfigState", "width", "height", ViewProps.PADDING_START, ViewProps.PADDING_END, "updateState", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class FabricEnabledHeaderConfigViewGroup extends ViewGroup {
    private static final double DELTA = 0.9d;
    private int lastHeight;
    private int lastPaddingEnd;
    private int lastPaddingStart;

    public final void setStateWrapper(StateWrapper wrapper) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FabricEnabledHeaderConfigViewGroup(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void updateHeaderConfigState(int width, int height, int paddingStart, int paddingEnd) {
        updateState(width, height, paddingStart, paddingEnd);
    }

    private final void updateState(int width, int height, int paddingStart, int paddingEnd) {
        if (Math.abs(this.lastPaddingStart - paddingStart) >= DELTA || Math.abs(this.lastPaddingEnd - paddingEnd) >= DELTA || Math.abs(this.lastHeight - height) >= DELTA) {
            this.lastPaddingStart = paddingStart;
            this.lastPaddingEnd = paddingEnd;
            this.lastHeight = height;
            Context context = getContext();
            ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
            UIManagerModule uIManagerModule = reactContext != null ? (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class) : null;
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), new PaddingBundle(height, paddingStart, paddingEnd));
            }
        }
    }
}
