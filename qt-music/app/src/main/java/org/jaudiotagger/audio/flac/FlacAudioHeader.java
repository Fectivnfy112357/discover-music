package org.jaudiotagger.audio.flac;

import org.jaudiotagger.audio.generic.GenericAudioHeader;

/* loaded from: classes3.dex */
public class FlacAudioHeader extends GenericAudioHeader {
    private String md5;

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }
}
