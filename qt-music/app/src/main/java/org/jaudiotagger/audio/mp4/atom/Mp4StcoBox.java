package org.jaudiotagger.audio.mp4.atom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.Mp4AtomIdentifier;

/* loaded from: classes3.dex */
public class Mp4StcoBox extends AbstractMp4Box {
    public static final int NO_OF_OFFSETS_LENGTH = 4;
    public static final int NO_OF_OFFSETS_POS = 4;
    public static final int OFFSET_LENGTH = 4;
    public static final int OTHER_FLAG_LENGTH = 3;
    public static final int OTHER_FLAG_POS = 1;
    public static final int VERSION_FLAG_LENGTH = 1;
    public static final int VERSION_FLAG_POS = 0;
    private int firstOffSet;
    private int noOfOffSets;

    public Mp4StcoBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.noOfOffSets = 0;
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer.slice();
        this.dataBuffer.order(ByteOrder.BIG_ENDIAN);
        this.dataBuffer.position(this.dataBuffer.position() + 4);
        this.noOfOffSets = this.dataBuffer.getInt();
        this.firstOffSet = this.dataBuffer.getInt();
    }

    public void printTotalOffset() {
        this.dataBuffer.rewind();
        this.dataBuffer.position(8);
        int intBE = 0;
        for (int i = 0; i < this.noOfOffSets - 1; i++) {
            intBE += Utils.getIntBE(this.dataBuffer, this.dataBuffer.position(), this.dataBuffer.position() + 3);
            this.dataBuffer.position(this.dataBuffer.position() + 4);
        }
        System.out.println("Print Offset Total:" + (intBE + Utils.getIntBE(this.dataBuffer, this.dataBuffer.position(), this.dataBuffer.position() + 3)));
    }

    public void printAllOffsets() {
        System.out.println("Print Offsets:start");
        this.dataBuffer.rewind();
        this.dataBuffer.position(8);
        for (int i = 0; i < this.noOfOffSets - 1; i++) {
            System.out.println("offset into audio data is:" + this.dataBuffer.getInt());
        }
        System.out.println("offset into audio data is:" + this.dataBuffer.getInt());
        System.out.println("Print Offsets:end");
    }

    public void adjustOffsets(int i) {
        this.dataBuffer.rewind();
        this.dataBuffer.position(this.dataBuffer.position() + 8);
        for (int i2 = 0; i2 < this.noOfOffSets; i2++) {
            int i3 = this.dataBuffer.getInt() + i;
            this.dataBuffer.position(this.dataBuffer.position() - 4);
            this.dataBuffer.putInt(i3);
        }
    }

    public Mp4StcoBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer, int i) {
        this.noOfOffSets = 0;
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer.slice();
        this.dataBuffer.position(this.dataBuffer.position() + 4);
        this.noOfOffSets = Utils.getIntBE(this.dataBuffer, this.dataBuffer.position(), this.dataBuffer.position() + 3);
        this.dataBuffer.position(this.dataBuffer.position() + 4);
        for (int i2 = 0; i2 < this.noOfOffSets; i2++) {
            this.dataBuffer.put(Utils.getSizeBEInt32(Utils.getIntBE(this.dataBuffer, this.dataBuffer.position(), this.dataBuffer.position() + 3) + i));
        }
    }

    public int getNoOfOffSets() {
        return this.noOfOffSets;
    }

    public int getFirstOffSet() {
        return this.firstOffSet;
    }

    public static Mp4StcoBox getStco(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        FileChannel channel = randomAccessFile.getChannel();
        if (Mp4BoxHeader.seekWithinLevel(channel, Mp4AtomIdentifier.MOOV.getFieldName()) == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(r0.getLength() - 8);
        channel.read(byteBufferAllocate);
        byteBufferAllocate.rewind();
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.MVHD.getFieldName());
        if (mp4BoxHeaderSeekWithinLevel == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        ByteBuffer byteBufferSlice = byteBufferAllocate.slice();
        byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel.getDataLength());
        if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.TRAK.getFieldName()) == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MDIA.getFieldName()) == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel2 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MDHD.getFieldName());
        if (mp4BoxHeaderSeekWithinLevel2 == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel2.getDataLength());
        if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MINF.getFieldName()) == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel3 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.SMHD.getFieldName());
        if (mp4BoxHeaderSeekWithinLevel3 == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel3.getDataLength());
        if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.STBL.getFieldName()) == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel4 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.STCO.getFieldName());
        if (mp4BoxHeaderSeekWithinLevel4 == null) {
            throw new CannotReadException("This file does not appear to be an audio file");
        }
        return new Mp4StcoBox(mp4BoxHeaderSeekWithinLevel4, byteBufferSlice);
    }

    public static void debugShowStcoInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        getStco(randomAccessFile).printAllOffsets();
    }
}
