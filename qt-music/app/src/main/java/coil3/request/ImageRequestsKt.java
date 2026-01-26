package coil3.request;

import coil3.Extras;
import coil3.ExtrasKt;
import coil3.ImageLoader;
import coil3.request.ImageRequest;
import coil3.size.Size;
import coil3.size.SizeKt;
import kotlin.Metadata;

/* compiled from: imageRequests.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a\u0012\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\t\u001a\u0012\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0013\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0007\u001a\u00020\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\u0007\u001a\u00020\t*\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u000e\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\u000f*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0011\"\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016\"\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011\"\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"crossfade", "Lcoil3/request/ImageRequest$Builder;", "enable", "", "Lcoil3/ImageLoader$Builder;", "DEFAULT_CROSSFADE_MILLIS", "", "maxBitmapSize", "size", "Lcoil3/size/Size;", "Lcoil3/request/ImageRequest;", "getMaxBitmapSize", "(Lcoil3/request/ImageRequest;)Lcoil3/size/Size;", "Lcoil3/request/Options;", "(Lcoil3/request/Options;)Lcoil3/size/Size;", "Lcoil3/Extras$Key;", "Lcoil3/Extras$Key$Companion;", "(Lcoil3/Extras$Key$Companion;)Lcoil3/Extras$Key;", "maxBitmapSizeKey", "addLastModifiedToFileCacheKey", "getAddLastModifiedToFileCacheKey", "(Lcoil3/request/ImageRequest;)Z", "(Lcoil3/request/Options;)Z", "addLastModifiedToFileCacheKeyKey", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageRequestsKt {
    public static final int DEFAULT_CROSSFADE_MILLIS = 200;
    private static final Extras.Key<Size> maxBitmapSizeKey = new Extras.Key<>(SizeKt.Size(4096, 4096));
    private static final Extras.Key<Boolean> addLastModifiedToFileCacheKeyKey = new Extras.Key<>(false);

    public static final ImageRequest.Builder crossfade(ImageRequest.Builder builder, boolean z) {
        return ImageRequests_androidKt.crossfade(builder, z ? 200 : 0);
    }

    public static final ImageLoader.Builder crossfade(ImageLoader.Builder builder, boolean z) {
        return ImageRequests_androidKt.crossfade(builder, z ? 200 : 0);
    }

    public static final ImageRequest.Builder maxBitmapSize(ImageRequest.Builder builder, Size size) {
        builder.getExtras().set(maxBitmapSizeKey, size);
        return builder;
    }

    public static final ImageLoader.Builder maxBitmapSize(ImageLoader.Builder builder, Size size) {
        builder.getExtras().set(maxBitmapSizeKey, size);
        return builder;
    }

    public static final Size getMaxBitmapSize(ImageRequest imageRequest) {
        return (Size) ExtrasKt.getExtra(imageRequest, maxBitmapSizeKey);
    }

    public static final Size getMaxBitmapSize(Options options) {
        return (Size) ExtrasKt.getExtra(options, maxBitmapSizeKey);
    }

    public static final Extras.Key<Size> getMaxBitmapSize(Extras.Key.Companion companion) {
        return maxBitmapSizeKey;
    }

    public static final ImageRequest.Builder addLastModifiedToFileCacheKey(ImageRequest.Builder builder, boolean z) {
        builder.getExtras().set(addLastModifiedToFileCacheKeyKey, Boolean.valueOf(z));
        return builder;
    }

    public static final ImageLoader.Builder addLastModifiedToFileCacheKey(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(addLastModifiedToFileCacheKeyKey, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getAddLastModifiedToFileCacheKey(ImageRequest imageRequest) {
        return ((Boolean) ExtrasKt.getExtra(imageRequest, addLastModifiedToFileCacheKeyKey)).booleanValue();
    }

    public static final boolean getAddLastModifiedToFileCacheKey(Options options) {
        return ((Boolean) ExtrasKt.getExtra(options, addLastModifiedToFileCacheKeyKey)).booleanValue();
    }

    public static final Extras.Key<Boolean> getAddLastModifiedToFileCacheKey(Extras.Key.Companion companion) {
        return addLastModifiedToFileCacheKeyKey;
    }
}
