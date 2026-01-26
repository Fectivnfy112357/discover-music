package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTIT1 extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTIT1() {
    }

    public FrameBodyTIT1(FrameBodyTIT1 frameBodyTIT1) {
        super(frameBodyTIT1);
    }

    public FrameBodyTIT1(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTIT1(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TIT1";
    }
}
