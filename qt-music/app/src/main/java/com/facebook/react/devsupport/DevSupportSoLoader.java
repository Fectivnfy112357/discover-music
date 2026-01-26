package com.facebook.react.devsupport;

import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: DevSupportSoLoader.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/devsupport/DevSupportSoLoader;", "", "()V", "didInit", "", "staticInit", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DevSupportSoLoader {
    public static final DevSupportSoLoader INSTANCE = new DevSupportSoLoader();
    private static volatile boolean didInit;

    private DevSupportSoLoader() {
    }

    @JvmStatic
    public static final synchronized void staticInit() {
        if (didInit) {
            return;
        }
        SoLoader.loadLibrary("react_devsupportjni");
        didInit = true;
    }
}
