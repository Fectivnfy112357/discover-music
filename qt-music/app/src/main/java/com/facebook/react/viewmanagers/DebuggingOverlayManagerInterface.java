package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;

/* loaded from: classes2.dex */
public interface DebuggingOverlayManagerInterface<T extends View> {
    void clearElementsHighlights(T t);

    void highlightElements(T t, ReadableArray readableArray);

    void highlightTraceUpdates(T t, ReadableArray readableArray);
}
