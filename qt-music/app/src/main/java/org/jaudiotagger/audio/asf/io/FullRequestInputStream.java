package org.jaudiotagger.audio.asf.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class FullRequestInputStream extends FilterInputStream {
    public FullRequestInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = super.read(bArr, i + i3, i2 - i3);
            if (i4 >= 0) {
                i3 += i4;
            }
            if (i4 == -1) {
                throw new IOException((i2 - i3) + " more bytes expected.");
            }
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        int i = 0;
        long j2 = 0;
        while (j2 < j) {
            long jSkip = super.skip(j - j2);
            if (jSkip == 0 && (i = i + 1) == 2) {
                break;
            }
            j2 += jSkip;
        }
        return j2;
    }
}
