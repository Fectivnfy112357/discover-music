package com.doublesymmetry.kotlinaudio.event;

import com.doublesymmetry.kotlinaudio.models.AudioItemTransitionReason;
import com.doublesymmetry.kotlinaudio.models.AudioPlayerState;
import com.doublesymmetry.kotlinaudio.models.FocusChangeData;
import com.doublesymmetry.kotlinaudio.models.MediaSessionCallback;
import com.doublesymmetry.kotlinaudio.models.NotificationState;
import com.doublesymmetry.kotlinaudio.models.PlayWhenReadyChangeData;
import com.doublesymmetry.kotlinaudio.models.PlaybackEndedReason;
import com.doublesymmetry.kotlinaudio.models.PlaybackError;
import com.google.android.exoplayer2.MediaMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.SharedFlow;

/* compiled from: EventHolder.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000bR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\b8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000bR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\b8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000bR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\b8F¢\u0006\u0006\u001a\u0004\b \u0010\u000bR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\b8F¢\u0006\u0006\u001a\u0004\b#\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\b8F¢\u0006\u0006\u001a\u0004\b&\u0010\u000b¨\u0006'"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/event/EventHolder;", "", "notificationEventHolder", "Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;", "playerEventHolder", "Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;", "(Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;)V", "audioItemTransition", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "getAudioItemTransition", "()Lkotlinx/coroutines/flow/SharedFlow;", "notificationStateChange", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState;", "getNotificationStateChange", "onAudioFocusChanged", "Lcom/doublesymmetry/kotlinaudio/models/FocusChangeData;", "getOnAudioFocusChanged", "onCommonMetadata", "Lcom/google/android/exoplayer2/MediaMetadata;", "getOnCommonMetadata", "onPlayerActionTriggeredExternally", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "getOnPlayerActionTriggeredExternally", "onTimedMetadata", "Lcom/google/android/exoplayer2/metadata/Metadata;", "getOnTimedMetadata", "playWhenReadyChange", "Lcom/doublesymmetry/kotlinaudio/models/PlayWhenReadyChangeData;", "getPlayWhenReadyChange", "playbackEnd", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackEndedReason;", "getPlaybackEnd", "playbackError", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "getPlaybackError", "stateChange", "Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "getStateChange", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class EventHolder {
    private final NotificationEventHolder notificationEventHolder;
    private final PlayerEventHolder playerEventHolder;

    public EventHolder(NotificationEventHolder notificationEventHolder, PlayerEventHolder playerEventHolder) {
        Intrinsics.checkNotNullParameter(notificationEventHolder, "notificationEventHolder");
        Intrinsics.checkNotNullParameter(playerEventHolder, "playerEventHolder");
        this.notificationEventHolder = notificationEventHolder;
        this.playerEventHolder = playerEventHolder;
    }

    public final SharedFlow<AudioItemTransitionReason> getAudioItemTransition() {
        return this.playerEventHolder.getAudioItemTransition();
    }

    public final SharedFlow<NotificationState> getNotificationStateChange() {
        return this.notificationEventHolder.getNotificationStateChange();
    }

    public final SharedFlow<FocusChangeData> getOnAudioFocusChanged() {
        return this.playerEventHolder.getOnAudioFocusChanged();
    }

    public final SharedFlow<MediaMetadata> getOnCommonMetadata() {
        return this.playerEventHolder.getOnCommonMetadata();
    }

    public final SharedFlow<com.google.android.exoplayer2.metadata.Metadata> getOnTimedMetadata() {
        return this.playerEventHolder.getOnTimedMetadata();
    }

    public final SharedFlow<MediaSessionCallback> getOnPlayerActionTriggeredExternally() {
        return this.playerEventHolder.getOnPlayerActionTriggeredExternally();
    }

    public final SharedFlow<PlaybackEndedReason> getPlaybackEnd() {
        return this.playerEventHolder.getPlaybackEnd();
    }

    public final SharedFlow<PlayWhenReadyChangeData> getPlayWhenReadyChange() {
        return this.playerEventHolder.getPlayWhenReadyChange();
    }

    public final SharedFlow<AudioPlayerState> getStateChange() {
        return this.playerEventHolder.getStateChange();
    }

    public final SharedFlow<PlaybackError> getPlaybackError() {
        return this.playerEventHolder.getPlaybackError();
    }
}
