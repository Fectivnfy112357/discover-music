package com.doublesymmetry.trackplayer.model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import com.doublesymmetry.trackplayer.extensions.NumberExt;
import com.doublesymmetry.trackplayer.utils.BundleUtils;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackMetadata.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\b¨\u00060"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/TrackMetadata;", "", "()V", "album", "", "getAlbum", "()Ljava/lang/String;", "setAlbum", "(Ljava/lang/String;)V", "artist", "getArtist", "setArtist", "artwork", "Landroid/net/Uri;", "getArtwork", "()Landroid/net/Uri;", "setArtwork", "(Landroid/net/Uri;)V", "date", "getDate", "setDate", "duration", "", "getDuration", "()Ljava/lang/Long;", "setDuration", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "genre", "getGenre", "setGenre", "rating", "Landroid/support/v4/media/RatingCompat;", "getRating", "()Landroid/support/v4/media/RatingCompat;", "setRating", "(Landroid/support/v4/media/RatingCompat;)V", "title", "getTitle", "setTitle", "setMetadata", "", f.X, "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "ratingType", "", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class TrackMetadata {
    private String album;
    private String artist;
    private Uri artwork;
    private String date;
    private Long duration;
    private String genre;
    private RatingCompat rating;
    private String title;

    public final Uri getArtwork() {
        return this.artwork;
    }

    public final void setArtwork(Uri uri) {
        this.artwork = uri;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final void setAlbum(String str) {
        this.album = str;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final void setGenre(String str) {
        this.genre = str;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final void setDuration(Long l) {
        this.duration = l;
    }

    public final RatingCompat getRating() {
        return this.rating;
    }

    public final void setRating(RatingCompat ratingCompat) {
        this.rating = ratingCompat;
    }

    public void setMetadata(Context context, Bundle bundle, int ratingType) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.artwork = BundleUtils.INSTANCE.getUri(context, bundle, "artwork");
        Intrinsics.checkNotNull(bundle);
        this.title = bundle.getString("title");
        this.artist = bundle.getString("artist");
        this.album = bundle.getString("album");
        this.date = bundle.getString("date");
        this.genre = bundle.getString("genre");
        this.duration = bundle.containsKey("duration") ? Long.valueOf(NumberExt.INSTANCE.toMilliseconds(Double.valueOf(bundle.getDouble("duration")))) : null;
        this.rating = BundleUtils.INSTANCE.getRating(bundle, "rating", ratingType);
    }
}
