package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class CountingOutputstream extends OutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long count = 0;
    private final OutputStream wrapped;

    public CountingOutputstream(OutputStream outputStream) {
        this.wrapped = outputStream;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.wrapped.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.wrapped.flush();
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.wrapped.write(bArr);
        this.count += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.wrapped.write(bArr, i, i2);
        this.count += i2;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.wrapped.write(i);
        this.count++;
    }
}
