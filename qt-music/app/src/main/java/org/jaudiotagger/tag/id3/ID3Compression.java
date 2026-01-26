package org.jaudiotagger.tag.id3;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.InvalidFrameException;

/* loaded from: classes3.dex */
public class ID3Compression {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");

    protected static ByteBuffer uncompress(String str, String str2, ByteBuffer byteBuffer, int i, int i2) throws InvalidFrameException, DataFormatException {
        logger.config(str2 + ":About to decompress " + i2 + " bytes, expect result to be:" + i + " bytes");
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[i2];
        int iPosition = byteBuffer.position();
        byteBuffer.get(bArr2, 0, i2);
        byteBuffer.position(iPosition);
        Inflater inflater = new Inflater();
        inflater.setInput(bArr2);
        try {
            logger.config(str2 + ":Decompressed to " + inflater.inflate(bArr) + " bytes");
            inflater.end();
            return ByteBuffer.wrap(bArr);
        } catch (DataFormatException e) {
            logger.log(Level.CONFIG, "Unable to decompress this frame:" + str, (Throwable) e);
            byteBuffer.position(byteBuffer.position() + i2);
            throw new InvalidFrameException(ErrorMessage.ID3_UNABLE_TO_DECOMPRESS_FRAME.getMsg(str, str2, e.getMessage()));
        }
    }
}
