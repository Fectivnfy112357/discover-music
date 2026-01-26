package org.jaudiotagger.audio.dsf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.IffHeaderChunk;

/* loaded from: classes3.dex */
public class FmtChunk {
    public static final int FMT_CHUNK_MIN_DATA_SIZE_ = 40;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.dsf.FmtChunk");
    private long chunkSizeLength;

    public static FmtChunk readChunkHeader(ByteBuffer byteBuffer) {
        if (DsfChunkType.FORMAT.getCode().equals(Utils.readFourBytesAsChars(byteBuffer))) {
            return new FmtChunk(byteBuffer);
        }
        return null;
    }

    private FmtChunk(ByteBuffer byteBuffer) {
        this.chunkSizeLength = byteBuffer.getLong();
    }

    public GenericAudioHeader readChunkData(DsdChunk dsdChunk, FileChannel fileChannel) throws IOException {
        return readAudioInfo(dsdChunk, Utils.readFileDataIntoBufferLE(fileChannel, (int) (this.chunkSizeLength - (IffHeaderChunk.SIGNATURE_LENGTH + 8))));
    }

    private GenericAudioHeader readAudioInfo(DsdChunk dsdChunk, ByteBuffer byteBuffer) {
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        if (byteBuffer.limit() < 40) {
            logger.log(Level.WARNING, "Not enough bytes supplied for Generic audio header. Returning an empty one.");
            return genericAudioHeader;
        }
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.getInt();
        byteBuffer.getInt();
        byteBuffer.getInt();
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        int i3 = byteBuffer.getInt();
        long j = byteBuffer.getLong();
        byteBuffer.getInt();
        genericAudioHeader.setEncodingType("DSF");
        genericAudioHeader.setFormat(SupportedFileFormat.DSF.getDisplayName());
        genericAudioHeader.setBitRate(i3 * i2 * i);
        genericAudioHeader.setBitsPerSample(i3);
        genericAudioHeader.setChannelNumber(i);
        genericAudioHeader.setSamplingRate(i2);
        genericAudioHeader.setNoOfSamples(Long.valueOf(j));
        genericAudioHeader.setPreciseLength(j / i2);
        genericAudioHeader.setVariableBitRate(false);
        return genericAudioHeader;
    }
}
