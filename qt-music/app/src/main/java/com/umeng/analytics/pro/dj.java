package com.umeng.analytics.pro;

/* compiled from: TTransportException.java */
/* loaded from: classes3.dex */
public class dj extends cb {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static final long g = 1;
    protected int f;

    public dj() {
        this.f = 0;
    }

    public dj(int i) {
        this.f = i;
    }

    public dj(int i, String str) {
        super(str);
        this.f = i;
    }

    public dj(String str) {
        super(str);
        this.f = 0;
    }

    public dj(int i, Throwable th) {
        super(th);
        this.f = i;
    }

    public dj(Throwable th) {
        super(th);
        this.f = 0;
    }

    public dj(String str, Throwable th) {
        super(str, th);
        this.f = 0;
    }

    public dj(int i, String str, Throwable th) {
        super(str, th);
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
