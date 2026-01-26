package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyTDEN extends AbstractFrameBodyTextInfo implements ID3v24FrameBody {
    public FrameBodyTDEN() {
    }

    public FrameBodyTDEN(FrameBodyTDEN frameBodyTDEN) {
        super(frameBodyTDEN);
    }

    public FrameBodyTDEN(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTDEN(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_ENCODING_TIME;
    }
}
