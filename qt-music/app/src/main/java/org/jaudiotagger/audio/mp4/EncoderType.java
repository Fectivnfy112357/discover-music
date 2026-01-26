package org.jaudiotagger.audio.mp4;

/* loaded from: classes3.dex */
public enum EncoderType {
    AAC("Aac"),
    DRM_AAC("Aac (Drm)"),
    APPLE_LOSSLESS("Alac");

    private String description;

    EncoderType(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }
}
