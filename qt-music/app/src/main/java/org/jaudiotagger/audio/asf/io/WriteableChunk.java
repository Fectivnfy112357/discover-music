package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.OutputStream;
import org.jaudiotagger.audio.asf.data.GUID;

/* loaded from: classes3.dex */
public interface WriteableChunk {
    long getCurrentAsfChunkSize();

    GUID getGuid();

    boolean isEmpty();

    long writeInto(OutputStream outputStream) throws IOException;
}
