package com.facebook.react.modules.core;

import com.facebook.fbreact.specs.NativeTimingSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimingModule.kt */
@ReactModule(name = "Timing")
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u0000 \"2\u00020\u00012\u00020\u0002:\u0001\"B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\rH\u0016J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010\u001f\u001a\u00020\u000bH\u0016J\u0010\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u0016H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/facebook/react/modules/core/TimingModule;", "Lcom/facebook/fbreact/specs/NativeTimingSpec;", "Lcom/facebook/react/modules/core/JavaScriptTimerExecutor;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "(Lcom/facebook/react/bridge/ReactApplicationContext;Lcom/facebook/react/devsupport/interfaces/DevSupportManager;)V", "javaTimerManager", "Lcom/facebook/react/modules/core/JavaTimerManager;", "callIdleCallbacks", "", "frameTime", "", "callTimers", "timerIDs", "Lcom/facebook/react/bridge/WritableArray;", "createTimer", "callbackIDDouble", "durationDouble", "jsSchedulingTime", "repeat", "", "deleteTimer", "timerIdDouble", "emitTimeDriftWarning", "warningMessage", "", "hasActiveTimersInRange", "rangeMs", "", "invalidate", "setSendIdleEvents", "sendIdleEvents", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TimingModule extends NativeTimingSpec implements JavaScriptTimerExecutor {
    public static final String NAME = "Timing";
    private final JavaTimerManager javaTimerManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimingModule(ReactApplicationContext reactContext, DevSupportManager devSupportManager) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        this.javaTimerManager = new JavaTimerManager(reactContext, this, ReactChoreographer.INSTANCE.getInstance(), devSupportManager);
    }

    @Override // com.facebook.fbreact.specs.NativeTimingSpec
    public void createTimer(double callbackIDDouble, double durationDouble, double jsSchedulingTime, boolean repeat) {
        this.javaTimerManager.createAndMaybeCallTimer((int) callbackIDDouble, (int) durationDouble, jsSchedulingTime, repeat);
    }

    @Override // com.facebook.fbreact.specs.NativeTimingSpec
    public void deleteTimer(double timerIdDouble) {
        this.javaTimerManager.deleteTimer((int) timerIdDouble);
    }

    @Override // com.facebook.fbreact.specs.NativeTimingSpec
    public void setSendIdleEvents(boolean sendIdleEvents) {
        this.javaTimerManager.setSendIdleEvents(sendIdleEvents);
    }

    @Override // com.facebook.react.modules.core.JavaScriptTimerExecutor
    public void callTimers(WritableArray timerIDs) {
        JSTimers jSTimers;
        Intrinsics.checkNotNullParameter(timerIDs, "timerIDs");
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn == null || (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) == null) {
            return;
        }
        jSTimers.callTimers(timerIDs);
    }

    @Override // com.facebook.react.modules.core.JavaScriptTimerExecutor
    public void callIdleCallbacks(double frameTime) {
        JSTimers jSTimers;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn == null || (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) == null) {
            return;
        }
        jSTimers.callIdleCallbacks(frameTime);
    }

    @Override // com.facebook.react.modules.core.JavaScriptTimerExecutor
    public void emitTimeDriftWarning(String warningMessage) {
        JSTimers jSTimers;
        Intrinsics.checkNotNullParameter(warningMessage, "warningMessage");
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn == null || (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) == null) {
            return;
        }
        jSTimers.emitTimeDriftWarning(warningMessage);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        this.javaTimerManager.onInstanceDestroy();
    }

    @VisibleForTesting
    public final boolean hasActiveTimersInRange(long rangeMs) {
        return this.javaTimerManager.hasActiveTimersInRange$ReactAndroid_release(rangeMs);
    }
}
