package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3Tags;

/* loaded from: classes3.dex */
public class TempoCode extends AbstractDataType {
    private static final int MAXIMUM_NO_OF_DIGITS = 2;
    private static final int MINIMUM_NO_OF_DIGITS = 1;

    public TempoCode(TempoCode tempoCode) {
        super(tempoCode);
    }

    public TempoCode(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody, 0);
    }

    public TempoCode(String str, AbstractTagFrameBody abstractTagFrameBody, Object obj) {
        super(str, abstractTagFrameBody, obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        if (this.value == null) {
            return 0;
        }
        return ID3Tags.getWholeNumber(this.value) < 255 ? 1 : 2;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof TempoCode) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("negative offset into an array offset:" + i);
        }
        if (i >= bArr.length) {
            throw new InvalidDataTypeException("Offset to byte array is out of bounds: offset = " + i + ", array.length = " + bArr.length);
        }
        long j = bArr[i] & 255;
        if (j == 255) {
            j += bArr[i + 1] & 255;
        }
        this.value = Long.valueOf(j);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArr = new byte[getSize()];
        long wholeNumber = ID3Tags.getWholeNumber(this.value);
        char c = 0;
        if (wholeNumber >= 255) {
            bArr[0] = -1;
            wholeNumber -= 255;
            c = 1;
        }
        bArr[c] = (byte) (wholeNumber & 255);
        return bArr;
    }

    public String toString() {
        return this.value == null ? "" : this.value.toString();
    }
}
