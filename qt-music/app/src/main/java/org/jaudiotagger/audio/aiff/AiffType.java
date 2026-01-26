package org.jaudiotagger.audio.aiff;

/* loaded from: classes3.dex */
public enum AiffType {
    AIFF("AIFF"),
    AIFC("AIFC");

    String code;

    AiffType(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
