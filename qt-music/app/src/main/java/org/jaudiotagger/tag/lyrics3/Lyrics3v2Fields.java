package org.jaudiotagger.tag.lyrics3;

import org.jaudiotagger.tag.datatype.AbstractStringStringValuePair;

/* loaded from: classes3.dex */
public class Lyrics3v2Fields extends AbstractStringStringValuePair {
    public static final String CRLF;
    public static final String FIELD_V2_ADDITIONAL_MULTI_LINE_TEXT = "INF";
    public static final String FIELD_V2_ALBUM = "EAL";
    public static final String FIELD_V2_ARTIST = "EAR";
    public static final String FIELD_V2_AUTHOR = "AUT";
    public static final String FIELD_V2_IMAGE = "IMG";
    public static final String FIELD_V2_INDICATIONS = "IND";
    public static final String FIELD_V2_LYRICS_MULTI_LINE_TEXT = "LYR";
    public static final String FIELD_V2_TRACK = "ETT";
    private static final byte[] crlfByte;
    private static Lyrics3v2Fields lyrics3Fields;

    static {
        byte[] bArr = {13, 10};
        crlfByte = bArr;
        CRLF = new String(bArr);
    }

    public static Lyrics3v2Fields getInstanceOf() {
        if (lyrics3Fields == null) {
            lyrics3Fields = new Lyrics3v2Fields();
        }
        return lyrics3Fields;
    }

    private Lyrics3v2Fields() {
        this.idToValue.put(FIELD_V2_INDICATIONS, "Indications field");
        this.idToValue.put(FIELD_V2_LYRICS_MULTI_LINE_TEXT, "Lyrics multi line text");
        this.idToValue.put(FIELD_V2_ADDITIONAL_MULTI_LINE_TEXT, "Additional information multi line text");
        this.idToValue.put(FIELD_V2_AUTHOR, "Lyrics/Music Author name");
        this.idToValue.put(FIELD_V2_ALBUM, "Extended Album name");
        this.idToValue.put(FIELD_V2_ARTIST, "Extended Artist name");
        this.idToValue.put(FIELD_V2_TRACK, "Extended Track Title");
        this.idToValue.put(FIELD_V2_IMAGE, "Link to an image files");
        createMaps();
    }

    public static boolean isLyrics3v2FieldIdentifier(String str) {
        return str.length() >= 3 && getInstanceOf().getIdToValueMap().containsKey(str.substring(0, 3));
    }
}
