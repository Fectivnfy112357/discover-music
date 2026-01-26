package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ZIDManager {
    public static ZIDManager d;
    public boolean a = false;
    public boolean b = false;
    public boolean c;

    public class a implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ IZIDCompletionCallback b;

        public a(Context context, IZIDCompletionCallback iZIDCompletionCallback) {
            this.a = context;
            this.b = iZIDCompletionCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strA = ZIDManager.a(ZIDManager.this, this.a);
            if (TextUtils.isEmpty(strA)) {
                IZIDCompletionCallback iZIDCompletionCallback = this.b;
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onFailure("1002", "获取zid失败");
                    return;
                }
                return;
            }
            IZIDCompletionCallback iZIDCompletionCallback2 = this.b;
            if (iZIDCompletionCallback2 != null) {
                iZIDCompletionCallback2.onSuccess(strA);
            }
        }
    }

    public class b implements Runnable {
        public final /* synthetic */ Context a;

        public b(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZIDManager.this.b(this.a);
        }
    }

    public class c implements Runnable {
        public final /* synthetic */ Context a;

        public c(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZIDManager.a(ZIDManager.this, this.a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[Catch: all -> 0x00db, TryCatch #1 {all -> 0x00db, blocks: (B:6:0x0010, B:21:0x0056, B:22:0x005b, B:26:0x006b, B:28:0x008c, B:30:0x00a1, B:32:0x00b6, B:33:0x00b9, B:35:0x00c5, B:36:0x00c8, B:38:0x00d4, B:39:0x00d7, B:19:0x0051), top: B:50:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008c A[Catch: all -> 0x00db, TryCatch #1 {all -> 0x00db, blocks: (B:6:0x0010, B:21:0x0056, B:22:0x005b, B:26:0x006b, B:28:0x008c, B:30:0x00a1, B:32:0x00b6, B:33:0x00b9, B:35:0x00c5, B:36:0x00c8, B:38:0x00d4, B:39:0x00d7, B:19:0x0051), top: B:50:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.String a(com.umeng.umzid.ZIDManager r10, android.content.Context r11) {
        /*
            boolean r0 = r10.a
            r1 = 0
            if (r0 == 0) goto L7
            goto Le1
        L7:
            r0 = 1
            r10.a = r0
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            r3 = 0
            java.lang.String r4 = com.umeng.umzid.Spy.getID()     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r5 = "z"
            r2.put(r5, r4)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r5 = com.umeng.umzid.d.e(r11)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r6 = "mc"
            r2.put(r6, r5)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r6 = com.umeng.umzid.d.f(r11)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r7 = "o"
            r2.put(r7, r6)     // Catch: java.lang.Throwable -> Ldb
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L4e
            r7.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r8 = "vpn_pxy"
            boolean r9 = com.umeng.umzid.d.i(r11)     // Catch: java.lang.Throwable -> L4c
            r7.put(r8, r9)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r8 = "wifi_pxy"
            boolean r9 = com.umeng.umzid.d.j(r11)     // Catch: java.lang.Throwable -> L4c
            r7.put(r8, r9)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r8 = "double"
            boolean r9 = com.umeng.umzid.d.g(r11)     // Catch: java.lang.Throwable -> L4c
            r7.put(r8, r9)     // Catch: java.lang.Throwable -> L4c
            goto L54
        L4c:
            r8 = move-exception
            goto L51
        L4e:
            r7 = move-exception
            r8 = r7
            r7 = r1
        L51:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> Ldb
        L54:
            if (r7 == 0) goto L5b
            java.lang.String r8 = "anti"
            r2.put(r8, r7)     // Catch: java.lang.Throwable -> Ldb
        L5b:
            r10.a(r11, r2)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r7 = com.umeng.umzid.d.b(r11)     // Catch: java.lang.Throwable -> Ldb
            int r8 = r7.length()     // Catch: java.lang.Throwable -> Ldb
            if (r8 <= 0) goto L69
            goto L6b
        L69:
            java.lang.String r7 = "https://utoken.umeng.com"
        L6b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ldb
            r8.<init>()     // Catch: java.lang.Throwable -> Ldb
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r8 = "/anti/postZdata"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = com.umeng.umzid.a.a(r7, r2)     // Catch: java.lang.Throwable -> Ldb
            boolean r7 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> Ldb
            if (r7 != 0) goto Ld7
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Ldb
            r7.<init>(r2)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = "suc"
            boolean r2 = r7.optBoolean(r2)     // Catch: java.lang.Throwable -> Ldb
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> Ldb
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> Ldb
            if (r2 != r0) goto Ld7
            com.umeng.umzid.d.f(r11, r4)     // Catch: java.lang.Throwable -> Ldb
            com.umeng.umzid.d.a(r11, r5)     // Catch: java.lang.Throwable -> Ldb
            com.umeng.umzid.d.b(r11, r6)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r0 = "aaid"
            java.lang.String r1 = r7.optString(r0)     // Catch: java.lang.Throwable -> Ldb
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> Ldb
            if (r0 != 0) goto Lb9
            com.umeng.umzid.d.e(r11, r1)     // Catch: java.lang.Throwable -> Ldb
        Lb9:
            java.lang.String r0 = "uabc"
            java.lang.String r0 = r7.optString(r0)     // Catch: java.lang.Throwable -> Ldb
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Ldb
            if (r2 != 0) goto Lc8
            com.umeng.umzid.d.d(r11, r0)     // Catch: java.lang.Throwable -> Ldb
        Lc8:
            java.lang.String r0 = "resetToken"
            java.lang.String r0 = r7.optString(r0)     // Catch: java.lang.Throwable -> Ldb
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Ldb
            if (r2 != 0) goto Ld7
            com.umeng.umzid.d.c(r11, r0)     // Catch: java.lang.Throwable -> Ldb
        Ld7:
            r10.a(r11)     // Catch: java.lang.Throwable -> Ldb
            goto Ldf
        Ldb:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.lang.Throwable -> Le2
        Ldf:
            r10.a = r3
        Le1:
            return r1
        Le2:
            r11 = move-exception
            r10.a = r3
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.ZIDManager.a(com.umeng.umzid.ZIDManager, android.content.Context):java.lang.String");
    }

    public static void configureDomain(Context context, String str) {
        SharedPreferences sharedPreferencesA;
        SharedPreferences.Editor editorEdit;
        String strB = d.b(str);
        if (context == null || strB == null || TextUtils.isEmpty(strB) || (sharedPreferencesA = com.umeng.umzid.a.a(context)) == null || (editorEdit = sharedPreferencesA.edit()) == null) {
            return;
        }
        editorEdit.putString("inputDomain", strB).commit();
    }

    public static synchronized ZIDManager getInstance() {
        if (d == null) {
            d = new ZIDManager();
        }
        return d;
    }

    public static String getSDKVersion() {
        return "1.8.0";
    }

    public final void a(Context context) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod;
        Object objInvoke;
        Method declaredMethod2;
        try {
            Class<?> cls = Class.forName("com.uyumao.sdk.UYMManager");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0])) == null || (objInvoke = declaredMethod.invoke(cls, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("init", Context.class)) == null) {
                return;
            }
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(objInvoke, context);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f4 A[Catch: all -> 0x016f, TryCatch #3 {all -> 0x016f, blocks: (B:14:0x0033, B:16:0x0039, B:18:0x0041, B:20:0x0051, B:22:0x0057, B:24:0x0063, B:26:0x0069, B:28:0x0079, B:30:0x007f, B:32:0x008b, B:34:0x0091, B:37:0x00b4, B:39:0x00ba, B:40:0x00be, B:42:0x00c4, B:57:0x00f4, B:58:0x00f9, B:62:0x0106, B:64:0x0127, B:66:0x013c, B:68:0x014f, B:69:0x0152, B:71:0x015c, B:72:0x015f, B:74:0x0169, B:75:0x016c, B:55:0x00ef), top: B:85:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0127 A[Catch: all -> 0x016f, TryCatch #3 {all -> 0x016f, blocks: (B:14:0x0033, B:16:0x0039, B:18:0x0041, B:20:0x0051, B:22:0x0057, B:24:0x0063, B:26:0x0069, B:28:0x0079, B:30:0x007f, B:32:0x008b, B:34:0x0091, B:37:0x00b4, B:39:0x00ba, B:40:0x00be, B:42:0x00c4, B:57:0x00f4, B:58:0x00f9, B:62:0x0106, B:64:0x0127, B:66:0x013c, B:68:0x014f, B:69:0x0152, B:71:0x015c, B:72:0x015f, B:74:0x0169, B:75:0x016c, B:55:0x00ef), top: B:85:0x0033 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.ZIDManager.b(android.content.Context):java.lang.String");
    }

    public synchronized String getZID(Context context) {
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        String strD = d.d(applicationContext);
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        com.umeng.umzid.c.a(new c(applicationContext));
        return "";
    }

    public synchronized void init(Context context, String str, IZIDCompletionCallback iZIDCompletionCallback) {
        SharedPreferences sharedPreferencesA;
        SharedPreferences.Editor editorEdit;
        boolean zH = d.h(context);
        this.c = zH;
        if (zH) {
            if (context == null) {
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onFailure("1001", "传入参数Context为null");
                }
                return;
            }
            if (TextUtils.isEmpty(str)) {
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onFailure("1003", "传入参数appkey为空");
                }
                return;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null && str != null && !TextUtils.isEmpty(str) && (sharedPreferencesA = com.umeng.umzid.a.a(applicationContext)) != null && (editorEdit = sharedPreferencesA.edit()) != null) {
                editorEdit.putString("appkey", str).commit();
            }
            String strD = d.d(applicationContext);
            if (strD == null || TextUtils.isEmpty(strD)) {
                com.umeng.umzid.c.a(new a(applicationContext, iZIDCompletionCallback));
            } else {
                com.umeng.umzid.c.a(new b(applicationContext));
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onSuccess(strD);
                }
            }
            SharedPreferences sharedPreferencesA2 = com.umeng.umzid.a.a(context);
            if (TextUtils.isEmpty(sharedPreferencesA2 != null ? sharedPreferencesA2.getString("uuid", "") : "")) {
                String string = "";
                SharedPreferences sharedPreferencesA3 = com.umeng.umzid.a.a(context);
                try {
                    string = UUID.randomUUID().toString();
                } catch (Throwable unused) {
                }
                if (sharedPreferencesA3 != null) {
                    sharedPreferencesA3.edit().putString("uuid", string).commit();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.json.JSONObject a(android.content.Context r10, org.json.JSONObject r11) throws org.json.JSONException, java.lang.NoSuchMethodException, android.content.res.Resources.NotFoundException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.ZIDManager.a(android.content.Context, org.json.JSONObject):org.json.JSONObject");
    }
}
