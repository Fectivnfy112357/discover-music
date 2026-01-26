package com.facebook.react.runtime.cxxreactpackage;

import com.facebook.jni.HybridData;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import kotlin.Metadata;

/* compiled from: CxxReactPackage.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/runtime/cxxreactpackage/CxxReactPackage;", "", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "mHybridData", "getMHybridData$annotations", "()V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@UnstableReactNativeAPI
/* loaded from: classes2.dex */
public abstract class CxxReactPackage {
    private HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    protected CxxReactPackage(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
