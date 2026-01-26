package org.jaudiotagger.tag.aiff;

/* loaded from: classes3.dex */
public enum AiffTagFieldKey {
    TIMESTAMP("TIMESTAMP");

    private String fieldName;

    AiffTagFieldKey(String str) {
        this.fieldName = str;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
