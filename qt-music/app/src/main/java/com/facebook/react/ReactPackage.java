package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface ReactPackage {
    List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext);

    List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext);

    @Nullable
    @UnstableReactNativeAPI
    default NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        return null;
    }
}
