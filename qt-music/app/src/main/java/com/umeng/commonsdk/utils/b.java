package com.umeng.commonsdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.umeng.commonsdk.debug.UMRTLog;
import java.util.HashMap;

/* compiled from: PkgInfoUtil.java */
/* loaded from: classes3.dex */
public class b {
    private static HashMap<String, PackageInfo> a = new HashMap<>();
    private static Object b = new Object();

    private b() {
    }

    /* compiled from: PkgInfoUtil.java */
    private static class a {
        private static final b a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.a;
    }

    public PackageInfo a(Context context, String str, int i) {
        PackageInfo packageInfo;
        synchronized (b) {
            if (a.containsKey(str)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> pkg： " + str + ", pkgInfo缓存命中，直接返回");
                packageInfo = a.get(str);
            } else {
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(str, i);
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> pkg： " + str + ", 获取pkgInfo并缓存");
                    a.put(str, packageInfo);
                } catch (PackageManager.NameNotFoundException unused) {
                    a.put(str, null);
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> pkg: " + str + "，目标包未安装。");
                    packageInfo = null;
                }
            }
        }
        return packageInfo;
    }
}
