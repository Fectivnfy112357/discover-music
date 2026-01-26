package coil3.decode;

import android.content.res.AssetFileDescriptor;
import coil3.Uri;
import coil3.decode.ImageSource;
import kotlin.Metadata;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcoil3/decode/ContentMetadata;", "Lcoil3/decode/ImageSource$Metadata;", "uri", "Lcoil3/Uri;", "assetFileDescriptor", "Landroid/content/res/AssetFileDescriptor;", "<init>", "(Lcoil3/Uri;Landroid/content/res/AssetFileDescriptor;)V", "getUri", "()Lcoil3/Uri;", "getAssetFileDescriptor", "()Landroid/content/res/AssetFileDescriptor;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContentMetadata extends ImageSource.Metadata {
    private final AssetFileDescriptor assetFileDescriptor;
    private final Uri uri;

    public final Uri getUri() {
        return this.uri;
    }

    public final AssetFileDescriptor getAssetFileDescriptor() {
        return this.assetFileDescriptor;
    }

    public ContentMetadata(Uri uri, AssetFileDescriptor assetFileDescriptor) {
        this.uri = uri;
        this.assetFileDescriptor = assetFileDescriptor;
    }
}
