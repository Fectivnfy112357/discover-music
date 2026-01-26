package com.facebook.react.flipper;

import android.content.Context;
import com.facebook.react.ReactInstanceManager;
import com.umeng.analytics.pro.f;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeFlipper.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "ReactNative/Flipper integration is deprecated. Please remove the call to initializeFlipper from your MainApplication.java", replaceWith = @ReplaceWith(expression = "", imports = {}))
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/flipper/ReactNativeFlipper;", "", "()V", "initializeFlipper", "", f.X, "Landroid/content/Context;", "reactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFlipper {
    public static final ReactNativeFlipper INSTANCE = new ReactNativeFlipper();

    @Deprecated(level = DeprecationLevel.ERROR, message = "ReactNative/Flipper integration is deprecated. Please remove the call to initializeFlipper from your MainApplication.java", replaceWith = @ReplaceWith(expression = "", imports = {}))
    @JvmStatic
    public static final void initializeFlipper(Context context, ReactInstanceManager reactInstanceManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
    }

    private ReactNativeFlipper() {
    }
}
