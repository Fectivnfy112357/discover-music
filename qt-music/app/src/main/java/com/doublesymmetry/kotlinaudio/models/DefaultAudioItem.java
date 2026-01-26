package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioItem.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010)\u001a\u0004\u0018\u00010\rHÆ\u0003Jj\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00063"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/DefaultAudioItem;", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "audioUrl", "", "type", "Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "artist", "title", "albumTitle", "artwork", "duration", "", "options", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "(Ljava/lang/String;Lcom/doublesymmetry/kotlinaudio/models/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;)V", "getAlbumTitle", "()Ljava/lang/String;", "setAlbumTitle", "(Ljava/lang/String;)V", "getArtist", "setArtist", "getArtwork", "setArtwork", "getAudioUrl", "setAudioUrl", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOptions", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "getTitle", "setTitle", "getType", "()Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Lcom/doublesymmetry/kotlinaudio/models/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;)Lcom/doublesymmetry/kotlinaudio/models/DefaultAudioItem;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class DefaultAudioItem implements AudioItem {
    private String albumTitle;
    private String artist;
    private String artwork;
    private String audioUrl;
    private final Long duration;
    private final AudioItemOptions options;
    private String title;
    private final MediaType type;

    public final String component1() {
        return getAudioUrl();
    }

    public final MediaType component2() {
        return getType();
    }

    public final String component3() {
        return getArtist();
    }

    public final String component4() {
        return getTitle();
    }

    public final String component5() {
        return getAlbumTitle();
    }

    public final String component6() {
        return getArtwork();
    }

    public final Long component7() {
        return getDuration();
    }

    public final AudioItemOptions component8() {
        return getOptions();
    }

    public final DefaultAudioItem copy(String audioUrl, MediaType type, String artist, String title, String albumTitle, String artwork, Long duration, AudioItemOptions options) {
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        Intrinsics.checkNotNullParameter(type, "type");
        return new DefaultAudioItem(audioUrl, type, artist, title, albumTitle, artwork, duration, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultAudioItem)) {
            return false;
        }
        DefaultAudioItem defaultAudioItem = (DefaultAudioItem) other;
        return Intrinsics.areEqual(getAudioUrl(), defaultAudioItem.getAudioUrl()) && getType() == defaultAudioItem.getType() && Intrinsics.areEqual(getArtist(), defaultAudioItem.getArtist()) && Intrinsics.areEqual(getTitle(), defaultAudioItem.getTitle()) && Intrinsics.areEqual(getAlbumTitle(), defaultAudioItem.getAlbumTitle()) && Intrinsics.areEqual(getArtwork(), defaultAudioItem.getArtwork()) && Intrinsics.areEqual(getDuration(), defaultAudioItem.getDuration()) && Intrinsics.areEqual(getOptions(), defaultAudioItem.getOptions());
    }

    public int hashCode() {
        return (((((((((((((getAudioUrl().hashCode() * 31) + getType().hashCode()) * 31) + (getArtist() == null ? 0 : getArtist().hashCode())) * 31) + (getTitle() == null ? 0 : getTitle().hashCode())) * 31) + (getAlbumTitle() == null ? 0 : getAlbumTitle().hashCode())) * 31) + (getArtwork() == null ? 0 : getArtwork().hashCode())) * 31) + (getDuration() == null ? 0 : getDuration().hashCode())) * 31) + (getOptions() != null ? getOptions().hashCode() : 0);
    }

    public String toString() {
        return "DefaultAudioItem(audioUrl=" + getAudioUrl() + ", type=" + getType() + ", artist=" + getArtist() + ", title=" + getTitle() + ", albumTitle=" + getAlbumTitle() + ", artwork=" + getArtwork() + ", duration=" + getDuration() + ", options=" + getOptions() + ")";
    }

    public DefaultAudioItem(String audioUrl, MediaType type, String str, String str2, String str3, String str4, Long l, AudioItemOptions audioItemOptions) {
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        Intrinsics.checkNotNullParameter(type, "type");
        this.audioUrl = audioUrl;
        this.type = type;
        this.artist = str;
        this.title = str2;
        this.albumTitle = str3;
        this.artwork = str4;
        this.duration = l;
        this.options = audioItemOptions;
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

    public /* synthetic */ DefaultAudioItem(String str, MediaType mediaType, String str2, String str3, String str4, String str5, Long l, AudioItemOptions audioItemOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? MediaType.DEFAULT : mediaType, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : l, (i & 128) == 0 ? audioItemOptions : null);
    }

    @Override // com.doublesymmetry.kotlinaudio.models.AudioItem
    public MediaType getType() {
        return this.type;
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

    public void setArtwork(String str) {
        this.artwork = str;
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
