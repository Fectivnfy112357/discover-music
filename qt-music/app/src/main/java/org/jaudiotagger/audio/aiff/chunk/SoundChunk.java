package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class SoundChunk extends Chunk {
    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        return true;
    }

    public SoundChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer) {
        super(byteBuffer, chunkHeader);
    }
}
