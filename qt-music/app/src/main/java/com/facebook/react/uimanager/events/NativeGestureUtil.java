package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NativeGestureUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcom/facebook/react/uimanager/events/NativeGestureUtil;", "", "()V", "notifyNativeGestureEnded", "", "view", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "notifyNativeGestureStarted", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NativeGestureUtil {
    public static final NativeGestureUtil INSTANCE = new NativeGestureUtil();

    private NativeGestureUtil() {
    }

    @JvmStatic
    public static final void notifyNativeGestureStarted(View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(event, "event");
        RootView rootView = RootViewUtil.getRootView(view);
        if (rootView != null) {
            rootView.onChildStartedNativeGesture(view, event);
        }
    }

    @JvmStatic
    public static final void notifyNativeGestureEnded(View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(event, "event");
        RootView rootView = RootViewUtil.getRootView(view);
        if (rootView != null) {
            rootView.onChildEndedNativeGesture(view, event);
        }
    }
}
