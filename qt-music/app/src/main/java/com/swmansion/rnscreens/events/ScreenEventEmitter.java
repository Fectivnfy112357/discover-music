package com.swmansion.rnscreens.events;

import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ScreenEventEmitter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\r\u0010\u0012\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\r\u0010\u0013\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\r\u0010\u0014\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\r\u0010\u0015\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/swmansion/rnscreens/events/ScreenEventEmitter;", "", "screen", "Lcom/swmansion/rnscreens/Screen;", "(Lcom/swmansion/rnscreens/Screen;)V", "reactEventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getReactEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "reactSurfaceId", "", "getReactSurfaceId", "()I", "getScreen", "()Lcom/swmansion/rnscreens/Screen;", "dispatchOnAppear", "", "()Lkotlin/Unit;", "dispatchOnDisappear", "dispatchOnDismissed", "dispatchOnWillAppear", "dispatchOnWillDisappear", "dispatchTransitionProgress", "progress", "", "isExitAnimation", "", "isGoingForward", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenEventEmitter {
    private final Screen screen;

    public ScreenEventEmitter(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.screen = screen;
    }

    public final Screen getScreen() {
        return this.screen;
    }

    public final EventDispatcher getReactEventDispatcher() {
        return this.screen.getReactEventDispatcher();
    }

    public final int getReactSurfaceId() {
        return UIManagerHelper.getSurfaceId(this.screen);
    }

    public final Unit dispatchOnWillAppear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenWillAppearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnAppear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenAppearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnWillDisappear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenWillDisappearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnDisappear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenDisappearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnDismissed() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenDismissedEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final void dispatchTransitionProgress(float progress, boolean isExitAnimation, boolean isGoingForward) {
        float fCoerceIn = RangesKt.coerceIn(progress, 0.0f, 1.0f);
        short coalescingKey = ScreenFragment.INSTANCE.getCoalescingKey(fCoerceIn);
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher != null) {
            reactEventDispatcher.dispatchEvent(new ScreenTransitionProgressEvent(getReactSurfaceId(), this.screen.getId(), fCoerceIn, isExitAnimation, isGoingForward, coalescingKey));
        }
    }
}
