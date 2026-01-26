package com.facebook.react.uimanager.style;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BoxShadow.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0013JR\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0018\u0010\r¨\u0006'"}, d2 = {"Lcom/facebook/react/uimanager/style/BoxShadow;", "", "offsetX", "", "offsetY", "color", "", "blurRadius", "spreadDistance", "inset", "", "(FFLjava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Boolean;)V", "getBlurRadius", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getInset", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getOffsetX", "()F", "getOffsetY", "getSpreadDistance", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(FFLjava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Boolean;)Lcom/facebook/react/uimanager/style/BoxShadow;", "equals", "other", "hashCode", "toString", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BoxShadow {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Float blurRadius;
    private final Integer color;
    private final Boolean inset;
    private final float offsetX;
    private final float offsetY;
    private final Float spreadDistance;

    public static /* synthetic */ BoxShadow copy$default(BoxShadow boxShadow, float f, float f2, Integer num, Float f3, Float f4, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            f = boxShadow.offsetX;
        }
        if ((i & 2) != 0) {
            f2 = boxShadow.offsetY;
        }
        float f5 = f2;
        if ((i & 4) != 0) {
            num = boxShadow.color;
        }
        Integer num2 = num;
        if ((i & 8) != 0) {
            f3 = boxShadow.blurRadius;
        }
        Float f6 = f3;
        if ((i & 16) != 0) {
            f4 = boxShadow.spreadDistance;
        }
        Float f7 = f4;
        if ((i & 32) != 0) {
            bool = boxShadow.inset;
        }
        return boxShadow.copy(f, f5, num2, f6, f7, bool);
    }

    @JvmStatic
    public static final BoxShadow parse(ReadableMap readableMap) {
        return INSTANCE.parse(readableMap);
    }

    /* renamed from: component1, reason: from getter */
    public final float getOffsetX() {
        return this.offsetX;
    }

    /* renamed from: component2, reason: from getter */
    public final float getOffsetY() {
        return this.offsetY;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getColor() {
        return this.color;
    }

    /* renamed from: component4, reason: from getter */
    public final Float getBlurRadius() {
        return this.blurRadius;
    }

    /* renamed from: component5, reason: from getter */
    public final Float getSpreadDistance() {
        return this.spreadDistance;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getInset() {
        return this.inset;
    }

    public final BoxShadow copy(float offsetX, float offsetY, Integer color, Float blurRadius, Float spreadDistance, Boolean inset) {
        return new BoxShadow(offsetX, offsetY, color, blurRadius, spreadDistance, inset);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BoxShadow)) {
            return false;
        }
        BoxShadow boxShadow = (BoxShadow) other;
        return Float.compare(this.offsetX, boxShadow.offsetX) == 0 && Float.compare(this.offsetY, boxShadow.offsetY) == 0 && Intrinsics.areEqual(this.color, boxShadow.color) && Intrinsics.areEqual((Object) this.blurRadius, (Object) boxShadow.blurRadius) && Intrinsics.areEqual((Object) this.spreadDistance, (Object) boxShadow.spreadDistance) && Intrinsics.areEqual(this.inset, boxShadow.inset);
    }

    public int hashCode() {
        int iHashCode = ((Float.hashCode(this.offsetX) * 31) + Float.hashCode(this.offsetY)) * 31;
        Integer num = this.color;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Float f = this.blurRadius;
        int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.spreadDistance;
        int iHashCode4 = (iHashCode3 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Boolean bool = this.inset;
        return iHashCode4 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "BoxShadow(offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", color=" + this.color + ", blurRadius=" + this.blurRadius + ", spreadDistance=" + this.spreadDistance + ", inset=" + this.inset + ")";
    }

    public BoxShadow(float f, float f2, Integer num, Float f3, Float f4, Boolean bool) {
        this.offsetX = f;
        this.offsetY = f2;
        this.color = num;
        this.blurRadius = f3;
        this.spreadDistance = f4;
        this.inset = bool;
    }

    public /* synthetic */ BoxShadow(float f, float f2, Integer num, Float f3, Float f4, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : f3, (i & 16) != 0 ? null : f4, (i & 32) != 0 ? null : bool);
    }

    public final float getOffsetX() {
        return this.offsetX;
    }

    public final float getOffsetY() {
        return this.offsetY;
    }

    public final Integer getColor() {
        return this.color;
    }

    public final Float getBlurRadius() {
        return this.blurRadius;
    }

    public final Float getSpreadDistance() {
        return this.spreadDistance;
    }

    public final Boolean getInset() {
        return this.inset;
    }

    /* compiled from: BoxShadow.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/style/BoxShadow$Companion;", "", "()V", "parse", "Lcom/facebook/react/uimanager/style/BoxShadow;", ViewProps.BOX_SHADOW, "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BoxShadow parse(ReadableMap boxShadow) {
            Intrinsics.checkNotNullParameter(boxShadow, "boxShadow");
            if (boxShadow.hasKey("offsetX") && boxShadow.hasKey("offsetY")) {
                return new BoxShadow((float) boxShadow.getDouble("offsetX"), (float) boxShadow.getDouble("offsetY"), boxShadow.hasKey("color") ? Integer.valueOf(boxShadow.getInt("color")) : null, boxShadow.hasKey("blurRadius") ? Float.valueOf((float) boxShadow.getDouble("blurRadius")) : null, boxShadow.hasKey("spreadDistance") ? Float.valueOf((float) boxShadow.getDouble("spreadDistance")) : null, boxShadow.hasKey("inset") ? Boolean.valueOf(boxShadow.getBoolean("inset")) : null);
            }
            return null;
        }
    }
}
