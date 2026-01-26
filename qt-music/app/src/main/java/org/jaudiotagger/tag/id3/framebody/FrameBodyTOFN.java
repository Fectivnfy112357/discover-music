package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTOFN extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTOFN() {
    }

    public FrameBodyTOFN(FrameBodyTOFN frameBodyTOFN) {
        super(frameBodyTOFN);
    }

    public FrameBodyTOFN(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTOFN(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TOFN";
    }
}
