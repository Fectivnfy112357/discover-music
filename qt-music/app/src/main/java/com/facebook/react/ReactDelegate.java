package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.devsupport.ReleaseDevSupportManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/* loaded from: classes2.dex */
public class ReactDelegate {
    private final Activity mActivity;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    private boolean mFabricEnabled;
    private Bundle mLaunchOptions;
    private final String mMainComponentName;
    private ReactHost mReactHost;
    private ReactNativeHost mReactNativeHost;
    private ReactRootView mReactRootView;
    private ReactSurface mReactSurface;

    @Deprecated
    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle) {
        this.mFabricEnabled = ReactFeatureFlags.enableFabricRenderer;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactNativeHost = reactNativeHost;
    }

    public ReactDelegate(Activity activity, ReactHost reactHost, String str, Bundle bundle) {
        this.mFabricEnabled = ReactFeatureFlags.enableFabricRenderer;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactHost = reactHost;
    }

    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle, boolean z) {
        boolean z2 = ReactFeatureFlags.enableFabricRenderer;
        this.mFabricEnabled = z;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactNativeHost = reactNativeHost;
    }

    private DevSupportManager getDevSupportManager() {
        ReactHost reactHost;
        if (ReactFeatureFlags.enableBridgelessArchitecture && (reactHost = this.mReactHost) != null && reactHost.getDevSupportManager() != null) {
            return this.mReactHost.getDevSupportManager();
        }
        if (!getReactNativeHost().hasInstance() || getReactNativeHost().getReactInstanceManager() == null) {
            return null;
        }
        return getReactNativeHost().getReactInstanceManager().getDevSupportManager();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onHostResume() {
        if (!(this.mActivity instanceof DefaultHardwareBackBtnHandler)) {
            throw new ClassCastException("Host Activity does not implement DefaultHardwareBackBtnHandler");
        }
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            ReactHost reactHost = this.mReactHost;
            Activity activity = this.mActivity;
            reactHost.onHostResume(activity, (DefaultHardwareBackBtnHandler) activity);
        } else if (getReactNativeHost().hasInstance()) {
            ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();
            Activity activity2 = this.mActivity;
            reactInstanceManager.onHostResume(activity2, (DefaultHardwareBackBtnHandler) activity2);
        }
    }

    public void onUserLeaveHint() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onHostLeaveHint(this.mActivity);
        } else if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onUserLeaveHint(this.mActivity);
        }
    }

    public void onHostPause() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onHostPause(this.mActivity);
        } else if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(this.mActivity);
        }
    }

    public void onHostDestroy() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            ReactSurface reactSurface = this.mReactSurface;
            if (reactSurface != null) {
                reactSurface.stop();
                this.mReactSurface = null;
            }
            this.mReactHost.onHostDestroy(this.mActivity);
            return;
        }
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(this.mActivity);
        }
    }

    public boolean onBackPressed() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onBackPressed();
            return true;
        }
        if (!getReactNativeHost().hasInstance()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().onBackPressed();
        return true;
    }

    public boolean onNewIntent(Intent intent) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onNewIntent(intent);
            return true;
        }
        if (!getReactNativeHost().hasInstance()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent, boolean z) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onActivityResult(this.mActivity, i, i2, intent);
        } else if (getReactNativeHost().hasInstance() && z) {
            getReactNativeHost().getReactInstanceManager().onActivityResult(this.mActivity, i, i2, intent);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onWindowFocusChange(z);
        } else if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onWindowFocusChange(z);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onConfigurationChanged((Context) Assertions.assertNotNull(this.mActivity));
        } else if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onConfigurationChanged((Context) Assertions.assertNotNull(this.mActivity), configuration);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        ReactHost reactHost;
        if (i != 90) {
            return false;
        }
        if ((!ReactFeatureFlags.enableBridgelessArchitecture || (reactHost = this.mReactHost) == null || reactHost.getDevSupportManager() == null) && !(getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport())) {
            return false;
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyLongPress(int i) {
        ReactHost reactHost;
        if (i != 90) {
            return false;
        }
        if (ReactFeatureFlags.enableBridgelessArchitecture && (reactHost = this.mReactHost) != null) {
            DevSupportManager devSupportManager = reactHost.getDevSupportManager();
            if (devSupportManager == null || (devSupportManager instanceof ReleaseDevSupportManager)) {
                return false;
            }
            devSupportManager.showDevOptionsDialog();
            return true;
        }
        if (!getReactNativeHost().hasInstance() || !getReactNativeHost().getUseDeveloperSupport()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
        return true;
    }

    public void reload() {
        DevSupportManager devSupportManager = getDevSupportManager();
        if (devSupportManager == null) {
            return;
        }
        if (devSupportManager instanceof ReleaseDevSupportManager) {
            if (ReactFeatureFlags.enableBridgelessArchitecture) {
                ReactHost reactHost = this.mReactHost;
                if (reactHost != null) {
                    reactHost.reload("ReactDelegate.reload()");
                    return;
                }
                return;
            }
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactDelegate$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$reload$0();
                }
            });
            return;
        }
        devSupportManager.handleReloadJS();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reload$0() {
        if (!this.mReactNativeHost.hasInstance() || this.mReactNativeHost.getReactInstanceManager() == null) {
            return;
        }
        this.mReactNativeHost.getReactInstanceManager().recreateReactContextInBackground();
    }

    public void loadApp() {
        loadApp(this.mMainComponentName);
    }

    public void loadApp(String str) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            if (this.mReactSurface == null) {
                this.mReactSurface = this.mReactHost.createSurface(this.mActivity, str, this.mLaunchOptions);
            }
            this.mReactSurface.start();
        } else {
            if (this.mReactRootView != null) {
                throw new IllegalStateException("Cannot loadApp while app is already running.");
            }
            ReactRootView reactRootViewCreateRootView = createRootView();
            this.mReactRootView = reactRootViewCreateRootView;
            reactRootViewCreateRootView.startReactApplication(getReactNativeHost().getReactInstanceManager(), str, this.mLaunchOptions);
        }
    }

    public ReactRootView getReactRootView() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            return (ReactRootView) this.mReactSurface.getView();
        }
        return this.mReactRootView;
    }

    protected ReactRootView createRootView() {
        ReactRootView reactRootView = new ReactRootView(this.mActivity);
        reactRootView.setIsFabric(isFabricEnabled());
        return reactRootView;
    }

    public boolean shouldShowDevMenuOrReload(int i, KeyEvent keyEvent) {
        DevSupportManager devSupportManager = getDevSupportManager();
        if (devSupportManager != null && !(devSupportManager instanceof ReleaseDevSupportManager)) {
            if (i == 82) {
                devSupportManager.showDevOptionsDialog();
                return true;
            }
            if (((DoubleTapReloadRecognizer) Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(i, this.mActivity.getCurrentFocus())) {
                devSupportManager.handleReloadJS();
                return true;
            }
        }
        return false;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    public ReactInstanceManager getReactInstanceManager() {
        return getReactNativeHost().getReactInstanceManager();
    }

    protected boolean isFabricEnabled() {
        return this.mFabricEnabled;
    }
}
