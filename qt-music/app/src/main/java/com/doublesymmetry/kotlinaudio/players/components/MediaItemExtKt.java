package com.doublesymmetry.kotlinaudio.players.components;

import com.doublesymmetry.kotlinaudio.models.AudioItemHolder;
import com.google.android.exoplayer2.MediaItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItemExt.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getAudioItemHolder", "Lcom/doublesymmetry/kotlinaudio/models/AudioItemHolder;", "Lcom/google/android/exoplayer2/MediaItem;", "kotlin-audio_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MediaItemExtKt {
    public static final AudioItemHolder getAudioItemHolder(MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "<this>");
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        Intrinsics.checkNotNull(localConfiguration);
        Object obj = localConfiguration.tag;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.doublesymmetry.kotlinaudio.models.AudioItemHolder");
        return (AudioItemHolder) obj;
    }
}
