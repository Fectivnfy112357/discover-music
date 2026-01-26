package org.jaudiotagger.tag.datatype;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3v23FieldKey;
import org.jaudiotagger.tag.id3.ID3v23Frames;
import org.jaudiotagger.tag.id3.ID3v24FieldKey;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.jaudiotagger.tag.id3.framebody.FrameBodyWXXX;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;

/* loaded from: classes3.dex */
public abstract class AbstractString extends AbstractDataType {
    protected boolean isAllowReadMetadataWithOverrideCharset;

    protected AbstractString(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.isAllowReadMetadataWithOverrideCharset = false;
    }

    public AbstractString(String str, AbstractTagFrameBody abstractTagFrameBody, String str2) {
        super(str, abstractTagFrameBody, str2);
        this.isAllowReadMetadataWithOverrideCharset = false;
    }

    protected AbstractString(AbstractString abstractString) {
        super(abstractString);
        this.isAllowReadMetadataWithOverrideCharset = false;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return this.size;
    }

    protected void setSize(int i) {
        this.size = i;
    }

    public String toString() {
        return (String) this.value;
    }

    public boolean canBeEncoded() {
        CharsetEncoder charsetEncoderNewEncoder = TextEncoding.getInstanceOf().getCharsetForId(getBody().getTextEncoding()).newEncoder();
        if (charsetEncoderNewEncoder.canEncode((String) this.value)) {
            return true;
        }
        logger.finest("Failed Trying to decode" + this.value + "with" + charsetEncoderNewEncoder.toString());
        return false;
    }

    protected CharsetDecoder getCorrectDecoder(ByteBuffer byteBuffer) {
        ID3v23FieldKey fieldKeyFromFrameId;
        ID3v24FieldKey fieldKeyFromFrameId2;
        FieldKey genericKeyFromId3;
        EnumSet<FieldKey> overrideCharsetFields = TagOptionSingleton.getInstance().getOverrideCharsetFields();
        Charset textEncodingCharSet = getTextEncodingCharSet();
        if (textEncodingCharSet == StandardCharsets.ISO_8859_1 && this.isAllowReadMetadataWithOverrideCharset && TagOptionSingleton.getInstance().isOverrideCharsetForId3() && TagOptionSingleton.getInstance().getOverrideCharset() != null) {
            if (this.frameBody instanceof FrameBodyTXXX) {
                fieldKeyFromFrameId = ID3v23FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyTXXX) this.frameBody).getDescription());
            } else if (this.frameBody instanceof FrameBodyWXXX) {
                fieldKeyFromFrameId = ID3v23FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyWXXX) this.frameBody).getDescription());
            } else if (this.frameBody instanceof FrameBodyCOMM) {
                fieldKeyFromFrameId = ID3v23FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyCOMM) this.frameBody).getDescription());
            } else {
                fieldKeyFromFrameId = ID3v23FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier());
            }
            if (fieldKeyFromFrameId != null) {
                FieldKey genericKeyFromId32 = ID3v23Frames.getInstanceOf().getGenericKeyFromId3(fieldKeyFromFrameId);
                if (genericKeyFromId32 != null && overrideCharsetFields.contains(genericKeyFromId32)) {
                    textEncodingCharSet = TagOptionSingleton.getInstance().getOverrideCharset();
                }
            } else {
                if (this.frameBody instanceof FrameBodyTXXX) {
                    fieldKeyFromFrameId2 = ID3v24FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyTXXX) this.frameBody).getDescription());
                } else if (this.frameBody instanceof FrameBodyWXXX) {
                    fieldKeyFromFrameId2 = ID3v24FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyWXXX) this.frameBody).getDescription());
                } else if (this.frameBody instanceof FrameBodyCOMM) {
                    fieldKeyFromFrameId2 = ID3v24FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier() + ((FrameBodyCOMM) this.frameBody).getDescription());
                } else {
                    fieldKeyFromFrameId2 = ID3v24FieldKey.getFieldKeyFromFrameId(this.frameBody.getIdentifier());
                }
                if (fieldKeyFromFrameId2 != null && (genericKeyFromId3 = ID3v24Frames.getInstanceOf().getGenericKeyFromId3(fieldKeyFromFrameId2)) != null && overrideCharsetFields.contains(genericKeyFromId3)) {
                    textEncodingCharSet = TagOptionSingleton.getInstance().getOverrideCharset();
                }
            }
        }
        if (byteBuffer.remaining() <= 2) {
            CharsetDecoder charsetDecoderNewDecoder = textEncodingCharSet.newDecoder();
            charsetDecoderNewDecoder.reset();
            return charsetDecoderNewDecoder;
        }
        if (textEncodingCharSet == StandardCharsets.UTF_16) {
            if (byteBuffer.getChar(0) == 65534 || byteBuffer.getChar(0) == 65279) {
                CharsetDecoder charsetDecoderNewDecoder2 = textEncodingCharSet.newDecoder();
                charsetDecoderNewDecoder2.reset();
                return charsetDecoderNewDecoder2;
            }
            if (byteBuffer.get(0) == 0) {
                CharsetDecoder charsetDecoderNewDecoder3 = StandardCharsets.UTF_16BE.newDecoder();
                charsetDecoderNewDecoder3.reset();
                return charsetDecoderNewDecoder3;
            }
            CharsetDecoder charsetDecoderNewDecoder4 = StandardCharsets.UTF_16LE.newDecoder();
            charsetDecoderNewDecoder4.reset();
            return charsetDecoderNewDecoder4;
        }
        CharsetDecoder charsetDecoderNewDecoder5 = textEncodingCharSet.newDecoder();
        charsetDecoderNewDecoder5.reset();
        return charsetDecoderNewDecoder5;
    }

    protected Charset getTextEncodingCharSet() {
        return TextEncoding.getInstanceOf().getCharsetForId(getBody().getTextEncoding());
    }
}
