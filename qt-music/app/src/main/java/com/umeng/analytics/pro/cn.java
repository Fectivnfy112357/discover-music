package com.umeng.analytics.pro;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol.java */
/* loaded from: classes3.dex */
public class cn extends cu {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final cz h = new cz();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    @Override // com.umeng.analytics.pro.cu
    public void a() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cz czVar) {
    }

    @Override // com.umeng.analytics.pro.cu
    public void b() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void c() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void e() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void f() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void g() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void i() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void k() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void m() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void o() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void q() {
    }

    @Override // com.umeng.analytics.pro.cu
    public void s() {
    }

    /* compiled from: TBinaryProtocol.java */
    public static class a implements cw {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        @Override // com.umeng.analytics.pro.cw
        public cu a(di diVar) {
            cn cnVar = new cn(diVar, this.a, this.b);
            int i = this.c;
            if (i != 0) {
                cnVar.c(i);
            }
            return cnVar;
        }
    }

    public cn(di diVar) {
        this(diVar, false, true);
    }

    public cn(di diVar, boolean z, boolean z2) {
        super(diVar);
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cs csVar) throws cb, UnsupportedEncodingException {
        if (this.d) {
            a(b | csVar.b);
            a(csVar.a);
            a(csVar.c);
        } else {
            a(csVar.a);
            a(csVar.b);
            a(csVar.c);
        }
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cp cpVar) throws cb {
        a(cpVar.b);
        a(cpVar.c);
    }

    @Override // com.umeng.analytics.pro.cu
    public void d() throws cb {
        a((byte) 0);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cr crVar) throws cb {
        a(crVar.a);
        a(crVar.b);
        a(crVar.c);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cq cqVar) throws cb {
        a(cqVar.a);
        a(cqVar.b);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(cy cyVar) throws cb {
        a(cyVar.a);
        a(cyVar.b);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(boolean z) throws cb {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(byte b2) throws cb {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(short s) throws cb {
        byte[] bArr = this.j;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(int i) throws cb {
        byte[] bArr = this.k;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(long j) throws cb {
        byte[] bArr = this.l;
        bArr[0] = (byte) ((j >> 56) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[7] = (byte) (j & 255);
        this.g.b(this.l, 0, 8);
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(double d) throws cb {
        a(Double.doubleToLongBits(d));
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(String str) throws cb, UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new cb("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.cu
    public void a(ByteBuffer byteBuffer) throws cb {
        int iLimit = byteBuffer.limit() - byteBuffer.position();
        a(iLimit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), iLimit);
    }

    @Override // com.umeng.analytics.pro.cu
    public cs h() throws cb {
        int iW = w();
        if (iW < 0) {
            if (((-65536) & iW) != b) {
                throw new cv(4, "Bad version in readMessageBegin");
            }
            return new cs(z(), (byte) (iW & 255), w());
        }
        if (this.c) {
            throw new cv(4, "Missing version in readMessageBegin, old client?");
        }
        return new cs(b(iW), u(), w());
    }

    @Override // com.umeng.analytics.pro.cu
    public cz j() {
        return h;
    }

    @Override // com.umeng.analytics.pro.cu
    public cp l() throws cb {
        byte bU = u();
        return new cp("", bU, bU == 0 ? (short) 0 : v());
    }

    @Override // com.umeng.analytics.pro.cu
    public cr n() throws cb {
        return new cr(u(), u(), w());
    }

    @Override // com.umeng.analytics.pro.cu
    public cq p() throws cb {
        return new cq(u(), w());
    }

    @Override // com.umeng.analytics.pro.cu
    public cy r() throws cb {
        return new cy(u(), w());
    }

    @Override // com.umeng.analytics.pro.cu
    public boolean t() throws cb {
        return u() == 1;
    }

    @Override // com.umeng.analytics.pro.cu
    public byte u() throws cb {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    @Override // com.umeng.analytics.pro.cu
    public short v() throws cb {
        int iG;
        byte[] bArrF = this.n;
        if (this.g.h() >= 2) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
            iG = 0;
        }
        return (short) ((bArrF[iG + 1] & 255) | ((bArrF[iG] & 255) << 8));
    }

    @Override // com.umeng.analytics.pro.cu
    public int w() throws cb {
        int iG;
        byte[] bArrF = this.o;
        if (this.g.h() >= 4) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
            iG = 0;
        }
        return (bArrF[iG + 3] & 255) | ((bArrF[iG] & 255) << 24) | ((bArrF[iG + 1] & 255) << 16) | ((bArrF[iG + 2] & 255) << 8);
    }

    @Override // com.umeng.analytics.pro.cu
    public long x() throws cb {
        int iG;
        byte[] bArrF = this.p;
        if (this.g.h() >= 8) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
            iG = 0;
        }
        return (bArrF[iG + 7] & 255) | ((bArrF[iG] & 255) << 56) | ((bArrF[iG + 1] & 255) << 48) | ((bArrF[iG + 2] & 255) << 40) | ((bArrF[iG + 3] & 255) << 32) | ((bArrF[iG + 4] & 255) << 24) | ((bArrF[iG + 5] & 255) << 16) | ((bArrF[iG + 6] & 255) << 8);
    }

    @Override // com.umeng.analytics.pro.cu
    public double y() throws cb {
        return Double.longBitsToDouble(x());
    }

    @Override // com.umeng.analytics.pro.cu
    public String z() throws cb {
        int iW = w();
        if (this.g.h() >= iW) {
            try {
                String str = new String(this.g.f(), this.g.g(), iW, "UTF-8");
                this.g.a(iW);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new cb("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return b(iW);
    }

    public String b(int i) throws cb {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new cb("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.cu
    public ByteBuffer A() throws cb {
        int iW = w();
        d(iW);
        if (this.g.h() >= iW) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.g.f(), this.g.g(), iW);
            this.g.a(iW);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iW];
        this.g.d(bArr, 0, iW);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws cb {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    protected void d(int i) throws cb {
        if (i < 0) {
            throw new cv("Negative length: " + i);
        }
        if (this.f) {
            int i2 = this.e - i;
            this.e = i2;
            if (i2 < 0) {
                throw new cv("Message length exceeded: " + i);
            }
        }
    }
}
