package com.localmediametadata;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.localmediametadata.MetadataCallable;
import com.localmediametadata.media3.MetadataMedia3;

@ReactModule(name = LocalMediaMetadataModule.NAME)
/* loaded from: classes3.dex */
public class LocalMediaMetadataModule extends ReactContextBaseJavaModule {
    public static final String NAME = "LocalMediaMetadata";
    private final ReactApplicationContext reactContext;

    public LocalMediaMetadataModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void readMetadata(String str, Promise promise) {
        AsyncTask.runTask(new MetadataCallable.ReadMetadata(this.reactContext, str), promise);
    }

    @ReactMethod
    public void writeMetadata(String str, ReadableMap readableMap, boolean z, Promise promise) {
        AsyncTask.runTask(new MetadataCallable.WriteMetadata(this.reactContext, str, Arguments.toBundle(readableMap), z), promise);
    }

    private static boolean isSupportMedia3Pic(String str) {
        if (!str.startsWith("content://")) {
            return false;
        }
        String lowerCase = Utils.getFileExtension(str).toLowerCase();
        lowerCase.hashCode();
        return lowerCase.equals("mp3") || lowerCase.equals("flac");
    }

    @ReactMethod
    public void readPic(String str, String str2, Promise promise) {
        if (isSupportMedia3Pic(str)) {
            MetadataMedia3.readPic(this.reactContext, str, str2, promise);
        } else {
            AsyncTask.runTask(new MetadataCallable.ReadPic(this.reactContext, str, str2), promise);
        }
    }

    @ReactMethod
    public void writePic(String str, String str2, Promise promise) {
        AsyncTask.runTask(new MetadataCallable.WritePic(this.reactContext, str, str2), promise);
    }

    @ReactMethod
    public void readLyric(String str, boolean z, Promise promise) {
        AsyncTask.runTask(new MetadataCallable.ReadLyric(this.reactContext, str, z), promise);
    }

    @ReactMethod
    public void writeLyric(String str, String str2, Promise promise) {
        AsyncTask.runTask(new MetadataCallable.WriteLyric(this.reactContext, str, str2), promise);
    }
}
