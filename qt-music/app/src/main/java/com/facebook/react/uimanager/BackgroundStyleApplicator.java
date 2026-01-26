package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.drawable.CSSBackgroundDrawable;
import com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawable;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.BoxShadow;
import com.facebook.react.uimanager.style.LogicalEdge;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackgroundStyleApplicator.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0017\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001f\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J!\u0010 \u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010\"J \u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%H\u0007J)\u0010'\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010(J\"\u0010)\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\u0015H\u0007J\u001a\u0010+\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010\u0019H\u0007J'\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010.\u001a\u0004\u0018\u00010\u001bH\u0007¢\u0006\u0002\u0010/J\u001a\u00100\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u000102H\u0007J\u001e\u00100\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u00101\u001a\b\u0012\u0004\u0012\u0002030%H\u0007J\u001a\u00104\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u00105\u001a\u0004\u0018\u000106H\u0007¨\u00067"}, d2 = {"Lcom/facebook/react/uimanager/BackgroundStyleApplicator;", "", "()V", "clipToPaddingBox", "", "view", "Landroid/view/View;", "canvas", "Landroid/graphics/Canvas;", "ensureCSSBackground", "Lcom/facebook/react/uimanager/drawable/CSSBackgroundDrawable;", "ensureCompositeBackgroundDrawable", "Lcom/facebook/react/uimanager/drawable/CompositeBackgroundDrawable;", "getBackgroundColor", "", "(Landroid/view/View;)Ljava/lang/Integer;", "getBorderColor", "edge", "Lcom/facebook/react/uimanager/style/LogicalEdge;", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;)Ljava/lang/Integer;", "getBorderRadius", "Lcom/facebook/react/uimanager/LengthPercentage;", "corner", "Lcom/facebook/react/uimanager/style/BorderRadiusProp;", "getBorderStyle", "Lcom/facebook/react/uimanager/style/BorderStyle;", "getBorderWidth", "", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;)Ljava/lang/Float;", "getCSSBackground", "getCompositeBackgroundDrawable", "reset", "setBackgroundColor", "color", "(Landroid/view/View;Ljava/lang/Integer;)V", "setBackgroundImage", "backgroundImageLayers", "", "Lcom/facebook/react/uimanager/style/BackgroundImageLayer;", "setBorderColor", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;Ljava/lang/Integer;)V", "setBorderRadius", "radius", "setBorderStyle", "borderStyle", "setBorderWidth", "width", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;Ljava/lang/Float;)V", "setBoxShadow", "shadows", "Lcom/facebook/react/bridge/ReadableArray;", "Lcom/facebook/react/uimanager/style/BoxShadow;", "setFeedbackUnderlay", "drawable", "Landroid/graphics/drawable/Drawable;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BackgroundStyleApplicator {
    public static final BackgroundStyleApplicator INSTANCE = new BackgroundStyleApplicator();

    private BackgroundStyleApplicator() {
    }

    @JvmStatic
    public static final void setBackgroundColor(View view, Integer color) {
        Intrinsics.checkNotNullParameter(view, "view");
        if ((color == null || color.intValue() == 0) && !(view.getBackground() instanceof CompositeBackgroundDrawable)) {
            return;
        }
        INSTANCE.ensureCSSBackground(view).setColor(color != null ? color.intValue() : 0);
    }

    @JvmStatic
    public static final void setBackgroundImage(View view, List<BackgroundImageLayer> backgroundImageLayers) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureCSSBackground(view).setBackgroundImage(backgroundImageLayers);
    }

    @JvmStatic
    public static final Integer getBackgroundColor(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return Integer.valueOf(cSSBackground.getColor());
        }
        return null;
    }

    @JvmStatic
    public static final void setBorderWidth(View view, LogicalEdge edge, Float width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        backgroundStyleApplicator.ensureCSSBackground(view).setBorderWidth(edge.toSpacingType(), width != null ? PixelUtil.INSTANCE.dpToPx(width.floatValue()) : Float.NaN);
        if (Build.VERSION.SDK_INT >= 29) {
            CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
            BorderInsets borderInsets = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
            if (borderInsets == null) {
                borderInsets = new BorderInsets();
            }
            compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.setBorderInsets(borderInsets);
            BorderInsets borderInsets2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
            if (borderInsets2 != null) {
                borderInsets2.setBorderWidth(edge, width);
            }
            for (Drawable drawable : compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getInnerShadows()) {
                Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable");
                ((InsetBoxShadowDrawable) drawable).setBorderInsets(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
                drawable.invalidateSelf();
            }
        }
    }

    @JvmStatic
    public static final Float getBorderWidth(View view, LogicalEdge edge) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        Float borderWidth = cSSBackground != null ? cSSBackground.getBorderWidth(edge.toSpacingType()) : null;
        if (borderWidth == null || Float.isNaN(borderWidth.floatValue())) {
            return null;
        }
        return Float.valueOf(PixelUtil.INSTANCE.pxToDp(borderWidth.floatValue()));
    }

    @JvmStatic
    public static final void setBorderColor(View view, LogicalEdge edge, Integer color) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        INSTANCE.ensureCSSBackground(view).setBorderColor(edge.toSpacingType(), color);
    }

    @JvmStatic
    public static final Integer getBorderColor(View view, LogicalEdge edge) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return Integer.valueOf(cSSBackground.getBorderColor(edge.toSpacingType()));
        }
        return null;
    }

    @JvmStatic
    public static final void setBorderRadius(View view, BorderRadiusProp corner, LengthPercentage radius) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(corner, "corner");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        backgroundStyleApplicator.ensureCSSBackground(view).setBorderRadius(corner, radius);
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
        if (Build.VERSION.SDK_INT >= 28) {
            for (Drawable drawable : compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getOuterShadows()) {
                if (drawable instanceof OutsetBoxShadowDrawable) {
                    OutsetBoxShadowDrawable outsetBoxShadowDrawable = (OutsetBoxShadowDrawable) drawable;
                    BorderRadiusStyle borderRadius = outsetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius == null) {
                        borderRadius = new BorderRadiusStyle(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
                    }
                    outsetBoxShadowDrawable.setBorderRadius(borderRadius);
                    BorderRadiusStyle borderRadius2 = outsetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius2 != null) {
                        borderRadius2.set(corner, radius);
                    }
                    drawable.invalidateSelf();
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            for (Drawable drawable2 : compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getInnerShadows()) {
                if (drawable2 instanceof InsetBoxShadowDrawable) {
                    InsetBoxShadowDrawable insetBoxShadowDrawable = (InsetBoxShadowDrawable) drawable2;
                    BorderRadiusStyle borderRadius3 = insetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius3 == null) {
                        borderRadius3 = new BorderRadiusStyle(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
                    }
                    insetBoxShadowDrawable.setBorderRadius(borderRadius3);
                    BorderRadiusStyle borderRadius4 = insetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius4 != null) {
                        borderRadius4.set(corner, radius);
                    }
                    drawable2.invalidateSelf();
                }
            }
        }
    }

    @JvmStatic
    public static final LengthPercentage getBorderRadius(View view, BorderRadiusProp corner) {
        BorderRadiusStyle borderRadius;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(corner, "corner");
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground == null || (borderRadius = cSSBackground.getBorderRadius()) == null) {
            return null;
        }
        return borderRadius.get(corner);
    }

    @JvmStatic
    public static final void setBorderStyle(View view, BorderStyle borderStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureCSSBackground(view).setBorderStyle(borderStyle);
    }

    @JvmStatic
    public static final BorderStyle getBorderStyle(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return cSSBackground.getBorderStyle();
        }
        return null;
    }

    @JvmStatic
    public static final void setBoxShadow(View view, List<BoxShadow> shadows) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        BorderInsets borderInsets = INSTANCE.ensureCompositeBackgroundDrawable(view).getBorderInsets();
        for (BoxShadow boxShadow : shadows) {
            float offsetX = boxShadow.getOffsetX();
            float offsetY = boxShadow.getOffsetY();
            Integer color = boxShadow.getColor();
            int iIntValue = color != null ? color.intValue() : ViewCompat.MEASURED_STATE_MASK;
            Float blurRadius = boxShadow.getBlurRadius();
            float fFloatValue = blurRadius != null ? blurRadius.floatValue() : 0.0f;
            Float spreadDistance = boxShadow.getSpreadDistance();
            float fFloatValue2 = spreadDistance != null ? spreadDistance.floatValue() : 0.0f;
            Boolean inset = boxShadow.getInset();
            boolean zBooleanValue = inset != null ? inset.booleanValue() : false;
            if (zBooleanValue && Build.VERSION.SDK_INT >= 29) {
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                arrayList2.add(new InsetBoxShadowDrawable(context, INSTANCE.ensureCSSBackground(view).getBorderRadius(), borderInsets, iIntValue, offsetX, offsetY, fFloatValue, fFloatValue2));
            } else if (!zBooleanValue && Build.VERSION.SDK_INT >= 28) {
                Context context2 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                arrayList.add(new OutsetBoxShadowDrawable(context2, INSTANCE.ensureCSSBackground(view).getBorderRadius(), iIntValue, offsetX, offsetY, fFloatValue, fFloatValue2));
            }
        }
        view.setBackground(INSTANCE.ensureCompositeBackgroundDrawable(view).withNewShadows(arrayList, arrayList2));
    }

    @JvmStatic
    public static final void setBoxShadow(View view, ReadableArray shadows) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (shadows == null) {
            setBoxShadow(view, (List<BoxShadow>) CollectionsKt.emptyList());
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = shadows.size();
        for (int i = 0; i < size; i++) {
            BoxShadow boxShadow = BoxShadow.INSTANCE.parse(shadows.getMap(i));
            if (boxShadow == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            arrayList.add(boxShadow);
        }
        setBoxShadow(view, arrayList);
    }

    @JvmStatic
    public static final void setFeedbackUnderlay(View view, Drawable drawable) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBackground(INSTANCE.ensureCompositeBackgroundDrawable(view).withNewFeedbackUnderlay(drawable));
    }

    @JvmStatic
    public static final void clipToPaddingBox(View view, Canvas canvas) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground == null) {
            canvas.clipRect(rect);
            return;
        }
        Path paddingBoxPath = cSSBackground.getPaddingBoxPath();
        if (paddingBoxPath != null) {
            paddingBoxPath.offset(rect.left, rect.top);
            canvas.clipPath(paddingBoxPath);
        } else {
            RectF paddingBoxRect = cSSBackground.getPaddingBoxRect();
            Intrinsics.checkNotNullExpressionValue(paddingBoxRect, "getPaddingBoxRect(...)");
            paddingBoxRect.offset(rect.left, rect.top);
            canvas.clipRect(paddingBoxRect);
        }
    }

    @JvmStatic
    public static final void reset(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            view.setBackground(((CompositeBackgroundDrawable) background).getOriginalBackground());
        }
    }

    private final CompositeBackgroundDrawable ensureCompositeBackgroundDrawable(View view) {
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            return (CompositeBackgroundDrawable) background;
        }
        CompositeBackgroundDrawable compositeBackgroundDrawable = new CompositeBackgroundDrawable(view.getBackground(), null, null, null, null, 30, null);
        view.setBackground(compositeBackgroundDrawable);
        return compositeBackgroundDrawable;
    }

    private final CompositeBackgroundDrawable getCompositeBackgroundDrawable(View view) {
        Drawable background = view.getBackground();
        if (background instanceof CompositeBackgroundDrawable) {
            return (CompositeBackgroundDrawable) background;
        }
        return null;
    }

    private final CSSBackgroundDrawable ensureCSSBackground(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getCssBackground() != null) {
            return compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getCssBackground();
        }
        CSSBackgroundDrawable cSSBackgroundDrawable = new CSSBackgroundDrawable(view.getContext());
        view.setBackground(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.withNewCssBackground(cSSBackgroundDrawable));
        return cSSBackgroundDrawable;
    }

    private final CSSBackgroundDrawable getCSSBackground(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getCssBackground();
        }
        return null;
    }
}
