package com.swmansion.rnscreens;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Choreographer;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.utils.InsetsKtKt;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomToolbar.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J0\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u0018H\u0014J\b\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u0016H\u0016J\u0006\u0010'\u001a\u00020\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006("}, d2 = {"Lcom/swmansion/rnscreens/CustomToolbar;", "Landroidx/appcompat/widget/Toolbar;", f.X, "Landroid/content/Context;", "config", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "(Landroid/content/Context;Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;)V", "getConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "isForceShadowStateUpdateOnLayoutRequested", "", "isLayoutEnqueued", "lastInsets", "Landroidx/core/graphics/Insets;", "layoutCallback", "Landroid/view/Choreographer$FrameCallback;", "shouldApplyTopInset", "getShouldApplyTopInset", "()Z", "shouldAvoidDisplayCutout", "getShouldAvoidDisplayCutout", "applyExactPadding", "", "left", "", ViewProps.TOP, "right", ViewProps.BOTTOM, "onApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", ViewProps.ON_LAYOUT, "hasSizeChanged", CmcdData.Factory.STREAM_TYPE_LIVE, bm.aM, "r", "b", "requestForceShadowStateUpdateOnLayout", "requestLayout", "updateContentInsets", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CustomToolbar extends Toolbar {
    private final ScreenStackHeaderConfig config;
    private boolean isForceShadowStateUpdateOnLayoutRequested;
    private boolean isLayoutEnqueued;
    private Insets lastInsets;
    private final Choreographer.FrameCallback layoutCallback;

    public final ScreenStackHeaderConfig getConfig() {
        return this.config;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomToolbar(Context context, ScreenStackHeaderConfig config) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        Insets NONE = Insets.NONE;
        Intrinsics.checkNotNullExpressionValue(NONE, "NONE");
        this.lastInsets = NONE;
        this.layoutCallback = new Choreographer.FrameCallback() { // from class: com.swmansion.rnscreens.CustomToolbar$layoutCallback$1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long frameTimeNanos) {
                this.this$0.isLayoutEnqueued = false;
                CustomToolbar customToolbar = this.this$0;
                customToolbar.measure(View.MeasureSpec.makeMeasureSpec(customToolbar.getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), Integer.MIN_VALUE));
                CustomToolbar customToolbar2 = this.this$0;
                customToolbar2.layout(customToolbar2.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
            }
        };
    }

    private final boolean getShouldAvoidDisplayCutout() {
        return this.config.getIsTopInsetEnabled();
    }

    private final boolean getShouldApplyTopInset() {
        return this.config.getIsTopInsetEnabled();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        Window window;
        WindowManager.LayoutParams attributes;
        super.requestLayout();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        Activity currentActivity = ((ThemedReactContext) context).getCurrentActivity();
        Integer numValueOf = (currentActivity == null || (window = currentActivity.getWindow()) == null || (attributes = window.getAttributes()) == null) ? null : Integer.valueOf(attributes.softInputMode);
        if (Build.VERSION.SDK_INT > 29 || numValueOf == null || numValueOf.intValue() != 32 || this.isLayoutEnqueued || this.layoutCallback == null) {
            return;
        }
        this.isLayoutEnqueued = true;
        ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.layoutCallback);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(insets);
        WindowInsets rootWindowInsets = getRootWindowInsets();
        CustomToolbar customToolbar = this;
        Insets insetsResolveInsetsOrZero$default = InsetsKtKt.resolveInsetsOrZero$default(customToolbar, WindowInsetsCompat.Type.displayCutout(), rootWindowInsets, false, 4, null);
        Insets insetsResolveInsetsOrZero$default2 = InsetsKtKt.resolveInsetsOrZero$default(customToolbar, WindowInsetsCompat.Type.systemBars(), rootWindowInsets, false, 4, null);
        Insets insetsResolveInsetsOrZero = InsetsKtKt.resolveInsetsOrZero(customToolbar, WindowInsetsCompat.Type.systemBars(), rootWindowInsets, true);
        Insets insetsOf = Insets.of(insetsResolveInsetsOrZero$default.left + insetsResolveInsetsOrZero$default2.left, 0, insetsResolveInsetsOrZero$default.right + insetsResolveInsetsOrZero$default2.right, 0);
        Intrinsics.checkNotNullExpressionValue(insetsOf, "of(...)");
        Insets insetsOf2 = Insets.of(0, Math.max(insetsResolveInsetsOrZero$default.top, getShouldApplyTopInset() ? insetsResolveInsetsOrZero.top : 0), 0, Math.max(insetsResolveInsetsOrZero$default.bottom, 0));
        Intrinsics.checkNotNullExpressionValue(insetsOf2, "of(...)");
        Insets insetsAdd = Insets.add(insetsOf, insetsOf2);
        Intrinsics.checkNotNullExpressionValue(insetsAdd, "add(...)");
        if (!Intrinsics.areEqual(this.lastInsets, insetsAdd)) {
            this.lastInsets = insetsAdd;
            applyExactPadding(insetsAdd.left, this.lastInsets.top, this.lastInsets.right, this.lastInsets.bottom);
        }
        return windowInsetsOnApplyWindowInsets;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean hasSizeChanged, int l, int t, int r, int b) {
        super.onLayout(hasSizeChanged, l, t, r, b);
        this.config.onNativeToolbarLayout(this, hasSizeChanged || this.isForceShadowStateUpdateOnLayoutRequested);
        this.isForceShadowStateUpdateOnLayoutRequested = false;
    }

    public final void updateContentInsets() {
        setContentInsetStartWithNavigation(this.config.getPreferredContentInsetStartWithNavigation());
        setContentInsetsRelative(this.config.getPreferredContentInsetStart(), this.config.getDefaultStartInset());
    }

    private final void applyExactPadding(int left, int top, int right, int bottom) {
        requestForceShadowStateUpdateOnLayout();
        setPadding(left, top, right, bottom);
    }

    private final void requestForceShadowStateUpdateOnLayout() {
        this.isForceShadowStateUpdateOnLayoutRequested = getShouldAvoidDisplayCutout();
    }
}
