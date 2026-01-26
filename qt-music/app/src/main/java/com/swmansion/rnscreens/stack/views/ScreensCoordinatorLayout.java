package com.swmansion.rnscreens.stack.views;

import android.content.Context;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.PointerEventsBoxNoneImpl;
import com.swmansion.rnscreens.ScreenStackFragment;
import com.swmansion.rnscreens.bottomsheet.SheetUtilsKt;
import com.swmansion.rnscreens.stack.anim.ScreensAnimation;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreensCoordinatorLayout.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0011\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016J0\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001aH\u0014J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/swmansion/rnscreens/stack/views/ScreensCoordinatorLayout;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "Lcom/facebook/react/uimanager/ReactPointerEventsView;", f.X, "Landroid/content/Context;", "fragment", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "(Landroid/content/Context;Lcom/swmansion/rnscreens/ScreenStackFragment;)V", "pointerEventsImpl", "(Landroid/content/Context;Lcom/swmansion/rnscreens/ScreenStackFragment;Lcom/facebook/react/uimanager/ReactPointerEventsView;)V", "animationListener", "Landroid/view/animation/Animation$AnimationListener;", "getFragment$react_native_screens_release", "()Lcom/swmansion/rnscreens/ScreenStackFragment;", "clearFocus", "", "getPointerEvents", "Lcom/facebook/react/uimanager/PointerEvents;", "kotlin.jvm.PlatformType", "onApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", ViewProps.ON_LAYOUT, "changed", "", CmcdData.Factory.STREAM_TYPE_LIVE, "", bm.aM, "r", "b", "startAnimation", "animation", "Landroid/view/animation/Animation;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreensCoordinatorLayout extends CoordinatorLayout implements ReactPointerEventsView {
    private final Animation.AnimationListener animationListener;
    private final ScreenStackFragment fragment;
    private final ReactPointerEventsView pointerEventsImpl;

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.pointerEventsImpl.getPointerEvents();
    }

    /* renamed from: getFragment$react_native_screens_release, reason: from getter */
    public final ScreenStackFragment getFragment() {
        return this.fragment;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreensCoordinatorLayout(Context context, ScreenStackFragment fragment, ReactPointerEventsView pointerEventsImpl) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pointerEventsImpl, "pointerEventsImpl");
        this.fragment = fragment;
        this.pointerEventsImpl = pointerEventsImpl;
        this.animationListener = new Animation.AnimationListener() { // from class: com.swmansion.rnscreens.stack.views.ScreensCoordinatorLayout$animationListener$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                this.this$0.getFragment().onViewAnimationStart();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                this.this$0.getFragment().onViewAnimationEnd();
            }
        };
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScreensCoordinatorLayout(Context context, ScreenStackFragment fragment) {
        this(context, fragment, new PointerEventsBoxNoneImpl());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(insets);
        Intrinsics.checkNotNullExpressionValue(windowInsetsOnApplyWindowInsets, "onApplyWindowInsets(...)");
        return windowInsetsOnApplyWindowInsets;
    }

    @Override // android.view.View
    public void startAnimation(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        ScreensAnimation screensAnimation = new ScreensAnimation(this.fragment);
        screensAnimation.setDuration(animation.getDuration());
        if ((animation instanceof AnimationSet) && !this.fragment.isRemoving()) {
            AnimationSet animationSet = (AnimationSet) animation;
            animationSet.addAnimation(screensAnimation);
            animationSet.setAnimationListener(this.animationListener);
            super.startAnimation(animationSet);
            return;
        }
        AnimationSet animationSet2 = new AnimationSet(true);
        animationSet2.addAnimation(animation);
        animationSet2.addAnimation(screensAnimation);
        animationSet2.setAnimationListener(this.animationListener);
        super.startAnimation(animationSet2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        if (getVisibility() != 4) {
            super.clearFocus();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (SheetUtilsKt.usesFormSheetPresentation(this.fragment.getScreen())) {
            this.fragment.getScreen().onBottomSheetBehaviorDidLayout$react_native_screens_release(changed);
        }
    }
}
