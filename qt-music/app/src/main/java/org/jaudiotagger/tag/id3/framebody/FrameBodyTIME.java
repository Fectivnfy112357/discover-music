package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyTIME extends AbstractFrameBodyTextInfo implements ID3v23FrameBody {
    private boolean hoursOnly;

    public FrameBodyTIME() {
    }

    public FrameBodyTIME(FrameBodyTIME frameBodyTIME) {
        super(frameBodyTIME);
    }

    public FrameBodyTIME(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTIME(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_TIME;
    }

    public boolean isHoursOnly() {
        return this.hoursOnly;
    }

    public void setHoursOnly(boolean z) {
        this.hoursOnly = z;
    }
}
