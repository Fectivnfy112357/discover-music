package org.jaudiotagger.audio.dff;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class SndChunk {
    public static final int CHUNKSIZE_LENGTH = 0;
    public static final int SIGNATURE_LENGTH = 4;
    public static final int SND_HEADER_LENGTH = 4;

    public static SndChunk readChunk(ByteBuffer byteBuffer) {
        if (DffChunkType.SND.getCode().equals(Utils.readFourBytesAsChars(byteBuffer))) {
            return new SndChunk(byteBuffer);
        }
        return null;
    }

    private SndChunk(ByteBuffer byteBuffer) {
    }

    public String toString() {
        return DffChunkType.SND.getCode();
    }
}
