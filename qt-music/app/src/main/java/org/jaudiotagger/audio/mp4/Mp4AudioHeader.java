package org.jaudiotagger.audio.mp4;

import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.mp4.atom.Mp4EsdsBox;

/* loaded from: classes3.dex */
public class Mp4AudioHeader extends GenericAudioHeader {
    private String brand;
    private Mp4EsdsBox.Kind kind;
    private Mp4EsdsBox.AudioProfile profile;

    public void setKind(Mp4EsdsBox.Kind kind) {
        this.kind = kind;
    }

    public Mp4EsdsBox.Kind getKind() {
        return this.kind;
    }

    public void setProfile(Mp4EsdsBox.AudioProfile audioProfile) {
        this.profile = audioProfile;
    }

    public Mp4EsdsBox.AudioProfile getProfile() {
        return this.profile;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getBrand() {
        return this.brand;
    }
}
