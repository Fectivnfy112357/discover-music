package com.umeng.analytics.pro;

/* compiled from: TField.java */
/* loaded from: classes3.dex */
public class cp {
    public final String a;
    public final byte b;
    public final short c;

    public cp() {
        this("", (byte) 0, (short) 0);
    }

    public cp(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + ">";
    }

    public boolean a(cp cpVar) {
        return this.b == cpVar.b && this.c == cpVar.c;
    }
}
