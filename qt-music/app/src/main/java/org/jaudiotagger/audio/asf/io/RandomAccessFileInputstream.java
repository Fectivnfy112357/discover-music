package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public final class RandomAccessFileInputstream extends InputStream {
    private final RandomAccessFile source;

    public RandomAccessFileInputstream(RandomAccessFile randomAccessFile) {
        if (randomAccessFile == null) {
            throw new IllegalArgumentException("null");
        }
        this.source = randomAccessFile;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.source.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("invalid negative value");
        }
        while (j > 2147483647L) {
            this.source.skipBytes(Integer.MAX_VALUE);
            j -= 2147483647L;
        }
        return this.source.skipBytes((int) j);
    }
}
