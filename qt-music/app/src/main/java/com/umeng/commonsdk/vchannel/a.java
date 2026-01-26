package com.umeng.commonsdk.vchannel;

/* compiled from: Constant.java */
/* loaded from: classes3.dex */
public class a {
    public static String a = "https://pslog.umeng.com";
    public static String b = "https://pslog.umeng.com/";
    public static String c = "explog";
    public static final String d = "analytics";
    public static final String e = "ekv";
    public static final String f = "id";
    public static final String g = "ts";
    public static final String h = "ds";
    public static final String i = "pn";
    public static String j = "";

    static {
        String str = "SUB" + System.currentTimeMillis();
        j = str + String.format("%0" + (32 - str.length()) + "d", 0);
    }
}
