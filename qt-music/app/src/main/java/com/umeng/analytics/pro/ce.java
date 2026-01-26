package com.umeng.analytics.pro;

import com.umeng.analytics.pro.co;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: TSerializer.java */
/* loaded from: classes3.dex */
public class ce {
    private final ByteArrayOutputStream a;
    private final dg b;
    private cu c;

    public ce() {
        this(new co.a());
    }

    public ce(cw cwVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a = byteArrayOutputStream;
        dg dgVar = new dg(byteArrayOutputStream);
        this.b = dgVar;
        this.c = cwVar.a(dgVar);
    }

    public byte[] a(bv bvVar) throws cb {
        this.a.reset();
        bvVar.write(this.c);
        return this.a.toByteArray();
    }

    public String a(bv bvVar, String str) throws cb {
        try {
            return new String(a(bvVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new cb("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(bv bvVar) throws cb {
        return new String(a(bvVar));
    }
}
