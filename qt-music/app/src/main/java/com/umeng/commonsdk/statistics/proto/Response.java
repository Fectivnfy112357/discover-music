package com.umeng.commonsdk.statistics.proto;

import androidx.core.app.NotificationCompat;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.ch;
import com.umeng.analytics.pro.ci;
import com.umeng.analytics.pro.cm;
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

/* loaded from: classes3.dex */
public class Response implements bv<Response, e>, Serializable, Cloneable {
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<e, ch> metaDataMap;
    private static final Map<Class<? extends dc>, dd> schemes;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public com.umeng.commonsdk.statistics.proto.d imprint;
    public String msg;
    private e[] optionals;
    public int resp_code;
    private static final cz STRUCT_DESC = new cz("Response");
    private static final cp RESP_CODE_FIELD_DESC = new cp("resp_code", (byte) 8, 1);
    private static final cp MSG_FIELD_DESC = new cp(NotificationCompat.CATEGORY_MESSAGE, (byte) 11, 2);
    private static final cp IMPRINT_FIELD_DESC = new cp(bm.X, (byte) 12, 3);

    static {
        HashMap map = new HashMap();
        schemes = map;
        map.put(de.class, new b());
        map.put(df.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new ch("resp_code", (byte) 1, new ci((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new ch(NotificationCompat.CATEGORY_MESSAGE, (byte) 2, new ci((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new ch(bm.X, (byte) 2, new cm((byte) 12, com.umeng.commonsdk.statistics.proto.d.class)));
        Map<e, ch> mapUnmodifiableMap = Collections.unmodifiableMap(enumMap);
        metaDataMap = mapUnmodifiableMap;
        ch.a(Response.class, mapUnmodifiableMap);
    }

    public enum e implements cc {
        RESP_CODE(1, "resp_code"),
        MSG(2, NotificationCompat.CATEGORY_MESSAGE),
        IMPRINT(3, bm.X);

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
                return RESP_CODE;
            }
            if (i == 2) {
                return MSG;
            }
            if (i != 3) {
                return null;
            }
            return IMPRINT;
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

    public Response() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new com.umeng.commonsdk.statistics.proto.d(response.imprint);
        }
    }

    @Override // com.umeng.analytics.pro.bv
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = bs.b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return bs.a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = bs.a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (z) {
            return;
        }
        this.msg = null;
    }

    public com.umeng.commonsdk.statistics.proto.d getImprint() {
        return this.imprint;
    }

    public Response setImprint(com.umeng.commonsdk.statistics.proto.d dVar) {
        this.imprint = dVar;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (z) {
            return;
        }
        this.imprint = null;
    }

    @Override // com.umeng.analytics.pro.bv
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        schemes.get(cuVar.D()).b().b(cuVar, this);
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        schemes.get(cuVar.D()).b().a(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            String str = this.msg;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            com.umeng.commonsdk.statistics.proto.d dVar = this.imprint;
            if (dVar == null) {
                sb.append("null");
            } else {
                sb.append(dVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws cb {
        com.umeng.commonsdk.statistics.proto.d dVar = this.imprint;
        if (dVar != null) {
            dVar.l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new co(new dg(objectOutputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new co(new dg(objectInputStream)));
        } catch (cb e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private static class b implements dd {
        private b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    private static class a extends de<Response> {
        private a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, Response response) throws cb {
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
                            if (cpVarL.b == 12) {
                                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                                response.imprint.read(cuVar);
                                response.setImprintIsSet(true);
                            } else {
                                cx.a(cuVar, cpVarL.b);
                            }
                        } else {
                            cx.a(cuVar, cpVarL.b);
                        }
                    } else if (cpVarL.b == 11) {
                        response.msg = cuVar.z();
                        response.setMsgIsSet(true);
                    } else {
                        cx.a(cuVar, cpVarL.b);
                    }
                } else if (cpVarL.b == 8) {
                    response.resp_code = cuVar.w();
                    response.setResp_codeIsSet(true);
                } else {
                    cx.a(cuVar, cpVarL.b);
                }
                cuVar.m();
            }
            cuVar.k();
            if (!response.isSetResp_code()) {
                throw new cv("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
            }
            response.validate();
        }

        @Override // com.umeng.analytics.pro.dc
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, Response response) throws cb {
            response.validate();
            cuVar.a(Response.STRUCT_DESC);
            cuVar.a(Response.RESP_CODE_FIELD_DESC);
            cuVar.a(response.resp_code);
            cuVar.c();
            if (response.msg != null && response.isSetMsg()) {
                cuVar.a(Response.MSG_FIELD_DESC);
                cuVar.a(response.msg);
                cuVar.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                cuVar.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(cuVar);
                cuVar.c();
            }
            cuVar.d();
            cuVar.b();
        }
    }

    private static class d implements dd {
        private d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    private static class c extends df<Response> {
        private c() {
        }

        @Override // com.umeng.analytics.pro.dc
        public void a(cu cuVar, Response response) throws cb {
            da daVar = (da) cuVar;
            daVar.a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            daVar.a(bitSet, 2);
            if (response.isSetMsg()) {
                daVar.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(daVar);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        public void b(cu cuVar, Response response) throws cb {
            da daVar = (da) cuVar;
            response.resp_code = daVar.w();
            response.setResp_codeIsSet(true);
            BitSet bitSetB = daVar.b(2);
            if (bitSetB.get(0)) {
                response.msg = daVar.z();
                response.setMsgIsSet(true);
            }
            if (bitSetB.get(1)) {
                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                response.imprint.read(daVar);
                response.setImprintIsSet(true);
            }
        }
    }
}
