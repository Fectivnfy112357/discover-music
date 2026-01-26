package com.facebook.react.uimanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DisplayMetricsHolder.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0007J\b\u0010\u0011\u001a\u00020\u0006H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/uimanager/DisplayMetricsHolder;", "", "()V", "INITIALIZATION_MISSING_MESSAGE", "", "screenDisplayMetrics", "Landroid/util/DisplayMetrics;", "getScreenDisplayMetrics$annotations", "windowDisplayMetrics", "getWindowDisplayMetrics$annotations", "getDisplayMetricsWritableMap", "Lcom/facebook/react/bridge/WritableMap;", "fontScale", "", "getPhysicalPixelsWritableMap", "displayMetrics", "getScreenDisplayMetrics", "getWindowDisplayMetrics", "initDisplayMetrics", "", f.X, "Landroid/content/Context;", "initDisplayMetricsIfNotInitialized", "setScreenDisplayMetrics", "setWindowDisplayMetrics", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DisplayMetricsHolder {
    private static final String INITIALIZATION_MISSING_MESSAGE = "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics";
    public static final DisplayMetricsHolder INSTANCE = new DisplayMetricsHolder();
    private static DisplayMetrics screenDisplayMetrics;
    private static DisplayMetrics windowDisplayMetrics;

    @JvmStatic
    private static /* synthetic */ void getScreenDisplayMetrics$annotations() {
    }

    @JvmStatic
    private static /* synthetic */ void getWindowDisplayMetrics$annotations() {
    }

    private DisplayMetricsHolder() {
    }

    @JvmStatic
    public static final DisplayMetrics getWindowDisplayMetrics() {
        DisplayMetrics displayMetrics = windowDisplayMetrics;
        if (displayMetrics == null) {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE.toString());
        }
        Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
        return displayMetrics;
    }

    @JvmStatic
    public static final void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
        windowDisplayMetrics = displayMetrics;
    }

    @JvmStatic
    public static final DisplayMetrics getScreenDisplayMetrics() {
        DisplayMetrics displayMetrics = screenDisplayMetrics;
        if (displayMetrics == null) {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE.toString());
        }
        Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
        return displayMetrics;
    }

    @JvmStatic
    public static final void setScreenDisplayMetrics(DisplayMetrics displayMetrics) {
        screenDisplayMetrics = displayMetrics;
    }

    @JvmStatic
    public static final void initDisplayMetricsIfNotInitialized(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (screenDisplayMetrics != null) {
            return;
        }
        initDisplayMetrics(context);
    }

    @JvmStatic
    public static final void initDisplayMetrics(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        windowDisplayMetrics = displayMetrics;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics2);
        screenDisplayMetrics = displayMetrics2;
    }

    @JvmStatic
    public static final WritableMap getDisplayMetricsWritableMap(double fontScale) {
        DisplayMetricsHolder displayMetricsHolder = INSTANCE;
        if (windowDisplayMetrics == null) {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE.toString());
        }
        if (screenDisplayMetrics == null) {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE.toString());
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        DisplayMetrics displayMetrics = windowDisplayMetrics;
        Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
        writableNativeMap.putMap("windowPhysicalPixels", displayMetricsHolder.getPhysicalPixelsWritableMap(displayMetrics, fontScale));
        DisplayMetrics displayMetrics2 = screenDisplayMetrics;
        Intrinsics.checkNotNull(displayMetrics2, "null cannot be cast to non-null type android.util.DisplayMetrics");
        writableNativeMap.putMap("screenPhysicalPixels", displayMetricsHolder.getPhysicalPixelsWritableMap(displayMetrics2, fontScale));
        return writableNativeMap;
    }

    private final WritableMap getPhysicalPixelsWritableMap(DisplayMetrics displayMetrics, double fontScale) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble("scale", displayMetrics.density);
        writableNativeMap.putDouble("fontScale", fontScale);
        writableNativeMap.putDouble("densityDpi", displayMetrics.densityDpi);
        return writableNativeMap;
    }
}
