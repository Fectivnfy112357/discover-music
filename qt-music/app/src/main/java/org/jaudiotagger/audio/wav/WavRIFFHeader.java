package org.jaudiotagger.audio.wav;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.IffHeaderChunk;

/* loaded from: classes3.dex */
public class WavRIFFHeader {
    public static final String RIFF_SIGNATURE = "RIFF";
    public static final String WAVE_SIGNATURE = "WAVE";

    public static boolean isValidHeader(String str, FileChannel fileChannel) throws CannotReadException, IOException {
        if (fileChannel.size() - fileChannel.position() < IffHeaderChunk.FORM_HEADER_LENGTH) {
            throw new CannotReadException(str + ":This is not a WAV File (<12 bytes)");
        }
        ByteBuffer fileDataIntoBufferLE = Utils.readFileDataIntoBufferLE(fileChannel, IffHeaderChunk.FORM_HEADER_LENGTH);
        if (!Utils.readFourBytesAsChars(fileDataIntoBufferLE).equals(RIFF_SIGNATURE)) {
            return false;
        }
        IffHeaderChunk.logger.finer(str + ":Header:File:Size:" + fileDataIntoBufferLE.getInt());
        return Utils.readFourBytesAsChars(fileDataIntoBufferLE).equals(WAVE_SIGNATURE);
    }
}
