package org.jaudiotagger.tag.id3;

import java.util.TreeSet;

/* loaded from: classes3.dex */
public class ID3v2ChapterFrames extends ID3Frames {
    public static final String FRAME_ID_CHAPTER = "CHAP";
    public static final String FRAME_ID_TABLE_OF_CONTENT = "CTOC";
    private static ID3v2ChapterFrames id3v2ChapterFrames;

    public static ID3v2ChapterFrames getInstanceOf() {
        if (id3v2ChapterFrames == null) {
            id3v2ChapterFrames = new ID3v2ChapterFrames();
        }
        return id3v2ChapterFrames;
    }

    private ID3v2ChapterFrames() {
        this.idToValue.put("CHAP", "Chapter");
        this.idToValue.put("CTOC", "Table of content");
        createMaps();
        this.multipleFrames = new TreeSet<>();
        this.discardIfFileAlteredFrames = new TreeSet<>();
    }

    @Override // org.jaudiotagger.tag.id3.ID3Frames
    public void setITunes12_6WorkGroupingMode(boolean z) {
        throw new UnsupportedOperationException();
    }
}
