package com.doublesymmetry.kotlinaudio.models;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;

/* compiled from: QueuedPlayerOptions.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/QueuedPlayerOptions;", "Lcom/doublesymmetry/kotlinaudio/models/PlayerOptions;", MusicService.PAUSE_ON_INTERRUPTION_KEY, "", "getAlwaysPauseOnInterruption", "()Z", "setAlwaysPauseOnInterruption", "(Z)V", "repeatMode", "Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "getRepeatMode", "()Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "setRepeatMode", "(Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public interface QueuedPlayerOptions extends PlayerOptions {
    @Override // com.doublesymmetry.kotlinaudio.models.PlayerOptions
    boolean getAlwaysPauseOnInterruption();

    RepeatMode getRepeatMode();

    @Override // com.doublesymmetry.kotlinaudio.models.PlayerOptions
    void setAlwaysPauseOnInterruption(boolean z);

    void setRepeatMode(RepeatMode repeatMode);
}
