package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyWOAF extends AbstractFrameBodyUrlLink implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyWOAF() {
    }

    public FrameBodyWOAF(String str) {
        super(str);
    }

    public FrameBodyWOAF(FrameBodyWOAF frameBodyWOAF) {
        super(frameBodyWOAF);
    }

    public FrameBodyWOAF(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "WOAF";
    }
}
