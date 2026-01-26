package com.facebook.react.internal.featureflags;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: ReactNativeFeatureFlagsForTests.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsForTests;", "", "()V", "setUp", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeFeatureFlagsForTests {
    public static final ReactNativeFeatureFlagsForTests INSTANCE = new ReactNativeFeatureFlagsForTests();

    private ReactNativeFeatureFlagsForTests() {
    }

    public final void setUp() {
        ReactNativeFeatureFlags.INSTANCE.setAccessorProvider$ReactAndroid_release(new Function0<ReactNativeFeatureFlagsAccessor>() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsForTests.setUp.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReactNativeFeatureFlagsAccessor invoke() {
                return new ReactNativeFeatureFlagsLocalAccessor();
            }
        });
    }
}
