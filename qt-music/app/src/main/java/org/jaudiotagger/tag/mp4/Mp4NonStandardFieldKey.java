package org.jaudiotagger.tag.mp4;

import org.jaudiotagger.tag.reference.Tagger;

/* loaded from: classes3.dex */
public enum Mp4NonStandardFieldKey {
    AAPR("AApr", "MM3 Album Art Attributes", Tagger.MEDIA_MONKEY),
    ALFN("Alfn", "MM3 Album Art Unknown", Tagger.MEDIA_MONKEY),
    AMIM("AMIM", "MM3 Album Art MimeType", Tagger.MEDIA_MONKEY),
    ADCP("Adcp", "MM3 Album Art Description", Tagger.MEDIA_MONKEY),
    APTY("Apty", "MM3 Album Art ID3 Picture Type", Tagger.MEDIA_MONKEY);

    private String description;
    private String fieldName;
    private Tagger tagger;

    Mp4NonStandardFieldKey(String str, String str2, Tagger tagger) {
        this.fieldName = str;
        this.description = str2;
        this.tagger = tagger;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getDescription() {
        return this.description;
    }

    public Tagger geTagger() {
        return this.tagger;
    }
}
