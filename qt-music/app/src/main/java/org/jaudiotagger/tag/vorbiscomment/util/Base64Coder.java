package org.jaudiotagger.tag.vorbiscomment.util;

import java.nio.charset.StandardCharsets;

/* loaded from: classes3.dex */
public class Base64Coder {
    private static final char[] map1 = new char[64];
    private static final byte[] map2;

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = map1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        map2 = new byte[128];
        int i2 = 0;
        while (true) {
            byte[] bArr = map2;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            map2[map1[i3]] = (byte) i3;
        }
    }

    public static String encode(String str) {
        return new String(encode(str.getBytes(StandardCharsets.ISO_8859_1)));
    }

    public static char[] encode(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = ((length * 4) + 2) / 3;
        char[] cArr = new char[((length + 2) / 3) * 4];
        int i6 = 0;
        int i7 = 0;
        while (i6 < length) {
            int i8 = i6 + 1;
            byte b = bArr[i6];
            int i9 = b & 255;
            if (i8 < length) {
                i = i6 + 2;
                i2 = bArr[i8] & 255;
            } else {
                i = i8;
                i2 = 0;
            }
            if (i < length) {
                i3 = i + 1;
                i4 = bArr[i] & 255;
            } else {
                i3 = i;
                i4 = 0;
            }
            int i10 = ((b & 3) << 4) | (i2 >>> 4);
            int i11 = ((i2 & 15) << 2) | (i4 >>> 6);
            int i12 = i4 & 63;
            char[] cArr2 = map1;
            cArr[i7] = cArr2[i9 >>> 2];
            int i13 = i7 + 2;
            cArr[i7 + 1] = cArr2[i10];
            char c = '=';
            cArr[i13] = i13 < i5 ? cArr2[i11] : '=';
            int i14 = i7 + 3;
            if (i14 < i5) {
                c = cArr2[i12];
            }
            cArr[i14] = c;
            i7 += 4;
            i6 = i3;
        }
        return cArr;
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(char[] cArr) {
        char c;
        int length = cArr.length;
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (length > 0 && cArr[length - 1] == '=') {
            length--;
        }
        int i = (length * 3) / 4;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char c2 = cArr[i2];
            int i4 = i2 + 2;
            char c3 = cArr[i2 + 1];
            if (c2 == '\r' && c3 == '\n') {
                i2 = i4;
            } else {
                char c4 = 'A';
                if (i4 < length) {
                    i2 += 3;
                    c = cArr[i4];
                } else {
                    i2 = i4;
                    c = 'A';
                }
                if (i2 < length) {
                    c4 = cArr[i2];
                    i2++;
                }
                if (c2 > 127 || c3 > 127 || c > 127 || c4 > 127) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                byte[] bArr2 = map2;
                byte b = bArr2[c2];
                byte b2 = bArr2[c3];
                byte b3 = bArr2[c];
                byte b4 = bArr2[c4];
                if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                int i5 = (b << 2) | (b2 >>> 4);
                int i6 = ((b2 & 15) << 4) | (b3 >>> 2);
                int i7 = ((b3 & 3) << 6) | b4;
                int i8 = i3 + 1;
                bArr[i3] = (byte) i5;
                if (i8 < i) {
                    bArr[i8] = (byte) i6;
                    i8 = i3 + 2;
                }
                if (i8 < i) {
                    i3 = i8 + 1;
                    bArr[i8] = (byte) i7;
                } else {
                    i3 = i8;
                }
            }
        }
        return bArr;
    }
}
