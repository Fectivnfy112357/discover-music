package com.facebook.react.runtime;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: ReactSurfaceView.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 52\u00020\u0001:\u00015B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0012H\u0014J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016J\b\u0010$\u001a\u00020\u0012H\u0016J\u0018\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0017H\u0016J\u001a\u0010)\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u0017H\u0016J0\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\u0006\u0010/\u001a\u00020\bH\u0014J\u0018\u00100\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u0012H\u0016J\u0010\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u0012H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/facebook/react/runtime/ReactSurfaceView;", "Lcom/facebook/react/ReactRootView;", f.X, "Landroid/content/Context;", "surface", "Lcom/facebook/react/runtime/ReactSurfaceImpl;", "(Landroid/content/Context;Lcom/facebook/react/runtime/ReactSurfaceImpl;)V", "heightMeasureSpec", "", "jsPointerDispatcher", "Lcom/facebook/react/uimanager/JSPointerDispatcher;", "jsTouchDispatcher", "Lcom/facebook/react/uimanager/JSTouchDispatcher;", "viewportOffset", "Landroid/graphics/Point;", "getViewportOffset", "()Landroid/graphics/Point;", "wasMeasured", "", "widthMeasureSpec", "dispatchJSPointerEvent", "", "event", "Landroid/view/MotionEvent;", "isCapture", "dispatchJSTouchEvent", "getCurrentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getJSModuleName", "", "getUIManagerType", "handleException", bm.aM, "", "hasActiveReactContext", "hasActiveReactInstance", "isViewAttachedToReactInstance", "onChildEndedNativeGesture", "childView", "Landroid/view/View;", "ev", "onChildStartedNativeGesture", ViewProps.ON_LAYOUT, "changed", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "onMeasure", "requestDisallowInterceptTouchEvent", "disallowIntercept", "setIsFabric", "isFabric", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactSurfaceView extends ReactRootView {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "ReactSurfaceView";
    private int heightMeasureSpec;
    private JSPointerDispatcher jsPointerDispatcher;
    private final JSTouchDispatcher jsTouchDispatcher;
    private final ReactSurfaceImpl surface;
    private boolean wasMeasured;
    private int widthMeasureSpec;

    @Override // com.facebook.react.ReactRootView, com.facebook.react.uimanager.ReactRoot
    public int getUIManagerType() {
        return 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactSurfaceView(Context context, ReactSurfaceImpl surface) {
        super(context);
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.surface = surface;
        ReactSurfaceView reactSurfaceView = this;
        this.jsTouchDispatcher = new JSTouchDispatcher(reactSurfaceView);
        if (ReactFeatureFlags.dispatchPointerEvents) {
            this.jsPointerDispatcher = new JSPointerDispatcher(reactSurfaceView);
        }
    }

    @Override // com.facebook.react.ReactRootView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size;
        int size2;
        Systrace.beginSection(0L, "ReactSurfaceView.onMeasure");
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int childCount = getChildCount();
            int iMax = 0;
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                iMax = Math.max(iMax, childAt.getLeft() + childAt.getMeasuredWidth() + childAt.getPaddingLeft() + childAt.getPaddingRight());
            }
            size = iMax;
        } else {
            size = View.MeasureSpec.getSize(widthMeasureSpec);
        }
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            int childCount2 = getChildCount();
            int iMax2 = 0;
            for (int i2 = 0; i2 < childCount2; i2++) {
                View childAt2 = getChildAt(i2);
                iMax2 = Math.max(iMax2, childAt2.getTop() + childAt2.getMeasuredHeight() + childAt2.getPaddingTop() + childAt2.getPaddingBottom());
            }
            size2 = iMax2;
        } else {
            size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(size, size2);
        this.wasMeasured = true;
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
        Point viewportOffset = getViewportOffset();
        this.surface.updateLayoutSpecs(widthMeasureSpec, heightMeasureSpec, viewportOffset.x, viewportOffset.y);
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.ReactRootView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.wasMeasured && changed) {
            Point viewportOffset = getViewportOffset();
            this.surface.updateLayoutSpecs(this.widthMeasureSpec, this.heightMeasureSpec, viewportOffset.x, viewportOffset.y);
        }
    }

    private final Point getViewportOffset() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        iArr[0] = iArr[0] - rect.left;
        iArr[1] = iArr[1] - rect.top;
        return new Point(iArr[0], iArr[1]);
    }

    @Override // com.facebook.react.ReactRootView, android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    @Override // com.facebook.react.ReactRootView, com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(View childView, MotionEvent ev) {
        JSPointerDispatcher jSPointerDispatcher;
        Intrinsics.checkNotNullParameter(ev, "ev");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher == null) {
            return;
        }
        this.jsTouchDispatcher.onChildStartedNativeGesture(ev, eventDispatcher);
        if (childView == null || (jSPointerDispatcher = this.jsPointerDispatcher) == null) {
            return;
        }
        jSPointerDispatcher.onChildStartedNativeGesture(childView, ev, eventDispatcher);
    }

    @Override // com.facebook.react.ReactRootView, com.facebook.react.uimanager.RootView
    public void onChildEndedNativeGesture(View childView, MotionEvent ev) {
        Intrinsics.checkNotNullParameter(childView, "childView");
        Intrinsics.checkNotNullParameter(ev, "ev");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher == null) {
            return;
        }
        this.jsTouchDispatcher.onChildEndedNativeGesture(ev, eventDispatcher);
        JSPointerDispatcher jSPointerDispatcher = this.jsPointerDispatcher;
        if (jSPointerDispatcher != null) {
            jSPointerDispatcher.onChildEndedNativeGesture();
        }
    }

    @Override // com.facebook.react.ReactRootView, com.facebook.react.uimanager.RootView
    public void handleException(Throwable t) {
        Intrinsics.checkNotNullParameter(t, "t");
        ReactHostImpl reactHost = this.surface.getReactHost();
        Intrinsics.checkNotNullExpressionValue(reactHost, "getReactHost(...)");
        String string = Objects.toString(t.getMessage(), "");
        Intrinsics.checkNotNull(string);
        reactHost.handleHostException(new IllegalViewOperationException(string, this, t));
    }

    @Override // com.facebook.react.ReactRootView
    public void setIsFabric(boolean isFabric) {
        super.setIsFabric(true);
    }

    @Override // com.facebook.react.ReactRootView, com.facebook.react.uimanager.ReactRoot
    public String getJSModuleName() {
        String moduleName = this.surface.getModuleName();
        Intrinsics.checkNotNullExpressionValue(moduleName, "<get-moduleName>(...)");
        return moduleName;
    }

    @Override // com.facebook.react.ReactRootView
    protected void dispatchJSTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher != null) {
            this.jsTouchDispatcher.handleTouchEvent(event, eventDispatcher, this.surface.getReactHost().getCurrentReactContext());
        } else {
            FLog.w(TAG, "Unable to dispatch touch events to JS as the React instance has not been attached");
        }
    }

    @Override // com.facebook.react.ReactRootView
    protected void dispatchJSPointerEvent(MotionEvent event, boolean isCapture) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.jsPointerDispatcher == null) {
            if (ReactFeatureFlags.dispatchPointerEvents) {
                FLog.w(TAG, "Unable to dispatch pointer events to JS before the dispatcher is available");
                return;
            }
            return;
        }
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher != null) {
            JSPointerDispatcher jSPointerDispatcher = this.jsPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(event, eventDispatcher, isCapture);
                return;
            }
            return;
        }
        FLog.w(TAG, "Unable to dispatch pointer events to JS as the React instance has not been attached");
    }

    @Override // com.facebook.react.ReactRootView
    public boolean hasActiveReactContext() {
        return this.surface.isAttached() && this.surface.getReactHost().getCurrentReactContext() != null;
    }

    @Override // com.facebook.react.ReactRootView
    public boolean hasActiveReactInstance() {
        return this.surface.isAttached() && this.surface.getReactHost().isInstanceInitialized();
    }

    @Override // com.facebook.react.ReactRootView
    public ReactContext getCurrentReactContext() {
        if (this.surface.isAttached()) {
            return this.surface.getReactHost().getCurrentReactContext();
        }
        return null;
    }

    @Override // com.facebook.react.ReactRootView
    public boolean isViewAttachedToReactInstance() {
        return this.surface.isAttached();
    }

    /* compiled from: ReactSurfaceView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/runtime/ReactSurfaceView$Companion;", "", "()V", AbstractID3v1Tag.TAG, "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
