package com.swmansion.rnscreens.stack.views;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ChildDrawingOrderStrategyImpl.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/stack/views/ChildrenDrawingOrderStrategyBase;", "Lcom/swmansion/rnscreens/stack/views/ChildrenDrawingOrderStrategy;", ViewProps.ENABLED, "", "(Z)V", "getEnabled", "()Z", "setEnabled", "disable", "", "enable", "isEnabled", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ChildrenDrawingOrderStrategyBase implements ChildrenDrawingOrderStrategy {
    private boolean enabled;

    public ChildrenDrawingOrderStrategyBase() {
        this(false, 1, null);
    }

    public ChildrenDrawingOrderStrategyBase(boolean z) {
        this.enabled = z;
    }

    public /* synthetic */ ChildrenDrawingOrderStrategyBase(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    @Override // com.swmansion.rnscreens.stack.views.ChildrenDrawingOrderStrategy
    public void enable() {
        this.enabled = true;
    }

    @Override // com.swmansion.rnscreens.stack.views.ChildrenDrawingOrderStrategy
    public void disable() {
        this.enabled = false;
    }

    @Override // com.swmansion.rnscreens.stack.views.ChildrenDrawingOrderStrategy
    public boolean isEnabled() {
        return this.enabled;
    }
}
