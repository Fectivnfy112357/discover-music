package com.facebook.react.views.view;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.math.MathKt;

/* compiled from: ColorUtil.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007¨\u0006\f"}, d2 = {"Lcom/facebook/react/views/view/ColorUtil;", "", "()V", "clamp255", "", "value", "", "normalize", "r", "g", "b", "a", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ColorUtil {
    public static final ColorUtil INSTANCE = new ColorUtil();

    private ColorUtil() {
    }

    @JvmStatic
    public static final int normalize(double r, double g, double b, double a) {
        ColorUtil colorUtil = INSTANCE;
        return (colorUtil.clamp255(r) << 16) | (colorUtil.clamp255(a * 255) << 24) | (colorUtil.clamp255(g) << 8) | colorUtil.clamp255(b);
    }

    private final int clamp255(double value) {
        return Math.max(0, Math.min(255, MathKt.roundToInt(value)));
    }
}
