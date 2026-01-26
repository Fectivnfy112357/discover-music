package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.StringNullTerminated;

/* loaded from: classes3.dex */
public class FrameBodyUFID extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public static final String UFID_ID3TEST = "http://www.id3.org/dummy/ufid.html";
    public static final String UFID_MUSICBRAINZ = "http://musicbrainz.org";

    public FrameBodyUFID() {
        setOwner("");
        setUniqueIdentifier(new byte[0]);
    }

    public FrameBodyUFID(FrameBodyUFID frameBodyUFID) {
        super(frameBodyUFID);
    }

    public FrameBodyUFID(String str, byte[] bArr) {
        setOwner(str);
        setUniqueIdentifier(bArr);
    }

    public FrameBodyUFID(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "UFID";
    }

    public void setOwner(String str) {
        setObjectValue(DataTypes.OBJ_OWNER, str);
    }

    public String getOwner() {
        return (String) getObjectValue(DataTypes.OBJ_OWNER);
    }

    public void setUniqueIdentifier(byte[] bArr) {
        setObjectValue(DataTypes.OBJ_DATA, bArr);
    }

    public byte[] getUniqueIdentifier() {
        return (byte[]) getObjectValue(DataTypes.OBJ_DATA);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_OWNER, this));
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
