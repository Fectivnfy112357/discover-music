package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.style.CornerRadii;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LengthPercentage.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u0004HÂ\u0003J\t\u0010\u000b\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/uimanager/LengthPercentage;", "", "()V", "value", "", "type", "Lcom/facebook/react/uimanager/LengthPercentageType;", "(FLcom/facebook/react/uimanager/LengthPercentageType;)V", "getType", "()Lcom/facebook/react/uimanager/LengthPercentageType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "resolve", "Lcom/facebook/react/uimanager/style/CornerRadii;", "width", "height", "toString", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LengthPercentage {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final LengthPercentageType type;
    private final float value;

    /* renamed from: component1, reason: from getter */
    private final float getValue() {
        return this.value;
    }

    public static /* synthetic */ LengthPercentage copy$default(LengthPercentage lengthPercentage, float f, LengthPercentageType lengthPercentageType, int i, Object obj) {
        if ((i & 1) != 0) {
            f = lengthPercentage.value;
        }
        if ((i & 2) != 0) {
            lengthPercentageType = lengthPercentage.type;
        }
        return lengthPercentage.copy(f, lengthPercentageType);
    }

    @JvmStatic
    public static final LengthPercentage setFromDynamic(Dynamic dynamic) {
        return INSTANCE.setFromDynamic(dynamic);
    }

    /* renamed from: component2, reason: from getter */
    public final LengthPercentageType getType() {
        return this.type;
    }

    public final LengthPercentage copy(float value, LengthPercentageType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new LengthPercentage(value, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LengthPercentage)) {
            return false;
        }
        LengthPercentage lengthPercentage = (LengthPercentage) other;
        return Float.compare(this.value, lengthPercentage.value) == 0 && this.type == lengthPercentage.type;
    }

    public int hashCode() {
        return (Float.hashCode(this.value) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "LengthPercentage(value=" + this.value + ", type=" + this.type + ")";
    }

    public LengthPercentage(float f, LengthPercentageType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.value = f;
        this.type = type;
    }

    public final LengthPercentageType getType() {
        return this.type;
    }

    /* compiled from: LengthPercentage.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/LengthPercentage$Companion;", "", "()V", "setFromDynamic", "Lcom/facebook/react/uimanager/LengthPercentage;", "dynamic", "Lcom/facebook/react/bridge/Dynamic;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        /* compiled from: LengthPercentage.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ReadableType.values().length];
                try {
                    iArr[ReadableType.Number.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ReadableType.String.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final LengthPercentage setFromDynamic(Dynamic dynamic) throws NumberFormatException {
            Intrinsics.checkNotNullParameter(dynamic, "dynamic");
            int i = WhenMappings.$EnumSwitchMapping$0[dynamic.getType().ordinal()];
            if (i == 1) {
                double dAsDouble = dynamic.asDouble();
                if (dAsDouble >= 0.0d) {
                    return new LengthPercentage((float) dAsDouble, LengthPercentageType.POINT);
                }
                return null;
            }
            if (i == 2) {
                String strAsString = dynamic.asString();
                if (StringsKt.endsWith$default(strAsString, "%", false, 2, (Object) null)) {
                    try {
                        String strSubstring = strAsString.substring(0, strAsString.length() - 1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        float f = Float.parseFloat(strSubstring);
                        if (f >= 0.0f) {
                            return new LengthPercentage(f, LengthPercentageType.PERCENT);
                        }
                        return null;
                    } catch (NumberFormatException unused) {
                        FLog.w(ReactConstants.TAG, "Invalid percentage format: " + strAsString);
                        return null;
                    }
                }
                FLog.w(ReactConstants.TAG, "Invalid string value: " + strAsString);
                return null;
            }
            FLog.w(ReactConstants.TAG, "Unsupported type for radius property: " + dynamic.getType());
            return null;
        }
    }

    public final CornerRadii resolve(float width, float height) {
        if (this.type == LengthPercentageType.PERCENT) {
            float f = this.value;
            float f2 = 100;
            return new CornerRadii((f / f2) * width, (f / f2) * height);
        }
        float f3 = this.value;
        return new CornerRadii(f3, f3);
    }

    public LengthPercentage() {
        this(0.0f, LengthPercentageType.POINT);
    }
}
