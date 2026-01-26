package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyRVA2 extends AbstractID3v2FrameBody implements ID3v24FrameBody {
    public FrameBodyRVA2() {
    }

    public FrameBodyRVA2(FrameBodyRVA2 frameBodyRVA2) {
        super(frameBodyRVA2);
    }

    public FrameBodyRVA2(FrameBodyRVAD frameBodyRVAD) {
        setObjectValue(DataTypes.OBJ_DATA, frameBodyRVAD.getObjectValue(DataTypes.OBJ_DATA));
    }

    public FrameBodyRVA2(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
