package com.facebook.react.uimanager;

import com.facebook.yoga.YogaDirection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutDirectionUtil.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/LayoutDirectionUtil;", "", "()V", "toAndroidFromYoga", "", "direction", "Lcom/facebook/yoga/YogaDirection;", "toYogaFromAndroid", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LayoutDirectionUtil {
    public static final LayoutDirectionUtil INSTANCE = new LayoutDirectionUtil();

    /* compiled from: LayoutDirectionUtil.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[YogaDirection.values().length];
            try {
                iArr[YogaDirection.LTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[YogaDirection.RTL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private LayoutDirectionUtil() {
    }

    @JvmStatic
    public static final int toAndroidFromYoga(YogaDirection direction) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        int i = WhenMappings.$EnumSwitchMapping$0[direction.ordinal()];
        if (i != 1) {
            return i != 2 ? 2 : 1;
        }
        return 0;
    }

    @JvmStatic
    public static final YogaDirection toYogaFromAndroid(int direction) {
        if (direction == 0) {
            return YogaDirection.LTR;
        }
        if (direction == 1) {
            return YogaDirection.RTL;
        }
        return YogaDirection.INHERIT;
    }
}
