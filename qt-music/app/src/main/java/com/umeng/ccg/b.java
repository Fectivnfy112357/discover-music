package com.umeng.ccg;

import java.util.HashMap;
import java.util.Map;

/* compiled from: CcgSwitch.java */
/* loaded from: classes3.dex */
public class b {
    private static volatile boolean a = true;
    private static volatile boolean b = true;
    private static volatile boolean c = true;
    private static volatile boolean d = true;
    private static Map<String, Boolean> f = new HashMap();
    private static Object e = new Object();

    public static boolean a() {
        boolean z;
        synchronized (e) {
            z = a;
        }
        return z;
    }

    public static boolean b() {
        boolean z;
        synchronized (e) {
            z = b;
        }
        return z;
    }

    public static boolean c() {
        boolean z;
        synchronized (e) {
            z = c;
        }
        return z;
    }

    public static boolean d() {
        boolean z;
        synchronized (e) {
            z = d;
        }
        return z;
    }

    public static void a(boolean z) {
        synchronized (e) {
            d = z;
            f.put(a.e, Boolean.valueOf(z));
        }
    }

    public static boolean a(String str) {
        boolean zBooleanValue;
        synchronized (e) {
            zBooleanValue = f.containsKey(str) ? f.get(str).booleanValue() : true;
        }
        return zBooleanValue;
    }
}
