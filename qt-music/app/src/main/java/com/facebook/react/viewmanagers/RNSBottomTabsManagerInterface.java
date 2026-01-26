package com.facebook.react.viewmanagers;

import android.view.View;

/* loaded from: classes2.dex */
public interface RNSBottomTabsManagerInterface<T extends View> {
    void setControlNavigationStateInJS(T t, boolean z);

    void setTabBarBackgroundColor(T t, Integer num);

    void setTabBarItemActiveIndicatorColor(T t, Integer num);

    void setTabBarItemActiveIndicatorEnabled(T t, boolean z);

    void setTabBarItemIconColor(T t, Integer num);

    void setTabBarItemIconColorActive(T t, Integer num);

    void setTabBarItemLabelVisibilityMode(T t, String str);

    void setTabBarItemRippleColor(T t, Integer num);

    void setTabBarItemTitleFontColor(T t, Integer num);

    void setTabBarItemTitleFontColorActive(T t, Integer num);

    void setTabBarItemTitleFontFamily(T t, String str);

    void setTabBarItemTitleFontSize(T t, float f);

    void setTabBarItemTitleFontSizeActive(T t, float f);

    void setTabBarItemTitleFontStyle(T t, String str);

    void setTabBarItemTitleFontWeight(T t, String str);

    void setTabBarMinimizeBehavior(T t, String str);

    void setTabBarTintColor(T t, Integer num);
}
