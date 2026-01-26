package com.facebook.react.views.drawer;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactDrawerLayout.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\u0011\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/drawer/ReactDrawerLayout;", "Landroidx/drawerlayout/widget/DrawerLayout;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "dragging", "", "drawerPosition", "", "drawerWidth", ReactDrawerLayoutManager.COMMAND_CLOSE_DRAWER, "", "closeDrawer$ReactAndroid_release", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "onTouchEvent", ReactDrawerLayoutManager.COMMAND_OPEN_DRAWER, "openDrawer$ReactAndroid_release", "setDrawerPosition", "newDrawerPosition", "setDrawerPosition$ReactAndroid_release", "setDrawerProperties", "setDrawerProperties$ReactAndroid_release", "setDrawerWidth", "drawerWidthInPx", "setDrawerWidth$ReactAndroid_release", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactDrawerLayout extends DrawerLayout {
    public static final int DEFAULT_DRAWER_WIDTH = -1;
    private boolean dragging;
    private int drawerPosition;
    private int drawerWidth;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.drawerPosition = GravityCompat.START;
        this.drawerWidth = -1;
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.facebook.react.views.drawer.ReactDrawerLayout.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                ReactAccessibilityDelegate.AccessibilityRole accessibilityRoleFromViewTag = ReactAccessibilityDelegate.AccessibilityRole.fromViewTag(host);
                if (accessibilityRoleFromViewTag != null) {
                    info.setClassName(ReactAccessibilityDelegate.AccessibilityRole.getValue(accessibilityRoleFromViewTag));
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(event, "event");
                super.onInitializeAccessibilityEvent(host, event);
                Object tag = host.getTag(R.id.accessibility_role);
                if (tag instanceof ReactAccessibilityDelegate.AccessibilityRole) {
                    event.setClassName(ReactAccessibilityDelegate.AccessibilityRole.getValue((ReactAccessibilityDelegate.AccessibilityRole) tag));
                }
            }
        });
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        try {
            if (!super.onInterceptTouchEvent(ev)) {
                return false;
            }
            NativeGestureUtil.notifyNativeGestureStarted(this, ev);
            this.dragging = true;
            return true;
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", e);
            return false;
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (ev.getActionMasked() == 1 && this.dragging) {
            NativeGestureUtil.notifyNativeGestureEnded(this, ev);
            this.dragging = false;
        }
        return super.onTouchEvent(ev);
    }

    public final void openDrawer$ReactAndroid_release() {
        openDrawer(this.drawerPosition);
    }

    public final void closeDrawer$ReactAndroid_release() {
        closeDrawer(this.drawerPosition);
    }

    public final void setDrawerPosition$ReactAndroid_release(int newDrawerPosition) {
        this.drawerPosition = newDrawerPosition;
        setDrawerProperties$ReactAndroid_release();
    }

    public final void setDrawerWidth$ReactAndroid_release(int drawerWidthInPx) {
        this.drawerWidth = drawerWidthInPx;
        setDrawerProperties$ReactAndroid_release();
    }

    public final void setDrawerProperties$ReactAndroid_release() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.drawerlayout.widget.DrawerLayout.LayoutParams");
            DrawerLayout.LayoutParams layoutParams2 = (DrawerLayout.LayoutParams) layoutParams;
            layoutParams2.gravity = this.drawerPosition;
            layoutParams2.width = this.drawerWidth;
            childAt.setLayoutParams(layoutParams2);
            childAt.setClickable(true);
        }
    }
}
