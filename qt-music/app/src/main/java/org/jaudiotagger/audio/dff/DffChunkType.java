package org.jaudiotagger.audio.dff;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public enum DffChunkType {
    FRM8("FRM8"),
    DSD("DSD "),
    PROP("PROP"),
    SND("SND "),
    FS("FS  "),
    CHNL("CHNL"),
    CMPR("CMPR"),
    DITI("DITI"),
    END("DSD "),
    DST("DST "),
    FRTE("FRTE"),
    ID3("ID3 "),
    DATA("data");

    private static final Map<String, DffChunkType> CODE_TYPE_MAP = new HashMap();
    private String code;

    DffChunkType(String str) {
        this.code = str;
    }

    public static synchronized DffChunkType get(String str) {
        if (CODE_TYPE_MAP.isEmpty()) {
            for (DffChunkType dffChunkType : values()) {
                CODE_TYPE_MAP.put(dffChunkType.getCode(), dffChunkType);
            }
        }
        return CODE_TYPE_MAP.get(str);
    }

    public String getCode() {
        return this.code;
    }
}
