package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;

/* compiled from: AudioItem.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0005R\u0018\u0010\r\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0005\"\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0005\"\u0004\b\u001a\u0010\u0007R\u0012\u0010\u001b\u001a\u00020\u001cX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "", "albumTitle", "", "getAlbumTitle", "()Ljava/lang/String;", "setAlbumTitle", "(Ljava/lang/String;)V", "artist", "getArtist", "setArtist", "artwork", "getArtwork", "audioUrl", "getAudioUrl", "setAudioUrl", "duration", "", "getDuration", "()Ljava/lang/Long;", "options", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "getOptions", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "title", "getTitle", "setTitle", "type", "Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "getType", "()Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public interface AudioItem {
    String getAlbumTitle();

    String getArtist();

    String getArtwork();

    String getAudioUrl();

    Long getDuration();

    AudioItemOptions getOptions();

    String getTitle();

    MediaType getType();

    void setAlbumTitle(String str);

    void setArtist(String str);

    void setAudioUrl(String str);

    void setTitle(String str);
}
