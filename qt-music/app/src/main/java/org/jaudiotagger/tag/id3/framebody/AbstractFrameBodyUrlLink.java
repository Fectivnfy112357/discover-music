package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public abstract class AbstractFrameBodyUrlLink extends AbstractID3v2FrameBody {
    protected AbstractFrameBodyUrlLink() {
    }

    protected AbstractFrameBodyUrlLink(AbstractFrameBodyUrlLink abstractFrameBodyUrlLink) {
        super(abstractFrameBodyUrlLink);
    }

    public AbstractFrameBodyUrlLink(String str) {
        setObjectValue(DataTypes.OBJ_URLLINK, str);
    }

    protected AbstractFrameBodyUrlLink(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getUserFriendlyValue() {
        return getUrlLink();
    }

    public void setUrlLink(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        setObjectValue(DataTypes.OBJ_URLLINK, str);
    }

    public String getUrlLink() {
        return (String) getObjectValue(DataTypes.OBJ_URLLINK);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        CharsetEncoder charsetEncoderNewEncoder = StandardCharsets.ISO_8859_1.newEncoder();
        String urlLink = getUrlLink();
        if (!charsetEncoderNewEncoder.canEncode(urlLink)) {
            setUrlLink(encodeURL(urlLink));
            if (!charsetEncoderNewEncoder.canEncode(getUrlLink())) {
                logger.warning(ErrorMessage.MP3_UNABLE_TO_ENCODE_URL.getMsg(urlLink));
                setUrlLink("");
            } else {
                logger.warning(ErrorMessage.MP3_URL_SAVED_ENCODED.getMsg(urlLink, getUrlLink()));
            }
        }
        super.write(byteArrayOutputStream);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated(DataTypes.OBJ_URLLINK, this));
    }

    private String encodeURL(String str) {
        try {
            String[] strArrSplit = str.split("(?<!/)/(?!/)", -1);
            StringBuffer stringBuffer = new StringBuffer(strArrSplit[0]);
            for (int i = 1; i < strArrSplit.length; i++) {
                stringBuffer.append("/").append(URLEncoder.encode(strArrSplit[i], "utf-8"));
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            logger.warning("Uable to url encode because utf-8 charset not available:" + e.getMessage());
            return str;
        }
    }
}
