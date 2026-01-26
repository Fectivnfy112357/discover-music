package com.doublesymmetry.trackplayer.model;

import com.doublesymmetry.kotlinaudio.models.AudioItem;
import com.doublesymmetry.kotlinaudio.models.AudioItemOptions;
import com.doublesymmetry.kotlinaudio.models.MediaType;
import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackAudioItem.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010-\u001a\u0004\u0018\u00010\u000fHÆ\u0003Jt\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0012\"\u0004\b\u0019\u0010\u0014R\u0018\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00067"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/TrackAudioItem;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", MusicService.TRACK_KEY, "Lcom/doublesymmetry/trackplayer/model/Track;", "type", "Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "audioUrl", "", "artist", "title", "albumTitle", "artwork", "duration", "", "options", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "(Lcom/doublesymmetry/trackplayer/model/Track;Lcom/doublesymmetry/kotlinaudio/models/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;)V", "getAlbumTitle", "()Ljava/lang/String;", "setAlbumTitle", "(Ljava/lang/String;)V", "getArtist", "setArtist", "getArtwork", "getAudioUrl", "setAudioUrl", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOptions", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "getTitle", "setTitle", "getTrack", "()Lcom/doublesymmetry/trackplayer/model/Track;", "getType", "()Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lcom/doublesymmetry/trackplayer/model/Track;Lcom/doublesymmetry/kotlinaudio/models/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;)Lcom/doublesymmetry/trackplayer/model/TrackAudioItem;", "equals", "", "other", "", "hashCode", "", "toString", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class TrackAudioItem implements AudioItem {
    private String albumTitle;
    private String artist;
    private final String artwork;
    private String audioUrl;
    private final Long duration;
    private final AudioItemOptions options;
    private String title;
    private final Track track;
    private final MediaType type;

    /* renamed from: component1, reason: from getter */
    public final Track getTrack() {
        return this.track;
    }

    /* renamed from: component2, reason: from getter */
    public final MediaType getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAudioUrl() {
        return this.audioUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAlbumTitle() {
        return this.albumTitle;
    }

    /* renamed from: component7, reason: from getter */
    public final String getArtwork() {
        return this.artwork;
    }

    /* renamed from: component8, reason: from getter */
    public final Long getDuration() {
        return this.duration;
    }

    /* renamed from: component9, reason: from getter */
    public final AudioItemOptions getOptions() {
        return this.options;
    }

    public final TrackAudioItem copy(Track track, MediaType type, String audioUrl, String artist, String title, String albumTitle, String artwork, Long duration, AudioItemOptions options) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        return new TrackAudioItem(track, type, audioUrl, artist, title, albumTitle, artwork, duration, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackAudioItem)) {
            return false;
        }
        TrackAudioItem trackAudioItem = (TrackAudioItem) other;
        return Intrinsics.areEqual(this.track, trackAudioItem.track) && this.type == trackAudioItem.type && Intrinsics.areEqual(this.audioUrl, trackAudioItem.audioUrl) && Intrinsics.areEqual(this.artist, trackAudioItem.artist) && Intrinsics.areEqual(this.title, trackAudioItem.title) && Intrinsics.areEqual(this.albumTitle, trackAudioItem.albumTitle) && Intrinsics.areEqual(this.artwork, trackAudioItem.artwork) && Intrinsics.areEqual(this.duration, trackAudioItem.duration) && Intrinsics.areEqual(this.options, trackAudioItem.options);
    }

    public int hashCode() {
        int iHashCode = ((((this.track.hashCode() * 31) + this.type.hashCode()) * 31) + this.audioUrl.hashCode()) * 31;
        String str = this.artist;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.title;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.albumTitle;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.artwork;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l = this.duration;
        int iHashCode6 = (iHashCode5 + (l == null ? 0 : l.hashCode())) * 31;
        AudioItemOptions audioItemOptions = this.options;
        return iHashCode6 + (audioItemOptions != null ? audioItemOptions.hashCode() : 0);
    }

    public String toString() {
        return "TrackAudioItem(track=" + this.track + ", type=" + this.type + ", audioUrl=" + this.audioUrl + ", artist=" + this.artist + ", title=" + this.title + ", albumTitle=" + this.albumTitle + ", artwork=" + this.artwork + ", duration=" + this.duration + ", options=" + this.options + ")";
    }

    public TrackAudioItem(Track track, MediaType type, String audioUrl, String str, String str2, String str3, String str4, Long l, AudioItemOptions audioItemOptions) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        this.track = track;
        this.type = type;
        this.audioUrl = audioUrl;
        this.artist = str;
        this.title = str2;
        this.albumTitle = str3;
        this.artwork = str4;
        this.duration = l;
        this.options = audioItemOptions;
    }

    public /* synthetic */ TrackAudioItem(Track track, MediaType mediaType, String str, String str2, String str3, String str4, String str5, Long l, AudioItemOptions audioItemOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(track, mediaType, str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : l, (i & 256) != 0 ? null : audioItemOptions);
    }

    public final Track getTrack() {
        return this.track;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public MediaType getType() {
        return this.type;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public String getAudioUrl() {
        return this.audioUrl;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public void setAudioUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.audioUrl = str;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public String getArtist() {
        return this.artist;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public void setArtist(String str) {
        this.artist = str;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public String getTitle() {
        return this.title;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public void setTitle(String str) {
        this.title = str;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public String getAlbumTitle() {
        return this.albumTitle;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public void setAlbumTitle(String str) {
        this.albumTitle = str;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public String getArtwork() {
        return this.artwork;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public Long getDuration() {
        return this.duration;
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public AudioItemOptions getOptions() {
        return this.options;
    }
}
