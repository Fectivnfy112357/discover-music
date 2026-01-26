package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class BooleanByte extends AbstractDataType {
    int bitPosition;

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return 1;
    }

    public BooleanByte(String str, AbstractTagFrameBody abstractTagFrameBody, int i) {
        super(str, abstractTagFrameBody);
        this.bitPosition = -1;
        if (i < 0 || i > 7) {
            throw new IndexOutOfBoundsException("Bit position needs to be from 0 - 7 : " + i);
        }
        this.bitPosition = i;
    }

    public BooleanByte(BooleanByte booleanByte) {
        super(booleanByte);
        this.bitPosition = -1;
        this.bitPosition = booleanByte.bitPosition;
    }

    public int getBitPosition() {
        return this.bitPosition;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof BooleanByte) && this.bitPosition == ((BooleanByte) obj).bitPosition && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (i < 0 || i >= bArr.length) {
            throw new IndexOutOfBoundsException("Offset to byte array is out of bounds: offset = " + i + ", array.length = " + bArr.length);
        }
        this.value = Boolean.valueOf(((byte) (((byte) (bArr[i] >> this.bitPosition)) & 1)) == 1);
    }

    public String toString() {
        return "" + this.value;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArr = new byte[1];
        if (this.value != null) {
            byte b = ((Boolean) this.value).booleanValue() ? (byte) 1 : (byte) 0;
            bArr[0] = b;
            bArr[0] = (byte) (b << this.bitPosition);
        }
        return bArr;
    }
}
