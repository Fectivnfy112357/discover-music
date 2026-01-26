package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.aiff.AiffAudioHeader;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class AuthorChunk extends TextChunk {
    public AuthorChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) {
        super(chunkHeader, byteBuffer, aiffAudioHeader);
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        this.aiffAudioHeader.setAuthor(readChunkText());
        return true;
    }
}
