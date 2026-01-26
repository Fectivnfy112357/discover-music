package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberFixedLength;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodySEEK extends AbstractID3v2FrameBody implements ID3v24FrameBody {
    public FrameBodySEEK() {
    }

    public FrameBodySEEK(int i) {
        setObjectValue(DataTypes.OBJ_OFFSET, Integer.valueOf(i));
    }

    public FrameBodySEEK(FrameBodySEEK frameBodySEEK) {
        super(frameBodySEEK);
    }

    public FrameBodySEEK(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_AUDIO_SEEK_POINT_INDEX;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_OFFSET, this, 4));
    }
}
