package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTBPM extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTBPM() {
    }

    public FrameBodyTBPM(FrameBodyTBPM frameBodyTBPM) {
        super(frameBodyTBPM);
    }

    public FrameBodyTBPM(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTBPM(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TBPM";
    }
}
