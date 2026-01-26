package com.umeng.analytics.pro;

/* compiled from: DelayedStartCondition.java */
/* loaded from: classes3.dex */
public class ae implements ac {
    private long a;

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        return true;
    }

    public ae(long j) {
        this.a = j;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean b() {
        return !a();
    }

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return this.a;
    }
}
