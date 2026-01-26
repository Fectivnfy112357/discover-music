package com.swmansion.rnscreens.gamma.tabs;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.rnscreens.gamma.common.FragmentProviding;
import com.swmansion.rnscreens.gamma.helpers.SystemDrawableKt;
import com.swmansion.rnscreens.utils.RNSLog;
import com.umeng.analytics.pro.bm;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* compiled from: TabScreen.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 Y2\u00020\u00012\u00020\u0002:\u0001YB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\n\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010?\u001a\u00020@H\u0014J\u001d\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0000¢\u0006\u0002\bFJ0\u0010G\u001a\u00020@2\u0006\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020(2\u0006\u0010J\u001a\u00020(2\u0006\u0010K\u001a\u00020(2\u0006\u0010L\u001a\u00020(H\u0014J\b\u0010M\u001a\u00020@H\u0002J\b\u0010N\u001a\u00020@H\u0002J\r\u0010O\u001a\u00020@H\u0000¢\u0006\u0002\bPJ\u0017\u0010Q\u001a\u00020@2\b\u0010R\u001a\u0004\u0018\u000108H\u0000¢\u0006\u0002\bSJ#\u0010T\u001a\u00020@\"\u0004\b\u0000\u0010U2\u0006\u0010V\u001a\u0002HU2\u0006\u0010W\u001a\u0002HUH\u0002¢\u0006\u0002\u0010XR/\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R/\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0006\u001a\u0004\u0018\u00010\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001c\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b\u001d\u0010\n\"\u0004\b\u001e\u0010\fR$\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R/\u0010)\u001a\u0004\u0018\u00010(2\b\u0010\u0006\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u000e\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R/\u0010/\u001a\u0004\u0018\u00010(2\b\u0010\u0006\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u000e\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R(\u00103\u001a\u0004\u0018\u00010\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\n\"\u0004\b5\u0010\fR\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020807X\u0082\u000e¢\u0006\u0002\n\u0000R/\u00109\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b<\u0010\u000e\u001a\u0004\b:\u0010\n\"\u0004\b;\u0010\f¨\u0006Z"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "Landroid/view/ViewGroup;", "Lcom/swmansion/rnscreens/gamma/common/FragmentProviding;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "<set-?>", "", "badgeValue", "getBadgeValue", "()Ljava/lang/String;", "setBadgeValue", "(Ljava/lang/String;)V", "badgeValue$delegate", "Lkotlin/properties/ReadWriteProperty;", "eventEmitter", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;", "getEventEmitter$react_native_screens_release", "()Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;", "setEventEmitter$react_native_screens_release", "(Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;)V", "Landroid/graphics/drawable/Drawable;", "icon", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "icon$delegate", "iconResourceName", "getIconResourceName", "setIconResourceName", "iconResourceName$delegate", "value", "", "isFocusedTab", "()Z", "setFocusedTab", "(Z)V", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "", "tabBarItemBadgeBackgroundColor", "getTabBarItemBadgeBackgroundColor", "()Ljava/lang/Integer;", "setTabBarItemBadgeBackgroundColor", "(Ljava/lang/Integer;)V", "tabBarItemBadgeBackgroundColor$delegate", "tabBarItemBadgeTextColor", "getTabBarItemBadgeTextColor", "setTabBarItemBadgeTextColor", "tabBarItemBadgeTextColor$delegate", "tabKey", "getTabKey", "setTabKey", "tabScreenDelegate", "Ljava/lang/ref/WeakReference;", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenDelegate;", "tabTitle", "getTabTitle", "setTabTitle", "tabTitle$delegate", "getAssociatedFragment", "Landroidx/fragment/app/Fragment;", "onAttachedToWindow", "", "onFragmentConfigurationChange", "fragment", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "config", "Landroid/content/res/Configuration;", "onFragmentConfigurationChange$react_native_screens_release", ViewProps.ON_LAYOUT, "changed", CmcdData.Factory.STREAM_TYPE_LIVE, bm.aM, "r", "b", "onMenuItemAttributesChange", "onTabFocusChangedFromJS", "onViewManagerAddEventEmitters", "onViewManagerAddEventEmitters$react_native_screens_release", "setTabScreenDelegate", "delegate", "setTabScreenDelegate$react_native_screens_release", "updateMenuItemAttributesIfNeeded", ExifInterface.GPS_DIRECTION_TRUE, "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabScreen extends ViewGroup implements FragmentProviding {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabTitle", "getTabTitle()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "badgeValue", "getBadgeValue()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemBadgeTextColor", "getTabBarItemBadgeTextColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemBadgeBackgroundColor", "getTabBarItemBadgeBackgroundColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "iconResourceName", "getIconResourceName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "icon", "getIcon()Landroid/graphics/drawable/Drawable;", 0))};
    public static final String TAG = "TabScreen";

    /* renamed from: badgeValue$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty badgeValue;
    public TabScreenEventEmitter eventEmitter;

    /* renamed from: icon$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty icon;

    /* renamed from: iconResourceName$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty iconResourceName;
    private boolean isFocusedTab;
    private final ThemedReactContext reactContext;

    /* renamed from: tabBarItemBadgeBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemBadgeBackgroundColor;

    /* renamed from: tabBarItemBadgeTextColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemBadgeTextColor;
    private String tabKey;
    private WeakReference<TabScreenDelegate> tabScreenDelegate;

    /* renamed from: tabTitle$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabTitle;

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabScreen(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        final Object obj = null;
        this.tabScreenDelegate = new WeakReference<>(null);
        Delegates delegates = Delegates.INSTANCE;
        this.tabTitle = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        this.badgeValue = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates3 = Delegates.INSTANCE;
        this.tabBarItemBadgeTextColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates4 = Delegates.INSTANCE;
        this.tabBarItemBadgeBackgroundColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates5 = Delegates.INSTANCE;
        this.iconResourceName = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                String str = newValue;
                if (Intrinsics.areEqual(str, oldValue)) {
                    return;
                }
                TabScreen tabScreen = this;
                tabScreen.setIcon(SystemDrawableKt.getSystemDrawableResource(tabScreen.getReactContext(), str));
            }
        };
        Delegates delegates6 = Delegates.INSTANCE;
        this.icon = new ObservableProperty<Drawable>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Drawable oldValue, Drawable newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
    }

    public final TabScreenEventEmitter getEventEmitter$react_native_screens_release() {
        TabScreenEventEmitter tabScreenEventEmitter = this.eventEmitter;
        if (tabScreenEventEmitter != null) {
            return tabScreenEventEmitter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventEmitter");
        return null;
    }

    public final void setEventEmitter$react_native_screens_release(TabScreenEventEmitter tabScreenEventEmitter) {
        Intrinsics.checkNotNullParameter(tabScreenEventEmitter, "<set-?>");
        this.eventEmitter = tabScreenEventEmitter;
    }

    public final String getTabKey() {
        return this.tabKey;
    }

    public final void setTabKey(String str) {
        if (str != null && StringsKt.isBlank(str)) {
            str = null;
        }
        this.tabKey = str;
    }

    public final String getTabTitle() {
        return (String) this.tabTitle.getValue(this, $$delegatedProperties[0]);
    }

    public final void setTabTitle(String str) {
        this.tabTitle.setValue(this, $$delegatedProperties[0], str);
    }

    public final String getBadgeValue() {
        return (String) this.badgeValue.getValue(this, $$delegatedProperties[1]);
    }

    public final void setBadgeValue(String str) {
        this.badgeValue.setValue(this, $$delegatedProperties[1], str);
    }

    public final Integer getTabBarItemBadgeTextColor() {
        return (Integer) this.tabBarItemBadgeTextColor.getValue(this, $$delegatedProperties[2]);
    }

    public final void setTabBarItemBadgeTextColor(Integer num) {
        this.tabBarItemBadgeTextColor.setValue(this, $$delegatedProperties[2], num);
    }

    public final Integer getTabBarItemBadgeBackgroundColor() {
        return (Integer) this.tabBarItemBadgeBackgroundColor.getValue(this, $$delegatedProperties[3]);
    }

    public final void setTabBarItemBadgeBackgroundColor(Integer num) {
        this.tabBarItemBadgeBackgroundColor.setValue(this, $$delegatedProperties[3], num);
    }

    public final String getIconResourceName() {
        return (String) this.iconResourceName.getValue(this, $$delegatedProperties[4]);
    }

    public final void setIconResourceName(String str) {
        this.iconResourceName.setValue(this, $$delegatedProperties[4], str);
    }

    public final Drawable getIcon() {
        return (Drawable) this.icon.getValue(this, $$delegatedProperties[5]);
    }

    public final void setIcon(Drawable drawable) {
        this.icon.setValue(this, $$delegatedProperties[5], drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> void updateMenuItemAttributesIfNeeded(T oldValue, T newValue) {
        if (Intrinsics.areEqual(newValue, oldValue)) {
            return;
        }
        onMenuItemAttributesChange();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RNSLog.INSTANCE.d(TAG, "TabScreen [" + getId() + "] attached to window");
        super.onAttachedToWindow();
    }

    /* renamed from: isFocusedTab, reason: from getter */
    public final boolean getIsFocusedTab() {
        return this.isFocusedTab;
    }

    public final void setFocusedTab(boolean z) {
        if (this.isFocusedTab != z) {
            this.isFocusedTab = z;
            onTabFocusChangedFromJS();
        }
    }

    public final void setTabScreenDelegate$react_native_screens_release(TabScreenDelegate delegate) {
        this.tabScreenDelegate = new WeakReference<>(delegate);
    }

    @Override // com.swmansion.rnscreens.gamma.common.FragmentProviding
    public Fragment getAssociatedFragment() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            return tabScreenDelegate.getFragmentForTabScreen(this);
        }
        return null;
    }

    private final void onTabFocusChangedFromJS() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onTabFocusChangedFromJS(this, this.isFocusedTab);
        }
    }

    private final void onMenuItemAttributesChange() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onMenuItemAttributesChange(this);
        }
    }

    public final void onViewManagerAddEventEmitters$react_native_screens_release() {
        if (getId() == -1) {
            throw new IllegalStateException("[RNScreens] TabScreen must have its tag set when registering event emitters".toString());
        }
        setEventEmitter$react_native_screens_release(new TabScreenEventEmitter(this.reactContext, getId()));
    }

    public final void onFragmentConfigurationChange$react_native_screens_release(TabScreenFragment fragment, Configuration config) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(config, "config");
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onFragmentConfigurationChange(this, config);
        }
    }
}
