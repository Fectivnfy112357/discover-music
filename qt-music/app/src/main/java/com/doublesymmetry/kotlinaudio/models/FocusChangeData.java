package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;

/* compiled from: FocusChangeData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/FocusChangeData;", "", "isPaused", "", "isFocusLostPermanently", "(ZZ)V", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class FocusChangeData {
    private final boolean isFocusLostPermanently;
    private final boolean isPaused;

    public static /* synthetic */ FocusChangeData copy$default(FocusChangeData focusChangeData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = focusChangeData.isPaused;
        }
        if ((i & 2) != 0) {
            z2 = focusChangeData.isFocusLostPermanently;
        }
        return focusChangeData.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsPaused() {
        return this.isPaused;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsFocusLostPermanently() {
        return this.isFocusLostPermanently;
    }

    public final FocusChangeData copy(boolean isPaused, boolean isFocusLostPermanently) {
        return new FocusChangeData(isPaused, isFocusLostPermanently);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FocusChangeData)) {
            return false;
        }
        FocusChangeData focusChangeData = (FocusChangeData) other;
        return this.isPaused == focusChangeData.isPaused && this.isFocusLostPermanently == focusChangeData.isFocusLostPermanently;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.isPaused;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isFocusLostPermanently;
        return i + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "FocusChangeData(isPaused=" + this.isPaused + ", isFocusLostPermanently=" + this.isFocusLostPermanently + ")";
    }

    public FocusChangeData(boolean z, boolean z2) {
        this.isPaused = z;
        this.isFocusLostPermanently = z2;
    }

    public final boolean isFocusLostPermanently() {
        return this.isFocusLostPermanently;
    }

    public final boolean isPaused() {
        return this.isPaused;
    }
}
