package com.umeng.analytics.pro;

import android.os.Build;
import android.text.TextUtils;

/* compiled from: DeviceIdSupplier.java */
/* loaded from: classes3.dex */
public class ba {
    public static ax a() {
        String str = Build.BRAND;
        bl.a("Device", "Brand", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (bk.b()) {
            return new bb();
        }
        if (bk.d()) {
            return new bc();
        }
        if (str.equalsIgnoreCase("xiaomi") || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("meitu") || str.equalsIgnoreCase("小米") || str.equalsIgnoreCase("blackshark")) {
            return new bj();
        }
        if (str.equalsIgnoreCase("vivo")) {
            return new bi();
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("oneplus") || str.equalsIgnoreCase("realme")) {
            return new bg();
        }
        if (str.equalsIgnoreCase("lenovo") || str.equalsIgnoreCase("zuk")) {
            return new bd();
        }
        if (str.equalsIgnoreCase("nubia")) {
            return new bf();
        }
        if (str.equalsIgnoreCase("samsung")) {
            return new bh();
        }
        if (str.equalsIgnoreCase("meizu") || str.equalsIgnoreCase("mblu") || bk.a()) {
            return new be();
        }
        if (bk.e()) {
            return new az();
        }
        return null;
    }

    public static ax b() {
        return new bb();
    }
}
