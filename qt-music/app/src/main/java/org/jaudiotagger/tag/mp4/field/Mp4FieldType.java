package org.jaudiotagger.tag.mp4.field;

import java.util.EnumSet;
import java.util.HashMap;

/* loaded from: classes3.dex */
public enum Mp4FieldType {
    IMPLICIT(0),
    TEXT(1),
    TEXT_UTF16BE(2),
    TEXT_JAPANESE(3),
    HTML(6),
    XML(7),
    GUID(8),
    ISRC(9),
    MI3P(10),
    COVERART_GIF(12),
    COVERART_JPEG(13),
    COVERART_PNG(14),
    URL(15),
    DURATION(16),
    DATETIME(17),
    GENRES(18),
    INTEGER(21),
    RIAAPA(24),
    UPC(25),
    COVERART_BMP(27);

    private static EnumSet<Mp4FieldType> coverArtTypes;
    private static final HashMap<Integer, Mp4FieldType> fileClassIdFiedTypeMap = new HashMap<>(values().length);
    private int fileClassId;

    static {
        for (Mp4FieldType mp4FieldType : values()) {
            fileClassIdFiedTypeMap.put(Integer.valueOf(mp4FieldType.fileClassId), mp4FieldType);
        }
        coverArtTypes = EnumSet.of(COVERART_GIF, COVERART_JPEG, COVERART_PNG, COVERART_BMP);
    }

    Mp4FieldType(int i) {
        this.fileClassId = i;
    }

    public int getFileClassId() {
        return this.fileClassId;
    }

    public static Mp4FieldType getFieldType(int i) {
        return fileClassIdFiedTypeMap.get(Integer.valueOf(i));
    }

    public static boolean isCoverArtType(Mp4FieldType mp4FieldType) {
        return coverArtTypes.contains(mp4FieldType);
    }
}
