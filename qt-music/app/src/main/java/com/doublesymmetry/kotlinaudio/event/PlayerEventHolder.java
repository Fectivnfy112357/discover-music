package com.doublesymmetry.kotlinaudio.event;

import com.doublesymmetry.kotlinaudio.models.AudioItemTransitionReason;
import com.doublesymmetry.kotlinaudio.models.AudioPlayerState;
import com.doublesymmetry.kotlinaudio.models.FocusChangeData;
import com.doublesymmetry.kotlinaudio.models.MediaSessionCallback;
import com.doublesymmetry.kotlinaudio.models.PlayWhenReadyChangeData;
import com.doublesymmetry.kotlinaudio.models.PlaybackEndedReason;
import com.doublesymmetry.kotlinaudio.models.PlaybackError;
import com.doublesymmetry.kotlinaudio.models.PositionChangedReason;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.google.android.exoplayer2.MediaMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: PlayerEventHolder.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0005H\u0000¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020\u0017H\u0000¢\u0006\u0002\bAJ\u001d\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0000¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020<2\u0006\u0010H\u001a\u00020\tH\u0000¢\u0006\u0002\bIJ\u0015\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020\u000bH\u0000¢\u0006\u0002\bLJ\u0015\u0010M\u001a\u00020<2\u0006\u0010H\u001a\u00020\rH\u0000¢\u0006\u0002\bNJ\u0015\u0010O\u001a\u00020<2\u0006\u0010,\u001a\u00020\u000fH\u0000¢\u0006\u0002\bPJ\u0015\u0010Q\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0011H\u0000¢\u0006\u0002\bRJ\u0015\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020\u0013H\u0000¢\u0006\u0002\bUJ\u0015\u0010V\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0015H\u0000¢\u0006\u0002\bWR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR \u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001dR \u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001b\"\u0004\b(\u0010\u001dR \u0010)\u001a\b\u0012\u0004\u0012\u00020\r0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR \u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010\u001dR\"\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR \u00102\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001b\"\u0004\b4\u0010\u001dR\"\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u0010\u001dR \u00108\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001b\"\u0004\b:\u0010\u001d¨\u0006X"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;", "", "()V", "_audioItemTransition", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "_onAudioFocusChanged", "Lcom/doublesymmetry/kotlinaudio/models/FocusChangeData;", "_onCommonMetadata", "Lcom/google/android/exoplayer2/MediaMetadata;", "_onPlayerActionTriggeredExternally", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "_onTimedMetadata", "Lcom/google/android/exoplayer2/metadata/Metadata;", "_playWhenReadyChange", "Lcom/doublesymmetry/kotlinaudio/models/PlayWhenReadyChangeData;", "_playbackEnd", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackEndedReason;", "_playbackError", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "_positionChanged", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "_stateChange", "Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "audioItemTransition", "Lkotlinx/coroutines/flow/SharedFlow;", "getAudioItemTransition", "()Lkotlinx/coroutines/flow/SharedFlow;", "setAudioItemTransition", "(Lkotlinx/coroutines/flow/SharedFlow;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "onAudioFocusChanged", "getOnAudioFocusChanged", "setOnAudioFocusChanged", "onCommonMetadata", "getOnCommonMetadata", "setOnCommonMetadata", "onPlayerActionTriggeredExternally", "getOnPlayerActionTriggeredExternally", "setOnPlayerActionTriggeredExternally", "onTimedMetadata", "getOnTimedMetadata", "setOnTimedMetadata", "playWhenReadyChange", "getPlayWhenReadyChange", "setPlayWhenReadyChange", "playbackEnd", "getPlaybackEnd", "setPlaybackEnd", "playbackError", "getPlaybackError", "setPlaybackError", "positionChanged", "getPositionChanged", "setPositionChanged", "stateChange", "getStateChange", "setStateChange", "updateAudioItemTransition", "", "reason", "updateAudioItemTransition$kotlin_audio_release", "updateAudioPlayerState", MusicService.STATE_KEY, "updateAudioPlayerState$kotlin_audio_release", "updateOnAudioFocusChanged", "isPaused", "", "isPermanent", "updateOnAudioFocusChanged$kotlin_audio_release", "updateOnCommonMetadata", "metadata", "updateOnCommonMetadata$kotlin_audio_release", "updateOnPlayerActionTriggeredExternally", "callback", "updateOnPlayerActionTriggeredExternally$kotlin_audio_release", "updateOnTimedMetadata", "updateOnTimedMetadata$kotlin_audio_release", "updatePlayWhenReadyChange", "updatePlayWhenReadyChange$kotlin_audio_release", "updatePlaybackEndedReason", "updatePlaybackEndedReason$kotlin_audio_release", "updatePlaybackError", "error", "updatePlaybackError$kotlin_audio_release", "updatePositionChangedReason", "updatePositionChangedReason$kotlin_audio_release", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PlayerEventHolder {
    private MutableSharedFlow<AudioItemTransitionReason> _audioItemTransition;
    private MutableSharedFlow<FocusChangeData> _onAudioFocusChanged;
    private MutableSharedFlow<MediaMetadata> _onCommonMetadata;
    private MutableSharedFlow<MediaSessionCallback> _onPlayerActionTriggeredExternally;
    private MutableSharedFlow<com.google.android.exoplayer2.metadata.Metadata> _onTimedMetadata;
    private MutableSharedFlow<PlayWhenReadyChangeData> _playWhenReadyChange;
    private MutableSharedFlow<PlaybackEndedReason> _playbackEnd;
    private MutableSharedFlow<PlaybackError> _playbackError;
    private MutableSharedFlow<PositionChangedReason> _positionChanged;
    private MutableSharedFlow<AudioPlayerState> _stateChange;
    private SharedFlow<? extends AudioItemTransitionReason> audioItemTransition;
    private final CoroutineScope coroutineScope = CoroutineScopeKt.MainScope();
    private SharedFlow<FocusChangeData> onAudioFocusChanged;
    private SharedFlow<MediaMetadata> onCommonMetadata;
    private SharedFlow<? extends MediaSessionCallback> onPlayerActionTriggeredExternally;
    private SharedFlow<com.google.android.exoplayer2.metadata.Metadata> onTimedMetadata;
    private SharedFlow<PlayWhenReadyChangeData> playWhenReadyChange;
    private SharedFlow<? extends PlaybackEndedReason> playbackEnd;
    private SharedFlow<PlaybackError> playbackError;
    private SharedFlow<? extends PositionChangedReason> positionChanged;
    private SharedFlow<? extends AudioPlayerState> stateChange;

    public PlayerEventHolder() {
        MutableSharedFlow<AudioPlayerState> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._stateChange = mutableSharedFlowMutableSharedFlow$default;
        this.stateChange = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        MutableSharedFlow<PlaybackEndedReason> mutableSharedFlowMutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._playbackEnd = mutableSharedFlowMutableSharedFlow$default2;
        this.playbackEnd = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default2);
        MutableSharedFlow<PlaybackError> mutableSharedFlowMutableSharedFlow$default3 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._playbackError = mutableSharedFlowMutableSharedFlow$default3;
        this.playbackError = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default3);
        MutableSharedFlow<PlayWhenReadyChangeData> mutableSharedFlowMutableSharedFlow$default4 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._playWhenReadyChange = mutableSharedFlowMutableSharedFlow$default4;
        this.playWhenReadyChange = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default4);
        MutableSharedFlow<AudioItemTransitionReason> mutableSharedFlowMutableSharedFlow$default5 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._audioItemTransition = mutableSharedFlowMutableSharedFlow$default5;
        this.audioItemTransition = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default5);
        MutableSharedFlow<PositionChangedReason> mutableSharedFlowMutableSharedFlow$default6 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._positionChanged = mutableSharedFlowMutableSharedFlow$default6;
        this.positionChanged = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default6);
        MutableSharedFlow<FocusChangeData> mutableSharedFlowMutableSharedFlow$default7 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._onAudioFocusChanged = mutableSharedFlowMutableSharedFlow$default7;
        this.onAudioFocusChanged = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default7);
        MutableSharedFlow<MediaMetadata> mutableSharedFlowMutableSharedFlow$default8 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._onCommonMetadata = mutableSharedFlowMutableSharedFlow$default8;
        this.onCommonMetadata = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default8);
        MutableSharedFlow<com.google.android.exoplayer2.metadata.Metadata> mutableSharedFlowMutableSharedFlow$default9 = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._onTimedMetadata = mutableSharedFlowMutableSharedFlow$default9;
        this.onTimedMetadata = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default9);
        MutableSharedFlow<MediaSessionCallback> mutableSharedFlowMutableSharedFlow$default10 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._onPlayerActionTriggeredExternally = mutableSharedFlowMutableSharedFlow$default10;
        this.onPlayerActionTriggeredExternally = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default10);
    }

    public final SharedFlow<AudioPlayerState> getStateChange() {
        return this.stateChange;
    }

    public final void setStateChange(SharedFlow<? extends AudioPlayerState> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.stateChange = sharedFlow;
    }

    public final SharedFlow<PlaybackEndedReason> getPlaybackEnd() {
        return this.playbackEnd;
    }

    public final void setPlaybackEnd(SharedFlow<? extends PlaybackEndedReason> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.playbackEnd = sharedFlow;
    }

    public final SharedFlow<PlaybackError> getPlaybackError() {
        return this.playbackError;
    }

    public final void setPlaybackError(SharedFlow<PlaybackError> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.playbackError = sharedFlow;
    }

    public final SharedFlow<PlayWhenReadyChangeData> getPlayWhenReadyChange() {
        return this.playWhenReadyChange;
    }

    public final void setPlayWhenReadyChange(SharedFlow<PlayWhenReadyChangeData> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.playWhenReadyChange = sharedFlow;
    }

    public final SharedFlow<AudioItemTransitionReason> getAudioItemTransition() {
        return this.audioItemTransition;
    }

    public final void setAudioItemTransition(SharedFlow<? extends AudioItemTransitionReason> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.audioItemTransition = sharedFlow;
    }

    public final SharedFlow<PositionChangedReason> getPositionChanged() {
        return this.positionChanged;
    }

    public final void setPositionChanged(SharedFlow<? extends PositionChangedReason> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.positionChanged = sharedFlow;
    }

    public final SharedFlow<FocusChangeData> getOnAudioFocusChanged() {
        return this.onAudioFocusChanged;
    }

    public final void setOnAudioFocusChanged(SharedFlow<FocusChangeData> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.onAudioFocusChanged = sharedFlow;
    }

    public final SharedFlow<MediaMetadata> getOnCommonMetadata() {
        return this.onCommonMetadata;
    }

    public final void setOnCommonMetadata(SharedFlow<MediaMetadata> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.onCommonMetadata = sharedFlow;
    }

    public final SharedFlow<com.google.android.exoplayer2.metadata.Metadata> getOnTimedMetadata() {
        return this.onTimedMetadata;
    }

    public final void setOnTimedMetadata(SharedFlow<com.google.android.exoplayer2.metadata.Metadata> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.onTimedMetadata = sharedFlow;
    }

    public final SharedFlow<MediaSessionCallback> getOnPlayerActionTriggeredExternally() {
        return this.onPlayerActionTriggeredExternally;
    }

    public final void setOnPlayerActionTriggeredExternally(SharedFlow<? extends MediaSessionCallback> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.onPlayerActionTriggeredExternally = sharedFlow;
    }

    public final void updateAudioPlayerState$kotlin_audio_release(AudioPlayerState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateAudioPlayerState$1(this, state, null), 3, null);
    }

    public final void updatePlaybackEndedReason$kotlin_audio_release(PlaybackEndedReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updatePlaybackEndedReason$1(this, reason, null), 3, null);
    }

    public final void updatePlayWhenReadyChange$kotlin_audio_release(PlayWhenReadyChangeData playWhenReadyChange) {
        Intrinsics.checkNotNullParameter(playWhenReadyChange, "playWhenReadyChange");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updatePlayWhenReadyChange$1(this, playWhenReadyChange, null), 3, null);
    }

    public final void updateAudioItemTransition$kotlin_audio_release(AudioItemTransitionReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateAudioItemTransition$1(this, reason, null), 3, null);
    }

    public final void updatePositionChangedReason$kotlin_audio_release(PositionChangedReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updatePositionChangedReason$1(this, reason, null), 3, null);
    }

    public final void updateOnAudioFocusChanged$kotlin_audio_release(boolean isPaused, boolean isPermanent) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateOnAudioFocusChanged$1(this, isPaused, isPermanent, null), 3, null);
    }

    public final void updateOnCommonMetadata$kotlin_audio_release(MediaMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateOnCommonMetadata$1(this, metadata, null), 3, null);
    }

    public final void updateOnTimedMetadata$kotlin_audio_release(com.google.android.exoplayer2.metadata.Metadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateOnTimedMetadata$1(this, metadata, null), 3, null);
    }

    public final void updatePlaybackError$kotlin_audio_release(PlaybackError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updatePlaybackError$1(this, error, null), 3, null);
    }

    public final void updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new PlayerEventHolder$updateOnPlayerActionTriggeredExternally$1(this, callback, null), 3, null);
    }
}
