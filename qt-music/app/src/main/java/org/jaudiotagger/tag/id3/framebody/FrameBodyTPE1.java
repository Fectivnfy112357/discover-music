package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTPE1 extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTPE1() {
    }

    public FrameBodyTPE1(FrameBodyTPE1 frameBodyTPE1) {
        super(frameBodyTPE1);
    }

    public FrameBodyTPE1(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTPE1(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TPE1";
    }
}
