package com.facebook.react.uimanager;

import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ComponentNameResolverBinding.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0087 ¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/ComponentNameResolverBinding;", "", "()V", "install", "", "runtimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "componentNameResolver", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ComponentNameResolverBinding {
    public static final ComponentNameResolverBinding INSTANCE = new ComponentNameResolverBinding();

    @JvmStatic
    public static final native void install(RuntimeExecutor runtimeExecutor, Object componentNameResolver);

    private ComponentNameResolverBinding() {
    }

    static {
        SoLoader.loadLibrary("uimanagerjni");
    }
}
