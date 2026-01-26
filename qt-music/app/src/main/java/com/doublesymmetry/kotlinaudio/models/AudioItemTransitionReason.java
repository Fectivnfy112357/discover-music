package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AudioItemTransitionReason.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "", "oldPosition", "", "(J)V", "getOldPosition", "()J", "AUTO", "QUEUE_CHANGED", "REPEAT", "SEEK_TO_ANOTHER_AUDIO_ITEM", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$AUTO;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$QUEUE_CHANGED;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$REPEAT;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$SEEK_TO_ANOTHER_AUDIO_ITEM;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class AudioItemTransitionReason {
    private final long oldPosition;

    public /* synthetic */ AudioItemTransitionReason(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    private AudioItemTransitionReason(long j) {
        this.oldPosition = j;
    }

    public final long getOldPosition() {
        return this.oldPosition;
    }

    /* compiled from: AudioItemTransitionReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$AUTO;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "oldPosition", "", "(J)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AUTO extends AudioItemTransitionReason {
        public AUTO(long j) {
            super(j, null);
        }
    }

    /* compiled from: AudioItemTransitionReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$SEEK_TO_ANOTHER_AUDIO_ITEM;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "oldPosition", "", "(J)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SEEK_TO_ANOTHER_AUDIO_ITEM extends AudioItemTransitionReason {
        public SEEK_TO_ANOTHER_AUDIO_ITEM(long j) {
            super(j, null);
        }
    }

    /* compiled from: AudioItemTransitionReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$REPEAT;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "oldPosition", "", "(J)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class REPEAT extends AudioItemTransitionReason {
        public REPEAT(long j) {
            super(j, null);
        }
    }

    /* compiled from: AudioItemTransitionReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason$QUEUE_CHANGED;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemTransitionReason;", "oldPosition", "", "(J)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class QUEUE_CHANGED extends AudioItemTransitionReason {
        public QUEUE_CHANGED(long j) {
            super(j, null);
        }
    }
}
