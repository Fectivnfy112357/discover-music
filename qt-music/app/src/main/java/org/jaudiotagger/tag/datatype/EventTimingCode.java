package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.valuepair.EventTimingTypes;

/* loaded from: classes3.dex */
public class EventTimingCode extends AbstractDataType implements Cloneable {
    private static final int SIZE = 5;
    private NumberFixedLength timestamp;
    private NumberHashMap type;

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return 5;
    }

    public EventTimingCode(EventTimingCode eventTimingCode) {
        super(eventTimingCode);
        this.type = new NumberHashMap(DataTypes.OBJ_TYPE_OF_EVENT, null, 1);
        this.timestamp = new NumberFixedLength("DateTime", null, 4);
        this.type.setValue(eventTimingCode.type.getValue());
        this.timestamp.setValue(eventTimingCode.timestamp.getValue());
    }

    public EventTimingCode(String str, AbstractTagFrameBody abstractTagFrameBody) {
        this(str, abstractTagFrameBody, 0, 0L);
    }

    public EventTimingCode(String str, AbstractTagFrameBody abstractTagFrameBody, int i, long j) {
        super(str, abstractTagFrameBody);
        this.type = new NumberHashMap(DataTypes.OBJ_TYPE_OF_EVENT, null, 1);
        this.timestamp = new NumberFixedLength("DateTime", null, 4);
        setBody(abstractTagFrameBody);
        this.type.setValue(Integer.valueOf(i));
        this.timestamp.setValue(Long.valueOf(j));
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void setBody(AbstractTagFrameBody abstractTagFrameBody) {
        super.setBody(abstractTagFrameBody);
        this.type.setBody(abstractTagFrameBody);
        this.timestamp.setBody(abstractTagFrameBody);
    }

    public long getTimestamp() {
        return ((Number) this.timestamp.getValue()).longValue();
    }

    public void setTimestamp(long j) {
        this.timestamp.setValue(Long.valueOf(j));
    }

    public int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public void setType(int i) {
        this.type.setValue(Integer.valueOf(i));
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        int size = getSize();
        logger.finest("offset:" + i);
        if (i > bArr.length - size) {
            logger.warning("Invalid size for FrameBody");
            throw new InvalidDataTypeException("Invalid size for FrameBody");
        }
        this.type.readByteArray(bArr, i);
        this.timestamp.readByteArray(bArr, i + this.type.getSize());
        this.timestamp.getSize();
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArrWriteByteArray = this.type.writeByteArray();
        byte[] bArrWriteByteArray2 = this.timestamp.writeByteArray();
        if (bArrWriteByteArray == null || bArrWriteByteArray2 == null) {
            return null;
        }
        byte[] bArr = new byte[bArrWriteByteArray.length + bArrWriteByteArray2.length];
        System.arraycopy(bArrWriteByteArray, 0, bArr, 0, bArrWriteByteArray.length);
        System.arraycopy(bArrWriteByteArray2, 0, bArr, bArrWriteByteArray.length, bArrWriteByteArray2.length);
        return bArr;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        EventTimingCode eventTimingCode = (EventTimingCode) obj;
        return getType() == eventTimingCode.getType() && getTimestamp() == eventTimingCode.getTimestamp();
    }

    public int hashCode() {
        NumberHashMap numberHashMap = this.type;
        int iHashCode = (numberHashMap != null ? numberHashMap.hashCode() : 0) * 31;
        NumberFixedLength numberFixedLength = this.timestamp;
        return iHashCode + (numberFixedLength != null ? numberFixedLength.hashCode() : 0);
    }

    public String toString() {
        return "" + getType() + " (\"" + EventTimingTypes.getInstanceOf().getValueForId(getType()) + "\"), " + getTimestamp();
    }

    public Object clone() throws CloneNotSupportedException {
        return new EventTimingCode(this);
    }
}
