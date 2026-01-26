package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FrameBodyTPOS extends AbstractFrameBodyNumberTotal implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTPOS() {
    }

    public FrameBodyTPOS(FrameBodyTPOS frameBodyTPOS) {
        super(frameBodyTPOS);
    }

    public FrameBodyTPOS(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTPOS(byte b, Integer num, Integer num2) {
        super(b, num, num2);
    }

    public FrameBodyTPOS(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyNumberTotal, org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TPOS";
    }

    public Integer getDiscNo() {
        return getNumber();
    }

    public String getDiscNoAsText() {
        return getNumberAsText();
    }

    public void setDiscNo(Integer num) {
        setNumber(num);
    }

    public void setDiscNo(String str) {
        setNumber(str);
    }

    public Integer getDiscTotal() {
        return getTotal();
    }

    public String getDiscTotalAsText() {
        return getTotalAsText();
    }

    public void setDiscTotal(Integer num) {
        setTotal(num);
    }

    public void setDiscTotal(String str) {
        setTotal(str);
    }
}
