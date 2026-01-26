package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyTRDA extends AbstractFrameBodyTextInfo implements ID3v23FrameBody {
    public FrameBodyTRDA() {
    }

    public FrameBodyTRDA(FrameBodyTRDA frameBodyTRDA) {
        super(frameBodyTRDA);
    }

    public FrameBodyTRDA(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTRDA(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_TRDA;
    }
}
