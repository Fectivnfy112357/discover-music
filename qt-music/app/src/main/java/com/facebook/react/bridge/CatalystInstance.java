package com.facebook.react.bridge;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import com.umeng.analytics.pro.bm;
import java.util.Collection;
import kotlin.Deprecated;
import kotlin.Metadata;

/* compiled from: CatalystInstance.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H&J\"\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020'2\b\u00101\u001a\u0004\u0018\u000102H'J\b\u00103\u001a\u00020+H&J\u0010\u00104\u001a\u00020+2\u0006\u00105\u001a\u000206H&J\n\u00107\u001a\u0004\u0018\u000108H&J'\u00109\u001a\u0004\u0018\u0001H:\"\b\b\u0000\u0010:*\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H:0=H&¢\u0006\u0002\u0010>J'\u0010?\u001a\u0004\u0018\u0001H:\"\b\b\u0000\u0010:*\u00020\u00172\f\u0010@\u001a\b\u0012\u0004\u0012\u0002H:0=H&¢\u0006\u0002\u0010AJ\u0012\u0010?\u001a\u0004\u0018\u00010\u00172\u0006\u0010B\u001a\u00020'H&J \u0010C\u001a\u00020\u0005\"\b\b\u0000\u0010:*\u00020\u00172\f\u0010@\u001a\b\u0012\u0004\u0012\u0002H:0=H&J\b\u0010D\u001a\u00020\u0005H&J\b\u0010E\u001a\u00020+H'J\u0018\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020H2\u0006\u00101\u001a\u00020IH'J\u0018\u0010J\u001a\u00020+2\u0006\u0010K\u001a\u00020H2\u0006\u0010L\u001a\u00020'H&J\u0010\u0010M\u001a\u00020+2\u0006\u0010,\u001a\u00020-H&J\b\u0010N\u001a\u00020+H&J\u0010\u0010O\u001a\u00020+2\u0006\u0010P\u001a\u000208H&J\u0018\u0010Q\u001a\u00020+2\u0006\u0010R\u001a\u00020'2\u0006\u0010S\u001a\u00020'H'J\u0010\u0010T\u001a\u00020+2\u0006\u0010U\u001a\u00020VH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8gX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8gX¦\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u0004\u0018\u00010#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u0004\u0018\u00010'X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006WÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/bridge/CatalystInstance;", "Lcom/facebook/react/bridge/MemoryPressureListener;", "Lcom/facebook/react/bridge/JSInstance;", "Lcom/facebook/react/bridge/JSBundleLoaderDelegate;", "isDestroyed", "", "()Z", "javaScriptContextHolder", "Lcom/facebook/react/bridge/JavaScriptContextHolder;", "getJavaScriptContextHolder", "()Lcom/facebook/react/bridge/JavaScriptContextHolder;", "jsCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "getJSCallInvokerHolder$annotations", "()V", "getJSCallInvokerHolder", "()Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "nativeMethodCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/NativeMethodCallInvokerHolder;", "getNativeMethodCallInvokerHolder", "()Lcom/facebook/react/turbomodule/core/interfaces/NativeMethodCallInvokerHolder;", "nativeModules", "", "Lcom/facebook/react/bridge/NativeModule;", "getNativeModules", "()Ljava/util/Collection;", "reactQueueConfiguration", "Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "getReactQueueConfiguration", "()Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "runtimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "getRuntimeExecutor", "()Lcom/facebook/react/bridge/RuntimeExecutor;", "runtimeScheduler", "Lcom/facebook/react/bridge/RuntimeScheduler;", "getRuntimeScheduler", "()Lcom/facebook/react/bridge/RuntimeScheduler;", "sourceURL", "", "getSourceURL", "()Ljava/lang/String;", "addBridgeIdleDebugListener", "", "listener", "Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;", "callFunction", bm.e, "method", "arguments", "Lcom/facebook/react/bridge/NativeArray;", "destroy", "extendNativeModules", "modules", "Lcom/facebook/react/bridge/NativeModuleRegistry;", "getFabricUIManager", "Lcom/facebook/react/bridge/UIManager;", "getJSModule", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/react/bridge/JavaScriptModule;", "jsInterface", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/JavaScriptModule;", "getNativeModule", "nativeModuleInterface", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/NativeModule;", "moduleName", "hasNativeModule", "hasRunJSBundle", "initialize", "invokeCallback", "callbackID", "", "Lcom/facebook/react/bridge/NativeArrayInterface;", "registerSegment", "segmentId", "path", "removeBridgeIdleDebugListener", "runJSBundle", "setFabricUIManager", "fabricUIManager", "setGlobalVariable", "propName", "jsonValue", "setTurboModuleRegistry", "turboModuleRegistry", "Lcom/facebook/react/internal/turbomodule/core/interfaces/TurboModuleRegistry;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CatalystInstance extends MemoryPressureListener, JSInstance, JSBundleLoaderDelegate {
    static /* synthetic */ void getJSCallInvokerHolder$annotations() {
    }

    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener);

    void callFunction(String module, String method, NativeArray arguments);

    void destroy();

    void extendNativeModules(NativeModuleRegistry modules);

    UIManager getFabricUIManager();

    @Deprecated(message = "Use ReactContext.getJSCallInvokerHolder instead")
    CallInvokerHolder getJSCallInvokerHolder();

    <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface);

    @Deprecated(message = "Use getRuntimeExecutor() instead.")
    JavaScriptContextHolder getJavaScriptContextHolder();

    NativeMethodCallInvokerHolder getNativeMethodCallInvokerHolder();

    <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface);

    NativeModule getNativeModule(String moduleName);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    RuntimeExecutor getRuntimeExecutor();

    RuntimeScheduler getRuntimeScheduler();

    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface);

    boolean hasRunJSBundle();

    @VisibleForTesting
    void initialize();

    @Override // com.facebook.react.bridge.JSInstance
    void invokeCallback(int callbackID, NativeArrayInterface arguments);

    boolean isDestroyed();

    void registerSegment(int segmentId, String path);

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener);

    void runJSBundle();

    void setFabricUIManager(UIManager fabricUIManager);

    @VisibleForTesting
    void setGlobalVariable(String propName, String jsonValue);

    void setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry);
}
