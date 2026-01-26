package org.jaudiotagger.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import org.jaudiotagger.tag.TagOptionSingleton;

/* loaded from: classes3.dex */
public class ShiftData {
    public static void shiftDataByOffsetToMakeSpace(SeekableByteChannel seekableByteChannel, int i) throws IOException {
        long size = seekableByteChannel.size();
        long jPosition = seekableByteChannel.position();
        long size2 = seekableByteChannel.size() - jPosition;
        int writeChunkSize = (int) TagOptionSingleton.getInstance().getWriteChunkSize();
        long j = writeChunkSize;
        long j2 = size2 / j;
        long j3 = size2 % j;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(writeChunkSize);
        long j4 = i;
        long size3 = seekableByteChannel.size() - j;
        long size4 = (seekableByteChannel.size() - j) + j4;
        for (int i2 = 0; i2 < j2; i2++) {
            seekableByteChannel.position(size3);
            seekableByteChannel.read(byteBufferAllocate);
            byteBufferAllocate.flip();
            seekableByteChannel.position(size4);
            seekableByteChannel.write(byteBufferAllocate);
            byteBufferAllocate.rewind();
            size3 -= j;
            size4 -= j;
        }
        if (j3 > 0) {
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) j3);
            seekableByteChannel.position(jPosition);
            seekableByteChannel.read(byteBufferAllocate2);
            byteBufferAllocate2.flip();
            seekableByteChannel.position(jPosition + j4);
            seekableByteChannel.write(byteBufferAllocate2);
        }
        if (!(seekableByteChannel instanceof SeekableByteChannel) || i >= 0) {
            return;
        }
        seekableByteChannel.truncate(size + j4);
    }

    public static void shiftDataByOffsetToShrinkSpace(SeekableByteChannel seekableByteChannel, int i) throws IOException {
        long jPosition = seekableByteChannel.position();
        long size = seekableByteChannel.size() - jPosition;
        int writeChunkSize = (int) TagOptionSingleton.getInstance().getWriteChunkSize();
        long j = writeChunkSize;
        long j2 = size / j;
        long j3 = size % j;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(writeChunkSize);
        long j4 = jPosition - i;
        for (int i2 = 0; i2 < j2; i2++) {
            seekableByteChannel.position(jPosition);
            seekableByteChannel.read(byteBufferAllocate);
            byteBufferAllocate.flip();
            seekableByteChannel.position(j4);
            seekableByteChannel.write(byteBufferAllocate);
            byteBufferAllocate.rewind();
            jPosition += j;
            j4 += j;
        }
        if (j3 > 0) {
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) j3);
            seekableByteChannel.position(jPosition);
            seekableByteChannel.read(byteBufferAllocate2);
            byteBufferAllocate2.flip();
            seekableByteChannel.position(j4);
            seekableByteChannel.write(byteBufferAllocate2);
        }
        if (seekableByteChannel instanceof SeekableByteChannel) {
            seekableByteChannel.truncate(seekableByteChannel.position());
        }
    }
}
