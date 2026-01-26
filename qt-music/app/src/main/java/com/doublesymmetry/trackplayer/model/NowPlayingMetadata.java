package com.doublesymmetry.trackplayer.model;

import android.content.Context;
import android.os.Bundle;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NowPlayingMetadata.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/NowPlayingMetadata;", "Lcom/doublesymmetry/trackplayer/model/TrackMetadata;", f.X, "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "ratingType", "", "(Landroid/content/Context;Landroid/os/Bundle;I)V", "elapsedTime", "", "getElapsedTime", "()D", "setElapsedTime", "(D)V", "setMetadata", "", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NowPlayingMetadata extends TrackMetadata {
    private double elapsedTime;

    public NowPlayingMetadata(Context context, Bundle bundle, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        setMetadata(context, bundle, i);
    }

    public final double getElapsedTime() {
        return this.elapsedTime;
    }

    public final void setElapsedTime(double d) {
        this.elapsedTime = d;
    }

    @Override // com.doublesymmetry.trackplayer.model.TrackMetadata
    public void setMetadata(Context context, Bundle bundle, int ratingType) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.setMetadata(context, bundle, ratingType);
        Intrinsics.checkNotNull(bundle);
        this.elapsedTime = bundle.getDouble("elapsedTime", 0.0d);
    }
}
