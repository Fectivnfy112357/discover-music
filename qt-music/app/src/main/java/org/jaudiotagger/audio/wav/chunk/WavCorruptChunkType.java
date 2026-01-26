package org.jaudiotagger.audio.wav.chunk;

/* loaded from: classes3.dex */
public enum WavCorruptChunkType {
    CORRUPT_ID3_EARLY("id3"),
    CORRUPT_ID3_LATE("d3 "),
    CORRUPT_LIST_EARLY("LIS"),
    CORRUPT_LIST_LATE("IST");

    private String code;

    WavCorruptChunkType(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
