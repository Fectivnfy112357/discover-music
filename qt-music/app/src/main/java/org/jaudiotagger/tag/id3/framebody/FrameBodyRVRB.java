package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberFixedLength;

/* loaded from: classes3.dex */
public class FrameBodyRVRB extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyRVRB() {
    }

    public FrameBodyRVRB(FrameBodyRVRB frameBodyRVRB) {
        super(frameBodyRVRB);
    }

    public FrameBodyRVRB(short s, short s2, byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        setObjectValue(DataTypes.OBJ_REVERB_LEFT, Short.valueOf(s));
        setObjectValue(DataTypes.OBJ_REVERB_RIGHT, Short.valueOf(s2));
        setObjectValue(DataTypes.OBJ_REVERB_BOUNCE_LEFT, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_REVERB_BOUNCE_RIGHT, Byte.valueOf(b2));
        setObjectValue(DataTypes.OBJ_REVERB_FEEDBACK_LEFT_TO_LEFT, Byte.valueOf(b3));
        setObjectValue(DataTypes.OBJ_REVERB_FEEDBACK_LEFT_TO_RIGHT, Byte.valueOf(b4));
        setObjectValue(DataTypes.OBJ_REVERB_FEEDBACK_RIGHT_TO_RIGHT, Byte.valueOf(b5));
        setObjectValue(DataTypes.OBJ_REVERB_FEEDBACK_RIGHT_TO_LEFT, Byte.valueOf(b6));
        setObjectValue(DataTypes.OBJ_PREMIX_LEFT_TO_RIGHT, Byte.valueOf(b7));
        setObjectValue(DataTypes.OBJ_PREMIX_RIGHT_TO_LEFT, Byte.valueOf(b8));
    }

    public FrameBodyRVRB(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "RVRB";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_LEFT, this, 2));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_RIGHT, this, 2));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_BOUNCE_LEFT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_BOUNCE_RIGHT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_FEEDBACK_LEFT_TO_LEFT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_FEEDBACK_LEFT_TO_RIGHT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_FEEDBACK_RIGHT_TO_RIGHT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_REVERB_FEEDBACK_RIGHT_TO_LEFT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_PREMIX_LEFT_TO_RIGHT, this, 1));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_PREMIX_RIGHT_TO_LEFT, this, 1));
    }
}
