package org.jaudiotagger.tag.id3;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ID3SyncSafeInteger {
    public static final int INTEGRAL_SIZE = 4;
    public static final int MAX_SAFE_SIZE = 127;

    public static int bufferToValue(byte[] bArr) {
        return ((bArr[0] & 255) << 21) + ((bArr[1] & 255) << 14) + ((bArr[2] & 255) << 7) + (bArr[3] & 255);
    }

    public static int bufferToValue(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr, 0, 4);
        return bufferToValue(bArr);
    }

    protected static boolean isBufferNotSyncSafe(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        for (int i = 0; i < 4; i++) {
            if ((byteBuffer.get(iPosition + i) & 128) > 0) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isBufferEmpty(byte[] bArr) {
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    protected static byte[] valueToBuffer(int i) {
        return new byte[]{(byte) ((266338304 & i) >> 21), (byte) ((2080768 & i) >> 14), (byte) ((i & 16256) >> 7), (byte) (i & 127)};
    }
}
