package com.facebook.react.devsupport;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: InspectorFlags.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0087 ¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/devsupport/InspectorFlags;", "", "()V", "getFuseboxEnabled", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InspectorFlags {
    public static final InspectorFlags INSTANCE = new InspectorFlags();

    @JvmStatic
    public static final native boolean getFuseboxEnabled();

    private InspectorFlags() {
    }

    static {
        DevSupportSoLoader.staticInit();
    }
}
