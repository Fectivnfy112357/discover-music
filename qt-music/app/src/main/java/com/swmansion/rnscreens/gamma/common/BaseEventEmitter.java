package com.swmansion.rnscreens.gamma.common;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseEventEmitter.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00058DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/swmansion/rnscreens/gamma/common/BaseEventEmitter;", "", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "viewTag", "", "(Lcom/facebook/react/bridge/ReactContext;I)V", "getReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "reactEventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getReactEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "surfaceId", "getSurfaceId", "()I", "getViewTag", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseEventEmitter {
    public static final String TAG = "BaseEventEmitter";
    private final ReactContext reactContext;
    private final EventDispatcher reactEventDispatcher;
    private final int viewTag;

    public BaseEventEmitter(ReactContext reactContext, int i) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.viewTag = i;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, i);
        if (eventDispatcherForReactTag == null) {
            throw new IllegalStateException(("[RNScreens] Nullish event dispatcher for view with tag: " + i).toString());
        }
        this.reactEventDispatcher = eventDispatcherForReactTag;
    }

    public final ReactContext getReactContext() {
        return this.reactContext;
    }

    public final int getViewTag() {
        return this.viewTag;
    }

    protected final EventDispatcher getReactEventDispatcher() {
        return this.reactEventDispatcher;
    }

    protected final int getSurfaceId() {
        return UIManagerHelper.getSurfaceId(this.reactContext);
    }
}
