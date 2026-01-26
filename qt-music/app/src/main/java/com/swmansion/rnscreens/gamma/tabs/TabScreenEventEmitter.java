package com.swmansion.rnscreens.gamma.tabs;

import com.facebook.react.bridge.ReactContext;
import com.swmansion.rnscreens.gamma.common.BaseEventEmitter;
import com.swmansion.rnscreens.gamma.tabs.event.TabScreenDidAppearEvent;
import com.swmansion.rnscreens.gamma.tabs.event.TabScreenDidDisappearEvent;
import com.swmansion.rnscreens.gamma.tabs.event.TabScreenWillAppearEvent;
import com.swmansion.rnscreens.gamma.tabs.event.TabScreenWillDisappearEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TabScreenEventEmitter.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\b¨\u0006\r"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;", "Lcom/swmansion/rnscreens/gamma/common/BaseEventEmitter;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "viewTag", "", "(Lcom/facebook/react/bridge/ReactContext;I)V", "emitOnDidAppear", "", "emitOnDidDisappear", "emitOnWillAppear", "emitOnWillDisappear", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabScreenEventEmitter extends BaseEventEmitter {
    public static final String TAG = "TabScreenEventEmitter";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabScreenEventEmitter(ReactContext reactContext, int i) {
        super(reactContext, i);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    public final void emitOnWillAppear() {
        TabScreenEventEmitterKt.logEventDispatch(getViewTag(), TabScreenWillAppearEvent.EVENT_REGISTRATION_NAME);
        getReactEventDispatcher().dispatchEvent(new TabScreenWillAppearEvent(getSurfaceId(), getViewTag()));
    }

    public final void emitOnDidAppear() {
        TabScreenEventEmitterKt.logEventDispatch(getViewTag(), TabScreenDidAppearEvent.EVENT_REGISTRATION_NAME);
        getReactEventDispatcher().dispatchEvent(new TabScreenDidAppearEvent(getSurfaceId(), getViewTag()));
    }

    public final void emitOnWillDisappear() {
        TabScreenEventEmitterKt.logEventDispatch(getViewTag(), TabScreenWillDisappearEvent.EVENT_REGISTRATION_NAME);
        getReactEventDispatcher().dispatchEvent(new TabScreenWillDisappearEvent(getSurfaceId(), getViewTag()));
    }

    public final void emitOnDidDisappear() {
        TabScreenEventEmitterKt.logEventDispatch(getViewTag(), TabScreenDidDisappearEvent.EVENT_REGISTRATION_NAME);
        getReactEventDispatcher().dispatchEvent(new TabScreenDidDisappearEvent(getSurfaceId(), getViewTag()));
    }
}
