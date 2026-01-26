package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyTDOR extends AbstractFrameBodyTextInfo implements ID3v24FrameBody {
    public FrameBodyTDOR() {
    }

    public FrameBodyTDOR(FrameBodyTDOR frameBodyTDOR) {
        super(frameBodyTDOR);
    }

    public FrameBodyTDOR(FrameBodyTORY frameBodyTORY) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
        setObjectValue(DataTypes.OBJ_TEXT, frameBodyTORY.getText());
    }

    public FrameBodyTDOR(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTDOR(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_ORIGINAL_RELEASE_TIME;
    }
}
