package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public abstract class AiffChunkReader {
    protected ByteBuffer readChunkDataIntoBuffer(FileChannel fileChannel, ChunkHeader chunkHeader) throws IOException {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((int) chunkHeader.getSize());
        byteBufferAllocateDirect.order(ByteOrder.BIG_ENDIAN);
        fileChannel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.position(0);
        return byteBufferAllocateDirect;
    }
}
