package com.facebook.imageformat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageFormatCheckerUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007J(\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\fH\u0007J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0007¨\u0006\u0011"}, d2 = {"Lcom/facebook/imageformat/ImageFormatCheckerUtils;", "", "()V", "asciiBytes", "", "value", "", "hasPatternAt", "", "byteArray", "pattern", "offset", "", "indexOfPattern", "byteArrayLen", "patternLen", "startsWithPattern", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageFormatCheckerUtils {
    public static final ImageFormatCheckerUtils INSTANCE = new ImageFormatCheckerUtils();

    private ImageFormatCheckerUtils() {
    }

    @JvmStatic
    public static final byte[] asciiBytes(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            Charset charsetForName = Charset.forName("ASCII");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            byte[] bytes = value.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return bytes;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    @JvmStatic
    public static final boolean startsWithPattern(byte[] byteArray, byte[] pattern) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return hasPatternAt(byteArray, pattern, 0);
    }

    @JvmStatic
    public static final boolean hasPatternAt(byte[] byteArray, byte[] pattern, int offset) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        Iterable indices = ArraysKt.getIndices(pattern);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                if (byteArray[offset + iNextInt] != pattern[iNextInt]) {
                    return false;
                }
            }
        }
        return true;
    }

    @JvmStatic
    public static final int indexOfPattern(byte[] byteArray, int byteArrayLen, byte[] pattern, int patternLen) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        if (patternLen > byteArrayLen) {
            return -1;
        }
        int i = 0;
        byte b = pattern[0];
        int i2 = byteArrayLen - patternLen;
        while (i <= i2) {
            if (byteArray[i] != b) {
                do {
                    i++;
                    if (i > i2) {
                        break;
                    }
                } while (byteArray[i] != b);
            }
            if (i <= i2) {
                int i3 = i + 1;
                int i4 = (i3 + patternLen) - 1;
                for (int i5 = 1; i3 < i4 && byteArray[i3] == pattern[i5]; i5++) {
                    i3++;
                }
                if (i3 == i4) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
}
