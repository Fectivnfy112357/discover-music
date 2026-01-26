package com.umeng.analytics.pro;

/* compiled from: TMemoryInputTransport.java */
/* loaded from: classes3.dex */
public final class dh extends di {
    private byte[] a;
    private int b;
    private int c;

    @Override // com.umeng.analytics.pro.di
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.di
    public void b() throws dj {
    }

    @Override // com.umeng.analytics.pro.di
    public void c() {
    }

    public dh() {
    }

    public dh(byte[] bArr) {
        a(bArr);
    }

    public dh(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.a = null;
    }

    @Override // com.umeng.analytics.pro.di
    public int a(byte[] bArr, int i, int i2) throws dj {
        int iH = h();
        if (i2 > iH) {
            i2 = iH;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    @Override // com.umeng.analytics.pro.di
    public void b(byte[] bArr, int i, int i2) throws dj {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.analytics.pro.di
    public byte[] f() {
        return this.a;
    }

    @Override // com.umeng.analytics.pro.di
    public int g() {
        return this.b;
    }

    @Override // com.umeng.analytics.pro.di
    public int h() {
        return this.c - this.b;
    }

    @Override // com.umeng.analytics.pro.di
    public void a(int i) {
        this.b += i;
    }
}
