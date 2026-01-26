package com.facebook.react.uimanager.style;

import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Gradient.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/uimanager/style/Gradient;", "", "gradient", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", LinearGradientManager.PROP_COLORS, "", "endX", "", "endY", "positions", "", "startX", "startY", "type", "Lcom/facebook/react/uimanager/style/Gradient$GradientType;", "getShader", "Landroid/graphics/Shader;", "bounds", "Landroid/graphics/Rect;", "GradientType", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Gradient {
    private final int[] colors;
    private float endX;
    private float endY;
    private final float[] positions;
    private float startX;
    private float startY;
    private final GradientType type;

    /* compiled from: Gradient.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GradientType.values().length];
            try {
                iArr[GradientType.LINEAR_GRADIENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Gradient.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/uimanager/style/Gradient$GradientType;", "", "(Ljava/lang/String;I)V", "LINEAR_GRADIENT", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class GradientType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ GradientType[] $VALUES;
        public static final GradientType LINEAR_GRADIENT = new GradientType("LINEAR_GRADIENT", 0);

        private static final /* synthetic */ GradientType[] $values() {
            return new GradientType[]{LINEAR_GRADIENT};
        }

        public static EnumEntries<GradientType> getEntries() {
            return $ENTRIES;
        }

        public static GradientType valueOf(String str) {
            return (GradientType) Enum.valueOf(GradientType.class, str);
        }

        public static GradientType[] values() {
            return (GradientType[]) $VALUES.clone();
        }

        private GradientType(String str, int i) {
        }

        static {
            GradientType[] gradientTypeArr$values = $values();
            $VALUES = gradientTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(gradientTypeArr$values);
        }
    }

    public Gradient(ReadableMap readableMap) {
        if (readableMap == null) {
            throw new IllegalArgumentException("Gradient cannot be null");
        }
        String string = readableMap.getString("type");
        if (Intrinsics.areEqual(string, "linearGradient")) {
            this.type = GradientType.LINEAR_GRADIENT;
            ReadableMap map = readableMap.getMap("start");
            if (map != null) {
                this.startX = (float) map.getDouble("x");
                this.startY = (float) map.getDouble("y");
            }
            ReadableMap map2 = readableMap.getMap("end");
            if (map2 != null) {
                this.endX = (float) map2.getDouble("x");
                this.endY = (float) map2.getDouble("y");
            }
            ReadableArray array = readableMap.getArray("colorStops");
            if (array == null) {
                throw new IllegalArgumentException("Invalid colorStops array");
            }
            int size = array.size();
            this.colors = new int[size];
            this.positions = new float[size];
            for (int i = 0; i < size; i++) {
                ReadableMap map3 = array.getMap(i);
                this.colors[i] = map3.getInt("color");
                this.positions[i] = (float) map3.getDouble("position");
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported gradient type: " + string);
    }

    public final Shader getShader(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        if (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()] == 1) {
            return new LinearGradient(this.startX * bounds.width(), this.startY * bounds.height(), this.endX * bounds.width(), this.endY * bounds.height(), this.colors, this.positions, Shader.TileMode.CLAMP);
        }
        throw new NoWhenBranchMatchedException();
    }
}
