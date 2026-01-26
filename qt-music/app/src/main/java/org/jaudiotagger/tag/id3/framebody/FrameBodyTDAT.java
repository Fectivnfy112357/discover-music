package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.id3.ID3v23Frames;

/* loaded from: classes3.dex */
public class FrameBodyTDAT extends AbstractFrameBodyTextInfo implements ID3v23FrameBody {
    public static final int DATA_SIZE = 4;
    public static final int DAY_END = 2;
    public static final int DAY_START = 0;
    public static final int MONTH_END = 4;
    public static final int MONTH_START = 2;
    private boolean monthOnly;

    public FrameBodyTDAT() {
    }

    public FrameBodyTDAT(FrameBodyTDAT frameBodyTDAT) {
        super(frameBodyTDAT);
    }

    public FrameBodyTDAT(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTDAT(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v23Frames.FRAME_ID_V3_TDAT;
    }

    public boolean isMonthOnly() {
        return this.monthOnly;
    }

    public void setMonthOnly(boolean z) {
        this.monthOnly = z;
    }
}
