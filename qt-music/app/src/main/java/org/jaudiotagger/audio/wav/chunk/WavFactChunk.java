package org.jaudiotagger.audio.wav.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class WavFactChunk extends Chunk {
    private GenericAudioHeader info;
    private boolean isValid;

    public WavFactChunk(ByteBuffer byteBuffer, ChunkHeader chunkHeader, GenericAudioHeader genericAudioHeader) throws IOException {
        super(byteBuffer, chunkHeader);
        this.isValid = false;
        this.info = genericAudioHeader;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        this.info.setNoOfSamples(Long.valueOf(Utils.u(this.chunkData.getInt())));
        return true;
    }

    public String toString() {
        return "Fact Chunk:\nIs valid?: " + this.isValid;
    }
}
