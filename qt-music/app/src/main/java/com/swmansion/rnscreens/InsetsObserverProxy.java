package com.swmansion.rnscreens;

import android.util.Log;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.umeng.analytics.pro.f;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsetsObserverProxy.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001J\n\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001J\u0006\u0010\"\u001a\u00020\u0012R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u000ej\b\u0012\u0004\u0012\u00020\u0001`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/swmansion/rnscreens/InsetsObserverProxy;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "()V", "allowRegistration", "", "getAllowRegistration", "()Z", "eventSourceView", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "hasBeenRegistered", "isObservingContextLifetime", "listeners", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "shouldForwardInsetsToView", "addOnApplyWindowInsetsListener", "", "listener", "getObservedView", "onApplyWindowInsets", "Landroidx/core/view/WindowInsetsCompat;", "v", "insets", "onHostDestroy", "onHostPause", "onHostResume", "registerOnView", "view", "registerWithContext", f.X, "Lcom/facebook/react/bridge/ReactApplicationContext;", "removeOnApplyWindowInsetsListener", "unregister", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InsetsObserverProxy implements OnApplyWindowInsetsListener, LifecycleEventListener {
    private static boolean hasBeenRegistered;
    private static boolean isObservingContextLifetime;
    public static final InsetsObserverProxy INSTANCE = new InsetsObserverProxy();
    private static final HashSet<OnApplyWindowInsetsListener> listeners = new HashSet<>();
    private static WeakReference<View> eventSourceView = new WeakReference<>(null);
    private static boolean shouldForwardInsetsToView = true;

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    private InsetsObserverProxy() {
    }

    private final boolean getAllowRegistration() {
        return !hasBeenRegistered || eventSourceView.get() == null;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        WindowInsetsCompat windowInsetsCompatOnApplyWindowInsets = shouldForwardInsetsToView ? ViewCompat.onApplyWindowInsets(v, insets) : insets;
        Intrinsics.checkNotNull(windowInsetsCompatOnApplyWindowInsets);
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            windowInsetsCompatOnApplyWindowInsets = ((OnApplyWindowInsetsListener) it.next()).onApplyWindowInsets(v, insets);
            Intrinsics.checkNotNullExpressionValue(windowInsetsCompatOnApplyWindowInsets, "onApplyWindowInsets(...)");
        }
        return windowInsetsCompatOnApplyWindowInsets;
    }

    public final void registerWithContext(ReactApplicationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isObservingContextLifetime) {
            Log.w("[RNScreens]", "InsetObserverProxy registers on new context while it has not been invalidated on the old one. Please report this as issue at https://github.com/software-mansion/react-native-screens/issues");
        }
        isObservingContextLifetime = true;
        context.addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        View observedView = getObservedView();
        if (hasBeenRegistered && observedView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(observedView, null);
            hasBeenRegistered = false;
            eventSourceView.clear();
        }
        isObservingContextLifetime = false;
    }

    public final void addOnApplyWindowInsetsListener(OnApplyWindowInsetsListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.add(listener);
    }

    public final void removeOnApplyWindowInsetsListener(OnApplyWindowInsetsListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.remove(listener);
    }

    public final boolean registerOnView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (!getAllowRegistration()) {
            return false;
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, this);
        eventSourceView = new WeakReference<>(view);
        hasBeenRegistered = true;
        return true;
    }

    public final void unregister() {
        View observedView = getObservedView();
        if (observedView != null) {
            if (!hasBeenRegistered) {
                observedView = null;
            }
            if (observedView != null) {
                ViewCompat.setOnApplyWindowInsetsListener(observedView, null);
            }
        }
    }

    private final View getObservedView() {
        return eventSourceView.get();
    }
}
