package com.doublesymmetry.kotlinaudio.models;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioItem.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ>\u0010\u0013\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "", "headers", "", "", "userAgent", "resourceId", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;)V", "getHeaders", "()Ljava/util/Map;", "getResourceId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUserAgent", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;)Lcom/doublesymmetry/kotlinaudio/models/AudioItemOptions;", "equals", "", "other", "hashCode", "toString", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AudioItemOptions {
    private final Map<String, String> headers;
    private final Integer resourceId;
    private final String userAgent;

    public AudioItemOptions() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AudioItemOptions copy$default(AudioItemOptions audioItemOptions, Map map, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            map = audioItemOptions.headers;
        }
        if ((i & 2) != 0) {
            str = audioItemOptions.userAgent;
        }
        if ((i & 4) != 0) {
            num = audioItemOptions.resourceId;
        }
        return audioItemOptions.copy(map, str, num);
    }

    public final Map<String, String> component1() {
        return this.headers;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserAgent() {
        return this.userAgent;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getResourceId() {
        return this.resourceId;
    }

    public final AudioItemOptions copy(Map<String, String> headers, String userAgent, Integer resourceId) {
        return new AudioItemOptions(headers, userAgent, resourceId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioItemOptions)) {
            return false;
        }
        AudioItemOptions audioItemOptions = (AudioItemOptions) other;
        return Intrinsics.areEqual(this.headers, audioItemOptions.headers) && Intrinsics.areEqual(this.userAgent, audioItemOptions.userAgent) && Intrinsics.areEqual(this.resourceId, audioItemOptions.resourceId);
    }

    public int hashCode() {
        Map<String, String> map = this.headers;
        int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
        String str = this.userAgent;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.resourceId;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "AudioItemOptions(headers=" + this.headers + ", userAgent=" + this.userAgent + ", resourceId=" + this.resourceId + ")";
    }

    public AudioItemOptions(Map<String, String> map, String str, Integer num) {
        this.headers = map;
        this.userAgent = str;
        this.resourceId = num;
    }

    public /* synthetic */ AudioItemOptions(Map map, String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num);
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final Integer getResourceId() {
        return this.resourceId;
    }
}
