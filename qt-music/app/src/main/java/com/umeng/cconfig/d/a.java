package com.umeng.cconfig.d;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.umeng.cconfig.UMRemoteConfig;
import com.umeng.cconfig.a.b;
import com.umeng.cconfig.b.d;

/* loaded from: classes3.dex */
public final class a implements Runnable {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    private boolean a() {
        boolean zA;
        try {
            d dVarE = b.a(this.a).e();
            if (!SessionDescription.SUPPORTED_SDP_VERSION.equals(dVarE.c)) {
                return false;
            }
            String str = dVarE.a;
            if (TextUtils.isEmpty(str) || !(zA = b.a(this.a).a(str))) {
                return false;
            }
            b.a(this.a).b(str);
            return zA;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            UMRemoteConfig.getInstance().handlerMessage(3, Boolean.valueOf(a()), null);
        } catch (Exception unused) {
        }
    }
}
