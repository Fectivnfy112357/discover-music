package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WritableNativeMap.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0011\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0082 J\u001a\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0019\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0010H\u0096 J\u0019\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0012H\u0096 J\u0019\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0014H\u0096 J\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0016H\u0096 J\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016J\u001b\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0019H\u0082 J\u001b\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0082 J\u0011\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0096 J\u001b\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0096 ¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/bridge/WritableNativeMap;", "Lcom/facebook/react/bridge/ReadableNativeMap;", "Lcom/facebook/react/bridge/WritableMap;", "()V", "copy", "merge", "", "source", "Lcom/facebook/react/bridge/ReadableMap;", "mergeNativeMap", "putArray", "key", "", "value", "Lcom/facebook/react/bridge/ReadableArray;", "putBoolean", "", "putDouble", "", "putInt", "", "putLong", "", "putMap", "putNativeArray", "Lcom/facebook/react/bridge/ReadableNativeArray;", "putNativeMap", "putNull", "putString", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    private static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native HybridData initHybrid();

    private final native void mergeNativeMap(ReadableNativeMap source);

    private final native void putNativeArray(String key, ReadableNativeArray value);

    private final native void putNativeMap(String key, ReadableNativeMap value);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putBoolean(String key, boolean value);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putDouble(String key, double value);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putInt(String key, int value);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putLong(String key, long value);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putNull(String key);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putString(String key, String value);

    public WritableNativeMap() {
        super(Companion.initHybrid());
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(String key, ReadableMap value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Assertions.assertCondition(value == null || (value instanceof ReadableNativeMap), "Illegal type provided");
        putNativeMap(key, (ReadableNativeMap) value);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(String key, ReadableArray value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Assertions.assertCondition(value == null || (value instanceof ReadableNativeArray), "Illegal type provided");
        putNativeArray(key, (ReadableNativeArray) value);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(ReadableMap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Assertions.assertCondition(source instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) source);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public WritableMap copy() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.merge(this);
        return writableNativeMap;
    }

    /* compiled from: WritableNativeMap.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0083 ¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/bridge/WritableNativeMap$Companion;", "", "()V", "initHybrid", "Lcom/facebook/jni/HybridData;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final HybridData initHybrid() {
            return WritableNativeMap.initHybrid();
        }

        private Companion() {
        }
    }

    static {
        ReactBridge.staticInit();
    }
}
