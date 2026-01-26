package org.jaudiotagger.audio.wav;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public enum WavSubFormat {
    FORMAT_PCM(1, "WAV PCM"),
    FORMAT_FLOAT(3, "WAV IEEE_FLOAT"),
    FORMAT_ALAW(6, "WAV A-LAW"),
    FORMAT_MULAW(7, "WAV µ-LAW"),
    FORMAT_EXTENSIBLE(65534, "EXTENSIBLE"),
    FORMAT_GSM_COMPRESSED(49, "GSM_COMPRESSED");

    private static final Map<Integer, WavSubFormat> lookup = new HashMap();
    private int code;
    private String description;

    static {
        for (WavSubFormat wavSubFormat : values()) {
            lookup.put(Integer.valueOf(wavSubFormat.getCode()), wavSubFormat);
        }
    }

    WavSubFormat(int i, String str) {
        this.code = i;
        this.description = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static WavSubFormat getByCode(Integer num) {
        return lookup.get(num);
    }
}
