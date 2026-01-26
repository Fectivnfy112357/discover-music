package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.exceptions.InvalidChunkException;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class BaseChunk {
    public static final int ID_LENGHT = 4;
    private Long chunkSize;
    private Long chunkStart;

    public static BaseChunk readIdChunk(ByteBuffer byteBuffer) throws InvalidChunkException {
        String fourBytesAsChars = Utils.readFourBytesAsChars(byteBuffer);
        if (DffChunkType.FS.getCode().equals(fourBytesAsChars)) {
            return new FsChunk(byteBuffer);
        }
        if (DffChunkType.CHNL.getCode().equals(fourBytesAsChars)) {
            return new ChnlChunk(byteBuffer);
        }
        if (DffChunkType.CMPR.getCode().equals(fourBytesAsChars)) {
            return new CmprChunk(byteBuffer);
        }
        if (DffChunkType.END.getCode().equals(fourBytesAsChars) || DffChunkType.DSD.getCode().equals(fourBytesAsChars)) {
            return new EndChunk(byteBuffer);
        }
        if (DffChunkType.DST.getCode().equals(fourBytesAsChars)) {
            return new DstChunk(byteBuffer);
        }
        if (DffChunkType.FRTE.getCode().equals(fourBytesAsChars)) {
            return new FrteChunk(byteBuffer);
        }
        if (DffChunkType.ID3.getCode().equals(fourBytesAsChars)) {
            return new Id3Chunk(byteBuffer);
        }
        throw new InvalidChunkException(fourBytesAsChars + " is not recognized as a valid DFF chunk");
    }

    protected BaseChunk(ByteBuffer byteBuffer) {
    }

    protected void readDataChunch(FileChannel fileChannel) throws IOException {
        this.chunkSize = Long.valueOf(Long.reverseBytes(Utils.readFileDataIntoBufferLE(fileChannel, 8).getLong()));
        this.chunkStart = Long.valueOf(fileChannel.position());
    }

    protected void skipToChunkEnd(FileChannel fileChannel) throws IOException {
        Long lValueOf = Long.valueOf(getChunkEnd().longValue() - fileChannel.position());
        if (lValueOf.longValue() > 0) {
            Utils.readFileDataIntoBufferLE(fileChannel, lValueOf.intValue());
        }
    }

    public Long getChunkStart() {
        return this.chunkStart;
    }

    public Long getChunkSize() {
        return this.chunkSize;
    }

    public Long getChunkEnd() {
        return Long.valueOf(this.chunkStart.longValue() + this.chunkSize.longValue());
    }
}
