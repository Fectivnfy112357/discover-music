package com.doublesymmetry.kotlinaudio.models;

import com.facebook.hermes.intl.Constants;
import kotlin.Metadata;

/* compiled from: AudioItem.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/MediaType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "DEFAULT", "DASH", "HLS", "SMOOTH_STREAMING", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public enum MediaType {
    DEFAULT(Constants.COLLATION_DEFAULT),
    DASH("dash"),
    HLS("hls"),
    SMOOTH_STREAMING("smoothstreaming");

    private final String value;

    MediaType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
