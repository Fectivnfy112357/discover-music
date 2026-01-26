package com.swmansion.rnscreens.bottomsheet;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.ext.NumericExtKt;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DimmingView.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 #2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001#B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0014J0\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001bH\u0014J\u0012\u0010\u001f\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010!H\u0017J\u0018\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0016R\u0014\u0010\f\u001a\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/DimmingView;", "Landroid/view/ViewGroup;", "Lcom/facebook/react/uimanager/ReactCompoundViewGroup;", "Lcom/facebook/react/uimanager/ReactPointerEventsView;", f.X, "Landroid/content/Context;", "initialAlpha", "", "(Landroid/content/Context;F)V", "pointerEventsProxy", "Lcom/swmansion/rnscreens/bottomsheet/DimmingViewPointerEventsProxy;", "(Landroid/content/Context;FLcom/swmansion/rnscreens/bottomsheet/DimmingViewPointerEventsProxy;)V", "blockGestures", "", "getBlockGestures$react_native_screens_release", "()Z", "getPointerEvents", "Lcom/facebook/react/uimanager/PointerEvents;", "kotlin.jvm.PlatformType", "interceptsTouchEvent", "x", "y", "onDetachedFromWindow", "", ViewProps.ON_LAYOUT, "changed", CmcdData.Factory.STREAM_TYPE_LIVE, "", bm.aM, "r", "b", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "reactTagForTouch", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DimmingView extends ViewGroup implements ReactCompoundViewGroup, ReactPointerEventsView {
    public static final String TAG = "DimmingView";
    private final DimmingViewPointerEventsProxy pointerEventsProxy;

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.pointerEventsProxy.getPointerEvents();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public /* synthetic */ DimmingView(Context context, float f, DimmingViewPointerEventsProxy dimmingViewPointerEventsProxy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? 0.6f : f, dimmingViewPointerEventsProxy);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DimmingView(Context context, float f, DimmingViewPointerEventsProxy pointerEventsProxy) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pointerEventsProxy, "pointerEventsProxy");
        this.pointerEventsProxy = pointerEventsProxy;
        pointerEventsProxy.setPointerEventsImpl(new DimmingViewPointerEventsImpl(this));
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setAlpha(f);
    }

    public /* synthetic */ DimmingView(Context context, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? 0.6f : f);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DimmingView(Context context, float f) {
        this(context, f, new DimmingViewPointerEventsProxy(null));
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final boolean getBlockGestures$react_native_screens_release() {
        return !NumericExtKt.equalWithRespectToEps$default(getAlpha(), 0.0f, 0.0f, 2, null);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (getBlockGestures$react_native_screens_release()) {
            callOnClick();
        }
        return getBlockGestures$react_native_screens_release();
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float x, float y) {
        throw new IllegalStateException("[RNScreens] DimmingView should never be asked for the view tag!");
    }

    @Override // com.facebook.react.uimanager.ReactCompoundViewGroup
    public boolean interceptsTouchEvent(float x, float y) {
        return getBlockGestures$react_native_screens_release();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.pointerEventsProxy.setPointerEventsImpl(null);
    }
}
