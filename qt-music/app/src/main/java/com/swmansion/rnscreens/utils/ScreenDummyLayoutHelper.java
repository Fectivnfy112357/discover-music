package com.swmansion.rnscreens.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.PixelUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.swmansion.rnscreens.ScreenStackHeaderConfig;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: ScreenDummyLayoutHelper.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0001\u0018\u0000 (2\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0012H\u0003J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\b\u0010!\u001a\u00020\u001bH\u0016J\b\u0010\"\u001a\u00020#H\u0002J\u001a\u0010$\u001a\u00020\u00032\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/swmansion/rnscreens/utils/ScreenDummyLayoutHelper;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "appBarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "cache", "Lcom/swmansion/rnscreens/utils/CacheEntry;", "coordinatorLayout", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "defaultContentInsetStartWithNavigation", "", "defaultFontSize", "", "dummyContentView", "Landroid/view/View;", "isLayoutInitialized", "", "reactContextRef", "Ljava/lang/ref/WeakReference;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "computeDummyLayout", "fontSize", "isTitleEmpty", "initDummyLayoutWithHeader", "", "contextWithTheme", "Landroid/content/Context;", "maybeInitDummyLayoutWithHeader", "onHostDestroy", "onHostPause", "onHostResume", "requireActivity", "Landroid/app/Activity;", "requireReactContext", "lazyMessage", "Lkotlin/Function0;", "", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenDummyLayoutHelper implements LifecycleEventListener {
    private static final String DEFAULT_HEADER_TITLE = "FontSize123!#$";
    public static final int FONT_SIZE_UNSET = -1;
    public static final String LIBRARY_NAME = "react_codegen_rnscreens";
    public static final String TAG = "ScreenDummyLayoutHelper";
    private AppBarLayout appBarLayout;
    private CacheEntry cache;
    private CoordinatorLayout coordinatorLayout;
    private int defaultContentInsetStartWithNavigation;
    private float defaultFontSize;
    private View dummyContentView;
    private volatile boolean isLayoutInitialized;
    private WeakReference<ReactApplicationContext> reactContextRef;
    private Toolbar toolbar;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static WeakReference<ScreenDummyLayoutHelper> weakInstance = new WeakReference<>(null);

    @JvmStatic
    public static final ScreenDummyLayoutHelper getInstance() {
        return INSTANCE.getInstance();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    public ScreenDummyLayoutHelper(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.cache = CacheEntry.INSTANCE.getEMPTY();
        this.reactContextRef = new WeakReference<>(reactContext);
        try {
            System.loadLibrary(LIBRARY_NAME);
        } catch (UnsatisfiedLinkError unused) {
            Log.w(TAG, "[RNScreens] Failed to load react_codegen_rnscreens library.");
        }
        weakInstance = new WeakReference<>(this);
        if (reactContext.hasCurrentActivity() && maybeInitDummyLayoutWithHeader(reactContext)) {
            return;
        }
        reactContext.addLifecycleEventListener(this);
    }

    private final boolean maybeInitDummyLayoutWithHeader(ReactApplicationContext reactContext) {
        if (this.isLayoutInitialized) {
            return true;
        }
        if (!reactContext.hasCurrentActivity()) {
            return false;
        }
        Activity currentActivity = reactContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalArgumentException("[RNScreens] Attempt to use context detached from activity. This could happen only due to race-condition.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(currentActivity, "requireNotNull(...)");
        synchronized (this) {
            if (this.isLayoutInitialized) {
                return true;
            }
            initDummyLayoutWithHeader(currentActivity);
            Unit unit = Unit.INSTANCE;
            return true;
        }
    }

    private final void initDummyLayoutWithHeader(Context contextWithTheme) {
        this.coordinatorLayout = new CoordinatorLayout(contextWithTheme);
        AppBarLayout appBarLayout = new AppBarLayout(contextWithTheme);
        appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(-1, -2));
        this.appBarLayout = appBarLayout;
        Toolbar toolbar = new Toolbar(contextWithTheme);
        toolbar.setTitle(DEFAULT_HEADER_TITLE);
        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
        layoutParams.setScrollFlags(0);
        toolbar.setLayoutParams(layoutParams);
        this.toolbar = toolbar;
        ScreenStackHeaderConfig.Companion companion = ScreenStackHeaderConfig.INSTANCE;
        Toolbar toolbar2 = this.toolbar;
        View view = null;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar2 = null;
        }
        TextView textViewFindTitleTextViewInToolbar = companion.findTitleTextViewInToolbar(toolbar2);
        Intrinsics.checkNotNull(textViewFindTitleTextViewInToolbar);
        this.defaultFontSize = textViewFindTitleTextViewInToolbar.getTextSize();
        Toolbar toolbar3 = this.toolbar;
        if (toolbar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar3 = null;
        }
        this.defaultContentInsetStartWithNavigation = toolbar3.getContentInsetStartWithNavigation();
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            appBarLayout2 = null;
        }
        Toolbar toolbar4 = this.toolbar;
        if (toolbar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar4 = null;
        }
        appBarLayout2.addView(toolbar4);
        View view2 = new View(contextWithTheme);
        view2.setLayoutParams(new CoordinatorLayout.LayoutParams(-1, -1));
        this.dummyContentView = view2;
        CoordinatorLayout coordinatorLayout = this.coordinatorLayout;
        if (coordinatorLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
            coordinatorLayout = null;
        }
        AppBarLayout appBarLayout3 = this.appBarLayout;
        if (appBarLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            appBarLayout3 = null;
        }
        coordinatorLayout.addView(appBarLayout3);
        View view3 = this.dummyContentView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyContentView");
        } else {
            view = view3;
        }
        coordinatorLayout.addView(view);
        this.isLayoutInitialized = true;
    }

    private final float computeDummyLayout(int fontSize, boolean isTitleEmpty) {
        if (!this.isLayoutInitialized && !maybeInitDummyLayoutWithHeader(requireReactContext(new Function0<Object>() { // from class: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$computeDummyLayout$reactContext$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "[RNScreens] Context was null-ed before dummy layout was initialized";
            }
        }))) {
            Log.e(TAG, "[RNScreens] Failed to late-init layout while computing header height. This is most likely a race-condition-bug in react-native-screens, please file an issue at https://github.com/software-mansion/react-native-screens/issues");
            return 0.0f;
        }
        if (this.cache.hasKey(new CacheKey(fontSize, isTitleEmpty))) {
            return this.cache.getHeaderHeight();
        }
        View decorView = requireActivity().getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        int width = decorView.getWidth();
        int height = decorView.getHeight();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
        AppBarLayout appBarLayout = null;
        if (isTitleEmpty) {
            Toolbar toolbar = this.toolbar;
            if (toolbar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                toolbar = null;
            }
            toolbar.setTitle("");
            Toolbar toolbar2 = this.toolbar;
            if (toolbar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                toolbar2 = null;
            }
            toolbar2.setContentInsetStartWithNavigation(0);
        } else {
            Toolbar toolbar3 = this.toolbar;
            if (toolbar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                toolbar3 = null;
            }
            toolbar3.setTitle(DEFAULT_HEADER_TITLE);
            Toolbar toolbar4 = this.toolbar;
            if (toolbar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                toolbar4 = null;
            }
            toolbar4.setContentInsetStartWithNavigation(this.defaultContentInsetStartWithNavigation);
        }
        ScreenStackHeaderConfig.Companion companion = ScreenStackHeaderConfig.INSTANCE;
        Toolbar toolbar5 = this.toolbar;
        if (toolbar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar5 = null;
        }
        TextView textViewFindTitleTextViewInToolbar = companion.findTitleTextViewInToolbar(toolbar5);
        if (textViewFindTitleTextViewInToolbar != null) {
            textViewFindTitleTextViewInToolbar.setTextSize(fontSize != -1 ? fontSize : this.defaultFontSize);
        }
        CoordinatorLayout coordinatorLayout = this.coordinatorLayout;
        if (coordinatorLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
            coordinatorLayout = null;
        }
        coordinatorLayout.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        CoordinatorLayout coordinatorLayout2 = this.coordinatorLayout;
        if (coordinatorLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
            coordinatorLayout2 = null;
        }
        coordinatorLayout2.layout(0, 0, width, height);
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
        } else {
            appBarLayout = appBarLayout2;
        }
        float dIPFromPixel = PixelUtil.toDIPFromPixel(appBarLayout.getHeight());
        this.cache = new CacheEntry(new CacheKey(fontSize, isTitleEmpty), dIPFromPixel);
        return dIPFromPixel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ ReactApplicationContext requireReactContext$default(ScreenDummyLayoutHelper screenDummyLayoutHelper, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return screenDummyLayoutHelper.requireReactContext(function0);
    }

    private final ReactApplicationContext requireReactContext(Function0<? extends Object> lazyMessage) {
        ReactApplicationContext reactApplicationContext = this.reactContextRef.get();
        if (lazyMessage == null) {
            lazyMessage = new Function0<String>() { // from class: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper.requireReactContext.1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "[RNScreens] Attempt to require missing react context";
                }
            };
        }
        if (reactApplicationContext != null) {
            return reactApplicationContext;
        }
        throw new IllegalArgumentException(lazyMessage.invoke().toString());
    }

    private final Activity requireActivity() {
        Activity currentActivity = requireReactContext$default(this, null, 1, null).getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new IllegalArgumentException("[RNScreens] Attempt to use context detached from activity".toString());
    }

    /* compiled from: ScreenDummyLayoutHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/swmansion/rnscreens/utils/ScreenDummyLayoutHelper$Companion;", "", "()V", "DEFAULT_HEADER_TITLE", "", "FONT_SIZE_UNSET", "", "LIBRARY_NAME", AbstractID3v1Tag.TAG, "weakInstance", "Ljava/lang/ref/WeakReference;", "Lcom/swmansion/rnscreens/utils/ScreenDummyLayoutHelper;", "getInstance", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ScreenDummyLayoutHelper getInstance() {
            return (ScreenDummyLayoutHelper) ScreenDummyLayoutHelper.weakInstance.get();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactApplicationContext reactApplicationContextRequireReactContext = requireReactContext(new Function0<Object>() { // from class: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$onHostResume$reactContext$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "[RNScreens] ReactContext missing in onHostResume! This should not happen.";
            }
        });
        if (maybeInitDummyLayoutWithHeader(reactApplicationContextRequireReactContext)) {
            reactApplicationContextRequireReactContext.removeLifecycleEventListener(this);
        } else {
            Log.w(TAG, "[RNScreens] Failed to initialise dummy layout in onHostResume.");
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        ReactApplicationContext reactApplicationContext = this.reactContextRef.get();
        if (reactApplicationContext != null) {
            reactApplicationContext.removeLifecycleEventListener(this);
        }
    }
}
