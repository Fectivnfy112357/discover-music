package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData.java */
/* loaded from: classes3.dex */
public class ch implements Serializable {
    private static Map<Class<? extends bv>, Map<? extends cc, ch>> d = new HashMap();
    public final String a;
    public final byte b;
    public final ci c;

    public ch(String str, byte b, ci ciVar) {
        this.a = str;
        this.b = b;
        this.c = ciVar;
    }

    public static void a(Class<? extends bv> cls, Map<? extends cc, ch> map) {
        d.put(cls, map);
    }

    public static Map<? extends cc, ch> a(Class<? extends bv> cls) throws IllegalAccessException, InstantiationException {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (InstantiationException e2) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }
}
