package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.GUID;

/* loaded from: classes3.dex */
public interface ChunkReader {
    boolean canFail();

    GUID[] getApplyingIds();

    Chunk read(GUID guid, InputStream inputStream, long j) throws IOException;
}
