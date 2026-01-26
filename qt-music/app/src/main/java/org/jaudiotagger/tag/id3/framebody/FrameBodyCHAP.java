package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberFixedLength;
import org.jaudiotagger.tag.datatype.StringNullTerminated;

/* loaded from: classes3.dex */
public class FrameBodyCHAP extends AbstractID3v2FrameBody implements ID3v2ChapterFrameBody, ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyCHAP() {
    }

    public FrameBodyCHAP(FrameBodyCHAP frameBodyCHAP) {
        super(frameBodyCHAP);
    }

    public FrameBodyCHAP(String str, int i, int i2, int i3, int i4) {
        setObjectValue(DataTypes.OBJ_ELEMENT_ID, str);
        setObjectValue(DataTypes.OBJ_START_TIME, Integer.valueOf(i));
        setObjectValue(DataTypes.OBJ_END_TIME, Integer.valueOf(i2));
        setObjectValue(DataTypes.OBJ_START_OFFSET, Integer.valueOf(i3));
        setObjectValue(DataTypes.OBJ_END_OFFSET, Integer.valueOf(i4));
    }

    public FrameBodyCHAP(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "CHAP";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_ELEMENT_ID, this));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_START_TIME, this, 4));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_END_TIME, this, 4));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_START_OFFSET, this, 4));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_END_OFFSET, this, 4));
    }
}
