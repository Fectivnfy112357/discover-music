package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

/* loaded from: classes2.dex */
class BridgelessDevSupportManager extends DevSupportManagerBase {
    private final ReactHostImpl mReactHost;

    public BridgelessDevSupportManager(ReactHostImpl reactHostImpl, Context context, String str) {
        super(context.getApplicationContext(), createInstanceDevHelper(reactHostImpl), str, true, null, null, 2, null, null, null, null);
        this.mReactHost = reactHostImpl;
    }

    @Override // com.facebook.react.devsupport.DevSupportManagerBase
    protected String getUniqueTag() {
        return "Bridgeless";
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new DevSupportManagerBase.CallbackWithBundleLoader() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.1
            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                BridgelessDevSupportManager.this.mReactHost.loadBundle(jSBundleLoader).onSuccess(new Continuation<Boolean, Void>() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.1.1
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public Void then(Task<Boolean> task) {
                        if (!task.getResult().equals(Boolean.TRUE)) {
                            return null;
                        }
                        String devServerSplitBundleURL = BridgelessDevSupportManager.this.getDevServerHelper().getDevServerSplitBundleURL(str);
                        ReactContext currentReactContext = BridgelessDevSupportManager.this.mReactHost.getCurrentReactContext();
                        if (currentReactContext != null) {
                            ((HMRClient) currentReactContext.getJSModule(HMRClient.class)).registerBundle(devServerSplitBundleURL);
                        }
                        devSplitBundleCallback.onSuccess();
                        return null;
                    }
                });
            }

            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onError(String str2, Throwable th) {
                devSplitBundleCallback.onError(str2, th);
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        hideRedboxDialog();
        this.mReactHost.reload("BridgelessDevSupportManager.handleReloadJS()");
    }

    private static ReactInstanceDevHelper createInstanceDevHelper(final ReactHostImpl reactHostImpl) {
        return new ReactInstanceDevHelper() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.2
            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void destroyRootView(View view) {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void onJSBundleLoadedFromServer() {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void toggleElementInspector() {
                ReactContext currentReactContext = reactHostImpl.getCurrentReactContext();
                if (currentReactContext != null) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
                }
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public Activity getCurrentActivity() {
                return reactHostImpl.getLastUsedActivity();
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                throw new IllegalStateException("Not implemented for bridgeless mode");
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public View createRootView(String str) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity == null || reactHostImpl.isSurfaceWithModuleNameAttached(str)) {
                    return null;
                }
                ReactSurfaceImpl reactSurfaceImplCreateWithView = ReactSurfaceImpl.createWithView(currentActivity, str, new Bundle());
                reactSurfaceImplCreateWithView.attach(reactHostImpl);
                reactSurfaceImplCreateWithView.start();
                return reactSurfaceImplCreateWithView.getView();
            }
        };
    }
}
