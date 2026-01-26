package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.aiff.AiffAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class ApplicationChunk extends Chunk {
    private static final String SIGNATURE_PDOS = "pdos";
    private static final String SIGNATURE_STOC = "stoc";
    private AiffAudioHeader aiffHeader;

    public ApplicationChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) {
        super(byteBuffer, chunkHeader);
        this.aiffHeader = aiffAudioHeader;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        String fourBytesAsChars = Utils.readFourBytesAsChars(this.chunkData);
        this.aiffHeader.addApplicationIdentifier(fourBytesAsChars + ": " + ((SIGNATURE_STOC.equals(fourBytesAsChars) || SIGNATURE_PDOS.equals(fourBytesAsChars)) ? Utils.readPascalString(this.chunkData) : null));
        return true;
    }
}
