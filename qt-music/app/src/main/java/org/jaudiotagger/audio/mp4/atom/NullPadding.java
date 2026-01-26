package org.jaudiotagger.audio.mp4.atom;

/* loaded from: classes3.dex */
public class NullPadding extends Mp4BoxHeader {
    public NullPadding(long j, long j2) {
        setFilePos(j);
        this.length = (int) (j2 - j);
    }
}
