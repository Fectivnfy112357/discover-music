package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.audio.wav.WavRIFFHeader;

/* compiled from: WebpUtil.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001e\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\f\u0010\u0019\u001a\u00020\r*\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/imageutils/WebpUtil;", "", "()V", "VP8L_HEADER", "", "VP8X_HEADER", "VP8_HEADER", "compare", "", "what", "", "with", "get2BytesAsInt", "", "stream", "Ljava/io/InputStream;", "getHeader", "header", "getInt", "getSize", "Lkotlin/Pair;", "getVP8Dimension", "getVP8LDimension", "getVP8XDimension", "read3Bytes", "getNextByteAsInt", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WebpUtil {
    public static final WebpUtil INSTANCE = new WebpUtil();
    private static final String VP8L_HEADER = "VP8L";
    private static final String VP8X_HEADER = "VP8X";
    private static final String VP8_HEADER = "VP8 ";

    private WebpUtil() {
    }

    @JvmStatic
    public static final Pair<Integer, Integer> getSize(InputStream stream) {
        WebpUtil webpUtil;
        Intrinsics.checkNotNullParameter(stream, "stream");
        byte[] bArr = new byte[4];
        try {
            try {
                try {
                    stream.read(bArr);
                    webpUtil = INSTANCE;
                } catch (IOException e) {
                    e.printStackTrace();
                    stream.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (!webpUtil.compare(bArr, WavRIFFHeader.RIFF_SIGNATURE)) {
                return null;
            }
            webpUtil.getInt(stream);
            stream.read(bArr);
            if (!webpUtil.compare(bArr, "WEBP")) {
                try {
                    stream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
            stream.read(bArr);
            String header = webpUtil.getHeader(bArr);
            int iHashCode = header.hashCode();
            if (iHashCode != 2640674) {
                if (iHashCode != 2640718) {
                    if (iHashCode == 2640730 && header.equals(VP8X_HEADER)) {
                        Pair<Integer, Integer> vP8XDimension = webpUtil.getVP8XDimension(stream);
                        try {
                            stream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        return vP8XDimension;
                    }
                } else if (header.equals(VP8L_HEADER)) {
                    Pair<Integer, Integer> vP8LDimension = webpUtil.getVP8LDimension(stream);
                    try {
                        stream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    return vP8LDimension;
                }
            } else if (header.equals(VP8_HEADER)) {
                Pair<Integer, Integer> vP8Dimension = webpUtil.getVP8Dimension(stream);
                try {
                    stream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                return vP8Dimension;
            }
            stream.close();
            return null;
        } finally {
            try {
                stream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
    }

    private final Pair<Integer, Integer> getVP8Dimension(InputStream stream) throws IOException {
        stream.skip(7L);
        int nextByteAsInt = getNextByteAsInt(stream);
        int nextByteAsInt2 = getNextByteAsInt(stream);
        int nextByteAsInt3 = getNextByteAsInt(stream);
        if (nextByteAsInt == 157 && nextByteAsInt2 == 1 && nextByteAsInt3 == 42) {
            return new Pair<>(Integer.valueOf(get2BytesAsInt(stream)), Integer.valueOf(get2BytesAsInt(stream)));
        }
        return null;
    }

    private final Pair<Integer, Integer> getVP8LDimension(InputStream stream) throws IOException {
        getInt(stream);
        if (getNextByteAsInt(stream) != 47) {
            return null;
        }
        int i = stream.read() & 255;
        int i2 = stream.read();
        return new Pair<>(Integer.valueOf((i | ((i2 & 63) << 8)) + 1), Integer.valueOf((((stream.read() & 15) << 10) | ((stream.read() & 255) << 2) | ((i2 & 192) >> 6)) + 1));
    }

    private final Pair<Integer, Integer> getVP8XDimension(InputStream stream) throws IOException {
        stream.skip(8L);
        return new Pair<>(Integer.valueOf(read3Bytes(stream) + 1), Integer.valueOf(read3Bytes(stream) + 1));
    }

    private final boolean compare(byte[] what, String with) {
        if (what.length != with.length()) {
            return false;
        }
        Iterable indices = ArraysKt.getIndices(what);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                if (((byte) with.charAt(iNextInt)) != what[iNextInt]) {
                    return false;
                }
            }
        }
        return true;
    }

    private final String getHeader(byte[] header) {
        StringBuilder sb = new StringBuilder();
        for (byte b : header) {
            sb.append((char) (UShort.m1283constructorimpl(b) & UShort.MAX_VALUE));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "str.toString()");
        return string;
    }

    private final int getInt(InputStream stream) throws IOException {
        int nextByteAsInt = getNextByteAsInt(stream);
        int nextByteAsInt2 = getNextByteAsInt(stream);
        return (getNextByteAsInt(stream) << 24) | (getNextByteAsInt(stream) << 16) | (nextByteAsInt2 << 8) | nextByteAsInt;
    }

    @JvmStatic
    public static final int get2BytesAsInt(InputStream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        WebpUtil webpUtil = INSTANCE;
        return (webpUtil.getNextByteAsInt(stream) << 8) | webpUtil.getNextByteAsInt(stream);
    }

    private final int read3Bytes(InputStream stream) throws IOException {
        return (getNextByteAsInt(stream) << 16) | (getNextByteAsInt(stream) << 8) | getNextByteAsInt(stream);
    }

    private final int getNextByteAsInt(InputStream inputStream) throws IOException {
        return inputStream.read() & 255;
    }
}
