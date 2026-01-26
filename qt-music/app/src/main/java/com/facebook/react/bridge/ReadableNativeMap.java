package com.facebook.react.bridge;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReadableNativeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReadableNativeMap.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0011\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J6\u0010\u001d\u001a\u0002H\u001e\"\u0006\b\u0000\u0010\u001e\u0018\u00012\u0006\u0010\u001f\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\n2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001e0\"H\u0083\b¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\nH\u0096\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u0010)\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u00100\u001a\u0002012\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0012\u00102\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0012\u00103\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020\tH\u0002J.\u00103\u001a\u0004\u0018\u0001H\u001e\"\u0006\b\u0000\u0010\u001e\u0018\u00012\u0006\u0010\u001f\u001a\u00020\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001e0\"H\u0082\b¢\u0006\u0002\u00104J\u0012\u00105\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u00106\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0010\u00107\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\tH\u0002J,\u00107\u001a\u0002H\u001e\"\u0006\b\u0000\u0010\u001e\u0018\u00012\u0006\u0010\u001f\u001a\u00020\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001e0\"H\u0082\b¢\u0006\u0002\u00104J\u0010\u00108\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\b\u00109\u001a\u00020/H\u0016J\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0082 ¢\u0006\u0002\u0010\u0010J\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0082 ¢\u0006\u0002\u0010<J\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0082 ¢\u0006\u0002\u0010<J\u0010\u0010>\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010?\u001a\u00020@H\u0016J$\u0010A\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0014j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u0015H\u0016R&\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R7\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0014j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R7\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001a0\u0014j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001a`\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001b\u0010\u0017¨\u0006C"}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeMap;", "Lcom/facebook/react/bridge/NativeMap;", "Lcom/facebook/react/bridge/ReadableMap;", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "entryIterator", "", "", "", "", "getEntryIterator", "()Ljava/util/Iterator;", "keys", "", "getKeys", "()[Ljava/lang/String;", "keys$delegate", "Lkotlin/Lazy;", "localMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getLocalMap", "()Ljava/util/HashMap;", "localMap$delegate", "localTypeMap", "Lcom/facebook/react/bridge/ReadableType;", "getLocalTypeMap", "localTypeMap$delegate", "checkInstance", ExifInterface.GPS_DIRECTION_TRUE, "name", "instance", "type", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "equals", "", "other", "getArray", "Lcom/facebook/react/bridge/ReadableArray;", "getBoolean", "getDouble", "", "getDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getInt", "", "getLong", "", "getMap", "getNullableValue", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "getString", "getType", "getValue", "hasKey", "hashCode", "importKeys", "importTypes", "()[Ljava/lang/Object;", "importValues", "isNull", "keySetIterator", "Lcom/facebook/react/bridge/ReadableMapKeySetIterator;", "toHashMap", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static final Companion Companion = new Companion(null);
    private static int jniPassCounter;

    /* renamed from: keys$delegate, reason: from kotlin metadata */
    private final Lazy keys;

    /* renamed from: localMap$delegate, reason: from kotlin metadata */
    private final Lazy localMap;

    /* renamed from: localTypeMap$delegate, reason: from kotlin metadata */
    private final Lazy localTypeMap;

    /* compiled from: ReadableNativeMap.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final int getJNIPassCounter() {
        return Companion.getJNIPassCounter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native String[] importKeys();

    /* JADX INFO: Access modifiers changed from: private */
    public final native Object[] importTypes();

    /* JADX INFO: Access modifiers changed from: private */
    public final native Object[] importValues();

    protected ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
        this.keys = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<String[]>() { // from class: com.facebook.react.bridge.ReadableNativeMap$keys$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String[] invoke() {
                String[] strArrImportKeys = this.this$0.importKeys();
                ReadableNativeMap.Companion unused = ReadableNativeMap.Companion;
                ReadableNativeMap.jniPassCounter++;
                return strArrImportKeys;
            }
        });
        this.localMap = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HashMap<String, Object>>() { // from class: com.facebook.react.bridge.ReadableNativeMap$localMap$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Object> invoke() {
                int length = this.this$0.getKeys().length;
                HashMap<String, Object> map = new HashMap<>(length);
                Object[] objArrImportValues = this.this$0.importValues();
                ReadableNativeMap.Companion unused = ReadableNativeMap.Companion;
                ReadableNativeMap.jniPassCounter++;
                for (int i = 0; i < length; i++) {
                    map.put(this.this$0.getKeys()[i], objArrImportValues[i]);
                }
                return map;
            }
        });
        this.localTypeMap = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<HashMap<String, ReadableType>>() { // from class: com.facebook.react.bridge.ReadableNativeMap$localTypeMap$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ReadableType> invoke() {
                int length = this.this$0.getKeys().length;
                HashMap<String, ReadableType> map = new HashMap<>(length);
                Object[] objArrImportTypes = this.this$0.importTypes();
                ReadableNativeMap.Companion unused = ReadableNativeMap.Companion;
                ReadableNativeMap.jniPassCounter++;
                for (int i = 0; i < length; i++) {
                    String str = this.this$0.getKeys()[i];
                    Object obj = objArrImportTypes[i];
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableType");
                    map.put(str, (ReadableType) obj);
                }
                return map;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String[] getKeys() {
        return (String[]) this.keys.getValue();
    }

    private final HashMap<String, Object> getLocalMap() {
        return (HashMap) this.localMap.getValue();
    }

    private final HashMap<String, ReadableType> getLocalTypeMap() {
        return (HashMap) this.localTypeMap.getValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getLocalMap().containsKey(name);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (getLocalMap().containsKey(name)) {
            return getLocalMap().get(name) == null;
        }
        throw new NoSuchKeyException(name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T> T checkInstance(String name, Object instance, Class<T> type) {
        Class<?> cls;
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        if (instance != 0) {
            return instance;
        }
        String simpleName = (instance == 0 || (cls = instance.getClass()) == null) ? null : cls.getSimpleName();
        if (simpleName == null) {
            simpleName = "NULL";
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    private final Object getValue(String name) {
        if (hasKey(name)) {
            Object objAssertNotNull = Assertions.assertNotNull(getLocalMap().get(name));
            Intrinsics.checkNotNullExpressionValue(objAssertNotNull, "assertNotNull(...)");
            return objAssertNotNull;
        }
        throw new NoSuchKeyException(name);
    }

    private final /* synthetic */ <T> T getValue(String name, Class<T> type) {
        Class<?> cls;
        T t = (T) getValue(name);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        if (t != null) {
            return t;
        }
        String simpleName = (t == null || (cls = t.getClass()) == null) ? null : cls.getSimpleName();
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    private final Object getNullableValue(String name) {
        return getLocalMap().get(name);
    }

    private final /* synthetic */ <T> T getNullableValue(String name, Class<T> type) {
        T t = (T) getNullableValue(name);
        if (t == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        if (t != null) {
            return t;
        }
        Class<?> cls = t.getClass();
        String simpleName = cls != null ? cls.getSimpleName() : null;
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + type.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(String name) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Boolean.TYPE;
        Object value = getValue(name);
        String simpleName = null;
        Boolean bool = (Boolean) (!(value instanceof Boolean) ? null : value);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (value != null && (cls = value.getClass()) != null) {
            simpleName = cls.getSimpleName();
        }
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(String name) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Double.TYPE;
        Object value = getValue(name);
        String simpleName = null;
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return d.doubleValue();
        }
        if (value != null && (cls = value.getClass()) != null) {
            simpleName = cls.getSimpleName();
        }
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(String name) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Double.TYPE;
        Object value = getValue(name);
        String simpleName = null;
        Double d = (Double) (!(value instanceof Double) ? null : value);
        if (d != null) {
            return (int) d.doubleValue();
        }
        if (value != null && (cls = value.getClass()) != null) {
            simpleName = cls.getSimpleName();
        }
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public long getLong(String name) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(name, "name");
        Class cls2 = Long.TYPE;
        Object value = getValue(name);
        String simpleName = null;
        Long l = (Long) (!(value instanceof Long) ? null : value);
        if (l != null) {
            return l.longValue();
        }
        if (value != null && (cls = value.getClass()) != null) {
            simpleName = cls.getSimpleName();
        }
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + cls2.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        String str = (String) (!(nullableValue instanceof String) ? null : nullableValue);
        if (str != null) {
            return str;
        }
        Class<?> cls = nullableValue.getClass();
        String simpleName = cls != null ? cls.getSimpleName() : null;
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to " + String.class.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        ReadableArray readableArray = (ReadableArray) (!(nullableValue instanceof ReadableArray) ? null : nullableValue);
        if (readableArray != null) {
            return readableArray;
        }
        Class<?> cls = nullableValue.getClass();
        String simpleName = cls != null ? cls.getSimpleName() : null;
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to ReadableArray");
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableType getType(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ReadableType readableType = getLocalTypeMap().get(name);
        if (readableType != null) {
            return readableType;
        }
        throw new NoSuchKeyException(name);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Dynamic getDynamic(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        DynamicFromMap dynamicFromMapCreate = DynamicFromMap.create(this, name);
        Intrinsics.checkNotNullExpressionValue(dynamicFromMapCreate, "create(...)");
        return dynamicFromMapCreate;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        ReadableNativeMap$entryIterator$1$1 readableNativeMap$entryIterator$1$1;
        synchronized (this) {
            String[] keys = getKeys();
            Object[] objArrImportValues = importValues();
            jniPassCounter++;
            readableNativeMap$entryIterator$1$1 = new ReadableNativeMap$entryIterator$1$1(keys, objArrImportValues);
        }
        return readableNativeMap$entryIterator$1$1;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableMapKeySetIterator keySetIterator() {
        final String[] keys = getKeys();
        return new ReadableMapKeySetIterator() { // from class: com.facebook.react.bridge.ReadableNativeMap.keySetIterator.1
            private int currentIndex;

            public final int getCurrentIndex() {
                return this.currentIndex;
            }

            public final void setCurrentIndex(int i) {
                this.currentIndex = i;
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public boolean hasNextKey() {
                return this.currentIndex < keys.length;
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public String nextKey() {
                String[] strArr = keys;
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                return strArr[i];
            }
        };
    }

    public int hashCode() {
        return getLocalMap().hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof ReadableNativeMap) {
            return Intrinsics.areEqual(getLocalMap(), ((ReadableNativeMap) other).getLocalMap());
        }
        return false;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>(getLocalMap());
        for (String str : map.keySet()) {
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            String str2 = str;
            switch (WhenMappings.$EnumSwitchMapping$0[getType(str2).ordinal()]) {
                case 5:
                    map.put(str2, ((ReadableNativeMap) Assertions.assertNotNull(getMap(str2))).toHashMap());
                    break;
                case 6:
                    map.put(str2, ((ReadableArray) Assertions.assertNotNull(getArray(str2))).toArrayList());
                    break;
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReadableNativeMap.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/bridge/ReadableNativeMap$Companion;", "", "()V", "jniPassCounter", "", "getJNIPassCounter", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getJNIPassCounter() {
            return ReadableNativeMap.jniPassCounter;
        }
    }

    static {
        ReactBridge.staticInit();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableNativeMap getMap(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object nullableValue = getNullableValue(name);
        if (nullableValue == null) {
            return null;
        }
        ReadableNativeMap readableNativeMap = (ReadableNativeMap) (!(nullableValue instanceof ReadableNativeMap) ? null : nullableValue);
        if (readableNativeMap != null) {
            return readableNativeMap;
        }
        Class<?> cls = nullableValue.getClass();
        String simpleName = cls != null ? cls.getSimpleName() : null;
        if (simpleName == null) {
            simpleName = "NULL";
        } else {
            Intrinsics.checkNotNull(simpleName);
        }
        throw new UnexpectedNativeTypeException("Value for " + name + " cannot be cast from " + simpleName + " to ReadableNativeMap");
    }
}
