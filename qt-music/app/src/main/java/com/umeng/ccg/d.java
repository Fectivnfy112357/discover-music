package com.umeng.ccg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.pro.ab;
import com.umeng.analytics.pro.ac;
import com.umeng.analytics.pro.ad;
import com.umeng.analytics.pro.ae;
import com.umeng.analytics.pro.af;
import com.umeng.analytics.pro.ag;
import com.umeng.analytics.pro.ah;
import com.umeng.analytics.pro.aj;
import com.umeng.analytics.pro.al;
import com.umeng.analytics.pro.ar;
import com.umeng.analytics.pro.at;
import com.umeng.analytics.pro.av;
import com.umeng.analytics.pro.aw;
import com.umeng.ccg.c;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Monitor.java */
/* loaded from: classes3.dex */
public class d implements c.a {
    private static final String a = "iucc";
    private static final String b = aw.b().b(aw.C);
    private static JSONObject c = null;
    private static final String[] d = {com.umeng.ccg.a.f, com.umeng.ccg.a.g, com.umeng.ccg.a.h};
    private static ArrayList<ac> e = null;
    private static ArrayList<ac> f = null;
    private static ArrayList<ac> g = null;
    private static c j = new c();
    private volatile String h = "";
    private Map<String, a> i = new HashMap();

    /* compiled from: Monitor.java */
    public class a {
        private JSONArray b;
        private String c;

        public JSONArray a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }

        public a(JSONArray jSONArray, String str) {
            this.b = jSONArray;
            this.c = str;
        }
    }

    /* compiled from: Monitor.java */
    public static class c extends BroadcastReceiver {
        public long a(ArrayList<ac> arrayList) {
            if (arrayList != null && arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    ac acVar = arrayList.get(i);
                    if (acVar instanceof ae) {
                        return ((ae) acVar).c();
                    }
                }
            }
            return 0L;
        }

        public boolean b(ArrayList<ac> arrayList) {
            if (arrayList == null || arrayList.size() <= 0) {
                return false;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).b()) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_SCREEN_ON");
                    if (b(d.e)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_on event.");
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.n, d.a(), null, a(d.e) * 1000);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_on event.");
                    }
                }
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_SCREEN_OFF");
                    if (b(d.f)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_off event.");
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.o, d.a(), null, a(d.f) * 1000);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_off event.");
                    }
                }
                if (action.equals("android.intent.action.USER_PRESENT")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_USER_PRESENT");
                    if (b(d.g)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_unlock event.");
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.p, d.a(), null, a(d.g) * 1000);
                        return;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_unlock event.");
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Context context, String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(str);
        context.registerReceiver(j, intentFilter);
    }

    /* compiled from: Monitor.java */
    private static class b {
        private static final d a = new d();

        private b() {
        }
    }

    public static d a() {
        return b.a;
    }

    public void a(Context context) {
        com.umeng.ccg.c.a(context, com.umeng.ccg.c.e, a(), null);
    }

    private boolean a(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("code")) {
            return false;
        }
        try {
            if (200 == Integer.valueOf(jSONObject.optInt("code")).intValue() && jSONObject.has(com.umeng.ccg.a.a)) {
                return jSONObject.has("ts");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private long b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("ts")) {
            try {
                return jSONObject.optLong("ts");
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    synchronized JSONObject b(Context context) {
        File filesDir;
        String str;
        FileInputStream fileInputStreamOpenFileInput;
        JSONObject jSONObject = null;
        try {
            filesDir = context.getFilesDir();
            str = b;
        } catch (Throwable unused) {
        }
        if (!new File(filesDir, str).exists()) {
            return null;
        }
        try {
            fileInputStreamOpenFileInput = context.openFileInput(str);
            try {
                JSONObject jSONObject2 = new JSONObject(new String(av.a(HelperUtils.readStreamToByteArray(fileInputStreamOpenFileInput), UMConfigure.sAppkey.getBytes())));
                try {
                    ar.a(fileInputStreamOpenFileInput);
                } catch (Throwable unused2) {
                }
                jSONObject = jSONObject2;
            } catch (Throwable unused3) {
                ar.a(fileInputStreamOpenFileInput);
                return jSONObject;
            }
        } catch (Throwable unused4) {
            fileInputStreamOpenFileInput = null;
        }
        return jSONObject;
    }

    private synchronized void a(Context context, JSONObject jSONObject, String str) {
        long jB;
        byte[] bArrA;
        try {
            jB = b(jSONObject);
            bArrA = av.a(jSONObject.toString().getBytes(), UMConfigure.sAppkey.getBytes());
        } catch (Throwable unused) {
        }
        if (bArrA != null && bArrA.length > 1) {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir(), b));
            try {
                fileOutputStream.write(bArrA);
                fileOutputStream.flush();
                ar.a(fileOutputStream);
                a(context, str, jB);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "saveConfigFile success.");
            } catch (Throwable th) {
                ar.a(fileOutputStream);
                throw th;
            }
        }
    }

    private void c(Context context) {
        ImprintHandler.getImprintService(context).registImprintCallback(a, new UMImprintChangeCallback() { // from class: com.umeng.ccg.d.1
            @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
            public void onImprintValueChanged(String str, String str2) {
                com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.g, d.a(), str2);
            }
        });
    }

    private void a(String str, ac acVar) {
        if (com.umeng.ccg.a.f.equalsIgnoreCase(str)) {
            if (e == null) {
                e = new ArrayList<>();
            }
            e.add(acVar);
        }
        if (com.umeng.ccg.a.g.equalsIgnoreCase(str)) {
            if (f == null) {
                f = new ArrayList<>();
            }
            f.add(acVar);
        }
        if (com.umeng.ccg.a.h.equalsIgnoreCase(str)) {
            if (g == null) {
                g = new ArrayList<>();
            }
            g.add(acVar);
        }
    }

    private ab a(String str, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray;
        String str2;
        String str3;
        ab abVar;
        JSONArray jSONArrayOptJSONArray2;
        if (jSONObject != null && (jSONObject instanceof JSONObject)) {
            try {
                if (jSONObject.has(com.umeng.ccg.a.i) && (jSONArrayOptJSONArray = jSONObject.optJSONArray(com.umeng.ccg.a.i)) != null && jSONArrayOptJSONArray.length() > 0) {
                    JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.get(0);
                    boolean zHas = jSONObject2.has(com.umeng.ccg.a.j);
                    boolean zHas2 = jSONObject2.has(com.umeng.ccg.a.m);
                    boolean zHas3 = jSONObject2.has(com.umeng.ccg.a.n);
                    if (!zHas || !zHas2 || !zHas3) {
                        return null;
                    }
                    try {
                        int iOptInt = jSONObject2.optInt(com.umeng.ccg.a.j);
                        long jOptLong = jSONObject2.optLong(com.umeng.ccg.a.m);
                        long jOptLong2 = jSONObject2.optLong(com.umeng.ccg.a.n);
                        String strOptString = jSONObject2.optString(com.umeng.ccg.a.o);
                        ArrayList arrayList = new ArrayList();
                        if (jSONObject2.has(com.umeng.ccg.a.k)) {
                            JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray(com.umeng.ccg.a.k);
                            str2 = com.umeng.ccg.a.t;
                            HashSet hashSet = new HashSet();
                            if (jSONArrayOptJSONArray3 != null) {
                                str3 = com.umeng.ccg.a.r;
                                int i = 0;
                                for (int length = jSONArrayOptJSONArray3.length(); i < length; length = length) {
                                    hashSet.add(Integer.valueOf(jSONArrayOptJSONArray3.getInt(i)));
                                    i++;
                                }
                            } else {
                                str3 = com.umeng.ccg.a.r;
                            }
                            if (hashSet.size() > 0) {
                                al alVar = new al(hashSet);
                                if (Arrays.asList(d).contains(str)) {
                                    a(str, alVar);
                                } else {
                                    arrayList.add(alVar);
                                }
                            }
                        } else {
                            str2 = com.umeng.ccg.a.t;
                            str3 = com.umeng.ccg.a.r;
                        }
                        if (jSONObject2.has(com.umeng.ccg.a.l)) {
                            String strOptString2 = jSONObject2.optString(com.umeng.ccg.a.l);
                            if (!TextUtils.isEmpty(strOptString2)) {
                                aj ajVar = new aj(strOptString2);
                                HashSet hashSet2 = new HashSet();
                                for (int i2 = 1; i2 <= 24; i2++) {
                                    if (ajVar.a(i2)) {
                                        hashSet2.add(Integer.valueOf(i2));
                                    }
                                }
                                if (hashSet2.size() > 0) {
                                    af afVar = new af(hashSet2);
                                    if (Arrays.asList(d).contains(str)) {
                                        a(str, afVar);
                                    } else {
                                        arrayList.add(afVar);
                                    }
                                }
                            }
                        }
                        arrayList.add(new ah(iOptInt));
                        ag agVar = new ag(str, jOptLong);
                        String[] strArr = d;
                        if (Arrays.asList(strArr).contains(str)) {
                            a(str, agVar);
                        } else {
                            arrayList.add(agVar);
                        }
                        ae aeVar = new ae(jOptLong2);
                        if (Arrays.asList(strArr).contains(str)) {
                            a(str, aeVar);
                            arrayList.add(aeVar);
                        } else {
                            arrayList.add(aeVar);
                        }
                        if (com.umeng.ccg.a.e.equals(str)) {
                            abVar = new ad(str, arrayList);
                        } else {
                            abVar = new ab(str, arrayList);
                        }
                        try {
                            abVar.a(strOptString);
                            String str4 = "";
                            String str5 = str3;
                            if (jSONObject.has(str5) && (jSONArrayOptJSONArray2 = jSONObject.optJSONArray(str5)) != null && (jSONArrayOptJSONArray2 instanceof JSONArray)) {
                                Map<String, a> map = this.i;
                                if (map != null && !map.containsKey(str)) {
                                    this.i.put(str, new a(new JSONArray(jSONArrayOptJSONArray2.toString()), strOptString));
                                }
                                int length2 = jSONArrayOptJSONArray2.length();
                                for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                                    str4 = str4 + jSONArrayOptJSONArray2.getString(i3);
                                    if (i3 < length2 - 1) {
                                        str4 = str4 + ",";
                                    }
                                }
                            }
                            abVar.b(str4);
                            if (com.umeng.ccg.a.e.equals(str) && (abVar instanceof ad)) {
                                String str6 = str2;
                                if (jSONObject2.has(str6)) {
                                    ((ad) abVar).d(jSONObject2.optString(str6));
                                }
                                if (jSONObject2.has(com.umeng.ccg.a.s)) {
                                    ((ad) abVar).c(jSONObject2.optString(com.umeng.ccg.a.s));
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        return abVar;
                    } catch (Throwable unused2) {
                        return null;
                    }
                }
            } catch (Throwable unused3) {
            }
        }
        return null;
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject != null && (jSONObject instanceof JSONObject) && jSONObject.has(com.umeng.ccg.a.a)) {
            try {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(com.umeng.ccg.a.a);
                ab abVarA = jSONObjectOptJSONObject.has(com.umeng.ccg.a.b) ? a(com.umeng.ccg.a.b, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.b)) : null;
                ab abVarA2 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.c) ? a(com.umeng.ccg.a.c, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.c)) : null;
                ab abVarA3 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.d) ? a(com.umeng.ccg.a.d, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.d)) : null;
                ab abVarA4 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.e) ? a(com.umeng.ccg.a.e, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.e)) : null;
                ab abVarA5 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.f) ? a(com.umeng.ccg.a.f, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.f)) : null;
                ab abVarA6 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.g) ? a(com.umeng.ccg.a.g, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.g)) : null;
                ab abVarA7 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.h) ? a(com.umeng.ccg.a.h, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.h)) : null;
                ArrayList arrayList = new ArrayList();
                if (abVarA != null) {
                    arrayList.add(abVarA);
                }
                if (abVarA2 != null) {
                    arrayList.add(abVarA2);
                }
                if (abVarA3 != null) {
                    arrayList.add(abVarA3);
                }
                if (abVarA4 != null) {
                    arrayList.add(abVarA4);
                }
                if (abVarA5 != null) {
                    arrayList.add(abVarA5);
                }
                if (abVarA6 != null) {
                    arrayList.add(abVarA6);
                }
                if (abVarA7 != null) {
                    arrayList.add(abVarA7);
                }
                com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.l, a(), arrayList);
            } catch (Throwable unused) {
            }
        }
    }

    private void a(Context context, String str, long j2) {
        SharedPreferences sharedPreferencesA;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String[] strArrSplit = str.split("@");
            if (strArrSplit.length != 4 || (sharedPreferencesA = at.a(context)) == null) {
                return;
            }
            long j3 = Long.parseLong(strArrSplit[0]);
            String str2 = strArrSplit[1];
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putLong(at.c, j2);
            editorEdit.putLong(at.d, j3);
            editorEdit.putString(at.e, str2).commit();
            UMRTLog.i(UMRTLog.RTLOG_TAG, "updateTsS1S2 : ts = " + j2 + "; s1 = " + j3 + "; s2 = " + str2);
        } catch (Throwable unused) {
        }
    }

    private Long d(Context context) {
        try {
            SharedPreferences sharedPreferencesA = at.a(context);
            if (sharedPreferencesA == null) {
                return 0L;
            }
            return Long.valueOf(sharedPreferencesA.getLong(at.d, 0L));
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private String e(Context context) {
        try {
            SharedPreferences sharedPreferencesA = at.a(context);
            return sharedPreferencesA != null ? sharedPreferencesA.getString(at.e, "") : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private void a(String str) {
        try {
            String[] strArrSplit = str.split("@");
            if (strArrSplit.length != 4) {
                return;
            }
            long j2 = Long.parseLong(strArrSplit[0]);
            String str2 = strArrSplit[1];
            if (!TextUtils.isEmpty(this.h)) {
                String[] strArrSplit2 = this.h.split("@");
                if (strArrSplit2.length == 2) {
                    long j3 = Long.parseLong(strArrSplit2[0]);
                    String str3 = strArrSplit2[1];
                    if (j3 == j2 && str3.equalsIgnoreCase(str2)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "重复的iucc S1 and S2, 忽略本次更新，不发起fetch。");
                        return;
                    }
                }
            }
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                if (sharedPreferencesA.getLong(at.c, 0L) != j2) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "local config ts != iuccS1, send FETCH_NEW_CONFIG msg.");
                    this.h = String.valueOf(j2) + "@" + str2;
                    com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 101, a(), str);
                } else {
                    d(UMGlobalContext.getAppContext());
                    if (e(UMGlobalContext.getAppContext()).equalsIgnoreCase(str2)) {
                        return;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "local S2 != iuccS2, send FETCH_NEW_CONFIG msg.");
                    this.h = String.valueOf(j2) + "@" + str2;
                    com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 101, a(), str);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void b(String str) {
        String str2 = at.b + str;
        SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
        if (sharedPreferencesA != null) {
            sharedPreferencesA.edit().putLong(str2, System.currentTimeMillis()).commit();
        }
    }

    private boolean e() {
        SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
        if (sharedPreferencesA != null) {
            String string = sharedPreferencesA.getString(at.f, "");
            if (TextUtils.isEmpty(string)) {
                f();
                return false;
            }
            try {
                if (!ar.a().keySet().equals(ar.a(new JSONObject(string)).keySet())) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private void f() {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                sharedPreferencesA.edit().putString(at.f, new JSONObject(ar.a()).toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    private void a(boolean z) {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                if (z) {
                    editorEdit.putString(at.g, "1").commit();
                } else {
                    editorEdit.putString(at.g, "").commit();
                }
            }
        } catch (Throwable unused) {
        }
    }

    private boolean g() {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                return !TextUtils.isEmpty(sharedPreferencesA.getString(at.g, ""));
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02fc A[Catch: all -> 0x04d8, TryCatch #3 {all -> 0x04d8, blocks: (B:10:0x003c, B:12:0x0047, B:14:0x0051, B:17:0x0089, B:19:0x0094, B:21:0x009e, B:24:0x00d6, B:26:0x00e1, B:28:0x00eb, B:30:0x0120, B:32:0x0124, B:34:0x0142, B:36:0x0159, B:38:0x0169, B:40:0x016f, B:41:0x017d, B:43:0x0183, B:44:0x0191, B:46:0x0197, B:47:0x01a7, B:49:0x01b0, B:53:0x01bb, B:55:0x01c7, B:57:0x01cd, B:60:0x020b, B:58:0x01f3, B:61:0x0214, B:62:0x021d, B:64:0x0224, B:66:0x0228, B:70:0x0233, B:72:0x0244, B:75:0x0256, B:77:0x0261, B:78:0x0287, B:79:0x028a, B:81:0x0291, B:83:0x0295, B:95:0x02d8, B:97:0x02dc, B:99:0x02e2, B:101:0x02ea, B:103:0x02f0, B:105:0x02f8, B:106:0x02fc, B:107:0x0300, B:109:0x0306, B:110:0x0326, B:144:0x03fb, B:146:0x0409, B:148:0x040f, B:149:0x041b, B:155:0x0432, B:157:0x043e, B:158:0x044d, B:166:0x048c, B:167:0x04b0, B:169:0x04b6), top: B:180:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03c1 A[Catch: all -> 0x03d8, TRY_LEAVE, TryCatch #2 {all -> 0x03d8, blocks: (B:126:0x03bc, B:128:0x03c1, B:137:0x03d5, B:133:0x03cc, B:135:0x03d1), top: B:178:0x0387 }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03d5 A[Catch: all -> 0x03d8, PHI: r3 r14
  0x03d5: PHI (r3v19 ??) = (r3v57 ??), (r3v58 ??) binds: [B:136:0x03d3, B:129:0x03c3] A[DONT_GENERATE, DONT_INLINE]
  0x03d5: PHI (r14v3 org.json.JSONObject) = (r14v2 org.json.JSONObject), (r14v4 org.json.JSONObject) binds: [B:136:0x03d3, B:129:0x03c3] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {all -> 0x03d8, blocks: (B:126:0x03bc, B:128:0x03c1, B:137:0x03d5, B:133:0x03cc, B:135:0x03d1), top: B:178:0x0387 }] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15, types: [int] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v18, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v19, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v26, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    @Override // com.umeng.ccg.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.Object r18, int r19) {
        /*
            Method dump skipped, instructions count: 1280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.ccg.d.a(java.lang.Object, int):void");
    }
}
