package org.jaudiotagger.tag.id3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public class ID3Unsynchronization {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");

    public static boolean requiresUnsynchronization(byte[] bArr) {
        for (int i = 0; i < bArr.length - 1; i++) {
            if ((bArr[i] & 255) == 255 && (bArr[i + 1] & 224) == 224) {
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("Unsynchronisation required found bit at:" + i);
                }
                return true;
            }
        }
        return false;
    }

    public static byte[] unsynchronize(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        int i = 0;
        while (byteArrayInputStream.available() > 0) {
            int i2 = byteArrayInputStream.read();
            i++;
            byteArrayOutputStream.write(i2);
            if ((i2 & 255) == 255 && byteArrayInputStream.available() > 0) {
                byteArrayInputStream.mark(1);
                int i3 = byteArrayInputStream.read();
                if ((i3 & 224) == 224) {
                    if (logger.isLoggable(Level.FINEST)) {
                        logger.finest("Writing unsynchronisation bit at:" + i);
                    }
                    byteArrayOutputStream.write(0);
                } else if (i3 == 0) {
                    if (logger.isLoggable(Level.FINEST)) {
                        logger.finest("Inserting zero unsynchronisation bit at:" + i);
                    }
                    byteArrayOutputStream.write(0);
                }
                byteArrayInputStream.reset();
            }
        }
        if ((bArr[bArr.length - 1] & 255) == 255) {
            logger.finest("Adding unsynchronisation bit at end of stream");
            byteArrayOutputStream.write(0);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static ByteBuffer synchronize(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining + 1];
        byteBuffer.get(bArr, 0, iRemaining);
        boolean z = true;
        int i = 0;
        int i2 = 0;
        while (i < iRemaining) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (z || b != 0) {
                bArr[i2] = b;
                i2++;
            }
            z = (b & 255) != 255;
            i = i3;
        }
        return ByteBuffer.wrap(bArr, 0, i2);
    }
}
