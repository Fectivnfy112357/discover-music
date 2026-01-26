package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: SpWrapper.java */
/* loaded from: classes3.dex */
public class at {
    public static final String a = "cl_count";
    public static final String b = "interval_";
    public static final String c = "config_ts";
    public static final String d = "iucc_s1";
    public static final String e = "iucc_s2";
    public static final String f = "sdk_type_ver";
    public static final String g = "should_fetch";
    private static final String h = "ccg_sp_config_file";

    private at() {
    }

    public static SharedPreferences a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(h, 0);
        } catch (Throwable unused) {
            return null;
        }
    }
}
