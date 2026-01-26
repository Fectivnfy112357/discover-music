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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class TextEncodedStringSizeTerminated extends AbstractString {
    public TextEncodedStringSizeTerminated(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.isAllowReadMetadataWithOverrideCharset = true;
    }

    public TextEncodedStringSizeTerminated(TextEncodedStringSizeTerminated textEncodedStringSizeTerminated) {
        super(textEncodedStringSizeTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TextEncodedStringSizeTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        logger.finest("Reading from array from offset:" + i);
        int length = bArr.length - i;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i, bArr2, 0, length);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        CharBuffer charBufferAllocate = CharBuffer.allocate(bArr.length - i);
        CharsetDecoder correctDecoder = getCorrectDecoder(byteBufferWrap);
        CoderResult coderResultDecode = correctDecoder.decode(byteBufferWrap, charBufferAllocate, true);
        if (coderResultDecode.isError()) {
            logger.warning("Decoding error:" + coderResultDecode.toString());
        }
        correctDecoder.flush(charBufferAllocate);
        charBufferAllocate.flip();
        if (StandardCharsets.UTF_16.equals(getTextEncodingCharSet())) {
            this.value = charBufferAllocate.toString().replace("\ufeff", "").replace("\ufffe", "");
            this.value = ((String) this.value).replace("﷿", "").replace("�", "");
        } else {
            this.value = charBufferAllocate.toString();
        }
        setSize(bArr.length - i);
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Read SizeTerminatedString:" + this.value + " size:" + this.size);
        }
    }

    protected ByteBuffer writeString(CharsetEncoder charsetEncoder, String str, int i, int i2) throws CharacterCodingException {
        ByteBuffer byteBufferEncode;
        if (i + 1 == i2) {
            byteBufferEncode = charsetEncoder.encode(CharBuffer.wrap(str));
        } else {
            byteBufferEncode = charsetEncoder.encode(CharBuffer.wrap(str + (char) 0));
        }
        byteBufferEncode.rewind();
        return byteBufferEncode;
    }

    protected ByteBuffer writeStringUTF16LEBOM(String str, int i, int i2) throws CharacterCodingException {
        ByteBuffer byteBufferEncode;
        CharsetEncoder charsetEncoderNewEncoder = StandardCharsets.UTF_16LE.newEncoder();
        charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.IGNORE);
        charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
        if (i + 1 == i2) {
            byteBufferEncode = charsetEncoderNewEncoder.encode(CharBuffer.wrap("\ufeff" + str));
        } else {
            byteBufferEncode = charsetEncoderNewEncoder.encode(CharBuffer.wrap("\ufeff" + str + (char) 0));
        }
        byteBufferEncode.rewind();
        return byteBufferEncode;
    }

    protected ByteBuffer writeStringUTF16BEBOM(String str, int i, int i2) throws CharacterCodingException {
        ByteBuffer byteBufferEncode;
        CharsetEncoder charsetEncoderNewEncoder = StandardCharsets.UTF_16BE.newEncoder();
        charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.IGNORE);
        charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
        if (i + 1 == i2) {
            byteBufferEncode = charsetEncoderNewEncoder.encode(CharBuffer.wrap("\ufeff" + str));
        } else {
            byteBufferEncode = charsetEncoderNewEncoder.encode(CharBuffer.wrap("\ufeff" + str + (char) 0));
        }
        byteBufferEncode.rewind();
        return byteBufferEncode;
    }

    protected void stripTrailingNull() {
        if (TagOptionSingleton.getInstance().isRemoveTrailingTerminatorOnWrite()) {
            String str = (String) this.value;
            if (str.length() <= 0 || str.charAt(str.length() - 1) != 0) {
                return;
            }
            this.value = str.substring(0, str.length() - 1);
        }
    }

    protected void checkTrailingNull(List<String> list, String str) {
        if (TagOptionSingleton.getInstance().isRemoveTrailingTerminatorOnWrite() || str.length() <= 0 || str.charAt(str.length() - 1) != 0) {
            return;
        }
        list.set(list.size() - 1, list.get(list.size() - 1) + (char) 0);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        Charset charset;
        Charset textEncodingCharSet = getTextEncodingCharSet();
        try {
            stripTrailingNull();
            String str = (String) this.value;
            if (!StandardCharsets.UTF_16.equals(textEncodingCharSet)) {
                charset = null;
            } else if (TagOptionSingleton.getInstance().isEncodeUTF16BomAsLittleEndian()) {
                charset = StandardCharsets.UTF_16LE;
            } else {
                charset = StandardCharsets.UTF_16BE;
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((str.length() + 3) * 3);
            List<String> listSplitByNullSeperator = splitByNullSeperator(str);
            checkTrailingNull(listSplitByNullSeperator, str);
            for (int i = 0; i < listSplitByNullSeperator.size(); i++) {
                String str2 = listSplitByNullSeperator.get(i);
                if (StandardCharsets.UTF_16LE.equals(charset)) {
                    byteBufferAllocate.put(writeStringUTF16LEBOM(str2, i, listSplitByNullSeperator.size()));
                } else if (StandardCharsets.UTF_16BE.equals(charset)) {
                    byteBufferAllocate.put(writeStringUTF16BEBOM(str2, i, listSplitByNullSeperator.size()));
                } else {
                    CharsetEncoder charsetEncoderNewEncoder = textEncodingCharSet.newEncoder();
                    charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.IGNORE);
                    charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
                    byteBufferAllocate.put(writeString(charsetEncoderNewEncoder, str2, i, listSplitByNullSeperator.size()));
                }
            }
            byteBufferAllocate.flip();
            int iLimit = byteBufferAllocate.limit();
            byte[] bArr = new byte[iLimit];
            byteBufferAllocate.rewind();
            byteBufferAllocate.get(bArr, 0, byteBufferAllocate.limit());
            setSize(iLimit);
            return bArr;
        } catch (CharacterCodingException e) {
            logger.severe(e.getMessage() + ":" + textEncodingCharSet + ":" + this.value);
            throw new RuntimeException(e);
        }
    }

    public static List<String> splitByNullSeperator(String str) {
        List<String> listAsList = Arrays.asList(str.split("\\u0000"));
        if (listAsList.size() != 0) {
            return listAsList;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("");
        return arrayList;
    }

    public void addValue(String str) {
        setValue(this.value + "\u0000" + str);
    }

    public int getNumberOfValues() {
        return splitByNullSeperator((String) this.value).size();
    }

    public String getValueAtIndex(int i) {
        return splitByNullSeperator((String) this.value).get(i);
    }

    public List<String> getValues() {
        return splitByNullSeperator((String) this.value);
    }

    public String getValueWithoutTrailingNull() {
        List<String> listSplitByNullSeperator = splitByNullSeperator((String) this.value);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < listSplitByNullSeperator.size(); i++) {
            if (i != 0) {
                stringBuffer.append("\u0000");
            }
            stringBuffer.append(listSplitByNullSeperator.get(i));
        }
        return stringBuffer.toString();
    }
}
