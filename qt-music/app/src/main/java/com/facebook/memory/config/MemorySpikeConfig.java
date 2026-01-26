package com.facebook.memory.config;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: MemorySpikeConfig.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/memory/config/MemorySpikeConfig;", "", "()V", "_avoidObjectsHashCode", "", "avoidObjectsHashCode", "setAvoidObjectsHashCode", "", "fbcore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MemorySpikeConfig {
    public static final MemorySpikeConfig INSTANCE = new MemorySpikeConfig();
    private static boolean _avoidObjectsHashCode;

    private MemorySpikeConfig() {
    }

    @JvmStatic
    public static final void setAvoidObjectsHashCode(boolean avoidObjectsHashCode) {
        _avoidObjectsHashCode = avoidObjectsHashCode;
    }

    @JvmStatic
    public static final boolean avoidObjectsHashCode() {
        return _avoidObjectsHashCode;
    }
}
