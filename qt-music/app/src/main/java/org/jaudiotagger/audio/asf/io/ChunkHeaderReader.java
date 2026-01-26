package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
final class ChunkHeaderReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_UNSPECIFIED};
    private static final ChunkHeaderReader INSTANCE = new ChunkHeaderReader();

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    public static ChunkHeaderReader getInstance() {
        return INSTANCE;
    }

    private ChunkHeaderReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        BigInteger big64 = Utils.readBig64(inputStream);
        inputStream.skip(big64.longValue() - 24);
        return new Chunk(guid, j, big64);
    }
}
