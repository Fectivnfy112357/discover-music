package com.facebook.react.uimanager.style;

import android.content.Context;
import android.graphics.RectF;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.ViewProps;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BorderInsets.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/uimanager/style/BorderInsets;", "", "()V", "edgeInsets", "", "", "[Ljava/lang/Float;", "resolve", "Landroid/graphics/RectF;", ViewProps.LAYOUT_DIRECTION, "", f.X, "Landroid/content/Context;", "setBorderWidth", "", "edge", "Lcom/facebook/react/uimanager/style/LogicalEdge;", "width", "(Lcom/facebook/react/uimanager/style/LogicalEdge;Ljava/lang/Float;)V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BorderInsets {
    private final Float[] edgeInsets = new Float[LogicalEdge.values().length];

    public final void setBorderWidth(LogicalEdge edge, Float width) {
        Intrinsics.checkNotNullParameter(edge, "edge");
        this.edgeInsets[edge.ordinal()] = width;
    }

    public final RectF resolve(int layoutDirection, Context context) {
        RectF rectF;
        Intrinsics.checkNotNullParameter(context, "context");
        if (layoutDirection == 0) {
            Float f = this.edgeInsets[LogicalEdge.START.ordinal()];
            float fFloatValue = (f == null && (f = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f.floatValue();
            Float f2 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
            float fFloatValue2 = (f2 == null && (f2 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f2 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f2 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f2 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f2.floatValue();
            Float f3 = this.edgeInsets[LogicalEdge.END.ordinal()];
            float fFloatValue3 = (f3 == null && (f3 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f3 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f3 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f3.floatValue();
            Float f4 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
            rectF = new RectF(fFloatValue, fFloatValue2, fFloatValue3, (f4 == null && (f4 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f4 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f4 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f4 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f4.floatValue());
        } else if (layoutDirection == 1) {
            if (I18nUtil.INSTANCE.getInstance().doLeftAndRightSwapInRTL(context)) {
                Float f5 = this.edgeInsets[LogicalEdge.END.ordinal()];
                float fFloatValue4 = (f5 == null && (f5 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f5 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f5 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f5.floatValue();
                Float f6 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
                float fFloatValue5 = (f6 == null && (f6 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f6 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f6 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f6 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f6.floatValue();
                Float f7 = this.edgeInsets[LogicalEdge.START.ordinal()];
                float fFloatValue6 = (f7 == null && (f7 = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f7 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f7 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f7.floatValue();
                Float f8 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
                rectF = new RectF(fFloatValue4, fFloatValue5, fFloatValue6, (f8 == null && (f8 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f8 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f8 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f8 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f8.floatValue());
            } else {
                Float f9 = this.edgeInsets[LogicalEdge.END.ordinal()];
                float fFloatValue7 = (f9 == null && (f9 = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f9 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f9 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f9.floatValue();
                Float f10 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
                float fFloatValue8 = (f10 == null && (f10 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f10 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f10 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f10 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f10.floatValue();
                Float f11 = this.edgeInsets[LogicalEdge.START.ordinal()];
                float fFloatValue9 = (f11 == null && (f11 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f11 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f11 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f11.floatValue();
                Float f12 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
                rectF = new RectF(fFloatValue7, fFloatValue8, fFloatValue9, (f12 == null && (f12 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) ? 0.0f : f12.floatValue());
            }
        } else {
            throw new IllegalArgumentException("Expected resolved layout direction");
        }
        return rectF;
    }
}
