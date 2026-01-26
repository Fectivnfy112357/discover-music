package org.jaudiotagger.audio.dff;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class Frm8Chunk {
    public static final int CHUNKSIZE_LENGTH = 8;
    public static final int FRM8_HEADER_LENGTH = 12;
    public static final int SIGNATURE_LENGTH = 4;

    public static Frm8Chunk readChunk(ByteBuffer byteBuffer) {
        if (DffChunkType.FRM8.getCode().equals(Utils.readFourBytesAsChars(byteBuffer))) {
            return new Frm8Chunk(byteBuffer);
        }
        return null;
    }

    private Frm8Chunk(ByteBuffer byteBuffer) {
    }

    public String toString() {
        return DffChunkType.FRM8.getCode();
    }
}
