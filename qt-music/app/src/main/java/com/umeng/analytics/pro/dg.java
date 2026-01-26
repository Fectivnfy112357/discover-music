package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport.java */
/* loaded from: classes3.dex */
public class dg extends di {
    protected InputStream a;
    protected OutputStream b;

    @Override // com.umeng.analytics.pro.di
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.di
    public void b() throws dj {
    }

    protected dg() {
        this.a = null;
        this.b = null;
    }

    public dg(InputStream inputStream) {
        this.b = null;
        this.a = inputStream;
    }

    public dg(OutputStream outputStream) {
        this.a = null;
        this.b = outputStream;
    }

    public dg(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    @Override // com.umeng.analytics.pro.di
    public void c() throws IOException {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // com.umeng.analytics.pro.di
    public int a(byte[] bArr, int i, int i2) throws IOException, dj {
        InputStream inputStream = this.a;
        if (inputStream == null) {
            throw new dj(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = inputStream.read(bArr, i, i2);
            if (i3 >= 0) {
                return i3;
            }
            throw new dj(4);
        } catch (IOException e) {
            throw new dj(0, e);
        }
    }

    @Override // com.umeng.analytics.pro.di
    public void b(byte[] bArr, int i, int i2) throws IOException, dj {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dj(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e) {
            throw new dj(0, e);
        }
    }

    @Override // com.umeng.analytics.pro.di
    public void d() throws IOException, dj {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dj(1, "Cannot flush null outputStream");
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            throw new dj(0, e);
        }
    }
}
