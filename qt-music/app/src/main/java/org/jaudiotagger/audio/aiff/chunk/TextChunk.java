package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.aiff.AiffAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public abstract class TextChunk extends Chunk {
    protected final AiffAudioHeader aiffAudioHeader;

    public TextChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) {
        super(byteBuffer, chunkHeader);
        this.aiffAudioHeader = aiffAudioHeader;
    }

    protected String readChunkText() throws IOException {
        return Utils.getString(this.chunkData, 0, this.chunkData.remaining(), StandardCharsets.ISO_8859_1);
    }
}
