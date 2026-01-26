package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.StringFixedLength;
import org.jaudiotagger.tag.datatype.StringNullTerminated;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FrameBodyLINK extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyLINK() {
    }

    public FrameBodyLINK(FrameBodyLINK frameBodyLINK) {
        super(frameBodyLINK);
    }

    public FrameBodyLINK(String str, String str2, String str3) {
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str);
        setObjectValue(DataTypes.OBJ_URL, str2);
        setObjectValue(DataTypes.OBJ_ID, str3);
    }

    public FrameBodyLINK(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public String getAdditionalData() {
        return (String) getObjectValue(DataTypes.OBJ_ID);
    }

    public void getAdditionalData(String str) {
        setObjectValue(DataTypes.OBJ_ID, str);
    }

    public String getFrameIdentifier() {
        return (String) getObjectValue(DataTypes.OBJ_DESCRIPTION);
    }

    public void getFrameIdentifier(String str) {
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "LINK";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringFixedLength(DataTypes.OBJ_DESCRIPTION, this, 4));
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_URL, this));
        this.objectList.add(new StringSizeTerminated(DataTypes.OBJ_ID, this));
    }
}
