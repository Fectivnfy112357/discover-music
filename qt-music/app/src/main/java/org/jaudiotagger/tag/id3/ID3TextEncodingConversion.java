package org.jaudiotagger.tag.id3;

import java.util.logging.Logger;
import org.jaudiotagger.tag.TagOptionSingleton;

/* loaded from: classes3.dex */
public class ID3TextEncodingConversion {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");

    private static byte convertV24textEncodingToV23textEncoding(byte b) {
        if (b == 2) {
            return (byte) 1;
        }
        if (b == 3) {
            return (byte) 0;
        }
        return b;
    }

    public static byte getTextEncoding(AbstractTagFrame abstractTagFrame, byte b) {
        if (abstractTagFrame == null) {
            logger.warning("Header has not yet been set for this framebody");
            if (TagOptionSingleton.getInstance().isResetTextEncodingForExistingFrames()) {
                return TagOptionSingleton.getInstance().getId3v23DefaultTextEncoding();
            }
            return convertV24textEncodingToV23textEncoding(b);
        }
        if (abstractTagFrame instanceof ID3v24Frame) {
            return TagOptionSingleton.getInstance().isResetTextEncodingForExistingFrames() ? TagOptionSingleton.getInstance().getId3v24DefaultTextEncoding() : b;
        }
        if (TagOptionSingleton.getInstance().isResetTextEncodingForExistingFrames()) {
            return TagOptionSingleton.getInstance().getId3v23DefaultTextEncoding();
        }
        return convertV24textEncodingToV23textEncoding(b);
    }

    public static byte getUnicodeTextEncoding(AbstractTagFrame abstractTagFrame) {
        if (abstractTagFrame == null) {
            logger.warning("Header has not yet been set for this framebody");
            return (byte) 1;
        }
        if (abstractTagFrame instanceof ID3v24Frame) {
            return TagOptionSingleton.getInstance().getId3v24UnicodeTextEncoding();
        }
        return (byte) 1;
    }
}
