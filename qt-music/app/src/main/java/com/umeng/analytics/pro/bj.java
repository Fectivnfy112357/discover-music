package com.umeng.analytics.pro;

import android.content.Context;
import org.repackage.com.miui.deviceid.IdentifierManager;

/* compiled from: XiaomiDeviceIdSupplier.java */
/* loaded from: classes3.dex */
class bj implements ax {
    bj() {
    }

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean zA = IdentifierManager.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return IdentifierManager.b(context);
        }
        return null;
    }
}
