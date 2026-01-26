package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: EmptyReactNativeConfig.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096 J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0096 J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0096 J\u0011\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\u0096 J\t\u0010\u000e\u001a\u00020\u0004H\u0082 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/fabric/EmptyReactNativeConfig;", "Lcom/facebook/react/fabric/ReactNativeConfig;", "()V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getBool", "", "param", "", "getDouble", "", "getInt64", "", "getString", "initHybrid", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmptyReactNativeConfig implements ReactNativeConfig {
    private static final Companion Companion = new Companion(null);
    private final HybridData mHybridData = initHybrid();

    private final native HybridData initHybrid();

    @Override // com.facebook.react.fabric.ReactNativeConfig
    public native boolean getBool(String param);

    @Override // com.facebook.react.fabric.ReactNativeConfig
    public native double getDouble(String param);

    @Override // com.facebook.react.fabric.ReactNativeConfig
    public native long getInt64(String param);

    @Override // com.facebook.react.fabric.ReactNativeConfig
    public native String getString(String param);

    /* compiled from: EmptyReactNativeConfig.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/facebook/react/fabric/EmptyReactNativeConfig$Companion;", "", "()V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
    }
}
