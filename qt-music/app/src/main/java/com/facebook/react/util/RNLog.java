package com.facebook.react.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNLog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J$\u0010\u0014\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u0016\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/util/RNLog;", "", "()V", "ADVICE", "", "ERROR", "LOG", "MINIMUM_LEVEL_FOR_UI", "TRACE", "WARN", "a", "", StackTraceHelper.MESSAGE_KEY, "", "e", f.X, "Lcom/facebook/react/bridge/ReactContext;", CmcdData.Factory.STREAM_TYPE_LIVE, "levelToString", "level", "logInternal", bm.aM, "w", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RNLog {
    public static final int ADVICE = 4;
    public static final int ERROR = 6;
    public static final RNLog INSTANCE = new RNLog();
    public static final int LOG = 2;
    public static final int MINIMUM_LEVEL_FOR_UI = 5;
    public static final int TRACE = 3;
    public static final int WARN = 5;

    private RNLog() {
    }

    @JvmStatic
    public static final void l(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        FLog.i(ReactConstants.TAG, message);
    }

    @JvmStatic
    public static final void t(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        FLog.i(ReactConstants.TAG, message);
    }

    @JvmStatic
    public static final void a(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        FLog.w(ReactConstants.TAG, "(ADVICE)" + message);
    }

    @JvmStatic
    public static final void w(ReactContext context, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        INSTANCE.logInternal(context, message, 5);
        FLog.w(ReactConstants.TAG, message);
    }

    @JvmStatic
    public static final void e(ReactContext context, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        INSTANCE.logInternal(context, message, 6);
        FLog.e(ReactConstants.TAG, message);
    }

    @JvmStatic
    public static final void e(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        FLog.e(ReactConstants.TAG, message);
    }

    private final void logInternal(ReactContext context, String message, int level) {
        if (level < 5 || context == null || !context.hasActiveReactInstance() || message == null) {
            return;
        }
        ((RCTLog) context.getJSModule(RCTLog.class)).logIfNoNativeHook(levelToString(level), message);
    }

    private final String levelToString(int level) {
        if (level == 2 || level == 3) {
            return "log";
        }
        if (level == 4 || level == 5) {
            return "warn";
        }
        if (level == 6) {
            return "error";
        }
        return "none";
    }
}
