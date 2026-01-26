package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.PartOfSet;

/* loaded from: classes3.dex */
public class FrameBodyTRCK extends AbstractFrameBodyNumberTotal implements ID3v23FrameBody, ID3v24FrameBody {
    public FrameBodyTRCK() {
    }

    public FrameBodyTRCK(FrameBodyTRCK frameBodyTRCK) {
        super(frameBodyTRCK);
    }

    public FrameBodyTRCK(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTRCK(byte b, Integer num, Integer num2) {
        super(b, num, num2);
    }

    public FrameBodyTRCK(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyNumberTotal, org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TRCK";
    }

    public Integer getTrackNo() {
        return getNumber();
    }

    public String getTrackNoAsText() {
        return getNumberAsText();
    }

    public void setTrackNo(Integer num) {
        setNumber(num);
    }

    public void setTrackNo(String str) {
        setNumber(str);
    }

    public Integer getTrackTotal() {
        return getTotal();
    }

    public String getTrackTotalAsText() {
        return getTotalAsText();
    }

    public void setTrackTotal(Integer num) {
        setTotal(num);
    }

    public void setTrackTotal(String str) {
        setTotal(str);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyNumberTotal
    public void setText(String str) {
        setObjectValue(DataTypes.OBJ_TEXT, new PartOfSet.PartOfSetValue(str));
    }
}
