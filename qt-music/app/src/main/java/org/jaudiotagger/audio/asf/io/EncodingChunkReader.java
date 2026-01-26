package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.EncodingChunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
class EncodingChunkReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_ENCODING};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected EncodingChunkReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        BigInteger big64 = Utils.readBig64(inputStream);
        EncodingChunk encodingChunk = new EncodingChunk(big64);
        inputStream.skip(20L);
        int uint16 = Utils.readUINT16(inputStream);
        int length = 46;
        for (int i = 0; i < uint16; i++) {
            String characterSizedString = Utils.readCharacterSizedString(inputStream);
            encodingChunk.addString(characterSizedString);
            length += (characterSizedString.length() * 2) + 4;
        }
        inputStream.skip(big64.longValue() - length);
        encodingChunk.setPosition(j);
        return encodingChunk;
    }
}
