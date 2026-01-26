package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.reference.MusicalKey;

/* loaded from: classes3.dex */
public class FrameBodyTKEY extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTKEY() {
    }

    public FrameBodyTKEY(FrameBodyTKEY frameBodyTKEY) {
        super(frameBodyTKEY);
    }

    public FrameBodyTKEY(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTKEY(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TKEY";
    }

    public boolean isValid() {
        return MusicalKey.isValid(getFirstTextValue());
    }
}
