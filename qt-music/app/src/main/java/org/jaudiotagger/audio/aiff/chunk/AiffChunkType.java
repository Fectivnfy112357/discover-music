package org.jaudiotagger.audio.aiff.chunk;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public enum AiffChunkType {
    FORMAT_VERSION("FVER"),
    APPLICATION("APPL"),
    SOUND("SSND"),
    COMMON("COMM"),
    COMMENTS("COMT"),
    NAME("NAME"),
    AUTHOR("AUTH"),
    COPYRIGHT("(c) "),
    ANNOTATION("ANNO"),
    TAG("ID3 "),
    CORRUPT_TAG_LATE("D3 \u0000"),
    CORRUPT_TAG_EARLY("\u0000ID3");

    private static final Map<String, AiffChunkType> CODE_TYPE_MAP = new HashMap();
    private String code;

    AiffChunkType(String str) {
        this.code = str;
    }

    public static synchronized AiffChunkType get(String str) {
        if (CODE_TYPE_MAP.isEmpty()) {
            for (AiffChunkType aiffChunkType : values()) {
                CODE_TYPE_MAP.put(aiffChunkType.getCode(), aiffChunkType);
            }
        }
        return CODE_TYPE_MAP.get(str);
    }

    public String getCode() {
        return this.code;
    }
}
