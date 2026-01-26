package coil3.decode;

import coil3.decode.ImageSource;
import kotlin.Metadata;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Path;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¨\u0006\u000f"}, d2 = {"ImageSource", "Lcoil3/decode/ImageSource;", "file", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "diskCacheKey", "", "closeable", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "metadata", "Lcoil3/decode/ImageSource$Metadata;", "source", "Lokio/BufferedSource;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageSourceKt {
    public static /* synthetic */ ImageSource ImageSource$default(Path path, FileSystem fileSystem, String str, AutoCloseable autoCloseable, ImageSource.Metadata metadata, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        if ((i & 8) != 0) {
            autoCloseable = null;
        }
        if ((i & 16) != 0) {
            metadata = null;
        }
        return ImageSource(path, fileSystem, str, autoCloseable, metadata);
    }

    public static final ImageSource ImageSource(Path path, FileSystem fileSystem, String str, AutoCloseable autoCloseable, ImageSource.Metadata metadata) {
        return new FileImageSource(path, fileSystem, str, autoCloseable, metadata);
    }

    public static /* synthetic */ ImageSource ImageSource$default(BufferedSource bufferedSource, FileSystem fileSystem, ImageSource.Metadata metadata, int i, Object obj) {
        if ((i & 4) != 0) {
            metadata = null;
        }
        return ImageSource(bufferedSource, fileSystem, metadata);
    }

    public static final ImageSource ImageSource(BufferedSource bufferedSource, FileSystem fileSystem, ImageSource.Metadata metadata) {
        return new SourceImageSource(bufferedSource, fileSystem, metadata);
    }
}
