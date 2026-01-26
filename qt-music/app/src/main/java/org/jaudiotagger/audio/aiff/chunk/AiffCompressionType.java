package org.jaudiotagger.audio.aiff.chunk;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public enum AiffCompressionType {
    NONE("NONE", "not compressed", "big-endian", "Apple", true),
    RAW("raw ", "PCM 8-bit", "offset-binary", "Apple", false),
    TWOS("twos", "PCM 16-bit", "twos-complement little-endian", "Apple", false),
    SOWT("sowt", "not compressed", "little-endian", "Apple", true),
    fl32("fl32", "PCM 32-bit", "floating point,", "Apple", false),
    ll64("fl64", "PCM 64-bit", "floating point", "Apple", false),
    IN24("in24", "PCM 24-bit", "integer", "Apple", false),
    IN32("in32", "PCM 32-bit", "integer", "Apple", false),
    alaw("alaw", "Alaw 2:1", "8-bit ITU-T G.711 A-law", "Apple", false),
    ulaw("ulaw", "µlaw 2:1", "8-bit ITU-T G.711 µ-law", "Apple", false),
    MAC3("MAC3", "MACE 3-to-1", "", "Apple", false),
    MAC6("MAC6", "MACE 6-to-1", "", "Apple", false),
    ALAW("ALAW", "CCITT G.711 A-law", "8-bit ITU-T G.711 A-law (64 kbit/s)", "SGI", false),
    ULAW("ULAW", "CCITT G.711 u-law", "8-bit ITU-T G.711 A-law (64 kbit/s)", "SGI", false),
    FL32("FL32", "Float 32", "IEEE 32-bit float", "SoundHack & Csound", false),
    rt24("rt24", "RT24 50:1", "", "Voxware", false),
    rt29("rt29", "RT29 50:1", "", "Voxware", false);

    private static final Map<String, AiffCompressionType> lookup = new HashMap();
    private final String code;
    private final String compression;
    private final String dataType;
    private final boolean isLossless;
    private final String provider;

    static {
        for (AiffCompressionType aiffCompressionType : values()) {
            lookup.put(aiffCompressionType.getCode(), aiffCompressionType);
        }
    }

    AiffCompressionType(String str, String str2, String str3, String str4, boolean z) {
        this.code = str;
        this.compression = str2;
        this.dataType = str3;
        this.provider = str4;
        this.isLossless = z;
    }

    public String getCode() {
        return this.code;
    }

    public String getCompression() {
        return this.compression;
    }

    public boolean isLossless() {
        return this.isLossless;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getProvider() {
        return this.provider;
    }

    public static AiffCompressionType getByCode(String str) {
        return lookup.get(str);
    }
}
