package coil3.decode;

import coil3.decode.ImageSource;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcoil3/decode/ByteBufferMetadata;", "Lcoil3/decode/ImageSource$Metadata;", "byteBuffer", "Ljava/nio/ByteBuffer;", "<init>", "(Ljava/nio/ByteBuffer;)V", "getByteBuffer", "()Ljava/nio/ByteBuffer;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ByteBufferMetadata extends ImageSource.Metadata {
    private final ByteBuffer byteBuffer;

    public final ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    public ByteBufferMetadata(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }
}
