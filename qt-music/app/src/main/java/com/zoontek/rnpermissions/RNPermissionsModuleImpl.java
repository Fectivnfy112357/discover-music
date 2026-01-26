package com.zoontek.rnpermissions;

import android.app.AlarmManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RNPermissionsModuleImpl.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J,\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J \u0010&\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010'\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013J4\u0010(\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010+\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J4\u0010,\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010-\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010.\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/zoontek/rnpermissions/RNPermissionsModuleImpl;", "", "()V", "BLOCKED", "", "DENIED", "ERROR_INVALID_ACTIVITY", "GRANTED", "NAME", "UNAVAILABLE", "minimumApi", "", "", "requestCode", "canScheduleExactAlarms", "", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "check", "permission", "checkLocationAccuracy", "checkMultiple", "permissions", "Lcom/facebook/react/bridge/ReadableArray;", "checkNotifications", "getPermissionAwareActivity", "Lcom/facebook/react/modules/core/PermissionAwareActivity;", "isPermissionAvailable", "", "onRequestPermissionsResult", "callbacks", "Landroid/util/SparseArray;", "Lcom/facebook/react/bridge/Callback;", "grantResults", "", "openPhotoPicker", "openSettings", "type", "request", "listener", "Lcom/facebook/react/modules/core/PermissionListener;", "requestLocationAccuracy", "requestMultiple", "requestNotifications", "shouldShowRequestRationale", "react-native-permissions_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNPermissionsModuleImpl {
    private static final String BLOCKED = "blocked";
    private static final String DENIED = "denied";
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private static final String GRANTED = "granted";
    public static final String NAME = "RNPermissions";
    private static final String UNAVAILABLE = "unavailable";
    private static int requestCode;
    public static final RNPermissionsModuleImpl INSTANCE = new RNPermissionsModuleImpl();
    private static final Map<String, Integer> minimumApi = MapsKt.mapOf(TuplesKt.to("android.permission.ACCEPT_HANDOVER", 28), TuplesKt.to("android.permission.ACCESS_BACKGROUND_LOCATION", 29), TuplesKt.to("android.permission.ACCESS_MEDIA_LOCATION", 29), TuplesKt.to("android.permission.ACTIVITY_RECOGNITION", 29), TuplesKt.to("android.permission.ANSWER_PHONE_CALLS", 26), TuplesKt.to("android.permission.BLUETOOTH_ADVERTISE", 31), TuplesKt.to("android.permission.BLUETOOTH_CONNECT", 31), TuplesKt.to("android.permission.BLUETOOTH_SCAN", 31), TuplesKt.to("android.permission.BODY_SENSORS_BACKGROUND", 33), TuplesKt.to("android.permission.NEARBY_WIFI_DEVICES", 33), TuplesKt.to("android.permission.READ_MEDIA_AUDIO", 33), TuplesKt.to("android.permission.READ_MEDIA_IMAGES", 33), TuplesKt.to("android.permission.READ_MEDIA_VIDEO", 33), TuplesKt.to("android.permission.READ_MEDIA_VISUAL_USER_SELECTED", 34), TuplesKt.to("android.permission.READ_PHONE_NUMBERS", 26), TuplesKt.to("android.permission.UWB_RANGING", 31));

    private RNPermissionsModuleImpl() {
    }

    private final boolean isPermissionAvailable(String permission) {
        return Build.VERSION.SDK_INT >= minimumApi.getOrDefault(permission, 1).intValue();
    }

    public final void openSettings(ReactApplicationContext reactContext, String type, Promise promise) {
        Intent intent;
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            String packageName = reactContext.getPackageName();
            if (Build.VERSION.SDK_INT >= 31 && Intrinsics.areEqual(type, "alarms")) {
                intent = new Intent();
                intent.setAction("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
                intent.setData(Uri.parse("package:" + packageName));
            } else if (Build.VERSION.SDK_INT >= 26 && Intrinsics.areEqual(type, "notifications")) {
                intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            } else {
                intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + packageName));
            }
            intent.addFlags(268435456);
            reactContext.startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    public final void canScheduleExactAlarms(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (Build.VERSION.SDK_INT < 31) {
            promise.resolve(true);
            return;
        }
        Object systemService = reactContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        promise.resolve(Boolean.valueOf(alarmManager != null ? alarmManager.canScheduleExactAlarms() : false));
    }

    public final void check(ReactApplicationContext reactContext, String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (!isPermissionAvailable(permission)) {
            promise.resolve(UNAVAILABLE);
        } else if (reactContext.getBaseContext().checkSelfPermission(permission) == 0) {
            promise.resolve(GRANTED);
        } else {
            promise.resolve(DENIED);
        }
    }

    public final void checkNotifications(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(reactContext).areNotificationsEnabled();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(NotificationCompat.CATEGORY_STATUS, zAreNotificationsEnabled ? GRANTED : DENIED);
        writableMapCreateMap.putMap("settings", Arguments.createMap());
        promise.resolve(writableMapCreateMap);
    }

    public final void checkMultiple(ReactApplicationContext reactContext, ReadableArray permissions, Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Context baseContext = reactContext.getBaseContext();
        int size = permissions.size();
        for (int i = 0; i < size; i++) {
            String string = permissions.getString(i);
            String str2 = string;
            if (str2 != null && !StringsKt.isBlank(str2)) {
                if (isPermissionAvailable(string)) {
                    str = baseContext.checkSelfPermission(string) == 0 ? GRANTED : DENIED;
                } else {
                    str = UNAVAILABLE;
                }
                writableNativeMap.putString(string, str);
            }
        }
        promise.resolve(writableNativeMap);
    }

    public final void request(ReactApplicationContext reactContext, PermissionListener listener, SparseArray<Callback> callbacks, final String permission, final Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (!isPermissionAvailable(permission)) {
            promise.resolve(UNAVAILABLE);
            return;
        }
        if (reactContext.getBaseContext().checkSelfPermission(permission) == 0) {
            promise.resolve(GRANTED);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactContext);
            callbacks.put(requestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl$$ExternalSyntheticLambda0
                @Override // com.facebook.react.bridge.Callback
                public final void invoke(Object[] objArr) {
                    RNPermissionsModuleImpl.request$lambda$5(promise, permission, objArr);
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{permission}, requestCode, listener);
            requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void request$lambda$5(Promise promise, String permission, Object[] args) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permission, "$permission");
        Intrinsics.checkNotNullParameter(args, "args");
        Object obj = args[0];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.IntArray");
        Object obj2 = args[1];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) obj2;
        Integer orNull = ArraysKt.getOrNull((int[]) obj, 0);
        if (orNull != null && orNull.intValue() == 0) {
            str = GRANTED;
        } else {
            str = permissionAwareActivity.shouldShowRequestPermissionRationale(permission) ? DENIED : BLOCKED;
        }
        promise.resolve(str);
    }

    public final void requestNotifications(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(reactContext).areNotificationsEnabled();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(NotificationCompat.CATEGORY_STATUS, zAreNotificationsEnabled ? GRANTED : BLOCKED);
        writableMapCreateMap.putMap("settings", Arguments.createMap());
        promise.resolve(writableMapCreateMap);
    }

    public final void requestMultiple(ReactApplicationContext reactContext, PermissionListener listener, SparseArray<Callback> callbacks, ReadableArray permissions, final Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = reactContext.getBaseContext();
        int size = permissions.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String string = permissions.getString(i2);
            String str = string;
            if (str != null && !StringsKt.isBlank(str)) {
                if (!isPermissionAvailable(string)) {
                    writableNativeMap.putString(string, UNAVAILABLE);
                } else if (baseContext.checkSelfPermission(string) == 0) {
                    writableNativeMap.putString(string, GRANTED);
                } else {
                    arrayList.add(string);
                }
                i++;
            }
        }
        if (permissions.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactContext);
            callbacks.put(requestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl$$ExternalSyntheticLambda1
                @Override // com.facebook.react.bridge.Callback
                public final void invoke(Object[] objArr) {
                    RNPermissionsModuleImpl.requestMultiple$lambda$8(arrayList, promise, writableNativeMap, objArr);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), requestCode, listener);
            requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestMultiple$lambda$8(ArrayList permissionsToCheck, Promise promise, WritableMap output, Object[] args) {
        String str;
        Intrinsics.checkNotNullParameter(permissionsToCheck, "$permissionsToCheck");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(output, "$output");
        Intrinsics.checkNotNullParameter(args, "args");
        int i = 0;
        Object obj = args[0];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr = (int[]) obj;
        Object obj2 = args[1];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) obj2;
        for (Object obj3 : permissionsToCheck) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj3;
            Integer orNull = ArraysKt.getOrNull(iArr, i);
            if (orNull != null && orNull.intValue() == 0) {
                str = GRANTED;
            } else {
                str = permissionAwareActivity.shouldShowRequestPermissionRationale(str2) ? DENIED : BLOCKED;
            }
            output.putString(str2, str);
            i = i2;
        }
        promise.resolve(output);
    }

    public final void shouldShowRequestRationale(ReactApplicationContext reactContext, String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity(reactContext).shouldShowRequestPermissionRationale(permission)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    private final PermissionAwareActivity getPermissionAwareActivity(ReactApplicationContext reactContext) {
        ComponentCallbacks2 currentActivity = reactContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.".toString());
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.".toString());
        }
        return (PermissionAwareActivity) currentActivity;
    }

    public final void openPhotoPicker(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:openPhotoPicker", "openPhotoPicker is not supported on Android");
    }

    public final void checkLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:checkLocationAccuracy", "checkLocationAccuracy is not supported on Android");
    }

    public final void requestLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:requestLocationAccuracy", "requestLocationAccuracy is not supported on Android");
    }

    public final boolean onRequestPermissionsResult(ReactApplicationContext reactContext, SparseArray<Callback> callbacks, int requestCode2, int[] grantResults) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        try {
            Callback callback = callbacks.get(requestCode2);
            if (callback != null) {
                callback.invoke(grantResults, getPermissionAwareActivity(reactContext));
                callbacks.remove(requestCode2);
            } else {
                FLog.w("PermissionsModule", "Unable to find callback with requestCode %d", Integer.valueOf(requestCode2));
            }
            return callbacks.size() == 0;
        } catch (IllegalStateException e) {
            FLog.e("PermissionsModule", e, "Unexpected invocation of `onRequestPermissionsResult`", new Object[0]);
            return false;
        }
    }
}
