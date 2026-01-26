package org.jaudiotagger.tag.datatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.datatype.AbstractDataType;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public abstract class AbstractDataTypeList<T extends AbstractDataType> extends AbstractDataType {
    protected abstract T createListElement();

    public AbstractDataTypeList(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        setValue((List) new ArrayList());
    }

    protected AbstractDataTypeList(AbstractDataTypeList<T> abstractDataTypeList) {
        super(abstractDataTypeList);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public List<T> getValue() {
        return (List) super.getValue();
    }

    public void setValue(List<T> list) {
        super.setValue((Object) (list == null ? new ArrayList() : new ArrayList(list)));
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        Iterator<T> it = getValue().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
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
            getValue().clear();
            return;
        }
        while (i < bArr.length) {
            AbstractDataType abstractDataTypeCreateListElement = createListElement();
            abstractDataTypeCreateListElement.readByteArray(bArr, i);
            abstractDataTypeCreateListElement.setBody(this.frameBody);
            getValue().add(abstractDataTypeCreateListElement);
            i += abstractDataTypeCreateListElement.getSize();
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("Writing DataTypeList " + getIdentifier());
        }
        byte[] bArr = new byte[getSize()];
        Iterator<T> it = getValue().iterator();
        int length = 0;
        while (it.hasNext()) {
            byte[] bArrWriteByteArray = it.next().writeByteArray();
            System.arraycopy(bArrWriteByteArray, 0, bArr, length, bArrWriteByteArray.length);
            length += bArrWriteByteArray.length;
        }
        return bArr;
    }

    public int hashCode() {
        if (getValue() != null) {
            return getValue().hashCode();
        }
        return 0;
    }

    public String toString() {
        return getValue() != null ? getValue().toString() : "{}";
    }
}
