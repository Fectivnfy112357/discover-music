package org.jaudiotagger.audio.flac.metadatablock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class MetadataBlockHeader {
    public static final int BLOCK_LENGTH = 3;
    public static final int BLOCK_TYPE_LENGTH = 1;
    public static final int HEADER_LENGTH = 4;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");
    private BlockType blockType;
    private byte[] bytes;
    private int dataLength;
    private boolean isLastBlock;
    private long startByte;

    private int u(int i) {
        return i & 255;
    }

    public static MetadataBlockHeader readHeader(FileChannel fileChannel) throws CannotReadException, IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        long jPosition = fileChannel.position();
        int i = fileChannel.read(byteBufferAllocate);
        if (i < 4) {
            throw new IOException("Unable to read required number of databytes read:" + i + ":required:4");
        }
        byteBufferAllocate.rewind();
        return new MetadataBlockHeader(jPosition, byteBufferAllocate);
    }

    public String toString() {
        return String.format("StartByte:%d BlockType:%s DataLength:%d isLastBlock:%s", Long.valueOf(this.startByte), this.blockType, Integer.valueOf(this.dataLength), Boolean.valueOf(this.isLastBlock));
    }

    public MetadataBlockHeader(long j, ByteBuffer byteBuffer) throws CannotReadException {
        this.startByte = j;
        this.isLastBlock = ((byteBuffer.get(0) & 128) >>> 7) == 1;
        int i = byteBuffer.get(0) & 127;
        if (i < BlockType.values().length) {
            this.blockType = BlockType.values()[i];
            this.dataLength = (u(byteBuffer.get(1)) << 16) + (u(byteBuffer.get(2)) << 8) + u(byteBuffer.get(3));
            this.bytes = new byte[4];
            for (int i2 = 0; i2 < 4; i2++) {
                this.bytes[i2] = byteBuffer.get(i2);
            }
            return;
        }
        throw new CannotReadException(ErrorMessage.FLAC_NO_BLOCKTYPE.getMsg(Integer.valueOf(i)));
    }

    public MetadataBlockHeader(boolean z, BlockType blockType, int i) {
        int id;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        this.blockType = blockType;
        this.isLastBlock = z;
        this.dataLength = i;
        if (z) {
            id = blockType.getId() | 128;
        } else {
            id = blockType.getId();
        }
        byteBufferAllocate.put((byte) id);
        byteBufferAllocate.put((byte) ((16711680 & i) >>> 16));
        byteBufferAllocate.put((byte) ((65280 & i) >>> 8));
        byteBufferAllocate.put((byte) (i & 255));
        this.bytes = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            this.bytes[i2] = byteBufferAllocate.get(i2);
        }
    }

    public int getDataLength() {
        return this.dataLength;
    }

    public BlockType getBlockType() {
        return this.blockType;
    }

    public boolean isLastBlock() {
        return this.isLastBlock;
    }

    public byte[] getBytesWithoutIsLastBlockFlag() {
        byte[] bArr = this.bytes;
        bArr[0] = (byte) (bArr[0] & 127);
        return bArr;
    }

    public byte[] getBytesWithLastBlockFlag() {
        byte[] bArr = this.bytes;
        bArr[0] = (byte) (bArr[0] | 128);
        return bArr;
    }

    public byte[] getBytes() {
        return this.bytes;
    }
}
