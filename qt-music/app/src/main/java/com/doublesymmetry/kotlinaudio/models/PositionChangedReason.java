package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* compiled from: PositionChangedReason.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\t\n\u000b\f\r\u000eB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0006\u000f\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "", "oldPosition", "", "newPosition", "(JJ)V", "getNewPosition", "()J", "getOldPosition", "AUTO", "QUEUE_CHANGED", ID3v24Frames.FRAME_ID_SEEK, "SEEK_FAILED", "SKIPPED_PERIOD", "UNKNOWN", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$AUTO;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$QUEUE_CHANGED;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SEEK;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SEEK_FAILED;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SKIPPED_PERIOD;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$UNKNOWN;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class PositionChangedReason {
    private final long newPosition;
    private final long oldPosition;

    public /* synthetic */ PositionChangedReason(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    private PositionChangedReason(long j, long j2) {
        this.oldPosition = j;
        this.newPosition = j2;
    }

    public final long getNewPosition() {
        return this.newPosition;
    }

    public final long getOldPosition() {
        return this.oldPosition;
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$AUTO;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AUTO extends PositionChangedReason {
        public AUTO(long j, long j2) {
            super(j, j2, null);
        }
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$QUEUE_CHANGED;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class QUEUE_CHANGED extends PositionChangedReason {
        public QUEUE_CHANGED(long j, long j2) {
            super(j, j2, null);
        }
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SEEK;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SEEK extends PositionChangedReason {
        public SEEK(long j, long j2) {
            super(j, j2, null);
        }
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SEEK_FAILED;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SEEK_FAILED extends PositionChangedReason {
        public SEEK_FAILED(long j, long j2) {
            super(j, j2, null);
        }
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$SKIPPED_PERIOD;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SKIPPED_PERIOD extends PositionChangedReason {
        public SKIPPED_PERIOD(long j, long j2) {
            super(j, j2, null);
        }
    }

    /* compiled from: PositionChangedReason.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason$UNKNOWN;", "Lcom/doublesymmetry/kotlinaudio/models/PositionChangedReason;", "oldPosition", "", "newPosition", "(JJ)V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class UNKNOWN extends PositionChangedReason {
        public UNKNOWN(long j, long j2) {
            super(j, j2, null);
        }
    }
}
