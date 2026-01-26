package com.umeng.analytics.pro;

import com.umeng.commonsdk.debug.UMRTLog;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: WeekOnCondition.java */
/* loaded from: classes3.dex */
public class al implements ac {
    private Set<Integer> a;

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return 0L;
    }

    public al(Set<Integer> set) {
        this.a = null;
        this.a = new HashSet(set);
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int i = 7;
            int i2 = calendar.get(7) - 1;
            if (i2 != 0) {
                i = i2;
            }
            Set<Integer> set = this.a;
            if (set != null && set.contains(Integer.valueOf(i))) {
                return true;
            }
            String str = "";
            Iterator<Integer> it = this.a.iterator();
            while (it.hasNext()) {
                str = str + it.next() + ",";
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "WeekOn skipped. day of week: " + i + "; config: " + str);
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
