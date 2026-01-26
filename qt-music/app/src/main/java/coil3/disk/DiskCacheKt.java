package coil3.disk;

import coil3.disk.DiskCache;
import java.io.File;
import kotlin.Metadata;
import okio.Path;

/* compiled from: diskCache.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u0002¨\u0006\u0003"}, d2 = {"directory", "Lcoil3/disk/DiskCache$Builder;", "Ljava/io/File;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DiskCacheKt {
    public static final DiskCache.Builder directory(DiskCache.Builder builder, File file) {
        return builder.directory(Path.Companion.get$default(Path.INSTANCE, file, false, 1, (Object) null));
    }
}
