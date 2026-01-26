package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.common.mapbuffer.MapBufferSoLoader;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindingImpl.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0006\u001a\u00020\u0007H\u0096 J\t\u0010\b\u001a\u00020\u0007H\u0096 J\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096 J9\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0082 J8\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u001dH\u0016J\u0011\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0096 J\u0011\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0096 JQ\u0010$\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0096 J\u0011\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020&H\u0096 J!\u00101\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0096 Ja\u00106\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0096 J\u0011\u00107\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0096 J\t\u00108\u001a\u00020\u0007H\u0082 J\b\u00109\u001a\u00020\u0007H\u0016J\u0011\u0010:\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0096 R\u0016\u0010\u0003\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006<"}, d2 = {"Lcom/facebook/react/fabric/BindingImpl;", "Lcom/facebook/react/fabric/Binding;", "()V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "drainPreallocateViewsQueue", "", "driveCxxAnimations", "getInspectorDataForInstance", "Lcom/facebook/react/bridge/ReadableNativeMap;", "eventEmitterWrapper", "Lcom/facebook/react/fabric/events/EventEmitterWrapper;", "installFabricUIManager", "runtimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "runtimeScheduler", "Lcom/facebook/react/bridge/RuntimeScheduler;", "uiManager", "Lcom/facebook/react/fabric/FabricUIManager;", "eventBeatManager", "Lcom/facebook/react/fabric/events/EventBeatManager;", "componentsRegistry", "Lcom/facebook/react/fabric/ComponentFactory;", "reactNativeConfig", "", "register", "fabricUIManager", "componentFactory", "Lcom/facebook/react/fabric/ReactNativeConfig;", "registerSurface", "surfaceHandler", "Lcom/facebook/react/fabric/SurfaceHandlerBinding;", "reportMount", "surfaceId", "", "setConstraints", ViewProps.MIN_WIDTH, "", ViewProps.MAX_WIDTH, ViewProps.MIN_HEIGHT, ViewProps.MAX_HEIGHT, "offsetX", "offsetY", "isRTL", "", "doLeftAndRightSwapInRTL", "setPixelDensity", "pointScaleFactor", "startSurface", "moduleName", "", "initialProps", "Lcom/facebook/react/bridge/NativeMap;", "startSurfaceWithConstraints", "stopSurface", "uninstallFabricUIManager", "unregister", "unregisterSurface", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BindingImpl implements Binding {
    private static final Companion Companion = new Companion(null);
    private final HybridData mHybridData = Companion.initHybrid();

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native HybridData initHybrid();

    private final native void installFabricUIManager(RuntimeExecutor runtimeExecutor, RuntimeScheduler runtimeScheduler, FabricUIManager uiManager, EventBeatManager eventBeatManager, ComponentFactory componentsRegistry, Object reactNativeConfig);

    private final native void uninstallFabricUIManager();

    @Override // com.facebook.react.fabric.Binding
    public native void drainPreallocateViewsQueue();

    @Override // com.facebook.react.fabric.Binding
    public native void driveCxxAnimations();

    @Override // com.facebook.react.fabric.Binding
    public native ReadableNativeMap getInspectorDataForInstance(EventEmitterWrapper eventEmitterWrapper);

    @Override // com.facebook.react.fabric.Binding
    public native void registerSurface(SurfaceHandlerBinding surfaceHandler);

    @Override // com.facebook.react.fabric.Binding
    public native void reportMount(int surfaceId);

    @Override // com.facebook.react.fabric.Binding
    public native void setConstraints(int surfaceId, float minWidth, float maxWidth, float minHeight, float maxHeight, float offsetX, float offsetY, boolean isRTL, boolean doLeftAndRightSwapInRTL);

    @Override // com.facebook.react.fabric.Binding
    public native void setPixelDensity(float pointScaleFactor);

    @Override // com.facebook.react.fabric.Binding
    public native void startSurface(int surfaceId, String moduleName, NativeMap initialProps);

    @Override // com.facebook.react.fabric.Binding
    public native void startSurfaceWithConstraints(int surfaceId, String moduleName, NativeMap initialProps, float minWidth, float maxWidth, float minHeight, float maxHeight, float offsetX, float offsetY, boolean isRTL, boolean doLeftAndRightSwapInRTL);

    @Override // com.facebook.react.fabric.Binding
    public native void stopSurface(int surfaceId);

    @Override // com.facebook.react.fabric.Binding
    public native void unregisterSurface(SurfaceHandlerBinding surfaceHandler);

    @Override // com.facebook.react.fabric.Binding
    public void register(RuntimeExecutor runtimeExecutor, RuntimeScheduler runtimeScheduler, FabricUIManager fabricUIManager, EventBeatManager eventBeatManager, ComponentFactory componentFactory, ReactNativeConfig reactNativeConfig) {
        Intrinsics.checkNotNullParameter(runtimeExecutor, "runtimeExecutor");
        Intrinsics.checkNotNullParameter(runtimeScheduler, "runtimeScheduler");
        Intrinsics.checkNotNullParameter(fabricUIManager, "fabricUIManager");
        Intrinsics.checkNotNullParameter(eventBeatManager, "eventBeatManager");
        Intrinsics.checkNotNullParameter(componentFactory, "componentFactory");
        Intrinsics.checkNotNullParameter(reactNativeConfig, "reactNativeConfig");
        fabricUIManager.setBinding(this);
        installFabricUIManager(runtimeExecutor, runtimeScheduler, fabricUIManager, eventBeatManager, componentFactory, reactNativeConfig);
        setPixelDensity(PixelUtil.getDisplayMetricDensity());
    }

    @Override // com.facebook.react.fabric.Binding
    public void unregister() {
        uninstallFabricUIManager();
    }

    /* compiled from: BindingImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0083 ¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/fabric/BindingImpl$Companion;", "", "()V", "initHybrid", "Lcom/facebook/jni/HybridData;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final HybridData initHybrid() {
            return BindingImpl.initHybrid();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
        MapBufferSoLoader.staticInit();
    }
}
