package coil3.svg.internal;

import java.io.IOException;
import kotlin.Metadata;
import okio.BufferedSource;
import okio.ByteString;

/* compiled from: utils.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"indexOf", "", "Lokio/BufferedSource;", "bytes", "Lokio/ByteString;", "fromIndex", "toIndex", "MIME_TYPE_SVG", "", "SVG_DEFAULT_SIZE", "", "SVG_SIZE_BYTES", "coil-svg_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UtilsKt {
    public static final String MIME_TYPE_SVG = "image/svg+xml";
    public static final int SVG_DEFAULT_SIZE = 512;
    public static final long SVG_SIZE_BYTES = 2048;

    public static final long indexOf(BufferedSource bufferedSource, ByteString byteString, long j, long j2) throws IOException {
        if (byteString.size() <= 0) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        byte b = byteString.getByte(0);
        long size = j2 - byteString.size();
        long j3 = j;
        while (j3 < size) {
            long jIndexOf = bufferedSource.indexOf(b, j3, size);
            if (jIndexOf == -1 || bufferedSource.rangeEquals(jIndexOf, byteString)) {
                return jIndexOf;
            }
            j3 = jIndexOf + 1;
        }
        return -1L;
    }
}
