package coil3.memory;

import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import kotlin.Metadata;

/* compiled from: MemoryCacheService.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"needsSizeInCacheKey", "", "Lcoil3/request/ImageRequest;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MemoryCacheServiceKt {
    public static final boolean needsSizeInCacheKey(ImageRequest imageRequest) {
        return !ImageRequests_androidKt.getTransformations(imageRequest).isEmpty();
    }
}
