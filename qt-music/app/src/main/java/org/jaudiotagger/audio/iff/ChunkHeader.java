package org.jaudiotagger.audio.iff;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class ChunkHeader {
    public static final int CHUNK_HEADER_SIZE = 8;
    private ByteOrder byteOrder;
    private String chunkId;
    private long size;
    private long startLocationInFile;

    public ChunkHeader(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public boolean readHeader(FileChannel fileChannel) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        this.startLocationInFile = fileChannel.position();
        fileChannel.read(byteBufferAllocate);
        byteBufferAllocate.order(this.byteOrder);
        byteBufferAllocate.position(0);
        this.chunkId = Utils.readFourBytesAsChars(byteBufferAllocate);
        this.size = ChunkHeader$$ExternalSyntheticBackport0.m(byteBufferAllocate.getInt());
        return true;
    }

    public boolean readHeader(RandomAccessFile randomAccessFile) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        this.startLocationInFile = randomAccessFile.getFilePointer();
        randomAccessFile.getChannel().read(byteBufferAllocate);
        byteBufferAllocate.order(this.byteOrder);
        byteBufferAllocate.position(0);
        this.chunkId = Utils.readFourBytesAsChars(byteBufferAllocate);
        this.size = byteBufferAllocate.getInt();
        return true;
    }

    public ByteBuffer writeHeader() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(this.byteOrder);
        byteBufferAllocate.put(this.chunkId.getBytes(StandardCharsets.US_ASCII));
        byteBufferAllocate.putInt((int) this.size);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    public void setID(String str) {
        this.chunkId = str;
    }

    public String getID() {
        return this.chunkId;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public long getStartLocationInFile() {
        return this.startLocationInFile;
    }

    public String toString() {
        return getID() + ":Size:" + getSize() + "startLocation:" + getStartLocationInFile();
    }
}
