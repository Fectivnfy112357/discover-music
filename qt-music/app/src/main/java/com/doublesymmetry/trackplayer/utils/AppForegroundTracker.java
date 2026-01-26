package com.doublesymmetry.trackplayer.utils;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.facebook.react.bridge.UiThreadUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppForegroundTracker.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/doublesymmetry/trackplayer/utils/AppForegroundTracker;", "", "()V", "activityCount", "", "backgrounded", "", "getBackgrounded", "()Z", "foregrounded", "getForegrounded", "start", "", "Observer", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AppForegroundTracker {
    public static final AppForegroundTracker INSTANCE = new AppForegroundTracker();
    private static int activityCount;

    private AppForegroundTracker() {
    }

    public final boolean getForegrounded() {
        return activityCount > 0;
    }

    public final boolean getBackgrounded() {
        return activityCount <= 0;
    }

    public final void start() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.doublesymmetry.trackplayer.utils.AppForegroundTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppForegroundTracker.start$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(Observer.INSTANCE);
    }

    /* compiled from: AppForegroundTracker.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/doublesymmetry/trackplayer/utils/AppForegroundTracker$Observer;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "()V", "onPause", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onResume", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Observer implements DefaultLifecycleObserver {
        public static final Observer INSTANCE = new Observer();

        private Observer() {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            super.onResume(owner);
            AppForegroundTracker appForegroundTracker = AppForegroundTracker.INSTANCE;
            AppForegroundTracker.activityCount++;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onPause(LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            super.onPause(owner);
            AppForegroundTracker appForegroundTracker = AppForegroundTracker.INSTANCE;
            AppForegroundTracker.activityCount--;
        }
    }
}
