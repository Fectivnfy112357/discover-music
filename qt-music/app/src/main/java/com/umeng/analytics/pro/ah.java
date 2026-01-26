package com.umeng.analytics.pro;

import android.content.SharedPreferences;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;

/* compiled from: LaunchTimesCondition.java */
/* loaded from: classes3.dex */
public class ah implements ac {
    private int a;

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return 0L;
    }

    public ah(int i) {
        this.a = i;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        long j = 0;
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                j = sharedPreferencesA.getLong(at.a, 0L);
                if (j >= this.a) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "launch times skipped. times: " + j + " ; config: " + this.a);
        return false;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean b() {
        return !a();
    }
}
