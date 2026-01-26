package com.facebook.react.views.scroll;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.umeng.analytics.pro.f;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: ReactScrollViewHelper.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0019\bÆ\u0002\u0018\u00002\u00020\u0001:\bVWXYZ[\\]B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0007J+\u0010\u001e\u001a\u00020\u001c\"\u0014\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001fH\u0007¢\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\"H\u0007J%\u0010&\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001fH\u0007¢\u0006\u0002\u0010$J5\u0010'\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0002\u0010+J-\u0010,\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010-\u001a\u00020.H\u0002¢\u0006\u0002\u0010/JG\u0010,\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010-\u001a\u00020.2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\b\b\u0002\u00100\u001a\u00020\bH\u0002¢\u0006\u0002\u00101J5\u0010,\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0002\u0010+J5\u00102\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00103J%\u00104\u001a\u00020\u001c\"\u000e\b\u0000\u0010\u001f*\u0004\u0018\u00010!*\u00020\"2\u0006\u0010#\u001a\u0002H\u001fH\u0007¢\u0006\u0002\u0010$J+\u00105\u001a\u00020\u001c\"\u0014\b\u0000\u0010\u001f*\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001fH\u0007¢\u0006\u0002\u0010$J\u0012\u00108\u001a\u00020\u000f2\b\u00109\u001a\u0004\u0018\u00010:H\u0007JC\u0010;\u001a\u00020\u000f\"\u0014\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u000106*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010?J\u0012\u0010@\u001a\u00020\u000f2\b\u0010A\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010B\u001a\u00020\u000f2\b\u0010C\u001a\u0004\u0018\u00010\u0004H\u0007JK\u0010D\u001a\u00020E\"\u0014\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u000106*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010F\u001a\u00020\u000f2\u0006\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010JJ/\u0010K\u001a\u00020\u001c\"\u001a\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f¢\u0006\u0002\u0010$J\u0010\u0010L\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0007JA\u0010M\u001a\u00020\u001c\"\u001a\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00103J1\u0010P\u001a\u00020\u001c\"\u001a\b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001fH\u0007¢\u0006\u0002\u0010$J9\u0010P\u001a\u00020\u001c\"\u0014\b\u0000\u0010\u001f*\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u000f¢\u0006\u0002\u00103JG\u0010S\u001a\u00020\u001c\" \b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u00010!*\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0002\u0010+JO\u0010S\u001a\u00020\u001c\" \b\u0000\u0010\u001f*\u0004\u0018\u00010 *\u0004\u0018\u00010!*\u0004\u0018\u000106*\u0004\u0018\u000107*\u00020\"2\u0006\u0010#\u001a\u0002H\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010T\u001a\u00020\bH\u0007¢\u0006\u0002\u0010UR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0015*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper;", "", "()V", "AUTO", "", "CONTENT_OFFSET_LEFT", "CONTENT_OFFSET_TOP", "DEBUG_MODE", "", "MOMENTUM_DELAY", "", "OVER_SCROLL_ALWAYS", "OVER_SCROLL_NEVER", "SCROLL_AWAY_PADDING_TOP", "SMOOTH_SCROLL_DURATION", "", "SNAP_ALIGNMENT_CENTER", "SNAP_ALIGNMENT_DISABLED", "SNAP_ALIGNMENT_END", "SNAP_ALIGNMENT_START", AbstractID3v1Tag.TAG, "kotlin.jvm.PlatformType", "scrollListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ScrollListener;", "smoothScrollDurationInitialized", "addScrollListener", "", "listener", "dispatchMomentumEndOnAnimationEnd", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasFlingAnimator;", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasScrollEventThrottle;", "Landroid/view/ViewGroup;", "scrollView", "(Landroid/view/ViewGroup;)V", "emitLayoutEvent", "emitScrollBeginDragEvent", "emitScrollEndDragEvent", "xVelocity", "", "yVelocity", "(Landroid/view/ViewGroup;FF)V", "emitScrollEvent", "scrollEventType", "Lcom/facebook/react/views/scroll/ScrollEventType;", "(Landroid/view/ViewGroup;Lcom/facebook/react/views/scroll/ScrollEventType;)V", "experimental_isSynchronous", "(Landroid/view/ViewGroup;Lcom/facebook/react/views/scroll/ScrollEventType;FFZ)V", "emitScrollMomentumBeginEvent", "(Landroid/view/ViewGroup;II)V", "emitScrollMomentumEndEvent", "forceUpdateState", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasScrollState;", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasStateWrapper;", "getDefaultScrollAnimationDuration", f.X, "Landroid/content/Context;", "getNextFlingStartValue", "currentValue", "postAnimationValue", "velocity", "(Landroid/view/ViewGroup;III)I", "parseOverScrollMode", "jsOverScrollMode", "parseSnapToAlignment", "alignment", "predictFinalScrollPosition", "Landroid/graphics/Point;", "velocityX", "velocityY", "maximumOffsetX", "maximumOffsetY", "(Landroid/view/ViewGroup;IIII)Landroid/graphics/Point;", "registerFlingAnimator", "removeScrollListener", "smoothScrollTo", "x", "y", "updateFabricScrollState", "scrollX", "scrollY", "updateStateOnScrollChanged", "experimental_synchronous", "(Landroid/view/ViewGroup;FFZ)V", "HasFlingAnimator", "HasScrollEventThrottle", "HasScrollState", "HasSmoothScroll", "HasStateWrapper", "OverScrollerDurationGetter", "ReactScrollViewScrollState", "ScrollListener", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactScrollViewHelper {
    public static final String AUTO = "auto";
    private static final String CONTENT_OFFSET_LEFT = "contentOffsetLeft";
    private static final String CONTENT_OFFSET_TOP = "contentOffsetTop";
    private static final boolean DEBUG_MODE = false;
    public static final long MOMENTUM_DELAY = 20;
    public static final String OVER_SCROLL_ALWAYS = "always";
    public static final String OVER_SCROLL_NEVER = "never";
    private static final String SCROLL_AWAY_PADDING_TOP = "scrollAwayPaddingTop";
    public static final int SNAP_ALIGNMENT_CENTER = 2;
    public static final int SNAP_ALIGNMENT_DISABLED = 0;
    public static final int SNAP_ALIGNMENT_END = 3;
    public static final int SNAP_ALIGNMENT_START = 1;
    private static boolean smoothScrollDurationInitialized;
    public static final ReactScrollViewHelper INSTANCE = new ReactScrollViewHelper();
    private static final String TAG = "ReactHorizontalScrollView";
    private static final CopyOnWriteArrayList<WeakReference<ScrollListener>> scrollListeners = new CopyOnWriteArrayList<>();
    private static int SMOOTH_SCROLL_DURATION = 250;

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasFlingAnimator;", "", "getFlingAnimator", "Landroid/animation/ValueAnimator;", "getFlingExtrapolatedDistance", "", "velocity", "startFlingAnimator", "", "start", "end", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface HasFlingAnimator {
        ValueAnimator getFlingAnimator();

        int getFlingExtrapolatedDistance(int velocity);

        void startFlingAnimator(int start, int end);
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasScrollEventThrottle;", "", "lastScrollDispatchTime", "", "getLastScrollDispatchTime", "()J", "setLastScrollDispatchTime", "(J)V", "scrollEventThrottle", "", "getScrollEventThrottle", "()I", "setScrollEventThrottle", "(I)V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface HasScrollEventThrottle {
        long getLastScrollDispatchTime();

        int getScrollEventThrottle();

        void setLastScrollDispatchTime(long j);

        void setScrollEventThrottle(int i);
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasScrollState;", "", "reactScrollViewScrollState", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ReactScrollViewScrollState;", "getReactScrollViewScrollState", "()Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ReactScrollViewScrollState;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface HasScrollState {
        ReactScrollViewScrollState getReactScrollViewScrollState();
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasSmoothScroll;", "", "reactSmoothScrollTo", "", "x", "", "y", "scrollToPreservingMomentum", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface HasSmoothScroll {
        void reactSmoothScrollTo(int x, int y);

        void scrollToPreservingMomentum(int x, int y);
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasStateWrapper;", "", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "getStateWrapper", "()Lcom/facebook/react/uimanager/StateWrapper;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface HasStateWrapper {
        StateWrapper getStateWrapper();
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J,\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ScrollListener;", "", ViewProps.ON_LAYOUT, "", "scrollView", "Landroid/view/ViewGroup;", "onScroll", "scrollEventType", "Lcom/facebook/react/views/scroll/ScrollEventType;", "xVelocity", "", "yVelocity", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ScrollListener {
        void onLayout(ViewGroup scrollView);

        void onScroll(ViewGroup scrollView, ScrollEventType scrollEventType, float xVelocity, float yVelocity);
    }

    private ReactScrollViewHelper() {
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T scrollView, float xVelocity, float yVelocity) {
        emitScrollEvent$default(INSTANCE, scrollView, ScrollEventType.SCROLL, xVelocity, yVelocity, false, 16, null);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollBeginDragEvent(T scrollView) {
        INSTANCE.emitScrollEvent(scrollView, ScrollEventType.BEGIN_DRAG);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEndDragEvent(T scrollView, float xVelocity, float yVelocity) {
        emitScrollEvent$default(INSTANCE, scrollView, ScrollEventType.END_DRAG, xVelocity, yVelocity, false, 16, null);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumBeginEvent(T scrollView, int xVelocity, int yVelocity) {
        emitScrollEvent$default(INSTANCE, scrollView, ScrollEventType.MOMENTUM_BEGIN, xVelocity, yVelocity, false, 16, null);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumEndEvent(T scrollView) {
        INSTANCE.emitScrollEvent(scrollView, ScrollEventType.MOMENTUM_END);
    }

    private final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T scrollView, ScrollEventType scrollEventType) {
        emitScrollEvent$default(this, scrollView, scrollEventType, 0.0f, 0.0f, false, 16, null);
    }

    static /* synthetic */ void emitScrollEvent$default(ReactScrollViewHelper reactScrollViewHelper, ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        reactScrollViewHelper.emitScrollEvent(viewGroup, scrollEventType, f, f2, z);
    }

    private final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T scrollView, ScrollEventType scrollEventType, float xVelocity, float yVelocity, boolean experimental_isSynchronous) {
        View childAt;
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = scrollView;
        if (t.getScrollEventThrottle() < Math.max(17L, jCurrentTimeMillis - t.getLastScrollDispatchTime()) && (childAt = scrollView.getChildAt(0)) != null) {
            Iterator<WeakReference<ScrollListener>> it = scrollListeners.iterator();
            while (it.hasNext()) {
                ScrollListener scrollListener = it.next().get();
                if (scrollListener != null) {
                    scrollListener.onScroll(scrollView, scrollEventType, xVelocity, yVelocity);
                }
            }
            Context context = scrollView.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            ReactContext reactContext = (ReactContext) context;
            int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, scrollView.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(ScrollEvent.INSTANCE.obtain(surfaceId, scrollView.getId(), scrollEventType, scrollView.getScrollX(), scrollView.getScrollY(), xVelocity, yVelocity, childAt.getWidth(), childAt.getHeight(), scrollView.getWidth(), scrollView.getHeight(), experimental_isSynchronous));
                t.setLastScrollDispatchTime(jCurrentTimeMillis);
            }
        }
    }

    @JvmStatic
    public static final void emitLayoutEvent(ViewGroup scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Iterator<WeakReference<ScrollListener>> it = scrollListeners.iterator();
        while (it.hasNext()) {
            ScrollListener scrollListener = it.next().get();
            if (scrollListener != null) {
                scrollListener.onLayout(scrollView);
            }
        }
    }

    @JvmStatic
    public static final int parseOverScrollMode(String jsOverScrollMode) {
        if (jsOverScrollMode == null) {
            return 1;
        }
        int iHashCode = jsOverScrollMode.hashCode();
        if (iHashCode != -1414557169) {
            if (iHashCode != 3005871) {
                if (iHashCode == 104712844 && jsOverScrollMode.equals(OVER_SCROLL_NEVER)) {
                    return 2;
                }
            } else if (jsOverScrollMode.equals("auto")) {
                return 1;
            }
        } else if (jsOverScrollMode.equals(OVER_SCROLL_ALWAYS)) {
            return 0;
        }
        FLog.w(ReactConstants.TAG, "wrong overScrollMode: " + jsOverScrollMode);
        return 1;
    }

    @JvmStatic
    public static final int parseSnapToAlignment(String alignment) {
        if (alignment == null) {
            return 0;
        }
        if (StringsKt.equals("start", alignment, true)) {
            return 1;
        }
        if (StringsKt.equals("center", alignment, true)) {
            return 2;
        }
        if (Intrinsics.areEqual("end", alignment)) {
            return 3;
        }
        FLog.w(ReactConstants.TAG, "wrong snap alignment value: " + alignment);
        return 0;
    }

    @JvmStatic
    public static final int getDefaultScrollAnimationDuration(Context context) {
        if (!smoothScrollDurationInitialized) {
            smoothScrollDurationInitialized = true;
            try {
                SMOOTH_SCROLL_DURATION = new OverScrollerDurationGetter(context).getScrollAnimationDuration();
            } catch (Throwable unused) {
            }
        }
        return SMOOTH_SCROLL_DURATION;
    }

    @JvmStatic
    public static final void addScrollListener(ScrollListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        scrollListeners.add(new WeakReference<>(listener));
    }

    @JvmStatic
    public static final void removeScrollListener(ScrollListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        scrollListeners.remove(new WeakReference(listener));
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void smoothScrollTo(T scrollView, int x, int y) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollTo[%d] x %d y %d", Integer.valueOf(scrollView.getId()), Integer.valueOf(x), Integer.valueOf(y));
        }
        T t = scrollView;
        ValueAnimator flingAnimator = t.getFlingAnimator();
        if (flingAnimator.getListeners() == null || flingAnimator.getListeners().size() == 0) {
            INSTANCE.registerFlingAnimator(scrollView);
        }
        scrollView.getReactScrollViewScrollState().setFinalAnimatedPositionScroll(x, y);
        int scrollX = scrollView.getScrollX();
        int scrollY = scrollView.getScrollY();
        if (scrollX != x) {
            t.startFlingAnimator(scrollX, x);
        }
        if (scrollY != y) {
            t.startFlingAnimator(scrollY, y);
        }
        if (ReactNativeFeatureFlags.fixIncorrectScrollViewStateUpdateOnAndroid()) {
            INSTANCE.updateFabricScrollState(scrollView, x, y);
        }
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState> int getNextFlingStartValue(T scrollView, int currentValue, int postAnimationValue, int velocity) {
        ReactScrollViewScrollState reactScrollViewScrollState = scrollView.getReactScrollViewScrollState();
        return (!reactScrollViewScrollState.getIsFinished() || (reactScrollViewScrollState.getIsCanceled() && ((velocity != 0 ? velocity / Math.abs(velocity) : 0) * (postAnimationValue - currentValue) > 0))) ? postAnimationValue : currentValue;
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void updateFabricScrollState(T scrollView) {
        INSTANCE.updateFabricScrollState(scrollView, scrollView.getScrollX(), scrollView.getScrollY());
    }

    public final <T extends ViewGroup & HasScrollState & HasStateWrapper> void updateFabricScrollState(T scrollView, int scrollX, int scrollY) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d", Integer.valueOf(scrollView.getId()), Integer.valueOf(scrollX), Integer.valueOf(scrollY));
        }
        if (ViewUtil.getUIManagerType(scrollView.getId()) == 1) {
            return;
        }
        ReactScrollViewScrollState reactScrollViewScrollState = scrollView.getReactScrollViewScrollState();
        if (reactScrollViewScrollState.getLastStateUpdateScroll().equals(scrollX, scrollY)) {
            return;
        }
        reactScrollViewScrollState.setLastStateUpdateScroll(scrollX, scrollY);
        forceUpdateState(scrollView);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasScrollState & HasStateWrapper> void forceUpdateState(T scrollView) {
        int i;
        ReactScrollViewScrollState reactScrollViewScrollState = scrollView.getReactScrollViewScrollState();
        int scrollAwayPaddingTop = reactScrollViewScrollState.getScrollAwayPaddingTop();
        Point lastStateUpdateScroll = reactScrollViewScrollState.getLastStateUpdateScroll();
        int i2 = lastStateUpdateScroll.x;
        int i3 = lastStateUpdateScroll.y;
        if (reactScrollViewScrollState.getLayoutDirection() == 1) {
            View childAt = scrollView.getChildAt(0);
            i = -(((childAt != null ? childAt.getWidth() : 0) - i2) - scrollView.getWidth());
        } else {
            i = i2;
        }
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d fabricScrollX %d", Integer.valueOf(scrollView.getId()), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i));
        }
        StateWrapper stateWrapper = scrollView.getStateWrapper();
        if (stateWrapper != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble(CONTENT_OFFSET_LEFT, PixelUtil.toDIPFromPixel(i2));
            writableNativeMap.putDouble(CONTENT_OFFSET_TOP, PixelUtil.toDIPFromPixel(i3));
            writableNativeMap.putDouble(SCROLL_AWAY_PADDING_TOP, PixelUtil.toDIPFromPixel(scrollAwayPaddingTop));
            stateWrapper.updateState(writableNativeMap);
        }
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollEventThrottle & HasScrollState & HasStateWrapper> void updateStateOnScrollChanged(T scrollView, float xVelocity, float yVelocity) {
        updateStateOnScrollChanged(scrollView, xVelocity, yVelocity, false);
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollEventThrottle & HasScrollState & HasStateWrapper> void updateStateOnScrollChanged(T scrollView, float xVelocity, float yVelocity, boolean experimental_synchronous) {
        ReactScrollViewHelper reactScrollViewHelper = INSTANCE;
        reactScrollViewHelper.updateFabricScrollState(scrollView, scrollView.getScrollX(), scrollView.getScrollY());
        reactScrollViewHelper.emitScrollEvent(scrollView, ScrollEventType.SCROLL, xVelocity, yVelocity, experimental_synchronous);
    }

    public final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void registerFlingAnimator(final T scrollView) {
        scrollView.getFlingAnimator().addListener(new Animator.AnimatorListener() { // from class: com.facebook.react.views.scroll.ReactScrollViewHelper.registerFlingAnimator.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) scrollView).getReactScrollViewScrollState();
                reactScrollViewScrollState.setCanceled(false);
                reactScrollViewScrollState.setFinished(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ((HasScrollState) scrollView).getReactScrollViewScrollState().setFinished(true);
                ReactScrollViewHelper.updateFabricScrollState(scrollView);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ((HasScrollState) scrollView).getReactScrollViewScrollState().setCanceled(true);
            }
        });
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollEventThrottle> void dispatchMomentumEndOnAnimationEnd(final T scrollView) {
        scrollView.getFlingAnimator().addListener(new Animator.AnimatorListener() { // from class: com.facebook.react.views.scroll.ReactScrollViewHelper.dispatchMomentumEndOnAnimationEnd.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ReactScrollViewHelper.emitScrollMomentumEndEvent(scrollView);
                animator.removeListener(this);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                ReactScrollViewHelper.emitScrollMomentumEndEvent(scrollView);
                animator.removeListener(this);
            }
        });
    }

    @JvmStatic
    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState> Point predictFinalScrollPosition(T scrollView, int velocityX, int velocityY, int maximumOffsetX, int maximumOffsetY) {
        ReactScrollViewScrollState reactScrollViewScrollState = scrollView.getReactScrollViewScrollState();
        OverScroller overScroller = new OverScroller(scrollView.getContext());
        overScroller.setFriction(1.0f - reactScrollViewScrollState.getDecelerationRate());
        int width = (scrollView.getWidth() - scrollView.getPaddingStart()) - scrollView.getPaddingEnd();
        int height = (scrollView.getHeight() - scrollView.getPaddingBottom()) - scrollView.getPaddingTop();
        Point finalAnimatedPositionScroll = reactScrollViewScrollState.getFinalAnimatedPositionScroll();
        overScroller.fling(getNextFlingStartValue(scrollView, scrollView.getScrollX(), finalAnimatedPositionScroll.x, velocityX), getNextFlingStartValue(scrollView, scrollView.getScrollY(), finalAnimatedPositionScroll.y, velocityY), velocityX, velocityY, 0, maximumOffsetX, 0, maximumOffsetY, width / 2, height / 2);
        return new Point(overScroller.getFinalX(), overScroller.getFinalY());
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J0\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$OverScrollerDurationGetter;", "Landroid/widget/OverScroller;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", "currentScrollAnimationDuration", "", "scrollAnimationDuration", "getScrollAnimationDuration", "()I", "startScroll", "", "startX", "startY", "dx", "dy", "duration", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class OverScrollerDurationGetter extends OverScroller {
        private int currentScrollAnimationDuration;

        public OverScrollerDurationGetter(Context context) {
            super(context);
            this.currentScrollAnimationDuration = 250;
        }

        public final int getScrollAnimationDuration() {
            super.startScroll(0, 0, 0, 0);
            return this.currentScrollAnimationDuration;
        }

        @Override // android.widget.OverScroller
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            this.currentScrollAnimationDuration = duration;
        }
    }

    /* compiled from: ReactScrollViewHelper.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0019\"\u0004\b\u001c\u0010\u0004¨\u0006#"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewHelper$ReactScrollViewScrollState;", "", ViewProps.LAYOUT_DIRECTION, "", "(I)V", "decelerationRate", "", "getDecelerationRate", "()F", "setDecelerationRate", "(F)V", "finalAnimatedPositionScroll", "Landroid/graphics/Point;", "getFinalAnimatedPositionScroll", "()Landroid/graphics/Point;", "isCanceled", "", "()Z", "setCanceled", "(Z)V", "isFinished", "setFinished", "lastStateUpdateScroll", "getLastStateUpdateScroll", "getLayoutDirection", "()I", ReactScrollViewHelper.SCROLL_AWAY_PADDING_TOP, "getScrollAwayPaddingTop", "setScrollAwayPaddingTop", "setFinalAnimatedPositionScroll", "finalAnimatedPositionScrollX", "finalAnimatedPositionScrollY", "setLastStateUpdateScroll", "lastStateUpdateScrollX", "lastStateUpdateScrollY", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ReactScrollViewScrollState {
        private boolean isCanceled;
        private final int layoutDirection;
        private int scrollAwayPaddingTop;
        private final Point finalAnimatedPositionScroll = new Point();
        private final Point lastStateUpdateScroll = new Point(-1, -1);
        private boolean isFinished = true;
        private float decelerationRate = 0.985f;

        public ReactScrollViewScrollState(int i) {
            this.layoutDirection = i;
        }

        public final int getLayoutDirection() {
            return this.layoutDirection;
        }

        public final Point getFinalAnimatedPositionScroll() {
            return this.finalAnimatedPositionScroll;
        }

        public final int getScrollAwayPaddingTop() {
            return this.scrollAwayPaddingTop;
        }

        public final void setScrollAwayPaddingTop(int i) {
            this.scrollAwayPaddingTop = i;
        }

        public final Point getLastStateUpdateScroll() {
            return this.lastStateUpdateScroll;
        }

        /* renamed from: isCanceled, reason: from getter */
        public final boolean getIsCanceled() {
            return this.isCanceled;
        }

        public final void setCanceled(boolean z) {
            this.isCanceled = z;
        }

        /* renamed from: isFinished, reason: from getter */
        public final boolean getIsFinished() {
            return this.isFinished;
        }

        public final void setFinished(boolean z) {
            this.isFinished = z;
        }

        public final float getDecelerationRate() {
            return this.decelerationRate;
        }

        public final void setDecelerationRate(float f) {
            this.decelerationRate = f;
        }

        public final ReactScrollViewScrollState setFinalAnimatedPositionScroll(int finalAnimatedPositionScrollX, int finalAnimatedPositionScrollY) {
            this.finalAnimatedPositionScroll.set(finalAnimatedPositionScrollX, finalAnimatedPositionScrollY);
            return this;
        }

        public final ReactScrollViewScrollState setLastStateUpdateScroll(int lastStateUpdateScrollX, int lastStateUpdateScrollY) {
            this.lastStateUpdateScroll.set(lastStateUpdateScrollX, lastStateUpdateScrollY);
            return this;
        }
    }
}
