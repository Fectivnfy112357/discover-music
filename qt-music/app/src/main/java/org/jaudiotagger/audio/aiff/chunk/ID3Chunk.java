package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.aiff.AiffTag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;

/* loaded from: classes3.dex */
public class ID3Chunk extends Chunk {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.aiff.chunk");
    private AiffTag aiffTag;
    private String loggingName;

    public ID3Chunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffTag aiffTag, String str) {
        super(byteBuffer, chunkHeader);
        this.aiffTag = aiffTag;
        this.loggingName = str;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        AbstractID3v2Tag iD3v22Tag;
        AudioFile.logger.config(this.loggingName + ":Reading chunk");
        if (!isId3v2Tag(this.chunkData)) {
            logger.severe(this.loggingName + ":Invalid ID3 header for ID3 chunk");
            return false;
        }
        byte b = this.chunkData.get();
        if (b == 2) {
            iD3v22Tag = new ID3v22Tag();
            AudioFile.logger.config(this.loggingName + ":Reading ID3V2.2 tag");
        } else if (b == 3) {
            iD3v22Tag = new ID3v23Tag();
            AudioFile.logger.config(this.loggingName + ":Reading ID3V2.3 tag");
        } else {
            if (b != 4) {
                return false;
            }
            iD3v22Tag = new ID3v24Tag();
            AudioFile.logger.config(this.loggingName + ":Reading ID3V2.4 tag");
        }
        this.aiffTag.setID3Tag(iD3v22Tag);
        this.chunkData.position(0);
        try {
            iD3v22Tag.read(this.chunkData);
            return true;
        } catch (TagException e) {
            AudioFile.logger.severe(this.loggingName + ":Exception reading ID3 tag: " + e.getClass().getName() + ": " + e.getMessage());
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
