package com.doublesymmetry.kotlinaudio.models;

import com.doublesymmetry.trackplayer.service.MusicService;
import com.google.android.exoplayer2.ExoPlayer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueuedPlayerOptions.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/DefaultQueuedPlayerOptions;", "Lcom/doublesymmetry/kotlinaudio/models/QueuedPlayerOptions;", "exoPlayer", "Lcom/google/android/exoplayer2/ExoPlayer;", MusicService.PAUSE_ON_INTERRUPTION_KEY, "", "(Lcom/google/android/exoplayer2/ExoPlayer;Z)V", "getAlwaysPauseOnInterruption", "()Z", "setAlwaysPauseOnInterruption", "(Z)V", "value", "Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "repeatMode", "getRepeatMode", "()Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "setRepeatMode", "(Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultQueuedPlayerOptions implements QueuedPlayerOptions {
    private boolean alwaysPauseOnInterruption;
    private final ExoPlayer exoPlayer;

    /* compiled from: QueuedPlayerOptions.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            iArr[RepeatMode.ALL.ordinal()] = 1;
            iArr[RepeatMode.ONE.ordinal()] = 2;
            iArr[RepeatMode.OFF.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultQueuedPlayerOptions(ExoPlayer exoPlayer, boolean z) {
        Intrinsics.checkNotNullParameter(exoPlayer, "exoPlayer");
        this.exoPlayer = exoPlayer;
        this.alwaysPauseOnInterruption = z;
    }

    public /* synthetic */ DefaultQueuedPlayerOptions(ExoPlayer exoPlayer, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(exoPlayer, (i & 2) != 0 ? false : z);
    }

    @Override // com.doublesymmetry.kotlinaudio.models.QueuedPlayerOptions, com.doublesymmetry.kotlinaudio.models.PlayerOptions
    public boolean getAlwaysPauseOnInterruption() {
        return this.alwaysPauseOnInterruption;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.QueuedPlayerOptions, com.doublesymmetry.kotlinaudio.models.PlayerOptions
    public void setAlwaysPauseOnInterruption(boolean z) {
        this.alwaysPauseOnInterruption = z;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.QueuedPlayerOptions
    public RepeatMode getRepeatMode() {
        int repeatMode = this.exoPlayer.getRepeatMode();
        if (repeatMode == 1) {
            return RepeatMode.ONE;
        }
        if (repeatMode == 2) {
            return RepeatMode.ALL;
        }
        return RepeatMode.OFF;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.QueuedPlayerOptions
    public void setRepeatMode(RepeatMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        int i = WhenMappings.$EnumSwitchMapping$0[value.ordinal()];
        if (i == 1) {
            this.exoPlayer.setRepeatMode(2);
        } else if (i == 2) {
            this.exoPlayer.setRepeatMode(1);
        } else {
            if (i != 3) {
                return;
            }
            this.exoPlayer.setRepeatMode(0);
        }
    }
}
