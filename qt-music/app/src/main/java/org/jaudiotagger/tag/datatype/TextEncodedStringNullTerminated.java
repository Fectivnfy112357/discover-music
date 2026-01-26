package org.jaudiotagger.tag.datatype;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;

/* loaded from: classes3.dex */
public class TextEncodedStringNullTerminated extends AbstractString {
    public TextEncodedStringNullTerminated(String str, AbstractTagFrameBody abstractTagFrameBody, boolean z) {
        super(str, abstractTagFrameBody);
        this.isAllowReadMetadataWithOverrideCharset = z;
    }

    public TextEncodedStringNullTerminated(String str, AbstractTagFrameBody abstractTagFrameBody, String str2) {
        super(str, abstractTagFrameBody, str2);
    }

    public TextEncodedStringNullTerminated(TextEncodedStringNullTerminated textEncodedStringNullTerminated) {
        super(textEncodedStringNullTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof TextEncodedStringNullTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        int i2;
        int iPosition;
        int iPosition2;
        if (i >= bArr.length) {
            throw new InvalidDataTypeException("Unable to find null terminated string");
        }
        Charset textEncodingCharSet = getTextEncodingCharSet();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, bArr.length - i);
        boolean z = false;
        boolean z2 = StandardCharsets.ISO_8859_1 == textEncodingCharSet || StandardCharsets.UTF_8 == textEncodingCharSet;
        while (byteBufferWrap.hasRemaining()) {
            if (byteBufferWrap.get() == 0) {
                if (z2) {
                    byteBufferWrap.mark();
                    byteBufferWrap.reset();
                    iPosition = byteBufferWrap.position();
                } else if (byteBufferWrap.hasRemaining()) {
                    if (byteBufferWrap.get() == 0) {
                        byteBufferWrap.mark();
                        byteBufferWrap.reset();
                        iPosition2 = byteBufferWrap.position() - 2;
                        i2 = iPosition2;
                        z = true;
                        break;
                    }
                } else {
                    byteBufferWrap.mark();
                    byteBufferWrap.reset();
                    iPosition = byteBufferWrap.position();
                }
                iPosition2 = iPosition - 1;
                i2 = iPosition2;
                z = true;
                break;
            }
            if (!z2 && byteBufferWrap.hasRemaining()) {
                byteBufferWrap.get();
            }
        }
        i2 = 0;
        if (!z) {
            throw new InvalidDataTypeException("Unable to find null terminated string");
        }
        int i3 = i2 - i;
        int i4 = i3 + 1;
        if (!z2) {
            i4 = i3 + 2;
        }
        setSize(i4);
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Text size is:" + i3);
        }
        if (i3 == 0) {
            this.value = "";
        } else {
            ByteBuffer byteBufferSlice = ByteBuffer.wrap(bArr, i, i3).slice();
            CharBuffer charBufferAllocate = CharBuffer.allocate(i3);
            CharsetDecoder correctDecoder = getCorrectDecoder(byteBufferSlice);
            CoderResult coderResultDecode = correctDecoder.decode(byteBufferSlice, charBufferAllocate, true);
            if (coderResultDecode.isError()) {
                logger.warning("Problem decoding text encoded null terminated string:" + coderResultDecode.toString());
            }
            correctDecoder.flush(charBufferAllocate);
            charBufferAllocate.flip();
            this.value = charBufferAllocate.toString();
        }
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("Read NullTerminatedString:" + this.value + " size inc terminator:" + i4);
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() throws CharacterCodingException {
        byte[] bArr;
        logger.config("Writing NullTerminatedString." + this.value);
        Charset textEncodingCharSet = getTextEncodingCharSet();
        try {
            if (StandardCharsets.UTF_16.equals(textEncodingCharSet)) {
                if (TagOptionSingleton.getInstance().isEncodeUTF16BomAsLittleEndian()) {
                    CharsetEncoder charsetEncoderNewEncoder = StandardCharsets.UTF_16LE.newEncoder();
                    charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.IGNORE);
                    charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
                    ByteBuffer byteBufferEncode = charsetEncoderNewEncoder.encode(CharBuffer.wrap("\ufeff" + ((String) this.value) + (char) 0));
                    bArr = new byte[byteBufferEncode.limit()];
                    byteBufferEncode.get(bArr, 0, byteBufferEncode.limit());
                } else {
                    CharsetEncoder charsetEncoderNewEncoder2 = StandardCharsets.UTF_16BE.newEncoder();
                    charsetEncoderNewEncoder2.onMalformedInput(CodingErrorAction.IGNORE);
                    charsetEncoderNewEncoder2.onUnmappableCharacter(CodingErrorAction.IGNORE);
                    ByteBuffer byteBufferEncode2 = charsetEncoderNewEncoder2.encode(CharBuffer.wrap("\ufeff" + ((String) this.value) + (char) 0));
                    bArr = new byte[byteBufferEncode2.limit()];
                    byteBufferEncode2.get(bArr, 0, byteBufferEncode2.limit());
                }
            } else {
                CharsetEncoder charsetEncoderNewEncoder3 = textEncodingCharSet.newEncoder();
                charsetEncoderNewEncoder3.onMalformedInput(CodingErrorAction.IGNORE);
                charsetEncoderNewEncoder3.onUnmappableCharacter(CodingErrorAction.IGNORE);
                ByteBuffer byteBufferEncode3 = charsetEncoderNewEncoder3.encode(CharBuffer.wrap(((String) this.value) + (char) 0));
                bArr = new byte[byteBufferEncode3.limit()];
                byteBufferEncode3.get(bArr, 0, byteBufferEncode3.limit());
            }
            setSize(bArr.length);
            return bArr;
        } catch (CharacterCodingException e) {
            logger.severe(e.getMessage() + ":" + textEncodingCharSet.name() + ":" + this.value);
            throw new RuntimeException(e);
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
