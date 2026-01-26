package org.jaudiotagger.audio.wav.chunk;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.wav.WavInfoTag;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavInfoChunk {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav.WavInfoChunk");
    private String loggingName;
    private WavInfoTag wavInfoTag;

    public WavInfoChunk(WavTag wavTag, String str) {
        this.loggingName = str;
        WavInfoTag wavInfoTag = new WavInfoTag();
        this.wavInfoTag = wavInfoTag;
        wavTag.setInfoTag(wavInfoTag);
    }

    public boolean readChunks(ByteBuffer byteBuffer) {
        EnumSet<FieldKey> overrideCharsetFields = TagOptionSingleton.getInstance().getOverrideCharsetFields();
        while (byteBuffer.remaining() >= IffHeaderChunk.TYPE_LENGTH) {
            String fourBytesAsChars = Utils.readFourBytesAsChars(byteBuffer);
            if (fourBytesAsChars.trim().isEmpty()) {
                return true;
            }
            int i = byteBuffer.getInt();
            if (!isAlphabetic(fourBytesAsChars.charAt(0)) || !isAlphabetic(fourBytesAsChars.charAt(1)) || !isAlphabetic(fourBytesAsChars.charAt(2)) || !isAlphabetic(fourBytesAsChars.charAt(3))) {
                logger.severe(this.loggingName + "LISTINFO appears corrupt, ignoring:" + fourBytesAsChars + ":" + i);
                return false;
            }
            Charset overrideCharset = StandardCharsets.UTF_8;
            if (TagOptionSingleton.getInstance().isOverrideCharsetForInfo() && TagOptionSingleton.getInstance().getOverrideCharset() != null && overrideCharsetFields.contains(WavInfoIdentifier.getByCode(fourBytesAsChars).getFieldKey())) {
                overrideCharset = TagOptionSingleton.getInstance().getOverrideCharset();
                logger.severe(this.loggingName + "Charset used is:" + overrideCharset.displayName());
            }
            try {
                String string = Utils.getString(byteBuffer, 0, i, overrideCharset);
                logger.config(this.loggingName + "Result:" + fourBytesAsChars + ":" + i + ":" + string + ":");
                WavInfoIdentifier byCode = WavInfoIdentifier.getByCode(fourBytesAsChars);
                if (byCode != null && byCode.getFieldKey() != null) {
                    try {
                        this.wavInfoTag.setField(byCode.getFieldKey(), string);
                    } catch (FieldDataInvalidException e) {
                        logger.log(Level.SEVERE, this.loggingName + e.getMessage(), (Throwable) e);
                    }
                } else if (fourBytesAsChars != null && !fourBytesAsChars.trim().isEmpty()) {
                    this.wavInfoTag.addUnRecognizedField(fourBytesAsChars, string);
                }
                if (Utils.isOddLength(i) && byteBuffer.hasRemaining()) {
                    byteBuffer.get();
                }
            } catch (BufferUnderflowException e2) {
                logger.log(Level.SEVERE, this.loggingName + "LISTINFO appears corrupt, buffer underflow, ignoring:" + e2.getMessage(), (Throwable) e2);
                return false;
            }
        }
        return true;
    }

    private static boolean isAlphabetic(int i) {
        return ((1086 >> Character.getType(i)) & 1) != 0;
    }
}
