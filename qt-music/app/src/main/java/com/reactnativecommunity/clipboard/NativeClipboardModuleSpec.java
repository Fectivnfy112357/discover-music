package com.reactnativecommunity.clipboard;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* loaded from: classes3.dex */
public abstract class NativeClipboardModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void getImage(Promise promise);

    @ReactMethod
    public abstract void getImageJPG(Promise promise);

    @ReactMethod
    public abstract void getImagePNG(Promise promise);

    @ReactMethod
    public abstract void getString(Promise promise);

    @ReactMethod
    public abstract void getStrings(Promise promise);

    @ReactMethod
    public abstract void hasImage(Promise promise);

    @ReactMethod
    public abstract void hasNumber(Promise promise);

    @ReactMethod
    public abstract void hasString(Promise promise);

    @ReactMethod
    public abstract void hasURL(Promise promise);

    @ReactMethod
    public abstract void hasWebURL(Promise promise);

    @ReactMethod
    public abstract void removeListener();

    @ReactMethod
    public abstract void removeListeners(double d);

    @ReactMethod
    public abstract void setImage(String str, Promise promise);

    @ReactMethod
    public abstract void setListener();

    @ReactMethod
    public abstract void setString(String str);

    @ReactMethod
    public abstract void setStrings(ReadableArray readableArray);

    public NativeClipboardModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
