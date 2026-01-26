package com.doublesymmetry.kotlinaudio.players;

import android.content.Context;
import com.doublesymmetry.kotlinaudio.models.BufferConfig;
import com.doublesymmetry.kotlinaudio.models.CacheConfig;
import com.doublesymmetry.kotlinaudio.models.PlayerConfig;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioPlayer.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/players/AudioPlayer;", "Lcom/doublesymmetry/kotlinaudio/players/BaseAudioPlayer;", f.X, "Landroid/content/Context;", "playerConfig", "Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;", "bufferConfig", "Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;", "cacheConfig", "Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "(Landroid/content/Context;Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AudioPlayer extends BaseAudioPlayer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioPlayer(Context context, PlayerConfig playerConfig, BufferConfig bufferConfig, CacheConfig cacheConfig) {
        super(context, playerConfig, bufferConfig, cacheConfig);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playerConfig, "playerConfig");
    }

    public /* synthetic */ AudioPlayer(Context context, PlayerConfig playerConfig, BufferConfig bufferConfig, CacheConfig cacheConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new PlayerConfig(false, false, false, null, null, 31, null) : playerConfig, (i & 4) != 0 ? null : bufferConfig, (i & 8) != 0 ? null : cacheConfig);
    }
}
