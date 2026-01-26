package com.umeng.commonsdk.statistics.proto;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.cp;
import com.umeng.analytics.pro.cu;
import com.umeng.analytics.pro.cv;
import com.umeng.analytics.pro.cx;
import com.umeng.analytics.pro.cz;
import com.umeng.analytics.pro.da;
import com.umeng.analytics.pro.dc;
import com.umeng.analytics.pro.dd;
import com.umeng.analytics.pro.de;
import com.umeng.analytics.pro.df;
import com.umeng.analytics.pro.dg;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdSnapshot.java */
/* loaded from: classes3.dex */
public class b implements bv<b, e>, Serializable, Cloneable {
    public static final Map<e, ch> d;
    private static final long e = -6496538196005191531L;
    private static final cz f = new cz("IdSnapshot");
    private static final cp g = new cp(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, (byte) 11, 1);
    private static final cp h = new cp("ts", (byte) 10, 2);
    private static final cp i = new cp("version", (byte) 8, 3);
    private static final Map<Class<? extends dc>, dd> j;
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    static {
        HashMap map = new HashMap();
        j = map;
        map.put(de.class, new C0045b());
        map.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new ch(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, (byte) 1, new ci((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new ch("ts", (byte) 1, new ci((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new ch("version", (byte) 1, new ci((byte) 8)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(b.class, mapUnmodifiableMap);
    }

    /* compiled from: IdSnapshot.java */
    public enum e implements cc {
        IDENTITY(1, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY),
        TS(2, "ts"),
        VERSION(3, "version");

        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.e;
        }

        @Override // com.umeng.analytics.pro.cc
        public String b() {
            return this.f;
        }
    }

    public b() {
        this.m = (byte) 0;
    }

    public b(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public b(b bVar) {
        this.m = (byte) 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b deepCopy() {
        return new b(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public String b() {
        return this.a;
    }

    public b a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public long e() {
        return this.b;
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.m = bs.b(this.m, 0);
    }

    public boolean g() {
        return bs.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = bs.a(this.m, 0, z);
    }

    public int h() {
        return this.c;
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void i() {
        this.m = bs.b(this.m, 1);
    }

    public boolean j() {
        return bs.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = bs.a(this.m, 1, z);
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        j.get(cuVar.D()).b().b(cuVar, this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        j.get(cuVar.D()).b().a(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(identity:");
        String str = this.a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public void k() throws cb {
        if (this.a == null) {
            throw new cv("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.m = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdSnapshot.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b, reason: collision with other inner class name */
    private static class C0045b implements dd {
        private C0045b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdSnapshot.java */
    private static class a extends de<b> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, b bVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                if (cpVarL.b == 0) {
                    break;
                }
                short s = cpVarL.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s == 3) {
                            if (cpVarL.b == 8) {
                                bVar.c = cuVar.w();
                                bVar.c(true);
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 10) {
                        bVar.b = cuVar.x();
                        bVar.b(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                } else if (cpVarL.b == 11) {
                    bVar.a = cuVar.z();
                    bVar.a(true);
                } else {
                    cx.a(cuVar, cpVarL.b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (!bVar.g()) {
                throw new cv("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            if (!bVar.j()) {
                throw new cv("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            bVar.k();
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, b bVar) throws cb {
            bVar.k();
            cuVar.a(b.f);
            if (bVar.a != null) {
                cuVar.a(b.g);
                cuVar.a(bVar.a);
                cuVar.c();
            }
            cuVar.a(b.h);
            cuVar.a(bVar.b);
            cuVar.c();
            cuVar.a(b.i);
            cuVar.a(bVar.c);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: IdSnapshot.java */
    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdSnapshot.java */
    private static class c extends df<b> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, b bVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(bVar.a);
            daVar.a(bVar.b);
            daVar.a(bVar.c);
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, b bVar) throws cb {
            da daVar = (da) cuVar;
            bVar.a = daVar.z();
            bVar.a(true);
            bVar.b = daVar.x();
            bVar.b(true);
            bVar.c = daVar.w();
            bVar.c(true);
        }
    }
}
