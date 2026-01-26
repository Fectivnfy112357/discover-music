package org.jaudiotagger.audio.real;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class RealChunk {
    protected static final String CONT = "CONT";
    protected static final String DATA = "DATA";
    protected static final String INDX = "INDX";
    protected static final String MDPR = "MDPR";
    protected static final String PROP = "PROP";
    protected static final String RMF = ".RMF";
    private final byte[] bytes;
    private final String id;
    private final int size;

    public static RealChunk readChunk(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        String string = Utils.readString(randomAccessFile, 4);
        int uint32 = (int) Utils.readUint32(randomAccessFile);
        if (uint32 < 8) {
            throw new CannotReadException("Corrupt file: RealAudio chunk length at position " + (randomAccessFile.getFilePointer() - 4) + " cannot be less than 8");
        }
        if (uint32 > (randomAccessFile.length() - randomAccessFile.getFilePointer()) + 8) {
            throw new CannotReadException("Corrupt file: RealAudio chunk length of " + uint32 + " at position " + (randomAccessFile.getFilePointer() - 4) + " extends beyond the end of the file");
        }
        byte[] bArr = new byte[uint32 - 8];
        randomAccessFile.readFully(bArr);
        return new RealChunk(string, uint32, bArr);
    }

    public RealChunk(String str, int i, byte[] bArr) {
        this.id = str;
        this.size = i;
        this.bytes = bArr;
    }

    public DataInputStream getDataInputStream() {
        return new DataInputStream(new ByteArrayInputStream(getBytes()));
    }

    public boolean isCONT() {
        return CONT.equals(this.id);
    }

    public boolean isPROP() {
        return PROP.equals(this.id);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        return this.id + "\t" + this.size;
    }
}
