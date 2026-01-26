package com.swmansion.rnscreens;

import android.app.Activity;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.math.MathUtils;
import com.swmansion.rnscreens.bottomsheet.SheetUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenFooter.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0018*\u0002\u0006\r\b\u0007\u0018\u0000 >2\u00020\u0001:\u0001>B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00122\b\b\u0002\u0010+\u001a\u00020\u0012J\b\u0010,\u001a\u00020'H\u0014J\b\u0010-\u001a\u00020'H\u0014J0\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u0012H\u0014J6\u00104\u001a\u00020'2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0012J\u0014\u00105\u001a\u00020'2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001f0#J\b\u00107\u001a\u00020\u001fH\u0002J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020\u001f0#H\u0002J\u0010\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u0012H\u0002J\u0010\u0010;\u001a\u00020\u00122\u0006\u0010<\u001a\u00020\u0015H\u0002J\u0014\u0010=\u001a\u00020'2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001f0#R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001c\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006?"}, d2 = {"Lcom/swmansion/rnscreens/ScreenFooter;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "footerCallback", "com/swmansion/rnscreens/ScreenFooter$footerCallback$1", "Lcom/swmansion/rnscreens/ScreenFooter$footerCallback$1;", "hasReceivedInitialLayoutFromParent", "", "getHasReceivedInitialLayoutFromParent", "()Z", "insetsAnimation", "com/swmansion/rnscreens/ScreenFooter$insetsAnimation$1", "Lcom/swmansion/rnscreens/ScreenFooter$insetsAnimation$1;", "isAnimationControlledByKeyboard", "isCallbackRegistered", "lastBottomInset", "", "lastContainerHeight", "lastSlideOffset", "", "lastStableSheetState", "getReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "reactHeight", "getReactHeight", "()I", "reactWidth", "getReactWidth", "screenParent", "Lcom/swmansion/rnscreens/Screen;", "getScreenParent", "()Lcom/swmansion/rnscreens/Screen;", "sheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "getSheetBehavior", "()Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "layoutFooterOnYAxis", "", "containerHeight", "footerHeight", "sheetTop", "bottomInset", "onAttachedToWindow", "onDetachedFromWindow", ViewProps.ON_LAYOUT, "changed", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "onParentLayout", "registerWithSheetBehavior", "behavior", "requireScreenParent", "requireSheetBehavior", "sheetTopInStableState", MusicService.STATE_KEY, "sheetTopWhileDragging", "slideOffset", "unregisterWithSheetBehavior", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenFooter extends ReactViewGroup {
    public static final String TAG = "ScreenFooter";
    private ScreenFooter$footerCallback$1 footerCallback;
    private final ScreenFooter$insetsAnimation$1 insetsAnimation;
    private boolean isAnimationControlledByKeyboard;
    private boolean isCallbackRegistered;
    private int lastBottomInset;
    private int lastContainerHeight;
    private float lastSlideOffset;
    private int lastStableSheetState;
    private final ReactContext reactContext;

    public final ReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.swmansion.rnscreens.ScreenFooter$insetsAnimation$1] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.swmansion.rnscreens.ScreenFooter$footerCallback$1] */
    public ScreenFooter(ReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.lastStableSheetState = 5;
        ?? r0 = new WindowInsetsAnimationCompat.Callback() { // from class: com.swmansion.rnscreens.ScreenFooter$insetsAnimation$1
            {
                super(0);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                Intrinsics.checkNotNullParameter(bounds, "bounds");
                this.this$0.isAnimationControlledByKeyboard = true;
                WindowInsetsAnimationCompat.BoundsCompat boundsCompatOnStart = super.onStart(animation, bounds);
                Intrinsics.checkNotNullExpressionValue(boundsCompatOnStart, "onStart(...)");
                return boundsCompatOnStart;
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) {
                Intrinsics.checkNotNullParameter(insets, "insets");
                Intrinsics.checkNotNullParameter(runningAnimations, "runningAnimations");
                this.this$0.lastBottomInset = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
                ScreenFooter screenFooter = this.this$0;
                int i = screenFooter.lastContainerHeight;
                int reactHeight = this.this$0.getReactHeight();
                ScreenFooter screenFooter2 = this.this$0;
                screenFooter.layoutFooterOnYAxis(i, reactHeight, screenFooter2.sheetTopWhileDragging(screenFooter2.lastSlideOffset), this.this$0.lastBottomInset);
                return insets;
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public void onEnd(WindowInsetsAnimationCompat animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                this.this$0.isAnimationControlledByKeyboard = false;
            }
        };
        this.insetsAnimation = r0;
        Activity currentActivity = reactContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("[RNScreens] Context detached from activity while creating ScreenFooter".toString());
        }
        View decorView = currentActivity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        ViewCompat.setWindowInsetsAnimationCallback(decorView, (WindowInsetsAnimationCompat.Callback) r0);
        this.footerCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.swmansion.rnscreens.ScreenFooter$footerCallback$1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
                if (SheetUtils.INSTANCE.isStateStable(newState)) {
                    if (newState == 3 || newState == 4 || newState == 6) {
                        ScreenFooter screenFooter = this.this$0;
                        screenFooter.layoutFooterOnYAxis(screenFooter.lastContainerHeight, this.this$0.getReactHeight(), this.this$0.sheetTopInStableState(newState), this.this$0.lastBottomInset);
                    }
                    this.this$0.lastStableSheetState = newState;
                }
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
                Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
                this.this$0.lastSlideOffset = Math.max(slideOffset, 0.0f);
                if (this.this$0.isAnimationControlledByKeyboard) {
                    return;
                }
                ScreenFooter screenFooter = this.this$0;
                int i = screenFooter.lastContainerHeight;
                int reactHeight = this.this$0.getReactHeight();
                ScreenFooter screenFooter2 = this.this$0;
                screenFooter.layoutFooterOnYAxis(i, reactHeight, screenFooter2.sheetTopWhileDragging(screenFooter2.lastSlideOffset), this.this$0.lastBottomInset);
            }
        };
    }

    private final Screen getScreenParent() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    private final BottomSheetBehavior<Screen> getSheetBehavior() {
        return requireScreenParent().getSheetBehavior();
    }

    private final boolean getHasReceivedInitialLayoutFromParent() {
        return this.lastContainerHeight > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getReactHeight() {
        return getMeasuredHeight();
    }

    private final int getReactWidth() {
        return getMeasuredWidth();
    }

    private final Screen requireScreenParent() {
        Screen screenParent = getScreenParent();
        if (screenParent != null) {
            return screenParent;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private final BottomSheetBehavior<Screen> requireSheetBehavior() {
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            return sheetBehavior;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getHasReceivedInitialLayoutFromParent()) {
            layoutFooterOnYAxis(this.lastContainerHeight, bottom - top, sheetTopInStableState(requireSheetBehavior().getState()), this.lastBottomInset);
        }
    }

    public final void registerWithSheetBehavior(BottomSheetBehavior<Screen> behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        if (this.isCallbackRegistered) {
            return;
        }
        behavior.addBottomSheetCallback(this.footerCallback);
        this.isCallbackRegistered = true;
    }

    public final void unregisterWithSheetBehavior(BottomSheetBehavior<Screen> behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        if (this.isCallbackRegistered) {
            behavior.removeBottomSheetCallback(this.footerCallback);
            this.isCallbackRegistered = false;
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            registerWithSheetBehavior(sheetBehavior);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            unregisterWithSheetBehavior(sheetBehavior);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int sheetTopInStableState(int state) {
        BottomSheetBehavior<Screen> bottomSheetBehaviorRequireSheetBehavior = requireSheetBehavior();
        if (state == 3) {
            return bottomSheetBehaviorRequireSheetBehavior.getExpandedOffset();
        }
        if (state == 4) {
            return this.lastContainerHeight - bottomSheetBehaviorRequireSheetBehavior.getPeekHeight();
        }
        if (state == 5) {
            return this.lastContainerHeight;
        }
        if (state == 6) {
            return (int) (this.lastContainerHeight * (1 - bottomSheetBehaviorRequireSheetBehavior.getHalfExpandedRatio()));
        }
        throw new IllegalArgumentException("[RNScreens] use of stable-state method for unstable state");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int sheetTopWhileDragging(float slideOffset) {
        Screen screenParent = getScreenParent();
        return screenParent != null ? screenParent.getTop() : (int) MathUtils.lerp(sheetTopInStableState(4), sheetTopInStableState(3), slideOffset);
    }

    public final void onParentLayout(boolean changed, int left, int top, int right, int bottom, int containerHeight) {
        this.lastContainerHeight = containerHeight;
        layoutFooterOnYAxis$default(this, containerHeight, getReactHeight(), sheetTopInStableState(requireSheetBehavior().getState()), 0, 8, null);
    }

    public static /* synthetic */ void layoutFooterOnYAxis$default(ScreenFooter screenFooter, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        screenFooter.layoutFooterOnYAxis(i, i2, i3, i4);
    }

    public final void layoutFooterOnYAxis(int containerHeight, int footerHeight, int sheetTop, int bottomInset) {
        int iMax = ((containerHeight - footerHeight) - sheetTop) - Math.max(bottomInset, 0);
        int reactHeight = getReactHeight();
        setTop(Math.max(iMax, 0));
        setBottom(getTop() + reactHeight);
    }
}
