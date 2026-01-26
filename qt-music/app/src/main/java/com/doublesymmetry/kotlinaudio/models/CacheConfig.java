package com.doublesymmetry.kotlinaudio.models;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CacheConfig.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J$\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "", MusicService.MAX_CACHE_SIZE_KEY, "", "identifier", "", "(Ljava/lang/Long;Ljava/lang/String;)V", "getIdentifier", "()Ljava/lang/String;", "getMaxCacheSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/lang/String;)Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "equals", "", "other", "hashCode", "", "toString", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class CacheConfig {
    private final String identifier;
    private final Long maxCacheSize;

    public static /* synthetic */ CacheConfig copy$default(CacheConfig cacheConfig, Long l, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = cacheConfig.maxCacheSize;
        }
        if ((i & 2) != 0) {
            str = cacheConfig.identifier;
        }
        return cacheConfig.copy(l, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getMaxCacheSize() {
        return this.maxCacheSize;
    }

    /* renamed from: component2, reason: from getter */
    public final String getIdentifier() {
        return this.identifier;
    }

    public final CacheConfig copy(Long maxCacheSize, String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return new CacheConfig(maxCacheSize, identifier);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CacheConfig)) {
            return false;
        }
        CacheConfig cacheConfig = (CacheConfig) other;
        return Intrinsics.areEqual(this.maxCacheSize, cacheConfig.maxCacheSize) && Intrinsics.areEqual(this.identifier, cacheConfig.identifier);
    }

    public int hashCode() {
        Long l = this.maxCacheSize;
        return ((l == null ? 0 : l.hashCode()) * 31) + this.identifier.hashCode();
    }

    public String toString() {
        return "CacheConfig(maxCacheSize=" + this.maxCacheSize + ", identifier=" + this.identifier + ")";
    }

    public CacheConfig(Long l, String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        this.maxCacheSize = l;
        this.identifier = identifier;
    }

    public final Long getMaxCacheSize() {
        return this.maxCacheSize;
    }

    public /* synthetic */ CacheConfig(Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, (i & 2) != 0 ? MusicService.TASK_KEY : str);
    }

    public final String getIdentifier() {
        return this.identifier;
    }
}
