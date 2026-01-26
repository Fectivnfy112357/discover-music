package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public final class RandomAccessFileOutputStream extends OutputStream {
    private final RandomAccessFile targetFile;

    public RandomAccessFileOutputStream(RandomAccessFile randomAccessFile) {
        this.targetFile = randomAccessFile;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.targetFile.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.targetFile.write(i);
    }
}
