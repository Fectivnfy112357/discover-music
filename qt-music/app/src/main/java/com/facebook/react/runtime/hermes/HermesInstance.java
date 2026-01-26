package com.facebook.react.runtime.hermes;

import com.facebook.jni.HybridData;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.runtime.JSRuntimeFactory;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: HermesInstance.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/runtime/hermes/HermesInstance;", "Lcom/facebook/react/runtime/JSRuntimeFactory;", "()V", "reactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "allocInOldGenBeforeTTI", "", "(Lcom/facebook/react/fabric/ReactNativeConfig;Z)V", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HermesInstance extends JSRuntimeFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    protected static final native HybridData initHybrid(Object obj, boolean z);

    public HermesInstance(ReactNativeConfig reactNativeConfig, boolean z) {
        super(initHybrid(reactNativeConfig, z));
    }

    public HermesInstance() {
        this(null, false);
    }

    /* compiled from: HermesInstance.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0085 ¨\u0006\b"}, d2 = {"Lcom/facebook/react/runtime/hermes/HermesInstance$Companion;", "", "()V", "initHybrid", "Lcom/facebook/jni/HybridData;", "reactNativeConfig", "allocInOldGenBeforeTTI", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        protected final HybridData initHybrid(Object reactNativeConfig, boolean allocInOldGenBeforeTTI) {
            return HermesInstance.initHybrid(reactNativeConfig, allocInOldGenBeforeTTI);
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("hermesinstancejni");
    }
}
