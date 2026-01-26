package com.umeng.analytics.pro;

import android.content.SharedPreferences;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;

/* compiled from: IntervalPeriodCondition.java */
/* loaded from: classes3.dex */
public class ag implements ac {
    private String a;
    private long b;

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return 0L;
    }

    public ag(String str, long j) {
        this.a = str;
        this.b = j;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        try {
            String str = at.b + this.a;
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA == null) {
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - sharedPreferencesA.getLong(str, 0L);
            if (jCurrentTimeMillis > this.b * 1000) {
                return true;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "internal period skipped. elapse: " + jCurrentTimeMillis + "; config: " + (this.b * 1000));
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean b() {
        return !a();
    }
}
