package com.facebook.react.uimanager;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseViewManagerDelegate.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00028\u0001¢\u0006\u0002\u0010\u0007J)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010\u0010J)\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015R\u0012\u0010\u0006\u001a\u00028\u00018\u0004X\u0085\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/uimanager/BaseViewManagerDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "U", "Lcom/facebook/react/uimanager/BaseViewManagerInterface;", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "mViewManager", "(Lcom/facebook/react/uimanager/BaseViewManagerInterface;)V", "Lcom/facebook/react/uimanager/BaseViewManagerInterface;", "receiveCommand", "", "view", "commandName", "", "args", "Lcom/facebook/react/bridge/ReadableArray;", "(Landroid/view/View;Ljava/lang/String;Lcom/facebook/react/bridge/ReadableArray;)V", "setProperty", "propName", "value", "", "(Landroid/view/View;Ljava/lang/String;Ljava/lang/Object;)V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T view, String commandName, ReadableArray args) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public BaseViewManagerDelegate(U mViewManager) {
        Intrinsics.checkNotNullParameter(mViewManager, "mViewManager");
        this.mViewManager = mViewManager;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T view, String propName, Object value) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (propName != null) {
            switch (propName.hashCode()) {
                case -1721943862:
                    if (propName.equals(ViewProps.TRANSLATE_X)) {
                        Double d = (Double) value;
                        this.mViewManager.setTranslateX(view, d != null ? (float) d.doubleValue() : 0.0f);
                        break;
                    }
                    break;
                case -1721943861:
                    if (propName.equals(ViewProps.TRANSLATE_Y)) {
                        Double d2 = (Double) value;
                        this.mViewManager.setTranslateY(view, d2 != null ? (float) d2.doubleValue() : 0.0f);
                        break;
                    }
                    break;
                case -1589741021:
                    if (propName.equals(ViewProps.SHADOW_COLOR)) {
                        U u = this.mViewManager;
                        int color = value == null ? 0 : ColorPropConverter.getColor(value, view.getContext());
                        Intrinsics.checkNotNull(color);
                        u.setShadowColor(view, color.intValue());
                        break;
                    }
                    break;
                case -1267206133:
                    if (propName.equals(ViewProps.OPACITY)) {
                        Double d3 = (Double) value;
                        this.mViewManager.setOpacity(view, d3 != null ? (float) d3.doubleValue() : 1.0f);
                        break;
                    }
                    break;
                case -1228066334:
                    if (propName.equals(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
                        Double d4 = (Double) value;
                        this.mViewManager.setBorderTopLeftRadius(view, d4 != null ? (float) d4.doubleValue() : Float.NaN);
                        break;
                    }
                    break;
                case -908189618:
                    if (propName.equals(ViewProps.SCALE_X)) {
                        Double d5 = (Double) value;
                        this.mViewManager.setScaleX(view, d5 != null ? (float) d5.doubleValue() : 1.0f);
                        break;
                    }
                    break;
                case -908189617:
                    if (propName.equals(ViewProps.SCALE_Y)) {
                        Double d6 = (Double) value;
                        this.mViewManager.setScaleY(view, d6 != null ? (float) d6.doubleValue() : 1.0f);
                        break;
                    }
                    break;
                case -877170387:
                    if (propName.equals(ViewProps.TEST_ID)) {
                        this.mViewManager.setTestId(view, (String) value);
                        break;
                    }
                    break;
                case -781597262:
                    if (propName.equals(ViewProps.TRANSFORM_ORIGIN)) {
                        this.mViewManager.setTransformOrigin(view, (ReadableArray) value);
                        break;
                    }
                    break;
                case -731417480:
                    if (propName.equals(ViewProps.Z_INDEX)) {
                        Double d7 = (Double) value;
                        this.mViewManager.setZIndex(view, d7 != null ? (float) d7.doubleValue() : 0.0f);
                        break;
                    }
                    break;
                case -101663499:
                    if (propName.equals(ViewProps.ACCESSIBILITY_HINT)) {
                        this.mViewManager.setAccessibilityHint(view, (String) value);
                        break;
                    }
                    break;
                case -101359900:
                    if (propName.equals(ViewProps.ACCESSIBILITY_ROLE)) {
                        this.mViewManager.setAccessibilityRole(view, (String) value);
                        break;
                    }
                    break;
                case -80891667:
                    if (propName.equals(ViewProps.RENDER_TO_HARDWARE_TEXTURE)) {
                        Boolean bool = (Boolean) value;
                        this.mViewManager.setRenderToHardwareTexture(view, bool != null ? bool.booleanValue() : false);
                        break;
                    }
                    break;
                case -40300674:
                    if (propName.equals(ViewProps.ROTATION)) {
                        Double d8 = (Double) value;
                        this.mViewManager.setRotation(view, d8 != null ? (float) d8.doubleValue() : 0.0f);
                        break;
                    }
                    break;
                case -4379043:
                    if (propName.equals(ViewProps.ELEVATION)) {
                        Double d9 = (Double) value;
                        this.mViewManager.setElevation(view, d9 != null ? (float) d9.doubleValue() : 0.0f);
                        break;
                    }
                    break;
                case 3506294:
                    if (propName.equals(ViewProps.ROLE)) {
                        this.mViewManager.setRole(view, (String) value);
                        break;
                    }
                    break;
                case 36255470:
                    if (propName.equals(ViewProps.ACCESSIBILITY_LIVE_REGION)) {
                        this.mViewManager.setAccessibilityLiveRegion(view, (String) value);
                        break;
                    }
                    break;
                case 333432965:
                    if (propName.equals(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
                        Double d10 = (Double) value;
                        this.mViewManager.setBorderTopRightRadius(view, d10 != null ? (float) d10.doubleValue() : Float.NaN);
                        break;
                    }
                    break;
                case 581268560:
                    if (propName.equals(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
                        Double d11 = (Double) value;
                        this.mViewManager.setBorderBottomLeftRadius(view, d11 != null ? (float) d11.doubleValue() : Float.NaN);
                        break;
                    }
                    break;
                case 588239831:
                    if (propName.equals(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
                        Double d12 = (Double) value;
                        this.mViewManager.setBorderBottomRightRadius(view, d12 != null ? (float) d12.doubleValue() : Float.NaN);
                        break;
                    }
                    break;
                case 746986311:
                    if (propName.equals(ViewProps.IMPORTANT_FOR_ACCESSIBILITY)) {
                        this.mViewManager.setImportantForAccessibility(view, (String) value);
                        break;
                    }
                    break;
                case 1052666732:
                    if (propName.equals(ViewProps.TRANSFORM)) {
                        this.mViewManager.setTransform(view, (ReadableArray) value);
                        break;
                    }
                    break;
                case 1146842694:
                    if (propName.equals(ViewProps.ACCESSIBILITY_LABEL)) {
                        this.mViewManager.setAccessibilityLabel(view, (String) value);
                        break;
                    }
                    break;
                case 1153872867:
                    if (propName.equals(ViewProps.ACCESSIBILITY_STATE)) {
                        this.mViewManager.setViewState(view, (ReadableMap) value);
                        break;
                    }
                    break;
                case 1287124693:
                    if (propName.equals("backgroundColor")) {
                        U u2 = this.mViewManager;
                        int color2 = value == null ? 0 : ColorPropConverter.getColor(value, view.getContext());
                        Intrinsics.checkNotNull(color2);
                        u2.setBackgroundColor(view, color2.intValue());
                        break;
                    }
                    break;
                case 1349188574:
                    if (propName.equals(ViewProps.BORDER_RADIUS)) {
                        Double d13 = (Double) value;
                        this.mViewManager.setBorderRadius(view, d13 != null ? (float) d13.doubleValue() : Float.NaN);
                        break;
                    }
                    break;
                case 1505602511:
                    if (propName.equals(ViewProps.ACCESSIBILITY_ACTIONS)) {
                        this.mViewManager.setAccessibilityActions(view, (ReadableArray) value);
                        break;
                    }
                    break;
                case 1761903244:
                    if (propName.equals(ViewProps.ACCESSIBILITY_COLLECTION)) {
                        this.mViewManager.setAccessibilityCollection(view, (ReadableMap) value);
                        break;
                    }
                    break;
                case 1865277756:
                    if (propName.equals(ViewProps.ACCESSIBILITY_LABELLED_BY)) {
                        this.mViewManager.setAccessibilityLabelledBy(view, new DynamicFromObject(value));
                        break;
                    }
                    break;
                case 1993034687:
                    if (propName.equals(ViewProps.ACCESSIBILITY_COLLECTION_ITEM)) {
                        this.mViewManager.setAccessibilityCollectionItem(view, (ReadableMap) value);
                        break;
                    }
                    break;
                case 2045685618:
                    if (propName.equals(ViewProps.NATIVE_ID)) {
                        this.mViewManager.setNativeId(view, (String) value);
                        break;
                    }
                    break;
            }
        }
    }
}
