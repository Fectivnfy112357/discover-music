package com.umeng.analytics.pro;

/* compiled from: TProtocolException.java */
/* loaded from: classes3.dex */
public class cv extends cb {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static final long h = 1;
    protected int g;

    public cv() {
        this.g = 0;
    }

    public cv(int i) {
        this.g = i;
    }

    public cv(int i, String str) {
        super(str);
        this.g = i;
    }

    public cv(String str) {
        super(str);
        this.g = 0;
    }

    public cv(int i, Throwable th) {
        super(th);
        this.g = i;
    }

    public cv(Throwable th) {
        super(th);
        this.g = 0;
    }

    public cv(String str, Throwable th) {
        super(str, th);
        this.g = 0;
    }

    public cv(int i, String str, Throwable th) {
        super(str, th);
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
