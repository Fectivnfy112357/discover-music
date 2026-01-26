package com.swmansion.rnscreens.gamma.tabs;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ContextThemeWrapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umeng.analytics.pro.f;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TabsHostAppearanceCoordinator.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\rH\u0002J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHostAppearanceCoordinator;", "", f.X, "Landroidx/appcompat/view/ContextThemeWrapper;", "bottomNavigationView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "tabScreenFragments", "", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "(Landroidx/appcompat/view/ContextThemeWrapper;Lcom/google/android/material/bottomnavigation/BottomNavigationView;Ljava/util/List;)V", "appearanceApplicator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostAppearanceApplicator;", "updateMenuItemAppearance", "", "menuItem", "Landroid/view/MenuItem;", "tabScreen", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "updateMenuItems", "updateTabAppearance", "tabsHost", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabsHostAppearanceCoordinator {
    private final TabsHostAppearanceApplicator appearanceApplicator;
    private final BottomNavigationView bottomNavigationView;
    private final List<TabScreenFragment> tabScreenFragments;

    public TabsHostAppearanceCoordinator(ContextThemeWrapper context, BottomNavigationView bottomNavigationView, List<TabScreenFragment> tabScreenFragments) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bottomNavigationView, "bottomNavigationView");
        Intrinsics.checkNotNullParameter(tabScreenFragments, "tabScreenFragments");
        this.bottomNavigationView = bottomNavigationView;
        this.tabScreenFragments = tabScreenFragments;
        this.appearanceApplicator = new TabsHostAppearanceApplicator(context, bottomNavigationView);
    }

    public final void updateTabAppearance(TabsHost tabsHost) {
        Intrinsics.checkNotNullParameter(tabsHost, "tabsHost");
        this.appearanceApplicator.updateSharedAppearance(tabsHost);
        updateMenuItems();
        this.appearanceApplicator.updateFontStyles(tabsHost);
    }

    private final void updateMenuItems() {
        Menu menu = this.bottomNavigationView.getMenu();
        Intrinsics.checkNotNullExpressionValue(menu, "getMenu(...)");
        if (menu.size() != this.tabScreenFragments.size()) {
            this.bottomNavigationView.getMenu().clear();
        }
        int i = 0;
        for (Object obj : this.tabScreenFragments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TabScreenFragment tabScreenFragment = (TabScreenFragment) obj;
            Menu menu2 = this.bottomNavigationView.getMenu();
            Intrinsics.checkNotNullExpressionValue(menu2, "getMenu(...)");
            MenuItem orCreateMenuItem = TabsHostAppearanceCoordinatorKt.getOrCreateMenuItem(menu2, i, tabScreenFragment.getTabScreen());
            if (orCreateMenuItem.getItemId() != i) {
                throw new IllegalStateException("[RNScreens] Illegal state: menu items are shuffled".toString());
            }
            updateMenuItemAppearance(orCreateMenuItem, tabScreenFragment.getTabScreen());
            i = i2;
        }
    }

    public final void updateMenuItemAppearance(MenuItem menuItem, TabScreen tabScreen) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        this.appearanceApplicator.updateMenuItemAppearance(menuItem, tabScreen);
        this.appearanceApplicator.updateBadgeAppearance(menuItem, tabScreen);
    }
}
