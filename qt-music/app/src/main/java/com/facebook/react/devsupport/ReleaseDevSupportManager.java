package com.facebook.react.devsupport;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReleaseDevSupportManager.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u00152\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0014\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u00010\u0015H\u0016J\u0014\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010<\u001a\u0002022\b\u0010=\u001a\u0004\u0018\u000107H\u0016J\u001c\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020\u00152\b\u0010A\u001a\u0004\u0018\u00010?H\u0016J\u0014\u0010B\u001a\u0002022\n\u0010C\u001a\u00060Dj\u0002`EH\u0016J\b\u0010F\u001a\u000202H\u0016J\b\u0010G\u001a\u00020\u000eH\u0016J\b\u0010H\u001a\u000202H\u0016J\b\u0010I\u001a\u000202H\u0016J\u0010\u0010J\u001a\u0002022\u0006\u0010K\u001a\u00020LH\u0016J\u0018\u0010M\u001a\u0002022\u0006\u0010N\u001a\u00020\u00152\u0006\u0010K\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u0002022\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u0002022\u0006\u0010Q\u001a\u00020RH\u0016J\b\u0010T\u001a\u000202H\u0016J8\u0010U\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0018\u00010V2\u001a\u0010W\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0018\u00010VH\u0016J\u0012\u0010X\u001a\u0002022\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\u0018\u0010[\u001a\u0002022\u0006\u0010\\\u001a\u00020\u00152\u0006\u0010K\u001a\u00020]H\u0016J\b\u0010^\u001a\u000202H\u0016J\u0018\u0010_\u001a\u0002022\u0006\u0010`\u001a\u00020\u00152\u0006\u0010a\u001a\u00020\u0015H\u0016J\u0010\u0010b\u001a\u0002022\u0006\u0010c\u001a\u00020\u000eH\u0016J\u0010\u0010d\u001a\u0002022\u0006\u0010e\u001a\u00020\u000eH\u0016J\u0012\u0010f\u001a\u0002022\b\u0010g\u001a\u0004\u0018\u00010hH\u0016J\u0010\u0010i\u001a\u0002022\u0006\u0010j\u001a\u00020\u000eH\u0016J\b\u0010k\u001a\u000202H\u0016J$\u0010l\u001a\u0002022\b\u0010m\u001a\u0004\u0018\u00010\u00152\b\u0010n\u001a\u0004\u0018\u00010o2\u0006\u0010p\u001a\u00020\u001bH\u0016J\u001c\u0010q\u001a\u0002022\b\u0010m\u001a\u0004\u0018\u00010\u00152\b\u0010C\u001a\u0004\u0018\u00010rH\u0016J\u0018\u0010s\u001a\u0002022\u0006\u0010m\u001a\u00020\u00152\u0006\u0010t\u001a\u00020uH\u0016J\b\u0010v\u001a\u000202H\u0016J\b\u0010w\u001a\u000202H\u0016J\b\u0010x\u001a\u000202H\u0016J$\u0010y\u001a\u0002022\b\u0010m\u001a\u0004\u0018\u00010\u00152\b\u0010n\u001a\u0004\u0018\u00010o2\u0006\u0010p\u001a\u00020\u001bH\u0016R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0016\u0010#\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0017R\u0016\u0010%\u001a\u0004\u0018\u00010&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0016\u0010)\u001a\u0004\u0018\u00010*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0016\u0010-\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0017R\u0016\u0010/\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0017¨\u0006z"}, d2 = {"Lcom/facebook/react/devsupport/ReleaseDevSupportManager;", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "defaultJSExceptionHandler", "Lcom/facebook/react/bridge/DefaultJSExceptionHandler;", "devSettings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "getDevSettings", "()Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "isDevSupportEnabled", "", "devSupportEnabled", "getDevSupportEnabled", "()Z", "setDevSupportEnabled", "(Z)V", "downloadedJSBundleFile", "", "getDownloadedJSBundleFile", "()Ljava/lang/String;", "jSBundleURLForRemoteDebugging", "getJSBundleURLForRemoteDebugging", "lastErrorCookie", "", "getLastErrorCookie", "()I", "lastErrorStack", "", "Lcom/facebook/react/devsupport/interfaces/StackFrame;", "getLastErrorStack", "()[Lcom/facebook/react/devsupport/interfaces/StackFrame;", "lastErrorTitle", "getLastErrorTitle", "lastErrorType", "Lcom/facebook/react/devsupport/interfaces/ErrorType;", "getLastErrorType", "()Lcom/facebook/react/devsupport/interfaces/ErrorType;", "redBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getRedBoxHandler", "()Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "sourceMapUrl", "getSourceMapUrl", "sourceUrl", "getSourceUrl", "addCustomDevOption", "", "optionName", "optionHandler", "Lcom/facebook/react/devsupport/interfaces/DevOptionHandler;", "createRootView", "Landroid/view/View;", "appKey", "createSurfaceDelegate", "Lcom/facebook/react/common/SurfaceDelegate;", "moduleName", "destroyRootView", "rootView", "downloadBundleResourceFromUrlSync", "Ljava/io/File;", "resourceURL", "outputFile", "handleException", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleReloadJS", "hasUpToDateJSBundleInCache", "hidePausedInDebuggerOverlay", "hideRedboxDialog", "isPackagerRunning", "callback", "Lcom/facebook/react/devsupport/interfaces/PackagerStatusCallback;", "loadSplitBundleFromServer", "bundlePath", "Lcom/facebook/react/devsupport/interfaces/DevSplitBundleCallback;", "onNewReactContextCreated", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "onReactInstanceDestroyed", "openDebugger", "processErrorCustomizers", "Landroid/util/Pair;", "errorInfo", "registerErrorCustomizer", "errorCustomizer", "Lcom/facebook/react/devsupport/interfaces/ErrorCustomizer;", "reloadJSFromServer", "bundleURL", "Lcom/facebook/react/devsupport/interfaces/BundleLoadCallback;", "reloadSettings", "setAdditionalOptionForPackager", "name", "value", "setFpsDebugEnabled", "isFpsDebugEnabled", "setHotModuleReplacementEnabled", "isHotModuleReplacementEnabled", "setPackagerLocationCustomizer", "packagerLocationCustomizer", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PackagerLocationCustomizer;", "setRemoteJSDebugEnabled", "isRemoteJSDebugEnabled", "showDevOptionsDialog", "showNewJSError", StackTraceHelper.MESSAGE_KEY, "details", "Lcom/facebook/react/bridge/ReadableArray;", "errorCookie", "showNewJavaError", "", "showPausedInDebuggerOverlay", "listener", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager$PausedInDebuggerOverlayCommandListener;", "startInspector", "stopInspector", "toggleElementInspector", "updateJSError", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ReleaseDevSupportManager implements DevSupportManager {
    private final DefaultJSExceptionHandler defaultJSExceptionHandler = new DefaultJSExceptionHandler();
    private final int lastErrorCookie;

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public View createRootView(String appKey) {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public SurfaceDelegate createSurfaceDelegate(String moduleName) {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void destroyRootView(View rootView) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
        Intrinsics.checkNotNullParameter(resourceURL, "resourceURL");
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public Activity getCurrentActivity() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public DeveloperSettings getDevSettings() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getDevSupportEnabled() {
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getDownloadedJSBundleFile() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getJSBundleURLForRemoteDebugging() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public StackFrame[] getLastErrorStack() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getLastErrorTitle() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public ErrorType getLastErrorType() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public RedBoxHandler getRedBoxHandler() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceMapUrl() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceUrl() {
        return null;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean hasUpToDateJSBundleInCache() {
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hidePausedInDebuggerOverlay() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hideRedboxDialog() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void loadSplitBundleFromServer(String bundlePath, DevSplitBundleCallback callback) {
        Intrinsics.checkNotNullParameter(bundlePath, "bundlePath");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onNewReactContextCreated(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onReactInstanceDestroyed(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void openDebugger() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> errorInfo) {
        return errorInfo;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadJSFromServer(String bundleURL, BundleLoadCallback callback) {
        Intrinsics.checkNotNullParameter(bundleURL, "bundleURL");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadSettings() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setAdditionalOptionForPackager(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setDevSupportEnabled(boolean z) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setFpsDebugEnabled(boolean isFpsDebugEnabled) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setHotModuleReplacementEnabled(boolean isHotModuleReplacementEnabled) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setPackagerLocationCustomizer(DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setRemoteJSDebugEnabled(boolean isRemoteJSDebugEnabled) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showDevOptionsDialog() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJSError(String message, ReadableArray details, int errorCookie) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJavaError(String message, Throwable e) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showPausedInDebuggerOverlay(String message, DevSupportManager.PausedInDebuggerOverlayCommandListener listener) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void toggleElementInspector() {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void updateJSError(String message, ReadableArray details, int errorCookie) {
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void isPackagerRunning(PackagerStatusCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onPackagerStatusFetched(false);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public int getLastErrorCookie() {
        return this.lastErrorCookie;
    }

    @Override // com.facebook.react.bridge.JSExceptionHandler
    public void handleException(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.defaultJSExceptionHandler.handleException(e);
    }
}
