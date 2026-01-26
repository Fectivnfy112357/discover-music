package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.reference.Languages;

/* loaded from: classes3.dex */
public class FrameBodyTLAN extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTLAN() {
    }

    public FrameBodyTLAN(FrameBodyTLAN frameBodyTLAN) {
        super(frameBodyTLAN);
    }

    public FrameBodyTLAN(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTLAN(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TLAN";
    }

    public boolean isValid() {
        return Languages.getInstanceOf().getValueForId(getFirstTextValue()) != null;
    }
}
