package org.jaudiotagger.tag.datatype;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;

/* loaded from: classes3.dex */
public class StringFixedLength extends AbstractString {
    public StringFixedLength(String str, AbstractTagFrameBody abstractTagFrameBody, int i) {
        super(str, abstractTagFrameBody);
        if (i < 0) {
            throw new IllegalArgumentException("size is less than zero: " + i);
        }
        setSize(i);
    }

    public StringFixedLength(StringFixedLength stringFixedLength) {
        super(stringFixedLength);
        this.size = stringFixedLength.size;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof StringFixedLength) && this.size == ((StringFixedLength) obj).size && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        CharsetDecoder charsetDecoderNewDecoder;
        logger.config("Reading from array from offset:" + i);
        try {
            charsetDecoderNewDecoder = getTextEncodingCharSet().newDecoder();
            logger.finest("Array length is:" + bArr.length + "offset is:" + i + "Size is:" + this.size);
        } catch (CharacterCodingException e) {
            logger.severe(e.getMessage());
            this.value = "";
        }
        if (bArr.length - i < this.size) {
            throw new InvalidDataTypeException("byte array is to small to retrieve string of declared length:" + this.size);
        }
        String string = charsetDecoderNewDecoder.decode(ByteBuffer.wrap(bArr, i, this.size)).toString();
        if (string == null) {
            throw new NullPointerException("String is null");
        }
        this.value = string;
        logger.config("Read StringFixedLength:" + this.value);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        int i = 0;
        if (this.value == null) {
            logger.warning("Value of StringFixedlength Field is null using default value instead");
            byte[] bArr = new byte[this.size];
            while (i < this.size) {
                bArr[i] = 32;
                i++;
            }
            return bArr;
        }
        try {
            Charset textEncodingCharSet = getTextEncodingCharSet();
            ByteBuffer byteBufferEncode = StandardCharsets.UTF_16.equals(textEncodingCharSet) ? StandardCharsets.UTF_16LE.newEncoder().encode(CharBuffer.wrap("\ufeff" + ((String) this.value))) : textEncodingCharSet.newEncoder().encode(CharBuffer.wrap((String) this.value));
            if (byteBufferEncode == null) {
                logger.warning("There was a serious problem writing the following StringFixedlength Field:" + this.value + ":using default value instead");
                byte[] bArr2 = new byte[this.size];
                while (i < this.size) {
                    bArr2[i] = 32;
                    i++;
                }
                return bArr2;
            }
            if (byteBufferEncode.limit() == this.size) {
                byte[] bArr3 = new byte[byteBufferEncode.limit()];
                byteBufferEncode.get(bArr3, 0, byteBufferEncode.limit());
                return bArr3;
            }
            if (byteBufferEncode.limit() > this.size) {
                logger.warning("There was a problem writing the following StringFixedlength Field:" + this.value + " when converted to bytes has length of:" + byteBufferEncode.limit() + " but field was defined with length of:" + this.size + " too long so stripping extra length");
                byte[] bArr4 = new byte[this.size];
                byteBufferEncode.get(bArr4, 0, this.size);
                return bArr4;
            }
            logger.warning("There was a problem writing the following StringFixedlength Field:" + this.value + " when converted to bytes has length of:" + byteBufferEncode.limit() + " but field was defined with length of:" + this.size + " too short so padding with spaces to make up extra length");
            byte[] bArr5 = new byte[this.size];
            byteBufferEncode.get(bArr5, 0, byteBufferEncode.limit());
            for (int iLimit = byteBufferEncode.limit(); iLimit < this.size; iLimit++) {
                bArr5[iLimit] = 32;
            }
            return bArr5;
        } catch (CharacterCodingException e) {
            logger.warning("There was a problem writing the following StringFixedlength Field:" + this.value + ":" + e.getMessage() + "using default value instead");
            byte[] bArr6 = new byte[this.size];
            while (i < this.size) {
                bArr6[i] = 32;
                i++;
            }
            return bArr6;
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractString
    protected Charset getTextEncodingCharSet() {
        byte textEncoding = getBody().getTextEncoding();
        Charset charsetForId = TextEncoding.getInstanceOf().getCharsetForId(textEncoding);
        logger.finest("text encoding:" + ((int) textEncoding) + " charset:" + charsetForId.name());
        return charsetForId;
    }
}
