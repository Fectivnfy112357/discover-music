package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: SessionIdGenerateServiceImpl.java */
/* loaded from: classes3.dex */
class z implements y {
    private long a = AnalyticsConfig.kContinueSessionMillis;

    z() {
    }

    @Override // com.umeng.analytics.pro.y
    public void a(long j) {
        this.a = j;
    }

    @Override // com.umeng.analytics.pro.y
    public long a() {
        return this.a;
    }

    @Override // com.umeng.analytics.pro.y
    public String a(Context context) {
        String appkey = UMUtils.getAppkey(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(jCurrentTimeMillis).append(appkey).append("02:00:00:00:00:00");
        return UMUtils.MD5(sb.toString());
    }

    @Override // com.umeng.analytics.pro.y
    public boolean a(long j, long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        return (j == 0 || jCurrentTimeMillis - j >= this.a) && j2 > 0 && jCurrentTimeMillis - j2 > this.a;
    }

    @Override // com.umeng.analytics.pro.y
    public void a(Context context, String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(context).edit();
            editorEdit.putString("session_id", str);
            editorEdit.putLong(w.b, 0L);
            editorEdit.putLong(w.e, jCurrentTimeMillis);
            editorEdit.putLong(w.f, 0L);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }
}
