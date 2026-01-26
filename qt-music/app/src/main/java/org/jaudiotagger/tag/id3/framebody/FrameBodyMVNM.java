package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyMVNM extends AbstractFrameBodyTextInfo implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyMVNM() {
    }

    public FrameBodyMVNM(FrameBodyMVNM frameBodyMVNM) {
        super(frameBodyMVNM);
    }

    public FrameBodyMVNM(byte b, String str) {
        super(b, str);
    }

    public FrameBodyMVNM(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "MVNM";
    }
}
