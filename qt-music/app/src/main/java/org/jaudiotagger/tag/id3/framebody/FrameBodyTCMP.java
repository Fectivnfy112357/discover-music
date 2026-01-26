package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;

/* loaded from: classes3.dex */
public class FrameBodyTCMP extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    static String IS_COMPILATION = "1\u0000";

    public FrameBodyTCMP() {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
        setObjectValue(DataTypes.OBJ_TEXT, IS_COMPILATION);
    }

    public FrameBodyTCMP(FrameBodyTCMP frameBodyTCMP) {
        super(frameBodyTCMP);
    }

    public FrameBodyTCMP(byte b, String str) {
        super(b, str);
    }

    public boolean isCompilation() {
        return getText().equals(IS_COMPILATION);
    }

    public FrameBodyTCMP(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TCMP";
    }
}
