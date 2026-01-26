package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.ck;
import com.umeng.analytics.pro.cm;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.cp;
import com.umeng.analytics.pro.cr;
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

/* compiled from: Imprint.java */
/* loaded from: classes3.dex */
public class d implements bv<d, e>, Serializable, Cloneable {
    public static final Map<e, ch> d;
    private static final long e = 2846460275012375038L;
    private static final cz f = new cz("Imprint");
    private static final cp g = new cp("property", (byte) 13, 1);
    private static final cp h = new cp("version", (byte) 8, 2);
    private static final cp i = new cp("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dc>, dd> j;
    private static final int k = 0;
    public Map<String, com.umeng.commonsdk.statistics.proto.e> a;
    public int b;
    public String c;
    private byte l;

    static {
        HashMap map = new HashMap();
        j = map;
        map.put(de.class, new b());
        map.put(df.class, new C0047d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PROPERTY, (e) new ch("property", (byte) 1, new ck((byte) 13, new ci((byte) 11), new cm((byte) 12, com.umeng.commonsdk.statistics.proto.e.class))));
        enumMap.put((EnumMap) e.VERSION, (e) new ch("version", (byte) 1, new ci((byte) 8)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new ch("checksum", (byte) 1, new ci((byte) 11)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(d.class, mapUnmodifiableMap);
    }

    /* compiled from: Imprint.java */
    public enum e implements cc {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");

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
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
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

    public d() {
        this.l = (byte) 0;
    }

    public d(Map<String, com.umeng.commonsdk.statistics.proto.e> map, int i2, String str) {
        this();
        this.a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    public d(d dVar) {
        this.l = (byte) 0;
        this.l = dVar.l;
        if (dVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.e(entry.getValue()));
            }
            this.a = map;
        }
        this.b = dVar.b;
        if (dVar.k()) {
            this.c = dVar.c;
        }
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d deepCopy() {
        return new d(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int b() {
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.e eVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, eVar);
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.e> c() {
        return this.a;
    }

    public d a(Map<String, com.umeng.commonsdk.statistics.proto.e> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public int f() {
        return this.b;
    }

    public d a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public void g() {
        this.l = bs.b(this.l, 0);
    }

    public boolean h() {
        return bs.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = bs.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
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
        StringBuilder sb = new StringBuilder("Imprint(property:");
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.c;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    public void l() throws cb {
        if (this.a == null) {
            throw new cv("Required field 'property' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new cv("Required field 'checksum' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Imprint.java */
    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: Imprint.java */
    private static class a extends de<d> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, d dVar) throws cb {
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
                            if (cpVarL.b == 11) {
                                dVar.c = cuVar.z();
                                dVar.c(true);
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 8) {
                        dVar.b = cuVar.w();
                        dVar.b(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                } else if (cpVarL.b == 13) {
                    cr crVarN = cuVar.n();
                    dVar.a = new HashMap(crVarN.c * 2);
                    for (int i = 0; i < crVarN.c; i++) {
                        String strZ = cuVar.z();
                        com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                        eVar.read(cuVar);
                        dVar.a.put(strZ, eVar);
                    }
                    cuVar.o();
                    dVar.a(true);
                } else {
                    cx.a(cuVar, cpVarL.b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (!dVar.h()) {
                throw new cv("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            dVar.l();
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, d dVar) throws cb {
            dVar.l();
            cuVar.a(d.f);
            if (dVar.a != null) {
                cuVar.a(d.g);
                cuVar.a(new cr((byte) 11, (byte) 12, dVar.a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.a.entrySet()) {
                    cuVar.a(entry.getKey());
                    entry.getValue().write(cuVar);
                }
                cuVar.e();
                cuVar.c();
            }
            cuVar.a(d.h);
            cuVar.a(dVar.b);
            cuVar.c();
            if (dVar.c != null) {
                cuVar.a(d.i);
                cuVar.a(dVar.c);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: Imprint.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.d$d, reason: collision with other inner class name */
    private static class C0047d implements dd {
        private C0047d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: Imprint.java */
    private static class c extends df<d> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, d dVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(dVar.a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.a.entrySet()) {
                daVar.a(entry.getKey());
                entry.getValue().write(daVar);
            }
            daVar.a(dVar.b);
            daVar.a(dVar.c);
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, d dVar) throws cb {
            da daVar = (da) cuVar;
            cr crVar = new cr((byte) 11, (byte) 12, daVar.w());
            dVar.a = new HashMap(crVar.c * 2);
            for (int i = 0; i < crVar.c; i++) {
                String strZ = daVar.z();
                com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                eVar.read(daVar);
                dVar.a.put(strZ, eVar);
            }
            dVar.a(true);
            dVar.b = daVar.w();
            dVar.b(true);
            dVar.c = daVar.z();
            dVar.c(true);
        }
    }
}
