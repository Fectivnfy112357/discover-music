package com.swmansion.rnscreens.bottomsheet;

import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DimmingViewPointerEvents.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/DimmingViewPointerEventsImpl;", "Lcom/facebook/react/uimanager/ReactPointerEventsView;", "dimmingView", "Lcom/swmansion/rnscreens/bottomsheet/DimmingView;", "(Lcom/swmansion/rnscreens/bottomsheet/DimmingView;)V", "getDimmingView", "()Lcom/swmansion/rnscreens/bottomsheet/DimmingView;", "getPointerEvents", "Lcom/facebook/react/uimanager/PointerEvents;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DimmingViewPointerEventsImpl implements ReactPointerEventsView {
    private final DimmingView dimmingView;

    public DimmingViewPointerEventsImpl(DimmingView dimmingView) {
        Intrinsics.checkNotNullParameter(dimmingView, "dimmingView");
        this.dimmingView = dimmingView;
    }

    public final DimmingView getDimmingView() {
        return this.dimmingView;
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.dimmingView.getBlockGestures$react_native_screens_release() ? PointerEvents.AUTO : PointerEvents.NONE;
    }
}
