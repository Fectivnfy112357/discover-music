package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyXSOP extends AbstractFrameBodyTextInfo implements ID3v23FrameBody {
    public FrameBodyXSOP() {
    }

    public FrameBodyXSOP(FrameBodyXSOP frameBodyXSOP) {
        super(frameBodyXSOP);
    }

    public FrameBodyXSOP(byte b, String str) {
        super(b, str);
    }

    public FrameBodyXSOP(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRAINZ;
    }
}
