package org.jaudiotagger.tag.datatype;

import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3Tags;

/* loaded from: classes3.dex */
public class NumberFixedLength extends AbstractDataType {
    public NumberFixedLength(String str, AbstractTagFrameBody abstractTagFrameBody, int i) {
        super(str, abstractTagFrameBody);
        if (i < 0) {
            throw new IllegalArgumentException("Length is less than zero: " + i);
        }
        this.size = i;
    }

    public NumberFixedLength(NumberFixedLength numberFixedLength) {
        super(numberFixedLength);
        this.size = numberFixedLength.size;
    }

    public void setSize(int i) {
        if (i > 0) {
            this.size = i;
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return this.size;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void setValue(Object obj) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException("Invalid value type for NumberFixedLength:" + obj.getClass());
        }
        super.setValue(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof NumberFixedLength) && this.size == ((NumberFixedLength) obj).size && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (i < 0 || i >= bArr.length) {
            throw new InvalidDataTypeException("Offset to byte array is out of bounds: offset = " + i + ", array.length = " + bArr.length);
        }
        if (this.size + i > bArr.length) {
            throw new InvalidDataTypeException("Offset plus size to byte array is out of bounds: offset = " + i + ", size = " + this.size + " + arr.length " + bArr.length);
        }
        long j = 0;
        for (int i2 = i; i2 < this.size + i; i2++) {
            j = (j << 8) + (bArr[i2] & 255);
        }
        this.value = Long.valueOf(j);
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("Read NumberFixedlength:" + this.value);
        }
    }

    public String toString() {
        if (this.value == null) {
            return "";
        }
        return this.value.toString();
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArr = new byte[this.size];
        if (this.value != null) {
            long wholeNumber = ID3Tags.getWholeNumber(this.value);
            for (int i = this.size - 1; i >= 0; i--) {
                bArr[i] = (byte) (255 & wholeNumber);
                wholeNumber >>= 8;
            }
        }
        return bArr;
    }
}
