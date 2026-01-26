package com.doublesymmetry.kotlinaudio.players.components;

import android.content.Context;
import com.doublesymmetry.kotlinaudio.models.CacheConfig;
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.umeng.analytics.pro.f;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerCache.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/players/components/PlayerCache;", "", "()V", "instance", "Lcom/google/android/exoplayer2/upstream/cache/SimpleCache;", "getInstance", f.X, "Landroid/content/Context;", "cacheConfig", "Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PlayerCache {
    public static final PlayerCache INSTANCE = new PlayerCache();
    private static volatile SimpleCache instance;

    private PlayerCache() {
    }

    public final SimpleCache getInstance(Context context, CacheConfig cacheConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheConfig, "cacheConfig");
        File file = new File(context.getCacheDir(), cacheConfig.getIdentifier());
        StandaloneDatabaseProvider standaloneDatabaseProvider = new StandaloneDatabaseProvider(context);
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    Long maxCacheSize = cacheConfig.getMaxCacheSize();
                    instance = new SimpleCache(file, new LeastRecentlyUsedCacheEvictor(maxCacheSize != null ? maxCacheSize.longValue() : 0L), standaloneDatabaseProvider);
                }
            }
        }
        return instance;
    }
}
