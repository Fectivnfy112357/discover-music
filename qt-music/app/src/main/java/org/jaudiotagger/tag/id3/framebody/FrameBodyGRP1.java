package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyGRP1 extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyGRP1() {
    }

    public FrameBodyGRP1(FrameBodyGRP1 frameBodyGRP1) {
        super(frameBodyGRP1);
    }

    public FrameBodyGRP1(byte b, String str) {
        super(b, str);
    }

    public FrameBodyGRP1(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "GRP1";
    }
}
