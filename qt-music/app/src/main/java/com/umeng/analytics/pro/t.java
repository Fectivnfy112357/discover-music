package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DefconProcesser.java */
/* loaded from: classes3.dex */
public class t {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private final long e;

    private t() {
        this.e = 60000L;
    }

    /* compiled from: DefconProcesser.java */
    private static class a {
        public static final t a = new t();

        private a() {
        }
    }

    public static t a() {
        return a.a;
    }

    public int a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    private void a(JSONObject jSONObject, boolean z) {
        if (!z && jSONObject.has(f.n)) {
            jSONObject.remove(f.n);
        }
        if (jSONObject.has(f.L)) {
            jSONObject.remove(f.L);
        }
        if (jSONObject.has("error")) {
            jSONObject.remove("error");
        }
        if (jSONObject.has("ekv")) {
            jSONObject.remove("ekv");
        }
        if (jSONObject.has(f.Z)) {
            jSONObject.remove(f.Z);
        }
        if (jSONObject.has(f.L)) {
            jSONObject.remove(f.L);
        }
        if (jSONObject.has("userlevel")) {
            jSONObject.remove("userlevel");
        }
    }

    public void a(JSONObject jSONObject, Context context) throws JSONException {
        int iA = a(context);
        if (iA == 1) {
            a(jSONObject, true);
            k.a(context).b(false, true);
        } else {
            if (iA == 2) {
                jSONObject.remove(f.n);
                try {
                    jSONObject.put(f.n, b());
                } catch (Exception unused) {
                }
                a(jSONObject, true);
                k.a(context).b(false, true);
                return;
            }
            if (iA == 3) {
                a(jSONObject, false);
                k.a(context).b(false, true);
            }
        }
    }

    public void b(JSONObject jSONObject, Context context) {
        int iA = a(context);
        if (iA == 1) {
            if (jSONObject.has(f.L)) {
                jSONObject.remove(f.L);
            }
            if (jSONObject.has(f.n)) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(f.n);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has(f.aA)) {
                            jSONObject2.remove(f.aA);
                        }
                        if (jSONObject2.has(f.aB)) {
                            jSONObject2.remove(f.aB);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
            k.a(context).a(false, true);
            return;
        }
        if (iA == 2) {
            if (jSONObject.has(f.L)) {
                jSONObject.remove(f.L);
            }
            if (jSONObject.has(f.n)) {
                jSONObject.remove(f.n);
            }
            try {
                jSONObject.put(f.n, c());
            } catch (Exception unused2) {
            }
            k.a(context).a(false, true);
            return;
        }
        if (iA == 3) {
            if (jSONObject.has(f.L)) {
                jSONObject.remove(f.L);
            }
            jSONObject.remove(f.n);
            k.a(context).a(false, true);
        }
    }

    private JSONArray b() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", aa.a().a(UMGlobalContext.getAppContext(null)));
            jSONObject.put(f.p, jCurrentTimeMillis);
            jSONObject.put(f.q, jCurrentTimeMillis + 60000);
            jSONObject.put("duration", 60000L);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        return jSONArray;
    }

    private JSONArray c() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", aa.a().d(UMGlobalContext.getAppContext(null)));
            jSONObject.put(f.p, jCurrentTimeMillis);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        return jSONArray;
    }
}
