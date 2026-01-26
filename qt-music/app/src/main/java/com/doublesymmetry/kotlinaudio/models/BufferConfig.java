package com.doublesymmetry.kotlinaudio.models;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferConfig.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ>\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\r\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;", "", MusicService.MIN_BUFFER_KEY, "", MusicService.MAX_BUFFER_KEY, MusicService.PLAY_BUFFER_KEY, MusicService.BACK_BUFFER_KEY, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getBackBuffer", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxBuffer", "getMinBuffer", "getPlayBuffer", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;", "equals", "", "other", "hashCode", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class BufferConfig {
    private final Integer backBuffer;
    private final Integer maxBuffer;
    private final Integer minBuffer;
    private final Integer playBuffer;

    public static /* synthetic */ BufferConfig copy$default(BufferConfig bufferConfig, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = bufferConfig.minBuffer;
        }
        if ((i & 2) != 0) {
            num2 = bufferConfig.maxBuffer;
        }
        if ((i & 4) != 0) {
            num3 = bufferConfig.playBuffer;
        }
        if ((i & 8) != 0) {
            num4 = bufferConfig.backBuffer;
        }
        return bufferConfig.copy(num, num2, num3, num4);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getMinBuffer() {
        return this.minBuffer;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getMaxBuffer() {
        return this.maxBuffer;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getPlayBuffer() {
        return this.playBuffer;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getBackBuffer() {
        return this.backBuffer;
    }

    public final BufferConfig copy(Integer minBuffer, Integer maxBuffer, Integer playBuffer, Integer backBuffer) {
        return new BufferConfig(minBuffer, maxBuffer, playBuffer, backBuffer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BufferConfig)) {
            return false;
        }
        BufferConfig bufferConfig = (BufferConfig) other;
        return Intrinsics.areEqual(this.minBuffer, bufferConfig.minBuffer) && Intrinsics.areEqual(this.maxBuffer, bufferConfig.maxBuffer) && Intrinsics.areEqual(this.playBuffer, bufferConfig.playBuffer) && Intrinsics.areEqual(this.backBuffer, bufferConfig.backBuffer);
    }

    public int hashCode() {
        Integer num = this.minBuffer;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.maxBuffer;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.playBuffer;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.backBuffer;
        return iHashCode3 + (num4 != null ? num4.hashCode() : 0);
    }

    public String toString() {
        return "BufferConfig(minBuffer=" + this.minBuffer + ", maxBuffer=" + this.maxBuffer + ", playBuffer=" + this.playBuffer + ", backBuffer=" + this.backBuffer + ")";
    }

    public BufferConfig(Integer num, Integer num2, Integer num3, Integer num4) {
        this.minBuffer = num;
        this.maxBuffer = num2;
        this.playBuffer = num3;
        this.backBuffer = num4;
    }

    public final Integer getMinBuffer() {
        return this.minBuffer;
    }

    public final Integer getMaxBuffer() {
        return this.maxBuffer;
    }

    public final Integer getPlayBuffer() {
        return this.playBuffer;
    }

    public final Integer getBackBuffer() {
        return this.backBuffer;
    }
}
