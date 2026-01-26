package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyMVIN extends AbstractFrameBodyNumberTotal implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyMVIN() {
    }

    public FrameBodyMVIN(FrameBodyMVIN frameBodyMVIN) {
        super(frameBodyMVIN);
    }

    public FrameBodyMVIN(byte b, String str) {
        super(b, str);
    }

    public FrameBodyMVIN(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public FrameBodyMVIN(byte b, Integer num, Integer num2) {
        super(b, num, num2);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyNumberTotal, org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "MVIN";
    }
}
