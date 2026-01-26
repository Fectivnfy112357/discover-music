package com.doublesymmetry.kotlinaudio.models;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioItem.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0004¨\u0006\u000e"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemHolder;", "", "audioItem", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "(Lcom/doublesymmetry/kotlinaudio/models/AudioItem;)V", "artworkBitmap", "Landroid/graphics/Bitmap;", "getArtworkBitmap", "()Landroid/graphics/Bitmap;", "setArtworkBitmap", "(Landroid/graphics/Bitmap;)V", "getAudioItem", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "setAudioItem", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AudioItemHolder {
    private Bitmap artworkBitmap;
    private AudioItem audioItem;

    public AudioItemHolder(AudioItem audioItem) {
        Intrinsics.checkNotNullParameter(audioItem, "audioItem");
        this.audioItem = audioItem;
    }

    public final AudioItem getAudioItem() {
        return this.audioItem;
    }

    public final void setAudioItem(AudioItem audioItem) {
        Intrinsics.checkNotNullParameter(audioItem, "<set-?>");
        this.audioItem = audioItem;
    }

    public final Bitmap getArtworkBitmap() {
        return this.artworkBitmap;
    }

    public final void setArtworkBitmap(Bitmap bitmap) {
        this.artworkBitmap = bitmap;
    }
}
