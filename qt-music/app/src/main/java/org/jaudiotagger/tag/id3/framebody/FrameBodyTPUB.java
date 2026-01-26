package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTPUB extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTPUB() {
    }

    public FrameBodyTPUB(FrameBodyTPUB frameBodyTPUB) {
        super(frameBodyTPUB);
    }

    public FrameBodyTPUB(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTPUB(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TPUB";
    }
}
