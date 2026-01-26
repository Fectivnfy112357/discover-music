package org.jaudiotagger.utils;

/* loaded from: classes3.dex */
public final class EqualsUtil {
    public static boolean areEqual(char c, char c2) {
        return c == c2;
    }

    public static boolean areEqual(long j, long j2) {
        return j == j2;
    }

    public static boolean areEqual(boolean z, boolean z2) {
        return z == z2;
    }

    public static boolean areEqual(float f, float f2) {
        return Float.floatToIntBits(f) == Float.floatToIntBits(f2);
    }

    public static boolean areEqual(double d, double d2) {
        return Double.doubleToLongBits(d) == Double.doubleToLongBits(d2);
    }

    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
