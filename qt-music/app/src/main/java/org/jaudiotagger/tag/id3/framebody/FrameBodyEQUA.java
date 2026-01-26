package org.jaudiotagger.tag.id3.framebody;

import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyEQUA extends AbstractID3v2FrameBody implements ID3v23FrameBody {
    public FrameBodyEQUA() {
    }

    public FrameBodyEQUA(FrameBodyEQUA frameBodyEQUA) {
        super(frameBodyEQUA);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_EQUALISATION;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
