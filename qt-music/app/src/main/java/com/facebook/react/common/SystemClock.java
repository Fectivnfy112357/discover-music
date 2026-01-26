package com.facebook.react.common;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: SystemClock.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/common/SystemClock;", "", "()V", "currentTimeMillis", "", "nanoTime", "uptimeMillis", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystemClock {
    public static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
    }

    @JvmStatic
    public static final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @JvmStatic
    public static final long nanoTime() {
        return System.nanoTime();
    }

    @JvmStatic
    public static final long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
