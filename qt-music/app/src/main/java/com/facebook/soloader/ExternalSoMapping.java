package com.facebook.soloader;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface ExternalSoMapping {
    void invokeJniOnload(String str);

    @Nullable
    String mapLibName(String str);
}
