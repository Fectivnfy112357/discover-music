package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyRVAD extends AbstractID3v2FrameBody implements ID3v23FrameBody {
    public FrameBodyRVAD() {
    }

    public FrameBodyRVAD(FrameBodyRVAD frameBodyRVAD) {
        super(frameBodyRVAD);
    }

    public FrameBodyRVAD(FrameBodyRVA2 frameBodyRVA2) {
        setObjectValue(DataTypes.OBJ_DATA, frameBodyRVA2.getObjectValue(DataTypes.OBJ_DATA));
    }

    public FrameBodyRVAD(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
