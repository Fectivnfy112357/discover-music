package com.umeng.analytics.pro;

import android.text.TextUtils;

/* compiled from: RomUtils.java */
/* loaded from: classes3.dex */
public class as {
    private static String a = "";
    private static String b = "";
    private static final String c = "hw_sc.build.platform.version";
    private static final String d = "ro.build.version.emui";
    private static final String e = "ro.build.version.magic";
    private static final String f = "ro.miui.ui.version.name";
    private static final String g = "ro.build.version.opporom";
    private static final String h = "ro.vivo.os.name";
    private static final String i = "ro.vivo.os.version";
    private static final String j = "ro.build.version.oplusrom";
    private static final String k = "ro.rom.version";

    private static String d(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean a() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return !TextUtils.isEmpty((String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(a)) {
            e(str);
        }
        return a;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(a)) {
            e(str);
        }
        return b;
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll(" ", "").toUpperCase();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void e(java.lang.String r4) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.as.e(java.lang.String):void");
    }
}
