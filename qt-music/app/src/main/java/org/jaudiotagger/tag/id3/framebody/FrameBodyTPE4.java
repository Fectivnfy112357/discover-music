package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTPE4 extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTPE4() {
    }

    public FrameBodyTPE4(FrameBodyTPE4 frameBodyTPE4) {
        super(frameBodyTPE4);
    }

    public FrameBodyTPE4(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTPE4(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TPE4";
    }
}
