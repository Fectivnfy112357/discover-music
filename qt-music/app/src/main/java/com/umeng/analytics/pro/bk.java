package com.umeng.analytics.pro;

import android.os.Build;
import android.text.TextUtils;

/* compiled from: DeviceUtil.java */
/* loaded from: classes3.dex */
public class bk {
    private static final String a = "ro.build.version.emui";
    private static final String b = "hw_sc.build.platform.version";

    public static boolean a() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return !TextUtils.isEmpty((String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.build.flyme.version", ""));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b() {
        return f() && !g();
    }

    public static boolean c() {
        return f() && g();
    }

    private static boolean f() {
        return Build.MANUFACTURER.equalsIgnoreCase("HONOR");
    }

    private static boolean g() {
        return !TextUtils.isEmpty(a(a));
    }

    public static boolean d() {
        String str = Build.BRAND;
        if (!str.equalsIgnoreCase("huawei") && !str.equalsIgnoreCase("honor") && !str.equalsIgnoreCase("华为")) {
            String strA = a(a);
            String strA2 = a(b);
            if (TextUtils.isEmpty(strA) && TextUtils.isEmpty(strA2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean e() {
        return !TextUtils.isEmpty(a("ro.coolos.version"));
    }

    private static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
        } catch (Throwable unused) {
            return "";
        }
    }
}
