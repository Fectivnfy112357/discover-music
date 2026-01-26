package com.facebook.yoga;

/* loaded from: classes2.dex */
public abstract class YogaConfigFactory {
    public static YogaConfig create() {
        return new YogaConfigJNIFinalizer();
    }
}
