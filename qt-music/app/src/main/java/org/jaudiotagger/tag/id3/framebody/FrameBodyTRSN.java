package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTRSN extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTRSN() {
    }

    public FrameBodyTRSN(FrameBodyTRSN frameBodyTRSN) {
        super(frameBodyTRSN);
    }

    public FrameBodyTRSN(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTRSN(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TRSN";
    }
}
