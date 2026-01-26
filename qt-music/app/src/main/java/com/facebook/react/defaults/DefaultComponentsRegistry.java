package com.facebook.react.defaults;

import com.facebook.react.fabric.ComponentFactory;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: DefaultComponentsRegistry.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0087 ¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/defaults/DefaultComponentsRegistry;", "", "()V", "register", "", "componentFactory", "Lcom/facebook/react/fabric/ComponentFactory;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultComponentsRegistry {
    public static final DefaultComponentsRegistry INSTANCE = new DefaultComponentsRegistry();

    @JvmStatic
    public static final native void register(ComponentFactory componentFactory);

    private DefaultComponentsRegistry() {
    }

    static {
        DefaultSoLoader.INSTANCE.maybeLoadSoLibrary();
    }
}
