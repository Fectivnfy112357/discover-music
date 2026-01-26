package com.swmansion.rnscreens.utils;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: PaddingBundle.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/rnscreens/utils/PaddingBundle;", "", "height", "", ViewProps.PADDING_START, ViewProps.PADDING_END, "(FFF)V", "getHeight", "()F", "getPaddingEnd", "getPaddingStart", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PaddingBundle {
    private final float height;
    private final float paddingEnd;
    private final float paddingStart;

    public static /* synthetic */ PaddingBundle copy$default(PaddingBundle paddingBundle, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = paddingBundle.height;
        }
        if ((i & 2) != 0) {
            f2 = paddingBundle.paddingStart;
        }
        if ((i & 4) != 0) {
            f3 = paddingBundle.paddingEnd;
        }
        return paddingBundle.copy(f, f2, f3);
    }

    /* renamed from: component1, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    /* renamed from: component2, reason: from getter */
    public final float getPaddingStart() {
        return this.paddingStart;
    }

    /* renamed from: component3, reason: from getter */
    public final float getPaddingEnd() {
        return this.paddingEnd;
    }

    public final PaddingBundle copy(float height, float paddingStart, float paddingEnd) {
        return new PaddingBundle(height, paddingStart, paddingEnd);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PaddingBundle)) {
            return false;
        }
        PaddingBundle paddingBundle = (PaddingBundle) other;
        return Float.compare(this.height, paddingBundle.height) == 0 && Float.compare(this.paddingStart, paddingBundle.paddingStart) == 0 && Float.compare(this.paddingEnd, paddingBundle.paddingEnd) == 0;
    }

    public int hashCode() {
        return (((Float.hashCode(this.height) * 31) + Float.hashCode(this.paddingStart)) * 31) + Float.hashCode(this.paddingEnd);
    }

    public String toString() {
        return "PaddingBundle(height=" + this.height + ", paddingStart=" + this.paddingStart + ", paddingEnd=" + this.paddingEnd + ")";
    }

    public PaddingBundle(float f, float f2, float f3) {
        this.height = f;
        this.paddingStart = f2;
        this.paddingEnd = f3;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getPaddingStart() {
        return this.paddingStart;
    }

    public final float getPaddingEnd() {
        return this.paddingEnd;
    }
}
