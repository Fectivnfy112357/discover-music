package com.umeng.analytics.pro;

import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: AplAction.java */
/* loaded from: classes3.dex */
public class ad extends ab {
    private String a;
    private String b;

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.a = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public ad(String str, ArrayList<ac> arrayList) {
        super(str, arrayList);
        this.a = "";
        this.b = "";
    }

    @Override // com.umeng.analytics.pro.ab, com.umeng.analytics.pro.ai
    public JSONObject a(String str, JSONObject jSONObject) {
        JSONObject jSONObjectA = super.a(str, jSONObject);
        if (jSONObjectA != null) {
            try {
                jSONObjectA.put(com.umeng.ccg.a.s, this.a);
                jSONObjectA.put(com.umeng.ccg.a.t, this.b);
            } catch (Throwable unused) {
            }
        }
        return jSONObjectA;
    }
}
