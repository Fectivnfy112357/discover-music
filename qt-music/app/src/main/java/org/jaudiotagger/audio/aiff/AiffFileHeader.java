package org.jaudiotagger.audio.aiff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class AiffFileHeader {
    private static final String FORM = "FORM";
    private static Logger logger = Logger.getLogger("org.jaudiotagger.audio.aiff.AudioFileHeader");
    private String loggingName;

    public AiffFileHeader(String str) {
        this.loggingName = str;
    }

    public long readHeader(FileChannel fileChannel, AiffAudioHeader aiffAudioHeader) throws CannotReadException, IOException {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(IffHeaderChunk.FORM_HEADER_LENGTH);
        byteBufferAllocateDirect.order(ByteOrder.BIG_ENDIAN);
        int i = fileChannel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.position(0);
        if (i < IffHeaderChunk.FORM_HEADER_LENGTH) {
            throw new IOException(this.loggingName + ":AIFF:Unable to read required number of databytes read:" + i + ":required:" + IffHeaderChunk.FORM_HEADER_LENGTH);
        }
        String fourBytesAsChars = Utils.readFourBytesAsChars(byteBufferAllocateDirect);
        if (FORM.equals(fourBytesAsChars)) {
            long j = byteBufferAllocateDirect.getInt();
            logger.config(this.loggingName + ":Reading AIFF header size:" + Hex.asDecAndHex(j) + ":File Size Should End At:" + Hex.asDecAndHex(8 + j));
            readFileType(byteBufferAllocateDirect, aiffAudioHeader);
            return j;
        }
        throw new CannotReadException(this.loggingName + ":Not an AIFF file: incorrect signature " + fourBytesAsChars);
    }

    private void readFileType(ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) throws CannotReadException, IOException {
        String fourBytesAsChars = Utils.readFourBytesAsChars(byteBuffer);
        if (AiffType.AIFF.getCode().equals(fourBytesAsChars)) {
            aiffAudioHeader.setFileType(AiffType.AIFF);
        } else {
            if (AiffType.AIFC.getCode().equals(fourBytesAsChars)) {
                aiffAudioHeader.setFileType(AiffType.AIFC);
                return;
            }
            throw new CannotReadException(this.loggingName + ":Invalid AIFF file: Incorrect file type info " + fourBytesAsChars);
        }
    }
}
