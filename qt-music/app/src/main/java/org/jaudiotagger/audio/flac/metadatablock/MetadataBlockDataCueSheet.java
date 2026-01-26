package org.jaudiotagger.audio.flac.metadatablock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: classes3.dex */
public class MetadataBlockDataCueSheet implements MetadataBlockData {
    private ByteBuffer data;

    public MetadataBlockDataCueSheet(MetadataBlockHeader metadataBlockHeader, FileChannel fileChannel) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(metadataBlockHeader.getDataLength());
        this.data = byteBufferAllocate;
        fileChannel.read(byteBufferAllocate);
        this.data.flip();
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public ByteBuffer getBytes() {
        return this.data;
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public int getLength() {
        return this.data.limit();
    }
}
