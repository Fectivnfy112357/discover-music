package com.umeng.analytics.pro;

import android.content.Context;
import org.repackage.com.vivo.identifier.IdentifierManager;

/* compiled from: VivoDeviceIdSupplier.java */
/* loaded from: classes3.dex */
public class bi implements ax {
    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean zA = IdentifierManager.a(context);
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return IdentifierManager.b(context);
        }
        return null;
    }
}
