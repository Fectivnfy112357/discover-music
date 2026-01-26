package com.umeng.cconfig;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.umeng.analytics.pro.aw;
import com.umeng.analytics.pro.g;
import com.umeng.cconfig.b.c;
import com.umeng.cconfig.b.d;
import com.umeng.cconfig.b.e;
import com.umeng.cconfig.c.b;
import com.umeng.cconfig.listener.OnConfigStatusChangedListener;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class UMRemoteConfig {
    private int g;
    private RemoteConfigSettings h;
    private OnConfigStatusChangedListener i;
    private final ReadWriteLock j;
    public static final Pattern a = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]{1,255}");
    private static Context c = null;
    private static Map<String, c> d = new HashMap();
    private static Map<String, c> e = new HashMap();
    private static Map<String, String> f = new HashMap();
    public static boolean b = true;

    static class a {
        private static final UMRemoteConfig a = new UMRemoteConfig(0);
    }

    private UMRemoteConfig() {
        this.j = new ReentrantReadWriteLock(true);
    }

    /* synthetic */ UMRemoteConfig(byte b2) {
        this();
    }

    private void a(Context context, int i) {
        if (context != null) {
            try {
                try {
                    if (i > 0) {
                        try {
                            this.j.writeLock().lock();
                            XmlResourceParser xml = context.getResources().getXml(i);
                            String name = null;
                            String text = null;
                            String text2 = null;
                            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                                if (eventType == 2) {
                                    name = xml.getName();
                                } else if (eventType == 3) {
                                    if ("entry".equals(xml.getName()) && text != null && text2 != null) {
                                        if (a.matcher(text.trim()).matches()) {
                                            f.put(text, text2);
                                            c cVar = new c();
                                            cVar.a = text;
                                            cVar.b = text2;
                                            e.put(text, cVar);
                                            text = null;
                                            text2 = null;
                                        }
                                        if (f.size() > 1000) {
                                            break;
                                        }
                                    }
                                    name = null;
                                } else if (eventType == 4) {
                                    if ("key".equals(name)) {
                                        text = xml.getText();
                                    } else if ("value".equals(name)) {
                                        text2 = xml.getText();
                                    }
                                }
                            }
                        } catch (IOException unused) {
                            ULog.e("jessie", "[xmlLoad] xml load fail");
                        } catch (XmlPullParserException unused2) {
                            ULog.e("jessie", "[xmlLoad] xml load fail");
                        }
                    }
                } finally {
                    this.j.writeLock().unlock();
                }
            } catch (Exception unused3) {
                ULog.e("jessie", "[xmlLoad] xml load fail");
            }
        }
    }

    private void a(d dVar) {
        if (dVar != null) {
            String str = dVar.b;
            try {
                try {
                    this.j.writeLock().lock();
                    if (!TextUtils.isEmpty(str)) {
                        JSONArray jSONArray = new JSONArray(str);
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                            try {
                                String string = "";
                                c cVar = new c();
                                if (jSONObject.has("k")) {
                                    string = jSONObject.getString("k");
                                    cVar.a = string;
                                }
                                if (jSONObject.has("v")) {
                                    cVar.b = jSONObject.getString("v");
                                }
                                if (jSONObject.has("e")) {
                                    cVar.c = jSONObject.getString("e");
                                }
                                if (jSONObject.has("g")) {
                                    cVar.d = jSONObject.getString("g");
                                }
                                if (!TextUtils.isEmpty(string)) {
                                    e.put(jSONObject.getString("k"), cVar);
                                }
                            } catch (Exception unused) {
                                ULog.e("jessie", "[active] new config active false");
                            }
                        }
                    }
                    d.clear();
                    d.putAll(e);
                    e.clear();
                    ULog.i("jessie", "[active] new config active success i");
                } catch (Exception unused2) {
                    ULog.e("jessie", "[active] new config active false");
                }
            } finally {
                this.j.writeLock().unlock();
            }
        } else {
            try {
                this.j.writeLock().lock();
                d.clear();
                d.putAll(e);
                e.clear();
                ULog.i("[active] new config active success ii");
            } catch (Exception unused3) {
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static UMRemoteConfig getInstance() {
        return a.a;
    }

    public static String getVersion() {
        return "1.0.0";
    }

    public void activeFetchConfig() {
        if (!b) {
            MLog.d(aw.i, "remote config disable");
            return;
        }
        Context context = c;
        if (context == null) {
            MLog.d(aw.i, "UMRemoteConfig did not init");
        } else if (!UMUtils.isMainProgress(context)) {
            MLog.d(aw.i, "can not be called in child process");
        } else {
            try {
                new com.umeng.cconfig.d.a(c).run();
            } catch (Exception unused) {
            }
        }
    }

    public String getConfigValue(String str) {
        String str2 = null;
        if (!b) {
            MLog.d(aw.i, "remote config disable");
            return null;
        }
        Context context = c;
        if (context == null) {
            MLog.d(aw.i, "UMRemoteConfig did not init");
            return null;
        }
        if (!UMUtils.isMainProgress(context)) {
            MLog.d(aw.i, "can not be called in child process");
            return null;
        }
        try {
            this.j.readLock().lock();
            c cVar = d.get(str);
            if (cVar != null) {
                str2 = cVar.b;
                if (!TextUtils.isEmpty(cVar.d)) {
                    JSONArray jSONArrayA = e.a(cVar, c);
                    ULog.d("jessie", "[ablog] ablog params : " + jSONArrayA.toString());
                    if (jSONArrayA != null) {
                        com.umeng.cconfig.c.c.a(new b("https://pslog.umeng.com/ablog", jSONArrayA));
                    }
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.j.readLock().unlock();
            throw th;
        }
        this.j.readLock().unlock();
        return str2;
    }

    public void handlerMessage(int i, Object obj, String str) {
        SharedPreferences sharedPreferencesA;
        OnConfigStatusChangedListener onConfigStatusChangedListener;
        try {
            if (i == 1) {
                String str2 = (String) obj;
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    this.j.writeLock().lock();
                    JSONObject jSONObject = new JSONObject(str2);
                    String string = jSONObject.has(com.umeng.ccg.a.a) ? jSONObject.getString(com.umeng.ccg.a.a) : "";
                    String string2 = jSONObject.has("ts") ? jSONObject.getString("ts") : "";
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        com.umeng.cconfig.a.b bVarA = com.umeng.cconfig.a.b.a(c);
                        try {
                            try {
                                try {
                                    try {
                                        bVarA.a();
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("__ts", string2);
                                        contentValues.put(g.d.a.d, string);
                                        contentValues.put("__a", SessionDescription.SUPPORTED_SDP_VERSION);
                                        ULog.i("jessie", "[DbManager] insert timeStamp: " + string2 + " content: " + string + " active: 0");
                                        bVarA.a.insert("__cc", null, contentValues);
                                        bVarA.a.setTransactionSuccessful();
                                        if (bVarA.a != null) {
                                            bVarA.a.endTransaction();
                                        }
                                    } catch (SQLiteDatabaseCorruptException unused) {
                                        ULog.i("jessie", "[DbManager] insert failed");
                                        if (bVarA.a != null) {
                                            bVarA.a.endTransaction();
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        if (bVarA.a != null) {
                                            bVarA.a.endTransaction();
                                        }
                                    } catch (Throwable unused2) {
                                    }
                                    bVarA.b();
                                    throw th;
                                }
                            } catch (Throwable unused3) {
                                if (bVarA.a != null) {
                                    bVarA.a.endTransaction();
                                }
                            }
                        } catch (Throwable unused4) {
                        }
                        bVarA.b();
                        com.umeng.cconfig.a.b.a(c).d();
                        MLog.i(aw.i, "get new config success");
                        RemoteConfigSettings remoteConfigSettings = this.h;
                        if (remoteConfigSettings != null) {
                            if (remoteConfigSettings.isAutoUpdateModeEnabled()) {
                                activeFetchConfig();
                            } else {
                                OnConfigStatusChangedListener onConfigStatusChangedListener2 = this.i;
                                if (onConfigStatusChangedListener2 != null) {
                                    onConfigStatusChangedListener2.onFetchComplete();
                                }
                            }
                        }
                    }
                } finally {
                }
            } else if (i == 2) {
                try {
                    this.j.writeLock().lock();
                    if (TextUtils.isEmpty((String) obj) && !TextUtils.isEmpty(str) && (sharedPreferencesA = com.umeng.cconfig.a.c.a(c)) != null) {
                        SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                        editorEdit.putString("abtest_sp_last_request_data", str);
                        editorEdit.commit();
                    }
                } finally {
                }
            } else {
                if (i != 3) {
                    return;
                }
                try {
                    this.j.writeLock().lock();
                    if (((Boolean) obj).booleanValue()) {
                        a(c, this.g);
                        a(com.umeng.cconfig.a.b.a(c).c());
                        MLog.i(aw.i, "active new config success");
                        if (this.h != null && (onConfigStatusChangedListener = this.i) != null) {
                            onConfigStatusChangedListener.onActiveComplete();
                        }
                    }
                } finally {
                }
            }
        } catch (Exception unused5) {
        }
    }

    public void init(Context context) {
        try {
            if (!b) {
                MLog.d(aw.i, "remote config disable");
                return;
            }
            if (context == null) {
                return;
            }
            if (c == null) {
                c = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(context)) {
                MLog.d(aw.i, "can not be called in child process");
            } else {
                if (this.h == null) {
                    MLog.d(aw.i, "please set RemoteConfigSettings using UMRemoteConfig.getInstance().setConfigSettings");
                    return;
                }
                a(c, this.g);
                a(com.umeng.cconfig.a.b.a(c).c());
                ImprintHandler.getImprintService(c).registImprintCallback(aw.i, new UMImprintChangeCallback() { // from class: com.umeng.cconfig.UMRemoteConfig.1
                    @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
                    public final void onImprintValueChanged(String str, String str2) {
                        SharedPreferences sharedPreferencesA;
                        try {
                            if (aw.i.equals(str)) {
                                ULog.i("jessie", "[imprint] key: " + str + " value: " + str2);
                                String[] strArrSplit = str2.split("@");
                                String str3 = strArrSplit[3];
                                if (TextUtils.isEmpty(str3) || !"1".equals(str3)) {
                                    return;
                                }
                                String str4 = strArrSplit[0];
                                if (TextUtils.isEmpty(str4) || (sharedPreferencesA = com.umeng.cconfig.a.c.a(UMRemoteConfig.c)) == null) {
                                    return;
                                }
                                String string = sharedPreferencesA.getString("cconfig_sp_last_request_time", "");
                                if (!TextUtils.isEmpty(string) && str4.equals(string)) {
                                    ULog.i("jessie", "[imprint] newTimeStamp.equals(oldTimeStamp)");
                                    return;
                                }
                                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                                editorEdit.putString("cconfig_sp_last_request_time", str4);
                                editorEdit.commit();
                                JSONObject jSONObjectA = e.a(UMRemoteConfig.c);
                                if (jSONObjectA != null) {
                                    try {
                                        ULog.i("jessie", "[imprint] send request. body: " + jSONObjectA.toString());
                                        com.umeng.cconfig.c.c.a(new b("https://ucc.umeng.com/v1/fetch", jSONObjectA));
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        } catch (Exception unused2) {
                            ULog.e("jessie", "[imprint] fail");
                        }
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void setConfigSettings(RemoteConfigSettings remoteConfigSettings) {
        if (!b) {
            MLog.d(aw.i, "remote config disable");
        } else {
            if (remoteConfigSettings != null) {
                this.h = remoteConfigSettings;
            }
        }
    }

    public synchronized void setDefaults(int i) {
        if (b) {
            this.g = i;
        } else {
            MLog.d(aw.i, "remote config disable");
        }
    }

    public synchronized void setOnNewConfigfecthed(OnConfigStatusChangedListener onConfigStatusChangedListener) {
        if (!b) {
            MLog.d(aw.i, "remote config disable");
        } else {
            if (onConfigStatusChangedListener != null) {
                this.i = onConfigStatusChangedListener;
            }
        }
    }
}
