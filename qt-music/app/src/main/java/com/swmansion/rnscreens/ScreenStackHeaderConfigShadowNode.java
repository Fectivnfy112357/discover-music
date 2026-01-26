package com.swmansion.rnscreens;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.utils.PaddingBundle;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenStackHeaderConfigShadowNode.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStackHeaderConfigShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", f.X, "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "height", "", "getHeight", "()F", "setHeight", "(F)V", ViewProps.PADDING_END, "getPaddingEnd", "setPaddingEnd", ViewProps.PADDING_START, "getPaddingStart", "setPaddingStart", "setLocalData", "", "data", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenStackHeaderConfigShadowNode extends LayoutShadowNode {
    private ReactContext context;
    private float height;
    private float paddingEnd;
    private float paddingStart;

    public ScreenStackHeaderConfigShadowNode(ReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final float getPaddingStart() {
        return this.paddingStart;
    }

    public final void setPaddingStart(float f) {
        this.paddingStart = f;
    }

    public final float getPaddingEnd() {
        return this.paddingEnd;
    }

    public final void setPaddingEnd(float f) {
        this.paddingEnd = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object data) {
        if (data instanceof PaddingBundle) {
            PaddingBundle paddingBundle = (PaddingBundle) data;
            this.paddingStart = paddingBundle.getPaddingStart();
            this.paddingEnd = paddingBundle.getPaddingEnd();
            this.height = paddingBundle.getHeight();
            setPadding(4, this.paddingStart);
            setPadding(5, this.paddingEnd);
            setPosition(1, -this.height);
            return;
        }
        super.setLocalData(data);
    }
}
