package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.text.TextUtilsCompat;
import com.facebook.hermes.intl.Constants;
import com.umeng.analytics.pro.f;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: I18nUtil.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0004J \u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J \u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0016\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/modules/i18nmanager/I18nUtil;", "", "()V", "isDevicePreferredLanguageRTL", "", "()Z", "allowRTL", "", f.X, "Landroid/content/Context;", "applicationHasRtlSupport", "doLeftAndRightSwapInRTL", "forceRTL", "isPrefSet", "key", "", "defaultValue", "isRTL", "isRTLAllowed", "isRTLForced", "setPref", "value", "swapLeftAndRightInRTL", "flip", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class I18nUtil {
    private static final String KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES = "RCTI18nUtil_makeRTLFlipLeftAndRightStyles";
    private static final String KEY_FOR_PREFS_ALLOWRTL = "RCTI18nUtil_allowRTL";
    private static final String KEY_FOR_PREFS_FORCERTL = "RCTI18nUtil_forceRTL";
    private static final String SHARED_PREFS_NAME = "com.facebook.react.modules.i18nmanager.I18nUtil";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final I18nUtil instance = new I18nUtil();

    @JvmStatic
    public static final I18nUtil getInstance() {
        return INSTANCE.getInstance();
    }

    private I18nUtil() {
    }

    public final boolean isRTL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return applicationHasRtlSupport(context) && (isRTLForced(context) || (isRTLAllowed(context) && isDevicePreferredLanguageRTL()));
    }

    private final boolean applicationHasRtlSupport(Context context) {
        return (context.getApplicationInfo().flags & 4194304) != 0;
    }

    private final boolean isRTLAllowed(Context context) {
        return isPrefSet(context, KEY_FOR_PREFS_ALLOWRTL, true);
    }

    public final void allowRTL(Context context, boolean allowRTL) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PREFS_ALLOWRTL, allowRTL);
    }

    public final boolean doLeftAndRightSwapInRTL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isPrefSet(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, true);
    }

    public final void swapLeftAndRightInRTL(Context context, boolean flip) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, flip);
    }

    private final boolean isRTLForced(Context context) {
        return isPrefSet(context, KEY_FOR_PREFS_FORCERTL, false) || StringsKt.equals(System.getProperty("FORCE_RTL_FOR_TESTING", Constants.CASEFIRST_FALSE), "true", true);
    }

    public final void forceRTL(Context context, boolean forceRTL) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PREFS_FORCERTL, forceRTL);
    }

    private final boolean isDevicePreferredLanguageRTL() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    private final boolean isPrefSet(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getBoolean(key, defaultValue);
    }

    private final void setPref(Context context, String key, boolean value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFS_NAME, 0).edit();
        editorEdit.putBoolean(key, value);
        editorEdit.apply();
    }

    /* compiled from: I18nUtil.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00020\t8G¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/facebook/react/modules/i18nmanager/I18nUtil$Companion;", "", "()V", "KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES", "", "KEY_FOR_PREFS_ALLOWRTL", "KEY_FOR_PREFS_FORCERTL", "SHARED_PREFS_NAME", "instance", "Lcom/facebook/react/modules/i18nmanager/I18nUtil;", "getInstance", "()Lcom/facebook/react/modules/i18nmanager/I18nUtil;", "DEPRECATED$getInstance", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final I18nUtil getInstance() {
            return I18nUtil.instance;
        }

        @Deprecated(message = "Use .instance instead, this API is only for backward compat", replaceWith = @ReplaceWith(expression = "instance", imports = {}))
        public final I18nUtil DEPRECATED$getInstance() {
            return getInstance();
        }
    }
}
