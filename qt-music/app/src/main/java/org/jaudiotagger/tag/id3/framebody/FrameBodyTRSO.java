package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTRSO extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTRSO() {
    }

    public FrameBodyTRSO(FrameBodyTRSO frameBodyTRSO) {
        super(frameBodyTRSO);
    }

    public FrameBodyTRSO(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTRSO(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TRSO";
    }
}
