package org.jaudiotagger.audio.ogg;

/* loaded from: classes3.dex */
public enum VorbisVersion {
    VERSION_ONE("Ogg Vorbis v1");

    private String displayName;

    VorbisVersion(String str) {
        this.displayName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.displayName;
    }
}
