package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreamProcessor.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/facebook/imageutils/StreamProcessor;", "", "()V", "readPackedInt", "", "stream", "Ljava/io/InputStream;", "numBytes", "isLittleEndian", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StreamProcessor {
    public static final StreamProcessor INSTANCE = new StreamProcessor();

    private StreamProcessor() {
    }

    @JvmStatic
    public static final int readPackedInt(InputStream stream, int numBytes, boolean isLittleEndian) throws IOException {
        int i;
        Intrinsics.checkNotNullParameter(stream, "stream");
        int i2 = 0;
        for (int i3 = 0; i3 < numBytes; i3++) {
            int i4 = stream.read();
            if (i4 == -1) {
                throw new IOException("no more bytes");
            }
            if (isLittleEndian) {
                i = (i4 & 255) << (i3 * 8);
            } else {
                i2 <<= 8;
                i = i4 & 255;
            }
            i2 |= i;
        }
        return i2;
    }
}
