package com.umeng.analytics.pro;

import java.io.ByteArrayOutputStream;

/* compiled from: TByteArrayOutputStream.java */
/* loaded from: classes3.dex */
public class bx extends ByteArrayOutputStream {
    public bx(int i) {
        super(i);
    }

    public bx() {
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
