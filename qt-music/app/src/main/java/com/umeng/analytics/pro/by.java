package com.umeng.analytics.pro;

import com.umeng.analytics.pro.co;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TDeserializer.java */
/* loaded from: classes3.dex */
public class by {
    private final cu a;
    private final dh b;

    public by() {
        this(new co.a());
    }

    public by(cw cwVar) {
        dh dhVar = new dh();
        this.b = dhVar;
        this.a = cwVar.a(dhVar);
    }

    public void a(bv bvVar, byte[] bArr) throws cb {
        try {
            this.b.a(bArr);
            bvVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(bv bvVar, String str, String str2) throws cb {
        try {
            try {
                a(bvVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new cb("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.a.B();
        }
    }

    public void a(bv bvVar, byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        try {
            try {
                if (j(bArr, ccVar, ccVarArr) != null) {
                    bvVar.read(this.a);
                }
            } catch (Exception e) {
                throw new cb(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Boolean) a((byte) 2, bArr, ccVar, ccVarArr);
    }

    public Byte b(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Byte) a((byte) 3, bArr, ccVar, ccVarArr);
    }

    public Double c(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Double) a((byte) 4, bArr, ccVar, ccVarArr);
    }

    public Short d(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Short) a((byte) 6, bArr, ccVar, ccVarArr);
    }

    public Integer e(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Integer) a((byte) 8, bArr, ccVar, ccVarArr);
    }

    public Long f(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Long) a((byte) 10, bArr, ccVar, ccVarArr);
    }

    public String g(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (String) a((byte) 11, bArr, ccVar, ccVarArr);
    }

    public ByteBuffer h(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (ByteBuffer) a((byte) 100, bArr, ccVar, ccVarArr);
    }

    public Short i(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        try {
            try {
                if (j(bArr, ccVar, ccVarArr) != null) {
                    this.a.j();
                    return Short.valueOf(this.a.l().c);
                }
                this.b.e();
                this.a.B();
                return null;
            } catch (Exception e) {
                throw new cb(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        try {
            try {
                cp cpVarJ = j(bArr, ccVar, ccVarArr);
                if (cpVarJ != null) {
                    if (b != 2) {
                        if (b != 3) {
                            if (b != 4) {
                                if (b != 6) {
                                    if (b != 8) {
                                        if (b != 100) {
                                            if (b != 10) {
                                                if (b == 11 && cpVarJ.b == 11) {
                                                    return this.a.z();
                                                }
                                            } else if (cpVarJ.b == 10) {
                                                return Long.valueOf(this.a.x());
                                            }
                                        } else if (cpVarJ.b == 11) {
                                            return this.a.A();
                                        }
                                    } else if (cpVarJ.b == 8) {
                                        return Integer.valueOf(this.a.w());
                                    }
                                } else if (cpVarJ.b == 6) {
                                    return Short.valueOf(this.a.v());
                                }
                            } else if (cpVarJ.b == 4) {
                                return Double.valueOf(this.a.y());
                            }
                        } else if (cpVarJ.b == 3) {
                            return Byte.valueOf(this.a.u());
                        }
                    } else if (cpVarJ.b == 2) {
                        return Boolean.valueOf(this.a.t());
                    }
                }
                this.b.e();
                this.a.B();
                return null;
            } catch (Exception e) {
                throw new cb(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private cp j(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        this.b.a(bArr);
        int length = ccVarArr.length + 1;
        cc[] ccVarArr2 = new cc[length];
        int i = 0;
        ccVarArr2[0] = ccVar;
        int i2 = 0;
        while (i2 < ccVarArr.length) {
            int i3 = i2 + 1;
            ccVarArr2[i3] = ccVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        cp cpVarL = null;
        while (i < length) {
            cpVarL = this.a.l();
            if (cpVarL.b == 0 || cpVarL.c > ccVarArr2[i].a()) {
                return null;
            }
            if (cpVarL.c != ccVarArr2[i].a()) {
                cx.a(this.a, cpVarL.b);
                this.a.m();
            } else {
                i++;
                if (i < length) {
                    this.a.j();
                }
            }
        }
        return cpVarL;
    }

    public void a(bv bvVar, String str) throws cb {
        a(bvVar, str.getBytes());
    }
}
