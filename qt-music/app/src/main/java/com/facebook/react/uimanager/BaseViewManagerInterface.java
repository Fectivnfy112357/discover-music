package com.facebook.react.uimanager;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;

/* compiled from: BaseViewManagerInterface.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u001a\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001f\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001d\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020 H&¢\u0006\u0002\u0010!J\u001d\u0010\"\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010#\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010#\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010'\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010#\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010(\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010#\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010)\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010#\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010*\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010+\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010,\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010-\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ\u001f\u0010.\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010/\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001d\u00100\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u00100\u001a\u00020\u0012H&¢\u0006\u0002\u0010\u0013J\u001f\u00101\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001d\u00102\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u00103\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u00104\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u00105\u001a\u000206H&¢\u0006\u0002\u00107J\u001f\u00108\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u00109\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001d\u0010:\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010;\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010<\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010=\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010>\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010?\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010@\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010A\u001a\u00020 H&¢\u0006\u0002\u0010!J\u001f\u0010B\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010C\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013J\u001f\u0010D\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010E\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001f\u0010F\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010G\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001d\u0010H\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010I\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001d\u0010J\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010K\u001a\u00020$H&¢\u0006\u0002\u0010%J\u001f\u0010L\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\b\u0010M\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\u001d\u0010N\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010O\u001a\u00020$H&¢\u0006\u0002\u0010%ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006PÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/uimanager/BaseViewManagerInterface;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "", "setAccessibilityActions", "", "view", ViewProps.ACCESSIBILITY_ACTIONS, "Lcom/facebook/react/bridge/ReadableArray;", "(Landroid/view/View;Lcom/facebook/react/bridge/ReadableArray;)V", "setAccessibilityCollection", ViewProps.ACCESSIBILITY_COLLECTION, "Lcom/facebook/react/bridge/ReadableMap;", "(Landroid/view/View;Lcom/facebook/react/bridge/ReadableMap;)V", "setAccessibilityCollectionItem", ViewProps.ACCESSIBILITY_COLLECTION_ITEM, "setAccessibilityHint", ViewProps.ACCESSIBILITY_HINT, "", "(Landroid/view/View;Ljava/lang/String;)V", "setAccessibilityLabel", ViewProps.ACCESSIBILITY_LABEL, "setAccessibilityLabelledBy", "nativeId", "Lcom/facebook/react/bridge/Dynamic;", "(Landroid/view/View;Lcom/facebook/react/bridge/Dynamic;)V", "setAccessibilityLiveRegion", "liveRegion", "setAccessibilityRole", ViewProps.ACCESSIBILITY_ROLE, "setBackgroundColor", "backgroundColor", "", "(Landroid/view/View;I)V", "setBorderBottomLeftRadius", ViewProps.BORDER_RADIUS, "", "(Landroid/view/View;F)V", "setBorderBottomRightRadius", "setBorderRadius", "setBorderTopLeftRadius", "setBorderTopRightRadius", "setElevation", ViewProps.ELEVATION, "setFilter", ViewProps.FILTER, "setImportantForAccessibility", ViewProps.IMPORTANT_FOR_ACCESSIBILITY, "setMixBlendMode", "setNativeId", "setOpacity", ViewProps.OPACITY, "setRenderToHardwareTexture", "useHWTexture", "", "(Landroid/view/View;Z)V", "setRole", ViewProps.ROLE, "setRotation", ViewProps.ROTATION, "setScaleX", ViewProps.SCALE_X, "setScaleY", ViewProps.SCALE_Y, "setShadowColor", ViewProps.SHADOW_COLOR, "setTestId", "testId", "setTransform", "matrix", "setTransformOrigin", ViewProps.TRANSFORM_ORIGIN, "setTranslateX", ViewProps.TRANSLATE_X, "setTranslateY", ViewProps.TRANSLATE_Y, "setViewState", ViewProps.ACCESSIBILITY_STATE, "setZIndex", ViewProps.Z_INDEX, "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BaseViewManagerInterface<T extends View> {
    void setAccessibilityActions(T view, ReadableArray accessibilityActions);

    void setAccessibilityCollection(T view, ReadableMap accessibilityCollection);

    void setAccessibilityCollectionItem(T view, ReadableMap accessibilityCollectionItem);

    void setAccessibilityHint(T view, String accessibilityHint);

    void setAccessibilityLabel(T view, String accessibilityLabel);

    void setAccessibilityLabelledBy(T view, Dynamic nativeId);

    void setAccessibilityLiveRegion(T view, String liveRegion);

    void setAccessibilityRole(T view, String accessibilityRole);

    void setBackgroundColor(T view, int backgroundColor);

    void setBorderBottomLeftRadius(T view, float borderRadius);

    void setBorderBottomRightRadius(T view, float borderRadius);

    void setBorderRadius(T view, float borderRadius);

    void setBorderTopLeftRadius(T view, float borderRadius);

    void setBorderTopRightRadius(T view, float borderRadius);

    void setElevation(T view, float elevation);

    void setFilter(T view, ReadableArray filter);

    void setImportantForAccessibility(T view, String importantForAccessibility);

    void setMixBlendMode(T view, String setMixBlendMode);

    void setNativeId(T view, String nativeId);

    void setOpacity(T view, float opacity);

    void setRenderToHardwareTexture(T view, boolean useHWTexture);

    void setRole(T view, String role);

    void setRotation(T view, float rotation);

    void setScaleX(T view, float scaleX);

    void setScaleY(T view, float scaleY);

    void setShadowColor(T view, int shadowColor);

    void setTestId(T view, String testId);

    void setTransform(T view, ReadableArray matrix);

    void setTransformOrigin(T view, ReadableArray transformOrigin);

    void setTranslateX(T view, float translateX);

    void setTranslateY(T view, float translateY);

    void setViewState(T view, ReadableMap accessibilityState);

    void setZIndex(T view, float zIndex);
}
