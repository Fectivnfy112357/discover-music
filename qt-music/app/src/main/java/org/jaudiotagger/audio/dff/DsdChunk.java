package org.jaudiotagger.audio.dff;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.dsf.DsfChunkType;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class DsdChunk {
    public static final int CHUNKSIZE_LENGTH = 8;
    public static final int DSD_HEADER_LENGTH = 8;
    public static final int SIGNATURE_LENGTH = 4;

    public static DsdChunk readChunk(ByteBuffer byteBuffer) {
        if (DsfChunkType.DSD.getCode().equals(Utils.readFourBytesAsChars(byteBuffer))) {
            return new DsdChunk(byteBuffer);
        }
        return null;
    }

    private DsdChunk(ByteBuffer byteBuffer) {
    }

    public String toString() {
        return DffChunkType.DSD.getCode();
    }
}
