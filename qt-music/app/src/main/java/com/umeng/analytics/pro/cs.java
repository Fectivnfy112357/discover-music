package com.umeng.analytics.pro;

/* compiled from: TMessage.java */
/* loaded from: classes3.dex */
public final class cs {
    public final String a;
    public final byte b;
    public final int c;

    public cs() {
        this("", (byte) 0, 0);
    }

    public cs(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + ((int) this.b) + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof cs) {
            return a((cs) obj);
        }
        return false;
    }

    public boolean a(cs csVar) {
        return this.a.equals(csVar.a) && this.b == csVar.b && this.c == csVar.c;
    }
}
