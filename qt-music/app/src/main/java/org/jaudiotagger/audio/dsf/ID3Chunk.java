package org.jaudiotagger.audio.dsf;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class ID3Chunk {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.generic.ID3Chunk");
    private ByteBuffer dataBuffer;

    public static ID3Chunk readChunk(ByteBuffer byteBuffer) {
        String threeBytesAsChars = Utils.readThreeBytesAsChars(byteBuffer);
        if (DsfChunkType.ID3.getCode().equals(threeBytesAsChars)) {
            return new ID3Chunk(byteBuffer);
        }
        logger.log(Level.WARNING, "Invalid type:" + threeBytesAsChars + " where expected ID3 tag");
        return null;
    }

    public ID3Chunk(ByteBuffer byteBuffer) {
        this.dataBuffer = byteBuffer;
    }

    public ByteBuffer getDataBuffer() {
        return this.dataBuffer;
    }
}
