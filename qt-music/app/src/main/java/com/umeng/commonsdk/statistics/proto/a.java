package com.umeng.commonsdk.statistics.proto;

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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdJournal.java */
/* loaded from: classes3.dex */
public class a implements bv<a, e>, Serializable, Cloneable {
    public static final Map<e, ch> e;
    private static final long f = 9132678615281394583L;
    private static final cz g = new cz("IdJournal");
    private static final cp h = new cp("domain", (byte) 11, 1);
    private static final cp i = new cp("old_id", (byte) 11, 2);
    private static final cp j = new cp("new_id", (byte) 11, 3);
    private static final cp k = new cp("ts", (byte) 10, 4);
    private static final Map<Class<? extends dc>, dd> l;
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    static {
        HashMap map = new HashMap();
        l = map;
        map.put(de.class, new b());
        map.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DOMAIN, (e) new ch("domain", (byte) 1, new ci((byte) 11)));
        enumMap.put((EnumMap) e.OLD_ID, (e) new ch("old_id", (byte) 2, new ci((byte) 11)));
        enumMap.put((EnumMap) e.NEW_ID, (e) new ch("new_id", (byte) 1, new ci((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new ch("ts", (byte) 1, new ci((byte) 10)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        e = mapUnmodifiableMap;
        ch.a(a.class, mapUnmodifiableMap);
    }

    /* compiled from: IdJournal.java */
    public enum e implements cc {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");

        private static final Map<String, e> e = new HashMap();
        private final short f;
        private final String g;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return e.get(str);
        }

        e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        @Override // com.umeng.analytics.pro.cc
        public short a() {
            return this.f;
        }

        @Override // com.umeng.analytics.pro.cc
        public String b() {
            return this.g;
        }
    }

    public a() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public a(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public a(a aVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.a = aVar.a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a deepCopy() {
        return new a(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0L;
    }

    public String b() {
        return this.a;
    }

    public a a(String str) {
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

    public String e() {
        return this.b;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String h() {
        return this.c;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    public long k() {
        return this.d;
    }

    public a a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void l() {
        this.n = bs.b(this.n, 0);
    }

    public boolean m() {
        return bs.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = bs.a(this.n, 0, z);
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        l.get(cuVar.D()).b().b(cuVar, this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        l.get(cuVar.D()).b().a(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(domain:");
        String str = this.a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    public void n() throws cb {
        if (this.a == null) {
            throw new cv("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.c == null) {
            throw new cv("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdJournal.java */
    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0044a b() {
            return new C0044a();
        }
    }

    /* compiled from: IdJournal.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.a$a, reason: collision with other inner class name */
    private static class C0044a extends de<a> {
        private C0044a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, a aVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                if (cpVarL.b == 0) {
                    break;
                }
                short s = cpVarL.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            if (s == 4) {
                                if (cpVarL.b == 10) {
                                    aVar.d = cuVar.x();
                                    aVar.d(true);
                                } else {
                                    cx.a(cuVar, cpVarL.b);
                                }
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else if (cpVarL.b == 11) {
                            aVar.c = cuVar.z();
                            aVar.c(true);
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 11) {
                        aVar.b = cuVar.z();
                        aVar.b(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                } else if (cpVarL.b == 11) {
                    aVar.a = cuVar.z();
                    aVar.a(true);
                } else {
                    cx.a(cuVar, cpVarL.b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (!aVar.m()) {
                throw new cv("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            aVar.n();
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, a aVar) throws cb {
            aVar.n();
            cuVar.a(a.g);
            if (aVar.a != null) {
                cuVar.a(a.h);
                cuVar.a(aVar.a);
                cuVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                cuVar.a(a.i);
                cuVar.a(aVar.b);
                cuVar.c();
            }
            if (aVar.c != null) {
                cuVar.a(a.j);
                cuVar.a(aVar.c);
                cuVar.c();
            }
            cuVar.a(a.k);
            cuVar.a(aVar.d);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: IdJournal.java */
    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdJournal.java */
    private static class c extends df<a> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, a aVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(aVar.a);
            daVar.a(aVar.c);
            daVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            daVar.a(bitSet, 1);
            if (aVar.g()) {
                daVar.a(aVar.b);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, a aVar) throws cb {
            da daVar = (da) cuVar;
            aVar.a = daVar.z();
            aVar.a(true);
            aVar.c = daVar.z();
            aVar.c(true);
            aVar.d = daVar.x();
            aVar.d(true);
            if (daVar.b(1).get(0)) {
                aVar.b = daVar.z();
                aVar.b(true);
            }
        }
    }
}
