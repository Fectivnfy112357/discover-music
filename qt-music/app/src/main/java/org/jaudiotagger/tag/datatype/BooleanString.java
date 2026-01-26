package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class BooleanString extends AbstractDataType {
    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return 1;
    }

    public BooleanString(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
    }

    public BooleanString(BooleanString booleanString) {
        super(booleanString);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof BooleanString) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        this.value = Boolean.valueOf(bArr[i] != 48);
    }

    public String toString() {
        return "" + this.value;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArr = new byte[1];
        if (this.value == null || ((Boolean) this.value).booleanValue()) {
            bArr[0] = 48;
        } else {
            bArr[0] = 49;
        }
        return bArr;
    }
}
