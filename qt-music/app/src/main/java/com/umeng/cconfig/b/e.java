package com.umeng.cconfig.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class e {
    public static synchronized JSONArray a(c cVar, Context context) {
        JSONArray jSONArray = null;
        if (cVar != null) {
            if (context != null) {
                try {
                    a aVar = new a();
                    String uMId = UMUtils.getUMId(context);
                    if (TextUtils.isEmpty(uMId)) {
                        return null;
                    }
                    aVar.i = uMId;
                    String appkey = UMUtils.getAppkey(context);
                    if (TextUtils.isEmpty(appkey)) {
                        return null;
                    }
                    aVar.h = appkey;
                    aVar.l = Long.valueOf(System.currentTimeMillis());
                    aVar.j = Integer.valueOf(Integer.parseInt(cVar.c));
                    aVar.k = Integer.valueOf(Integer.parseInt(cVar.d));
                    aVar.m = cVar.a;
                    aVar.n = cVar.b;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray2 = new JSONArray();
                    try {
                        try {
                            jSONObject.put(a.a, aVar.h);
                            jSONObject.put(a.e, aVar.l);
                            jSONObject.put(a.c, aVar.j);
                            jSONObject.put(a.d, aVar.k);
                            jSONObject.put(a.b, aVar.i);
                            jSONObject.put(a.f, aVar.m);
                            jSONObject.put(a.g, aVar.n);
                            jSONArray2.put(0, jSONObject);
                            List<JSONObject> listB = b(context);
                            if (listB.size() > 0) {
                                for (int i = 1; i <= listB.size(); i++) {
                                    jSONArray2.put(i, listB.get(i - 1));
                                }
                            }
                            try {
                                SharedPreferences sharedPreferencesA = com.umeng.cconfig.a.c.a(context);
                                if (sharedPreferencesA != null) {
                                    SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                                    editorEdit.putString("abtest_sp_last_request_data", "");
                                    editorEdit.commit();
                                }
                            } catch (Exception unused) {
                            }
                            return jSONArray2;
                        } catch (Exception unused2) {
                            jSONArray = jSONArray2;
                            ULog.i("jessie", "[getUpdateAbEventLogParam] error ii");
                            return jSONArray;
                        }
                    } catch (Exception unused3) {
                        ULog.i("jessie", "[getUpdateAbEventLogParam] error i");
                        jSONArray = jSONArray2;
                    }
                } catch (Exception unused4) {
                }
            }
        }
        return jSONArray;
    }

    public static synchronized JSONObject a(Context context) {
        JSONObject jSONObject = null;
        try {
            b bVar = new b();
            String uMId = UMUtils.getUMId(context);
            if (TextUtils.isEmpty(uMId)) {
                return null;
            }
            bVar.n = uMId;
            String appkey = UMUtils.getAppkey(context);
            if (TextUtils.isEmpty(appkey)) {
                return null;
            }
            bVar.o = appkey;
            bVar.p = UMUtils.getAppVersionName(context);
            bVar.q = "9.3.3";
            bVar.r = UMUtils.getChannel(context);
            bVar.s = new StringBuilder().append(Build.VERSION.SDK_INT).toString();
            bVar.t = Build.BRAND;
            bVar.u = Build.MODEL;
            String[] localeInfo = DeviceConfig.getLocaleInfo(context);
            bVar.v = localeInfo[1];
            bVar.y = localeInfo[0];
            int[] resolutionArray = DeviceConfig.getResolutionArray(context);
            bVar.x = Integer.valueOf(resolutionArray[1]);
            bVar.w = Integer.valueOf(resolutionArray[0]);
            String strImprintProperty = "";
            if (context != null) {
                strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "install_datetime", "");
            }
            bVar.z = strImprintProperty;
            try {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(b.a, bVar.n);
                    jSONObject2.put(b.c, bVar.p);
                    jSONObject2.put(b.b, bVar.o);
                    jSONObject2.put(b.d, bVar.q);
                    jSONObject2.put(b.e, bVar.r);
                    jSONObject2.put(b.f, bVar.s);
                    jSONObject2.put(b.g, bVar.t);
                    jSONObject2.put(b.h, bVar.u);
                    jSONObject2.put(b.k, bVar.x);
                    jSONObject2.put(b.j, bVar.w);
                    jSONObject2.put(b.l, bVar.y);
                    jSONObject2.put(b.i, bVar.v);
                    jSONObject2.put(b.m, bVar.z);
                    return jSONObject2;
                } catch (JSONException unused) {
                    jSONObject = jSONObject2;
                    ULog.i("jessie", "[getCloudConfigParam] error i");
                    return jSONObject;
                } catch (Exception unused2) {
                    jSONObject = jSONObject2;
                    ULog.i("jessie", "[getCloudConfigParam] error ii");
                    return jSONObject;
                }
            } catch (JSONException unused3) {
            }
        } catch (Exception unused4) {
        }
    }

    private static List<JSONObject> b(Context context) throws JSONException {
        ArrayList arrayList = new ArrayList();
        try {
            SharedPreferences sharedPreferencesA = com.umeng.cconfig.a.c.a(context);
            if (sharedPreferencesA != null) {
                String string = sharedPreferencesA.getString("abtest_sp_last_request_data", "");
                if (!TextUtils.isEmpty(string)) {
                    try {
                        JSONArray jSONArray = new JSONArray(string);
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            String string2 = jSONArray.getString(i);
                            if (!TextUtils.isEmpty(string2)) {
                                arrayList.add(new JSONObject(string2));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }
}
