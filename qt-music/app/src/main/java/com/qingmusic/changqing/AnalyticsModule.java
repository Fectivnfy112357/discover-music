package com.qingmusic.changqing;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AnalyticsModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;

    public AnalyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "UMAnalyticsModule";
    }

    @ReactMethod
    public void onPageStart(String str) {
        MobclickAgent.onPageStart(str);
    }

    @ReactMethod
    public void onPageEnd(String str) {
        MobclickAgent.onPageEnd(str);
    }

    @ReactMethod
    public void onEvent(String str) {
        MobclickAgent.onEvent(this.context, str);
    }

    @ReactMethod
    public void onEventWithLable(String str, String str2) {
        MobclickAgent.onEvent(this.context, str, str2);
    }

    @ReactMethod
    public void onEventWithMap(String str, ReadableMap readableMap) {
        HashMap map = new HashMap();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (ReadableType.Array == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getArray(strNextKey).toString());
            } else if (ReadableType.Boolean == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getBoolean(strNextKey)));
            } else if (ReadableType.Number == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getInt(strNextKey)));
            } else if (ReadableType.String == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getString(strNextKey));
            } else if (ReadableType.Map == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getMap(strNextKey).toString());
            }
        }
        MobclickAgent.onEvent(this.context, str, map);
    }

    @ReactMethod
    public void onEventWithMapAndCount(String str, ReadableMap readableMap, int i) {
        HashMap map = new HashMap();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (ReadableType.Array == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getArray(strNextKey).toString());
            } else if (ReadableType.Boolean == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getBoolean(strNextKey)));
            } else if (ReadableType.Number == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getInt(strNextKey)));
            } else if (ReadableType.String == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getString(strNextKey));
            } else if (ReadableType.Map == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getMap(strNextKey).toString());
            }
        }
        MobclickAgent.onEventValue(this.context, str, map, i);
    }

    @ReactMethod
    public void onEventObject(String str, ReadableMap readableMap) {
        HashMap map = new HashMap();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (ReadableType.Array == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getArray(strNextKey).toString());
            } else if (ReadableType.Boolean == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getBoolean(strNextKey)));
            } else if (ReadableType.Number == readableMap.getType(strNextKey)) {
                map.put(strNextKey, String.valueOf(readableMap.getInt(strNextKey)));
            } else if (ReadableType.String == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getString(strNextKey));
            } else if (ReadableType.Map == readableMap.getType(strNextKey)) {
                map.put(strNextKey, readableMap.getMap(strNextKey).toString());
            }
        }
        MobclickAgent.onEventObject(this.context, str, map);
    }

    @ReactMethod
    public void registerPreProperties(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : ((ReadableNativeMap) readableMap).toHashMap().entrySet()) {
            try {
                jSONObject.put(entry.getKey(), (String) entry.getValue());
            } catch (JSONException unused) {
            }
        }
        MobclickAgent.registerPreProperties(this.context, jSONObject);
    }

    @ReactMethod
    public void unregisterPreProperty(String str) {
        MobclickAgent.unregisterPreProperty(this.context, str);
    }

    @ReactMethod
    public void getPreProperties(Callback callback) {
        callback.invoke(MobclickAgent.getPreProperties(this.context).toString());
    }

    @ReactMethod
    public void clearPreProperties() {
        MobclickAgent.clearPreProperties(this.context);
    }

    @ReactMethod
    public void setFirstLaunchEvent(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            if (ReadableType.Array == readableArray.getType(i)) {
                arrayList.add(readableArray.getArray(i).toString());
            } else if (ReadableType.Boolean == readableArray.getType(i)) {
                arrayList.add(String.valueOf(readableArray.getBoolean(i)));
            } else if (ReadableType.Number == readableArray.getType(i)) {
                arrayList.add(String.valueOf(readableArray.getInt(i)));
            } else if (ReadableType.String == readableArray.getType(i)) {
                arrayList.add(readableArray.getString(i));
            } else if (ReadableType.Map == readableArray.getType(i)) {
                arrayList.add(readableArray.getMap(i).toString());
            }
        }
        MobclickAgent.setFirstLaunchEvent(this.context, arrayList);
    }

    @ReactMethod
    public void profileSignInWithPUID(String str) {
        MobclickAgent.onProfileSignIn(str);
    }

    @ReactMethod
    public void profileSignInWithPUIDWithProvider(String str, String str2) {
        MobclickAgent.onProfileSignIn(str, str2);
    }

    @ReactMethod
    public void profileSignOff() {
        MobclickAgent.onProfileSignOff();
    }
}
