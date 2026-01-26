package com.swmansion.rnscreens.bottomsheet;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenStackFragment;
import com.swmansion.rnscreens.bottomsheet.DimmingViewManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: DimmingViewManager.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0017J\u001c\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0017J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bJ\u001a\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006#"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/DimmingViewManager;", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "screen", "Lcom/swmansion/rnscreens/Screen;", "(Lcom/facebook/react/uimanager/ThemedReactContext;Lcom/swmansion/rnscreens/Screen;)V", "dimmingView", "Lcom/swmansion/rnscreens/bottomsheet/DimmingView;", "getDimmingView$react_native_screens_release", "()Lcom/swmansion/rnscreens/bottomsheet/DimmingView;", "dimmingViewCallback", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior$BottomSheetCallback;", "maxAlpha", "", "getMaxAlpha$react_native_screens_release", "()F", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "createDimmingView", "invalidate", "", "behavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "onBehaviourAttached", "onViewHierarchyCreated", "root", "Landroid/view/ViewGroup;", "requireBottomSheetCallback", "forceCreation", "", "willDimForDetentIndex", "index", "", "AnimateDimmingViewCallback", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DimmingViewManager {
    private final DimmingView dimmingView;
    private BottomSheetBehavior.BottomSheetCallback dimmingViewCallback;
    private final float maxAlpha;
    private final ThemedReactContext reactContext;

    public DimmingViewManager(ThemedReactContext reactContext, Screen screen) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.reactContext = reactContext;
        this.dimmingView = createDimmingView(screen);
        this.maxAlpha = 0.3f;
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* renamed from: getDimmingView$react_native_screens_release, reason: from getter */
    public final DimmingView getDimmingView() {
        return this.dimmingView;
    }

    /* renamed from: getMaxAlpha$react_native_screens_release, reason: from getter */
    public final float getMaxAlpha() {
        return this.maxAlpha;
    }

    public final void onViewHierarchyCreated(Screen screen, ViewGroup root) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(root, "root");
        root.addView(this.dimmingView, 0);
        if (!willDimForDetentIndex(screen, screen.getSheetInitialDetentIndex())) {
            this.dimmingView.setAlpha(0.0f);
        } else {
            this.dimmingView.setAlpha(this.maxAlpha);
        }
    }

    public final void onBehaviourAttached(Screen screen, BottomSheetBehavior<Screen> behavior) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        behavior.addBottomSheetCallback(requireBottomSheetCallback(screen, true));
    }

    public final boolean willDimForDetentIndex(Screen screen, int index) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return index > screen.getSheetLargestUndimmedDetentIndex();
    }

    public final void invalidate(BottomSheetBehavior<Screen> behavior) {
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = this.dimmingViewCallback;
        if (bottomSheetCallback == null || behavior == null) {
            return;
        }
        behavior.removeBottomSheetCallback(bottomSheetCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DimmingViewManager.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0017H\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/DimmingViewManager$AnimateDimmingViewCallback;", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior$BottomSheetCallback;", "screen", "Lcom/swmansion/rnscreens/Screen;", "viewToAnimate", "Landroid/view/View;", "maxAlpha", "", "(Lcom/swmansion/rnscreens/Screen;Landroid/view/View;F)V", "animator", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "firstDimmedOffset", "intervalLength", "largestUndimmedOffset", "getMaxAlpha", "()F", "getScreen", "()Lcom/swmansion/rnscreens/Screen;", "getViewToAnimate", "()Landroid/view/View;", "computeOffsetFromDetentIndex", "index", "", "onSlide", "", "bottomSheet", "slideOffset", "onStateChanged", "newState", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class AnimateDimmingViewCallback extends BottomSheetBehavior.BottomSheetCallback {
        private final ValueAnimator animator;
        private float firstDimmedOffset;
        private float intervalLength;
        private float largestUndimmedOffset;
        private final float maxAlpha;
        private final Screen screen;
        private final View viewToAnimate;

        public final Screen getScreen() {
            return this.screen;
        }

        public final View getViewToAnimate() {
            return this.viewToAnimate;
        }

        public final float getMaxAlpha() {
            return this.maxAlpha;
        }

        public AnimateDimmingViewCallback(Screen screen, View viewToAnimate, float f) {
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(viewToAnimate, "viewToAnimate");
            this.screen = screen;
            this.viewToAnimate = viewToAnimate;
            this.maxAlpha = f;
            this.largestUndimmedOffset = computeOffsetFromDetentIndex(screen.getSheetLargestUndimmedDetentIndex());
            float fComputeOffsetFromDetentIndex = computeOffsetFromDetentIndex(RangesKt.coerceIn(screen.getSheetLargestUndimmedDetentIndex() + 1, 0, screen.getSheetDetents().size() - 1));
            this.firstDimmedOffset = fComputeOffsetFromDetentIndex;
            this.intervalLength = fComputeOffsetFromDetentIndex - this.largestUndimmedOffset;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, f);
            valueAnimatorOfFloat.setDuration(1L);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.swmansion.rnscreens.bottomsheet.DimmingViewManager$AnimateDimmingViewCallback$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DimmingViewManager.AnimateDimmingViewCallback.animator$lambda$1$lambda$0(this.f$0, valueAnimator);
                }
            });
            this.animator = valueAnimatorOfFloat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void animator$lambda$1$lambda$0(AnimateDimmingViewCallback this$0, ValueAnimator it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "it");
            View view = this$0.viewToAnimate;
            Object animatedValue = it.getAnimatedValue();
            Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            view.setAlpha(((Float) animatedValue).floatValue());
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(View bottomSheet, int newState) {
            Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
            if (newState == 1 || newState == 2) {
                this.largestUndimmedOffset = computeOffsetFromDetentIndex(this.screen.getSheetLargestUndimmedDetentIndex());
                float fComputeOffsetFromDetentIndex = computeOffsetFromDetentIndex(RangesKt.coerceIn(this.screen.getSheetLargestUndimmedDetentIndex() + 1, 0, this.screen.getSheetDetents().size() - 1));
                this.firstDimmedOffset = fComputeOffsetFromDetentIndex;
                this.intervalLength = fComputeOffsetFromDetentIndex - this.largestUndimmedOffset;
            }
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(View bottomSheet, float slideOffset) {
            Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
            float f = this.largestUndimmedOffset;
            if (f >= slideOffset || slideOffset >= this.firstDimmedOffset) {
                return;
            }
            this.animator.setCurrentFraction((slideOffset - f) / this.intervalLength);
        }

        private final float computeOffsetFromDetentIndex(int index) {
            int size = this.screen.getSheetDetents().size();
            if (size != 1) {
                if (size != 2) {
                    if (size == 3 && index != -1) {
                        if (index != 0) {
                            if (index == 1) {
                                BottomSheetBehavior<Screen> sheetBehavior = this.screen.getSheetBehavior();
                                Intrinsics.checkNotNull(sheetBehavior);
                                return sheetBehavior.getHalfExpandedRatio();
                            }
                            if (index == 2) {
                                return 1.0f;
                            }
                        }
                        return 0.0f;
                    }
                } else if (index != -1) {
                    if (index != 0) {
                        if (index == 1) {
                            return 1.0f;
                        }
                    }
                    return 0.0f;
                }
            } else if (index != -1 && index == 0) {
                return 1.0f;
            }
            return -1.0f;
        }
    }

    private final DimmingView createDimmingView(final Screen screen) {
        DimmingView dimmingView = new DimmingView(this.reactContext, this.maxAlpha);
        dimmingView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        dimmingView.setOnClickListener(new View.OnClickListener() { // from class: com.swmansion.rnscreens.bottomsheet.DimmingViewManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DimmingViewManager.createDimmingView$lambda$2$lambda$1(screen, view);
            }
        });
        return dimmingView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createDimmingView$lambda$2$lambda$1(Screen screen, View view) {
        Intrinsics.checkNotNullParameter(screen, "$screen");
        if (screen.getSheetClosesOnTouchOutside()) {
            Fragment fragment = screen.getFragment();
            Intrinsics.checkNotNull(fragment, "null cannot be cast to non-null type com.swmansion.rnscreens.ScreenStackFragment");
            ((ScreenStackFragment) fragment).dismissSelf$react_native_screens_release();
        }
    }

    static /* synthetic */ BottomSheetBehavior.BottomSheetCallback requireBottomSheetCallback$default(DimmingViewManager dimmingViewManager, Screen screen, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return dimmingViewManager.requireBottomSheetCallback(screen, z);
    }

    private final BottomSheetBehavior.BottomSheetCallback requireBottomSheetCallback(Screen screen, boolean forceCreation) {
        if (this.dimmingViewCallback == null || forceCreation) {
            this.dimmingViewCallback = new AnimateDimmingViewCallback(screen, this.dimmingView, this.maxAlpha);
        }
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = this.dimmingViewCallback;
        Intrinsics.checkNotNull(bottomSheetCallback);
        return bottomSheetCallback;
    }
}
