package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.cj;
import com.umeng.analytics.pro.ck;
import com.umeng.analytics.pro.cm;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.cp;
import com.umeng.analytics.pro.cq;
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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: IdTracking.java */
/* loaded from: classes3.dex */
public class c implements bv<c, e>, Serializable, Cloneable {
    public static final Map<e, ch> d;
    private static final long e = -5764118265293965743L;
    private static final cz f = new cz("IdTracking");
    private static final cp g = new cp("snapshots", (byte) 13, 1);
    private static final cp h = new cp("journals", (byte) 15, 2);
    private static final cp i = new cp("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dc>, dd> j;
    public Map<String, com.umeng.commonsdk.statistics.proto.b> a;
    public List<com.umeng.commonsdk.statistics.proto.a> b;
    public String c;
    private e[] k;

    static {
        HashMap map = new HashMap();
        j = map;
        map.put(de.class, new b());
        map.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new ch("snapshots", (byte) 1, new ck((byte) 13, new ci((byte) 11), new cm((byte) 12, com.umeng.commonsdk.statistics.proto.b.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new ch("journals", (byte) 2, new cj((byte) 15, new cm((byte) 12, com.umeng.commonsdk.statistics.proto.a.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new ch("checksum", (byte) 2, new ci((byte) 11)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(c.class, mapUnmodifiableMap);
    }

    /* compiled from: IdTracking.java */
    public enum e implements cc {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
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
                return SNAPSHOTS;
            }
            if (i == 2) {
                return JOURNALS;
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

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.b(entry.getValue()));
            }
            this.a = map;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
            while (it.hasNext()) {
                arrayList.add(new com.umeng.commonsdk.statistics.proto.a(it.next()));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c deepCopy() {
        return new c(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        Map<String, com.umeng.commonsdk.statistics.proto.b> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.b> c() {
        return this.a;
    }

    public c a(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
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
        List<com.umeng.commonsdk.statistics.proto.a> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<com.umeng.commonsdk.statistics.proto.a> g() {
        List<com.umeng.commonsdk.statistics.proto.a> list = this.b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(com.umeng.commonsdk.statistics.proto.a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<com.umeng.commonsdk.statistics.proto.a> h() {
        return this.b;
    }

    public c a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
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
        StringBuilder sb = new StringBuilder("IdTracking(snapshots:");
        Map<String, com.umeng.commonsdk.statistics.proto.b> map = this.a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            List<com.umeng.commonsdk.statistics.proto.a> list = this.b;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            String str = this.c;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void n() throws cb {
        if (this.a == null) {
            throw new cv("Required field 'snapshots' was not present! Struct: " + toString());
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
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking.java */
    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdTracking.java */
    private static class a extends de<c> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, c cVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                if (cpVarL.b != 0) {
                    short s = cpVarL.c;
                    int i = 0;
                    if (s != 1) {
                        if (s != 2) {
                            if (s == 3) {
                                if (cpVarL.b == 11) {
                                    cVar.c = cuVar.z();
                                    cVar.c(true);
                                } else {
                                    cx.a(cuVar, cpVarL.b);
                                }
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else if (cpVarL.b == 15) {
                            cq cqVarP = cuVar.p();
                            cVar.b = new ArrayList(cqVarP.b);
                            while (i < cqVarP.b) {
                                com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                                aVar.read(cuVar);
                                cVar.b.add(aVar);
                                i++;
                            }
                            cuVar.q();
                            cVar.b(true);
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 13) {
                        cr crVarN = cuVar.n();
                        cVar.a = new HashMap(crVarN.c * 2);
                        while (i < crVarN.c) {
                            String strZ = cuVar.z();
                            com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                            bVar.read(cuVar);
                            cVar.a.put(strZ, bVar);
                            i++;
                        }
                        cuVar.o();
                        cVar.a(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                    cuVar.m();
                } else {
                    cuVar.k();
                    cVar.n();
                    return;
                }
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, c cVar) throws cb {
            cVar.n();
            cuVar.a(c.f);
            if (cVar.a != null) {
                cuVar.a(c.g);
                cuVar.a(new cr((byte) 11, (byte) 12, cVar.a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                    cuVar.a(entry.getKey());
                    entry.getValue().write(cuVar);
                }
                cuVar.e();
                cuVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                cuVar.a(c.h);
                cuVar.a(new cq((byte) 12, cVar.b.size()));
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
                while (it.hasNext()) {
                    it.next().write(cuVar);
                }
                cuVar.f();
                cuVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                cuVar.a(c.i);
                cuVar.a(cVar.c);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: IdTracking.java */
    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0046c b() {
            return new C0046c();
        }
    }

    /* compiled from: IdTracking.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c, reason: collision with other inner class name */
    private static class C0046c extends df<c> {
        private C0046c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, c cVar) throws cb {
            da daVar = (da) cuVar;
            daVar.a(cVar.a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                daVar.a(entry.getKey());
                entry.getValue().write(daVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            daVar.a(bitSet, 2);
            if (cVar.j()) {
                daVar.a(cVar.b.size());
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.b.iterator();
                while (it.hasNext()) {
                    it.next().write(daVar);
                }
            }
            if (cVar.m()) {
                daVar.a(cVar.c);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, c cVar) throws cb {
            da daVar = (da) cuVar;
            cr crVar = new cr((byte) 11, (byte) 12, daVar.w());
            cVar.a = new HashMap(crVar.c * 2);
            for (int i = 0; i < crVar.c; i++) {
                String strZ = daVar.z();
                com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                bVar.read(daVar);
                cVar.a.put(strZ, bVar);
            }
            cVar.a(true);
            BitSet bitSetB = daVar.b(2);
            if (bitSetB.get(0)) {
                cq cqVar = new cq((byte) 12, daVar.w());
                cVar.b = new ArrayList(cqVar.b);
                for (int i2 = 0; i2 < cqVar.b; i2++) {
                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                    aVar.read(daVar);
                    cVar.b.add(aVar);
                }
                cVar.b(true);
            }
            if (bitSetB.get(1)) {
                cVar.c = daVar.z();
                cVar.c(true);
            }
        }
    }
}
