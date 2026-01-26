package org.jaudiotagger.tag.vorbiscomment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class VorbisCommentReader {
    public static final int FIELD_COMMENT_LENGTH_LENGTH = 4;
    public static final int FIELD_USER_COMMENT_LIST_LENGTH = 4;
    public static final int FIELD_VENDOR_LENGTH_LENGTH = 4;
    public static final int FIELD_VENDOR_LENGTH_POS = 0;
    public static final int FIELD_VENDOR_STRING_POS = 4;
    private static final int JAUDIOTAGGER_MAX_COMMENT_LENGTH = 10000000;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.vorbiscomment.VorbisCommentReader");

    public VorbisCommentTag read(byte[] bArr, boolean z, Path path) throws CannotReadException, IOException {
        VorbisCommentTag vorbisCommentTag = new VorbisCommentTag();
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        int intLE = Utils.getIntLE(bArr2);
        byte[] bArr3 = new byte[intLE];
        System.arraycopy(bArr, 4, bArr3, 0, intLE);
        vorbisCommentTag.setVendor(new String(bArr3, "UTF-8"));
        logger.config("Vendor is:" + vorbisCommentTag.getVendor());
        byte[] bArr4 = new byte[4];
        System.arraycopy(bArr, intLE + 4, bArr4, 0, 4);
        int i = intLE + 8;
        int intLE2 = Utils.getIntLE(bArr4);
        logger.config("Number of user comments:" + intLE2);
        int i2 = 0;
        while (true) {
            if (i2 >= intLE2) {
                break;
            }
            byte[] bArr5 = new byte[4];
            System.arraycopy(bArr, i, bArr5, 0, 4);
            i += 4;
            int intLE3 = Utils.getIntLE(bArr5);
            logger.config("Next Comment Length:" + intLE3);
            if (intLE3 > JAUDIOTAGGER_MAX_COMMENT_LENGTH) {
                if (path != null) {
                    logger.warning(path.toString() + ":" + ErrorMessage.VORBIS_COMMENT_LENGTH_TOO_LARGE.getMsg(Integer.valueOf(intLE3)));
                } else {
                    logger.warning(ErrorMessage.VORBIS_COMMENT_LENGTH_TOO_LARGE.getMsg(Integer.valueOf(intLE3)));
                }
            } else if (intLE3 <= bArr.length - i) {
                byte[] bArr6 = new byte[intLE3];
                System.arraycopy(bArr, i, bArr6, 0, intLE3);
                i += intLE3;
                VorbisCommentTagField vorbisCommentTagField = new VorbisCommentTagField(bArr6);
                logger.config("Adding:" + vorbisCommentTagField.getId());
                vorbisCommentTag.addField(vorbisCommentTagField);
                i2++;
            } else if (path != null) {
                logger.warning(path.toString() + ":" + ErrorMessage.VORBIS_COMMENT_LENGTH_LARGE_THAN_HEADER.getMsg(Integer.valueOf(intLE3), Integer.valueOf(bArr.length - i)));
            } else {
                logger.warning(ErrorMessage.VORBIS_COMMENT_LENGTH_LARGE_THAN_HEADER.getMsg(Integer.valueOf(intLE3), Integer.valueOf(bArr.length)));
            }
        }
        if (!z || (bArr[i] & 1) == 1) {
            return vorbisCommentTag;
        }
        throw new CannotReadException(ErrorMessage.OGG_VORBIS_NO_FRAMING_BIT.getMsg(Integer.valueOf(bArr[i] & 1)));
    }
}
