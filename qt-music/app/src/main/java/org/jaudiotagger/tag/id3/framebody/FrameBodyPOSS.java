package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.NumberVariableLength;

/* loaded from: classes3.dex */
public class FrameBodyPOSS extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyPOSS() {
    }

    public FrameBodyPOSS(FrameBodyPOSS frameBodyPOSS) {
        super(frameBodyPOSS);
    }

    public FrameBodyPOSS(byte b, long j) {
        setObjectValue(DataTypes.OBJ_TIME_STAMP_FORMAT, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_POSITION, Long.valueOf(j));
    }

    public FrameBodyPOSS(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "POSS";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TIME_STAMP_FORMAT, this, 1));
        this.objectList.add(new NumberVariableLength(DataTypes.OBJ_POSITION, this, 1));
    }
}
