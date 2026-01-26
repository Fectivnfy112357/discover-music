package org.jaudiotagger.audio.aiff;

/* loaded from: classes3.dex */
public class ExtDouble {
    byte[] _rawData;

    public ExtDouble(byte[] bArr) {
        this._rawData = bArr;
    }

    public double toDouble() {
        byte[] bArr = this._rawData;
        byte b = bArr[0];
        int i = b >> 7;
        int i2 = ((bArr[1] | (b << 8)) & 32767) - 16445;
        long j = 0;
        int i3 = 55;
        for (int i4 = 2; i4 < 9; i4++) {
            j |= (this._rawData[i4] & 255) << i3;
            i3 -= 8;
        }
        double dPow = Math.pow(2.0d, i2) * (j | (this._rawData[9] >>> 1));
        return i != 0 ? -dPow : dPow;
    }
}
