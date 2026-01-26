package com.facebook.react.bridge;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class ReactNoCrashSoftException extends RuntimeException {
    public ReactNoCrashSoftException(String str) {
        super(str);
    }

    public ReactNoCrashSoftException(Throwable th) {
        super(th);
    }

    public ReactNoCrashSoftException(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
