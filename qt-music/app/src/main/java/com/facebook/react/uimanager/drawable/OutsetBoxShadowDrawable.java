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
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: OutsetBoxShadowDrawable.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J(\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0007H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0007H\u0016J\u0012\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010%H\u0016R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/facebook/react/uimanager/drawable/OutsetBoxShadowDrawable;", "Landroid/graphics/drawable/Drawable;", f.X, "Landroid/content/Context;", ViewProps.BORDER_RADIUS, "Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", ViewProps.SHADOW_COLOR, "", "offsetX", "", "offsetY", "blurRadius", "spread", "(Landroid/content/Context;Lcom/facebook/react/uimanager/style/BorderRadiusStyle;IFFFF)V", "value", "getBorderRadius", "()Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "setBorderRadius", "(Lcom/facebook/react/uimanager/style/BorderRadiusStyle;)V", "shadowPaint", "Landroid/graphics/Paint;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "drawShadowRect", "shadowRect", "Landroid/graphics/RectF;", "drawShadowRoundRect", "spreadExtent", "computedBorderRadii", "Lcom/facebook/react/uimanager/style/ComputedBorderRadius;", "getOpacity", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OutsetBoxShadowDrawable extends Drawable {
    private final float blurRadius;
    private BorderRadiusStyle borderRadius;
    private final Context context;
    private final float offsetX;
    private final float offsetY;
    private final int shadowColor;
    private final Paint shadowPaint;
    private final float spread;

    public /* synthetic */ OutsetBoxShadowDrawable(Context context, BorderRadiusStyle borderRadiusStyle, int i, float f, float f2, float f3, float f4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : borderRadiusStyle, i, f, f2, f3, f4);
    }

    public OutsetBoxShadowDrawable(Context context, BorderRadiusStyle borderRadiusStyle, int i, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.shadowColor = i;
        this.offsetX = f;
        this.offsetY = f2;
        this.blurRadius = f3;
        this.spread = f4;
        this.borderRadius = borderRadiusStyle;
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
        ComputedBorderRadius computedBorderRadiusResolve;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float fPxToDp = PixelUtil.INSTANCE.pxToDp(getBounds().width());
        float fPxToDp2 = PixelUtil.INSTANCE.pxToDp(getBounds().height());
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        ComputedBorderRadius computedBorderRadius = (borderRadiusStyle == null || (computedBorderRadiusResolve = borderRadiusStyle.resolve(getLayoutDirection(), this.context, fPxToDp, fPxToDp2)) == null) ? null : new ComputedBorderRadius(new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopLeft().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopLeft().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopRight().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getTopRight().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomLeft().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomLeft().getVertical())), new CornerRadii(PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomRight().getHorizontal()), PixelUtil.INSTANCE.dpToPx(computedBorderRadiusResolve.getBottomRight().getVertical())));
        float fDpToPx = PixelUtil.INSTANCE.dpToPx(this.spread);
        RectF rectF = new RectF(getBounds());
        float f = -fDpToPx;
        rectF.inset(f, f);
        rectF.offset(PixelUtil.INSTANCE.dpToPx(this.offsetX), PixelUtil.INSTANCE.dpToPx(this.offsetY));
        int iSave = canvas.save();
        if (computedBorderRadius != null && computedBorderRadius.hasRoundedBorders()) {
            drawShadowRoundRect(canvas, rectF, fDpToPx, computedBorderRadius);
        } else {
            drawShadowRect(canvas, rectF);
        }
        canvas.restoreToCount(iSave);
    }

    private final void drawShadowRoundRect(Canvas canvas, RectF shadowRect, float spreadExtent, ComputedBorderRadius computedBorderRadii) {
        RectF rectF = new RectF(getBounds());
        rectF.inset(0.4f, 0.4f);
        Path path = new Path();
        path.addRoundRect(rectF, new float[]{computedBorderRadii.getTopLeft().getHorizontal(), computedBorderRadii.getTopLeft().getVertical(), computedBorderRadii.getTopRight().getHorizontal(), computedBorderRadii.getTopRight().getVertical(), computedBorderRadii.getBottomRight().getHorizontal(), computedBorderRadii.getBottomRight().getVertical(), computedBorderRadii.getBottomLeft().getHorizontal(), computedBorderRadii.getBottomLeft().getVertical()}, Path.Direction.CW);
        canvas.clipOutPath(path);
        Path path2 = new Path();
        path2.addRoundRect(shadowRect, new float[]{BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getTopLeft().getHorizontal(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getTopLeft().getVertical(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getTopRight().getHorizontal(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getTopRight().getVertical(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getBottomRight().getHorizontal(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getBottomRight().getVertical(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getBottomLeft().getHorizontal(), spreadExtent), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadii.getBottomLeft().getVertical(), spreadExtent)}, Path.Direction.CW);
        canvas.drawPath(path2, this.shadowPaint);
    }

    private final void drawShadowRect(Canvas canvas, RectF shadowRect) {
        canvas.clipOutRect(getBounds());
        canvas.drawRect(shadowRect, this.shadowPaint);
    }
}
