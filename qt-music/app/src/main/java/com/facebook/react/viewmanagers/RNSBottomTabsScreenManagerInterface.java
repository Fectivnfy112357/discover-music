package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes2.dex */
public interface RNSBottomTabsScreenManagerInterface<T extends View> {
    void setBadgeValue(T t, String str);

    void setIconImageSource(T t, ReadableMap readableMap);

    void setIconResource(T t, ReadableMap readableMap);

    void setIconResourceName(T t, String str);

    void setIconSfSymbolName(T t, String str);

    void setIconType(T t, String str);

    void setIsFocused(T t, boolean z);

    void setOrientation(T t, String str);

    void setOverrideScrollViewContentInsetAdjustmentBehavior(T t, boolean z);

    void setScrollEdgeAppearance(T t, Dynamic dynamic);

    void setSelectedIconImageSource(T t, ReadableMap readableMap);

    void setSelectedIconSfSymbolName(T t, String str);

    void setSpecialEffects(T t, ReadableMap readableMap);

    void setStandardAppearance(T t, Dynamic dynamic);

    void setSystemItem(T t, String str);

    void setTabBarItemBadgeBackgroundColor(T t, Integer num);

    void setTabBarItemBadgeTextColor(T t, Integer num);

    void setTabKey(T t, String str);

    void setTitle(T t, String str);
}
