package org.jaudiotagger.audio.ogg.util;

/* loaded from: classes3.dex */
public enum VorbisPacketType {
    AUDIO(0),
    IDENTIFICATION_HEADER(1),
    COMMENT_HEADER(3),
    SETUP_HEADER(5);

    int type;

    VorbisPacketType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
