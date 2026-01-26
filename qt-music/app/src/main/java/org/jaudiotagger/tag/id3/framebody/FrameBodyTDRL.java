package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyTDRL extends AbstractFrameBodyTextInfo implements ID3v24FrameBody {
    public FrameBodyTDRL() {
    }

    public FrameBodyTDRL(FrameBodyTDRL frameBodyTDRL) {
        super(frameBodyTDRL);
    }

    public FrameBodyTDRL(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTDRL(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_RELEASE_TIME;
    }
}
