package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTOAL extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTOAL() {
    }

    public FrameBodyTOAL(FrameBodyTOAL frameBodyTOAL) {
        super(frameBodyTOAL);
    }

    public FrameBodyTOAL(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTOAL(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TOAL";
    }
}
