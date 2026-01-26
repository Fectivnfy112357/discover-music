package org.jaudiotagger.audio.wav;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public enum WavChunkType {
    FORMAT("fmt ", "Basic Audio Information"),
    FACT("fact", "Only strictly required for Non-PCM or compressed data"),
    DATA("data", "Stores the actual audio data"),
    LIST("LIST", "List chunk, wraps round other chunks"),
    INFO("INFO", "Original metadata implementation"),
    ID3("id3 ", "Stores metadata in ID3 chunk"),
    JUNK("JUNK", "Junk Data"),
    PAD("PAD ", "Official Padding Data"),
    IXML("iXML", "Location Sound Metadata"),
    BRDK("BRDK", "BRDK"),
    ID3_UPPERCASE("ID3 ", "Stores metadata in ID3 chunk, should be lowercase id");

    private static final Map<String, WavChunkType> CODE_TYPE_MAP = new HashMap();
    private String code;

    public static synchronized WavChunkType get(String str) {
        if (CODE_TYPE_MAP.isEmpty()) {
            for (WavChunkType wavChunkType : values()) {
                CODE_TYPE_MAP.put(wavChunkType.getCode(), wavChunkType);
            }
        }
        return CODE_TYPE_MAP.get(str);
    }

    WavChunkType(String str, String str2) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
