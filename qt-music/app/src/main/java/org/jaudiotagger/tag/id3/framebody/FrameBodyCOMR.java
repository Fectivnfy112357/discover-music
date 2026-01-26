package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.AbstractString;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.StringDate;
import org.jaudiotagger.tag.datatype.StringNullTerminated;
import org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class FrameBodyCOMR extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyCOMR() {
    }

    public FrameBodyCOMR(FrameBodyCOMR frameBodyCOMR) {
        super(frameBodyCOMR);
    }

    public FrameBodyCOMR(byte b, String str, String str2, String str3, byte b2, String str4, String str5, String str6, byte[] bArr) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_PRICE_STRING, str);
        setObjectValue(DataTypes.OBJ_VALID_UNTIL, str2);
        setObjectValue(DataTypes.OBJ_CONTACT_URL, str3);
        setObjectValue(DataTypes.OBJ_RECIEVED_AS, Byte.valueOf(b2));
        setObjectValue(DataTypes.OBJ_SELLER_NAME, str4);
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str5);
        setObjectValue(DataTypes.OBJ_MIME_TYPE, str6);
        setObjectValue(DataTypes.OBJ_SELLER_LOGO, bArr);
    }

    public FrameBodyCOMR(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "COMR";
    }

    public String getOwner() {
        return (String) getObjectValue(DataTypes.OBJ_OWNER);
    }

    public void getOwner(String str) {
        setObjectValue(DataTypes.OBJ_OWNER, str);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        if (!((AbstractString) getObject(DataTypes.OBJ_SELLER_NAME)).canBeEncoded()) {
            setTextEncoding((byte) 1);
        }
        if (!((AbstractString) getObject(DataTypes.OBJ_DESCRIPTION)).canBeEncoded()) {
            setTextEncoding((byte) 1);
        }
        super.write(byteArrayOutputStream);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_PRICE_STRING, this));
        this.objectList.add(new StringDate(DataTypes.OBJ_VALID_UNTIL, this));
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_CONTACT_URL, this));
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_RECIEVED_AS, this, 1));
        this.objectList.add(new TextEncodedStringNullTerminated(DataTypes.OBJ_SELLER_NAME, (AbstractTagFrameBody) this, false));
        this.objectList.add(new TextEncodedStringNullTerminated(DataTypes.OBJ_DESCRIPTION, (AbstractTagFrameBody) this, false));
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_MIME_TYPE, this));
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_SELLER_LOGO, this));
    }
}
