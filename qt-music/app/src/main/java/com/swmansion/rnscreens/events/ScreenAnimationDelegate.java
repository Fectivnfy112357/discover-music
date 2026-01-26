package com.swmansion.rnscreens.events;

import android.animation.Animator;
import com.swmansion.rnscreens.ScreenStackFragmentWrapper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenAnimationDelegate.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0003\u0013\u0014\u0015B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate;", "Landroid/animation/Animator$AnimatorListener;", "wrapper", "Lcom/swmansion/rnscreens/ScreenStackFragmentWrapper;", "eventEmitter", "Lcom/swmansion/rnscreens/events/ScreenEventEmitter;", "animationType", "Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate$AnimationType;", "(Lcom/swmansion/rnscreens/ScreenStackFragmentWrapper;Lcom/swmansion/rnscreens/events/ScreenEventEmitter;Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate$AnimationType;)V", "currentState", "Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate$LifecycleState;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "progressState", "AnimationType", "Companion", "LifecycleState", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenAnimationDelegate implements Animator.AnimatorListener {
    public static final String TAG = "ScreenEventDelegate";
    private final AnimationType animationType;
    private LifecycleState currentState;
    private final ScreenEventEmitter eventEmitter;
    private final ScreenStackFragmentWrapper wrapper;

    /* compiled from: ScreenAnimationDelegate.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LifecycleState.values().length];
            try {
                iArr[LifecycleState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LifecycleState.START_DISPATCHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LifecycleState.END_DISPATCHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AnimationType.values().length];
            try {
                iArr2[AnimationType.ENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[AnimationType.EXIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    public ScreenAnimationDelegate(ScreenStackFragmentWrapper wrapper, ScreenEventEmitter screenEventEmitter, AnimationType animationType) {
        Intrinsics.checkNotNullParameter(wrapper, "wrapper");
        Intrinsics.checkNotNullParameter(animationType, "animationType");
        this.wrapper = wrapper;
        this.eventEmitter = screenEventEmitter;
        this.animationType = animationType;
        this.currentState = LifecycleState.INITIALIZED;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ScreenAnimationDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate$AnimationType;", "", "(Ljava/lang/String;I)V", "ENTER", "EXIT", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AnimationType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ AnimationType[] $VALUES;
        public static final AnimationType ENTER = new AnimationType("ENTER", 0);
        public static final AnimationType EXIT = new AnimationType("EXIT", 1);

        private static final /* synthetic */ AnimationType[] $values() {
            return new AnimationType[]{ENTER, EXIT};
        }

        public static EnumEntries<AnimationType> getEntries() {
            return $ENTRIES;
        }

        public static AnimationType valueOf(String str) {
            return (AnimationType) Enum.valueOf(AnimationType.class, str);
        }

        public static AnimationType[] values() {
            return (AnimationType[]) $VALUES.clone();
        }

        private AnimationType(String str, int i) {
        }

        static {
            AnimationType[] animationTypeArr$values = $values();
            $VALUES = animationTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(animationTypeArr$values);
        }
    }

    private final void progressState() {
        LifecycleState lifecycleState;
        int i = WhenMappings.$EnumSwitchMapping$0[this.currentState.ordinal()];
        if (i == 1) {
            lifecycleState = LifecycleState.START_DISPATCHED;
        } else {
            if (i != 2 && i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            lifecycleState = LifecycleState.END_DISPATCHED;
        }
        this.currentState = lifecycleState;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
        ScreenEventEmitter screenEventEmitter;
        Intrinsics.checkNotNullParameter(animation, "animation");
        if (this.currentState == LifecycleState.INITIALIZED) {
            progressState();
            int i = WhenMappings.$EnumSwitchMapping$1[this.animationType.ordinal()];
            if (i == 1) {
                ScreenEventEmitter screenEventEmitter2 = this.eventEmitter;
                if (screenEventEmitter2 != null) {
                    screenEventEmitter2.dispatchOnWillAppear();
                }
            } else if (i == 2 && (screenEventEmitter = this.eventEmitter) != null) {
                screenEventEmitter.dispatchOnWillDisappear();
            }
            boolean z = this.animationType == AnimationType.EXIT;
            ScreenEventEmitter screenEventEmitter3 = this.eventEmitter;
            if (screenEventEmitter3 != null) {
                screenEventEmitter3.dispatchTransitionProgress(0.0f, z, z);
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        ScreenEventEmitter screenEventEmitter;
        Intrinsics.checkNotNullParameter(animation, "animation");
        if (this.currentState == LifecycleState.START_DISPATCHED) {
            progressState();
            animation.removeListener(this);
            int i = WhenMappings.$EnumSwitchMapping$1[this.animationType.ordinal()];
            if (i == 1) {
                ScreenEventEmitter screenEventEmitter2 = this.eventEmitter;
                if (screenEventEmitter2 != null) {
                    screenEventEmitter2.dispatchOnAppear();
                }
            } else if (i == 2 && (screenEventEmitter = this.eventEmitter) != null) {
                screenEventEmitter.dispatchOnDisappear();
            }
            boolean z = this.animationType == AnimationType.EXIT;
            ScreenEventEmitter screenEventEmitter3 = this.eventEmitter;
            if (screenEventEmitter3 != null) {
                screenEventEmitter3.dispatchTransitionProgress(1.0f, z, z);
            }
            this.wrapper.getScreen().endRemovalTransition();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ScreenAnimationDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/events/ScreenAnimationDelegate$LifecycleState;", "", "(Ljava/lang/String;I)V", "INITIALIZED", "START_DISPATCHED", "END_DISPATCHED", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class LifecycleState {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ LifecycleState[] $VALUES;
        public static final LifecycleState INITIALIZED = new LifecycleState("INITIALIZED", 0);
        public static final LifecycleState START_DISPATCHED = new LifecycleState("START_DISPATCHED", 1);
        public static final LifecycleState END_DISPATCHED = new LifecycleState("END_DISPATCHED", 2);

        private static final /* synthetic */ LifecycleState[] $values() {
            return new LifecycleState[]{INITIALIZED, START_DISPATCHED, END_DISPATCHED};
        }

        public static EnumEntries<LifecycleState> getEntries() {
            return $ENTRIES;
        }

        public static LifecycleState valueOf(String str) {
            return (LifecycleState) Enum.valueOf(LifecycleState.class, str);
        }

        public static LifecycleState[] values() {
            return (LifecycleState[]) $VALUES.clone();
        }

        private LifecycleState(String str, int i) {
        }

        static {
            LifecycleState[] lifecycleStateArr$values = $values();
            $VALUES = lifecycleStateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(lifecycleStateArr$values);
        }
    }
}
