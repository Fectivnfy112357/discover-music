package com.google.android.exoplayer2.util;

import android.util.Pair;
import java.lang.Throwable;

@Deprecated
/* loaded from: classes2.dex */
public interface ErrorMessageProvider<T extends Throwable> {
    Pair<Integer, String> getErrorMessage(T t);
}
