package com.umeng.analytics.pro;

import android.text.TextUtils;
import java.util.ArrayList;

/* compiled from: TimePeriodChain.java */
/* loaded from: classes3.dex */
public class aj {
    private String a;
    private ArrayList<ak> b = new ArrayList<>();

    public aj(String str) {
        this.a = "";
        this.a = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a();
    }

    private void a() {
        try {
            if (this.a.contains(",")) {
                for (String str : this.a.split(",")) {
                    if (!TextUtils.isEmpty(str)) {
                        String strTrim = str.trim();
                        if (this.b != null) {
                            this.b.add(new ak(strTrim));
                        }
                    }
                }
                return;
            }
            String str2 = this.a;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String strTrim2 = str2.trim();
            if (this.b != null) {
                this.b.add(new ak(strTrim2));
            }
        } catch (Throwable unused) {
        }
    }

    public boolean a(int i) {
        try {
            ArrayList<ak> arrayList = this.b;
            if (arrayList == null) {
                return false;
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ak akVar = this.b.get(i2);
                if (akVar != null && akVar.a(i)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
