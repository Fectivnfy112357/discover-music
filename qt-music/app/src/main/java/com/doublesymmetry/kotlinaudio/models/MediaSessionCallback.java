package com.doublesymmetry.kotlinaudio.models;

import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.audio.asf.data.ContentDescription;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* compiled from: MediaSessionCallback.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\t\u0003\u0004\u0005\u0006\u0007\b\t\n\u000bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\t\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "", "()V", "FORWARD", "NEXT", "PAUSE", "PLAY", "PREVIOUS", ContentDescription.KEY_RATING, "REWIND", ID3v24Frames.FRAME_ID_SEEK, "STOP", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$FORWARD;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$NEXT;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PAUSE;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PLAY;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PREVIOUS;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$RATING;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$REWIND;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$SEEK;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$STOP;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class MediaSessionCallback {
    public /* synthetic */ MediaSessionCallback(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$RATING;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "rating", "Landroid/support/v4/media/RatingCompat;", "extras", "Landroid/os/Bundle;", "(Landroid/support/v4/media/RatingCompat;Landroid/os/Bundle;)V", "getRating", "()Landroid/support/v4/media/RatingCompat;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class RATING extends MediaSessionCallback {
        private final RatingCompat rating;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RATING(RatingCompat rating, Bundle bundle) {
            super(null);
            Intrinsics.checkNotNullParameter(rating, "rating");
            this.rating = rating;
        }

        public final RatingCompat getRating() {
            return this.rating;
        }
    }

    private MediaSessionCallback() {
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PLAY;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class PLAY extends MediaSessionCallback {
        public static final PLAY INSTANCE = new PLAY();

        private PLAY() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PAUSE;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class PAUSE extends MediaSessionCallback {
        public static final PAUSE INSTANCE = new PAUSE();

        private PAUSE() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$NEXT;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NEXT extends MediaSessionCallback {
        public static final NEXT INSTANCE = new NEXT();

        private NEXT() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$PREVIOUS;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class PREVIOUS extends MediaSessionCallback {
        public static final PREVIOUS INSTANCE = new PREVIOUS();

        private PREVIOUS() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$FORWARD;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class FORWARD extends MediaSessionCallback {
        public static final FORWARD INSTANCE = new FORWARD();

        private FORWARD() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$REWIND;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class REWIND extends MediaSessionCallback {
        public static final REWIND INSTANCE = new REWIND();

        private REWIND() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$STOP;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class STOP extends MediaSessionCallback {
        public static final STOP INSTANCE = new STOP();

        private STOP() {
            super(null);
        }
    }

    /* compiled from: MediaSessionCallback.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback$SEEK;", "Lcom/doublesymmetry/kotlinaudio/models/MediaSessionCallback;", "positionMs", "", "(J)V", "getPositionMs", "()J", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SEEK extends MediaSessionCallback {
        private final long positionMs;

        public SEEK(long j) {
            super(null);
            this.positionMs = j;
        }

        public final long getPositionMs() {
            return this.positionMs;
        }
    }
}
