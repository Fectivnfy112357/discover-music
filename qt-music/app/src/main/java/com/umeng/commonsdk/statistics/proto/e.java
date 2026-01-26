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

/* compiled from: ImprintValue.java */
/* loaded from: classes3.dex */
public class e implements bv<e, EnumC0048e>, Serializable, Cloneable {
    public static final Map<EnumC0048e, ch> d;
    private static final long e = 7501688097813630241L;
    private static final cz f = new cz("ImprintValue");
    private static final cp g = new cp("value", (byte) 11, 1);
    private static final cp h = new cp("ts", (byte) 10, 2);
    private static final cp i = new cp("guid", (byte) 11, 3);
    private static final Map<Class<? extends dc>, dd> j;
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private EnumC0048e[] m;

    public void k() throws cb {
    }

    static {
        HashMap map = new HashMap();
        j = map;
        map.put(de.class, new b());
        map.put(df.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0048e.class);
        enumMap.put((EnumMap) EnumC0048e.VALUE, (EnumC0048e) new ch("value", (byte) 2, new ci((byte) 11)));
        enumMap.put((EnumMap) EnumC0048e.TS, (EnumC0048e) new ch("ts", (byte) 2, new ci((byte) 10)));
        enumMap.put((EnumMap) EnumC0048e.GUID, (EnumC0048e) new ch("guid", (byte) 2, new ci((byte) 11)));
        Map<EnumC0048e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        d = mapUnmodifiableMap;
        ch.a(e.class, mapUnmodifiableMap);
    }

    /* compiled from: ImprintValue.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.e$e, reason: collision with other inner class name */
    public enum EnumC0048e implements cc {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");

        private static final Map<String, EnumC0048e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(EnumC0048e.class).iterator();
            while (it.hasNext()) {
                EnumC0048e enumC0048e = (EnumC0048e) it.next();
                d.put(enumC0048e.b(), enumC0048e);
            }
        }

        public static EnumC0048e a(int i) {
            if (i == 1) {
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
        }

        public static EnumC0048e b(int i) {
            EnumC0048e enumC0048eA = a(i);
            if (enumC0048eA != null) {
                return enumC0048eA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static EnumC0048e a(String str) {
            return d.get(str);
        }

        EnumC0048e(short s, String str) {
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

    public e() {
        this.l = (byte) 0;
        this.m = new EnumC0048e[]{EnumC0048e.VALUE, EnumC0048e.TS, EnumC0048e.GUID};
    }

    public e(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.m = new EnumC0048e[]{EnumC0048e.VALUE, EnumC0048e.TS, EnumC0048e.GUID};
        this.l = eVar.l;
        if (eVar.d()) {
            this.a = eVar.a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
        }
    }

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public e deepCopy() {
        return new e(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public String b() {
        return this.a;
    }

    public e a(String str) {
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

    public e a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.l = bs.b(this.l, 0);
    }

    public boolean g() {
        return bs.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = bs.a(this.l, 0, z);
    }

    public String h() {
        return this.c;
    }

    public e b(String str) {
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

    @Override // com.umeng.analytics.pro.bv
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public EnumC0048e fieldForId(int i2) {
        return EnumC0048e.a(i2);
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
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            String str = this.a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.c;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
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

    /* compiled from: ImprintValue.java */
    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: ImprintValue.java */
    private static class a extends de<e> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, e eVar) throws cb {
            cuVar.j();
            while (true) {
                cp cpVarL = cuVar.l();
                if (cpVarL.b != 0) {
                    short s = cpVarL.c;
                    if (s != 1) {
                        if (s != 2) {
                            if (s == 3) {
                                if (cpVarL.b == 11) {
                                    eVar.c = cuVar.z();
                                    eVar.c(true);
                                } else {
                                    cx.a(cuVar, cpVarL.b);
                                }
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else if (cpVarL.b == 10) {
                            eVar.b = cuVar.x();
                            eVar.b(true);
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 11) {
                        eVar.a = cuVar.z();
                        eVar.a(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                    cuVar.m();
                } else {
                    cuVar.k();
                    eVar.k();
                    return;
                }
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, e eVar) throws cb {
            eVar.k();
            cuVar.a(e.f);
            if (eVar.a != null && eVar.d()) {
                cuVar.a(e.g);
                cuVar.a(eVar.a);
                cuVar.c();
            }
            if (eVar.g()) {
                cuVar.a(e.h);
                cuVar.a(eVar.b);
                cuVar.c();
            }
            if (eVar.c != null && eVar.j()) {
                cuVar.a(e.i);
                cuVar.a(eVar.c);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: ImprintValue.java */
    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: ImprintValue.java */
    private static class c extends df<e> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, e eVar) throws cb {
            da daVar = (da) cuVar;
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            if (eVar.g()) {
                bitSet.set(1);
            }
            if (eVar.j()) {
                bitSet.set(2);
            }
            daVar.a(bitSet, 3);
            if (eVar.d()) {
                daVar.a(eVar.a);
            }
            if (eVar.g()) {
                daVar.a(eVar.b);
            }
            if (eVar.j()) {
                daVar.a(eVar.c);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, e eVar) throws cb {
            da daVar = (da) cuVar;
            BitSet bitSetB = daVar.b(3);
            if (bitSetB.get(0)) {
                eVar.a = daVar.z();
                eVar.a(true);
            }
            if (bitSetB.get(1)) {
                eVar.b = daVar.x();
                eVar.b(true);
            }
            if (bitSetB.get(2)) {
                eVar.c = daVar.z();
                eVar.c(true);
            }
        }
    }
}
