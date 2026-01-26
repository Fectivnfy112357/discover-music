package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.cf;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: TUnion.java */
/* loaded from: classes3.dex */
public abstract class cf<T extends cf<?, ?>, F extends cc> implements bv<T, F> {
    private static final Map<Class<? extends dc>, dd> c;
    protected Object a;
    protected F b;

    protected abstract F a(short s);

    protected abstract Object a(cu cuVar, cp cpVar) throws cb;

    protected abstract Object a(cu cuVar, short s) throws cb;

    protected abstract void a(cu cuVar) throws cb;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract void b(cu cuVar) throws cb;

    protected abstract cp c(F f);

    protected abstract cz d();

    protected cf() {
        this.b = null;
        this.a = null;
    }

    static {
        HashMap map = new HashMap();
        c = map;
        map.put(de.class, new b());
        map.put(df.class, new d());
    }

    protected cf(F f, Object obj) throws ClassCastException {
        a((cf<T, F>) f, obj);
    }

    protected cf(cf<T, F> cfVar) {
        if (!cfVar.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.b = cfVar.b;
        this.a = a(cfVar.a);
    }

    private static Object a(Object obj) {
        if (obj instanceof bv) {
            return ((bv) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return bw.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(a(entry.getKey()), a(entry.getValue()));
        }
        return map2;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object b() {
        return this.a;
    }

    public Object a(F f) {
        if (f != this.b) {
            throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
        }
        return b();
    }

    public Object a(int i) {
        return a((cf<T, F>) a((short) i));
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b((cf<T, F>) a((short) i));
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        c.get(cuVar.D()).b().b(cuVar, this);
    }

    public void a(F f, Object obj) throws ClassCastException {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) throws ClassCastException {
        a((cf<T, F>) a((short) i), obj);
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        c.get(cuVar.D()).b().a(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (a() != null) {
            Object objB = b();
            sb.append(c(a()).a);
            sb.append(":");
            if (objB instanceof ByteBuffer) {
                bw.a((ByteBuffer) objB, sb);
            } else {
                sb.append(objB.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public final void clear() {
        this.b = null;
        this.a = null;
    }

    /* compiled from: TUnion.java */
    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* compiled from: TUnion.java */
    private static class a extends de<cf> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, cf cfVar) throws cb {
            cfVar.b = null;
            cfVar.a = null;
            cuVar.j();
            cp cpVarL = cuVar.l();
            cfVar.a = cfVar.a(cuVar, cpVarL);
            if (cfVar.a != null) {
                cfVar.b = (F) cfVar.a(cpVarL.c);
            }
            cuVar.m();
            cuVar.l();
            cuVar.k();
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, cf cfVar) throws cb {
            if (cfVar.a() == null || cfVar.b() == null) {
                throw new cv("Cannot write a TUnion with no set value!");
            }
            cuVar.a(cfVar.d());
            cuVar.a(cfVar.c(cfVar.b));
            cfVar.a(cuVar);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* compiled from: TUnion.java */
    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    /* compiled from: TUnion.java */
    private static class c extends df<cf> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, cf cfVar) throws cb {
            cfVar.b = null;
            cfVar.a = null;
            short sV = cuVar.v();
            cfVar.a = cfVar.a(cuVar, sV);
            if (cfVar.a != null) {
                cfVar.b = (F) cfVar.a(sV);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, cf cfVar) throws cb {
            if (cfVar.a() == null || cfVar.b() == null) {
                throw new cv("Cannot write a TUnion with no set value!");
            }
            cuVar.a(cfVar.b.a());
            cfVar.b(cuVar);
        }
    }
}
