package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.FilterHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.umeng.analytics.pro.f;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: InsetBoxShadowDrawable.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0002\u0010\u000fJ\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\tH\u0016J\u001f\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\tH\u0016J\u0012\u0010*\u001a\u00020 2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R(\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/facebook/react/uimanager/drawable/InsetBoxShadowDrawable;", "Landroid/graphics/drawable/Drawable;", f.X, "Landroid/content/Context;", ViewProps.BORDER_RADIUS, "Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "borderInsets", "Lcom/facebook/react/uimanager/style/BorderInsets;", ViewProps.SHADOW_COLOR, "", "offsetX", "", "offsetY", "blurRadius", "spread", "(Landroid/content/Context;Lcom/facebook/react/uimanager/style/BorderRadiusStyle;Lcom/facebook/react/uimanager/style/BorderInsets;IFFFF)V", "value", "getBorderInsets", "()Lcom/facebook/react/uimanager/style/BorderInsets;", "setBorderInsets", "(Lcom/facebook/react/uimanager/style/BorderInsets;)V", "getBorderRadius", "()Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "setBorderRadius", "(Lcom/facebook/react/uimanager/style/BorderRadiusStyle;)V", "shadowPaint", "Landroid/graphics/Paint;", "computeBorderInsets", "Landroid/graphics/RectF;", "computeBorderRadii", "Lcom/facebook/react/uimanager/style/ComputedBorderRadius;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getOpacity", "innerRadius", "radius", "borderInset", "(FLjava/lang/Float;)F", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InsetBoxShadowDrawable extends Drawable {
    private final float blurRadius;
    private BorderInsets borderInsets;
    private BorderRadiusStyle borderRadius;
    private final Context context;
    private final float offsetX;
    private final float offsetY;
    private final int shadowColor;
    private final Paint shadowPaint;
    private final float spread;

    public /* synthetic */ InsetBoxShadowDrawable(Context context, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets, int i, float f, float f2, float f3, float f4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : borderRadiusStyle, (i2 & 4) != 0 ? null : borderInsets, i, f, f2, f3, f4);
    }

    public InsetBoxShadowDrawable(Context context, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets, int i, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.shadowColor = i;
        this.offsetX = f;
        this.offsetY = f2;
        this.blurRadius = f3;
        this.spread = f4;
        this.borderRadius = borderRadiusStyle;
        this.borderInsets = borderInsets;
        Paint paint = new Paint();
        paint.setColor(i);
        if (f3 > 0.0f) {
            paint.setMaskFilter(new BlurMaskFilter(FilterHelper.INSTANCE.sigmaToRadius$ReactAndroid_release(f3 * 0.5f), BlurMaskFilter.Blur.NORMAL));
        }
        this.shadowPaint = paint;
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        if (Intrinsics.areEqual(borderRadiusStyle, this.borderRadius)) {
            return;
        }
        this.borderRadius = borderRadiusStyle;
        invalidateSelf();
    }

    public final BorderInsets getBorderInsets() {
        return this.borderInsets;
    }

    public final void setBorderInsets(BorderInsets borderInsets) {
        if (Intrinsics.areEqual(borderInsets, this.borderInsets)) {
            return;
        }
        this.borderInsets = borderInsets;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.shadowPaint.setAlpha(MathKt.roundToInt((alpha / 255.0f) * (Color.alpha(this.shadowColor) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.shadowPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return MathKt.roundToInt(((this.shadowPaint.getAlpha() / 255.0f) / (Color.alpha(this.shadowColor) / 255.0f)) * 255.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        ComputedBorderRadius computedBorderRadiusComputeBorderRadii = computeBorderRadii();
        RectF rectFComputeBorderInsets = computeBorderInsets();
        RectF rectF = new RectF(getBounds().left + (rectFComputeBorderInsets != null ? rectFComputeBorderInsets.left : 0.0f), getBounds().top + (rectFComputeBorderInsets != null ? rectFComputeBorderInsets.top : 0.0f), getBounds().right - (rectFComputeBorderInsets != null ? rectFComputeBorderInsets.right : 0.0f), getBounds().bottom - (rectFComputeBorderInsets != null ? rectFComputeBorderInsets.bottom : 0.0f));
        float[] fArr = null;
        if (computedBorderRadiusComputeBorderRadii != null) {
            fArr = new float[]{innerRadius(computedBorderRadiusComputeBorderRadii.getTopLeft().getHorizontal(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.left) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getTopLeft().getVertical(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.top) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getTopRight().getHorizontal(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.right) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getTopRight().getVertical(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.top) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getBottomRight().getHorizontal(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.right) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getBottomRight().getVertical(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.bottom) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getBottomLeft().getHorizontal(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.left) : null), innerRadius(computedBorderRadiusComputeBorderRadii.getBottomLeft().getVertical(), rectFComputeBorderInsets != null ? Float.valueOf(rectFComputeBorderInsets.bottom) : null)};
        }
        float fDpToPx = PixelUtil.INSTANCE.dpToPx(this.offsetX);
        float fDpToPx2 = PixelUtil.INSTANCE.dpToPx(this.offsetY);
        float fDpToPx3 = PixelUtil.INSTANCE.dpToPx(this.spread);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(fDpToPx3, fDpToPx3);
        rectF2.offset(fDpToPx, fDpToPx2);
        float fSigmaToRadius$ReactAndroid_release = FilterHelper.INSTANCE.sigmaToRadius$ReactAndroid_release(this.blurRadius);
        RectF rectF3 = new RectF(rectF2);
        float f = -fSigmaToRadius$ReactAndroid_release;
        rectF3.inset(f, f);
        if (fDpToPx3 < 0.0f) {
            rectF3.inset(fDpToPx3, fDpToPx3);
        }
        RectF rectF4 = new RectF(rectF3);
        rectF4.offset(-fDpToPx, -fDpToPx2);
        rectF3.union(rectF4);
        int iSave = canvas.save();
        if (fArr != null) {
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.clipPath(path);
            ArrayList arrayList = new ArrayList(fArr.length);
            for (float f2 : fArr) {
                arrayList.add(Float.valueOf(BoxShadowBorderRadiusKt.adjustRadiusForSpread(f2, -fDpToPx3)));
            }
            canvas.drawDoubleRoundRect(rectF3, InsetBoxShadowDrawableKt.ZERO_RADII, rectF2, CollectionsKt.toFloatArray(arrayList), this.shadowPaint);
        } else {
            canvas.clipRect(rectF);
            canvas.drawDoubleRoundRect(rectF3, InsetBoxShadowDrawableKt.ZERO_RADII, rectF2, InsetBoxShadowDrawableKt.ZERO_RADII, this.shadowPaint);
        }
        canvas.restoreToCount(iSave);
    }

    private final ComputedBorderRadius computeBorderRadii() {
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        ComputedBorderRadius computedBorderRadiusResolve = borderRadiusStyle != null ? borderRadiusStyle.resolve(getLayoutDirection(), this.context, PixelUtil.INSTANCE.pxToDp(getBounds().width()), PixelUtil.INSTANCE.pxToDp(getBounds().height())) : null;
        if (computedBorderRadiusResolve == null || !computedBorderRadiusResolve.hasRoundedBorders()) {
            return null;
        }
        return new ComputedBorderRadius(new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopLeft().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopLeft().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopRight().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopRight().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomLeft().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomLeft().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomRight().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomRight().getVertical())));
    }

    private final RectF computeBorderInsets() {
        RectF rectFResolve;
        BorderInsets borderInsets = this.borderInsets;
        if (borderInsets == null || (rectFResolve = borderInsets.resolve(getLayoutDirection(), this.context)) == null) {
            return null;
        }
        return new RectF(PixelUtil.INSTANCE.dpToPx(rectFResolve.left), PixelUtil.INSTANCE.dpToPx(rectFResolve.top), PixelUtil.INSTANCE.dpToPx(rectFResolve.right), PixelUtil.INSTANCE.dpToPx(rectFResolve.bottom));
    }

    private final float innerRadius(float radius, Float borderInset) {
        return RangesKt.coerceAtLeast(radius - (borderInset != null ? borderInset.floatValue() : 0.0f), 0.0f);
    }
}
