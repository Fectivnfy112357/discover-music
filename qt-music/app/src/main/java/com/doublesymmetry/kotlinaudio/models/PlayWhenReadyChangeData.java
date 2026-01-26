package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;

/* compiled from: PlayWhenReadyChangeData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PlayWhenReadyChangeData;", "", "playWhenReady", "", "pausedBecauseReachedEnd", "(ZZ)V", "getPausedBecauseReachedEnd", "()Z", "getPlayWhenReady", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class PlayWhenReadyChangeData {
    private final boolean pausedBecauseReachedEnd;
    private final boolean playWhenReady;

    public static /* synthetic */ PlayWhenReadyChangeData copy$default(PlayWhenReadyChangeData playWhenReadyChangeData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = playWhenReadyChangeData.playWhenReady;
        }
        if ((i & 2) != 0) {
            z2 = playWhenReadyChangeData.pausedBecauseReachedEnd;
        }
        return playWhenReadyChangeData.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getPausedBecauseReachedEnd() {
        return this.pausedBecauseReachedEnd;
    }

    public final PlayWhenReadyChangeData copy(boolean playWhenReady, boolean pausedBecauseReachedEnd) {
        return new PlayWhenReadyChangeData(playWhenReady, pausedBecauseReachedEnd);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayWhenReadyChangeData)) {
            return false;
        }
        PlayWhenReadyChangeData playWhenReadyChangeData = (PlayWhenReadyChangeData) other;
        return this.playWhenReady == playWhenReadyChangeData.playWhenReady && this.pausedBecauseReachedEnd == playWhenReadyChangeData.pausedBecauseReachedEnd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.playWhenReady;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.pausedBecauseReachedEnd;
        return i + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "PlayWhenReadyChangeData(playWhenReady=" + this.playWhenReady + ", pausedBecauseReachedEnd=" + this.pausedBecauseReachedEnd + ")";
    }

    public PlayWhenReadyChangeData(boolean z, boolean z2) {
        this.playWhenReady = z;
        this.pausedBecauseReachedEnd = z2;
    }

    public final boolean getPausedBecauseReachedEnd() {
        return this.pausedBecauseReachedEnd;
    }

    public final boolean getPlayWhenReady() {
        return this.playWhenReady;
    }
}
