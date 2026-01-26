package com.swmansion.rnscreens.gamma.tabs;

import android.content.res.Configuration;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;

/* compiled from: TabScreenDelegate.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabScreenDelegate;", "", "getFragmentForTabScreen", "Landroidx/fragment/app/Fragment;", "tabScreen", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "onFragmentConfigurationChange", "", "config", "Landroid/content/res/Configuration;", "onMenuItemAttributesChange", "onTabFocusChangedFromJS", "isFocused", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TabScreenDelegate {
    Fragment getFragmentForTabScreen(TabScreen tabScreen);

    void onFragmentConfigurationChange(TabScreen tabScreen, Configuration config);

    void onMenuItemAttributesChange(TabScreen tabScreen);

    void onTabFocusChangedFromJS(TabScreen tabScreen, boolean isFocused);
}
