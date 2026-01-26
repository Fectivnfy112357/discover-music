package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTOPE extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTOPE() {
    }

    public FrameBodyTOPE(FrameBodyTOPE frameBodyTOPE) {
        super(frameBodyTOPE);
    }

    public FrameBodyTOPE(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTOPE(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TOPE";
    }
}
