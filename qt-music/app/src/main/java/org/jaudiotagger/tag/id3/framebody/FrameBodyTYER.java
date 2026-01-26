package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyTYER extends AbstractFrameBodyTextInfo implements ID3v23FrameBody {
    public FrameBodyTYER() {
    }

    public FrameBodyTYER(FrameBodyTYER frameBodyTYER) {
        super(frameBodyTYER);
    }

    public FrameBodyTYER(FrameBodyTDRC frameBodyTDRC) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
        setObjectValue(DataTypes.OBJ_TEXT, frameBodyTDRC.getText());
    }

    public FrameBodyTYER(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTYER(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_TYER;
    }
}
