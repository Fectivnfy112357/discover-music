package com.umeng.analytics.pro;

import android.text.TextUtils;
import com.umeng.ccg.ActionInfo;
import com.umeng.ccg.CcgAgent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: Action.java */
/* loaded from: classes3.dex */
public class ab implements ai {
    private String a;
    private ArrayList<ac> b;
    private String c;
    private String d;
    private String e;
    private String f;

    public ab(String str, ArrayList<ac> arrayList) {
        this.a = null;
        new ArrayList();
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.a = str;
        this.b = arrayList;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    private String c(String str) {
        String[] strArrSplit = str.split(",");
        String str2 = "";
        if (strArrSplit.length <= 0) {
            return "";
        }
        ArrayList<String> forbidSdkArray = CcgAgent.getForbidSdkArray(this.a);
        if (forbidSdkArray != null && forbidSdkArray.size() > 0) {
            this.f = forbidSdkArray.toString();
            for (String str3 : strArrSplit) {
                if (CcgAgent.getActionInfo(str3) != null && !forbidSdkArray.contains(str3)) {
                    return str3;
                }
            }
            return "";
        }
        for (String str4 : strArrSplit) {
            ActionInfo actionInfo = CcgAgent.getActionInfo(str4);
            if (actionInfo != null) {
                String[] supportAction = actionInfo.getSupportAction(UMGlobalContext.getAppContext());
                if (supportAction.length > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= supportAction.length) {
                            break;
                        }
                        if (this.a.equals(supportAction[i])) {
                            str2 = str4;
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                } else {
                    continue;
                }
            }
        }
        return str2;
    }

    @Override // com.umeng.analytics.pro.ai
    public JSONObject a(String str, JSONObject jSONObject) {
        try {
            int size = this.b.size();
            if (size == 0) {
                return null;
            }
            for (int i = 0; i < size; i++) {
                if (this.b.get(i).b()) {
                    return null;
                }
            }
            if (CcgAgent.hasRegistedActionInfo() && !TextUtils.isEmpty(this.d)) {
                String strC = c(this.d);
                this.e = strC;
                if (!TextUtils.isEmpty(strC)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "采集项：" + this.a + "; 选中Module: " + this.e + "; sdk: " + this.d);
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "采集项：" + this.a + "; 未选中可用Module ; sdk: " + this.d);
                }
            }
            ac acVar = this.b.get(size - 1);
            if (acVar == null || !(acVar instanceof ae)) {
                return null;
            }
            long jC = acVar.c();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("actionName", this.a);
                jSONObject2.put(com.umeng.ccg.a.r, this.d);
                jSONObject2.put(com.umeng.ccg.a.o, this.c);
                jSONObject2.put("delay", jC);
                jSONObject2.put(com.umeng.ccg.a.p, this.e);
                jSONObject2.put(com.umeng.ccg.a.q, this.f);
            } catch (Throwable unused) {
            }
            return jSONObject2;
        } catch (Throwable unused2) {
            return null;
        }
    }
}
