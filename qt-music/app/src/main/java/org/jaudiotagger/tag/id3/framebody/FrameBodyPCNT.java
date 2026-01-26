package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberVariableLength;

/* loaded from: classes3.dex */
public class FrameBodyPCNT extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    private static final int COUNTER_MINIMUM_FIELD_SIZE = 4;

    public FrameBodyPCNT() {
        setObjectValue(DataTypes.OBJ_NUMBER, 0L);
    }

    public FrameBodyPCNT(FrameBodyPCNT frameBodyPCNT) {
        super(frameBodyPCNT);
    }

    public FrameBodyPCNT(long j) {
        setObjectValue(DataTypes.OBJ_NUMBER, Long.valueOf(j));
    }

    public FrameBodyPCNT(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public long getCounter() {
        return ((Number) getObjectValue(DataTypes.OBJ_NUMBER)).longValue();
    }

    public void setCounter(long j) {
        setObjectValue(DataTypes.OBJ_NUMBER, Long.valueOf(j));
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "PCNT";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberVariableLength(DataTypes.OBJ_NUMBER, this, 4));
    }
}
