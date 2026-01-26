package org.jaudiotagger.audio.wav.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavId3Chunk extends Chunk {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav.chunk");
    private String loggingName;
    private WavTag wavTag;

    public WavId3Chunk(ByteBuffer byteBuffer, ChunkHeader chunkHeader, WavTag wavTag, String str) {
        super(byteBuffer, chunkHeader);
        this.wavTag = wavTag;
        this.loggingName = str;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        AbstractID3v2Tag iD3v22Tag;
        if (!isId3v2Tag(this.chunkData)) {
            logger.severe("Invalid ID3 header for ID3 chunk");
            return false;
        }
        byte b = this.chunkData.get();
        if (b == 2) {
            iD3v22Tag = new ID3v22Tag();
            iD3v22Tag.setLoggingFilename(this.loggingName);
        } else if (b == 3) {
            iD3v22Tag = new ID3v23Tag();
            iD3v22Tag.setLoggingFilename(this.loggingName);
        } else {
            if (b != 4) {
                return false;
            }
            iD3v22Tag = new ID3v24Tag();
            iD3v22Tag.setLoggingFilename(this.loggingName);
        }
        iD3v22Tag.setStartLocationInFile(this.chunkHeader.getStartLocationInFile() + 8);
        iD3v22Tag.setEndLocationInFile(this.chunkHeader.getStartLocationInFile() + 8 + this.chunkHeader.getSize());
        this.wavTag.setExistingId3Tag(true);
        this.wavTag.setID3Tag(iD3v22Tag);
        this.chunkData.position(0);
        try {
            iD3v22Tag.read(this.chunkData);
            return true;
        } catch (TagException e) {
            AudioFile.logger.info("Exception reading ID3 tag: " + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    private boolean isId3v2Tag(ByteBuffer byteBuffer) throws IOException {
        for (int i = 0; i < 3; i++) {
            if (byteBuffer.get() != AbstractID3v2Tag.TAG_ID[i]) {
                return false;
            }
        }
        return true;
    }
}
