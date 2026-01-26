package com.facebook.fresco.ui.common;

import android.net.Uri;
import com.facebook.common.internal.Fn;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class MultiUriHelper {
    @Nullable
    public static <T> Uri getMainUri(@Nullable T t, @Nullable T t2, @Nullable T[] tArr, Fn<T, Uri> fn) {
        T t3;
        Uri uriApply;
        Uri uriApply2;
        if (t != null && (uriApply2 = fn.apply(t)) != null) {
            return uriApply2;
        }
        if (tArr != null && tArr.length > 0 && (t3 = tArr[0]) != null && (uriApply = fn.apply(t3)) != null) {
            return uriApply;
        }
        if (t2 != null) {
            return fn.apply(t2);
        }
        return null;
    }
}
