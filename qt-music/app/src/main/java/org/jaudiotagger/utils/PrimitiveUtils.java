package org.jaudiotagger.utils;

/* loaded from: classes3.dex */
public class PrimitiveUtils {
    public static int safeLongToInt(long j) {
        if (j < -2147483648L || j > 2147483647L) {
            throw new IllegalArgumentException(j + " cannot be cast to int without changing its value.");
        }
        return (int) j;
    }
}
