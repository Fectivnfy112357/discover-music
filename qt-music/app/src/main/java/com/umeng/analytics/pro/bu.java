package com.umeng.analytics.pro;

import com.facebook.react.devsupport.StackTraceHelper;

/* compiled from: TApplicationException.java */
/* loaded from: classes3.dex */
public class bu extends cb {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final cz j = new cz("TApplicationException");
    private static final cp k = new cp(StackTraceHelper.MESSAGE_KEY, (byte) 11, 1);
    private static final cp l = new cp("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i;

    public bu() {
        this.i = 0;
    }

    public bu(int i) {
        this.i = i;
    }

    public bu(int i, String str) {
        super(str);
        this.i = i;
    }

    public bu(String str) {
        super(str);
        this.i = 0;
    }

    public int a() {
        return this.i;
    }

    public static bu a(cu cuVar) throws cb {
        cuVar.j();
        String strZ = null;
        int iW = 0;
        while (true) {
            cp cpVarL = cuVar.l();
            if (cpVarL.b != 0) {
                short s = cpVarL.c;
                if (s != 1) {
                    if (s == 2) {
                        if (cpVarL.b == 8) {
                            iW = cuVar.w();
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                } else if (cpVarL.b == 11) {
                    strZ = cuVar.z();
                } else {
                    cx.a(cuVar, cpVarL.b);
                }
                cuVar.m();
            } else {
                cuVar.k();
                return new bu(iW, strZ);
            }
        }
    }

    public void b(cu cuVar) throws cb {
        cuVar.a(j);
        if (getMessage() != null) {
            cuVar.a(k);
            cuVar.a(getMessage());
            cuVar.c();
        }
        cuVar.a(l);
        cuVar.a(this.i);
        cuVar.c();
        cuVar.d();
        cuVar.b();
    }
}
