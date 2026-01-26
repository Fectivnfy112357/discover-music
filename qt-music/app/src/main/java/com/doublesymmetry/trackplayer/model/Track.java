package com.doublesymmetry.trackplayer.model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.doublesymmetry.kotlinaudio.models.AudioItemOptions;
import com.doublesymmetry.kotlinaudio.models.MediaType;
import com.doublesymmetry.trackplayer.utils.BundleUtils;
import com.facebook.hermes.intl.Constants;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.umeng.analytics.pro.f;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jaudiotagger.tag.datatype.DataTypes;

/* compiled from: Track.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\"\u00103\u001a\u0002042\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0006\u00105\u001a\u000206R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\f\"\u0004\b2\u0010\u000e¨\u00067"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/Track;", "Lcom/doublesymmetry/trackplayer/model/TrackMetadata;", f.X, "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "ratingType", "", "(Landroid/content/Context;Landroid/os/Bundle;I)V", DataTypes.OBJ_CONTENT_TYPE, "", "getContentType", "()Ljava/lang/String;", "setContentType", "(Ljava/lang/String;)V", "headers", "", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "originalItem", "getOriginalItem", "()Landroid/os/Bundle;", "setOriginalItem", "(Landroid/os/Bundle;)V", "queueId", "", "getQueueId", "()J", "resourceId", "getResourceId", "()Ljava/lang/Integer;", "setResourceId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "type", "Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "getType", "()Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "setType", "(Lcom/doublesymmetry/kotlinaudio/models/MediaType;)V", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "userAgent", "getUserAgent", "setUserAgent", "setMetadata", "", "toAudioItem", "Lcom/doublesymmetry/trackplayer/model/TrackAudioItem;", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Track extends TrackMetadata {
    private String contentType;
    private Map<String, String> headers;
    private Bundle originalItem;
    private final long queueId;
    private Integer resourceId;
    private MediaType type;
    private Uri uri;
    private String userAgent;

    public Track(Context context, Bundle bundle, int i) {
        Uri uriBuildRawResourceUri;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this.type = MediaType.DEFAULT;
        Integer numValueOf = Integer.valueOf(BundleUtils.INSTANCE.getRawResourceId(context, bundle, "url"));
        this.resourceId = numValueOf;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            this.resourceId = null;
            uriBuildRawResourceUri = BundleUtils.INSTANCE.getUri(context, bundle, "url");
        } else {
            Integer num = this.resourceId;
            Intrinsics.checkNotNull(num);
            uriBuildRawResourceUri = RawResourceDataSource.buildRawResourceUri(num.intValue());
        }
        this.uri = uriBuildRawResourceUri;
        String string = bundle.getString("type", Constants.COLLATION_DEFAULT);
        MediaType[] mediaTypeArrValues = MediaType.values();
        int length = mediaTypeArrValues.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            MediaType mediaType = mediaTypeArrValues[i2];
            if (StringsKt.equals(mediaType.name(), string, true)) {
                this.type = mediaType;
                break;
            }
            i2++;
        }
        this.contentType = bundle.getString(DataTypes.OBJ_CONTENT_TYPE);
        this.userAgent = bundle.getString("userAgent");
        Bundle bundle2 = bundle.getBundle("headers");
        if (bundle2 != null) {
            this.headers = new HashMap();
            for (String str : bundle2.keySet()) {
                Map<String, String> map = this.headers;
                Intrinsics.checkNotNull(map);
                Intrinsics.checkNotNull(str);
                String string2 = bundle2.getString(str);
                Intrinsics.checkNotNull(string2);
                map.put(str, string2);
            }
        }
        setMetadata(context, bundle, i);
        this.queueId = System.currentTimeMillis();
        this.originalItem = bundle;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(Uri uri) {
        this.uri = uri;
    }

    public final Integer getResourceId() {
        return this.resourceId;
    }

    public final void setResourceId(Integer num) {
        this.resourceId = num;
    }

    public final MediaType getType() {
        return this.type;
    }

    public final void setType(MediaType mediaType) {
        Intrinsics.checkNotNullParameter(mediaType, "<set-?>");
        this.type = mediaType;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setContentType(String str) {
        this.contentType = str;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final void setUserAgent(String str) {
        this.userAgent = str;
    }

    public final Bundle getOriginalItem() {
        return this.originalItem;
    }

    public final void setOriginalItem(Bundle bundle) {
        this.originalItem = bundle;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public final long getQueueId() {
        return this.queueId;
    }

    @Override // com.doublesymmetry.trackplayer.model.TrackMetadata
    public void setMetadata(Context context, Bundle bundle, int ratingType) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.setMetadata(context, bundle, ratingType);
        Bundle bundle2 = this.originalItem;
        if (bundle2 == null || Intrinsics.areEqual(bundle2, bundle)) {
            return;
        }
        Bundle bundle3 = this.originalItem;
        Intrinsics.checkNotNull(bundle3);
        bundle3.putAll(bundle);
    }

    public final TrackAudioItem toAudioItem() {
        return new TrackAudioItem(this, this.type, String.valueOf(this.uri), getArtist(), getTitle(), getAlbum(), String.valueOf(getArtwork()), getDuration(), new AudioItemOptions(this.headers, this.userAgent, this.resourceId));
    }
}
