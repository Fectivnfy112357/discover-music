package com.umeng.analytics.pro;

import android.content.Context;
import org.repackage.com.meizu.flyme.openidsdk.OpenIdHelper;

/* compiled from: MeizuDeviceIdSupplier.java */
/* loaded from: classes3.dex */
class be implements ax {
    be() {
    }

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) throws NoSuchMethodException, SecurityException {
        if (context == null) {
            return null;
        }
        boolean zA = OpenIdHelper.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return OpenIdHelper.b(context);
        }
        return null;
    }
}
