package org.jaudiotagger.tag.id3.valuepair;

/* loaded from: classes3.dex */
public enum ID3V2ExtendedGenreTypes {
    RX("Remix"),
    CR("Cover");

    private String description;

    ID3V2ExtendedGenreTypes(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }
}
