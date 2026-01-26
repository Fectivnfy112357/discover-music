package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyTDTG extends AbstractFrameBodyTextInfo implements ID3v24FrameBody {
    public FrameBodyTDTG() {
    }

    public FrameBodyTDTG(FrameBodyTDTG frameBodyTDTG) {
        super(frameBodyTDTG);
    }

    public FrameBodyTDTG(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTDTG(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_TAGGING_TIME;
    }
}
