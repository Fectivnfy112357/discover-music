package org.jaudiotagger.audio.wav.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.wav.WavChunkType;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavListChunk extends Chunk {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav.chunk.WavListChunk");
    private boolean isValid;
    private String loggingName;
    private WavTag tag;

    public WavListChunk(String str, ByteBuffer byteBuffer, ChunkHeader chunkHeader, WavTag wavTag) throws IOException {
        super(byteBuffer, chunkHeader);
        this.isValid = false;
        this.tag = wavTag;
        this.loggingName = str;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        String fourBytesAsChars = Utils.readFourBytesAsChars(this.chunkData);
        if (fourBytesAsChars.equals(WavChunkType.INFO.getCode())) {
            boolean chunks = new WavInfoChunk(this.tag, this.loggingName).readChunks(this.chunkData);
            this.tag.getInfoTag().setStartLocationInFile(this.chunkHeader.getStartLocationInFile());
            this.tag.getInfoTag().setEndLocationInFile(this.chunkHeader.getStartLocationInFile() + 8 + this.chunkHeader.getSize());
            this.tag.setExistingInfoTag(true);
            return chunks;
        }
        logger.severe("LIST chunk does not contain INFO instead contains " + fourBytesAsChars + " so skipping");
        return true;
    }

    public String toString() {
        return "RIFF-WAVE Header:\nIs valid?: " + this.isValid;
    }
}
