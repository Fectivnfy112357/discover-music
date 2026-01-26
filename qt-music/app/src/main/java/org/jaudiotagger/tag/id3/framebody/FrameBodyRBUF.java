package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.BooleanByte;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberFixedLength;

/* loaded from: classes3.dex */
public class FrameBodyRBUF extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    private static int BUFFER_FIELD_SIZE = 3;
    private static int EMBED_FLAG_BIT_POSITION = 1;
    private static int OFFSET_FIELD_SIZE = 4;

    public FrameBodyRBUF() {
        setObjectValue(DataTypes.OBJ_BUFFER_SIZE, (byte) 0);
        setObjectValue(DataTypes.OBJ_EMBED_FLAG, Boolean.FALSE);
        setObjectValue(DataTypes.OBJ_OFFSET, (byte) 0);
    }

    public FrameBodyRBUF(FrameBodyRBUF frameBodyRBUF) {
        super(frameBodyRBUF);
    }

    public FrameBodyRBUF(byte b, boolean z, byte b2) {
        setObjectValue(DataTypes.OBJ_BUFFER_SIZE, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_EMBED_FLAG, Boolean.valueOf(z));
        setObjectValue(DataTypes.OBJ_OFFSET, Byte.valueOf(b2));
    }

    public FrameBodyRBUF(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "RBUF";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_BUFFER_SIZE, this, BUFFER_FIELD_SIZE));
        this.objectList.add(new BooleanByte(DataTypes.OBJ_EMBED_FLAG, this, (byte) EMBED_FLAG_BIT_POSITION));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_OFFSET, this, OFFSET_FIELD_SIZE));
    }
}
