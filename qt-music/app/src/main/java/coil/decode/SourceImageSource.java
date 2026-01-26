package coil.decode;

import coil.decode.ImageSource;
import coil.util.Utils;
import java.io.File;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcoil/decode/SourceImageSource;", "Lcoil/decode/ImageSource;", "source", "Lokio/BufferedSource;", "cacheDirectory", "Ljava/io/File;", "metadata", "Lcoil/decode/ImageSource$Metadata;", "(Lokio/BufferedSource;Ljava/io/File;Lcoil/decode/ImageSource$Metadata;)V", "file", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "getFileSystem", "()Lokio/FileSystem;", "isClosed", "", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "assertNotClosed", "", "close", "fileOrNull", "sourceOrNull", "coil-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SourceImageSource extends ImageSource {
    private final File cacheDirectory;
    private Path file;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private BufferedSource source;

    @Override // coil.decode.ImageSource
    public ImageSource.Metadata getMetadata() {
        return this.metadata;
    }

    public SourceImageSource(BufferedSource bufferedSource, File file, ImageSource.Metadata metadata) {
        super(null);
        this.cacheDirectory = file;
        this.metadata = metadata;
        this.source = bufferedSource;
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("cacheDirectory must be a directory.".toString());
        }
    }

    @Override // coil.decode.ImageSource
    public FileSystem getFileSystem() {
        return FileSystem.SYSTEM;
    }

    @Override // coil.decode.ImageSource
    public synchronized BufferedSource source() {
        assertNotClosed();
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            return bufferedSource;
        }
        FileSystem fileSystem = getFileSystem();
        Path path = this.file;
        Intrinsics.checkNotNull(path);
        BufferedSource bufferedSourceBuffer = Okio.buffer(fileSystem.source(path));
        this.source = bufferedSourceBuffer;
        return bufferedSourceBuffer;
    }

    @Override // coil.decode.ImageSource
    public BufferedSource sourceOrNull() {
        return source();
    }

    @Override // coil.decode.ImageSource
    public synchronized Path file() {
        Long lValueOf;
        assertNotClosed();
        Path path = this.file;
        if (path != null) {
            return path;
        }
        Path path2 = Path.Companion.get$default(Path.INSTANCE, File.createTempFile("tmp", null, this.cacheDirectory), false, 1, (Object) null);
        BufferedSink bufferedSinkBuffer = Okio.buffer(getFileSystem().sink(path2, false));
        try {
            BufferedSource bufferedSource = this.source;
            Intrinsics.checkNotNull(bufferedSource);
            lValueOf = Long.valueOf(bufferedSinkBuffer.writeAll(bufferedSource));
            th = null;
        } catch (Throwable th) {
            th = th;
            lValueOf = null;
        }
        if (bufferedSinkBuffer != null) {
            try {
                bufferedSinkBuffer.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    ExceptionsKt.addSuppressed(th, th2);
                }
            }
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(lValueOf);
        this.source = null;
        this.file = path2;
        return path2;
    }

    @Override // coil.decode.ImageSource
    public synchronized Path fileOrNull() {
        assertNotClosed();
        return this.file;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.isClosed = true;
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            Utils.closeQuietly(bufferedSource);
        }
        Path path = this.file;
        if (path != null) {
            getFileSystem().delete(path);
        }
    }

    private final void assertNotClosed() {
        if (this.isClosed) {
            throw new IllegalStateException("closed".toString());
        }
    }
}
