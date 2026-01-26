package org.jaudiotagger.audio.iff;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public abstract class Chunk {
    protected ByteBuffer chunkData;
    protected ChunkHeader chunkHeader;

    public abstract boolean readChunk() throws IOException;

    public Chunk(ByteBuffer byteBuffer, ChunkHeader chunkHeader) {
        this.chunkData = byteBuffer;
        this.chunkHeader = chunkHeader;
    }
}
