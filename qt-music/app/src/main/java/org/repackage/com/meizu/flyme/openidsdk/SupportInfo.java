package org.repackage.com.meizu.flyme.openidsdk;

import android.text.TextUtils;

/* loaded from: classes3.dex */
class SupportInfo {
    String a;
    Boolean b;

    SupportInfo() {
    }

    void a(boolean z) {
        this.b = Boolean.valueOf(z);
    }

    boolean a() {
        return this.b != null;
    }

    boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(this.a, str);
    }

    void b(String str) {
        this.a = str;
    }

    boolean b() {
        Boolean bool = this.b;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
