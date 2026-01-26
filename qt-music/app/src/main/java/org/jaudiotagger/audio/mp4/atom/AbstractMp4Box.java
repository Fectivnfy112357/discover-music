package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class AbstractMp4Box {
    protected ByteBuffer dataBuffer;
    protected Mp4BoxHeader header;

    public Mp4BoxHeader getHeader() {
        return this.header;
    }

    public ByteBuffer getData() {
        return this.dataBuffer;
    }
}
