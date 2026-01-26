package com.swmansion.rnscreens.gamma.tabs;

import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.MenuKt;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: TabsHostAppearanceApplicator.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHostAppearanceApplicator;", "", f.X, "Landroidx/appcompat/view/ContextThemeWrapper;", "bottomNavigationView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "(Landroidx/appcompat/view/ContextThemeWrapper;Lcom/google/android/material/bottomnavigation/BottomNavigationView;)V", "resolveColorAttr", "", "attr", "updateBadgeAppearance", "", "menuItem", "Landroid/view/MenuItem;", "tabScreen", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "updateFontStyles", "tabsHost", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;", "updateMenuItemAppearance", "updateSharedAppearance", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabsHostAppearanceApplicator {
    private final BottomNavigationView bottomNavigationView;
    private final ContextThemeWrapper context;

    public TabsHostAppearanceApplicator(ContextThemeWrapper context, BottomNavigationView bottomNavigationView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bottomNavigationView, "bottomNavigationView");
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
    }

    private final int resolveColorAttr(int attr) {
        TypedValue typedValue = new TypedValue();
        this.context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateSharedAppearance(com.swmansion.rnscreens.gamma.tabs.TabsHost r6) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.gamma.tabs.TabsHostAppearanceApplicator.updateSharedAppearance(com.swmansion.rnscreens.gamma.tabs.TabsHost):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateFontStyles(com.swmansion.rnscreens.gamma.tabs.TabsHost r12) throws android.content.res.Resources.NotFoundException {
        /*
            r11 = this;
            java.lang.String r0 = "tabsHost"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            com.google.android.material.bottomnavigation.BottomNavigationView r0 = r11.bottomNavigationView
            r1 = 0
            android.view.View r0 = r0.getChildAt(r1)
            java.lang.String r2 = "null cannot be cast to non-null type android.view.ViewGroup"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r2)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            java.util.Iterator r0 = r0.iterator()
        L1b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Le4
            java.lang.Object r2 = r0.next()
            android.view.View r2 = (android.view.View) r2
            int r3 = com.google.android.material.R.id.navigation_bar_item_large_label_view
            android.view.View r3 = r2.findViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            int r4 = com.google.android.material.R.id.navigation_bar_item_small_label_view
            android.view.View r2 = r2.findViewById(r4)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r4 = r12.getTabBarItemTitleFontStyle()
            java.lang.String r5 = "italic"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            java.lang.String r5 = r12.getTabBarItemTitleFontWeight()
            java.lang.String r6 = "bold"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L50
            r5 = 700(0x2bc, float:9.81E-43)
            goto L63
        L50:
            java.lang.String r5 = r12.getTabBarItemTitleFontWeight()
            if (r5 == 0) goto L61
            java.lang.Integer r5 = kotlin.text.StringsKt.toIntOrNull(r5)
            if (r5 == 0) goto L61
            int r5 = r5.intValue()
            goto L63
        L61:
            r5 = 400(0x190, float:5.6E-43)
        L63:
            com.facebook.react.common.assets.ReactFontManager$Companion r6 = com.facebook.react.common.assets.ReactFontManager.INSTANCE
            com.facebook.react.common.assets.ReactFontManager r6 = r6.getInstance()
            java.lang.String r7 = r12.getTabBarItemTitleFontFamily()
            if (r7 != 0) goto L71
            java.lang.String r7 = ""
        L71:
            androidx.appcompat.view.ContextThemeWrapper r8 = r11.context
            android.content.res.AssetManager r8 = r8.getAssets()
            android.graphics.Typeface r4 = r6.getTypeface(r7, r5, r4, r8)
            java.lang.Float r5 = r12.getTabBarItemTitleFontSize()
            r6 = 2
            r7 = 0
            r8 = 0
            if (r5 == 0) goto L9e
            r9 = r5
            java.lang.Number r9 = (java.lang.Number) r9
            float r9 = r9.floatValue()
            int r9 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r9 <= 0) goto L90
            goto L91
        L90:
            r5 = r7
        L91:
            if (r5 == 0) goto L9e
            java.lang.Number r5 = (java.lang.Number) r5
            float r5 = r5.floatValue()
            float r5 = com.facebook.react.uimanager.PixelUtil.toPixelFromSP$default(r5, r8, r6, r7)
            goto Laa
        L9e:
            androidx.appcompat.view.ContextThemeWrapper r5 = r11.context
            android.content.res.Resources r5 = r5.getResources()
            int r9 = com.google.android.material.R.dimen.design_bottom_navigation_text_size
            float r5 = r5.getDimension(r9)
        Laa:
            java.lang.Float r9 = r12.getTabBarItemTitleFontSizeActive()
            if (r9 == 0) goto Lca
            r10 = r9
            java.lang.Number r10 = (java.lang.Number) r10
            float r10 = r10.floatValue()
            int r10 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r10 <= 0) goto Lbc
            goto Lbd
        Lbc:
            r9 = r7
        Lbd:
            if (r9 == 0) goto Lca
            java.lang.Number r9 = (java.lang.Number) r9
            float r9 = r9.floatValue()
            float r6 = com.facebook.react.uimanager.PixelUtil.toPixelFromSP$default(r9, r8, r6, r7)
            goto Ld6
        Lca:
            androidx.appcompat.view.ContextThemeWrapper r6 = r11.context
            android.content.res.Resources r6 = r6.getResources()
            int r7 = com.google.android.material.R.dimen.design_bottom_navigation_text_size
            float r6 = r6.getDimension(r7)
        Ld6:
            r2.setTextSize(r1, r5)
            r2.setTypeface(r4)
            r3.setTextSize(r1, r6)
            r3.setTypeface(r4)
            goto L1b
        Le4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.gamma.tabs.TabsHostAppearanceApplicator.updateFontStyles(com.swmansion.rnscreens.gamma.tabs.TabsHost):void");
    }

    public final void updateMenuItemAppearance(MenuItem menuItem, TabScreen tabScreen) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        menuItem.setTitle(tabScreen.getTabTitle());
        menuItem.setIcon(tabScreen.getIcon());
    }

    public final void updateBadgeAppearance(MenuItem menuItem, TabScreen tabScreen) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        Menu menu = this.bottomNavigationView.getMenu();
        Intrinsics.checkNotNullExpressionValue(menu, "getMenu(...)");
        int iIndexOf = SequencesKt.indexOf(MenuKt.getChildren(menu), menuItem);
        String badgeValue = tabScreen.getBadgeValue();
        if (badgeValue == null) {
            BadgeDrawable badge = this.bottomNavigationView.getBadge(iIndexOf);
            if (badge == null) {
                return;
            }
            badge.setVisible(false);
            return;
        }
        Integer intOrNull = StringsKt.toIntOrNull(badgeValue);
        BadgeDrawable orCreateBadge = this.bottomNavigationView.getOrCreateBadge(iIndexOf);
        Intrinsics.checkNotNullExpressionValue(orCreateBadge, "getOrCreateBadge(...)");
        orCreateBadge.setVisible(true);
        orCreateBadge.clearText();
        orCreateBadge.clearNumber();
        if (intOrNull != null) {
            orCreateBadge.setNumber(intOrNull.intValue());
        } else if (!Intrinsics.areEqual(badgeValue, "")) {
            orCreateBadge.setText(badgeValue);
        }
        Integer tabBarItemBadgeTextColor = tabScreen.getTabBarItemBadgeTextColor();
        orCreateBadge.setBadgeTextColor(tabBarItemBadgeTextColor != null ? tabBarItemBadgeTextColor.intValue() : resolveColorAttr(R.attr.colorOnError));
        Integer tabBarItemBadgeBackgroundColor = tabScreen.getTabBarItemBadgeBackgroundColor();
        orCreateBadge.setBackgroundColor(tabBarItemBadgeBackgroundColor != null ? tabBarItemBadgeBackgroundColor.intValue() : resolveColorAttr(R.attr.colorError));
    }
}
