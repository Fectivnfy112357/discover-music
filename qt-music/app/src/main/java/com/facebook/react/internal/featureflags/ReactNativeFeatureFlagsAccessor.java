package com.facebook.react.internal.featureflags;

import com.umeng.analytics.pro.f;
import kotlin.Metadata;

/* compiled from: ReactNativeFeatureFlagsAccessor.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "dangerouslyReset", "", "override", f.M, "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ReactNativeFeatureFlagsAccessor extends ReactNativeFeatureFlagsProvider {
    void dangerouslyReset();

    void override(ReactNativeFeatureFlagsProvider provider);
}
