package com.azendoo.reactnativesnackbar;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.umeng.ccg.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SnackbarModule extends ReactContextBaseJavaModule {
    private static final String ON_SNACKBAR_VISIBILITY_EVENT = "onSnackbarVisibility";
    private static final String REACT_NAME = "RNSnackbar";
    private static final int SHOW_EVENT = 5;
    private final List<Snackbar> mActiveSnackbars;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public SnackbarModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mActiveSnackbars = new ArrayList();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("LENGTH_LONG", 0);
        map.put("LENGTH_SHORT", -1);
        map.put("LENGTH_INDEFINITE", -2);
        map.put("DISMISS_EVENT_SWIPE", 0);
        map.put("DISMISS_EVENT_ACTION", 1);
        map.put("DISMISS_EVENT_TIMEOUT", 2);
        map.put("DISMISS_EVENT_MANUAL", 3);
        map.put("DISMISS_EVENT_CONSECUTIVE", 4);
        map.put("SHOW_EVENT", 5);
        return map;
    }

    @ReactMethod
    public void show(ReadableMap readableMap, Callback callback) {
        try {
            ViewGroup viewGroup = (ViewGroup) getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
            if (viewGroup == null) {
                return;
            }
            this.mActiveSnackbars.clear();
            if (!viewGroup.hasWindowFocus()) {
                ArrayList<View> arrayListRecursiveLoopChildren = recursiveLoopChildren(viewGroup, new ArrayList<>());
                Collections.reverse(arrayListRecursiveLoopChildren);
                Iterator<View> it = arrayListRecursiveLoopChildren.iterator();
                while (it.hasNext()) {
                    View next = it.next();
                    if (next != null) {
                        displaySnackbar(next, readableMap, callback);
                        return;
                    }
                }
                if (viewGroup.getVisibility() == 0) {
                    displaySnackbar(viewGroup, readableMap, callback);
                    return;
                } else {
                    Log.w(REACT_NAME, "Content view is not in focus or not visible");
                    return;
                }
            }
            displaySnackbar(viewGroup, readableMap, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void dismiss() {
        for (Snackbar snackbar : this.mActiveSnackbars) {
            if (snackbar != null) {
                snackbar.dismiss();
            }
        }
        this.mActiveSnackbars.clear();
    }

    private void displaySnackbar(View view, ReadableMap readableMap, final Callback callback) {
        String optionValue = getOptionValue(readableMap, "text", "");
        int optionValue2 = getOptionValue(readableMap, "duration", -1);
        int optionValue3 = getOptionValue(readableMap, ViewProps.NUMBER_OF_LINES, 2);
        int optionValue4 = getOptionValue(readableMap, "textColor", -1);
        boolean optionValue5 = getOptionValue(readableMap, "textAlignCenter", false);
        boolean optionValue6 = getOptionValue(readableMap, "rtl", false);
        int optionValue7 = getOptionValue(readableMap, ViewProps.MARGIN_BOTTOM, 0);
        Typeface typefaceCreateFromAsset = null;
        String optionValue8 = getOptionValue(readableMap, "fontFamily", (String) null);
        if (optionValue8 != null) {
            try {
                typefaceCreateFromAsset = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/" + optionValue8 + ".ttf");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Error("Failed to load font " + optionValue8 + ".ttf, did you include it in your assets?");
            }
        }
        try {
            Snackbar snackbarMake = Snackbar.make(view, optionValue, optionValue2);
            snackbarMake.setAnimationMode(optionValue7 == 0 ? 0 : 1);
            View view2 = snackbarMake.getView();
            if (optionValue6) {
                view2.setLayoutDirection(1);
                view2.setTextDirection(4);
            }
            if (optionValue7 != 0) {
                view2.setTranslationY(-convertDpToPixel(optionValue7, view2.getContext()));
            }
            TextView textView = (TextView) view2.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setMaxLines(optionValue3);
            textView.setTextColor(optionValue4);
            if (optionValue5) {
                textView.setTextAlignment(4);
            } else {
                textView.setTextAlignment(2);
            }
            if (typefaceCreateFromAsset != null) {
                textView.setTypeface(typefaceCreateFromAsset);
            }
            this.mActiveSnackbars.add(snackbarMake);
            if (readableMap.hasKey("backgroundColor")) {
                view2.setBackgroundColor(readableMap.getInt("backgroundColor"));
            }
            if (readableMap.hasKey(a.t)) {
                ReadableMap map = readableMap.getMap(a.t);
                String optionValue9 = getOptionValue(map, "text", "");
                int optionValue10 = getOptionValue(map, "textColor", -1);
                snackbarMake.setAction(optionValue9, new View.OnClickListener() { // from class: com.azendoo.reactnativesnackbar.SnackbarModule.1
                    boolean callbackWasCalled = false;

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        if (this.callbackWasCalled) {
                            return;
                        }
                        this.callbackWasCalled = true;
                        callback.invoke(new Object[0]);
                    }
                });
                snackbarMake.setActionTextColor(optionValue10);
                if (typefaceCreateFromAsset != null) {
                    ((TextView) view2.findViewById(com.google.android.material.R.id.snackbar_action)).setTypeface(typefaceCreateFromAsset);
                }
            }
            snackbarMake.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() { // from class: com.azendoo.reactnativesnackbar.SnackbarModule.2
                @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
                public void onDismissed(Snackbar snackbar, int i) {
                    SnackbarModule.this.sendSnackbarVisibilityEvent(i);
                }

                @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
                public void onShown(Snackbar snackbar) {
                    SnackbarModule.this.sendSnackbarVisibilityEvent(5);
                }
            });
            snackbarMake.show();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public static float convertDpToPixel(float f, Context context) {
        return f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    private ArrayList<View> recursiveLoopChildren(ViewGroup viewGroup, ArrayList<View> arrayList) {
        if (viewGroup.getClass().getSimpleName().equalsIgnoreCase("ReactModalHostView")) {
            arrayList.add(viewGroup.getChildAt(0));
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) childAt, arrayList);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSnackbarVisibilityEvent(int i) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("event", i);
        sendEvent(getReactApplicationContext(), ON_SNACKBAR_VISIBILITY_EVENT, writableMapCreateMap);
    }

    private void sendEvent(ReactContext reactContext, String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    private String getOptionValue(ReadableMap readableMap, String str, String str2) {
        return readableMap.hasKey(str) ? readableMap.getString(str) : str2;
    }

    private int getOptionValue(ReadableMap readableMap, String str, int i) {
        return readableMap.hasKey(str) ? readableMap.getInt(str) : i;
    }

    private boolean getOptionValue(ReadableMap readableMap, String str, boolean z) {
        return readableMap.hasKey(str) ? readableMap.getBoolean(str) : z;
    }
}
