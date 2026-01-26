package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyWOAR extends AbstractFrameBodyUrlLink implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyWOAR() {
    }

    public FrameBodyWOAR(String str) {
        super(str);
    }

    public FrameBodyWOAR(FrameBodyWOAR frameBodyWOAR) {
        super(frameBodyWOAR);
    }

    public FrameBodyWOAR(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "WOAR";
    }
}
