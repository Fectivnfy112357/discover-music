package com.doublesymmetry.kotlinaudio.models;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: PlayerOptions.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/DefaultPlayerOptions;", "Lcom/doublesymmetry/kotlinaudio/models/PlayerOptions;", MusicService.PAUSE_ON_INTERRUPTION_KEY, "", "(Z)V", "getAlwaysPauseOnInterruption", "()Z", "setAlwaysPauseOnInterruption", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class DefaultPlayerOptions implements PlayerOptions {
    private boolean alwaysPauseOnInterruption;

    public DefaultPlayerOptions() {
        this(false, 1, null);
    }

    public static /* synthetic */ DefaultPlayerOptions copy$default(DefaultPlayerOptions defaultPlayerOptions, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = defaultPlayerOptions.getAlwaysPauseOnInterruption();
        }
        return defaultPlayerOptions.copy(z);
    }

    public final boolean component1() {
        return getAlwaysPauseOnInterruption();
    }

    public final DefaultPlayerOptions copy(boolean alwaysPauseOnInterruption) {
        return new DefaultPlayerOptions(alwaysPauseOnInterruption);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DefaultPlayerOptions) && getAlwaysPauseOnInterruption() == ((DefaultPlayerOptions) other).getAlwaysPauseOnInterruption();
    }

    public int hashCode() {
        boolean alwaysPauseOnInterruption = getAlwaysPauseOnInterruption();
        if (alwaysPauseOnInterruption) {
            return 1;
        }
        return alwaysPauseOnInterruption ? 1 : 0;
    }

    public String toString() {
        return "DefaultPlayerOptions(alwaysPauseOnInterruption=" + getAlwaysPauseOnInterruption() + ")";
    }

    public DefaultPlayerOptions(boolean z) {
        this.alwaysPauseOnInterruption = z;
    }

    public /* synthetic */ DefaultPlayerOptions(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    @Override // com.doublesymmetry.kotlinaudio.models.PlayerOptions
    public boolean getAlwaysPauseOnInterruption() {
        return this.alwaysPauseOnInterruption;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.PlayerOptions
    public void setAlwaysPauseOnInterruption(boolean z) {
        this.alwaysPauseOnInterruption = z;
    }
}
