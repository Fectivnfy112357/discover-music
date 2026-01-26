package com.zoontek.rnpermissions;

import android.util.SparseArray;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNPermissionsModule.kt */
@ReactModule(name = RNPermissionsModuleImpl.NAME)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\u0015\u001a\u00020\u000fH\u0016J+\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001a\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010!\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010$\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010'\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/zoontek/rnpermissions/RNPermissionsModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "Lcom/facebook/react/modules/core/PermissionListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "callbacks", "Landroid/util/SparseArray;", "Lcom/facebook/react/bridge/Callback;", "canScheduleExactAlarms", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "check", "permission", "", "checkLocationAccuracy", "checkMultiple", "permissions", "Lcom/facebook/react/bridge/ReadableArray;", "checkNotifications", "getName", "onRequestPermissionsResult", "", "requestCode", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)Z", "openPhotoPicker", "openSettings", "type", "request", "requestLocationAccuracy", "purposeKey", "requestMultiple", "requestNotifications", "options", "shouldShowRequestRationale", "react-native-permissions_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNPermissionsModule extends ReactContextBaseJavaModule implements PermissionListener {
    private final SparseArray<Callback> callbacks;

    public RNPermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.callbacks = new SparseArray<>();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNPermissionsModuleImpl.NAME;
    }

    @ReactMethod
    public final void openSettings(String type, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.openSettings(reactApplicationContext, type, promise);
    }

    @ReactMethod
    public final void canScheduleExactAlarms(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.canScheduleExactAlarms(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void check(String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.check(reactApplicationContext, permission, promise);
    }

    @ReactMethod
    public final void checkNotifications(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.checkNotifications(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void checkMultiple(ReadableArray permissions, Promise promise) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.checkMultiple(reactApplicationContext, permissions, promise);
    }

    @ReactMethod
    public final void request(String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.request(reactApplicationContext, this, this.callbacks, permission, promise);
    }

    @ReactMethod
    public final void requestNotifications(ReadableArray options, Promise promise) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.requestNotifications(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void requestMultiple(ReadableArray permissions, Promise promise) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.requestMultiple(reactApplicationContext, this, this.callbacks, permissions, promise);
    }

    @ReactMethod
    public final void shouldShowRequestRationale(String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.shouldShowRequestRationale(reactApplicationContext, permission, promise);
    }

    @ReactMethod
    public final void checkLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl.INSTANCE.checkLocationAccuracy(promise);
    }

    @ReactMethod
    public final void requestLocationAccuracy(String purposeKey, Promise promise) {
        Intrinsics.checkNotNullParameter(purposeKey, "purposeKey");
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl.INSTANCE.requestLocationAccuracy(promise);
    }

    @ReactMethod
    public final void openPhotoPicker(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        RNPermissionsModuleImpl.INSTANCE.openPhotoPicker(promise);
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        return rNPermissionsModuleImpl.onRequestPermissionsResult(reactApplicationContext, this.callbacks, requestCode, grantResults);
    }
}
