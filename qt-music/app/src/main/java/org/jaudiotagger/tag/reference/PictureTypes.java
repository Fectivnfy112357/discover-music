package org.jaudiotagger.tag.reference;

import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class PictureTypes extends AbstractIntStringValuePair {
    public static final Integer DEFAULT_ID = 3;
    public static final String DEFAULT_VALUE = "Cover (front)";
    public static final int PICTURE_TYPE_FIELD_SIZE = 1;
    private static PictureTypes pictureTypes;

    public static PictureTypes getInstanceOf() {
        if (pictureTypes == null) {
            pictureTypes = new PictureTypes();
        }
        return pictureTypes;
    }

    private PictureTypes() {
        this.idToValue.put(0, "Other");
        this.idToValue.put(1, "32x32 pixels 'file icon' (PNG only)");
        this.idToValue.put(2, "Other file icon");
        this.idToValue.put(3, DEFAULT_VALUE);
        this.idToValue.put(4, "Cover (back)");
        this.idToValue.put(5, "Leaflet page");
        this.idToValue.put(6, "Media (e.g. label side of CD)");
        this.idToValue.put(7, "Lead artist/lead performer/soloist");
        this.idToValue.put(8, "Artist/performer");
        this.idToValue.put(9, "Conductor");
        this.idToValue.put(10, "Band/Orchestra");
        this.idToValue.put(11, "Composer");
        this.idToValue.put(12, "Lyricist/text writer");
        this.idToValue.put(13, "Recording Location");
        this.idToValue.put(14, "During recording");
        this.idToValue.put(15, "During performance");
        this.idToValue.put(16, "Movie/video screen capture");
        this.idToValue.put(17, "A bright coloured fish");
        this.idToValue.put(18, "Illustration");
        this.idToValue.put(19, "Band/artist logotype");
        this.idToValue.put(20, "Publisher/Studio logotype");
        createMaps();
    }
}
