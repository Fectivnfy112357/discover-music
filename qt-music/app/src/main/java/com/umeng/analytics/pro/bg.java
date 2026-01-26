package com.umeng.analytics.pro;

import android.content.Context;
import org.repackage.com.heytap.openid.sdk.OpenIDSDK;

/* compiled from: OppoDeviceIdSupplier.java */
/* loaded from: classes3.dex */
public class bg implements ax {
    private boolean a = false;

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!this.a) {
            OpenIDSDK.a(context);
            this.a = true;
        }
        boolean zA = OpenIDSDK.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return OpenIDSDK.c(context);
        }
        return null;
    }
}
