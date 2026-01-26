package org.jaudiotagger.audio.flac.metadatablock;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class MetadataBlockDataPadding implements MetadataBlockData {
    private int length;

    public MetadataBlockDataPadding(int i) {
        this.length = i;
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public ByteBuffer getBytes() {
        return ByteBuffer.allocate(this.length);
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public int getLength() {
        return this.length;
    }
}
