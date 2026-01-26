package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectAnimatedNode.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010\u0012\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/animated/ObjectAnimatedNode;", "Lcom/facebook/react/animated/AnimatedNode;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "nativeAnimatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "(Lcom/facebook/react/bridge/ReadableMap;Lcom/facebook/react/animated/NativeAnimatedNodesManager;)V", "configClone", "Lcom/facebook/react/bridge/JavaOnlyMap;", "collectViewUpdates", "", "propKey", "", "propsMap", "collectViewUpdatesHelper", "Lcom/facebook/react/bridge/JavaOnlyArray;", "source", "Lcom/facebook/react/bridge/ReadableArray;", "prettyPrint", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ObjectAnimatedNode extends AnimatedNode {
    private static final String NODE_TAG_KEY = "nodeTag";
    private static final String VALUE_KEY = "value";
    private final JavaOnlyMap configClone;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    /* compiled from: ObjectAnimatedNode.kt */
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

    public ObjectAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager;
        JavaOnlyMap javaOnlyMapDeepClone = JavaOnlyMap.deepClone(config);
        Intrinsics.checkNotNullExpressionValue(javaOnlyMapDeepClone, "deepClone(...)");
        this.configClone = javaOnlyMapDeepClone;
    }

    public final void collectViewUpdates(String propKey, JavaOnlyMap propsMap) {
        Intrinsics.checkNotNullParameter(propKey, "propKey");
        Intrinsics.checkNotNullParameter(propsMap, "propsMap");
        ReadableType type = this.configClone.getType(VALUE_KEY);
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        if (type == ReadableType.Map) {
            propsMap.putMap(propKey, collectViewUpdatesHelper(this.configClone.getMap(VALUE_KEY)));
        } else {
            if (type == ReadableType.Array) {
                propsMap.putArray(propKey, collectViewUpdatesHelper(this.configClone.getArray(VALUE_KEY)));
                return;
            }
            throw new IllegalArgumentException("Invalid value type for ObjectAnimatedNode");
        }
    }

    private final JavaOnlyArray collectViewUpdatesHelper(ReadableArray source) {
        if (source == null) {
            return null;
        }
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        int size = source.size();
        for (int i = 0; i < size; i++) {
            switch (WhenMappings.$EnumSwitchMapping$0[source.getType(i).ordinal()]) {
                case 1:
                    javaOnlyArray.pushNull();
                    break;
                case 2:
                    javaOnlyArray.pushBoolean(source.getBoolean(i));
                    break;
                case 3:
                    javaOnlyArray.pushDouble(source.getDouble(i));
                    break;
                case 4:
                    javaOnlyArray.pushString(source.getString(i));
                    break;
                case 5:
                    ReadableMap map = source.getMap(i);
                    if (map.hasKey(NODE_TAG_KEY) && map.getType(NODE_TAG_KEY) == ReadableType.Number) {
                        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById == null) {
                            throw new IllegalArgumentException("Mapped value node does not exist".toString());
                        }
                        if (nodeById instanceof ValueAnimatedNode) {
                            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                            Object objectValue = valueAnimatedNode.getObjectValue();
                            if (objectValue instanceof Integer) {
                                javaOnlyArray.pushInt(((Number) objectValue).intValue());
                                break;
                            } else if (objectValue instanceof String) {
                                javaOnlyArray.pushString((String) objectValue);
                                break;
                            } else {
                                javaOnlyArray.pushDouble(valueAnimatedNode.getValue());
                                break;
                            }
                        } else if (nodeById instanceof ColorAnimatedNode) {
                            javaOnlyArray.pushInt(((ColorAnimatedNode) nodeById).getColor());
                            break;
                        } else {
                            break;
                        }
                    } else {
                        javaOnlyArray.pushMap(collectViewUpdatesHelper(source.getMap(i)));
                        break;
                    }
                    break;
                case 6:
                    javaOnlyArray.pushArray(collectViewUpdatesHelper(source.getArray(i)));
                    break;
            }
        }
        return javaOnlyArray;
    }

    private final JavaOnlyMap collectViewUpdatesHelper(ReadableMap source) {
        if (source == null) {
            return null;
        }
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = source.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (WhenMappings.$EnumSwitchMapping$0[source.getType(strNextKey).ordinal()]) {
                case 1:
                    javaOnlyMap.putNull(strNextKey);
                    break;
                case 2:
                    javaOnlyMap.putBoolean(strNextKey, source.getBoolean(strNextKey));
                    break;
                case 3:
                    javaOnlyMap.putDouble(strNextKey, source.getDouble(strNextKey));
                    break;
                case 4:
                    javaOnlyMap.putString(strNextKey, source.getString(strNextKey));
                    break;
                case 5:
                    ReadableMap map = source.getMap(strNextKey);
                    if (map != null && map.hasKey(NODE_TAG_KEY) && map.getType(NODE_TAG_KEY) == ReadableType.Number) {
                        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById == null) {
                            throw new IllegalArgumentException("Mapped value node does not exist".toString());
                        }
                        if (nodeById instanceof ValueAnimatedNode) {
                            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                            Object objectValue = valueAnimatedNode.getObjectValue();
                            if (objectValue instanceof Integer) {
                                javaOnlyMap.putInt(strNextKey, ((Number) objectValue).intValue());
                                break;
                            } else if (objectValue instanceof String) {
                                javaOnlyMap.putString(strNextKey, (String) objectValue);
                                break;
                            } else {
                                javaOnlyMap.putDouble(strNextKey, valueAnimatedNode.getValue());
                                break;
                            }
                        } else if (!(nodeById instanceof ColorAnimatedNode)) {
                            break;
                        } else {
                            javaOnlyMap.putInt(strNextKey, ((ColorAnimatedNode) nodeById).getColor());
                            break;
                        }
                    } else {
                        javaOnlyMap.putMap(strNextKey, collectViewUpdatesHelper(map));
                        break;
                    }
                    break;
                case 6:
                    javaOnlyMap.putArray(strNextKey, collectViewUpdatesHelper(source.getArray(strNextKey)));
                    break;
            }
        }
        return javaOnlyMap;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        return "ObjectAnimatedNode[" + this.tag + "]: mConfig: " + this.configClone;
    }
}
