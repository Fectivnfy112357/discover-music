package com.swmansion.rnscreens.gamma.tabs;

import android.view.View;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSBottomTabsManagerDelegate;
import com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface;
import com.swmansion.rnscreens.gamma.helpers.EventHelpersKt;
import com.swmansion.rnscreens.gamma.tabs.event.TabsHostNativeFocusChangeEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TabsHostViewManager.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0007\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u00011B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J \u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0014J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\u0014J\u0014\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u001c\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001f\u0010\u001f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u0018\u0010\"\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001eH\u0017J\u001f\u0010#\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001f\u0010$\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001a\u0010%\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0017J\u001f\u0010&\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001f\u0010'\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001f\u0010(\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010 J\u001a\u0010)\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0017J\u001a\u0010*\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001d\u001a\u00020+H\u0017J\u001a\u0010,\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001d\u001a\u00020+H\u0017J\u001a\u0010-\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0017J\u001a\u0010.\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0017J\u001a\u0010/\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0016J\u001f\u00100\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010 R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHostViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;", "Lcom/facebook/react/viewmanagers/RNSBottomTabsManagerInterface;", "()V", "delegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "addEventEmitters", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "addView", "parent", "child", "Landroid/view/View;", "index", "", "createViewInstance", "getDelegate", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "removeAllViews", "removeView", "removeViewAt", "setControlNavigationStateInJS", "value", "", "setTabBarBackgroundColor", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;Ljava/lang/Integer;)V", "setTabBarItemActiveIndicatorColor", "setTabBarItemActiveIndicatorEnabled", "setTabBarItemIconColor", "setTabBarItemIconColorActive", "setTabBarItemLabelVisibilityMode", "setTabBarItemRippleColor", "setTabBarItemTitleFontColor", "setTabBarItemTitleFontColorActive", "setTabBarItemTitleFontFamily", "setTabBarItemTitleFontSize", "", "setTabBarItemTitleFontSizeActive", "setTabBarItemTitleFontStyle", "setTabBarItemTitleFontWeight", "setTabBarMinimizeBehavior", "setTabBarTintColor", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@ReactModule(name = TabsHostViewManager.REACT_CLASS)
/* loaded from: classes3.dex */
public final class TabsHostViewManager extends ViewGroupManager<TabsHost> implements RNSBottomTabsManagerInterface<TabsHost> {
    public static final String REACT_CLASS = "RNSBottomTabs";
    private final ViewManagerDelegate<TabsHost> delegate = new RNSBottomTabsManagerDelegate(this);

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    public void setControlNavigationStateInJS(TabsHost view, boolean value) {
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    public void setTabBarMinimizeBehavior(TabsHost view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    public void setTabBarTintColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public TabsHost createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new TabsHost(reactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<TabsHost> getDelegate() {
        return this.delegate;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(TabsHost parent, View child, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        if (!(child instanceof TabScreen)) {
            throw new IllegalArgumentException("[RNScreens] Attempt to attach child that is not of type javaClass".toString());
        }
        parent.mountReactSubviewAt$react_native_screens_release((TabScreen) child, index);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeView(TabsHost parent, View child) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        if (!(child instanceof TabScreen)) {
            throw new IllegalArgumentException("[RNScreens] Attempt to detach child that is not of type javaClass".toString());
        }
        parent.unmountReactSubview$react_native_screens_release((TabScreen) child);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(TabsHost parent, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        parent.unmountReactSubviewAt$react_native_screens_release(index);
    }

    @Override // com.facebook.react.uimanager.IViewGroupManager
    public void removeAllViews(TabsHost parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        parent.unmountAllReactSubviews$react_native_screens_release();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapsKt.mutableMapOf(EventHelpersKt.makeEventRegistrationInfo(TabsHostNativeFocusChangeEvent.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext reactContext, TabsHost view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        super.addEventEmitters(reactContext, (ThemedReactContext) view);
        view.onViewManagerAddEventEmitters$react_native_screens_release();
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarBackgroundColor")
    public void setTabBarBackgroundColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarBackgroundColor(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemTitleFontSize")
    public void setTabBarItemTitleFontSize(TabsHost view, float value) {
        if (view == null) {
            return;
        }
        view.setTabBarItemTitleFontSize(Float.valueOf(value));
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemTitleFontFamily")
    public void setTabBarItemTitleFontFamily(TabsHost view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemTitleFontFamily(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemTitleFontWeight")
    public void setTabBarItemTitleFontWeight(TabsHost view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemTitleFontWeight(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemTitleFontStyle")
    public void setTabBarItemTitleFontStyle(TabsHost view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemTitleFontStyle(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemTitleFontColor")
    public void setTabBarItemTitleFontColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemTitleFontColor(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemIconColor")
    public void setTabBarItemIconColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemIconColor(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemTitleFontColorActive")
    public void setTabBarItemTitleFontColorActive(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemTitleFontColorActive(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemActiveIndicatorColor")
    public void setTabBarItemActiveIndicatorColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemActiveIndicatorColor(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemActiveIndicatorEnabled")
    public void setTabBarItemActiveIndicatorEnabled(TabsHost view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemActiveIndicatorEnabled(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemIconColorActive")
    public void setTabBarItemIconColorActive(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemIconColorActive(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemTitleFontSizeActive")
    public void setTabBarItemTitleFontSizeActive(TabsHost view, float value) {
        if (view == null) {
            return;
        }
        view.setTabBarItemTitleFontSizeActive(Float.valueOf(value));
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(customType = "Color", name = "tabBarItemRippleColor")
    public void setTabBarItemRippleColor(TabsHost view, Integer value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemRippleColor(value);
    }

    @Override // com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface
    @ReactProp(name = "tabBarItemLabelVisibilityMode")
    public void setTabBarItemLabelVisibilityMode(TabsHost view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTabBarItemLabelVisibilityMode(value);
    }
}
