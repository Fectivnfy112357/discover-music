package com.doublesymmetry.trackplayer.extensions;

import com.doublesymmetry.kotlinaudio.models.AudioPlayerState;
import com.doublesymmetry.trackplayer.model.State;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioPlayerStateExt.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"asLibState", "Lcom/doublesymmetry/trackplayer/model/State;", "Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "getAsLibState", "(Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;)Lcom/doublesymmetry/trackplayer/model/State;", "react-native-track-player_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AudioPlayerStateExtKt {

    /* compiled from: AudioPlayerStateExt.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AudioPlayerState.values().length];
            try {
                iArr[AudioPlayerState.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AudioPlayerState.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AudioPlayerState.BUFFERING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AudioPlayerState.PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AudioPlayerState.PLAYING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AudioPlayerState.IDLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[AudioPlayerState.ENDED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[AudioPlayerState.ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[AudioPlayerState.STOPPED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final State getAsLibState(AudioPlayerState audioPlayerState) {
        Intrinsics.checkNotNullParameter(audioPlayerState, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[audioPlayerState.ordinal()]) {
            case 1:
                return State.Loading;
            case 2:
                return State.Ready;
            case 3:
                return State.Buffering;
            case 4:
                return State.Paused;
            case 5:
                return State.Playing;
            case 6:
                return State.None;
            case 7:
                return State.Ended;
            case 8:
                return State.Error;
            case 9:
                return State.Stopped;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
