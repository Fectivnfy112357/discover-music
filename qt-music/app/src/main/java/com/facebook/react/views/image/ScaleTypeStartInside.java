package com.facebook.react.views.image;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ScaleTypeStartInside.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002JH\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/views/image/ScaleTypeStartInside;", "Lcom/facebook/drawee/drawable/ScalingUtils$AbstractScaleType;", "()V", "getTransformImpl", "", "outTransform", "Landroid/graphics/Matrix;", "parentRect", "Landroid/graphics/Rect;", "childWidth", "", "childHeight", "focusX", "", "focusY", ViewProps.SCALE_X, ViewProps.SCALE_Y, "toString", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ScaleTypeStartInside extends ScalingUtils.AbstractScaleType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ScalingUtils.ScaleType INSTANCE = new ScaleTypeStartInside();

    @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
    public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
        Intrinsics.checkNotNullParameter(outTransform, "outTransform");
        Intrinsics.checkNotNullParameter(parentRect, "parentRect");
        float fCoerceAtMost = RangesKt.coerceAtMost(Math.min(scaleX, scaleY), 1.0f);
        float f = parentRect.left;
        float f2 = parentRect.top;
        outTransform.setScale(fCoerceAtMost, fCoerceAtMost);
        outTransform.postTranslate(Math.round(f), Math.round(f2));
    }

    public String toString() {
        return "start_inside";
    }

    /* compiled from: ScaleTypeStartInside.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/views/image/ScaleTypeStartInside$Companion;", "", "()V", "INSTANCE", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getINSTANCE", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScalingUtils.ScaleType getINSTANCE() {
            return ScaleTypeStartInside.INSTANCE;
        }
    }
}
