package org.jaudiotagger.tag.mp4.atom;

/* loaded from: classes3.dex */
public enum Mp4RatingValue {
    CLEAN("Clean", 2),
    EXPLICIT("Explicit", 4);

    private String description;
    private int id;

    Mp4RatingValue(String str, int i) {
        this.description = str;
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
}
