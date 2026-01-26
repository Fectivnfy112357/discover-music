package org.jaudiotagger.audio.dff;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class PropChunk {
    public static final int CHUNKSIZE_LENGTH = 8;
    public static final int PROP_HEADER_LENGTH = 12;
    public static final int SIGNATURE_LENGTH = 4;

    public static PropChunk readChunk(ByteBuffer byteBuffer) {
        if (DffChunkType.PROP.getCode().equals(Utils.readFourBytesAsChars(byteBuffer))) {
            return new PropChunk(byteBuffer);
        }
        return null;
    }

    private PropChunk(ByteBuffer byteBuffer) {
    }

    public String toString() {
        return DffChunkType.PROP.getCode();
    }
}
