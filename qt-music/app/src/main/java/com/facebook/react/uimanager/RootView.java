package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes2.dex */
public interface RootView {
    void handleException(Throwable th);

    void onChildEndedNativeGesture(View view, MotionEvent motionEvent);

    void onChildStartedNativeGesture(View view, MotionEvent motionEvent);

    @Deprecated
    default void onChildStartedNativeGesture(MotionEvent motionEvent) {
        onChildStartedNativeGesture(null, motionEvent);
    }
}
