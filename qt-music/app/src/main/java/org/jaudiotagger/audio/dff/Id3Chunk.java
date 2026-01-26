package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: classes3.dex */
public class Id3Chunk extends BaseChunk {
    public Id3Chunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
    }

    public String toString() {
        return DffChunkType.ID3.getCode();
    }
}
