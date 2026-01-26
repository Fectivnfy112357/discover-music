package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.AbstractString;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;
import org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated;
import org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class FrameBodyWXXX extends AbstractFrameBodyUrlLink implements ID3v24FrameBody, ID3v23FrameBody {
    public static final String URL_BANDCAMP_ARTIST_SITE = "BANDCAMP_ARTIST";
    public static final String URL_BANDCAMP_RELEASE_SITE = "BANDCAMP_RELEASE";
    public static final String URL_DISCOGS_ARTIST_SITE = "DISCOGS_ARTIST";
    public static final String URL_DISCOGS_RELEASE_SITE = "DISCOGS_RELEASE";
    public static final String URL_LYRICS_SITE = "LYRICS_SITE";
    public static final String URL_OFFICIAL_RELEASE_SITE = "OFFICIAL_RELEASE";
    public static final String URL_WIKIPEDIA_ARTIST_SITE = "WIKIPEDIA_ARTIST";
    public static final String URL_WIKIPEDIA_RELEASE_SITE = "WIKIPEDIA_RELEASE";

    public FrameBodyWXXX() {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
        setObjectValue(DataTypes.OBJ_DESCRIPTION, "");
        setObjectValue(DataTypes.OBJ_URLLINK, "");
    }

    public FrameBodyWXXX(FrameBodyWXXX frameBodyWXXX) {
        super(frameBodyWXXX);
    }

    public FrameBodyWXXX(byte b, String str, String str2) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str);
        setObjectValue(DataTypes.OBJ_URLLINK, str2);
    }

    public FrameBodyWXXX(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public void setDescription(String str) {
        setObjectValue(DataTypes.OBJ_DESCRIPTION, str);
    }

    public String getDescription() {
        return (String) getObjectValue(DataTypes.OBJ_DESCRIPTION);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "WXXX";
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyUrlLink, org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        if (!((AbstractString) getObject(DataTypes.OBJ_DESCRIPTION)).canBeEncoded()) {
            setTextEncoding((byte) 1);
        }
        super.write(byteArrayOutputStream);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyUrlLink, org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new TextEncodedStringNullTerminated(DataTypes.OBJ_DESCRIPTION, (AbstractTagFrameBody) this, false));
        this.objectList.add(new StringSizeTerminated(DataTypes.OBJ_URLLINK, this));
    }

    public String getUrlLinkWithoutTrailingNulls() {
        return ((TextEncodedStringSizeTerminated) getObject(DataTypes.OBJ_URLLINK)).getValueWithoutTrailingNull();
    }

    public String getFirstUrlLink() {
        return ((TextEncodedStringSizeTerminated) getObject(DataTypes.OBJ_URLLINK)).getValueAtIndex(0);
    }

    public String getUrlLinkAtIndex(int i) {
        return ((TextEncodedStringSizeTerminated) getObject(DataTypes.OBJ_URLLINK)).getValueAtIndex(i);
    }

    public List<String> getUrlLinks() {
        return ((TextEncodedStringSizeTerminated) getObject(DataTypes.OBJ_URLLINK)).getValues();
    }

    public void addUrlLink(String str) {
        ((TextEncodedStringSizeTerminated) getObject(DataTypes.OBJ_URLLINK)).addValue(str);
    }
}
