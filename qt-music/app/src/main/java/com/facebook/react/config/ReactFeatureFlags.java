package com.facebook.react.config;

@Deprecated(since = "Use com.facebook.react.internal.featureflags.ReactNativeFeatureFlags instead.")
/* loaded from: classes2.dex */
public class ReactFeatureFlags {
    public static boolean dispatchPointerEvents = false;

    @Deprecated(forRemoval = true, since = "enableBridgelessArchitecture will be deleted in 0.77, please use DefaultNewArchitectureEntryPoint.load() to enable bridgeless architecture instead.")
    public static boolean enableBridgelessArchitecture = false;
    public static boolean enableCppPropsIteratorSetter = false;

    @Deprecated(forRemoval = true, since = "enableFabricRenderer will be deleted in 0.77, please use DefaultNewArchitectureEntryPoint.load() to enable fabric instead.")
    public static volatile boolean enableFabricRenderer = false;

    @Deprecated(forRemoval = true, since = "useTurboModules will be deleted in 0.77, please use DefaultNewArchitectureEntryPoint.load() to enable TurboModules instead.")
    public static volatile boolean useTurboModules = false;
}
