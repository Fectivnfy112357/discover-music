package org.jaudiotagger.tag.datatype;

import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class ByteArraySizeTerminated extends AbstractDataType {
    public ByteArraySizeTerminated(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
    }

    public ByteArraySizeTerminated(ByteArraySizeTerminated byteArraySizeTerminated) {
        super(byteArraySizeTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        if (this.value != null) {
            return ((byte[]) this.value).length;
        }
        return 0;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof ByteArraySizeTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Offset to byte array is out of bounds: offset = " + i + ", array.length = " + bArr.length);
        }
        if (i >= bArr.length) {
            this.value = null;
            return;
        }
        int length = bArr.length - i;
        this.value = new byte[length];
        System.arraycopy(bArr, i, this.value, 0, length);
    }

    public String toString() {
        return getSize() + " bytes";
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("Writing byte array" + getIdentifier());
        }
        return (byte[]) this.value;
    }
}
