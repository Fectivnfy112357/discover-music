package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSBottomTabsScreenManagerInterface;

/* loaded from: classes2.dex */
public class RNSBottomTabsScreenManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSBottomTabsScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSBottomTabsScreenManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1770963447:
                if (str.equals("specialEffects")) {
                    c = 0;
                    break;
                }
                break;
            case -1559978286:
                if (str.equals("iconResourceName")) {
                    c = 1;
                    break;
                }
                break;
            case -1439500848:
                if (str.equals("orientation")) {
                    c = 2;
                    break;
                }
                break;
            case -1270820115:
                if (str.equals("isFocused")) {
                    c = 3;
                    break;
                }
                break;
            case -1186468415:
                if (str.equals("overrideScrollViewContentInsetAdjustmentBehavior")) {
                    c = 4;
                    break;
                }
                break;
            case -881409398:
                if (str.equals("tabKey")) {
                    c = 5;
                    break;
                }
                break;
            case -776576227:
                if (str.equals("iconImageSource")) {
                    c = 6;
                    break;
                }
                break;
            case -737911981:
                if (str.equals("iconType")) {
                    c = 7;
                    break;
                }
                break;
            case -569869622:
                if (str.equals("selectedIconSfSymbolName")) {
                    c = '\b';
                    break;
                }
                break;
            case -270334418:
                if (str.equals("scrollEdgeAppearance")) {
                    c = '\t';
                    break;
                }
                break;
            case 28389121:
                if (str.equals("standardAppearance")) {
                    c = '\n';
                    break;
                }
                break;
            case 110371416:
                if (str.equals("title")) {
                    c = 11;
                    break;
                }
                break;
            case 143186447:
                if (str.equals("iconSfSymbolName")) {
                    c = '\f';
                    break;
                }
                break;
            case 642560482:
                if (str.equals("systemItem")) {
                    c = '\r';
                    break;
                }
                break;
            case 1001537282:
                if (str.equals("selectedIconImageSource")) {
                    c = 14;
                    break;
                }
                break;
            case 1072026510:
                if (str.equals("badgeValue")) {
                    c = 15;
                    break;
                }
                break;
            case 1293147047:
                if (str.equals("iconResource")) {
                    c = 16;
                    break;
                }
                break;
            case 1519110851:
                if (str.equals("tabBarItemBadgeBackgroundColor")) {
                    c = 17;
                    break;
                }
                break;
            case 1595935908:
                if (str.equals("tabBarItemBadgeTextColor")) {
                    c = 18;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setSpecialEffects(t, (ReadableMap) obj);
                break;
            case 1:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIconResourceName(t, obj != null ? (String) obj : null);
                break;
            case 2:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setOrientation(t, (String) obj);
                break;
            case 3:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIsFocused(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 4:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setOverrideScrollViewContentInsetAdjustmentBehavior(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 5:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setTabKey(t, obj != null ? (String) obj : null);
                break;
            case 6:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIconImageSource(t, (ReadableMap) obj);
                break;
            case 7:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIconType(t, (String) obj);
                break;
            case '\b':
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setSelectedIconSfSymbolName(t, obj != null ? (String) obj : null);
                break;
            case '\t':
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setScrollEdgeAppearance(t, new DynamicFromObject(obj));
                break;
            case '\n':
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setStandardAppearance(t, new DynamicFromObject(obj));
                break;
            case 11:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setTitle(t, obj != null ? (String) obj : null);
                break;
            case '\f':
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIconSfSymbolName(t, obj != null ? (String) obj : null);
                break;
            case '\r':
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setSystemItem(t, (String) obj);
                break;
            case 14:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setSelectedIconImageSource(t, (ReadableMap) obj);
                break;
            case 15:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setBadgeValue(t, obj != null ? (String) obj : null);
                break;
            case 16:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setIconResource(t, (ReadableMap) obj);
                break;
            case 17:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setTabBarItemBadgeBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 18:
                ((RNSBottomTabsScreenManagerInterface) ((BaseViewManager) this.mViewManager)).setTabBarItemBadgeTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            default:
                super.setProperty(t, str, obj);
                break;
        }
    }
}
