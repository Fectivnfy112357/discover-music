package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.AbstractString;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.StringNullTerminated;
import org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;

/* loaded from: classes3.dex */
public class FrameBodyAPIC extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public static final String IMAGE_IS_URL = "-->";

    public FrameBodyAPIC() {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
    }

    public FrameBodyAPIC(FrameBodyAPIC frameBodyAPIC) {
        super(frameBodyAPIC);
    }

    public FrameBodyAPIC(FrameBodyPIC frameBodyPIC) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(frameBodyPIC.getTextEncoding()));
        setObjectValue(DataTypes.OBJ_MIME_TYPE, ImageFormats.getMimeTypeForFormat((String) frameBodyPIC.getObjectValue(DataTypes.OBJ_IMAGE_FORMAT)));
        setObjectValue(DataTypes.OBJ_PICTURE_TYPE, frameBodyPIC.getObjectValue(DataTypes.OBJ_PICTURE_TYPE));
        setObjectValue(DataTypes.OBJ_DESCRIPTION, frameBodyPIC.getDescription());
        setObjectValue(DataTypes.OBJ_PICTURE_DATA, frameBodyPIC.getObjectValue(DataTypes.OBJ_PICTURE_DATA));
    }

    public FrameBodyAPIC(byte b, String str, byte b2, String str2, byte[] bArr) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setMimeType(str);
        setPictureType(b2);
        setDescription(str2);
        setImageData(bArr);
    }

    public FrameBodyAPIC(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getUserFriendlyValue() {
        if (getImageData() != null) {
            return getMimeType() + ":" + getDescription() + ":" + getImageData().length;
        }
        return getMimeType() + ":" + getDescription() + ":0";
    }

    public void setDescription(String str) {
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str);
    }

    public String getDescription() {
        return (String) getObjectValue(DataTypes.OBJ_DESCRIPTION);
    }

    public void setMimeType(String str) {
        setObjectValue(DataTypes.OBJ_MIME_TYPE, str);
    }

    public String getMimeType() {
        return (String) getObjectValue(DataTypes.OBJ_MIME_TYPE);
    }

    public void setImageData(byte[] bArr) {
        setObjectValue(DataTypes.OBJ_PICTURE_DATA, bArr);
    }

    public byte[] getImageData() {
        return (byte[]) getObjectValue(DataTypes.OBJ_PICTURE_DATA);
    }

    public void setPictureType(byte b) {
        setObjectValue(DataTypes.OBJ_PICTURE_TYPE, Byte.valueOf(b));
    }

    public int getPictureType() {
        return ((Long) getObjectValue(DataTypes.OBJ_PICTURE_TYPE)).intValue();
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "APIC";
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        if (TagOptionSingleton.getInstance().isAPICDescriptionITunesCompatible()) {
            setTextEncoding((byte) 0);
            if (!((AbstractString) getObject(DataTypes.OBJ_DESCRIPTION)).canBeEncoded()) {
                setDescription("");
            }
        } else if (!((AbstractString) getObject(DataTypes.OBJ_DESCRIPTION)).canBeEncoded()) {
            setTextEncoding((byte) 1);
        }
        super.write(byteArrayOutputStream);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_MIME_TYPE, this));
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_PICTURE_TYPE, this, 1));
        this.objectList.add(new TextEncodedStringNullTerminated(DataTypes.OBJ_DESCRIPTION, (AbstractTagFrameBody) this, false));
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_PICTURE_DATA, this));
    }

    public boolean isImageUrl() {
        return getMimeType() != null && getMimeType().equals("-->");
    }

    public String getImageUrl() {
        if (isImageUrl()) {
            return new String((byte[]) getObjectValue(DataTypes.OBJ_PICTURE_DATA), 0, ((byte[]) getObjectValue(DataTypes.OBJ_PICTURE_DATA)).length, StandardCharsets.ISO_8859_1);
        }
        return "";
    }
}
