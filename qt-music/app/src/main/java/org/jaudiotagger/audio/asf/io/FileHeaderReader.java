package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.FileHeader;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class FileHeaderReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_FILE};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected FileHeaderReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        BigInteger big64 = Utils.readBig64(inputStream);
        inputStream.skip(16L);
        FileHeader fileHeader = new FileHeader(big64, Utils.readBig64(inputStream), Utils.readBig64(inputStream), Utils.readBig64(inputStream), Utils.readBig64(inputStream), Utils.readBig64(inputStream), Utils.readBig64(inputStream), Utils.readUINT32(inputStream), Utils.readUINT32(inputStream), Utils.readUINT32(inputStream), Utils.readUINT32(inputStream));
        fileHeader.setPosition(j);
        return fileHeader;
    }
}
