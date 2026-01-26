package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTSOP extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTSOP() {
    }

    public FrameBodyTSOP(FrameBodyTSOP frameBodyTSOP) {
        super(frameBodyTSOP);
    }

    public FrameBodyTSOP(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTSOP(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TSOP";
    }
}
