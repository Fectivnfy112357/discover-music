package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.g;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UMStoreManager.java */
/* loaded from: classes3.dex */
public class k {
    public static final int a = 2049;
    public static final int b = 2050;
    private static final int c = 1000;
    private static Context d = null;
    private static String e = null;
    private static final String f = "umeng+";
    private static final String g = "ek__id";
    private static final String h = "ek_key";
    private List<String> i;
    private List<Integer> j;
    private String k;
    private List<String> l;

    /* compiled from: UMStoreManager.java */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* compiled from: UMStoreManager.java */
    private static class b {
        private static final k a = new k();

        private b() {
        }
    }

    private k() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = null;
        this.l = new ArrayList();
    }

    public static k a(Context context) {
        k kVar = b.a;
        if (d == null && context != null) {
            d = context.getApplicationContext();
            kVar.k();
        }
        return kVar;
    }

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    private Cursor a(String str, SQLiteDatabase sQLiteDatabase, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        if (sQLiteDatabase == null) {
            return null;
        }
        try {
            if (sQLiteDatabase.isOpen()) {
                return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void a() {
        this.i.clear();
    }

    public void b() {
        this.l.clear();
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x009a, code lost:
    
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009c, code lost:
    
        r2.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ae, code lost:
    
        if (r2 == null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(org.json.JSONArray r11) {
        /*
            r10 = this;
            java.lang.String r0 = "__t"
            java.lang.String r1 = "__i"
            r2 = 0
            android.content.Context r3 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L9a android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            com.umeng.analytics.pro.i r3 = com.umeng.analytics.pro.i.a(r3)     // Catch: java.lang.Throwable -> L9a android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch: java.lang.Throwable -> L9a android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            r3.beginTransaction()     // Catch: java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r4 = 0
        L13:
            int r5 = r11.length()     // Catch: java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            if (r4 >= r5) goto L8d
            org.json.JSONObject r5 = r11.getJSONObject(r4)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.<init>()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = r5.optString(r1)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r9 = "-1"
            if (r8 != 0) goto L34
            boolean r8 = r9.equals(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            if (r8 == 0) goto L43
        L34:
            com.umeng.analytics.pro.w r7 = com.umeng.analytics.pro.w.a()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = r7.b()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            if (r8 == 0) goto L43
            r7 = r9
        L43:
            r6.put(r1, r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = "__e"
            java.lang.String r8 = "id"
            java.lang.String r8 = r5.optString(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            int r7 = r5.optInt(r0)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.put(r0, r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = "__av"
            android.content.Context r8 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = "__vc"
            android.content.Context r8 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r5.remove(r1)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r5.remove(r0)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r7 = "__s"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r5 = r10.c(r5)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            r6.put(r7, r5)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            java.lang.String r5 = "__et"
            r3.insert(r5, r2, r6)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
        L8a:
            int r4 = r4 + 1
            goto L13
        L8d:
            r3.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L96 android.database.sqlite.SQLiteDatabaseCorruptException -> L98
            if (r3 == 0) goto L9f
            r3.endTransaction()     // Catch: java.lang.Throwable -> L9f
            goto L9f
        L96:
            r2 = r3
            goto L9a
        L98:
            r2 = r3
            goto La9
        L9a:
            if (r2 == 0) goto L9f
        L9c:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L9f
        L9f:
            android.content.Context r11 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r11 = com.umeng.analytics.pro.i.a(r11)
            r11.b()
            goto Lb1
        La9:
            android.content.Context r11 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> Lb2
            com.umeng.analytics.pro.j.a(r11)     // Catch: java.lang.Throwable -> Lb2
            if (r2 == 0) goto L9f
            goto L9c
        Lb1:
            return
        Lb2:
            r11 = move-exception
            if (r2 == 0) goto Lb8
            r2.endTransaction()     // Catch: java.lang.Throwable -> Lb8
        Lb8:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(org.json.JSONArray):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0058, code lost:
    
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005a, code lost:
    
        r0.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006c, code lost:
    
        if (r0 == null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            r2.<init>()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r3 = "__i"
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r5 = r4.c(r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            if (r6 != 0) goto L4b
            java.lang.String r6 = "__a"
            r2.put(r6, r5)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r5 = "__t"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r5 = "__av"
            android.content.Context r6 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r5 = "__vc"
            android.content.Context r6 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            java.lang.String r5 = "__er"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
        L4b:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L56
            if (r1 == 0) goto L5d
            r1.endTransaction()     // Catch: java.lang.Throwable -> L5d
            goto L5d
        L54:
            r0 = r1
            goto L58
        L56:
            r0 = r1
            goto L67
        L58:
            if (r0 == 0) goto L5d
        L5a:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L5d
        L5d:
            android.content.Context r5 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r5 = com.umeng.analytics.pro.i.a(r5)
            r5.b()
            goto L6f
        L67:
            android.content.Context r5 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L71
            com.umeng.analytics.pro.j.a(r5)     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L5d
            goto L5a
        L6f:
            r5 = 0
            return r5
        L71:
            r5 = move-exception
            if (r0 == 0) goto L77
            r0.endTransaction()     // Catch: java.lang.Throwable -> L77
        L77:
            android.content.Context r6 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r6 = com.umeng.analytics.pro.i.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(java.lang.String, java.lang.String, int):boolean");
    }

    public void d() {
        String strC;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                strC = w.a().c();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
            if (TextUtils.isEmpty(strC)) {
                return;
            }
            String[] strArr = {"", "-1"};
            for (int i = 0; i < 2; i++) {
                sQLiteDatabaseA.execSQL("update __et set __i=\"" + strC + "\" where __i=\"" + strArr[i] + "\"");
            }
            sQLiteDatabaseA.setTransactionSuccessful();
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused4) {
                }
            }
            i.a(d).b();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
    
        if (r2 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0089, code lost:
    
        r2.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009b, code lost:
    
        if (r2 == null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r7, org.json.JSONObject r8, com.umeng.analytics.pro.k.a r9) {
        /*
            r6 = this;
            java.lang.String r0 = "__e"
            r1 = 0
            if (r8 != 0) goto L6
            return r1
        L6:
            r2 = 0
            android.content.Context r3 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteDatabaseCorruptException -> L96
            com.umeng.analytics.pro.i r3 = com.umeng.analytics.pro.i.a(r3)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteDatabaseCorruptException -> L96
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteDatabaseCorruptException -> L96
            r3.beginTransaction()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            com.umeng.analytics.pro.k$a r4 = com.umeng.analytics.pro.k.a.BEGIN     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r4) goto L4f
            java.lang.Object r8 = r8.opt(r0)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.Long r8 = (java.lang.Long) r8     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            long r8 = r8.longValue()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            r4.<init>()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r5 = "__ii"
            r4.put(r5, r7)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            r4.put(r0, r7)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r7 = "__av"
            android.content.Context r8 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r8)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            r4.put(r7, r8)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r7 = "__vc"
            android.content.Context r8 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r8)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            r4.put(r7, r8)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            java.lang.String r7 = "__sd"
            r3.insert(r7, r2, r4)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            goto L7a
        L4f:
            com.umeng.analytics.pro.k$a r0 = com.umeng.analytics.pro.k.a.INSTANTSESSIONBEGIN     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r0) goto L57
            r6.b(r7, r8, r3)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            goto L7a
        L57:
            com.umeng.analytics.pro.k$a r0 = com.umeng.analytics.pro.k.a.END     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r0) goto L5f
            r6.a(r7, r8, r3)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            goto L7a
        L5f:
            com.umeng.analytics.pro.k$a r0 = com.umeng.analytics.pro.k.a.PAGE     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r0) goto L69
            java.lang.String r9 = "__a"
            r6.a(r7, r8, r3, r9)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            goto L7a
        L69:
            com.umeng.analytics.pro.k$a r0 = com.umeng.analytics.pro.k.a.AUTOPAGE     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r0) goto L73
            java.lang.String r9 = "__b"
            r6.a(r7, r8, r3, r9)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            goto L7a
        L73:
            com.umeng.analytics.pro.k$a r0 = com.umeng.analytics.pro.k.a.NEWSESSION     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r9 != r0) goto L7a
            r6.c(r7, r8, r3)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
        L7a:
            r3.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteDatabaseCorruptException -> L85
            if (r3 == 0) goto L8c
            r3.endTransaction()     // Catch: java.lang.Throwable -> L8c
            goto L8c
        L83:
            r2 = r3
            goto L87
        L85:
            r2 = r3
            goto L96
        L87:
            if (r2 == 0) goto L8c
        L89:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L8c
        L8c:
            android.content.Context r7 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r7 = com.umeng.analytics.pro.i.a(r7)
            r7.b()
            goto L9e
        L96:
            android.content.Context r7 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L9f
            com.umeng.analytics.pro.j.a(r7)     // Catch: java.lang.Throwable -> L9f
            if (r2 == 0) goto L8c
            goto L89
        L9e:
            return r1
        L9f:
            r7 = move-exception
            if (r2 == 0) goto La5
            r2.endTransaction()     // Catch: java.lang.Throwable -> La5
        La5:
            android.content.Context r8 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r8 = com.umeng.analytics.pro.i.a(r8)
            r8.b()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(java.lang.String, org.json.JSONObject, com.umeng.analytics.pro.k$a):boolean");
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        String strC;
        try {
            long jLongValue = ((Long) jSONObject.opt(g.d.a.g)).longValue();
            Object objOpt = jSONObject.opt(g.d.a.h);
            long jLongValue2 = (objOpt == null || !(objOpt instanceof Long)) ? 0L : ((Long) objOpt).longValue();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("__pp");
            String strC2 = "";
            if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
                strC = "";
            } else {
                strC = c(jSONObjectOptJSONObject.toString());
            }
            if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.length() > 0) {
                strC2 = c(jSONObjectOptJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + jLongValue + "\", __g=\"" + jLongValue2 + "\", __sp=\"" + strC + "\", __pp=\"" + strC2 + "\" where __ii=\"" + str + "\"");
        } catch (Throwable unused) {
        }
    }

    private void b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        String strC;
        try {
            long jLongValue = ((Long) jSONObject.get("__e")).longValue();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("__pp");
            String strC2 = "";
            if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
                strC = "";
            } else {
                strC = c(jSONObjectOptJSONObject.toString());
            }
            if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.length() > 0) {
                strC2 = c(jSONObjectOptJSONObject2.toString());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("__ii", str);
            contentValues.put("__e", String.valueOf(jLongValue));
            contentValues.put("__sp", strC);
            contentValues.put("__pp", strC2);
            contentValues.put("__av", UMGlobalContext.getInstance(d).getAppVersion());
            contentValues.put("__vc", UMUtils.getAppVersionCode(d));
            sQLiteDatabase.insert(g.c.a, null, contentValues);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060 A[Catch: Exception -> 0x0063, PHI: r2 r4
  0x0060: PHI (r2v3 long) = (r2v0 long), (r2v6 long) binds: [B:24:0x005e, B:9:0x003d] A[DONT_GENERATE, DONT_INLINE]
  0x0060: PHI (r4v4 android.database.sqlite.SQLiteDatabase) = (r4v3 android.database.sqlite.SQLiteDatabase), (r4v7 android.database.sqlite.SQLiteDatabase) binds: [B:24:0x005e, B:9:0x003d] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #5 {Exception -> 0x0063, blocks: (B:8:0x003a, B:25:0x0060, B:23:0x005b), top: B:33:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long a(java.lang.String r16) throws java.lang.Throwable {
        /*
            r15 = this;
            java.lang.String r0 = "__f"
            r1 = 0
            r2 = 0
            android.content.Context r4 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L58
            com.umeng.analytics.pro.i r4 = com.umeng.analytics.pro.i.a(r4)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L58
            android.database.sqlite.SQLiteDatabase r4 = r4.a()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L58
            r4.beginTransaction()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            r5 = 1
            java.lang.String[] r8 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            r6 = 0
            r8[r6] = r0     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            java.lang.String r7 = "__sd"
            java.lang.String r9 = "__ii=? "
            java.lang.String[] r10 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            r10[r6] = r16     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            r13 = 0
            r14 = 0
            r11 = 0
            r12 = 0
            r5 = r15
            r6 = r7
            r7 = r4
            android.database.Cursor r1 = r5.a(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            if (r1 == 0) goto L38
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
            long r2 = r1.getLong(r0)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L59
        L38:
            if (r1 == 0) goto L3d
            r1.close()     // Catch: java.lang.Exception -> L63
        L3d:
            if (r4 == 0) goto L63
            goto L60
        L40:
            r0 = move-exception
            goto L44
        L42:
            r0 = move-exception
            r4 = r1
        L44:
            if (r1 == 0) goto L49
            r1.close()     // Catch: java.lang.Exception -> L4e
        L49:
            if (r4 == 0) goto L4e
            r4.endTransaction()     // Catch: java.lang.Exception -> L4e
        L4e:
            android.content.Context r1 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)
            r1.b()
            throw r0
        L58:
            r4 = r1
        L59:
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.lang.Exception -> L63
        L5e:
            if (r4 == 0) goto L63
        L60:
            r4.endTransaction()     // Catch: java.lang.Exception -> L63
        L63:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(java.lang.String):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(java.lang.String r20, org.json.JSONObject r21, android.database.sqlite.SQLiteDatabase r22) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.c(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0077 A[Catch: all -> 0x0069, TryCatch #0 {all -> 0x0069, blocks: (B:22:0x0056, B:24:0x005c, B:26:0x006c, B:28:0x0077, B:29:0x007c, B:36:0x008c, B:38:0x0092, B:40:0x0098, B:42:0x009e, B:44:0x00ac, B:41:0x009b), top: B:52:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0056 A[EXC_TOP_SPLITTER, LOOP:1: B:52:0x0056->B:24:0x005c, LOOP_START, PHI: r14
  0x0056: PHI (r14v2 java.lang.String) = (r14v7 java.lang.String), (r14v3 java.lang.String) binds: [B:21:0x0054, B:24:0x005c] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.lang.String r18, org.json.JSONObject r19, android.database.sqlite.SQLiteDatabase r20, java.lang.String r21) throws org.json.JSONException {
        /*
            r17 = this;
            r10 = r17
            r11 = r18
            r0 = r19
            r12 = r21
            java.lang.String r13 = "=\""
            java.lang.String r1 = "__b"
            java.lang.String r2 = "__a"
            r14 = 0
            boolean r3 = r2.equals(r12)     // Catch: java.lang.Throwable -> Lec
            if (r3 == 0) goto L25
            org.json.JSONArray r0 = r0.optJSONArray(r2)     // Catch: java.lang.Throwable -> Lec
            if (r0 == 0) goto L24
            int r1 = r0.length()     // Catch: java.lang.Throwable -> Lec
            if (r1 > 0) goto L22
            goto L24
        L22:
            r15 = r0
            goto L39
        L24:
            return
        L25:
            boolean r2 = r1.equals(r12)     // Catch: java.lang.Throwable -> Lec
            if (r2 == 0) goto L38
            org.json.JSONArray r0 = r0.optJSONArray(r1)     // Catch: java.lang.Throwable -> Lec
            if (r0 == 0) goto L37
            int r1 = r0.length()     // Catch: java.lang.Throwable -> Lec
            if (r1 > 0) goto L22
        L37:
            return
        L38:
            r15 = r14
        L39:
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> Lec
            r16 = 0
            r3[r16] = r12     // Catch: java.lang.Throwable -> Lec
            java.lang.String r1 = "__sd"
            java.lang.String r4 = "__ii=? "
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> Lec
            r5[r16] = r11     // Catch: java.lang.Throwable -> Lec
            r8 = 0
            r9 = 0
            r6 = 0
            r7 = 0
            r0 = r17
            r2 = r20
            android.database.Cursor r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> Lec
            if (r0 == 0) goto L6c
        L56:
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L6c
            int r1 = r0.getColumnIndex(r12)     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Throwable -> L69
            java.lang.String r14 = r10.d(r1)     // Catch: java.lang.Throwable -> L69
            goto L56
        L69:
            r14 = r0
            goto Lec
        L6c:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L69
            r1.<init>()     // Catch: java.lang.Throwable -> L69
            boolean r2 = android.text.TextUtils.isEmpty(r14)     // Catch: java.lang.Throwable -> L69
            if (r2 != 0) goto L7c
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L69
            r1.<init>(r14)     // Catch: java.lang.Throwable -> L69
        L7c:
            int r2 = r1.length()     // Catch: java.lang.Throwable -> L69
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 <= r3) goto L8a
            if (r0 == 0) goto L89
            r0.close()
        L89:
            return
        L8a:
            r2 = r16
        L8c:
            int r3 = r15.length()     // Catch: java.lang.Throwable -> L69
            if (r2 >= r3) goto L9e
            org.json.JSONObject r3 = r15.getJSONObject(r2)     // Catch: java.lang.Throwable -> L69 org.json.JSONException -> L9b
            if (r3 == 0) goto L9b
            r1.put(r3)     // Catch: java.lang.Throwable -> L69
        L9b:
            int r2 = r2 + 1
            goto L8c
        L9e:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = r10.c(r1)     // Catch: java.lang.Throwable -> L69
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L69
            if (r2 != 0) goto Le6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69
            r2.<init>()     // Catch: java.lang.Throwable -> L69
            java.lang.String r3 = "update __sd set "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r2 = r2.append(r12)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r2 = r2.append(r13)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "\" where "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "__ii"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = r1.append(r13)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "\""
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L69
            r2 = r20
            r2.execSQL(r1)     // Catch: java.lang.Throwable -> L69
        Le6:
            if (r0 == 0) goto Lf1
            r0.close()
            goto Lf1
        Lec:
            if (r14 == 0) goto Lf1
            r14.close()
        Lf1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    public boolean e() {
        return this.i.isEmpty();
    }

    public JSONObject a(boolean z) {
        a();
        this.j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a(jSONObject, z);
            b(jSONObject, (String) null);
            a(jSONObject, (String) null);
        } else {
            String strA = a(jSONObject, z);
            if (!TextUtils.isEmpty(strA)) {
                b(jSONObject, strA);
                a(jSONObject, strA);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0087 A[EXC_TOP_SPLITTER, PHI: r2 r5
  0x0087: PHI (r2v5 android.database.sqlite.SQLiteDatabase) = (r2v4 android.database.sqlite.SQLiteDatabase), (r2v6 android.database.sqlite.SQLiteDatabase) binds: [B:28:0x0085, B:35:0x00a1] A[DONT_GENERATE, DONT_INLINE]
  0x0087: PHI (r5v3 org.json.JSONObject) = (r5v2 org.json.JSONObject), (r5v5 org.json.JSONObject) binds: [B:28:0x0085, B:35:0x00a1] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject f() {
        /*
            r14 = this;
            java.lang.String r0 = "__vc"
            java.lang.String r1 = "__av"
            java.util.List<java.lang.String> r2 = r14.l
            boolean r2 = r2.isEmpty()
            r3 = 0
            if (r2 == 0) goto Le
            return r3
        Le:
            android.content.Context r2 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            com.umeng.analytics.pro.i r2 = com.umeng.analytics.pro.i.a(r2)     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            r2.beginTransaction()     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.util.List<java.lang.String> r4 = r14.l     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.lang.String r6 = "__is"
            java.lang.String r8 = "__ii=? "
            r7 = 1
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r9[r5] = r4     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r12 = 0
            r13 = 0
            r7 = 0
            r10 = 0
            r11 = 0
            r4 = r14
            r5 = r6
            r6 = r2
            android.database.Cursor r4 = r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            if (r4 == 0) goto L5d
            boolean r5 = r4.moveToNext()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            if (r5 == 0) goto L5d
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            r5.<init>()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            int r3 = r4.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            int r6 = r4.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r5.put(r1, r3)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r5.put(r0, r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r3 = r5
        L5d:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            if (r4 == 0) goto L65
            r4.close()
        L65:
            if (r2 == 0) goto L6a
            r2.endTransaction()     // Catch: java.lang.Throwable -> L6a
        L6a:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            goto La4
        L74:
            r5 = r3
        L75:
            r3 = r4
            goto L80
        L77:
            r5 = r3
        L78:
            r3 = r4
            goto L97
        L7a:
            r5 = r3
            goto L80
        L7c:
            r5 = r3
            goto L97
        L7e:
            r2 = r3
            r5 = r2
        L80:
            if (r3 == 0) goto L85
            r3.close()
        L85:
            if (r2 == 0) goto L8a
        L87:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L8a
        L8a:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            r3 = r5
            goto La4
        L95:
            r2 = r3
            r5 = r2
        L97:
            android.content.Context r0 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> La5
            com.umeng.analytics.pro.j.a(r0)     // Catch: java.lang.Throwable -> La5
            if (r3 == 0) goto La1
            r3.close()
        La1:
            if (r2 == 0) goto L8a
            goto L87
        La4:
            return r3
        La5:
            r0 = move-exception
            if (r3 == 0) goto Lab
            r3.close()
        Lab:
            if (r2 == 0) goto Lb0
            r2.endTransaction()     // Catch: java.lang.Throwable -> Lb0
        Lb0:
            android.content.Context r1 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.f():org.json.JSONObject");
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x0192 A[EXC_TOP_SPLITTER, PHI: r0 r1
  0x0192: PHI (r0v5 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v3 android.database.Cursor), (r0v8 android.database.Cursor) binds: [B:70:0x0182, B:76:0x0190, B:64:0x0174] A[DONT_GENERATE, DONT_INLINE]
  0x0192: PHI (r1v6 android.database.sqlite.SQLiteDatabase) = 
  (r1v3 android.database.sqlite.SQLiteDatabase)
  (r1v4 android.database.sqlite.SQLiteDatabase)
  (r1v9 android.database.sqlite.SQLiteDatabase)
 binds: [B:70:0x0182, B:76:0x0190, B:64:0x0174] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0195 A[PHI: r0 r1
  0x0195: PHI (r0v4 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v3 android.database.Cursor), (r0v5 android.database.Cursor) binds: [B:70:0x0182, B:76:0x0190, B:77:0x0192] A[DONT_GENERATE, DONT_INLINE]
  0x0195: PHI (r1v5 android.database.sqlite.SQLiteDatabase) = 
  (r1v3 android.database.sqlite.SQLiteDatabase)
  (r1v4 android.database.sqlite.SQLiteDatabase)
  (r1v6 android.database.sqlite.SQLiteDatabase)
 binds: [B:70:0x0182, B:76:0x0190, B:77:0x0192] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(org.json.JSONObject r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0098 A[PHI: r0 r1
  0x0098: PHI (r0v4 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v3 android.database.Cursor), (r0v5 android.database.Cursor) binds: [B:28:0x0085, B:34:0x0093, B:35:0x0095] A[DONT_GENERATE, DONT_INLINE]
  0x0098: PHI (r1v5 android.database.sqlite.SQLiteDatabase) = 
  (r1v3 android.database.sqlite.SQLiteDatabase)
  (r1v4 android.database.sqlite.SQLiteDatabase)
  (r1v6 android.database.sqlite.SQLiteDatabase)
 binds: [B:28:0x0085, B:34:0x0093, B:35:0x0095] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095 A[EXC_TOP_SPLITTER, PHI: r0 r1
  0x0095: PHI (r0v5 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v3 android.database.Cursor), (r0v8 android.database.Cursor) binds: [B:28:0x0085, B:34:0x0093, B:22:0x0077] A[DONT_GENERATE, DONT_INLINE]
  0x0095: PHI (r1v6 android.database.sqlite.SQLiteDatabase) = 
  (r1v3 android.database.sqlite.SQLiteDatabase)
  (r1v4 android.database.sqlite.SQLiteDatabase)
  (r1v9 android.database.sqlite.SQLiteDatabase)
 binds: [B:28:0x0085, B:34:0x0093, B:22:0x0077] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(org.json.JSONObject r13, java.lang.String r14) {
        /*
            r12 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L88
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L88
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L88
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            boolean r2 = android.text.TextUtils.isEmpty(r14)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            if (r2 != 0) goto L2a
            java.lang.String r3 = "__er"
            java.lang.String r6 = "__i=? "
            r2 = 1
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            r2 = 0
            r7[r2] = r14     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            r10 = 0
            r11 = 0
            r5 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            r4 = r1
            android.database.Cursor r14 = r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            goto L39
        L2a:
            java.lang.String r3 = "__er"
            r10 = 0
            r11 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            r4 = r1
            android.database.Cursor r14 = r2.a(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
        L39:
            r0 = r14
            if (r0 == 0) goto L6f
            org.json.JSONArray r14 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            r14.<init>()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
        L41:
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            if (r2 == 0) goto L64
            java.lang.String r2 = "__a"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            if (r3 != 0) goto L41
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            java.lang.String r2 = r12.d(r2)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            r14.put(r3)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            goto L41
        L64:
            int r2 = r14.length()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            if (r2 <= 0) goto L6f
            java.lang.String r2 = "error"
            r13.put(r2, r14)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
        L6f:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L89
            if (r0 == 0) goto L77
            r0.close()
        L77:
            if (r1 == 0) goto L98
            goto L95
        L7a:
            r1 = r0
        L7b:
            android.content.Context r13 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> La2
            com.umeng.analytics.pro.j.a(r13)     // Catch: java.lang.Throwable -> La2
            if (r0 == 0) goto L85
            r0.close()
        L85:
            if (r1 == 0) goto L98
            goto L95
        L88:
            r1 = r0
        L89:
            android.content.Context r13 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> La2
            com.umeng.analytics.pro.j.a(r13)     // Catch: java.lang.Throwable -> La2
            if (r0 == 0) goto L93
            r0.close()
        L93:
            if (r1 == 0) goto L98
        L95:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L98
        L98:
            android.content.Context r13 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r13 = com.umeng.analytics.pro.i.a(r13)
            r13.b()
            return
        La2:
            r13 = move-exception
            if (r0 == 0) goto La8
            r0.close()
        La8:
            if (r1 == 0) goto Lad
            r1.endTransaction()     // Catch: java.lang.Throwable -> Lad
        Lad:
            android.content.Context r14 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r14 = com.umeng.analytics.pro.i.a(r14)
            r14.b()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.b(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0087 A[EXC_TOP_SPLITTER, PHI: r2 r5
  0x0087: PHI (r2v5 android.database.sqlite.SQLiteDatabase) = (r2v4 android.database.sqlite.SQLiteDatabase), (r2v6 android.database.sqlite.SQLiteDatabase) binds: [B:28:0x0085, B:35:0x00a1] A[DONT_GENERATE, DONT_INLINE]
  0x0087: PHI (r5v3 org.json.JSONObject) = (r5v2 org.json.JSONObject), (r5v5 org.json.JSONObject) binds: [B:28:0x0085, B:35:0x00a1] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject g() {
        /*
            r14 = this;
            java.lang.String r0 = "__vc"
            java.lang.String r1 = "__av"
            java.util.List<java.lang.String> r2 = r14.i
            boolean r2 = r2.isEmpty()
            r3 = 0
            if (r2 == 0) goto Le
            return r3
        Le:
            android.content.Context r2 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            com.umeng.analytics.pro.i r2 = com.umeng.analytics.pro.i.a(r2)     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteDatabaseCorruptException -> L95
            r2.beginTransaction()     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.util.List<java.lang.String> r4 = r14.i     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            java.lang.String r6 = "__sd"
            java.lang.String r8 = "__ii=? "
            r7 = 1
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r9[r5] = r4     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            r12 = 0
            r13 = 0
            r7 = 0
            r10 = 0
            r11 = 0
            r4 = r14
            r5 = r6
            r6 = r2
            android.database.Cursor r4 = r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L7c
            if (r4 == 0) goto L5d
            boolean r5 = r4.moveToNext()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            if (r5 == 0) goto L5d
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            r5.<init>()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            int r3 = r4.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            int r6 = r4.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r5.put(r1, r3)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r5.put(r0, r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteDatabaseCorruptException -> L78
            r3 = r5
        L5d:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteDatabaseCorruptException -> L77
            if (r4 == 0) goto L65
            r4.close()
        L65:
            if (r2 == 0) goto L6a
            r2.endTransaction()     // Catch: java.lang.Throwable -> L6a
        L6a:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            goto La4
        L74:
            r5 = r3
        L75:
            r3 = r4
            goto L80
        L77:
            r5 = r3
        L78:
            r3 = r4
            goto L97
        L7a:
            r5 = r3
            goto L80
        L7c:
            r5 = r3
            goto L97
        L7e:
            r2 = r3
            r5 = r2
        L80:
            if (r3 == 0) goto L85
            r3.close()
        L85:
            if (r2 == 0) goto L8a
        L87:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L8a
        L8a:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            r3 = r5
            goto La4
        L95:
            r2 = r3
            r5 = r2
        L97:
            android.content.Context r0 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> La5
            com.umeng.analytics.pro.j.a(r0)     // Catch: java.lang.Throwable -> La5
            if (r3 == 0) goto La1
            r3.close()
        La1:
            if (r2 == 0) goto L8a
            goto L87
        La4:
            return r3
        La5:
            r0 = move-exception
            if (r3 == 0) goto Lab
            r3.close()
        Lab:
            if (r2 == 0) goto Lb0
            r2.endTransaction()     // Catch: java.lang.Throwable -> Lb0
        Lb0:
            android.content.Context r1 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.g():org.json.JSONObject");
    }

    private JSONArray b(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optLong("duration") > 0) {
                jSONArray2.put(jSONObjectOptJSONObject);
            }
        }
        return jSONArray2;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0239 A[PHI: r0 r12 r14
  0x0239: PHI (r0v4 java.lang.String) = (r0v2 java.lang.String), (r0v3 java.lang.String), (r0v5 java.lang.String) binds: [B:95:0x0225, B:101:0x0234, B:102:0x0236] A[DONT_GENERATE, DONT_INLINE]
  0x0239: PHI (r12v5 android.database.sqlite.SQLiteDatabase) = 
  (r12v3 android.database.sqlite.SQLiteDatabase)
  (r12v4 android.database.sqlite.SQLiteDatabase)
  (r12v6 android.database.sqlite.SQLiteDatabase)
 binds: [B:95:0x0225, B:101:0x0234, B:102:0x0236] A[DONT_GENERATE, DONT_INLINE]
  0x0239: PHI (r14v5 android.database.Cursor) = (r14v3 android.database.Cursor), (r14v4 android.database.Cursor), (r14v6 android.database.Cursor) binds: [B:95:0x0225, B:101:0x0234, B:102:0x0236] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0236 A[EXC_TOP_SPLITTER, PHI: r0 r12 r14
  0x0236: PHI (r0v5 java.lang.String) = (r0v2 java.lang.String), (r0v3 java.lang.String), (r0v39 java.lang.String) binds: [B:95:0x0225, B:101:0x0234, B:87:0x0212] A[DONT_GENERATE, DONT_INLINE]
  0x0236: PHI (r12v6 android.database.sqlite.SQLiteDatabase) = 
  (r12v3 android.database.sqlite.SQLiteDatabase)
  (r12v4 android.database.sqlite.SQLiteDatabase)
  (r12v7 android.database.sqlite.SQLiteDatabase)
 binds: [B:95:0x0225, B:101:0x0234, B:87:0x0212] A[DONT_GENERATE, DONT_INLINE]
  0x0236: PHI (r14v6 android.database.Cursor) = (r14v3 android.database.Cursor), (r14v4 android.database.Cursor), (r14v33 android.database.Cursor) binds: [B:95:0x0225, B:101:0x0234, B:87:0x0212] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0222  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(org.json.JSONObject r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e8 A[EXC_TOP_SPLITTER, PHI: r1 r13
  0x00e8: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = (r1v3 android.database.sqlite.SQLiteDatabase), (r1v6 android.database.sqlite.SQLiteDatabase) binds: [B:38:0x00d7, B:44:0x00e6] A[DONT_GENERATE, DONT_INLINE]
  0x00e8: PHI (r13v5 java.lang.Object) = (r13v4 java.lang.Object), (r13v7 java.lang.Object) binds: [B:38:0x00d7, B:44:0x00e6] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r13v0, types: [org.json.JSONObject] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(org.json.JSONObject r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.b(org.json.JSONObject, boolean):java.lang.String");
    }

    public void a(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                try {
                    sQLiteDatabaseA = i.a(d).a();
                    sQLiteDatabaseA.beginTransaction();
                    if (!z2) {
                        int size = this.l.size();
                        int i = 0;
                        if (size > 0) {
                            int i2 = 0;
                            while (i < size) {
                                String str = this.l.get(i);
                                if (str == null) {
                                    i2 = 1;
                                }
                                sQLiteDatabaseA.execSQL("delete from __is where __ii=\"" + str + "\"");
                                i++;
                            }
                            i = i2;
                        }
                        if (i != 0) {
                            sQLiteDatabaseA.execSQL("delete from __is where __ii is null");
                        }
                    } else if (z) {
                        sQLiteDatabaseA.execSQL("delete from __is");
                    }
                    sQLiteDatabaseA.setTransactionSuccessful();
                } catch (Throwable unused) {
                    j.a(d);
                    if (sQLiteDatabaseA != null) {
                    }
                }
            } catch (SQLiteDatabaseCorruptException unused2) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            }
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused4) {
                }
            }
            i.a(d).b();
            throw th;
        }
    }

    public void b(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                if (z2) {
                    if (z) {
                        sQLiteDatabaseA.execSQL("delete from __sd");
                    }
                } else if (this.i.size() > 0) {
                    for (int i = 0; i < this.i.size(); i++) {
                        sQLiteDatabaseA.execSQL("delete from __sd where __ii=\"" + this.i.get(i) + "\"");
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        }
    }

    public void h() {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                if (this.j.size() > 0) {
                    for (int i = 0; i < this.j.size(); i++) {
                        sQLiteDatabaseA.execSQL("delete from __et where rowid=" + this.j.get(i));
                    }
                }
                this.j.clear();
                sQLiteDatabaseA.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        }
    }

    public void i() {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                sQLiteDatabaseA.execSQL("delete from __er");
                sQLiteDatabaseA.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0051 A[EXC_TOP_SPLITTER, PHI: r3
  0x0051: PHI (r3v6 android.database.sqlite.SQLiteDatabase) = 
  (r3v4 android.database.sqlite.SQLiteDatabase)
  (r3v5 android.database.sqlite.SQLiteDatabase)
  (r3v9 android.database.sqlite.SQLiteDatabase)
 binds: [B:9:0x004f, B:14:0x0064, B:6:0x004b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j() {
        /*
            r6 = this;
            java.lang.String r0 = "\""
            java.lang.String r1 = "delete from __et where __i=\""
            java.lang.String r2 = "delete from __er where __i=\""
            java.lang.String r3 = r6.k
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            r4 = 0
            if (r3 != 0) goto L77
            android.content.Context r3 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteDatabaseCorruptException -> L5e
            com.umeng.analytics.pro.i r3 = com.umeng.analytics.pro.i.a(r3)     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteDatabaseCorruptException -> L5e
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteDatabaseCorruptException -> L5e
            r3.beginTransaction()     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.String r2 = r6.k     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            r3.execSQL(r2)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.String r1 = r6.k     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            r3.execSQL(r0)     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            r3.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L4f android.database.sqlite.SQLiteDatabaseCorruptException -> L5f
            if (r3 == 0) goto L54
            goto L51
        L4e:
            r3 = r4
        L4f:
            if (r3 == 0) goto L54
        L51:
            r3.endTransaction()     // Catch: java.lang.Throwable -> L54
        L54:
            android.content.Context r0 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r0 = com.umeng.analytics.pro.i.a(r0)
            r0.b()
            goto L77
        L5e:
            r3 = r4
        L5f:
            android.content.Context r0 = com.umeng.analytics.pro.k.d     // Catch: java.lang.Throwable -> L67
            com.umeng.analytics.pro.j.a(r0)     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L54
            goto L51
        L67:
            r0 = move-exception
            if (r3 == 0) goto L6d
            r3.endTransaction()     // Catch: java.lang.Throwable -> L6d
        L6d:
            android.content.Context r1 = com.umeng.analytics.pro.k.d
            com.umeng.analytics.pro.i r1 = com.umeng.analytics.pro.i.a(r1)
            r1.b()
            throw r0
        L77:
            r6.k = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.j():void");
    }

    public void a(boolean z, String str) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabaseA.execSQL("delete from __er where __i=\"" + str + "\"");
                    sQLiteDatabaseA.execSQL("delete from __et where __i=\"" + str + "\"");
                    this.j.clear();
                    sQLiteDatabaseA.execSQL("delete from __sd where __ii=\"" + str + "\"");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        }
    }

    public void b(String str) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = i.a(d).a();
                sQLiteDatabaseA.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabaseA.execSQL("delete from __is where __ii=\"" + str + "\"");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(d);
                if (sQLiteDatabaseA != null) {
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } finally {
            if (sQLiteDatabaseA != null) {
                try {
                    sQLiteDatabaseA.endTransaction();
                } catch (Throwable unused3) {
                }
            }
            i.a(d).b();
        }
    }

    private void l() {
        try {
            if (TextUtils.isEmpty(e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(d, g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = PreferenceWrapper.getDefault(d).getString(g, null);
                    if (TextUtils.isEmpty(multiProcessSP)) {
                        multiProcessSP = UMUtils.genId();
                    }
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(d, g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String strSubstring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < strSubstring.length(); i++) {
                        char cCharAt = strSubstring.charAt(i);
                        if (Character.isDigit(cCharAt)) {
                            if (Integer.parseInt(Character.toString(cCharAt)) == 0) {
                                sb.append(0);
                            } else {
                                sb.append(10 - Integer.parseInt(Character.toString(cCharAt)));
                            }
                        } else {
                            sb.append(cCharAt);
                        }
                    }
                    e = sb.toString();
                }
                if (TextUtils.isEmpty(e)) {
                    return;
                }
                e += new StringBuilder(e).reverse().toString();
                String multiProcessSP2 = UMUtils.getMultiProcessSP(d, h);
                if (TextUtils.isEmpty(multiProcessSP2)) {
                    UMUtils.setMultiProcessSP(d, h, c(f));
                } else {
                    if (f.equals(d(multiProcessSP2))) {
                        return;
                    }
                    b(true, false);
                    a(true, false);
                    h();
                    i();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), e.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), e.getBytes()));
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT >= 29 && !TextUtils.isEmpty(str)) {
                try {
                    new JSONObject(str);
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> UMStoreManager decrypt failed, return origin data.");
                    return str;
                } catch (Throwable unused2) {
                    return null;
                }
            }
            return null;
        }
    }
}
