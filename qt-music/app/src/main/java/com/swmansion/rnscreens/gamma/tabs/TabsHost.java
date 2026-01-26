package com.swmansion.rnscreens.gamma.tabs;

import android.content.res.Configuration;
import android.view.Choreographer;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.material.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.swmansion.rnscreens.gamma.helpers.FragmentManagerHelper;
import com.swmansion.rnscreens.gamma.helpers.ViewIdGenerator;
import com.swmansion.rnscreens.gamma.tabs.TabsHost;
import com.swmansion.rnscreens.utils.RNSLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: TabsHost.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 \u0094\u00012\u00020\u00012\u00020\u0002:\u0004\u0094\u0001\u0095\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020 H\u0002J\b\u0010m\u001a\u00020kH\u0002J\u0012\u0010n\u001a\u0004\u0018\u00010g2\u0006\u0010o\u001a\u00020 H\u0002J\u0012\u0010p\u001a\u0004\u0018\u00010g2\u0006\u0010q\u001a\u00020rH\u0016J\u0012\u0010s\u001a\u0004\u0018\u00010t2\u0006\u0010q\u001a\u00020rH\u0002J\u000f\u0010u\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0002\u0010+J\u001d\u0010v\u001a\u00020k2\u0006\u0010q\u001a\u00020r2\u0006\u0010w\u001a\u00020 H\u0000¢\u0006\u0002\bxJ\b\u0010y\u001a\u00020kH\u0014J\u0012\u0010z\u001a\u00020k2\b\u0010{\u001a\u0004\u0018\u00010|H\u0014J\u0018\u0010}\u001a\u00020k2\u0006\u0010q\u001a\u00020r2\u0006\u0010~\u001a\u00020|H\u0016J\u0010\u0010\u007f\u001a\u00020k2\u0006\u0010q\u001a\u00020rH\u0016J\u001a\u0010\u0080\u0001\u001a\u00020k2\u0006\u0010q\u001a\u00020r2\u0007\u0010\u0081\u0001\u001a\u00020\u0017H\u0016J\u000f\u0010\u0082\u0001\u001a\u00020kH\u0000¢\u0006\u0003\b\u0083\u0001J\t\u0010\u0084\u0001\u001a\u00020kH\u0002J\t\u0010\u0085\u0001\u001a\u00020kH\u0016J\u000f\u0010\u0086\u0001\u001a\u00020kH\u0000¢\u0006\u0003\b\u0087\u0001J\u0018\u0010\u0088\u0001\u001a\u00020k2\u0007\u0010\u0089\u0001\u001a\u00020rH\u0000¢\u0006\u0003\b\u008a\u0001J\u0017\u0010\u008b\u0001\u001a\u00020k2\u0006\u0010w\u001a\u00020 H\u0000¢\u0006\u0003\b\u008c\u0001J\t\u0010\u008d\u0001\u001a\u00020kH\u0002J*\u0010\u008e\u0001\u001a\u00020k\"\u0005\b\u0000\u0010\u008f\u00012\b\u0010\u0090\u0001\u001a\u0003H\u008f\u00012\b\u0010\u0091\u0001\u001a\u0003H\u008f\u0001H\u0002¢\u0006\u0003\u0010\u0092\u0001J\t\u0010\u0093\u0001\u001a\u00020kH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00178F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R/\u0010)\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u001e\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R/\u0010/\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u001e\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R/\u00103\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\u001e\u001a\u0004\b4\u0010+\"\u0004\b5\u0010-R/\u00107\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u001e\u001a\u0004\b8\u0010+\"\u0004\b9\u0010-R/\u0010<\u001a\u0004\u0018\u00010;2\b\u0010\u0018\u001a\u0004\u0018\u00010;8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010\u001e\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R/\u0010B\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u001e\u001a\u0004\bC\u0010+\"\u0004\bD\u0010-R/\u0010F\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010\u001e\u001a\u0004\bG\u0010+\"\u0004\bH\u0010-R/\u0010J\u001a\u0004\u0018\u00010 2\b\u0010\u0018\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010\u001e\u001a\u0004\bK\u0010+\"\u0004\bL\u0010-R/\u0010N\u001a\u0004\u0018\u00010;2\b\u0010\u0018\u001a\u0004\u0018\u00010;8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u0010\u001e\u001a\u0004\bO\u0010>\"\u0004\bP\u0010@R/\u0010S\u001a\u0004\u0018\u00010R2\b\u0010\u0018\u001a\u0004\u0018\u00010R8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010\u001e\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR/\u0010Y\u001a\u0004\u0018\u00010R2\b\u0010\u0018\u001a\u0004\u0018\u00010R8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u0010\u001e\u001a\u0004\bZ\u0010U\"\u0004\b[\u0010WR/\u0010]\u001a\u0004\u0018\u00010;2\b\u0010\u0018\u001a\u0004\u0018\u00010;8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b`\u0010\u001e\u001a\u0004\b^\u0010>\"\u0004\b_\u0010@R/\u0010a\u001a\u0004\u0018\u00010;2\b\u0010\u0018\u001a\u0004\u0018\u00010;8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bd\u0010\u001e\u001a\u0004\bb\u0010>\"\u0004\bc\u0010@R\u0014\u0010e\u001a\b\u0012\u0004\u0012\u00020g0fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020iX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0096\u0001"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;", "Landroid/widget/LinearLayout;", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenDelegate;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "appearanceCoordinator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostAppearanceCoordinator;", "bottomNavigationView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "containerUpdateCoordinator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$ContainerUpdateCoordinator;", "contentView", "Landroid/widget/FrameLayout;", "eventEmitter", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;", "getEventEmitter$react_native_screens_release", "()Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;", "setEventEmitter$react_native_screens_release", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;)V", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "isLayoutEnqueued", "", "<set-?>", "isTabBarItemActiveIndicatorEnabled", "()Z", "setTabBarItemActiveIndicatorEnabled", "(Z)V", "isTabBarItemActiveIndicatorEnabled$delegate", "Lkotlin/properties/ReadWriteProperty;", "lastAppliedUiMode", "", "Ljava/lang/Integer;", "layoutCallback", "Landroid/view/Choreographer$FrameCallback;", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "requireFragmentManager", "getRequireFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "tabBarBackgroundColor", "getTabBarBackgroundColor", "()Ljava/lang/Integer;", "setTabBarBackgroundColor", "(Ljava/lang/Integer;)V", "tabBarBackgroundColor$delegate", "tabBarItemActiveIndicatorColor", "getTabBarItemActiveIndicatorColor", "setTabBarItemActiveIndicatorColor", "tabBarItemActiveIndicatorColor$delegate", "tabBarItemIconColor", "getTabBarItemIconColor", "setTabBarItemIconColor", "tabBarItemIconColor$delegate", "tabBarItemIconColorActive", "getTabBarItemIconColorActive", "setTabBarItemIconColorActive", "tabBarItemIconColorActive$delegate", "", "tabBarItemLabelVisibilityMode", "getTabBarItemLabelVisibilityMode", "()Ljava/lang/String;", "setTabBarItemLabelVisibilityMode", "(Ljava/lang/String;)V", "tabBarItemLabelVisibilityMode$delegate", "tabBarItemRippleColor", "getTabBarItemRippleColor", "setTabBarItemRippleColor", "tabBarItemRippleColor$delegate", "tabBarItemTitleFontColor", "getTabBarItemTitleFontColor", "setTabBarItemTitleFontColor", "tabBarItemTitleFontColor$delegate", "tabBarItemTitleFontColorActive", "getTabBarItemTitleFontColorActive", "setTabBarItemTitleFontColorActive", "tabBarItemTitleFontColorActive$delegate", "tabBarItemTitleFontFamily", "getTabBarItemTitleFontFamily", "setTabBarItemTitleFontFamily", "tabBarItemTitleFontFamily$delegate", "", "tabBarItemTitleFontSize", "getTabBarItemTitleFontSize", "()Ljava/lang/Float;", "setTabBarItemTitleFontSize", "(Ljava/lang/Float;)V", "tabBarItemTitleFontSize$delegate", "tabBarItemTitleFontSizeActive", "getTabBarItemTitleFontSizeActive", "setTabBarItemTitleFontSizeActive", "tabBarItemTitleFontSizeActive$delegate", "tabBarItemTitleFontStyle", "getTabBarItemTitleFontStyle", "setTabBarItemTitleFontStyle", "tabBarItemTitleFontStyle$delegate", "tabBarItemTitleFontWeight", "getTabBarItemTitleFontWeight", "setTabBarItemTitleFontWeight", "tabBarItemTitleFontWeight$delegate", "tabScreenFragments", "", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "wrappedContext", "Landroidx/appcompat/view/ContextThemeWrapper;", "applyDayNightUiModeIfNeeded", "", "uiMode", "forceSubtreeMeasureAndLayoutPass", "getFragmentForMenuItemId", "itemId", "getFragmentForTabScreen", "tabScreen", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "getMenuItemForTabScreen", "Landroid/view/MenuItem;", "getSelectedTabScreenFragmentId", "mountReactSubviewAt", "index", "mountReactSubviewAt$react_native_screens_release", "onAttachedToWindow", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onFragmentConfigurationChange", "config", "onMenuItemAttributesChange", "onTabFocusChangedFromJS", "isFocused", "onViewManagerAddEventEmitters", "onViewManagerAddEventEmitters$react_native_screens_release", "refreshLayout", "requestLayout", "unmountAllReactSubviews", "unmountAllReactSubviews$react_native_screens_release", "unmountReactSubview", "reactSubview", "unmountReactSubview$react_native_screens_release", "unmountReactSubviewAt", "unmountReactSubviewAt$react_native_screens_release", "updateBottomNavigationViewAppearance", "updateNavigationMenuIfNeeded", ExifInterface.GPS_DIRECTION_TRUE, "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "updateSelectedTab", "Companion", "ContainerUpdateCoordinator", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabsHost extends LinearLayout implements TabScreenDelegate {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarBackgroundColor", "getTabBarBackgroundColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemActiveIndicatorColor", "getTabBarItemActiveIndicatorColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "isTabBarItemActiveIndicatorEnabled", "isTabBarItemActiveIndicatorEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemIconColor", "getTabBarItemIconColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontFamily", "getTabBarItemTitleFontFamily()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemIconColorActive", "getTabBarItemIconColorActive()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontColor", "getTabBarItemTitleFontColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontColorActive", "getTabBarItemTitleFontColorActive()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontSize", "getTabBarItemTitleFontSize()Ljava/lang/Float;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontSizeActive", "getTabBarItemTitleFontSizeActive()Ljava/lang/Float;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontWeight", "getTabBarItemTitleFontWeight()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontStyle", "getTabBarItemTitleFontStyle()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemRippleColor", "getTabBarItemRippleColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemLabelVisibilityMode", "getTabBarItemLabelVisibilityMode()Ljava/lang/String;", 0))};
    public static final String TAG = "TabsHost";
    private final TabsHostAppearanceCoordinator appearanceCoordinator;
    private final BottomNavigationView bottomNavigationView;
    private final ContainerUpdateCoordinator containerUpdateCoordinator;
    private final FrameLayout contentView;
    public TabsHostEventEmitter eventEmitter;
    private FragmentManager fragmentManager;
    private boolean isLayoutEnqueued;

    /* renamed from: isTabBarItemActiveIndicatorEnabled$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isTabBarItemActiveIndicatorEnabled;
    private Integer lastAppliedUiMode;
    private final Choreographer.FrameCallback layoutCallback;
    private final ThemedReactContext reactContext;

    /* renamed from: tabBarBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarBackgroundColor;

    /* renamed from: tabBarItemActiveIndicatorColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemActiveIndicatorColor;

    /* renamed from: tabBarItemIconColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemIconColor;

    /* renamed from: tabBarItemIconColorActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemIconColorActive;

    /* renamed from: tabBarItemLabelVisibilityMode$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemLabelVisibilityMode;

    /* renamed from: tabBarItemRippleColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemRippleColor;

    /* renamed from: tabBarItemTitleFontColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontColor;

    /* renamed from: tabBarItemTitleFontColorActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontColorActive;

    /* renamed from: tabBarItemTitleFontFamily$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontFamily;

    /* renamed from: tabBarItemTitleFontSize$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontSize;

    /* renamed from: tabBarItemTitleFontSizeActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontSizeActive;

    /* renamed from: tabBarItemTitleFontStyle$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontStyle;

    /* renamed from: tabBarItemTitleFontWeight$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontWeight;
    private final List<TabScreenFragment> tabScreenFragments;
    private final ContextThemeWrapper wrappedContext;

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabsHost(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.containerUpdateCoordinator = new ContainerUpdateCoordinator();
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(reactContext, R.style.Theme_Material3_DayNight_NoActionBar);
        this.wrappedContext = contextThemeWrapper;
        BottomNavigationView bottomNavigationView = new BottomNavigationView(contextThemeWrapper);
        bottomNavigationView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.bottomNavigationView = bottomNavigationView;
        FrameLayout frameLayout = new FrameLayout(reactContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.setId(ViewIdGenerator.INSTANCE.generateViewId());
        this.contentView = frameLayout;
        ArrayList arrayList = new ArrayList();
        this.tabScreenFragments = arrayList;
        this.appearanceCoordinator = new TabsHostAppearanceCoordinator(contextThemeWrapper, bottomNavigationView, arrayList);
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.tabBarBackgroundColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        this.tabBarItemActiveIndicatorColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates3 = Delegates.INSTANCE;
        final boolean z = true;
        this.isTabBarItemActiveIndicatorEnabled = new ObservableProperty<Boolean>(z) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                this.updateNavigationMenuIfNeeded(Boolean.valueOf(oldValue.booleanValue()), Boolean.valueOf(zBooleanValue));
            }
        };
        Delegates delegates4 = Delegates.INSTANCE;
        this.tabBarItemIconColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates5 = Delegates.INSTANCE;
        this.tabBarItemTitleFontFamily = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates6 = Delegates.INSTANCE;
        this.tabBarItemIconColorActive = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates7 = Delegates.INSTANCE;
        this.tabBarItemTitleFontColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates8 = Delegates.INSTANCE;
        this.tabBarItemTitleFontColorActive = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates9 = Delegates.INSTANCE;
        this.tabBarItemTitleFontSize = new ObservableProperty<Float>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$9
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates10 = Delegates.INSTANCE;
        this.tabBarItemTitleFontSizeActive = new ObservableProperty<Float>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$10
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates11 = Delegates.INSTANCE;
        this.tabBarItemTitleFontWeight = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$11
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates12 = Delegates.INSTANCE;
        this.tabBarItemTitleFontStyle = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$12
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates13 = Delegates.INSTANCE;
        this.tabBarItemRippleColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$13
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates14 = Delegates.INSTANCE;
        this.tabBarItemLabelVisibilityMode = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$14
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        setOrientation(1);
        addView(frameLayout);
        addView(bottomNavigationView);
        bottomNavigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                TabsHost._init_$lambda$19(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                return TabsHost._init_$lambda$20(this.f$0, menuItem);
            }
        });
        this.layoutCallback = new Choreographer.FrameCallback() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda2
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                TabsHost.layoutCallback$lambda$42(this.f$0, j);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TabsHost.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\b\u0010\u000e\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$ContainerUpdateCoordinator;", "", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;)V", "isBottomNavigationMenuInvalidated", "", "isSelectedTabInvalidated", "isUpdatePending", "invalidateAll", "", "invalidateNavigationMenu", "invalidateSelectedTab", "postContainerUpdate", "postContainerUpdateIfNeeded", "runContainerUpdate", "runContainerUpdateIfNeeded", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    final class ContainerUpdateCoordinator {
        private boolean isBottomNavigationMenuInvalidated;
        private boolean isSelectedTabInvalidated;
        private boolean isUpdatePending;

        public ContainerUpdateCoordinator() {
        }

        public final void invalidateSelectedTab() {
            this.isSelectedTabInvalidated = true;
        }

        public final void invalidateNavigationMenu() {
            this.isBottomNavigationMenuInvalidated = true;
        }

        public final void invalidateAll() {
            invalidateSelectedTab();
            invalidateNavigationMenu();
        }

        public final void postContainerUpdateIfNeeded() {
            if (this.isUpdatePending) {
                return;
            }
            postContainerUpdate();
        }

        public final void postContainerUpdate() {
            this.isUpdatePending = true;
            TabsHost.this.post(new Runnable() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$ContainerUpdateCoordinator$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TabsHost.ContainerUpdateCoordinator.postContainerUpdate$lambda$0(this.f$0);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void postContainerUpdate$lambda$0(ContainerUpdateCoordinator this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.runContainerUpdateIfNeeded();
        }

        private final void runContainerUpdateIfNeeded() {
            if (this.isUpdatePending) {
                runContainerUpdate();
            }
        }

        public final void runContainerUpdate() {
            this.isUpdatePending = false;
            if (this.isSelectedTabInvalidated) {
                this.isSelectedTabInvalidated = false;
                TabsHost.this.updateSelectedTab();
            }
            if (this.isBottomNavigationMenuInvalidated) {
                this.isBottomNavigationMenuInvalidated = false;
                TabsHost.this.updateBottomNavigationViewAppearance();
            }
        }
    }

    public final TabsHostEventEmitter getEventEmitter$react_native_screens_release() {
        TabsHostEventEmitter tabsHostEventEmitter = this.eventEmitter;
        if (tabsHostEventEmitter != null) {
            return tabsHostEventEmitter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventEmitter");
        return null;
    }

    public final void setEventEmitter$react_native_screens_release(TabsHostEventEmitter tabsHostEventEmitter) {
        Intrinsics.checkNotNullParameter(tabsHostEventEmitter, "<set-?>");
        this.eventEmitter = tabsHostEventEmitter;
    }

    private final FragmentManager getRequireFragmentManager() {
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("[RNScreens] Nullish fragment manager".toString());
    }

    public final Integer getTabBarBackgroundColor() {
        return (Integer) this.tabBarBackgroundColor.getValue(this, $$delegatedProperties[0]);
    }

    public final void setTabBarBackgroundColor(Integer num) {
        this.tabBarBackgroundColor.setValue(this, $$delegatedProperties[0], num);
    }

    public final Integer getTabBarItemActiveIndicatorColor() {
        return (Integer) this.tabBarItemActiveIndicatorColor.getValue(this, $$delegatedProperties[1]);
    }

    public final void setTabBarItemActiveIndicatorColor(Integer num) {
        this.tabBarItemActiveIndicatorColor.setValue(this, $$delegatedProperties[1], num);
    }

    public final boolean isTabBarItemActiveIndicatorEnabled() {
        return ((Boolean) this.isTabBarItemActiveIndicatorEnabled.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public final void setTabBarItemActiveIndicatorEnabled(boolean z) {
        this.isTabBarItemActiveIndicatorEnabled.setValue(this, $$delegatedProperties[2], Boolean.valueOf(z));
    }

    public final Integer getTabBarItemIconColor() {
        return (Integer) this.tabBarItemIconColor.getValue(this, $$delegatedProperties[3]);
    }

    public final void setTabBarItemIconColor(Integer num) {
        this.tabBarItemIconColor.setValue(this, $$delegatedProperties[3], num);
    }

    public final String getTabBarItemTitleFontFamily() {
        return (String) this.tabBarItemTitleFontFamily.getValue(this, $$delegatedProperties[4]);
    }

    public final void setTabBarItemTitleFontFamily(String str) {
        this.tabBarItemTitleFontFamily.setValue(this, $$delegatedProperties[4], str);
    }

    public final Integer getTabBarItemIconColorActive() {
        return (Integer) this.tabBarItemIconColorActive.getValue(this, $$delegatedProperties[5]);
    }

    public final void setTabBarItemIconColorActive(Integer num) {
        this.tabBarItemIconColorActive.setValue(this, $$delegatedProperties[5], num);
    }

    public final Integer getTabBarItemTitleFontColor() {
        return (Integer) this.tabBarItemTitleFontColor.getValue(this, $$delegatedProperties[6]);
    }

    public final void setTabBarItemTitleFontColor(Integer num) {
        this.tabBarItemTitleFontColor.setValue(this, $$delegatedProperties[6], num);
    }

    public final Integer getTabBarItemTitleFontColorActive() {
        return (Integer) this.tabBarItemTitleFontColorActive.getValue(this, $$delegatedProperties[7]);
    }

    public final void setTabBarItemTitleFontColorActive(Integer num) {
        this.tabBarItemTitleFontColorActive.setValue(this, $$delegatedProperties[7], num);
    }

    public final Float getTabBarItemTitleFontSize() {
        return (Float) this.tabBarItemTitleFontSize.getValue(this, $$delegatedProperties[8]);
    }

    public final void setTabBarItemTitleFontSize(Float f) {
        this.tabBarItemTitleFontSize.setValue(this, $$delegatedProperties[8], f);
    }

    public final Float getTabBarItemTitleFontSizeActive() {
        return (Float) this.tabBarItemTitleFontSizeActive.getValue(this, $$delegatedProperties[9]);
    }

    public final void setTabBarItemTitleFontSizeActive(Float f) {
        this.tabBarItemTitleFontSizeActive.setValue(this, $$delegatedProperties[9], f);
    }

    public final String getTabBarItemTitleFontWeight() {
        return (String) this.tabBarItemTitleFontWeight.getValue(this, $$delegatedProperties[10]);
    }

    public final void setTabBarItemTitleFontWeight(String str) {
        this.tabBarItemTitleFontWeight.setValue(this, $$delegatedProperties[10], str);
    }

    public final String getTabBarItemTitleFontStyle() {
        return (String) this.tabBarItemTitleFontStyle.getValue(this, $$delegatedProperties[11]);
    }

    public final void setTabBarItemTitleFontStyle(String str) {
        this.tabBarItemTitleFontStyle.setValue(this, $$delegatedProperties[11], str);
    }

    public final Integer getTabBarItemRippleColor() {
        return (Integer) this.tabBarItemRippleColor.getValue(this, $$delegatedProperties[12]);
    }

    public final void setTabBarItemRippleColor(Integer num) {
        this.tabBarItemRippleColor.setValue(this, $$delegatedProperties[12], num);
    }

    public final String getTabBarItemLabelVisibilityMode() {
        return (String) this.tabBarItemLabelVisibilityMode.getValue(this, $$delegatedProperties[13]);
    }

    public final void setTabBarItemLabelVisibilityMode(String str) {
        this.tabBarItemLabelVisibilityMode.setValue(this, $$delegatedProperties[13], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> void updateNavigationMenuIfNeeded(T oldValue, T newValue) {
        if (Intrinsics.areEqual(newValue, oldValue)) {
            return;
        }
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateNavigationMenu();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$19(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        RNSLog.INSTANCE.d(TAG, "BottomNavigationView layout changed {" + i + ", " + i2 + "} {" + (i3 - i) + ", " + (i4 - i2) + "}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$20(TabsHost this$0, MenuItem item) {
        String tabKey;
        TabScreen tabScreen;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "item");
        RNSLog.INSTANCE.d(TAG, "Item selected " + item);
        TabScreenFragment fragmentForMenuItemId = this$0.getFragmentForMenuItemId(item.getItemId());
        if (fragmentForMenuItemId == null || (tabScreen = fragmentForMenuItemId.getTabScreen()) == null || (tabKey = tabScreen.getTabKey()) == null) {
            tabKey = "undefined";
        }
        this$0.getEventEmitter$react_native_screens_release().emitOnNativeFocusChange(tabKey);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RNSLog.INSTANCE.d(TAG, "TabsHost [" + getId() + "] attached to window");
        super.onAttachedToWindow();
        FragmentManager fragmentManagerFindFragmentManagerForView = FragmentManagerHelper.INSTANCE.findFragmentManagerForView(this);
        if (fragmentManagerFindFragmentManagerForView != null) {
            this.fragmentManager = fragmentManagerFindFragmentManagerForView;
            return;
        }
        throw new IllegalStateException("[RNScreens] Nullish fragment manager - can't run container operations".toString());
    }

    public final void mountReactSubviewAt$react_native_screens_release(TabScreen tabScreen, int index) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        if (index >= this.bottomNavigationView.getMaxItemCount()) {
            throw new IllegalArgumentException(("[RNScreens] Attempt to insert TabScreen at index " + index + "; BottomNavigationView supports at most " + this.bottomNavigationView.getMaxItemCount() + " items").toString());
        }
        this.tabScreenFragments.add(index, new TabScreenFragment(tabScreen));
        tabScreen.setTabScreenDelegate$react_native_screens_release(this);
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    public final void unmountReactSubviewAt$react_native_screens_release(int index) {
        this.tabScreenFragments.remove(index).getTabScreen().setTabScreenDelegate$react_native_screens_release(null);
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean unmountReactSubview$lambda$27(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    public final void unmountReactSubview$react_native_screens_release(final TabScreen reactSubview) {
        Intrinsics.checkNotNullParameter(reactSubview, "reactSubview");
        List<TabScreenFragment> list = this.tabScreenFragments;
        final Function1<TabScreenFragment, Boolean> function1 = new Function1<TabScreenFragment, Boolean>() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$unmountReactSubview$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(TabScreenFragment it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.getTabScreen() == reactSubview);
            }
        };
        Boolean boolValueOf = Boolean.valueOf(list.removeIf(new Predicate() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TabsHost.unmountReactSubview$lambda$27(function1, obj);
            }
        }));
        if (!boolValueOf.booleanValue()) {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            boolValueOf.booleanValue();
            reactSubview.setTabScreenDelegate$react_native_screens_release(null);
            ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
            containerUpdateCoordinator.invalidateAll();
            containerUpdateCoordinator.postContainerUpdateIfNeeded();
        }
    }

    public final void unmountAllReactSubviews$react_native_screens_release() {
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (it.hasNext()) {
            ((TabScreenFragment) it.next()).getTabScreen().setTabScreenDelegate$react_native_screens_release(null);
        }
        this.tabScreenFragments.clear();
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onTabFocusChangedFromJS(TabScreen tabScreen, boolean isFocused) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onMenuItemAttributesChange(TabScreen tabScreen) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        MenuItem menuItemForTabScreen = getMenuItemForTabScreen(tabScreen);
        if (menuItemForTabScreen != null) {
            this.appearanceCoordinator.updateMenuItemAppearance(menuItemForTabScreen, tabScreen);
        }
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public TabScreenFragment getFragmentForTabScreen(TabScreen tabScreen) {
        Object next;
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((TabScreenFragment) next).getTabScreen() == tabScreen) {
                break;
            }
        }
        return (TabScreenFragment) next;
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onFragmentConfigurationChange(TabScreen tabScreen, Configuration config) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        Intrinsics.checkNotNullParameter(config, "config");
        onConfigurationChanged(config);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateBottomNavigationViewAppearance() {
        RNSLog.INSTANCE.d(TAG, "updateBottomNavigationViewAppearance");
        this.appearanceCoordinator.updateTabAppearance(this);
        BottomNavigationView bottomNavigationView = this.bottomNavigationView;
        Integer selectedTabScreenFragmentId = getSelectedTabScreenFragmentId();
        if (selectedTabScreenFragmentId != null) {
            bottomNavigationView.setSelectedItemId(selectedTabScreenFragmentId.intValue());
            post(new Runnable() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    TabsHost.updateBottomNavigationViewAppearance$lambda$37(this.f$0);
                }
            });
            return;
        }
        throw new IllegalStateException("[RNScreens] A single selected tab must be present".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateBottomNavigationViewAppearance$lambda$37(TabsHost this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshLayout();
        RNSLog.INSTANCE.d(TAG, "BottomNavigationView request layout");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectedTab() {
        Object next;
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((TabScreenFragment) next).getTabScreen().getIsFocusedTab()) {
                    break;
                }
            }
        }
        if (next == null) {
            throw new IllegalStateException("[RNScreens] No focused tab present".toString());
        }
        TabScreenFragment tabScreenFragment = (TabScreenFragment) next;
        if (getRequireFragmentManager().getFragments().size() > 1) {
            throw new IllegalStateException("[RNScreens] There can be only a single focused tab".toString());
        }
        List<Fragment> fragments = getRequireFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
        Fragment fragment = (Fragment) CollectionsKt.firstOrNull((List) fragments);
        if (tabScreenFragment == fragment) {
            return;
        }
        FragmentTransaction reorderingAllowed = getRequireFragmentManager().beginTransaction().setReorderingAllowed(true);
        if (fragment != null) {
            reorderingAllowed.remove(fragment);
        }
        reorderingAllowed.add(this.contentView.getId(), tabScreenFragment);
        reorderingAllowed.commitNowAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void layoutCallback$lambda$42(TabsHost this$0, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isLayoutEnqueued = false;
        this$0.forceSubtreeMeasureAndLayoutPass();
    }

    private final void refreshLayout() {
        if (this.isLayoutEnqueued || this.layoutCallback == null) {
            return;
        }
        this.isLayoutEnqueued = true;
        ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.layoutCallback);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        refreshLayout();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig != null) {
            applyDayNightUiModeIfNeeded(newConfig.uiMode & 48);
        }
    }

    private final void applyDayNightUiModeIfNeeded(int uiMode) {
        Integer num = this.lastAppliedUiMode;
        if (num != null && uiMode == num.intValue()) {
            return;
        }
        if (uiMode == 16) {
            this.wrappedContext.setTheme(R.style.Theme_Material3_Light_NoActionBar);
        } else if (uiMode == 32) {
            this.wrappedContext.setTheme(R.style.Theme_Material3_Dark_NoActionBar);
        } else {
            this.wrappedContext.setTheme(R.style.Theme_Material3_DayNight_NoActionBar);
        }
        this.appearanceCoordinator.updateTabAppearance(this);
        this.lastAppliedUiMode = Integer.valueOf(uiMode);
    }

    private final void forceSubtreeMeasureAndLayoutPass() {
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom());
    }

    private final TabScreenFragment getFragmentForMenuItemId(int itemId) {
        return (TabScreenFragment) CollectionsKt.getOrNull(this.tabScreenFragments, itemId);
    }

    private final Integer getSelectedTabScreenFragmentId() {
        if (this.tabScreenFragments.isEmpty()) {
            return null;
        }
        Iterator<TabScreenFragment> it = this.tabScreenFragments.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getTabScreen().getIsFocusedTab()) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    private final MenuItem getMenuItemForTabScreen(TabScreen tabScreen) {
        Iterator<TabScreenFragment> it = this.tabScreenFragments.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getTabScreen() == tabScreen) {
                break;
            }
            i++;
        }
        Integer numValueOf = Integer.valueOf(i);
        if (numValueOf.intValue() == -1) {
            numValueOf = null;
        }
        if (numValueOf == null) {
            return null;
        }
        return this.bottomNavigationView.getMenu().findItem(numValueOf.intValue());
    }

    public final void onViewManagerAddEventEmitters$react_native_screens_release() {
        if (getId() == -1) {
            throw new IllegalStateException("[RNScreens] TabsHost must have its tag set when registering event emitters".toString());
        }
        setEventEmitter$react_native_screens_release(new TabsHostEventEmitter(this.reactContext, getId()));
    }
}
