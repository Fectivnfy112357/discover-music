package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.content.IntentFilter;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.bm;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.util.LinkedList;

/* compiled from: UMSLNetWorkSender.java */
/* loaded from: classes3.dex */
public class b {
    public static final int a = 273;
    private static Context b = null;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static final int f = 274;
    private static final int g = 275;
    private static final int h = 512;
    private static a i = null;
    private static IntentFilter j = null;
    private static volatile boolean k = false;
    private static Object e = new Object();
    private static LinkedList<String> l = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static void q() {
    }

    public static void a(boolean z) {
        k = z;
        if (z) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>网络可用： 触发2号数据仓信封消费动作。");
            b(274);
        } else {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>网络断连： 2号数据仓");
        }
    }

    /* compiled from: UMSLNetWorkSender.java */
    static class a extends FileObserver {
        public a(String str) {
            super(str);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if ((i & 8) != 8) {
                return;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> envelope file created >>> " + str);
            b.a(274);
        }
    }

    public static boolean a() {
        synchronized (e) {
            return i != null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[Catch: all -> 0x0081, DONT_GENERATE, TryCatch #0 {, blocks: (B:21:0x007f, B:20:0x007c, B:5:0x0008, B:7:0x0010, B:9:0x0014, B:11:0x0024, B:13:0x004e, B:14:0x0058, B:15:0x0069, B:17:0x006d), top: B:26:0x0006, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(android.content.Context r6) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.Object r0 = com.umeng.commonsdk.stateless.b.e
            monitor-enter(r0)
            if (r6 == 0) goto L7f
            android.content.Context r1 = r6.getApplicationContext()     // Catch: java.lang.Throwable -> L7b
            com.umeng.commonsdk.stateless.b.b = r1     // Catch: java.lang.Throwable -> L7b
            if (r1 == 0) goto L7f
            android.os.HandlerThread r1 = com.umeng.commonsdk.stateless.b.c     // Catch: java.lang.Throwable -> L7b
            if (r1 != 0) goto L7f
            android.os.HandlerThread r1 = new android.os.HandlerThread     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = "SL-NetWorkSender"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            com.umeng.commonsdk.stateless.b.c = r1     // Catch: java.lang.Throwable -> L7b
            r1.start()     // Catch: java.lang.Throwable -> L7b
            com.umeng.commonsdk.stateless.b$a r1 = com.umeng.commonsdk.stateless.b.i     // Catch: java.lang.Throwable -> L7b
            if (r1 != 0) goto L69
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b
            r1.<init>()     // Catch: java.lang.Throwable -> L7b
            android.content.Context r2 = com.umeng.commonsdk.stateless.b.b     // Catch: java.lang.Throwable -> L7b
            java.io.File r2 = r2.getFilesDir()     // Catch: java.lang.Throwable -> L7b
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = java.io.File.separator     // Catch: java.lang.Throwable -> L7b
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = com.umeng.commonsdk.stateless.a.f     // Catch: java.lang.Throwable -> L7b
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7b
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L7b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L7b
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L7b
            if (r3 != 0) goto L58
            java.lang.String r3 = "MobclickRT"
            java.lang.String r4 = "--->>> 2号数据仓目录不存在，创建之。"
            com.umeng.commonsdk.debug.UMRTLog.e(r3, r4)     // Catch: java.lang.Throwable -> L7b
            r2.mkdir()     // Catch: java.lang.Throwable -> L7b
        L58:
            com.umeng.commonsdk.stateless.b$a r2 = new com.umeng.commonsdk.stateless.b$a     // Catch: java.lang.Throwable -> L7b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L7b
            com.umeng.commonsdk.stateless.b.i = r2     // Catch: java.lang.Throwable -> L7b
            r2.startWatching()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = "MobclickRT"
            java.lang.String r2 = "--->>> 2号数据仓File Monitor启动."
            com.umeng.commonsdk.debug.UMRTLog.e(r1, r2)     // Catch: java.lang.Throwable -> L7b
        L69:
            android.os.Handler r1 = com.umeng.commonsdk.stateless.b.d     // Catch: java.lang.Throwable -> L7b
            if (r1 != 0) goto L7f
            com.umeng.commonsdk.stateless.b$1 r1 = new com.umeng.commonsdk.stateless.b$1     // Catch: java.lang.Throwable -> L7b
            android.os.HandlerThread r2 = com.umeng.commonsdk.stateless.b.c     // Catch: java.lang.Throwable -> L7b
            android.os.Looper r2 = r2.getLooper()     // Catch: java.lang.Throwable -> L7b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            com.umeng.commonsdk.stateless.b.d = r1     // Catch: java.lang.Throwable -> L7b
            goto L7f
        L7b:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r1)     // Catch: java.lang.Throwable -> L81
        L7f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L81
            return
        L81:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L81
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.b.<init>(android.content.Context):void");
    }

    public static void a(int i2) {
        Handler handler;
        if (!k || (handler = d) == null) {
            return;
        }
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.what = i2;
        d.sendMessage(messageObtainMessage);
    }

    public static void b(int i2) {
        Handler handler;
        try {
            if (!k || (handler = d) == null || handler.hasMessages(i2)) {
                return;
            }
            Message messageObtainMessage = d.obtainMessage();
            messageObtainMessage.what = i2;
            d.sendMessage(messageObtainMessage);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    private static void i() {
        File[] fileArrC = d.c(b);
        if (fileArrC != null) {
            if (l.size() > 0) {
                l.clear();
            }
            for (File file : fileArrC) {
                l.add(file.getAbsolutePath());
            }
        }
    }

    private static String j() {
        String str = null;
        try {
            String strPeek = l.peek();
            if (strPeek == null) {
                return strPeek;
            }
            try {
                l.removeFirst();
                return strPeek;
            } catch (Throwable unused) {
                str = strPeek;
                return str;
            }
        } catch (Throwable unused2) {
        }
    }

    private static void k() {
        String strPollFirst;
        byte[] bArrA;
        String strSubstring;
        String str;
        if (l.size() <= 0) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> todoList无内容，无需处理。");
            return;
        }
        do {
            strPollFirst = l.pollFirst();
            if (!TextUtils.isEmpty(strPollFirst)) {
                File file = new File(strPollFirst);
                if (!file.exists()) {
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 信封文件不存在，处理下一个文件。");
                } else {
                    c cVar = new c(b);
                    try {
                        bArrA = d.a(strPollFirst);
                    } catch (Exception unused) {
                        bArrA = null;
                    }
                    String name = file.getName();
                    if (TextUtils.isEmpty(name)) {
                        strSubstring = bm.aL;
                    } else {
                        strSubstring = name.substring(0, 1);
                    }
                    String strC = d.c(d.d(name));
                    if (!com.umeng.commonsdk.vchannel.a.c.equalsIgnoreCase(strC)) {
                        str = "";
                    } else {
                        str = com.umeng.commonsdk.vchannel.a.a;
                    }
                    if (cVar.a(bArrA, strC, str, strSubstring) && !file.delete()) {
                        file.delete();
                    }
                }
            }
        } while (strPollFirst != null);
        l.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
        File fileA;
        byte[] bArrA;
        if (!k || b == null) {
            return;
        }
        do {
            try {
                fileA = d.a(b);
                if (fileA != null && fileA.getParentFile() != null && !TextUtils.isEmpty(fileA.getParentFile().getName())) {
                    c cVar = new c(b);
                    String str = new String(Base64.decode(fileA.getParentFile().getName(), 0));
                    if (!com.umeng.commonsdk.internal.a.a.equalsIgnoreCase(str) && !com.umeng.commonsdk.internal.a.b.equalsIgnoreCase(str) && !com.umeng.commonsdk.internal.a.H.equalsIgnoreCase(str)) {
                        ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
                        try {
                            bArrA = d.a(fileA.getAbsolutePath());
                        } catch (Exception unused) {
                            bArrA = null;
                        }
                        String str2 = "";
                        if (com.umeng.commonsdk.vchannel.a.c.equalsIgnoreCase(str)) {
                            str2 = com.umeng.commonsdk.vchannel.a.a;
                        }
                        String str3 = bm.aL;
                        if (UMServerURL.PATH_SHARE.equalsIgnoreCase(str)) {
                            str3 = "s";
                        }
                        if (UMServerURL.PATH_PUSH_LAUNCH.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_REGIST.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_LOG.equalsIgnoreCase(str)) {
                            str3 = "p";
                        }
                        if (cVar.a(bArrA, str, str2, str3)) {
                            ULog.i("walle", "[stateless] Send envelope file success, delete it.");
                            File file = new File(fileA.getAbsolutePath());
                            if (!file.delete()) {
                                ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                                file.delete();
                            }
                        } else {
                            ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                            return;
                        }
                    } else {
                        new File(fileA.getAbsolutePath()).delete();
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(b, th);
            }
        } while (fileA != null);
        m();
    }

    private static void m() {
        try {
            File file = new File(b.getFilesDir() + File.separator + com.umeng.commonsdk.stateless.a.e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 2号数据仓：删除stateless目录。");
                d.a(file);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n() {
        if (!k || b == null) {
            return;
        }
        i();
        k();
        c();
    }

    private static void o() {
        try {
            File file = new File(b.getFilesDir() + File.separator + com.umeng.commonsdk.stateless.a.e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>2号数据仓：检测到stateless目录。");
                b(a);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p() {
        o();
    }

    public static void b() {
        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>信封构建成功： 触发2号数据仓信封消费动作。");
        b(274);
    }

    public static void c() {
        b(g);
    }

    public static void d() {
        b(512);
    }
}
