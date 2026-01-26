package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.data.StreamBitratePropertiesChunk;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class StreamBitratePropertiesReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_STREAM_BITRATE_PROPERTIES};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected StreamBitratePropertiesReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        StreamBitratePropertiesChunk streamBitratePropertiesChunk = new StreamBitratePropertiesChunk(Utils.readBig64(inputStream));
        long uint16 = Utils.readUINT16(inputStream);
        for (int i = 0; i < uint16; i++) {
            streamBitratePropertiesChunk.addBitrateRecord(Utils.readUINT16(inputStream) & 255, Utils.readUINT32(inputStream));
        }
        streamBitratePropertiesChunk.setPosition(j);
        return streamBitratePropertiesChunk;
    }
}
